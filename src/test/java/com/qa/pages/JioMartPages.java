package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JioMartPages {
	WebDriver driver;

	@FindBy(xpath = "//input[@id='search']")
	WebElement searchBox;

	@FindAll(@FindBy(xpath = "//*[@id=\"hits\"]/div/div/ol/li"))
	List <WebElement> searchResults;
	
	
	@FindBy(xpath = "//span[contains(text(),'Sorry! No product found')]")
	WebElement noResult;

	public WebElement getSearchBox() {
		return searchBox;
	}

	public List <WebElement> getSearchResults() {
		return searchResults;
	}
	
	public WebElement getIsNoResult() {
		return noResult;
	}

	public JioMartPages(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

}

