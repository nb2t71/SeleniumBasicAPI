package com.logigear.selenium.test.railway;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.logigear.selenium.constant.Constant;
import com.logigear.selenium.pageobjects.railway.HomePage;
import com.logigear.selenium.pageobjects.railway.LoginPage;

/**
 * @author thao.thanh.nguyen
 * This test involves 
 * TC01	- User can log into Railway with valid username and password
 * TC02	- User can't login with blank "Username" textbox
 * TC03	- User cannot log into Railway with invalid password 
 * TC04	- System shows message when user enter wrong password several times
 * TC06	- User can't login with account hasn't been activated
 */

public class TC01_TestFunctionLogin_DataProvider extends Precondition{

	String strMail = Constant.RAILWAY_ACC_MAIL;
	String actualMsg;
	String expectedMsg;
	
	@Test
	public void precondition() {
		Precondition.preconditionCreateAndActiveAccount();
	}
	
	@Test(dependsOnMethods="precondition")
	public void TC01_TC02_TC03_TC04_TC06_DataProvider_Valid() {
		System.out.println("TC01 - Test Login Validation - Valid case");
		System.out.println("Involve TC01 - User can log into Railway with valid username and password");
		
		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();

		// 2. Click on "Login" tab	
		LoginPage loginpage = homepage.gotoLoginPage();
		
		// FOR: TC01 - User can log into Railway with valid username and password
		// 3. Enter valid Email and Password
		// 4. Click on "Login" button User is logged into Railway
		loginpage.login(strMail, Constant.RAILWAY_PASSWORD);
		
		// VP: Welcome user message is displayed
		actualMsg = loginpage.getWelcomeMessage();
		expectedMsg = "Welcome " + strMail;		
		assertEquals(actualMsg, expectedMsg, "TC01 - Welcome message is not displayed as expected");
	}
	
	@Test(dataProvider="Invalid Cases", dependsOnMethods="precondition")
	public void TC01_TC02_TC03_TC04_TC06_DataProvider_Invalid(String outMessage, String strUsername, String strPassword, int repeatTime, String strExpectedMessage, String strErrorCheckMessage) {
		System.out.println("TC01 - Test Login Valication - Invalid case");
//		System.out.println("Involve TC02 - User can't login with blank \"Username\" textbox");
//		System.out.println("Involve TC03 - User cannot log into Railway with invalid password");
//		System.out.println("Involve TC04 - System shows message when user enter wrong password several times");
//		System.out.println("Involve TC06 - User can't login with account hasn't been activated");
		
		System.out.println(outMessage);
		
		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();

		// 2. Click on "Login" tab	
		LoginPage loginpage = homepage.gotoLoginPage();
		
		// FOR: TC06 - User can't login with account hasn't been activated"
		// 3. User doesn't type any words into "Username" textbox but enter valid information into "Password" textbox 
		// 4. Click on "Login" button
		// VP: User can't login and message "Invalid username or password. Please try again." appears.
		
		// FOR: TC02 - User can't login with blank \"Username\" textbox"
		// 3. User doesn't type any words into "Username" textbox but enter valid information into "Password" textbox 
		// 4. Click on "Login" button
		// VP: User can't login and message "There was a problem with your login and/or errors exist in your form." appears
		
		// FOR: TC03 - User cannot log into Railway with invalid password
		// 3. User doesn't type any words into "Username" textbox but enter valid information into "Password" textbox 
		// 4. Click on "Login" button
		// VP: Error message "There was a problem with your login and/or errors exist in your form." is displayed
		
		// FOR: TC04 - System shows message when user enter wrong password several times
		// 3. User doesn't type any words into "Username" textbox but enter valid information into "Password" textbox 
		// 4. Click on "Login" button
		// VP: User can't login and message "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes." appears.

		for(int i=0; i<repeatTime; i++) {
			loginpage.login(strUsername, strPassword);
		}		
		
		// VP: <message> is displayed
		actualMsg = loginpage.getLoginErrorMsg();
		expectedMsg = strExpectedMessage;		
		assertEquals(actualMsg, expectedMsg, strErrorCheckMessage);
		
//		loginpage.logout();
//		loginpage = homepage.gotoLoginPage();
//		
//		// FOR: TC06 - User can't login with account hasn't been activated"
//		// 3. User doesn't type any words into "Username" textbox but enter valid information into "Password" textbox 
//		// 4. Click on "Login" button
//		loginpage.login(Constant.RAILWAY_ACC_MAIL_UNACTIVE, Constant.RAILWAY_ACC_PASSWORD);		
//		// VP: User can't login and message "Invalid username or password. Please try again." appears.
//		actualMsg = loginpage.getLoginErrorMsg();
//		expectedMsg = "Invalid username or password. Please try again.";				
//		assertEquals(actualMsg, expectedMsg, "Error message is displayed as expected");
		
//		// FOR: TC02 - User can't login with blank \"Username\" textbox"
//		// 3. User doesn't type any words into "Username" textbox but enter valid information into "Password" textbox 
//		// 4. Click on "Login" button
//		loginpage.login(Constant.USERNAME, "");//		
//		// VP: User can't login and message "There was a problem with your login and/or errors exist in your form." appears
//		actualMsg = loginpage.getLoginErrorMsg();
//		expectedMsg = "There was a problem with your login and/or errors exist in your form.";		
//		assertEquals(actualMsg, expectedMsg, "TC02 - Error message is not displayed as expected");
//		
//		// FOR: TC03 - User cannot log into Railway with invalid password
//		// 3. User doesn't type any words into "Username" textbox but enter valid information into "Password" textbox 
//		// 4. Click on "Login" button
//		loginpage.login(Constant.USERNAME, "123");//		
//		// VP: Error message "There was a problem with your login and/or errors exist in your form." is displayed
//		actualMsg = loginpage.getLoginErrorMsg();
//		expectedMsg = "Invalid username or password. Please try again.";		
//		assertEquals(actualMsg, expectedMsg, "TC03 - Error message is not displayed as expected");
//		
//		// FOR: TC04 - System shows message when user enter wrong password several times
//		// 3. User doesn't type any words into "Username" textbox but enter valid information into "Password" textbox 
//		// 4. Click on "Login" button
//		for(int i=0; i<5; i++) {
//			loginpage.login(Constant.USERNAME, "123");//		}		
//		
//		// VP: User can't login and message "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes." appears.
//		actualMsg = loginpage.getLoginErrorMsg();
//		expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
//		assertEquals(actualMsg, expectedMsg, "TC04 - Error message is not displayed as expected");
	}
	
	@DataProvider(name="Invalid Cases")
	public Object[][] getDataFromDataprovider(){
		return new Object[][] {
			{"Involve TC06 - User can't login with account hasn't been activated", Constant.RAILWAY_ACC_MAIL_UNACTIVE, "123", 1, "Invalid username or password. Please try again.", "TC06 - Error message is not displayed as expected"},
			{"Involve TC02 - User can't login with blank \"Username\" textbox", strMail, "", 1, "There was a problem with your login and/or errors exist in your form.", "TC02 - Error message is not displayed as expected"},
			{"Involve TC03 - User cannot log into Railway with invalid password", strMail, "123", 1, "Invalid username or password. Please try again.", "TC03 - Error message is not displayed as expected"},
			{"Involve TC04 - System shows message when user enter wrong password several times", strMail, 4, "123", "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.", "TC04 - Error message is not displayed as expected"},
		};
	}
}
