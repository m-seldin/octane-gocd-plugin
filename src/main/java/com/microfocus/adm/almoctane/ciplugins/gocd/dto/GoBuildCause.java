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
import com.microfocus.adm.almoctane.ciplugins.gocd.dto.GoMaterialRevision;

import java.util.List;

/**
 * This DTO represent the cause of a build.
 */
public class GoBuildCause {

	private String approver;
	@SerializedName("trigger_forced")
	private boolean triggerForced;
	@SerializedName("trigger_message")
	private String triggerMessage;
	@SerializedName("material_revisions")
	private List<GoMaterialRevision> materialRevision;

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public boolean isTriggerForced() {
		return triggerForced;
	}

	public void setTriggerForced(boolean triggerForced) {
		this.triggerForced = triggerForced;
	}

	public String getTriggerMessage() {
		return triggerMessage;
	}

	public void setTriggerMessage(String triggerMessage) {
		this.triggerMessage = triggerMessage;
	}

	public List<GoMaterialRevision> getMaterialRevision() {
		return materialRevision;
	}

	public void setMaterialRevision(List<GoMaterialRevision> materialRevision) {
		this.materialRevision = materialRevision;
	}
}
