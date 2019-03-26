package com.assignment.asserts;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class AssignmentAssert {
	private static SoftAssert softAssert = new SoftAssert();
	private static final Logger LOGGER =Logger.getLogger(AssignmentAssert.class.getName());
	
	public static void assertTrue(boolean condition, String message) {
		if(condition) {
			Assert.assertTrue(condition);
			logSuccessMessage(message);
		}
		else {
			Assert.assertTrue(condition);
			logFailureMessage(message);
		}
		
	}

	private static void logSuccessMessage(String message) {
		LOGGER.info(message+": PASSED");
	}
	private static void logFailureMessage(String message) {
		LOGGER.info(message+": FAILED");
	}
	public static void assertEquals(String actual, String expected, String message) {
		if(actual.equals(expected)) {
			Assert.assertEquals(actual,expected);
			logSuccessMessage(message);
		}
		else {
			Assert.assertEquals(actual,expected);
			logFailureMessage(message);
		}
		
	}

	public static void isElementPresent(WebElement element, String message) {
		if(element.isDisplayed()) {
			softAssert.assertTrue(true);
			logSuccessMessage(message);
			}
		else {
			softAssert.assertTrue(false);
			logFailureMessage(message);
			}
	}
	
	public static void isElementCLickable(WebElement element, String message) {
		if(element.isEnabled()) {
			softAssert.assertTrue(true);
			logSuccessMessage(message);
			}
		else {
			softAssert.assertTrue(false);
			logFailureMessage(message);
			}
	}
	public static void verifyTrue(boolean condition, String message) {
		if(condition) {
			softAssert.assertTrue(condition);
			logSuccessMessage(message);
		}
		else {
			softAssert.assertTrue(condition);
			logFailureMessage(message);
			}
		
	}

	public static void verifyEquals(String actual, String expected, String message) {
		if(actual.equals(expected)) {
			softAssert.assertEquals(actual,expected);
			logSuccessMessage(message);
		}
		else {
			softAssert.assertEquals(actual,expected);
			logFailureMessage(message);
			}
		
	}


	public static void takeScreenShot(String methodName, WebDriver driver) {
   	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
   	 System.out.println(scrFile);
        //The below method will save the screen shot in d drive with test method name 
           try {
        		String filePath = System.getProperty("user.dir");
				FileHandler.copy(scrFile, new File(filePath+methodName+".png"));
				System.out.println("***Placed screen shot in "+filePath+" ***");
			} catch (IOException e) {
				e.printStackTrace();
			}
   }
	
}
