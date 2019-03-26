package com.assignment.listener;

import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AssignmentLogger implements ITestListener {
	private static final Logger LOGGER = Logger.getLogger(AssignmentLogger.class.getName());

	@Override
	public void onTestStart(ITestResult result) {
		LOGGER.info(result.getStartMillis() + " started");

	}
	
    @Override
    public void onTestFailure(ITestResult result) {
    	System.out.println("***** Error "+result.getName()+" test has failed *****");
    }
    
    
	

	@Override
	public void onTestSuccess(ITestResult result) {
		LOGGER.info(result.getEndMillis() + "  test finished and passed");

	}

	
	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext testContext) {

		LOGGER.info("PASSED TEST CASES");
		testContext.getPassedTests().getAllResults().forEach(result -> {
			LOGGER.info(result.getName());
		});

		LOGGER.info("FAILED TEST CASES");
		testContext.getFailedTests().getAllResults().forEach(result -> {
			LOGGER.info(result.getName());
		});

		LOGGER.info("Test completed on: " + testContext.getEndDate().toString());

	}


}
