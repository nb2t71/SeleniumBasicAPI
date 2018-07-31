package com.logigear.selenium.test.railway;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.logigear.selenium.common.Utilities;
import com.logigear.selenium.constant.Constant;
import com.logigear.selenium.pageobjects.gmail.GMailLoginPage;
import com.logigear.selenium.pageobjects.gmail.GMailPage;
import com.logigear.selenium.pageobjects.railway.HomePage;
import com.logigear.selenium.pageobjects.railway.RegisterPage;

/**
 * @author thao.thanh.nguyen
 * This also involves 
 * TC05	- User can create new account
 */

public class Precondition{
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Run beforeMethod");
		
		System.setProperty("webdriver.chrome.driver", Utilities.getProjectPath() + "\\Executables\\chromedriver.exe");
		Constant.WEBDRIVER = new ChromeDriver();
		Constant.WEBDRIVER.manage().window().maximize();
		
//		//Create and Activate an account
//		preconditionCreateAndActiveAccount();
	}
	
	@AfterMethod
	public void afterMetohod() {
		System.out.println("Run afterMethod");
		
		Constant.WEBDRIVER.quit();
	}
	
//	@Test
	public static void preconditionCreateAndActiveAccount() {
		System.out.println("Precondition - Use \"TC05 - User can create new account\" for precondition");
		
		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();
		// Store the current window handle
		String homepageWinHandle = Constant.WEBDRIVER.getWindowHandle();
		
		// 2. Click on "Register" tab	
		System.out.println("Random Mail will be activated: " + Constant.RAILWAY_ACC_MAIL);
		RegisterPage registerpage = homepage.gotoRegisterPage();
		// 3. Enter valid information into all fields
		// 4. Click on "Register" button
		registerpage.registNewAccount(Constant.RAILWAY_ACC_MAIL, Constant.RAILWAY_ACC_PASSWORD, Constant.RAILWAY_ACC_CONFIRM_PASSWORD, Constant.RAILWAY_ACC_PID);
		
		// VP: New account is created and message "Thank you for registering your account" appears.
		String actualMsg = registerpage.getRegisterThankYouMsg();
		String expectedMsg = "Thank you for registering your account";
		assertEquals(actualMsg, expectedMsg, "Error message is displayed as expected");
		
//		// Repeat step 2, 3, 4 to regist another mail which will not be activated
//		System.out.println("Random Mail will not be activated: " + Constant.RAILWAY_ACC_MAIL_UNACTIVE);
//		registerpage = homepage.gotoRegisterPage();
//		registerpage.registNewAccount(Constant.RAILWAY_ACC_MAIL_UNACTIVE, Constant.RAILWAY_ACC_PASSWORD, Constant.RAILWAY_ACC_CONFIRM_PASSWORD, Constant.RAILWAY_ACC_PID);
		
		GMailLoginPage gmailloginpage = homepage.gotoGMailLoginPage(Constant.GMAIL_URL);		
		GMailPage gmailpage = gmailloginpage.loginGMail(Constant.GMAIL_PASSWORD);
		gmailpage.activeAccount(Constant.RAILWAY_ACC_MAIL);
				
//		Constant.WEBDRIVER.close();
		Constant.WEBDRIVER.switchTo().window(homepageWinHandle);
	}
}
