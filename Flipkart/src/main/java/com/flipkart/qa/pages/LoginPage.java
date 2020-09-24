package com.flipkart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement login;
	
	@FindBy(xpath="//div[contains(@class,'row')]//input[@type='text']")
	WebElement mobile;
	
	@FindBy(xpath="//div[contains(@class,'row')]//input[@type='password']")
	WebElement password;
	
	@FindBy(xpath="//div[contains(@class,'row')]//button[@type='submit']")
	WebElement submitLogin;
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);	
	}
	
	public HomePage loginToFlipkart(String mob, String pwd) throws Exception
	{
		mobile.sendKeys(mob);
		password.sendKeys(pwd);
		submitLogin.click();
		return new HomePage();
	}

}
