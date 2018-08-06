package com.logigear.selenium.test.railway;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.logigear.selenium.common.Utilities;
import com.logigear.selenium.constant.Constant;
import com.logigear.selenium.pageobjects.railway.HomePage;
import com.logigear.selenium.pageobjects.railway.RegisterPage;
import com.logigear.selenium.underground.thread.Context;
import com.logigear.selenium.underground.thread.MyThreadLocal;
import com.logigear.selenium.utilities.gmail.gmailApi;

/**
 * @author thao.thanh.nguyen
 * This also involves 
 * TC05	- User can create new account
 */

public class Precondition_Invoked{
	
//	String pathDrivers = Utilities.getProjectPath() + "\\src\\test\\resources\\drivers";
//	MyThreadLocal mythreadlocal;
//	ThreadLocal threadLocal;
//	Context context;
	
	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(@Optional("chrome") String browser) throws MalformedURLException {
		System.out.println("Run beforeMethod");
		
//		threadLocal = new ThreadLocal();
//		System.out.println("Thread :" + threadLocal.toString());
//		thread.start();
		
//		mythreadlocal = new MyThreadLocal();
//		mythreadlocal.start();		
//		Context context = new Context();
				
		
//		if(browser.equalsIgnoreCase("chrome")){
//			System.setProperty("webdriver.chrome.driver", pathDrivers + "\\" + "chromedriver.exe");
//			Constant.getWebDriver() = new ChromeDriver();
//			Constant.getWebDriver()_IMPLICIT_WAIT = 10;
//		}
//		else if(browser.equalsIgnoreCase("firefox")){
//			System.setProperty("webdriver.gecko.driver", pathDrivers + "\\" + "geckodriver.exe");
//			Constant.getWebDriver() = new FirefoxDriver();
//			Constant.getWebDriver()_IMPLICIT_WAIT = 60;
//		}
//		//		else if(browser.equalsIgnoreCase("ie")){
//		//			System.setProperty("webdriver.ie.driver", pathDrivers + "\\"  + "IEDriverServer.exe");
//		//			Constant.getWebDriver() = new InternetExplorerDriver();
//		//			Constant.getWebDriver()_IMPLICIT_WAIT = 60;
//		//		}
//		else{
//			throw new Exception("Browser is not correct");
//		}
//		Constant.getWebDriver().manage().timeouts().implicitlyWait(Constant.getWebDriver()_IMPLICIT_WAIT, TimeUnit.SECONDS);
//		Constant.getWebDriver().manage().window().maximize();
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMetohod() {
		System.out.println("Run afterMethod");
//		
//		Constant.getWebDriver().quit();
////		threadLocal.remove();
//		MyThreadLocal.unset();
		
	}
	
	public static void preconditionCreateAndActiveAccount() {
		System.out.println("Precondition - Use \"TC05 - User can create new account\" for precondition");
		Constant.RAILWAY_ACC_MAIL = Utilities.generateRandomText("abcdefghijklmnopqrstuvwxyz1234567890", 7) + "@def.com";
		
		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();
		// Store the current window handle
		String homepageWinHandle = Constant.getWebDriver().getWindowHandle();
		
		// 2. Click on "Register" tab	
		System.out.println("Random Mail will be activated: " + Constant.RAILWAY_ACC_MAIL);
		RegisterPage registerpage = homepage.gotoRegisterPage();
		// 3. Enter valid information into all fields
		// 4. Click on "Register" button
		registerpage.registNewAccount(Constant.RAILWAY_ACC_MAIL, Constant.RAILWAY_ACC_PASSWORD, Constant.RAILWAY_ACC_CONFIRM_PASSWORD, Constant.RAILWAY_ACC_PID);
		
		// VP: New account is created and message "Thank you for registering your account" appears.
		String actualMsg = registerpage.getRegisterThankYouMsg();
		String expectedMsg = "Thank you for registering your account";
		assertEquals(actualMsg, expectedMsg, "Message is not displayed as expected");
		
		//for GMail API
		gmailApi gmail = new gmailApi();
		Constant.getWebDriver().navigate().to(gmail.activeAccount(Constant.RAILWAY_ACC_MAIL));
		
		//for GMail UI
		//GMailLoginPage gmailloginpage = homepage.gotoGMailLoginPage(Constant.GMAIL_URL);		
		//GMailPage gmailpage = gmailloginpage.loginGMail(Constant.GMAIL_PASSWORD);
		//gmailpage.activeAccount(Constant.RAILWAY_ACC_MAIL);
		Constant.getWebDriver().switchTo().window(homepageWinHandle);
	}
	
	public static void preconditionCreateInactiveAccount() {
		System.out.println("Precondition - Use \"TC05 - User can create new account\" for precondition");
		Constant.RAILWAY_ACC_MAIL_UNACTIVE = Utilities.generateRandomText("abcdefghijklmnopqrstuvwxyz1234567890", 7) + "@def.com";
		
		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();
		// Store the current window handle
		String homepageWinHandle = Constant.getWebDriver().getWindowHandle();
		
		// 2. Click on "Register" tab	
		System.out.println("Random Mail will not be activated: " + Constant.RAILWAY_ACC_MAIL_UNACTIVE);
		RegisterPage registerpage = homepage.gotoRegisterPage();
		// 3. Enter valid information into all fields
		// 4. Click on "Register" button
		registerpage.registNewAccount(Constant.RAILWAY_ACC_MAIL_UNACTIVE, Constant.RAILWAY_ACC_PASSWORD, Constant.RAILWAY_ACC_CONFIRM_PASSWORD, Constant.RAILWAY_ACC_PID);
		
		// VP: New account is created and message "Thank you for registering your account" appears.
		String actualMsg = registerpage.getRegisterThankYouMsg();
		String expectedMsg = "Thank you for registering your account";
		assertEquals(actualMsg, expectedMsg, "Message is not displayed as expected");
				
//		Constant.getWebDriver().close();
		Constant.getWebDriver().switchTo().window(homepageWinHandle);
	}
}
