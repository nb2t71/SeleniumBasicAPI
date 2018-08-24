package com.logigear.selenium.page.railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.logigear.selenium.page.gmail.GMailLoginPage;
import com.logigear.selenium.utilities.constant.Constant;

public class GeneralPage {
//	private WebDriver driverGeneralPage;
//	public GeneralPage() {
//		
//	}
//	
//	public GeneralPage(WebDriver driver) {
//		//super();
//		this.driverGeneralPage = driver;
//	}
	// Locators
	private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
	private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
	private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
	private final By tabChangePassword = By.xpath("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']");
	private final By tabBookTicket = By.xpath("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']");
	private final By tabTimetable = By.xpath("//div[@id='menu']//a[@href='TrainTimeListPage.cshtml']");
	private final By tabTicketPrice = By.xpath("//div[@id='menu']//a[@href='/Page/TrainPriceListPage.cshtml']");
	private final By tabMyTicket = By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']");
	private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");

	// Elements
	protected WebElement getTabLogin() {
		return Constant.getWebDriver().findElement(tabLogin);
	}

	protected WebElement getTabLogout() {
		return Constant.getWebDriver().findElement(tabLogout);
	}

	protected WebElement getTabRegister() {
		return Constant.getWebDriver().findElement(tabRegister);
	}

	protected WebElement getTabChangePassword() {
		return Constant.getWebDriver().findElement(tabChangePassword);
	}

	protected WebElement getLblWelcomeMessage() {
		return Constant.getWebDriver().findElement(lblWelcomeMessage);
	}

	protected WebElement getTabBookTicket() {
		return Constant.getWebDriver().findElement(tabBookTicket);
	}

	protected WebElement getTabTimetable() {
		return Constant.getWebDriver().findElement(tabTimetable);
	}

	protected WebElement getTabTicketPrice() {
		return Constant.getWebDriver().findElement(tabTicketPrice);
	}

	protected WebElement getTabMyTicket() {
		return Constant.getWebDriver().findElement(tabMyTicket);
	}

	// Methods
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
		Constant.getWebDriver().get(srtURL);
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
		if (this.getTabLogout().isDisplayed()) {
			this.getTabLogout().click();
		}
		return this;
	}
}
