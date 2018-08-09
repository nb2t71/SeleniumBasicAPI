package com.swtestacademy.selenium.utilities.listeners;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.relevantcodes.extentreports.LogStatus;
import com.swtestacademy.selenium.driver.TLDriverFactory;
import com.swtestacademy.selenium.utilities.extentreports.ExtentTestManager;

public class Retry implements IRetryAnalyzer {

	private int count = 0;
	private static int maxTry = 1; // Run the failed test 2 times

	@Override
	public boolean retry(ITestResult iTestResult) {
		if (!iTestResult.isSuccess()) { // Check if test not succeed
			if (count < maxTry) { // Check if maxtry count is reached
				count++; // Increase the maxTry count by 1
				iTestResult.setStatus(ITestResult.FAILURE); // Mark test as failed
				extendReportsFailOperations(iTestResult); // ExtentReports fail operations
				return true; // Tells TestNG to re-run the test
			}
		} else {
			iTestResult.setStatus(ITestResult.SUCCESS); // If test passes, TestNG marks it as passed
		}
		return false;
	}

	public void extendReportsFailOperations(ITestResult iTestResult) {
		Object testClass = iTestResult.getInstance();
//		WebDriver webDriver = ((BaseTest) testClass).getDriver();
		WebDriver webDriver = ((TLDriverFactory) testClass).getDriver();
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
		try {
			ExtentTestManager.getTest().addScreenCaptureFromPath(base64Screenshot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
