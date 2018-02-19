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

/**
 * This DTO resembles the service response of <a href="https://plugin-api.gocd.org/current/notifications/#get-server-info">Get Server Info</a>
 */
public class GoServerInfo {

	@SerializedName("server_id")
	private String serverID;
	@SerializedName("site_url")
	private String siteURL;
	@SerializedName("secure_site_url")
	private String secureSiteURL;

	public String getServerID() {
		return serverID;
	}

	public void setServerID(String serverID) {
		this.serverID = serverID;
	}

	public String getSiteURL() {
		return siteURL;
	}

	public void setSiteURL(String siteURL) {
		this.siteURL = siteURL;
	}

	public String getSecureSiteURL() {
		return secureSiteURL;
	}

	public void setSecureSiteURL(String secureSiteURL) {
		this.secureSiteURL = secureSiteURL;
	}
}
