package com.logigear.selenium.test.railway;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.logigear.selenium.page.railway.BookTicketPage;
import com.logigear.selenium.page.railway.HomePage;
import com.logigear.selenium.page.railway.LoginPage;
import com.logigear.selenium.page.railway.TicketPricePage;
import com.logigear.selenium.page.railway.TimetablePage;
import com.logigear.selenium.test.Precondition_Invoked;
import com.logigear.selenium.utilities.constant.Constant;

/**
 * @author thao.thanh.nguyen This also involves TC12 - User can open "Book
 *         ticket" page by clicking on "Book ticket" link in "Train timetable"
 *         TC13 - User can open "Book ticket" page by click on "Book ticket"
 *         link in "Ticket price"
 */

public class TC04_TestNavigationsToBookTicketPage extends Precondition_Invoked {

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
		Precondition_Invoked.preconditionCreateAndActiveAccount();
	}

	@Test(dependsOnMethods = "precondition")
	public void TC12() {
		System.out.println(
				"TC12 - User can open \"Book ticket\" page by clicking on \"Book ticket\" link in \"Train timetable\"");

		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();

		// 2. Login with valid account
		LoginPage loginpage = homepage.gotoLoginPage();
		loginpage.login(strMail, strPassword);

		// 3. Click on "Timetable" tab
		TimetablePage timetablepage = homepage.gotoTimetablePage();

		// 4. Click on "Book ticket" link of the route from "Huáº¿" to "SÃ i GÃ²n"
		BookTicketPage bookticketpage = timetablepage.openBookTicketFromDepartAndArrival(strTC12DepartFrom,
				strTC12ArriveAt);

		// VP: '"Book ticket" page is loaded with correct for "Depart from" and "Arrive
		// at" values.
		String strPageTitle = Constant.WEBDRIVER.getTitle();
		String strActualDepart = bookticketpage.getDepartFromSelectingValue();
		String strActualArrival = bookticketpage.getArriveAtSelectingValue();

		assertTrue(strPageTitle.contains("Book Ticket"), "Book Ticket is opened as expected");
		assertEquals(strTC12DepartFrom, strActualDepart, "Depart's value is as expected");
		assertEquals(strTC12ArriveAt, strActualArrival, "Arrival's value is as expected");

	}

	@Test(dependsOnMethods = "precondition")
	public void TC13() {
		System.out.println(
				"TC13 - User can open \"Book ticket\" page by click on \"Book ticket\" link in \"Ticket price\"");

		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();

		// 2. Login with valid account
		LoginPage loginpage = homepage.gotoLoginPage();
		loginpage.login(strMail, strPassword);

		// 3. Click on "Ticket price" tab
		TicketPricePage ticketpricepage = homepage.gotoTicketPricePage();

		// 4. Click on "Check price" link of ticket from "Nha Trang" to "Phan Thiáº¿t"
		ticketpricepage.openTicketPriceFromDepartAndArrival(strTC13DepartFrom, strTC13ArriveAt);

		// 5. Click on "Book ticket" button of "Soft bed with air conditioner"
		BookTicketPage bookticketpage = ticketpricepage.bookTicket(strTC13SeatType);

		// VP: '"Book ticket" page is loaded with correct for "Depart from", "Arrive
		// at", and "Seat type".
		String strPageTitle = Constant.WEBDRIVER.getTitle();
		String strActualDepart = bookticketpage.getDepartFromSelectingValue();
		String strActualArrival = bookticketpage.getArriveAtSelectingValue();
		String strActualSeatType = bookticketpage.getSeatTypeSelectingValue();

		assertTrue(strPageTitle.contains("Book Ticket"), "Book Ticket is not opened as expected");
		assertEquals(strTC13DepartFrom, strActualDepart, "Depart's value is not as expected");
		assertEquals(strTC13ArriveAt, strActualArrival, "Arrival's value is not as expected");
		assertEquals(strTC13SeatType, strActualSeatType, "Arrival's value is not as expected");
	}

}
