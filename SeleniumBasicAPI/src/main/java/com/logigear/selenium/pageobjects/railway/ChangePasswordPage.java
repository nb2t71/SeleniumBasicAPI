package com.logigear.selenium.pageobjects.railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.logigear.selenium.constant.Constant;

public class ChangePasswordPage extends GeneralPage {

	//Locators
	private final By txtCurrentPassword = By.xpath("//input[@id='currentPassword']");
	private final By txtNewPassword = By.xpath("//input[@id='newPassword']");
	private final By txtConfirmPassword = By.xpath("//input[@id='confirmPassword']");
	private final By btnChangePassword = By.xpath("//input[@value='Change Password']");
	private final By lblChangePasswordSuccessMsg = By.xpath("//p[@class='message success']");
	private final By lblChangePasswordErrorMsg = By.xpath("//p[@class='message error']");
	private final By lblChangePasswordMsg = By.xpath("//p[contains(@class,'message')]");
	
	//Elements
	protected WebElement getTxtCurrentPassword() {
		return Constant.WEBDRIVER.findElement(txtCurrentPassword);
	}
	
	protected WebElement getTxtNewPassword() {
		return Constant.WEBDRIVER.findElement(txtNewPassword);
	}
	
	protected WebElement getTxtConfirmPassword() {
		return Constant.WEBDRIVER.findElement(txtConfirmPassword);
	}
	
	protected WebElement getBtnChagePassword() {
		return Constant.WEBDRIVER.findElement(btnChangePassword);
	}
	
	protected WebElement getlblChangePasswordSuccessMsg() {
		return Constant.WEBDRIVER.findElement(lblChangePasswordSuccessMsg);
	}
	
	protected WebElement getlblChangePasswordErrorMsg() {
		return Constant.WEBDRIVER.findElement(lblChangePasswordErrorMsg);
	}
	
	protected WebElement getlblChangePasswordMsg() {
		return Constant.WEBDRIVER.findElement(lblChangePasswordMsg);
	}
	
	//Methods
	public void changePassword(String strCurrentPassword, String strNewPassword, String strConfirmPassword) {
		this.getTxtCurrentPassword().sendKeys(strCurrentPassword);
		this.getTxtNewPassword().sendKeys(strNewPassword);
		this.getTxtConfirmPassword().sendKeys(strConfirmPassword);
		
		this.getBtnChagePassword().click();
	}
	
	public String getSuccessMsg() {
		return this.getlblChangePasswordSuccessMsg().getText();
	}
	
	public String getErrorMsg() {
		return this.getlblChangePasswordErrorMsg().getText();
	}
	
	public String getChangePasswordMsg() {
		return this.getlblChangePasswordMsg().getText();
	}
}
