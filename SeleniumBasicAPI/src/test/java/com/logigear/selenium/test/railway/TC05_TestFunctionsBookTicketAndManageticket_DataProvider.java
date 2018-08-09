package com.logigear.selenium.test.railway;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.logigear.selenium.constant.Constant;
import com.logigear.selenium.pageobjects.railway.BookTicketPage;
import com.logigear.selenium.pageobjects.railway.HomePage;
import com.logigear.selenium.pageobjects.railway.LoginPage;
import com.logigear.selenium.pageobjects.railway.MyTicketPage;

/**
 * @author thao.thanh.nguyen This also involves TC10 - User can book 1 ticket at
 *         a time TC11 - User can book many tickets at a time TC15 - User can
 *         filter "Manager ticket" table with Depart Station TC16 - User can
 *         filter "Manager ticket" table with Depart Date TC17 - User can't
 *         filter "Manage ticket" table when choosing value of "Status" doesn't
 *         exist in "Manage ticket" table TC14 - User can cancel a ticket
 */

public class TC05_TestFunctionsBookTicketAndManageticket_DataProvider extends Precondition_Invoked {

	String strMail = Constant.RAILWAY_ACC_MAIL;
	String strPassword = Constant.RAILWAY_ACC_PASSWORD;

	String strDepartDate = "8/10/2018";
	String strDepartFrom = "Sài Gòn";
	String strArriveAt = "Nha Trang";
	String strSeatType = "Soft bed with air conditioner";
	String strTicketAmount = "1";

	@Test
	public void precondition() {
		Precondition_Invoked.preconditionCreateAndActiveAccount();
	}

	@Test(dependsOnMethods = "precondition", dataProvider = "DataForBookTicket")
	public void TC10andTC11(String strTMname, String strDepartDate, String strDepartFrom, String strArriveAt,
			String strSeatType, String strTicketAmount) {
		System.out.println(strTMname);

		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();

		// 2. Login with valid account
		LoginPage loginpage = homepage.gotoLoginPage();
		loginpage.login(strMail, strPassword);

		// 3. Click on "Book ticket" tab
		BookTicketPage bookticketpage = homepage.gotoBookTicketPage();
		// 4. Select <option value> for "Depart date" from the list
		// 5. Select <option value> for "Depart from" and <option value> for "Arrive
		// at".
		// 6. Select <option value> for "Seat type"
		// 7. Select <option value> for "Ticket amount"
		// 8. Click on "Book ticket" button
		bookticketpage.bookTicket(strDepartDate, strDepartFrom, strArriveAt, strSeatType, strTicketAmount);

		// VP: Message "Ticket booked successfully!" displays. Ticket information
		// display correctly (Depart Date, Depart Station, Arrive Station, Seat Type,
		// Amount)
		String actualMsg = bookticketpage.getBookTicketSuccessMessage();
		String expectedMsg = "Ticket booked successfully!";

		assertEquals(actualMsg, expectedMsg, "Ticket booked successfully!");
	}

	@Test(dependsOnMethods = "TC10andTC11", dataProvider = "DataForManageTicket")
	public void TC15_TC16(String strTMname, String strFilteredByDepartStation, String strFilteredByArriveStation,
			String strFilteredByDepartDate, String strFilteredByByStatus) {
		System.out.println("TC16 - User can filter \"Manager ticket\" table with Depart Date");
		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();

		// 2. Login with a valid account
		LoginPage loginpage = homepage.gotoLoginPage();
		loginpage.login(strMail, strPassword);

		// 3. Book more than 6 tickets with different Depart Dates
		// <Skip> because it has been done in TC10_TC11

		// 4. Click on "My ticket" tab
		MyTicketPage myticketpage = homepage.gotoMyTicketPage();
		String[][] strArrayTicketsBeforeFilter = myticketpage.getTickets();

		// 5. Input one of booked Depart Date in "Depart date" textbox
		// 6. Click "Apply filter" button
		String[][] strArrayTicketsAfterFilter = myticketpage.filterTickets(strFilteredByDepartStation,
				strFilteredByArriveStation, strFilteredByDepartDate, strFilteredByByStatus);

		// VP: "Manage ticket" table shows correct ticket(s)
		assertTrue(myticketpage.checkTicketsExistInTable(strArrayTicketsBeforeFilter, strArrayTicketsAfterFilter),
				"Filtered tickets are not correct");
	}

	@Test(dependsOnMethods = "TC10andTC11", priority = 1)
	public void TC15() {
		System.out.println("TC15 - User can filter \"Manager ticket\" table with Depart Station");
		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();

		// 2. Login with a valid account
		LoginPage loginpage = homepage.gotoLoginPage();
		loginpage.login(strMail, strPassword);

		// 3. Book more than 6 tickets with different Depart Dates
		// <Skip> because it has been done in TC10_TC11

		// 4. Click on "My ticket" tab
		MyTicketPage myticketpage = homepage.gotoMyTicketPage();
		String[][] strArrayTicketsBeforeFilter = myticketpage.getTickets();

		// 5. Select one of booked Depart Station in "Depart Station" dropdown list
		// 6. Click "Apply filter" button
		String[][] strArrayTicketsAfterFilter = myticketpage.filterTickets(strDepartFrom, "", "", "");

		// VP: "Manage ticket" table shows correct ticket(s)
		assertTrue(myticketpage.checkTicketsExistInTable(strArrayTicketsBeforeFilter, strArrayTicketsAfterFilter),
				"Filtered tickets are not correct");
	}

	@Test(dependsOnMethods = "TC10andTC11", priority = 2)
	public void TC16() {
		System.out.println("TC16 - User can filter \"Manager ticket\" table with Depart Date");
		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();

		// 2. Login with a valid account
		LoginPage loginpage = homepage.gotoLoginPage();
		loginpage.login(strMail, strPassword);

		// 3. Book more than 6 tickets with different Depart Dates
		// <Skip> because it has been done in TC10_TC11

		// 4. Click on "My ticket" tab
		MyTicketPage myticketpage = homepage.gotoMyTicketPage();
		String[][] strArrayTicketsBeforeFilter = myticketpage.getTickets();

		// 5. Input one of booked Depart Date in "Depart date" textbox
		// 6. Click "Apply filter" button
		String[][] strArrayTicketsAfterFilter = myticketpage.filterTickets("", "", strDepartDate, "");

		// VP: "Manage ticket" table shows correct ticket(s)
		assertTrue(myticketpage.checkTicketsExistInTable(strArrayTicketsBeforeFilter, strArrayTicketsAfterFilter),
				"Filtered tickets are not correct");
	}

	@Test(dependsOnMethods = "TC10andTC11", priority = 3)
	public void TC17() {
		System.out.println(
				"TC17 - User can't filter \"Manage ticket\" table when choosing value of \"Status\" doesn't exist in \"Manage ticket\" table");
		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();

		// 2. Login with a valid account
		LoginPage loginpage = homepage.gotoLoginPage();
		loginpage.login(strMail, strPassword);

		// 3. Book more than 6 tickets
		// <Skip> because it has been done in TC10_TC11

		// 4. Click on "My ticket" tab
		MyTicketPage myticketpage = homepage.gotoMyTicketPage();

		// 5. Select "Paid" for "Status"
		// 6. Click "Apply filter" button
		myticketpage.filterTickets("", "", "", "Paid");

		// VP: "Manage ticket" table shows message "Sorry, can't find any results that
		// match your filters.\nPlease change the filters and try again."
		String actualMsg = myticketpage.getFitlerErrorMsg();
		String expectedMsg = "Sorry, can't find any results that match your filters.\nPlease change the filters and try again.";

		assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
	}

	@Test(dependsOnMethods = "TC10andTC11", priority = 4)
	public void TC14() {
		System.out.println("TC14 - User can cancel a ticket");
		// Pre-condition: Create and activate a new account
		// 1. Navigate to QA Railway Website
		// 2. Login with a valid account
		// 3. Book a ticket
		HomePage homepage = new HomePage();
		homepage.open();
		LoginPage loginpage = homepage.gotoLoginPage();
		loginpage.login(strMail, strPassword);

		// 4. Click on "My ticket" tab
		MyTicketPage myticketpage = homepage.gotoMyTicketPage();

		String[] strArrayFirstTicketBeforeCancel = myticketpage.getFirstTicketInfo();
		// 5. Click on "Cancel" button of ticket which user want to cancel.
		// 6. Click on "OK" button on Confirmation message "Are you sure?"
		myticketpage.cancelFirstTicket();
		String[] strArrayFirstTicketAfterCancel = myticketpage.getFirstTicketInfo();

		// VP: The canceled ticket is disappeared.
		assertTrue(strArrayFirstTicketBeforeCancel != strArrayFirstTicketAfterCancel);
	}

	@DataProvider(name = "DataForBookTicket")
	public Object[][] getDataFromDataproviderForBookTicket() {
		return new Object[][] {
				{ "TC10 - User can book 1 ticket at a time", strDepartDate, strDepartFrom, strArriveAt, strSeatType,
						strTicketAmount },
				{ "TC11 - User can book many tickets at a time", "8/11/2018", "Nha Trang", "Sài Gòn",
						"Soft bed with air conditioner", "5" },
				{ "Prepare data 3 for TC14 to TC17", "8/12/2018", "Huế", "Quảng Ngãi", "Hard seat", "1" },
				{ "Prepare data 4 for TC14 to TC17", "8/13/2018", "Phan Thiết", "Nha Trang", "Hard bed", "1" },
				{ "Prepare data 5 for TC14 to TC17", "8/14/2018", "Quảng Ngãi", "Huế", "Soft seat", "1" },
				{ "Prepare data 6 for TC14 to TC17", "8/15/2018", "Sài Gòn", "Phan Thiết", "Hard seat", "1" } };
	}

	@DataProvider(name = "DataForManageTicket")
	public Object[][] getDataFromDataproviderForManageTicket() {
		return new Object[][] {
				{ "TC15 - User can filter \"Manager ticket\" table with Depart Station", strDepartFrom, "", "", "" },
				{ "TC16 - User can filter \"Manager ticket\" table with Depart Date", "", "", strDepartDate, "" },
//            { "TC17 - User can't filter \"Manage ticket\" table when choosing value of \"Status\" doesn't exist in \"Manage ticket\" table", "8/12/2018", "", "", "", "" },
		};
	}

}
