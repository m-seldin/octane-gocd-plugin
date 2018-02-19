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
import com.microfocus.adm.almoctane.ciplugins.gocd.dto.GoApprovalConfig;
import com.microfocus.adm.almoctane.ciplugins.gocd.dto.GoEnvironmentVariable;
import com.microfocus.adm.almoctane.ciplugins.gocd.dto.GoJobConfig;

import java.util.List;

/**
 * This DTO represents a stage config.
 */
public class GoStageConfig {

	private String name;
	@SerializedName("fetch_materials")
	private boolean fetchMaterials;
	@SerializedName("clean_working_directory")
	private boolean cleanWorkingDirectory;
	@SerializedName("never_cleanup_artifacts")
	private boolean neverCleanupArtifacts;
	private GoApprovalConfig approval;
	@SerializedName("environment_variables")
	private List<GoEnvironmentVariable> environmentVariables;
	private List<GoJobConfig> jobs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFetchMaterials() {
		return fetchMaterials;
	}

	public void setFetchMaterials(boolean fetchMaterials) {
		this.fetchMaterials = fetchMaterials;
	}

	public boolean isCleanWorkingDirectory() {
		return cleanWorkingDirectory;
	}

	public void setCleanWorkingDirectory(boolean cleanWorkingDirectory) {
		this.cleanWorkingDirectory = cleanWorkingDirectory;
	}

	public boolean isNeverCleanupArtifacts() {
		return neverCleanupArtifacts;
	}

	public void setNeverCleanupArtifacts(boolean neverCleanupArtifacts) {
		this.neverCleanupArtifacts = neverCleanupArtifacts;
	}

	public GoApprovalConfig getApproval() {
		return approval;
	}

	public void setApproval(GoApprovalConfig approval) {
		this.approval = approval;
	}

	public List<GoEnvironmentVariable> getEnvironmentVariables() {
		return environmentVariables;
	}

	public void setEnvironmentVariables(List<GoEnvironmentVariable> environmentVariables) {
		this.environmentVariables = environmentVariables;
	}

	public List<GoJobConfig> getJobs() {
		return jobs;
	}

	public void setJobs(List<GoJobConfig> jobs) {
		this.jobs = jobs;
	}
}
