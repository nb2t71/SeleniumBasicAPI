package com.logigear.selenium.pageobjects.gmail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import com.logigear.selenium.constant.Constant;
import com.logigear.selenium.pageobjects.railway.GeneralPage;

public class GMailPage extends GeneralPage{
	
	//Locators
	
	//Elements
	
	//Methods
	public GMailPage open() {
		Constant.WEBDRIVER.navigate().to(Constant.GMAIL_URL);
		return this;
	}
	
	
	public void activeAccount(String strMail) {
		while(Constant.WEBDRIVER.findElements(By.xpath("//span[contains(.,'"+strMail+"')]")).size()==0) {
			Constant.WEBDRIVER.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			Constant.WEBDRIVER.navigate().refresh();
		}
		
		Actions actions = new Actions(Constant.WEBDRIVER);
		actions.moveToElement(Constant.WEBDRIVER.findElement(By.xpath("//span[contains(.,'"+strMail+"')]"))).click().perform();
		
		Constant.WEBDRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Constant.WEBDRIVER.findElement(By.xpath("//div[contains(text(),'Your confirmation code is')]/a")).click();
	}
}
