package com.logigear.selenium.pageobjects.railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.logigear.selenium.constant.Constant;

public class RegisterPage extends GeneralPage {

	//Locators
	private final By txtEmail = By.xpath("//input[@id='email']");
	private final By txtPassword = By.xpath("//input[@id='password']");
	private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By txtPID = By.xpath("//input[@id='pid']");
	private final By btnRegist = By.xpath("//input[@value='Register']");
	private final By lblThankYou = By.xpath("//div[@id='content']/h1[@align='center']");
	private final By lblRegisterErrorMsg = By.xpath("//p[@class='message error']");

	//Elements
	protected WebElement getTxtEmail() {
		return Constant.WEBDRIVER.findElement(txtEmail);
	}
	protected WebElement getTxtPassword() {
		return Constant.WEBDRIVER.findElement(txtPassword);
	}
	protected WebElement getTxtConfirmPassword() {
		return Constant.WEBDRIVER.findElement(txtConfirmPassword);
	}
	protected WebElement getTxtPID() {
		return Constant.WEBDRIVER.findElement(txtPID);
	}
	protected WebElement getBtnRegist() {
		return Constant.WEBDRIVER.findElement(btnRegist);
	}
	protected WebElement getLblThankYou() {
		return Constant.WEBDRIVER.findElement(lblThankYou);
	}
	protected WebElement getLblRegisterErrorMsg() {
		return Constant.WEBDRIVER.findElement(lblRegisterErrorMsg);
	}
	
	//Methods
//	public void registNewAccount(String strMail, String strPassword, String strConfirmPassword, String strPID) {
//		this.getTxtEmail().sendKeys(strMail);
//		this.getTxtPassword().sendKeys(strPassword);
//		this.getTxtConfirmPassword().sendKeys(strConfirmPassword);
//		this.getTxtPID().sendKeys(strPID);
//		this.getBtnRegist().click();
//	}
	
	public RegisterPage registNewAccount(String strMail, String strPassword, String strConfirmPassword, String strPID) {
		this.getTxtEmail().sendKeys(strMail);
		this.getTxtPassword().sendKeys(strPassword);
		this.getTxtConfirmPassword().sendKeys(strConfirmPassword);
		this.getTxtPID().sendKeys(strPID);
		this.getBtnRegist().click();
		return this;
	}
	
	public String getRegisterThankYouMsg() {
		return this.getLblThankYou().getText();
	}

	public String getRegisterErrorMsg() {
		return this.getLblRegisterErrorMsg().getText();
	}
}
