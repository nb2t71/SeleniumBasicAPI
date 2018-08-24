package com.logigear.selenium.page.gmail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.logigear.selenium.page.railway.GeneralPage;
import com.logigear.selenium.utilities.constant.Constant;

public class GMailLoginPage extends GeneralPage {

	// Locators
	private final By txtPassword = By.xpath("//input[@name='password']");
	private final By btnNext = By.xpath("//div[@role='button']//span[text()='Next']");

	// Elements
	protected WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(txtPassword);
	}

	protected WebElement getBtnNext() {
		return Constant.WEBDRIVER.findElement(btnNext);
	}

	// Methods
//	public GMailLoginPage gotoGMailLoginPage() {
//		Constant.WEBDRIVER.navigate().to(Constant.GMAIL_URL);
//		return this;
//	}

	public GMailPage loginGMail(String strGMailPassword) {
		WebDriverWait webdriverwait = new WebDriverWait(Constant.WEBDRIVER, Constant.WEBDRIVER_IMPLICIT_WAIT);

		this.getBtnNext().click();
		Constant.WEBDRIVER.manage().timeouts().implicitlyWait(Constant.WEBDRIVER_IMPLICIT_WAIT, TimeUnit.SECONDS);

		webdriverwait.until(ExpectedConditions.visibilityOf(getTxtPassword())).sendKeys(Constant.GMAIL_PASSWORD);
		;
		webdriverwait.until(ExpectedConditions.elementToBeClickable(btnNext)).click();

		Constant.WEBDRIVER.manage().timeouts().implicitlyWait(Constant.WEBDRIVER_IMPLICIT_WAIT, TimeUnit.SECONDS);
		return new GMailPage();
	}

}
