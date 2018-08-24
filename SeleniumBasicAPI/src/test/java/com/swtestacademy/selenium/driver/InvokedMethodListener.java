package com.swtestacademy.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class InvokedMethodListener implements IInvokedMethodListener {
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			System.out.println("Test Method BeforeInvocation is started. " + Thread.currentThread().getId());
			String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browser");
			String runMode = method.getTestMethod().getXmlTest().getLocalParameters().get("runmode");
			String hubURL = method.getTestMethod().getXmlTest().getLocalParameters().get("huburl");
			TLDriverFactory.setDriver(browserName, runMode, hubURL);
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			System.out.println("Test Method AfterInvocation is started. " + Thread.currentThread().getId());
			WebDriver driver = TLDriverFactory.getDriver();
			if (driver != null) {
				driver.quit();
			}
		}
	}
}