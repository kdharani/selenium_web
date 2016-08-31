package com.appsfreedom.tests;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.appsfreedom.fm.pages.LoginPage;
import com.appsfreedom.utils.ReadExcel;
import com.appsfreedom.utils.Util;

public class LoginTest extends BaseTest{
	//Logger log = Logger.getLogger("devpinoyLogger");
	
	/*@Test(dataProvider = "loginTest",enabled = true)
	public void loginTest(Hashtable<String, String> data) {
		// Declare variable
		String result = "Login successfull";
		if(!data.get("RunMode").equalsIgnoreCase("Y")){
			throw new SkipException("Run Mode is No");
		}

		try {
			loginPg = new LoginPage(driver);
			homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
					data.get("Environment"));
			Assert.assertTrue(homePg.logoutBtn.isDisplayed());
			loginPg = homePg.logout();
			Util.delay(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Util.getscreenshot(driver, e.getClass().toString());
			log.debug(e.getLocalizedMessage());
			Assert.fail(e.getMessage());
		}
	}*/

	/*@DataProvider()
	public Object[][] loginTest() {
		return ReadExcel.getData("LoginTest", excel);

	}*/
}


