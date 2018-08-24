package com.swtestacademy.selenium.utilities.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.logigear.selenium.test.BaseTest;
import com.swtestacademy.selenium.utilities.extentreports.ExtentManager;

//**********************************************************************************************************
//Author: Onur Baskirt
//Description: This is the main listener class.
//**********************************************************************************************************
public class TestListener extends BaseTest implements ITestListener {

  //Extent Report Declarations
  private static ExtentReports extent = ExtentManager.createInstance();
  private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

  @Override
  public synchronized void onStart(ITestContext context) {
      System.out.println("Extent Reports Version 3 Test Suite started!");
  }

  @Override
  public synchronized void onFinish(ITestContext context) {
      System.out.println(("Extent Reports Version 3  Test Suite is ending!"));
      extent.flush();
  }

  @Override
  public synchronized void onTestStart(ITestResult result) {
      System.out.println((result.getMethod().getMethodName() + " started!"));
      ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
      test.set(extentTest);
  }

  @Override
  public synchronized void onTestSuccess(ITestResult result) {
      System.out.println((result.getMethod().getMethodName() + " passed!"));
      test.get().pass("Test passed");
  }

  @Override
  public synchronized void onTestFailure(ITestResult result) {
      System.out.println((result.getMethod().getMethodName() + " failed!"));
      test.get().fail(result.getThrowable());
  }

  @Override
  public synchronized void onTestSkipped(ITestResult result) {
      System.out.println((result.getMethod().getMethodName() + " skipped!"));
      test.get().skip(result.getThrowable());
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
      System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
  }
}

/*
 * ExtentReport V2.x
 */

/*public class TestListener extends BaseTest implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	// Before starting all tests, below method runs.
	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println("I am in onStart method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", TLDriverFactory.getDriver());
//        iTestContext.setAttribute("WebDriver", this.driver);
	}

	// After ending all tests, below method runs.
	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println("I am in onFinish method " + iTestContext.getName());
		// Do tier down operations for extentreports reporting!
		ExtentTestManager.endTest();
		ExtentManager.getReporter().flush();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
		// Start operation for extentreports.
		ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(), "");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
		// Extentreports log operation for passed tests.
		ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");

		// Get driver from BaseTest and assign to local webdriver variable.
		Object testClass = iTestResult.getInstance();
		WebDriver webDriver = ((TLDriverFactory) testClass).getDriver();
//		WebDriver webDriver = ((BaseTest) testClass).getDriver();

		// Take base64Screenshot screenshot.
		String base64Screenshot = "data:image/png;base64,"
				+ ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);

		// Extentreports log and screenshot operations for failed tests.
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
				ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
		// Extentreports log operation for skipped tests.
		ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

}*/
