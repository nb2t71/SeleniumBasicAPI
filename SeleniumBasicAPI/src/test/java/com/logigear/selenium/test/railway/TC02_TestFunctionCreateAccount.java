package com.logigear.selenium.test.railway;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.logigear.selenium.common.Utilities;
import com.logigear.selenium.constant.Constant;
import com.logigear.selenium.pageobjects.railway.HomePage;
import com.logigear.selenium.pageobjects.railway.RegisterPage;

/**
 * @author thao.thanh.nguyen
 * This test involves 
 * TC09	- User can't create account with "Confirm password" is not the same as "Password"
 */

public class TC02_TestFunctionCreateAccount extends Precondition{
	
	String strMail = Utilities.generateRandomText("abcdefghijklmnopqrstuvwxyz1234567890", 7) + "@def.com";

	@Test
	public void TC09() {
		System.out.println("TC09 - User can't create account with \"Confirm password\" is not the same as \"Password\"");
		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();

		// 2. Click on "Register" tab	
		RegisterPage registerpage = homepage.gotoRegisterPage();
				
		// 3. Enter valid information into all fields except "Confirm password" is not the same as "Password" 
		// 4. Click on "Register" button
		registerpage.registNewAccount(strMail, Constant.RAILWAY_PASSWORD, Constant.RAILWAY_PASSWORD+"New", Constant.RAILWAY_ACC_PID);
		
		// VP: Message "There're errors in the form. Please correct the errors and try again." appears.
		String actualMsg = registerpage.getRegisterErrorMsg();
		String expectedMsg = "There're errors in the form. Please correct the errors and try again.";
		
		assertEquals(actualMsg, expectedMsg, "Error message is displayed as expected");
	}
	
}
