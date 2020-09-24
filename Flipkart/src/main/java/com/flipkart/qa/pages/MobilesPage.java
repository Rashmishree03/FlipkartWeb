package com.flipkart.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.flipkart.qa.base.TestBase;

public class MobilesPage extends TestBase {

	@FindBy(xpath="//div[contains(@data-id,'MOB')]//div[contains(@class,'NK')]")
	WebElement amount;
	
	@FindBy(xpath="//div[contains(@data-id,'MOB')]//div[contains(@class,'row')]//div[contains(@class,'_3wU53n')]")
	WebElement brand;
	
	@FindBy(xpath="//div[contains(@data-id,'MOB')]//div[contains(@class,'row')]//span[contains(@class,'_38sUEc')]/span/span[1]")
	WebElement rating;
	
	@FindBy(xpath="//div[@class='_1YoBfV']//select[@class='fPjUPw']")
	WebElement amtFilter;
	
	@FindBy(xpath="//a[@class='_3fVaIS']//span[text()='Next']")
	WebElement nextBtn;
	
	public MobilesPage()
	{
		PageFactory.initElements(driver, this);	
	}
	
	public List<WebElement> retrievePrice()
	{
		List<WebElement> amt = driver.findElements(By.xpath("//div[contains(@data-id,'MOB')]//div[contains(@class,'NK')]"));
		return amt;
	}
	
	public WebElement retrieveBrand(int i)
	{
		
		WebElement _brand = driver.findElement(By.xpath("(//div[contains(@data-id,'MOB')]//div[contains(@class,'row')]//div[contains(@class,'_3wU53n')])["+i+"]"));
		return _brand;
	}
	
	public WebElement retrieveRating(int index)
	{
		WebElement _rating = driver.findElement(By.xpath("(//div[contains(@data-id,'MOB')]//div[contains(@class,'row')]//span[contains(@class,'_38sUEc')]/span/span[1])["+index+"]"));
		return _rating;
	}
	
	public void selectMaxAmt(String amt)
	{
		Select sel = new Select(amtFilter);
		sel.selectByValue(amt);
	}
	
	public WebElement nextButton()
	{
		WebElement nextBtn = null;
		try {
			nextBtn = driver.findElement(By.xpath("//a[@class='_3fVaIS']//span[text()='Next']"));
		}catch(Exception e){}
		return nextBtn;
	}
}
