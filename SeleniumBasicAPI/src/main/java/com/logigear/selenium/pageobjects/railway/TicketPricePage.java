package com.logigear.selenium.pageobjects.railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.logigear.selenium.constant.Constant;

public class TicketPricePage extends GeneralPage {

	// Locators

	// Elements

	// Methods
	public TicketPricePage openTicketPriceFromDepartAndArrival(String strDepartStation, String strArriveStation) {
		WebElement elementLblRoute = Constant.getWebDriver()
				.findElement(By.xpath("//li[text()='" + strDepartStation + " to " + strArriveStation + "']/../.."));
		WebElement elementBtnTicketPrice = elementLblRoute
				.findElement(By.xpath(".//a[contains(@href, 'TicketPricePage')]"));
		Actions actions = new Actions(Constant.getWebDriver());
		actions.moveToElement(elementLblRoute);
		elementBtnTicketPrice.click();
		return this;
	}

	public BookTicketPage bookTicket(String strSeatType) {
		WebElement elementLblSeatType = Constant.getWebDriver()
				.findElement(By.xpath("//td[text()='" + strSeatType + "']/.."));
		WebElement elementBtnBookTicket = elementLblSeatType
				.findElement(By.xpath(".//a[contains(@href, 'BookTicketPage')]"));

		elementBtnBookTicket.click();
		return new BookTicketPage();
	}

}
