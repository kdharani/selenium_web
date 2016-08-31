package com.appsfreedom.tests;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.appsfreedom.fm.pages.HomePage;
import com.appsfreedom.utils.DataProviderClass;
import com.appsfreedom.utils.MyLogger;
import com.appsfreedom.utils.ReadExcel;
import com.appsfreedom.utils.Util;

public class FreedomDriveTest extends BaseTest{
	//Logger log = Logger.getLogger("devpinoyLogger");

	@Factory(dataProviderClass=DataProviderClass.class, dataProvider="login")
	public FreedomDriveTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "freedomDrive", enabled = true)
	public void freedomDriveLaunchTest(Hashtable<String, String> data) {
		// Declare variable
		String result = "Drive launched successfully";
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}

		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			homePg = new HomePage();
			drivePg = homePg.lauchDrive();
			Assert.assertTrue(drivePg.driver.getTitle().contains("Drive"));
			MyLogger.log.info(result);
			homePg = drivePg.closeDrive();
			homePg.switchToFrame();
			homePg.gotoHome();
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}

}
