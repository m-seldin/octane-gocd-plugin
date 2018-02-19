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

import java.util.List;

/**
 * DTO for a material revision in a pipeline.
 */
public class GoMaterialRevision {

	private GoMaterial material;
	private boolean changed;
	private List<GoModification> modifications;

	public GoMaterial getMaterial() {
		return material;
	}

	public void setMaterial(GoMaterial material) {
		this.material = material;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public List<GoModification> getModifications() {
		return modifications;
	}

	public void setModifications(List<GoModification> modifications) {
		this.modifications = modifications;
	}
}
