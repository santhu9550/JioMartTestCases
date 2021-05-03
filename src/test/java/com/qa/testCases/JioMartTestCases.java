package com.qa.testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.Utilities.ExcelUtility;
import com.qa.pages.JioMartPages;
import com.qa.BaseTest.BaseTest;

public class JioMartTestCases extends BaseTest {

	@Test(dataProvider = "getData")
	public void SearchTest(String inp) throws InterruptedException {
		JioMartPages page = new JioMartPages(driver);
		page.getSearchBox().sendKeys(inp, Keys.ENTER);
		Thread.sleep(2000);
		boolean noResults;
		try {
			page.getIsNoResult().isDisplayed();
			noResults = true;
		} catch (NoSuchElementException e) {
			noResults = false;
		}
		if (noResults) {
			Reporter.log("No Item Found", true);

		} else {
			List<WebElement> results = page.getSearchResults();
			String title;
			String price;
			for (WebElement result : results) {
				title = result.findElement(By.className("clsgetname")).getText();
				price = result.findElement(By.id("final_price")).getText();
				Reporter.log("price of " + title + " : " + price, true);

			}
		}
		

	}
	
	@DataProvider
	public String[][] getData() throws IOException {
		String path = "C:\\Users\\santh\\eclipse-workspace\\JioMart\\src\\test\\java\\com\\qa\\testdata\\inpData .xlsx";
		String sheet = "Sheet1";
		int rowCount = ExcelUtility.getRowCount(path, sheet);
		int cellCount = ExcelUtility.getCellCount(path, sheet, rowCount);
		String[][] data = new String[rowCount][cellCount];
		for  (int i = 1; i<= rowCount; i++) {
			for(int j = 0; j< cellCount ; j++) {
				data[i-1][j] =  ExcelUtility.getCellData(path, sheet, i, j);
			}

			
		}
		
		return data;
	}

}

