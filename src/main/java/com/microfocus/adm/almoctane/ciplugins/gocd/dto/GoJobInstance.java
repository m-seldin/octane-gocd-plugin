/*
 * (c) Copyright 2018 Micro Focus or one of its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */


package com.microfocus.adm.almoctane.ciplugins.gocd.dto;

import com.google.gson.annotations.SerializedName;
import com.microfocus.adm.almoctane.ciplugins.gocd.dto.GoJobStateTransition;

import java.util.List;

/**
 * This DTO is an instance of a job.
 */
public class GoJobInstance {

	private int id;
	private String name;
	@SerializedName("agent_uuid")
	private String agentUuid;
	@SerializedName("scheduled_date")
	private long scheduledDate;
	private String result;
	private String state;
	@SerializedName("job_state_transitions")
	private List<GoJobStateTransition> jobStateTransitions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgentUuid() {
		return agentUuid;
	}

	public void setAgentUuid(String agentUuid) {
		this.agentUuid = agentUuid;
	}

	public long getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(long scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<GoJobStateTransition> getJobStateTransitions() {
		return jobStateTransitions;
	}

	public void setJobStateTransitions(List<GoJobStateTransition> jobStateTransitions) {
		this.jobStateTransitions = jobStateTransitions;
	}

	public Long getLastJobTransitionDate() {
		if (jobStateTransitions != null && !jobStateTransitions.isEmpty()) {
			return jobStateTransitions.get(jobStateTransitions.size() - 1).getStateChangeTime();
		} else {
			return null;
		}
	}

	public Long getDuration() {
		Long lastTransitionDate = getLastJobTransitionDate();
		if (lastTransitionDate != null) {
			return lastTransitionDate - scheduledDate;
		} else {
			return null;
		}
	}
}
