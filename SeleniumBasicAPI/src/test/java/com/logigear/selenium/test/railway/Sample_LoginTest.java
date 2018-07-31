package com.logigear.selenium.test.railway;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.logigear.selenium.common.Utilities;
import com.logigear.selenium.constant.Constant;
import com.logigear.selenium.pageobjects.railway.HomePage;
import com.logigear.selenium.pageobjects.railway.LoginPage;

public class Sample_LoginTest {	
	
	String strMail = Utilities.generateRandomText("abcdefghijklmnopqrstuvwxyz", 6) + "@abc.com";
	String strPassword = "12345678";
	String strConfirmPassword = "12345678";
	String strPid = "01010101";
	String strTC08NewPassword = "a123:\"/{}!@$\\";
	String strTC08ConfirmPassword = "b456:\"/{}!@$\\";
	String strTC09NewPassword = "00000001";
	String strTC09ConfirmPassword = "10000000";

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Run beforeMethod");
		
		System.setProperty("webdriver.chrome.driver", Utilities.getProjectPath() + "\\Executables\\chromedriver.exe");
		Constant.WEBDRIVER = new ChromeDriver();
		Constant.WEBDRIVER.manage().window().maximize();
	}
	
	@AfterMethod
	public void afterMetohod() {
		System.out.println("Run afterMethod");
		
		Constant.WEBDRIVER.quit();
	}
	
	@Test
	public void TC01() {
		System.out.println("TC01 - User can log into Railway with valid username and password");
		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();

		// 2. Click on "Login" tab	
		LoginPage loginpage = homepage.gotoLoginPage();
				
		// 3. Enter valid Email and Password
		// 4. Click on "Login" button User is logged into Railway
		loginpage.login(Constant.RAILWAY_USERNAME, Constant.RAILWAY_PASSWORD);
		
		// VP: Welcome user message is displayed
		String actualMsg = loginpage.getWelcomeMessage();
		String expectedMsg = "Welcome " + Constant.RAILWAY_USERNAME;
		
		assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
	}
	
}
