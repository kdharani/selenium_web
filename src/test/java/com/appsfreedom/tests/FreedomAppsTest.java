package com.appsfreedom.tests;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.appsfreedom.fm.pages.HomePage;
import com.appsfreedom.fm.pages.LoginPage;
import com.appsfreedom.utils.DataProviderClass;
import com.appsfreedom.utils.ReadExcel;
import com.appsfreedom.utils.Util;

public class FreedomAppsTest extends BaseTest{
	Logger log = Logger.getLogger("devpinoyLogger");
	
	@Factory(dataProviderClass=DataProviderClass.class, dataProvider = "login")
	public FreedomAppsTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "freedomApps", enabled = true)
	public void freedomApp(Hashtable<String, String> data) {
		String result = "Login successfull";
		if(!data.get("RunMode").equalsIgnoreCase("Y")){
			throw new SkipException("Run Mode is No");
		}

		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			homePg = new HomePage();
			appLibPg = homePg.openAppLib();
			freedomAppsPg = appLibPg.openFreedomAppsTab();
			freedomAppsPg.clickFreedomApps();
			log.error("Message from FreedomApp Library");
			Util.delay(2000);
			freedomAppsPg.clickFreedomAppUser();
			Util.delay(2000);
			freedomAppsPg.clickAddUserGroup();
			Util.delay(2000);
			freedomAppsPg.clickSelectAppUser();
			Util.delay(2000);
			freedomAppsPg.clickSelectAvailableUser();
			Util.delay(2000);
			freedomAppsPg.clickSaveAvailableUser();
			Util.delay(2000);
			freedomAppsPg.clickFreedomAppUser();
			Util.delay(2000);
			freedomAppsPg.removeUser();
			Util.delay(2000);
			freedomAppsPg.clickLanguage();
			Util.delay(1000);
			//freedomAppsPg.addLanguage();
			Util.delay(2000);
			freedomAppsPg.clickCustomUri();
			Util.delay(2000);
			//freedomAppsPg.clickAssignContainerApps();
			//Util.delay(5000);
			freedomAppsPg.removeContainerApp();
			//Util.delay(2000);
			freedomAppsPg.closeFreedomAppsDialog();
			//Util.delay(5000);
			log.info(result);
			//homePg.logout();
			//Util.delay(5000);
		}catch(UnhandledAlertException e){
			log.info("UnhandledAlertException "+e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Util.getscreenshot(e.getClass().getSimpleName());
			log.debug(e.getLocalizedMessage());
			Assert.fail(e.getMessage());
		}
	}
	
}