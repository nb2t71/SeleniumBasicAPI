package com.logigear.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.logigear.selenium.constant.Constant;
 
public class TLDriverFactory {
 
    private static OptionsManager optionsManager = new OptionsManager();
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
 
    public static synchronized void setDriver(String browser) {
        if (browser.equals("firefox")) {
            //For Local Usage
        	System.setProperty("webdriver.gecko.driver", Constant.PATH_DRIVERS + "\\" + "geckodriver.exe");
            tlDriver = ThreadLocal.withInitial(() -> new FirefoxDriver(optionsManager.getFirefoxOptions()));
 
            //For Grid Usage
            /*try {
                tlDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), optionsManager.getFirefoxOptions()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }*/
        } else if (browser.equals("chrome")) {
            //For Local Usage
        	System.setProperty("webdriver.chrome.driver", Constant.PATH_DRIVERS + "\\" + "chromedriver.exe");
            tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
 
            /*//For Grid Usage
            try {
                tlDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), optionsManager.getChromeOptions()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }*/
        }
    }
 
    public static synchronized WebDriverWait getWait (WebDriver driver) {
        return new WebDriverWait(driver,20);
    }
 
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }
}
