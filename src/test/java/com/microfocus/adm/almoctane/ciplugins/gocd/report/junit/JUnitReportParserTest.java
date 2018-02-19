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

package com.microfocus.adm.almoctane.ciplugins.gocd.report.junit;

import com.microfocus.adm.almoctane.ciplugins.gocd.report.junit.dom.JUnitTestCase;
import com.microfocus.adm.almoctane.ciplugins.gocd.report.junit.dom.JUnitTestSuite;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * Ensure that {@link JUnitReportParser} is working correctly.
 */
public class JUnitReportParserTest {

	@Test
	public void testParsingJUnit4Report() throws JAXBException {
		JUnitTestSuite testSuite = new JUnitReportParser().parseFrom(getClass().getClassLoader().getResourceAsStream("junit.testResults.xml"));
		Assert.assertNotNull("testSuite should not be null", testSuite);
		Assert.assertEquals("testSuite name", "com.microfocus.adm.almoctane.ciplugins.gocd.report.junit.JUnitReportParserTest", testSuite.getName());
		Assert.assertEquals("testSuite hostname", "hypo", testSuite.getHostname());
		Assert.assertEquals("testSuite timestamp", "2017-11-08T13:13:06", testSuite.getTimestamp());
		Assert.assertTrue("testSuite time is greater zero", 0 < testSuite.getTime());
		List<JUnitTestCase> testCase = testSuite.getTestCases();
		Assert.assertNotNull("testCase should not be null", testCase);
		Assert.assertFalse("testCase should not be empty", testCase.isEmpty());
	}
}
