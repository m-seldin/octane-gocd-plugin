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

import com.microfocus.adm.almoctane.ciplugins.gocd.service.GoGetPipelineHistory;

import java.util.List;

/**
 * This DTO is the response of {@link GoGetPipelineHistory}.
 */
public class GoPipelineHistory {

	private List<GoPipelineInstance> pipelines;

	public List<GoPipelineInstance> getPipelines() {
		return pipelines;
	}

	public void setPipelines(List<GoPipelineInstance> pipelines) {
		this.pipelines = pipelines;
	}
}
