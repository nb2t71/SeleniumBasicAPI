package com.logigear.selenium.constant;

import org.openqa.selenium.WebDriver;

import com.logigear.selenium.common.Utilities;
import com.logigear.selenium.driver.TLDriverFactory;

public class Constant {

	public static WebDriver WEBDRIVER;
	public static long WEBDRIVER_IMPLICIT_WAIT = 10;
	
	public static String PATH_DRIVERS = Utilities.getProjectPath() + "\\src\\test\\resources\\drivers";
	
	public static final String RAILWAY_URL = "http://192.168.171.251:8081";
//	public static final String RAILWAY_URL = "http://192.168.171.127:8082";
	public static final String RAILWAY_USERNAME = "thaonbt@gmail.com";
	public static final String RAILWAY_PASSWORD = "12345678";
	public static String RAILWAY_ACC_MAIL = Utilities.generateRandomText("abcdefghijklmnopqrstuvwxyz1234567890", 7) + "@def.com";
	public static String RAILWAY_ACC_MAIL_UNACTIVE = Utilities.generateRandomText("abcdefghijklmnopqrstuvwxyz1234567890", 7) + "@def.com";
	public static final String RAILWAY_ACC_PASSWORD = "12345678";
	public static final String RAILWAY_ACC_CONFIRM_PASSWORD = "12345678";
	public static final String RAILWAY_ACC_PID = "01010101";
	
	public static final String GMAIL_USERNAME = "seltrain2015";		
	public static final String GMAIL_DOMAIN = "gmail.com";
	public static final String GMAIL_PASSWORD = "!logigear123";
	public static final String GMAIL_URL = "https://accounts.google.com/AccountChooser?"
			+ "continue=https%3A%2F%2Fmail.google.com%2Fmail%2Fmu%2Fmp%2F%3F"
			+ "authuser%3D"+ GMAIL_USERNAME +"%40"+ GMAIL_DOMAIN + "&service=mail&Email="+ GMAIL_USERNAME +"@" + GMAIL_DOMAIN;
	
//	https://accounts.google.com/AccountChooser?continue=https%3A%2F%2Fmail.google.com%2Fmail%2Fmu%2Fmp%2F%3Fauthuser%3DUSERNAME%40DOMAIN&service=mail&Email=USERNAME@DOMAIN
//	https://accounts.google.com/AccountChooser?continue=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F%3Fauthuser%3Dseltrain2015%40gmail.com&service=mail&Email=seltrain2015@gmail.com

	public static WebDriver getWebDriver() {
		return TLDriverFactory.getDriver();
	}

}
