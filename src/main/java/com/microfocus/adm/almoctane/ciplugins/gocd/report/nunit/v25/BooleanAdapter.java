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

package com.microfocus.adm.almoctane.ciplugins.gocd.report.nunit.v25;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * In NUnit xml file boolean values are written as "True" or "False".
 * This adapter helps converting them.
 */
public class BooleanAdapter extends XmlAdapter<String,Boolean> {

	@Override
	public Boolean unmarshal(String value) throws Exception {
		if ("True".equals(value)) {
			return Boolean.TRUE;
		} else if ("False".equals(value)) {
			return Boolean.FALSE;
		}
		return null;
	}

	@Override
	public String marshal(Boolean value) throws Exception {
		if (Boolean.TRUE.equals(value)) {
			return "True";
		} else if (Boolean.FALSE.equals(value)) {
			return "False";
		}
		return null;
	}
}
