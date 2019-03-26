package com.assignment.amazon.page.home;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.assignment.asserts.AssignmentAssert;

public class AmazonHomePage {
	private static final Logger LOGGER = Logger.getLogger(AmazonHomePage.class.getName());

	
	WebDriver driver;
	@FindBy(id="searchDropdownBox")
	WebElement searchDropDown;
	@FindBy(id="twotabsearchtextbox")
	WebElement searchTextBox;
	@FindBy(xpath="//input[@value='Go']")
	WebElement goSearchButton;
	@FindBy(xpath="//div[@data-index='0']//h5/a") WebElement firstSearchResult;
	@FindBy(xpath="//div[@data-index='0']//span//i[1]") WebElement starRatingLink;
	@FindBy(id="histogramTable") WebElement ratingTable;
	@FindBy(xpath="//div[@data-index='0']//a[contains(text(),'Paperback')]") WebElement paperBackLink;
	@FindBy(xpath="//div[@data-index='0']//a[contains(text(),'Hardcover')]") WebElement hardCoverLink;
	@FindBy(xpath="//div[@data-index='0']//a[contains(text(),'Kindle')]") WebElement kindleLink;
	@FindBys(value = { @FindBy(xpath="//div[@data-index='0']//a") }) List<WebElement> allLinks;
	@FindBys(value = { @FindBy(xpath="//div[@data-index]") }) List<WebElement> allResults;
	@FindBy(xpath="//div[@data-index='0']//span//a[contains(@href,'#customerReviews')]") WebElement numberOfReviewersElement;
	@FindBy(xpath="//div[@data-index='0']//span[@aria-label]/span[@class='a-text-bold']") WebElement itemAvailable;
	@FindBy(xpath="//div[@data-index='0']//span[contains(@aria-label,'out of stock')]") WebElement outOfStockElement;
	@FindBy(xpath="//div[@data-index='0']//span[contains(@aria-label,'unavailable')]") WebElement itemUnavailable;
	@FindBy(xpath="//div[@data-index='0']//span[contains(@aria-label,'as soon as')]") WebElement getItAsSoonAs;
	@FindBys(value= {@FindBy(xpath="//div[@data-index='0']//span[@class='a-price']")})List<WebElement> priceValue;
	@FindBy(xpath="//span[contains(text(),'results for')]") WebElement totalSearchResults;
	public AmazonHomePage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
	
	public void selectDropDownByVisibleText(String text) throws SecurityException, Exception {
		AssignmentAssert.isElementPresent(this.searchDropDown, "Category dropdown");
		Select select = new Select(this.searchDropDown);
		select.selectByVisibleText(text);
		System.out.println(select.getFirstSelectedOption().getText());
		AssignmentAssert.assertEquals(select.getFirstSelectedOption().getText(),text, "Dropdown for "+text);
		
	}
	
	public void searchForCategory(String category) {
		AssignmentAssert.isElementPresent(this.searchTextBox, "Search text field");
		this.searchTextBox.sendKeys(category);
	}
	public void clickGoButton() {
		AssignmentAssert.isElementCLickable(this.goSearchButton, "Go Search Button");
		this.goSearchButton.click();
	}
	
	
	public void clickonSearchResult() {
		AssignmentAssert.isElementCLickable(this.firstSearchResult, "First Search Result Link");
		this.firstSearchResult.click();
	}
	public String  getTitleFortheSerachResult() {
		AssignmentAssert.isElementPresent(this.firstSearchResult, "First Search Result");
		LOGGER.info("Product title for the serach Result"+this.firstSearchResult.getText());
		AssignmentAssert.assertTrue(this.firstSearchResult.getText().trim().length()>0, "Title is not empty");
		return this.firstSearchResult.getText();
	}
	
	public String getRatingForSearchedResult() {
		Actions hoverAndGetText = new Actions(driver);
		hoverAndGetText.moveToElement(this.starRatingLink).perform();
		LOGGER.info("Rating for the product"+this.ratingTable.getText());

		return this.ratingTable.getText();
	}
	public String getNumberOfReviewers() {
		AssignmentAssert.isElementPresent(this.numberOfReviewersElement, "Ratings for the first Search Item");
		LOGGER.info("Number of reviewers : "+this.numberOfReviewersElement.getText());
		AssignmentAssert.assertTrue(Integer.valueOf(this.numberOfReviewersElement.getText().trim())>0, "Number of reviewers for the result greater than 0");
		return this.numberOfReviewersElement.getText();
	}
	public void getAllLinks() {
	
		for(WebElement element:this.allLinks) {
			LOGGER.info("Links Present for the first search result"+element.getText());
		}
		
	}
	public boolean isKindleLinkPresent() {
		for(WebElement element:this.allLinks) {

			if(element.getText().equalsIgnoreCase("Kindle")){
				LOGGER.info("Link value "+element.getText());
				return true;
			}
		}
		return false;
	}
	public boolean isHardCoverLinkPresent() {
			if(this.hardCoverLink.getText().equalsIgnoreCase("Hardcover")){
				LOGGER.info("Link value "+hardCoverLink.getText());
				return true;
			}
			else {
				return false;
			}
	}
	public boolean isPaperBackLinkPresent() {

			if(this.paperBackLink.getText().trim().equalsIgnoreCase("Paperback")){
				LOGGER.info("Link value "+paperBackLink.getText());
				return true;
			}
			else {
				return false;
			}
	}
	public String fetchFilteredSearchResultByIndex(int index) {
		int size=this.allResults.size();
		for(int i=0;i<size;i++) {
			if(i==index) {
			WebElement searchedItem=this.allResults.get(index);
			return searchedItem.getText();
			}
			
		}
		return null;
		
	}
	
	public String getItemDeliveredOn() {
		AssignmentAssert.isElementPresent(this.getItAsSoonAs, "Item Expected delivery date display");
		LOGGER.info(this.getItAsSoonAs.getText());
		return this.getItAsSoonAs.getText();
	}
	
	public boolean isItemOutOfStock() {
		if(!this.outOfStockElement.isDisplayed())
			{
			LOGGER.info(this.outOfStockElement.getText());
			
			}
		else {
			return false;
		}
		return false;
	}
	
	public boolean isItemUnavailable() {
		if(!"".equalsIgnoreCase(this.itemUnavailable.getText()))
		{LOGGER.info(this.itemUnavailable.getText());

			return true;}
		else {
	
		return false;
		}
	}
	
	public boolean isCurrencySymbolUSD() {
		for(WebElement priceSymbol :this.priceValue) {
			if(priceSymbol.getText().contains("$")) {
				AssignmentAssert.assertTrue(true, "Price in USD currency");			
				}
			else {
				return false;
			}
			}
		
		return true;
	}
	public void isCurrencyNonAlphabetic() {
		Float value;
		for(WebElement priceSymbol :this.priceValue) {
			String price=priceSymbol.getText().replace("$", "");
			if(price.contains("\n")) {
				price=price.replaceAll("\n", ".");
				 value=Float.parseFloat(price);
				 AssignmentAssert.assertEquals(String.valueOf(value),price, "Price value is numeric");
			}
			else {
			 value=Float.parseFloat(price);
			 AssignmentAssert.assertEquals(String.valueOf(value),price, "Price value is numeric");
			}
		}
	
	}
	
	
	public String getSearchResults() {
		this.totalSearchResults.getText();
		LOGGER.info("Total Number of search results"+this.totalSearchResults.getText());
		return this.totalSearchResults.getText();
		
	}
}
