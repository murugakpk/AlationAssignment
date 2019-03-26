package com.test.alation.assignment;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.assignment.amazon.page.home.AmazonHomePage;
import com.assignment.asserts.AssignmentAssert;

public class SearchDataCatalog {
	WebDriver driver;

	@BeforeClass
	@Parameters("browser")
	public void setUp(String browser) {

		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Windows\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions(); 
			options.addArguments("disable-infobars"); 
			 driver = new ChromeDriver(options);
			} 
		else if (browser.equalsIgnoreCase("Firefox")) {
			File pathBinary = new File("C:\\Program Files (X86)\\Mozilla Firefox\\firefox.exe");
			FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
			System.setProperty("webdriver.firefox.marionette", "C:\\Users\\Perumal\\Desktop\\geckodriver.exe");
			FirefoxOptions opt = new FirefoxOptions();
			opt.setBinary(firefoxBinary);
			opt.setCapability("Platform", org.openqa.selenium.Platform.ANY);
			driver = new FirefoxDriver(opt);

		}
	}

	@Test(priority = 1)
	//Open Amazon.com
	public void openAmazon() {
		driver.get("https://www.amazon.com");
		driver.manage().timeouts().pageLoadTimeout(60000, TimeUnit.MILLISECONDS);
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
	}

	@Test(priority = 2)
	//Check for the category in the dropdown and select
	public void selectBooksCategory() throws SecurityException, Exception {
		AmazonHomePage page = new AmazonHomePage(driver);
		page.selectDropDownByVisibleText("Books");
	}

	@Test(priority = 3)
	//Check for the Text Field and input the search text and click Go button
	public void searchForDataCatalog() {
		AmazonHomePage page = new AmazonHomePage(driver);
		page.searchForCategory("data catalog");
		page.clickGoButton();
		
	}

	@Test(priority = 4)
	//Verify the first search Result and the product title
	public void verifyFirstSearchedResult() {
		AmazonHomePage page = new AmazonHomePage(driver);
		page.getTitleFortheSerachResult();
		page.fetchFilteredSearchResultByIndex(0);
	}

	@Test(priority = 5)
	//Verify if the first Search Result has Kindle Variant option
	public void verifyKindleBookMode() {
		AmazonHomePage page = new AmazonHomePage(driver);
		AssignmentAssert.assertTrue(page.isKindleLinkPresent(), "Kindle device mode");
		}
	@Test(priority = 6)
	//Verify if the first Search Result has Paper Back Variant option
	public void verifyPaperBackBookMode() {
		AmazonHomePage page = new AmazonHomePage(driver);
		AssignmentAssert.assertTrue(page.isPaperBackLinkPresent(), "Paper Back mode");
		}
	@Test(priority = 7)
	//Verify if the first Search Result has Hard Cover Variant option
	public void verifyHardCoverBookMode() {
		AmazonHomePage page = new AmazonHomePage(driver);
		AssignmentAssert.assertTrue(page.isHardCoverLinkPresent(), "Hard Cover mode");
		}
	@Test(priority = 8)
	//Verify Rating present for the first search result and number of reviewers not zero
	public void verifyRatingReviewers() {
		AmazonHomePage page = new AmazonHomePage(driver);
		page.getNumberOfReviewers();
		}
	@Test(priority = 9)
	//Verify currency symbol is $ as amazon.com
	public void verifyCurrencySymbolUSD (){
		AmazonHomePage page = new AmazonHomePage(driver);
		page.isCurrencySymbolUSD();
		}
	@Test(priority = 10)
	//Verify price value for the first search result is numeric
	public void verifyPriceIsNumericValue() {
		AmazonHomePage page = new AmazonHomePage(driver);
		page.isCurrencyNonAlphabetic();
		}
	@Test(priority = 11)
	//Verify item availability for the first search result
	public void verifyItemAvailability() {
		AmazonHomePage page = new AmazonHomePage(driver);
		page.isItemOutOfStock();
		page.isItemUnavailable();
		}
	
	@Test(priority = 12)
	//Verify item expected Delivery Data
	public void verifyItemDeliveryDate() {
		AmazonHomePage page = new AmazonHomePage(driver);
		page.getItemDeliveredOn();
		}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
