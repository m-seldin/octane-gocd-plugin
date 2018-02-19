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

package com.microfocus.adm.almoctane.ciplugins.gocd.plugin.settings;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

/**
 * This test ensure that parsing JSON into {@link OctaneGoCDPluginSettingsWrapper} works correctly.
 */
public class OctaneGoCDPluginSettingsWrapperTest {

	@Test
	public void testParsingJsonIntoValidWrapper() {
		final String json = "{ \"plugin-settings\": { " +
			" \"serverURL\"   : { \"value\": \"https://foobar.org\" }," +
			" \"clientID\"    : { \"value\": \"nobody\"             }," +
			" \"clientSecret\": { \"value\": \"password\"           }," +
			" \"goUsername\"  : { \"value\": \"charly\"             }," +
			" \"goPassword\"  : { \"value\": \"54123\"              }" +
			"}}";

		final OctaneGoCDPluginSettingsWrapper wrapper = new Gson().fromJson(json, OctaneGoCDPluginSettingsWrapper.class);
		Assert.assertNotNull("wrapper should not be null", wrapper);
		final OctaneGoCDPluginSettings settings = wrapper.getPluginSettings();
		Assert.assertNotNull("wrapper should contain settings", settings);
		Assert.assertEquals("serverURL should equal", "https://foobar.org", settings.getServerURL());
		Assert.assertEquals("clientID should equal", "nobody", settings.getClientID());
		Assert.assertEquals("clientSecret should equal", "password", settings.getClientSecret());
		Assert.assertEquals("goUsername should equal", "charly", settings.getGoUsername());
		Assert.assertEquals("goPassword should equal", "54123", settings.getGoPassword());
		Assert.assertTrue("wrapper should have valid settings", new SettingsValidator().validate(settings).isEmpty());
	}
}
