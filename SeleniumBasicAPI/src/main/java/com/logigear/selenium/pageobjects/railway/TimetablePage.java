package com.logigear.selenium.pageobjects.railway;

import org.openqa.selenium.By;

import com.logigear.selenium.constant.Constant;
import com.logigear.selenium.pageobjects.railway.BookTicketPage;

public class TimetablePage extends GeneralPage {

	//Locators
	
	//Elements

	//Methods
	
	public BookTicketPage openBookTicketFromDepartAndArrival(String strDepartStation, String strArriveStation) {
		//td[text()='Sài Gòn']/following-sibling::td[text()='Phan Thiết']/parent::tr//a[text()='book ticket']
		By strXPath = By.xpath("//td[text()='"+strDepartStation+"']/following-sibling::td[text()='"+strArriveStation+"']/parent::tr//a[text()='book ticket']");
		Constant.WEBDRIVER.findElement(strXPath).click();
		return new BookTicketPage();
	}
	
}
