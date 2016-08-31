package com.appsfreedom.tests;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.appsfreedom.fm.pages.HomePage;
import com.appsfreedom.fm.pages.LoginPage;
import com.appsfreedom.utils.DataProviderClass;
import com.appsfreedom.utils.MyLogger;
import com.appsfreedom.utils.ReadExcel;
import com.appsfreedom.utils.Util;

public class AppDesignerTest extends BaseTest {
	
	@Factory(dataProviderClass=DataProviderClass.class, dataProvider="login")
	public AppDesignerTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "appDesigner", enabled = true)
	public void appDesignerLaunchTest(Hashtable<String, String> data) {
		// Declare variable
		String result = "App Designer launched successfull";
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}

		try {
			homePg = new HomePage();
			appDesignPg = homePg.lauchAppDesigner();
			Util.delay(5000);
			Assert.assertTrue(appDesignPg.driver.getTitle().equalsIgnoreCase("App Designer"));
			MyLogger.log.info(result);
			homePg = appDesignPg.closeAppDesigner();
			homePg.switchToFrame();
			homePg.gotoHome();
			Util.delay(5000);
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}


}
