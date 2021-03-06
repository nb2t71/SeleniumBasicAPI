package com.logigear.selenium.pageobjects.gmail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.logigear.selenium.constant.Constant;
import com.logigear.selenium.pageobjects.railway.GeneralPage;

public class GMailLoginPage extends GeneralPage{

	// Locators
	private final By txtPassword = By.xpath("//input[@name='password']");
	private final By btnNext = By.xpath("//div[@role='button']//span[text()='Next']");
	
	//Elements
	protected WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(txtPassword);
	}
	
	protected WebElement getBtnNext() {
		return Constant.WEBDRIVER.findElement(btnNext);
	}
	
	//Methods
//	public GMailLoginPage gotoGMailLoginPage() {
//		Constant.WEBDRIVER.navigate().to(Constant.GMAIL_URL);
//		return this;
//	}
	
	public GMailPage loginGMail(String strGMailPassword) {
		this.getBtnNext().click();
		Constant.WEBDRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		this.getTxtPassword().sendKeys(Constant.GMAIL_PASSWORD);
		WebDriverWait webdriverwait = new WebDriverWait(Constant.WEBDRIVER, 10);
		webdriverwait.until(ExpectedConditions.elementToBeClickable(btnNext)).click();	
		
		Constant.WEBDRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return new GMailPage();
	}
	
}
