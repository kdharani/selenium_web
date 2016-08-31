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

public class MailSettingsSetupTest extends BaseTest {
	//Logger log = Logger.getLogger("devpinoyLogger");
	//SoftAssert s_assert = new SoftAssert();
	
	@Factory(dataProviderClass=DataProviderClass.class, dataProvider="login")
	public MailSettingsSetupTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}

/*	@Test(dataProvider = "Logs", enabled = true, priority = 0)
	public void setupMailParameters(Hashtable<String, String> data) 
	{
		if (!data.get("RunMode").equalsIgnoreCase("Y")) 
		{
			throw new SkipException("Run Mode is No");
		}
		try 
		{
			loginPg = new LoginPage(driver);
			homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
					data.get("Environment"));
			platformConfigPg = homePg.openConfigurator();
			mailsPg = platformConfigPg.openMailTab();
			mailsPg.setMailParameters(data.get("Mail_Address"), data.get("SMTP_Host"), data.get("SMTP_Port"), data.get("User_Name"), data.get("Password"));
		} 
		catch (Exception e)
		{ 
			log.error(e.getClass().toString(), e);
			Util.getscreenshot(driver, e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}
	*/
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "Logs", enabled = true, priority = 1)
	public void verifyMailParameters(Hashtable<String, String> data) 
	{
		if (!data.get("RunMode").equalsIgnoreCase("Y")) 
		{
			throw new SkipException("Run Mode is No");
		}
		try 
		{
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			mailsPg = platformConfigPg.openMailTab();	
			mailsPg.verifyMailParameters(data.get("Mail_Address"), data.get("SMTP_Host"), data.get("SMTP_Port"), data.get("User_Name"));
		} 
						

		catch (Exception e)
		{ 
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}

}
