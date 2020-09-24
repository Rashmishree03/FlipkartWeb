package com.flipkart.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.qa.base.TestBase;

public class HomePage extends TestBase{

	@FindBy(xpath="//input[@title='Search for products, brands and more']")
	WebElement search;
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public MobilesPage searchProduct(String product)
	{
		search.sendKeys(product,Keys.ENTER);
		return new MobilesPage();
	}
}
