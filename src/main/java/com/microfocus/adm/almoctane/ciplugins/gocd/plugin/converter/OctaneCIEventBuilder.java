/*
 *
 *  (c) Copyright 2018 Micro Focus or one of its affiliates.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 * /
 *
 */

package com.microfocus.adm.almoctane.ciplugins.gocd.plugin.converter;

import com.hp.octane.integrations.OctaneSDK;
import com.hp.octane.integrations.dto.DTOFactory;
import com.hp.octane.integrations.dto.causes.CIEventCause;
import com.hp.octane.integrations.dto.events.CIEvent;
import com.hp.octane.integrations.dto.events.CIEventType;
import com.hp.octane.integrations.dto.snapshots.CIBuildResult;
import com.microfocus.adm.almoctane.ciplugins.gocd.dto.GoPipelineInstance;
import com.microfocus.adm.almoctane.ciplugins.gocd.dto.GoStageInstance;
import com.microfocus.adm.almoctane.ciplugins.gocd.service.GoApiClient;
import com.microfocus.adm.almoctane.ciplugins.gocd.service.GoGetPipelineHistory;
import com.microfocus.adm.almoctane.ciplugins.gocd.service.GoGetPipelineInstance;
import com.microfocus.adm.almoctane.ciplugins.gocd.service.GoGetStageInstance;
import com.thoughtworks.go.plugin.api.logging.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * This build helps converting GoCD status information into {@link CIEvent}s Octane can understand.
 *
 * GoCD uses a stage-centric approach, meaning it will send state event, whenever a stage is about
 * to be build or finishes building. Octane on the other hand is only interested in the overall
 * pipeline and does not care about the stages in detail.
 *
 * @see <a href="https://plugin-api.gocd.org/current/notifications/#stage-start-notifications">Stage Start Notifications</a>
 * @see <a href="https://plugin-api.gocd.org/current/notifications/#stage-completion-notifications">Stage Completion Notifications</a>
 */
public class OctaneCIEventBuilder {

	/** All possible values for a state of a stage in a pipeline. */
	public enum PipelineStageState {
		Building,
		Passed,
		Failed,
		Cancelled
	}

	protected static final Logger Log = Logger.getLoggerFor(OctaneCIEventBuilder.class);

	private final GoApiClient goApiClient;
	private final OctaneSDK octaneInstance;

	public OctaneCIEventBuilder(final GoApiClient goApiClient, final OctaneSDK octaneInstance) {
		this.goApiClient = goApiClient;
		this.octaneInstance = octaneInstance;
	}

	/**
	 * This method will analyze the given {@param statusInfo} and may or may not generate
	 * and send a {@link CIEvent} to Octane.
	 */
	public void sendCIEvent(StatusInfoWrapper statusInfo) {
		if (statusInfo == null) {
			return;
		}

		final String pipelineName = statusInfo.getPipelineName();
		final Integer pipelineCounter = Integer.valueOf(statusInfo.getPipelineCounter());
		final GoPipelineInstance pipelineInstance = new GoGetPipelineInstance(goApiClient).get(pipelineName, pipelineCounter);
		final List<GoStageInstance> stages = pipelineInstance.getStages();

		if (PipelineStageState.Building.name().equals(statusInfo.getStageStatus())) {
			// only generate a start-event if the current stage is the very first one in the pipeline.
			if (stages != null && !stages.isEmpty() &&
				stages.get(0).getName().equals(String.valueOf(statusInfo.getStageName()))) {
				sendStartEvent(statusInfo);
			}
		} else if (PipelineStageState.Passed.name().equals(statusInfo.getStageStatus())) {
			// whenever a stage passes check whether it is the last stage of this pipeline; only then send an end-event.
			if (stages != null && !stages.isEmpty() &&
				stages.get(stages.size() - 1).getName().equals(String.valueOf(statusInfo.getStageName()))) {
				sendEndEvent(statusInfo);
			}
		} else {
			// all other state changes end a pipeline build.
			sendEndEvent(statusInfo);
		}
	}

	private void sendStartEvent(StatusInfoWrapper statusInfo) {
		final String pipelineName = statusInfo.getPipelineName();
		final String pipelineCounter = String.valueOf(statusInfo.getPipelineCounter());
		CIEvent event = DTOFactory.getInstance().newDTO(CIEvent.class)
			.setEventType(CIEventType.STARTED)
			.setProject(pipelineName)
			.setProjectDisplayName(pipelineName)
			.setBuildCiId(pipelineCounter)
			.setNumber(pipelineCounter)
			.setCauses(Collections.<CIEventCause>emptyList());

		Date createTime = parseTime(String.valueOf(statusInfo.getStageCreateTime()));
		if (createTime != null) {
			event.setStartTime(createTime.getTime());
		}

		// try to give an estimate about the expected building time.
		final List<Long> successfulDurations = getLastSuccessfulDurations(pipelineName, 3);
		Long estimatedDuration = null;
		Collections.reverse(successfulDurations); // since the newest instance should have the highest weight, start with the oldest instance.
		for (Long duration : successfulDurations) {
			if (estimatedDuration == null) {
				estimatedDuration = duration;
			} else {
				estimatedDuration = (long)(estimatedDuration * 0.5 + duration * 0.5);
			}
		}
		event.setEstimatedDuration(estimatedDuration);

		octaneInstance.getEventsService().publishEvent(event);
	}

	private void sendEndEvent(StatusInfoWrapper statusInfo) {
		final String pipelineName = statusInfo.getPipelineName();
		final String pipelineCounter = String.valueOf(statusInfo.getPipelineCounter());
		CIEvent event = DTOFactory.getInstance().newDTO(CIEvent.class)
			.setEventType(CIEventType.FINISHED)
			.setProject(pipelineName)
			.setProjectDisplayName(pipelineName)
			.setBuildCiId(pipelineCounter)
			.setNumber(pipelineCounter)
			.setCauses(Collections.<CIEventCause>emptyList());

		PipelineStageState stageState = statusInfo.getStageStatus();
		switch (stageState) {
			case Passed: event.setResult(CIBuildResult.SUCCESS); break;
			case Failed: event.setResult(CIBuildResult.FAILURE); break;
			case Cancelled: event.setResult(CIBuildResult.ABORTED); break;
		}

		// determine the start-time of this pipeline.
		GoPipelineInstance pipelineInstance = new GoGetPipelineInstance(goApiClient).get(pipelineName, Integer.valueOf(pipelineCounter));
		if (pipelineInstance != null) {
			Long firstScheduledDate = pipelineInstance.getFirstScheduledDate();
			// correct the start time to the first documented date.
			event.setStartTime(firstScheduledDate);
			Date lastTransitionTime = parseTime(String.valueOf(statusInfo.getStageLastTransitionTime()));
			if (lastTransitionTime != null && firstScheduledDate != null) {
				event.setDuration(lastTransitionTime.getTime() - firstScheduledDate); // in ms
			}
			// add repository information to the event.
			event.setScmData(new OctaneSCMDataBuilder().retrieveFrom(pipelineInstance));
		}

		octaneInstance.getEventsService().publishEvent(event);
		// tell octane to request the test results.
		octaneInstance.getTestsService().enqueuePushTestsResult(pipelineName, pipelineCounter);
	}

	/**
	 * This helper method help parsing a given time into a {@link Date}.
	 */
	public static Date parseTime(String time) {
		if (time == null) {
			return null;
		}
		/* Even though the time looks like being a Zulu-time it probably isn't.
		 * Please see whether bug https://github.com/gocd/gocd/issues/3989 is still open.
		 * The StageConverter used to render the time has probably used the default local to
		 * render the time and did just append the letter 'Z'. */
		try { // try to parse the time with an RFC822 timezone
			return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(time);
		} catch (ParseException e) {
			Log.warn("Could not parse given time with RFC822 timezone '" + time + "'");
		}
		try { // try to parse the time with the pattern the StageConverter was probably using
			return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(time);
		} catch (ParseException e) {
			Log.error("Could not parse given time with the assumed pattern", e);
		}
		return null; // giving up
	}

	/**
	 * This method collects the durations of the last successful pipeline runs.
	 * @param pipelineName name of the pipeline
	 * @param amount maximum number of durations
	 * @return found durations as a list. Never null. Might be less than the wanted amount.
	 */
	protected List<Long> getLastSuccessfulDurations(final String pipelineName, final int amount) {
		final List<Long> successfulDurations = new ArrayList<>();
		for (GoPipelineInstance instance : new GoGetPipelineHistory(goApiClient).get(pipelineName)) {
			if (successfulDurations.size() >= amount) {
				break; // enough durations collected.
			}
			if (!instance.isPassed()) {
				continue; // skip incomplete instances.
			}
			Long startTime = instance.getFirstScheduledDate();
			// PipelineInstance do not contain the jobTransitions, we have to query them.
			GoStageInstance stage = instance.getLastStage();
			if (stage != null) {
				GoStageInstance detailedStageInstance = new GoGetStageInstance(goApiClient).get(pipelineName, instance.getCounter(), stage.getName(), Integer.valueOf(stage.getCounter()));
				if (detailedStageInstance != null) {
					Long lastTransitionTime = detailedStageInstance.getLastJobTransitionDate();
					if (lastTransitionTime != null) {
						successfulDurations.add(lastTransitionTime - startTime);
					}
				}
			}

		}
		return successfulDurations;
	}
}
