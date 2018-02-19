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
import com.microfocus.adm.almoctane.ciplugins.gocd.dto.*;

import java.util.List;

/**
 * This DTO represents the config of a job.
 */
public class GoJobConfig {

	private String name;
	@SerializedName("environment_variables")
	private List<GoEnvironmentVariable> environmentVariables;
	private List<String> resources;
	private List<GoTaskConfig> tasks;
	private List<GoTabConfig> tabs;
	private List<GoArtifactConfig> artifacts;
	private List<GoPropertyConfig> properties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GoEnvironmentVariable> getEnvironmentVariables() {
		return environmentVariables;
	}

	public void setEnvironmentVariables(List<GoEnvironmentVariable> environmentVariables) {
		this.environmentVariables = environmentVariables;
	}

	public List<String> getResources() {
		return resources;
	}

	public void setResources(List<String> resources) {
		this.resources = resources;
	}

	public List<GoTaskConfig> getTasks() {
		return tasks;
	}

	public void setTasks(List<GoTaskConfig> tasks) {
		this.tasks = tasks;
	}

	public List<GoTabConfig> getTabs() {
		return tabs;
	}

	public void setTabs(List<GoTabConfig> tabs) {
		this.tabs = tabs;
	}

	public List<GoArtifactConfig> getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(List<GoArtifactConfig> artifacts) {
		this.artifacts = artifacts;
	}

	public List<GoPropertyConfig> getProperties() {
		return properties;
	}

	public void setProperties(List<GoPropertyConfig> properties) {
		this.properties = properties;
	}
}
