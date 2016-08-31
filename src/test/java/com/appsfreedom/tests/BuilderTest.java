package com.appsfreedom.tests;

import java.util.Hashtable;

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

public class BuilderTest extends BaseTest {
	//Logger log = Logger.getLogger("devpinoyLogger");
	
	@Factory(dataProviderClass=DataProviderClass.class, dataProvider="login")
	public BuilderTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "createBOS", enabled = true)
	public void builderLaunchTest(Hashtable<String, String> data) {
		// Declare variable
		String result = "Builder launched successfully";
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}

		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			homePg = new HomePage();
			builderPg = homePg.lauchBuilder();
			Assert.assertTrue(builderPg.driver.getTitle().equalsIgnoreCase("Integration Builder"));
			MyLogger.log.info(result);
			homePg = builderPg.closeBuilder();
			homePg.switchToFrame();
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "createBOS", enabled = true)
	public void createBOS(Hashtable<String, String> data) {
		// Declare variable
		String result = "Bos created successfull";

		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			homePg = new HomePage();
			builderPg = homePg.lauchBuilder();
			builderPg.switchToMyToolbx();
			builderPg.expandFreedomDb();
			builderPg.addSelectSQL();
			homePg = builderPg.closeBuilder();
			homePg.switchToFrame();
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "executeBOS", enabled = true)
	public void executeBOS(Hashtable<String, String> data) {
		// Declare variable
		String result = "BOS executed successfull";

		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			homePg = new HomePage();
			builderPg = homePg.lauchBuilder();
			builderPg.switchToMyProjects();
			builderPg.selectBOS(data.get("PrjName"), data.get("BOSName"));
			s_assert.assertTrue(builderPg.testBos(data.get("Input"),data.get("Output")));
			homePg = builderPg.closeBuilder();
			homePg.switchToFrame();
			s_assert.assertAll();
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "importBOS", enabled = true)
	public void importBOS(Hashtable<String, String> data) {
		// Declare variable
		String result = "BOS imported successfull";

		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			builderPg = homePg.lauchBuilder();
			builderPg.importBos(System.getProperty("user.dir")+"\\resources\\Charts_Normal.xml");
			builderPg.testBos(data.get("Input"),data.get("Output"));
			homePg = builderPg.closeBuilder();
			homePg.switchToFrame();
			//homePg.logout();
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}

}
