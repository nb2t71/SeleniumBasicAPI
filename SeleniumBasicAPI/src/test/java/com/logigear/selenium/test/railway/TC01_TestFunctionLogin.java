package com.logigear.selenium.test.railway;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.logigear.selenium.constant.Constant;
import com.logigear.selenium.pageobjects.railway.HomePage;
import com.logigear.selenium.pageobjects.railway.LoginPage;

/**
 * @author thao.thanh.nguyen This test involves TC01 - User can log into Railway
 *         with valid username and password TC02 - User can't login with blank
 *         "Username" textbox TC03 - User cannot log into Railway with invalid
 *         password TC04 - System shows message when user enter wrong password
 *         several times TC06 - User can't login with account hasn't been
 *         activated
 */

public class TC01_TestFunctionLogin extends Precondition_Invoked {

	String actualMsg;
	String expectedMsg;

	@Test
	public void precondition() {
		Precondition_Invoked.preconditionCreateAndActiveAccount();
		Precondition_Invoked.preconditionCreateInactiveAccount();
	}

	@Test(dependsOnMethods = "precondition")
	public void TC01_TC02_TC03_TC04_TC06() {

		System.out.println("TC01 - Test Login Valication");
		System.out.println("Involve TC01 - User can log into Railway with valid username and password");
		System.out.println("Involve TC02 - User can't login with blank \"Username\" textbox");
		System.out.println("Involve TC03 - User cannot log into Railway with invalid password");
		System.out.println("Involve TC04 - System shows message when user enter wrong password several times");
		System.out.println("Involve TC06 - User can't login with account hasn't been activated");

		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();

		// 2. Click on "Login" tab
		LoginPage loginpage = homepage.gotoLoginPage();

		// FOR: TC01 - User can log into Railway with valid username and password
		// 3. Enter valid Email and Password
		// 4. Click on "Login" button User is logged into Railway
		loginpage.login(Constant.RAILWAY_ACC_MAIL, Constant.RAILWAY_ACC_PASSWORD);

		// VP: Welcome user message is displayed
		actualMsg = loginpage.getWelcomeMessage();
		expectedMsg = "Welcome " + Constant.RAILWAY_ACC_MAIL;
		assertEquals(actualMsg, expectedMsg, "TC01 - Welcome message is not displayed as expected");

		loginpage.logout();
		loginpage = homepage.gotoLoginPage();

		// FOR: TC06 - User can't login with account hasn't been activated"
		// 3. User doesn't type any words into "Username" textbox but enter valid
		// information into "Password" textbox
		// 4. Click on "Login" button
		loginpage.login(Constant.RAILWAY_ACC_MAIL_UNACTIVE, Constant.RAILWAY_ACC_PASSWORD);
		// VP: User can't login and message "Invalid username or password. Please try
		// again." appears.
		actualMsg = loginpage.getLoginErrorMsg();
		expectedMsg = "Invalid username or password. Please try again.";
		assertEquals(actualMsg, expectedMsg, "Error message is displayed as expected");

		// FOR: TC02 - User can't login with blank \"Username\" textbox"
		// 3. User doesn't type any words into "Username" textbox but enter valid
		// information into "Password" textbox
		// 4. Click on "Login" button
		loginpage.login(Constant.RAILWAY_ACC_MAIL, "");
		// VP: User can't login and message "There was a problem with your login and/or
		// errors exist in your form." appears
		actualMsg = loginpage.getLoginErrorMsg();
		expectedMsg = "There was a problem with your login and/or errors exist in your form.";
		assertEquals(actualMsg, expectedMsg, "TC02 - Error message is not displayed as expected");

		// FOR: TC03 - User cannot log into Railway with invalid password
		// 3. User doesn't type any words into "Username" textbox but enter valid
		// information into "Password" textbox
		// 4. Click on "Login" button
		loginpage.login(Constant.RAILWAY_USERNAME, "123");
		// VP: Error message "There was a problem with your login and/or errors exist in
		// your form." is displayed
		actualMsg = loginpage.getLoginErrorMsg();
		expectedMsg = "Invalid username or password. Please try again.";
		assertEquals(actualMsg, expectedMsg, "TC03 - Error message is not displayed as expected");

		// FOR: TC04 - System shows message when user enter wrong password several times
		// 3. User doesn't type any words into "Username" textbox but enter valid
		// information into "Password" textbox
		// 4. Click on "Login" button
		for (int i = 0; i < 4; i++) {
			loginpage.login(Constant.RAILWAY_USERNAME, "123");
		}
		// VP: User can't login and message "You have used 4 out of 5 login attempts.
		// After all 5 have been used, you will be unable to login for 15 minutes."
		// appears.
		actualMsg = loginpage.getLoginErrorMsg();
		expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

		assertEquals(actualMsg, expectedMsg, "TC04 - Error message is not displayed as expected");
	}

}
