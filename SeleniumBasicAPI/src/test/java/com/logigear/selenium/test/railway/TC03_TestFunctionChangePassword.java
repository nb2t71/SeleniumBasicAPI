package com.logigear.selenium.test.railway;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.logigear.selenium.page.railway.ChangePasswordPage;
import com.logigear.selenium.page.railway.HomePage;
import com.logigear.selenium.page.railway.LoginPage;
import com.logigear.selenium.test.Precondition_Invoked;
import com.logigear.selenium.utilities.constant.Constant;

/**
 * @author thao.thanh.nguyen This test involves TC07 - User can change password
 *         TC08 - User can't change password when "New Password" and "Confirm
 *         Password" are different.
 */

public class TC03_TestFunctionChangePassword extends Precondition_Invoked {

	String strTC08NewPassword = "a123:\"/{}!@$\\";
	String strTC08ConfirmPassword = "b456:\"/{}!@$\\";
	String actualMsg;
	String expectedMsg;

	@Test
	public void precondition() {
		Precondition_Invoked.preconditionCreateAndActiveAccount();
	}

	@Test(dependsOnMethods = "precondition", priority = 2)
	public void TC07_TC08() {
		System.out.println("TC07 - User can change password");

		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();

		// 2. Login with valid account
		LoginPage loginpage = homepage.gotoLoginPage();
		loginpage.login(Constant.RAILWAY_ACC_MAIL, Constant.RAILWAY_ACC_PASSWORD);

		// 3. Click on "Change Password" tab
		ChangePasswordPage changepasswordpage = homepage.gotoChangePasswordPage();

		// 4. Enter valid value into all fields
		// 5. Click on "Change Password" button
		changepasswordpage.changePassword(Constant.RAILWAY_ACC_PASSWORD, Constant.RAILWAY_ACC_PASSWORD + "updated",
				Constant.RAILWAY_ACC_PASSWORD + "updated");

		// VP: Message "Your password has been updated" appears.
		actualMsg = changepasswordpage.getSuccessMsg();
		expectedMsg = "Your password has been updated!";
		assertEquals(actualMsg, expectedMsg, "Your password has been updated!");
	}

	@Test(dependsOnMethods = "precondition", priority = 1)
	public void TC08() {
		System.out.println(
				"TC08 - User can't change password when \"New Password\" and \"Confirm Password\" are different.");

		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();

		// 2. Login with a valid account
		LoginPage loginpage = homepage.gotoLoginPage();
		loginpage.login(Constant.RAILWAY_ACC_MAIL, Constant.RAILWAY_ACC_PASSWORD);

		// 3. Click on "Change Password" tab
		ChangePasswordPage changepasswordpage = loginpage.gotoChangePasswordPage();

		// 4. Enter valid information into "Current Password" textbox but enter
		// "a123:"/{}!@$\" into "New Password" textbox and "b456:"/{}!@$\" into "Confirm
		// Password" textbox.
		// 5. Click on "Change Password" button
		changepasswordpage.changePassword(Constant.RAILWAY_ACC_PASSWORD, strTC08NewPassword, strTC08ConfirmPassword);

		// VP: Error message "Password change failed. Please correct the errors and try
		// again." appears.
		actualMsg = changepasswordpage.getErrorMsg();
		expectedMsg = "Password change failed. Please correct the errors and try again.";

		assertEquals(actualMsg, expectedMsg, "Error message is displayed as expected");
	}
}
