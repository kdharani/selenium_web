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
import com.appsfreedom.utils.SQLConnector;
import com.appsfreedom.utils.Util;

public class LogsTest extends BaseTest {
	//Logger log = Logger.getLogger("devpinoyLogger");
	//SoftAssert s_assert = new SoftAssert();

	@Factory(dataProviderClass=DataProviderClass.class, dataProvider="login")
	public LogsTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "Logs", enabled = true)
	public void apiLogCountTest(Hashtable<String, String> data) {
		int actualLogcount=0;
		int expectedLogcount=0;
		int actualsearchLogcount=0;
		
		// Declare variable
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try 
		{
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			
			logsPg = platformConfigPg.openLogsTab();
			actualLogcount = logsPg.getLogsCounts();
			expectedLogcount = SQLConnector.getLogsCount(data.get("TenantId"));
			MyLogger.log.info(actualLogcount);
			MyLogger.log.info(expectedLogcount);
			s_assert.assertEquals(actualLogcount, expectedLogcount);
			s_assert.assertAll();
			
			actualsearchLogcount = logsPg.searchLogEntries();
			expectedLogcount = SQLConnector.getsearchLogsCount(data.get("TenantId"));
			MyLogger.log.info(actualsearchLogcount);
			MyLogger.log.info(expectedLogcount);
			s_assert.assertEquals(actualsearchLogcount, expectedLogcount);
			s_assert.assertAll();
			
			
		} 
		catch (Exception e)
		{ 
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}

}
