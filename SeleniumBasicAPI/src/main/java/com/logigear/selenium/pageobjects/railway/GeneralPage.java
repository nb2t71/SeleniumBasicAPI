package com.logigear.selenium.pageobjects.railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.logigear.selenium.constant.Constant;
import com.logigear.selenium.pageobjects.gmail.GMailLoginPage;

public class GeneralPage {

	//Locators
	private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
	private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
	private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
	private final By tabChangePassword = By.xpath("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']");
	private final By tabBookTicket = By.xpath("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']");
	private final By tabTimetable = By.xpath("//div[@id='menu']//a[@href='TrainTimeListPage.cshtml']");
	private final By tabTicketPrice = By.xpath("//div[@id='menu']//a[@href='/Page/TrainPriceListPage.cshtml']");
	private final By tabMyTicket = By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']");
	private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
	
	//Elements
	protected WebElement getTabLogin() {
		return Constant.WEBDRIVER.findElement(tabLogin);
	}
	
	protected WebElement getTabLogout() {
		return Constant.WEBDRIVER.findElement(tabLogout);
	}
	
	protected WebElement getTabRegister() {
		return Constant.WEBDRIVER.findElement(tabRegister);
	}
	
	protected WebElement getTabChangePassword() {
		return Constant.WEBDRIVER.findElement(tabChangePassword);
	}
	
	protected WebElement getLblWelcomeMessage() {
		return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
	}
	
	protected WebElement getTabBookTicket() {
		return Constant.WEBDRIVER.findElement(tabBookTicket);
	}
	
	protected WebElement getTabTimetable() {
		return Constant.WEBDRIVER.findElement(tabTimetable);
	}
	
	protected WebElement getTabTicketPrice() {
		return Constant.WEBDRIVER.findElement(tabTicketPrice);
	}
	
	protected WebElement getTabMyTicket() {
		return Constant.WEBDRIVER.findElement(tabMyTicket);
	}
	
	//Methods
	public String getWelcomeMessage() {
		return this.getLblWelcomeMessage().getText();
	}
	
	public LoginPage gotoLoginPage() {
		this.getTabLogin().click();
		return new LoginPage();
	}
	
	public RegisterPage gotoRegisterPage() {
		this.getTabRegister().click();
		return new RegisterPage();
	}
	
	public ChangePasswordPage gotoChangePasswordPage() {
		this.getTabChangePassword().click();
		return new ChangePasswordPage();
	}
	
	public GMailLoginPage gotoGMailLoginPage(String srtURL) {
		Constant.WEBDRIVER.get(srtURL);
		return new GMailLoginPage();
	}
	
	public BookTicketPage gotoBookTicketPage() {
		this.getTabBookTicket().click();
		return new BookTicketPage();
	}
	
	public TimetablePage gotoTimetablePage() {
		this.getTabTimetable().click();
		return new TimetablePage();
	}
	
	public TicketPricePage gotoTicketPricePage() {
		this.getTabTicketPrice().click();
		return new TicketPricePage();
	}
	
	public MyTicketPage gotoMyTicketPage() {
		this.getTabMyTicket().click();
		return new MyTicketPage();
	}	
	
	public GeneralPage logout() {
		if(this.getTabLogout().isDisplayed()) {
			this.getTabLogout().click();
		}		
		return this;
	}
}
