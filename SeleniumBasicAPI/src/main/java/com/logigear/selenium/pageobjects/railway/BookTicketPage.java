package com.logigear.selenium.pageobjects.railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.logigear.selenium.constant.Constant;

public class BookTicketPage extends GeneralPage {
	
	//Locators
	private final By cmbDepartDate = By.xpath("//select[@name='Date']");
	private final By cmbDepartFrom = By.xpath("//select[@name='DepartStation']");
	private final By cmbArriveAt = By.xpath("//select[@name='ArriveStation']");
	private final By cmbSeatType = By.xpath("//select[@name='SeatType']");
	private final By cmbTicketAmount = By.xpath("//select[@name='TicketAmount']");
	private final By lblBookTicketSuccess = By.xpath("//div[@id='content']/h1");
	private final By btnBookTicket = By.xpath("//input[@type='submit' and @value='Book ticket']");
	
	//Elements
	protected WebElement getCmbDepartDate() {
		return Constant.WEBDRIVER.findElement(cmbDepartDate);
	}
	
	protected WebElement getCmbDepartFrom() {
		return Constant.WEBDRIVER.findElement(cmbDepartFrom);
	}
	
	protected WebElement getCmbArriveAt() {
		return Constant.WEBDRIVER.findElement(cmbArriveAt);
	}
	
	protected WebElement getCmbSeatType() {
		return Constant.WEBDRIVER.findElement(cmbSeatType);
	}
	
	protected WebElement getCmbTicketAmount() {
		return Constant.WEBDRIVER.findElement(cmbTicketAmount);
	}
	
	protected WebElement getLblBookTicketSuccess() {
		return Constant.WEBDRIVER.findElement(lblBookTicketSuccess);
	}
	
	protected WebElement getBtnBookTicket() {
		return Constant.WEBDRIVER.findElement(btnBookTicket);
	}
	
	//Methods
	public BookTicketPage bookTicket(String strDepartDate, String strDepartFrom, String strArriveAt, String strSeatType, String strTicketAmount) {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, 10);

		new Select(this.getCmbDepartDate()).selectByVisibleText(strDepartDate);
		new Select(this.getCmbDepartFrom()).selectByVisibleText(strDepartFrom);
		
		wait.until(ExpectedConditions.textToBePresentInElement(this.getCmbDepartFrom(), strDepartFrom));
		wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(getCmbDepartFrom(), By.xpath("//option[text()='"+strDepartFrom+"']")));
		wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(getCmbArriveAt(), By.xpath("//option[text()='"+strArriveAt+"']")));
		wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(getCmbArriveAt(), By.xpath("//option[text()='"+strArriveAt+"']")));

		new Select(this.getCmbArriveAt()).selectByVisibleText(strArriveAt);
		new Select(this.getCmbSeatType()).selectByVisibleText(strSeatType);
		new Select(this.getCmbTicketAmount()).selectByVisibleText(strTicketAmount);
		
		wait.until(ExpectedConditions.elementToBeClickable(getBtnBookTicket()));
		this.getBtnBookTicket().click();

		return this;
	}
	
	public String getBookTicketSuccessMessage() {
		WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, 10);
		wait.until(ExpectedConditions.visibilityOf(getLblBookTicketSuccess()));
		return this.getLblBookTicketSuccess().getText();
	}
}
