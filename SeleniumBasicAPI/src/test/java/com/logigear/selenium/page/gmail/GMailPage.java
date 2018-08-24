package com.logigear.selenium.page.gmail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.logigear.selenium.page.railway.GeneralPage;
import com.logigear.selenium.utilities.constant.Constant;

public class GMailPage extends GeneralPage {

	private WebDriver driverGMailPage;

	public GMailPage() {

	}

	public GMailPage(WebDriver driver) {
		super();
		this.driverGMailPage = driver;
	}
	// Locators

	// Elements

	// Methods
	public GMailPage open() {
		Constant.WEBDRIVER.navigate().to(Constant.GMAIL_URL);
		return this;
	}

	public void activeAccount(String strMail) {
		while (driverGMailPage.findElements(By.xpath("//span[contains(.,'" + strMail + "')]")).size() == 0) {
			driverGMailPage.manage().timeouts().implicitlyWait(Constant.WEBDRIVER_IMPLICIT_WAIT * 3, TimeUnit.SECONDS);
			driverGMailPage.navigate().refresh();
		}

		Actions actions = new Actions(Constant.WEBDRIVER);
		actions.moveToElement(driverGMailPage.findElement(By.xpath("//span[contains(.,'" + strMail + "')]"))).click()
				.perform();

		driverGMailPage.manage().timeouts().implicitlyWait(Constant.WEBDRIVER_IMPLICIT_WAIT, TimeUnit.SECONDS);
		driverGMailPage.findElement(By.xpath("//div[contains(text(),'Your confirmation code is')]/a")).click();
	}
}
