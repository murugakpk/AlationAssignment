package com.assignment.util;

import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class AssignmentAssert {
	private static final Logger LOGGER = Logger.getLogger(AssignmentAssert.class.getName());
	private static SoftAssert softAssert = new SoftAssert();
	public static void assertTrue(boolean result, String msg) {
		if (result == true) {
			LOGGER.info(msg + " Passed");
			Assert.assertTrue(result);

		} else {

			LOGGER.info("Expected true but found :" + result + " for " + msg + " Failed");
			Assert.assertTrue(result);

		}
	}
	public static void verifyTrue(boolean actual, String msg) {

		if (actual == true) {
			softAssert.assertTrue(actual);
			LOGGER.info(msg + " Passed");
		} else {
			LOGGER.info("Expected true but found :" + actual + " for " + msg + " Failed");
			softAssert.assertTrue(actual);

		}
	}

}
