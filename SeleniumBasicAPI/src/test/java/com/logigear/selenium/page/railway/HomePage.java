package com.logigear.selenium.page.railway;

import com.logigear.selenium.utilities.constant.Constant;

public class HomePage extends GeneralPage {
//	private WebDriver driverHomePage = Constant.getWebDriver();
//	public HomePage() {
//		
//	}
//	
//	public HomePage(WebDriver driver) {
//		super();
//		this.driverHomePage = driver;
//	}
	// Locators

	// Elements

	// Methods
	public HomePage open() {
		Constant.getWebDriver().navigate().to(Constant.RAILWAY_URL);
		return this;
	}
}
