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

public class ProcessModelerTest extends BaseTest{
	//Logger log = Logger.getLogger("devpinoyLogger");
	@Factory(dataProviderClass=DataProviderClass.class, dataProvider="login")
	public ProcessModelerTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "processModeler", enabled = true)
	public void processModelerLaunchTest(Hashtable<String, String> data) {
		// Declare variable
		String result = "Process Modeler launched successfully";
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}

		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			homePg = new HomePage();
			processModelerPg = homePg.lauchProcessModeler();
			Assert.assertTrue(processModelerPg.driver.getTitle().contains("Process Modeler"));
			MyLogger.log.info(result);
			homePg = processModelerPg.closeProcessModeler();
			homePg.switchToFrame();
			homePg.gotoHome();
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}

}
