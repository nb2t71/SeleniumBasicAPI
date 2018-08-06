package com.logigear.selenium.test.railway;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.logigear.selenium.constant.Constant;
import com.logigear.selenium.pageobjects.railway.ChangePasswordPage;
import com.logigear.selenium.pageobjects.railway.HomePage;
import com.logigear.selenium.pageobjects.railway.LoginPage;

/**
 * @author thao.thanh.nguyen
 * This test involves 
 * TC07	- User can change password
 * TC08	- User can't change password when "New Password" and "Confirm Password" are different.
 */

public class TC03_TestFunctionChangePassword_DataProvider extends Precondition_Invoked{
	
	String strMail = Constant.RAILWAY_ACC_MAIL;
	String strPassword = Constant.RAILWAY_ACC_PASSWORD;
	String strTC08NewPassword = "a123:\"/{}!@$\\";
	String strTC08ConfirmPassword = "b456:\"/{}!@$\\";
	String actualMsg;
	String expectedMsg;

	@Test
	public void precondition() {
		Precondition_Invoked.preconditionCreateAndActiveAccount();
	}
	
	@Test(dependsOnMethods="precondition", dataProvider="data")
	public void TC07_TC08(String outMessage, String strUsername, String strCurrentPassword, String strNewPassword, String strConfirmPassword, String strExpectedMessage) {
		System.out.println(outMessage);
	
		// 1. Navigate to QA Railway Website
		HomePage homepage = new HomePage();
		homepage.open();

		// 2. Login with valid account	
		LoginPage loginpage = homepage.gotoLoginPage();
		loginpage.login(strUsername, strCurrentPassword);

		// FOR: TC08 - User can't change password when \"New Password\" and \"Confirm Password\" are different.
		// 3. Click on "Change Password" tab
		// 4. Enter valid information into "Current Password" textbox but enter "a123:"/{}!@$\" into "New Password" textbox and "b456:"/{}!@$\" into "Confirm Password" textbox.
		// 5. Click on "Change Password" button
		
		// FOR: TC07 - User can change password 
		// 3. Click on "Change Password" tab
		// 4. Enter valid value into all fields
		// 5. Click on "Change Password" button
		
		ChangePasswordPage changepasswordpage = homepage.gotoChangePasswordPage();
		changepasswordpage.changePassword(strCurrentPassword, strNewPassword, strConfirmPassword);
		
		// VP: Message "Your password has been updated" appears.
		actualMsg = changepasswordpage.getChangePasswordMsg();
		expectedMsg = strExpectedMessage;		
		assertEquals(actualMsg, expectedMsg, "Expected message is not displayed");
	}
	
	@DataProvider(name="data")
	public Object[][] getDataFromDataprovider(){
		return new Object[][] {
			{"Involve TC08 - User can change password", Constant.RAILWAY_ACC_MAIL, Constant.RAILWAY_ACC_PASSWORD, strTC08NewPassword, strTC08ConfirmPassword, "Password change failed. Please correct the errors and try again."},
			{"Involve TC07 - User can change password", Constant.RAILWAY_ACC_MAIL, Constant.RAILWAY_ACC_PASSWORD, strPassword+"updated", strPassword+"updated", "Your password has been updated!"}
		};
	}
}
