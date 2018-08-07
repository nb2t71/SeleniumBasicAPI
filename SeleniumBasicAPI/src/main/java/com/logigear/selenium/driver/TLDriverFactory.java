package com.logigear.selenium.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.logigear.selenium.constant.Constant;

public class TLDriverFactory {

	private static OptionsManager optionsManager = new OptionsManager();
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public static synchronized void setDriver(String browser, String runMode, String hubURL) {
		if(runMode==null) 
			runMode = "local";
		switch (runMode) {
		case "local":
			// For Local Usage
			if (browser.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", Constant.PATH_DRIVERS + "\\" + "geckodriver.exe");
				tlDriver = ThreadLocal.withInitial(() -> new FirefoxDriver(optionsManager.getFirefoxOptions()));
			} else if (browser.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", Constant.PATH_DRIVERS + "\\" + "chromedriver.exe");
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			break;
		case "remote":
			// For Grid Usage
			if (browser.equals("firefox")) {	
				  try { tlDriver.set(new RemoteWebDriver(new URL(hubURL), optionsManager.getFirefoxOptions())); }
				  catch (MalformedURLException e) { e.printStackTrace(); }
			} else if (browser.equals("chrome")) {				
				  try { tlDriver.set(new RemoteWebDriver(new URL(hubURL), optionsManager.getChromeOptions())); }
				  catch (MalformedURLException e) { e.printStackTrace(); }			 
			}
			break;

		default:
			break;
		}
		
	}

	public static synchronized WebDriverWait getWait(WebDriver driver) {
		return new WebDriverWait(driver, 20);
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
}
