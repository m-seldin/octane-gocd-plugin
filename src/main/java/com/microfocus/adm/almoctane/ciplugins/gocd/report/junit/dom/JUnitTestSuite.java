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

package com.microfocus.adm.almoctane.ciplugins.gocd.report.junit.dom;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * A {@link JUnitTestSuite} holds multiple {@link JUnitTestCase}s.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "testsuite")
public class JUnitTestSuite {

	@XmlAttribute(required = true)
	private String name;
	@XmlAttribute(required = true)
	private int tests;
	@XmlAttribute
	private int skipped;
	@XmlAttribute
	private int failures;
	@XmlAttribute
	private int errors;
	@XmlAttribute
	private String timestamp;
	@XmlAttribute
	private String hostname;
	@XmlAttribute
	private double time;

	@XmlElement(name = "property")
	@XmlElementWrapper(name = "properties")
	private List<JUnitProperty> properties;
	@XmlElement(name = "testcase")
	private List<JUnitTestCase> testCases;

	@XmlElement(name = "system-out")
	private String systemOut;
	@XmlElement(name = "system-err")
	private String systemErr;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTests() {
		return tests;
	}

	public void setTests(int tests) {
		this.tests = tests;
	}

	public int getSkipped() {
		return skipped;
	}

	public void setSkipped(int skipped) {
		this.skipped = skipped;
	}

	public int getFailures() {
		return failures;
	}

	public void setFailures(int failures) {
		this.failures = failures;
	}

	public int getErrors() {
		return errors;
	}

	public void setErrors(int errors) {
		this.errors = errors;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public List<JUnitProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<JUnitProperty> properties) {
		this.properties = properties;
	}

	public List<JUnitTestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(List<JUnitTestCase> testCases) {
		this.testCases = testCases;
	}

	public String getSystemOut() {
		return systemOut;
	}

	public void setSystemOut(String systemOut) {
		this.systemOut = systemOut;
	}

	public String getSystemErr() {
		return systemErr;
	}

	public void setSystemErr(String systemErr) {
		this.systemErr = systemErr;
	}
}
