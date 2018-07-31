package com.logigear.selenium.test.railway;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.logigear.selenium.constant.Constant;
import com.logigear.selenium.pageobjects.railway.BookTicketPage;
import com.logigear.selenium.pageobjects.railway.HomePage;
import com.logigear.selenium.pageobjects.railway.LoginPage;
import com.logigear.selenium.pageobjects.railway.TicketPricePage;
import com.logigear.selenium.pageobjects.railway.TimetablePage;

/**
 * @author thao.thanh.nguyen
 * This also involves 
 * TC12 - User can open "Book ticket" page by clicking on "Book ticket" link in "Train timetable"
 * TC13	- User can open "Book ticket" page by click on "Book ticket" link in "Ticket price"
 */

public class TC04_TestNavigationsToBookTicketPage extends Precondition{
	
	String strMail = Constant.RAILWAY_ACC_MAIL;
	String strPassword = Constant.RAILWAY_ACC_PASSWORD;
	String strTC12DepartFrom = "Huế";
	String strTC12ArriveAt = "Sài Gòn";
	String strTC13DepartFrom = "Nha Trang";
	String strTC13ArriveAt = "Phan Thiết";
	String strTC13SeatType = "Soft bed with air conditioner";
	String actualMsg;
	String expectedMsg;
	
	@Test
	public void precondition() {
		Precondition.preconditionCreateAndActiveAccount();
	}
	
	@Test(dependsOnMethods="precondition")
	public void TC12() {		
		System.out.println("TC12 - User can open \"Book ticket\" page by clicking on \"Book ticket\" link in \"Train timetable\"");
		
		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();
		
		// 2. Login with valid account	
		LoginPage loginpage = homepage.gotoLoginPage();
		loginpage.login(strMail, strPassword);
		
		// 3. Click on "Timetable" tab
		TimetablePage timetablepage = homepage.gotoTimetablePage();
		
		// 4. Click on "Book ticket" link of the route from "Huế" to "Sài Gòn"
		BookTicketPage bookticketpage = timetablepage.openBookTicketFromDepartAndArrival(strTC12DepartFrom, strTC12ArriveAt);
				
		// VP: '"Book ticket" page is loaded with correct for  "Depart from" and "Arrive at" values.
		String strPageTitle = Constant.WEBDRIVER.getTitle();
		String strActualDepart = bookticketpage.getCmbDepartFrom()
				.findElement(By.xpath(".//option[@selected='selected']")).getText();		
		String strActualArrival = bookticketpage.getCmbArriveAt()
				.findElement(By.xpath(".//option[@selected='selected']")).getText();
		
		assertTrue(strPageTitle.contains("Book Ticket"), "Book Ticket is opened as expected");
		assertEquals(strTC12DepartFrom, strActualDepart, "Depart's value is as expected");
		assertEquals(strTC12ArriveAt, strActualArrival, "Arrival's value is as expected");
		
	}
	
	@Test(dependsOnMethods="precondition")
	public void TC13() {		
		System.out.println("TC13 - User can open \"Book ticket\" page by click on \"Book ticket\" link in \"Ticket price\"");
	
		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();
		
		// 2. Login with valid account	
		LoginPage loginpage = homepage.gotoLoginPage();
		loginpage.login(strMail, strPassword);
		
		// 3. Click on "Ticket price" tab		
		TicketPricePage ticketpricepage = homepage.gotoTicketPricePage();
		
		// 4. Click on "Check price" link of ticket from "Nha Trang" to "Phan Thiết"
		ticketpricepage.openTicketPriceFromDepartAndArrival(strTC13DepartFrom, strTC13ArriveAt);
				
		// 5. Click on "Book ticket" button of "Soft bed with air conditioner"
		BookTicketPage bookticketpage = ticketpricepage.bookTicket(strTC13SeatType);
		
		// VP: '"Book ticket" page is loaded with correct for "Depart from", "Arrive at", and "Seat type".
		String strPageTitle = Constant.WEBDRIVER.getTitle();
		String strActualDepart = bookticketpage.getCmbDepartFrom()
				.findElement(By.xpath(".//option[@selected='selected']")).getText();		
		String strActualArrival = bookticketpage.getCmbArriveAt()
				.findElement(By.xpath(".//option[@selected='selected']")).getText();
		String strActualSeatType = bookticketpage.getCmbSeatType()
				.findElement(By.xpath(".//option[@selected='selected']")).getText();
		
		assertTrue(strPageTitle.contains("Book Ticket"), "Book Ticket is not opened as expected");
		assertEquals(strTC13DepartFrom, strActualDepart, "Depart's value is not as expected");
		assertEquals(strTC13ArriveAt, strActualArrival, "Arrival's value is not as expected");
		assertEquals(strTC13SeatType, strActualSeatType, "Arrival's value is not as expected");
	}
	
}
