package com.flipkart.qa.testcases;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flipkart.qa.base.TestBase;
import com.flipkart.qa.pages.HomePage;
import com.flipkart.qa.pages.LoginPage;
import com.flipkart.qa.pages.MobilesPage;
import com.flipkart.qa.util.TestUtil;

public class SearchProduct extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	MobilesPage mobilesPage;
	
	SearchProduct()
	{
		//calling Test Base class constructor to instantiate property file
		super();
	}
	
	@BeforeMethod
	public void setUp() throws Exception
	{
		//getting the driver
		initialization();
		//logging in to Flipkart using mobile no
		loginPage = new LoginPage();
		homePage = loginPage.loginToFlipkart(prop.getProperty("mobileno"), prop.getProperty("password"));
	}
	
	@Test
	public void searchProduct_withinThePriceRange() throws Exception
	{
		//Below product and maxPrice can be taken as a parameter from the xml
		String product = "iphone";
		int maxPrice = 40000;
		
		//to store all the retrieved data
		List<String[]> data = new ArrayList<String[]>();
		
		//searhing phones with the given keyword
		mobilesPage = homePage.searchProduct(product);
		
		//filtering displayed products by max price
		mobilesPage.selectMaxAmt("50000");
		Thread.sleep(3000);
		
		//finding next button and loops over till next becomes null
		WebElement next = mobilesPage.nextButton();
		while(!(next==null))
		{
			//finding the price of all the products and looping based on its size
			List<WebElement> amount = mobilesPage.retrievePrice();
			for(int i=0;i<amount.size();i++)
			{
				//fetching and price and manipulating the string to compare with the maxPrice
				String str_price = amount.get(i).getText();
				str_price = str_price.replaceAll(str_price.substring(0, 1), "");
				str_price = str_price.replaceAll(",", "");
				int int_price = Integer.parseInt(str_price);
				
				//comparing amount of each product is lesser than max price specified or not
				//if yes brand and rating details will be fetched for the same
				if(int_price < maxPrice)
				{
					//retrieving brand
					WebElement brandElem = mobilesPage.retrieveBrand(i+1);
					String brand = brandElem.getText();
					System.out.println("brand"+brand);
					
					//retrieving rating
					WebElement ratingElem = mobilesPage.retrieveRating(i+1);
					String rating = ratingElem.getText();
					rating = rating.split(" ")[0]; 
					System.out.println("rating"+rating);
					
					//storing in brand , price and rating in an array
					String[] details = {brand,str_price,rating};
					//adding array to the list
					data.add(details);
				}
			}
			//clicking on nextbutton if exists
			mobilesPage.nextButton().click();
			Thread.sleep(2000);
			next = mobilesPage.nextButton();
		}
		//sorting stored records by amout
		TestUtil.sortDataByIndex(data, 1);
		//writing data into csv file
		TestUtil.writeCSV(data);
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
