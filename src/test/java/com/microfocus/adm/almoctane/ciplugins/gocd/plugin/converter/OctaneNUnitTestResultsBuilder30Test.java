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

package com.microfocus.adm.almoctane.ciplugins.gocd.plugin.converter;

import com.hp.octane.integrations.dto.tests.TestRun;
import com.hp.octane.integrations.dto.tests.TestRunResult;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * This class ensures that {@link OctaneNUnit25TestResultsBuilder} is working correctly.
 */
public class OctaneNUnitTestResultsBuilder30Test {

	@Test
	public void testConversionFromNUnitXMLIntoOctaneModel01() throws JAXBException {
		final List<TestRun> tests = OctaneNUnit30TestResultsBuilder.convert(getClass().getClassLoader().getResourceAsStream("nunit30.testResults.xml"));
		Assert.assertNotNull("tests should not be null", tests);
		Assert.assertEquals("number of tests", 27, tests.size());
		TestRun testRun = tests.get(1);
		Assert.assertEquals("package name", "MicroFocus.PT.Api.SystemTests.AP.Tests", testRun.getPackageName());
		Assert.assertEquals("class name", "JournalTransactionTests", testRun.getClassName());
		Assert.assertEquals("test name", "GlEntries_ForNonStockItemsWithNoOtherDiscountInPurchaseOrdder_CorrectAccountsArePostedToGl(\"EU\")", testRun.getTestName());
		Assert.assertEquals("test result", TestRunResult.PASSED, testRun.getResult());
	}

	@Test
	public void testConversionFromNUnitXMLIntoOctaneModel02() throws JAXBException {
		final List<TestRun> tests = OctaneNUnit30TestResultsBuilder.convert(getClass().getClassLoader().getResourceAsStream("nunit30.failed.testResults.xml"));
		Assert.assertNotNull("tests should not be null", tests);
		Assert.assertEquals("number of tests", 27, tests.size());
		TestRun testRun = tests.get(1);
		Assert.assertEquals("package name", "MicroFocus.PT.Api.SystemTests.AP.Tests", testRun.getPackageName());
		Assert.assertEquals("class name", "JournalTransactionTests", testRun.getClassName());
		Assert.assertEquals("test name", "GlEntries_ForNonStockItemsWithNoOtherDiscountInPurchaseOrdder_CorrectAccountsArePostedToGl(\"EU\")", testRun.getTestName());
		Assert.assertEquals("test result", TestRunResult.FAILED, testRun.getResult());
		Assert.assertNotNull("error should not be null", testRun.getError());
		Assert.assertEquals("error message", "GL should contain transactions for following account. 5400\n" +
			"1406\n" +
			"1401\n" +
			"3300\n" +
			"5200\n" +
			"5200\n\n", testRun.getError().getErrorMessage());
		Assert.assertEquals("error stackTrace", "   at MicroFocus.PT.Api.SystemTests.TestBase.GlAssert.Verify(GlExpectation expected, JournalTransaction actual)\n", testRun.getError().getStackTrace());
	}
}
