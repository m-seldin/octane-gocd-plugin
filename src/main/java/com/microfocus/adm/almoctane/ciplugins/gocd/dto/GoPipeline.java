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

import com.microfocus.adm.almoctane.ciplugins.gocd.dto.GoMaterial;
import com.microfocus.adm.almoctane.ciplugins.gocd.dto.GoStage;

import java.util.List;

/**
 * DTO for a pipeline in Go.
 */
public class GoPipeline {

	private String name;
	private String label;
	private List<GoStage> stages;
	private List<GoMaterial> materials;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<GoStage> getStages() {
		return stages;
	}

	public void setStages(List<GoStage> stages) {
		this.stages = stages;
	}

	public List<GoMaterial> getMaterials() {
		return materials;
	}

	public void setMaterials(List<GoMaterial> materials) {
		this.materials = materials;
	}
}
