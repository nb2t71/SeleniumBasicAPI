package com.logigear.selenium.page.railway;

import org.openqa.selenium.By;

import com.logigear.selenium.utilities.constant.Constant;

public class TimetablePage extends GeneralPage {

	// Locators

	// Elements

	// Methods

	public BookTicketPage openBookTicketFromDepartAndArrival(String strDepartStation, String strArriveStation) {
		// td[text()='Sài Gòn']/following-sibling::td[text()='Phan
		// Thiết']/parent::tr//a[text()='book ticket']
		By strXPath = By.xpath("//td[text()='" + strDepartStation + "']/following-sibling::td[text()='"
				+ strArriveStation + "']/parent::tr//a[text()='book ticket']");
		Constant.getWebDriver().findElement(strXPath).click();
		return new BookTicketPage();
	}

}
