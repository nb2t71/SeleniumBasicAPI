package com.logigear.selenium.pageobjects.railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.logigear.selenium.constant.Constant;

public class LoginPage extends GeneralPage {

//	private WebDriver driverLoginPage;
//	public LoginPage() {
//		
//	}
//	
//	public LoginPage(WebDriver driver) {
//		super();
//		this.driverLoginPage = driver;
//	}

	// Locators
	private final By txtUsername = By.xpath("//input[@id='username']");
	private final By txtPassword = By.xpath("//input[@id='password']");
	private final By btnLogin = By.xpath("//input[@value='login']");
	private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");

	// Elements
	protected WebElement getTxtUsername() {
		return Constant.getWebDriver().findElement(txtUsername);
	}

	protected WebElement getTxtPassword() {
		return Constant.getWebDriver().findElement(txtPassword);
	}

	protected WebElement getBtnLogin() {
		return Constant.getWebDriver().findElement(btnLogin);
	}

	protected WebElement getLblLoginErrorMssg() {
		return Constant.getWebDriver().findElement(lblLoginErrorMsg);
	}

	// Methods
	public HomePage login(String username, String password) {
		// Submit login credentials
		this.getTxtUsername().sendKeys(username);
		this.getTxtPassword().sendKeys(password);
		this.getBtnLogin().click();

		// Land on HomePage
		return new HomePage();
	}

	public String getLoginErrorMsg() {
		return this.getLblLoginErrorMssg().getText();
	}
}
