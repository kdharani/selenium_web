package com.appsfreedom.tests;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.appsfreedom.fm.pages.HomePage;
import com.appsfreedom.fm.pages.LoginPage;
import com.appsfreedom.utils.DataProviderClass;
import com.appsfreedom.utils.Excel;
import com.appsfreedom.utils.Initializer;
import com.appsfreedom.utils.MyLogger;
import com.appsfreedom.utils.ReadExcel;
import com.appsfreedom.utils.SQLConnector;
import com.appsfreedom.utils.Util;

public class ConfiguratorTest extends BaseTest {
	//Logger log = Logger.getLogger("devpinoyLogger");
	//SoftAssert s_assert = new SoftAssert();

	@Factory(dataProviderClass=DataProviderClass.class, dataProvider="login")
	public ConfiguratorTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "createCA", enabled = true)
	public void checkConfigTabs(Hashtable<String, String> data) {
		// Declare variable
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			homePg = new HomePage();
			
			platformConfigPg = homePg.openConfigurator();
			pfUserPg = platformConfigPg.openPuserTab();
			homePg = pfUserPg.getHmePage().gotoHome();
			
			platformConfigPg = homePg.openConfigurator();
			connectionMngrPg = platformConfigPg.openConnectionMngrTab();
			homePg = connectionMngrPg.getHmePage().gotoHome();
			
			platformConfigPg = homePg.openConfigurator();
			connectivityPg = platformConfigPg.openConnectivityTab();
			homePg = connectivityPg.getHmePage().gotoHome();

			platformConfigPg = homePg.openConfigurator();
			logsPg  = platformConfigPg.openLogsTab();
			homePg = logsPg.getHmePage().gotoHome();
	
			platformConfigPg = homePg.openConfigurator();
			mailsPg = platformConfigPg.openMailTab();
			homePg = mailsPg.getHmePage().gotoHome();

			//platformConfigPg.openNotifyTab();
			platformConfigPg = homePg.openConfigurator();
			projTranPage = platformConfigPg.openPrjTransportTab();
			homePg = projTranPage.getHmePage().gotoHome();
			
			platformConfigPg = homePg.openConfigurator();
			platformRolePg = platformConfigPg.openProleTab();
			homePg = platformRolePg.getHmePage().gotoHome();
			
			platformConfigPg = homePg.openConfigurator();
			schedulerPg = platformConfigPg.openSchedulerTab();
			homePg = schedulerPg.getHmePage().gotoHome();
			
			//platformConfigPg.openSyncTab();
			platformConfigPg = homePg.openConfigurator();
			OrganizationPg = platformConfigPg.openOrgTab();
			homePg = OrganizationPg.getHmePage().gotoHome();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Util.getscreenshot(e.getMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "createCA", enabled = true)
	public void appWatchLaunchTest(Hashtable<String, String> data) {
		// Declare variable
		String result = "App watch launched successfully";
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}

		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			homePg = new HomePage();
			homePg.openAppWatch();
			Assert.assertTrue(homePg.driver.getPageSource().contains("App Watch"));
			MyLogger.log.info(result);
			homePg.gotoHome();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "createCA", enabled = false)
	public void applibraryLaunchTest(Hashtable<String, String> data) {
		// Declare variable
		String result = "App library launched successfully";
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}

		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			homePg = new HomePage();
			homePg.openAppLib();
			Assert.assertTrue(homePg.driver.getPageSource().contains("App Library"));
			MyLogger.log.info(result);
			homePg.gotoHome();
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "createCA", enabled = false)
	public void createCA(Hashtable<String, String> data) {
		// Declare variable
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			platformConfigPg = homePg.openConfigurator();
			platformConfigPg.openOTATab();
			platformConfigPg.clickCreateBtn();
			if (platformConfigPg.saveConfigFiles.isDisplayed()) {
				platformConfigPg.uploadConfigurationFiles();
			}
			platformConfigPg.enterAppId(data.get("AppId"));
			platformConfigPg.enterAppName(data.get("AppName"));
			platformConfigPg.selectMicroApp(data.get("MicroApp"));
			platformConfigPg.checkExternalLinks();
			platformConfigPg.uploadAppIcon();
			platformConfigPg.uploadSplashIcon();
			if (data.get("IOS").equalsIgnoreCase("Y")) {
				platformConfigPg.uploadAppleCertificates(data.get("Push"));
			}
			if (data.get("Android").equalsIgnoreCase("Y")) {
				platformConfigPg.uploadAndroidCertificates(data.get("Push"));
			}

			platformConfigPg.clickGenerate();
			while (platformConfigPg.checkOTAStatus(data.get("AppName")).equalsIgnoreCase("Initiated")) {
				Util.delay(60000);
				platformConfigPg.refreshOTATable();
			}

			Assert.assertTrue(platformConfigPg.checkOTAStatus(data.get("AppName")).equalsIgnoreCase("Finished"));

		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "submitTicket", enabled = false)
	public void submitSupportTicketTest(Hashtable<String, String> data) {
		// Declare variable
		String URL = data.get("URL");
		String userId = data.get("UserId");
		String pwd = data.get("Password");
		String tenantId = data.get("TenantId");
		String env = data.get("Environment");
		String usrNme = data.get("Name");
		String usrPh = data.get("Phone");
		String tktSubj = data.get("Subject");
		String tktDetail = data.get("Details");
		String result = "";

		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			loginPg = new LoginPage();
			homePg = loginPg.login(URL, userId, pwd, tenantId, env);
			homePg.submitSupportTkt(usrNme, usrPh, tktSubj, tktDetail);
			if (Util.isElementDisplayed(homePg.alertPopupModal)) {
				result = homePg.alertMessage.getText();
				MyLogger.log.info(result);
				homePg.closePopupModel();
			}
			s_assert.assertTrue(result.contains("Support request has been submitted"), "Ticket submission failed");

		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		} finally {
			s_assert.assertAll();
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "createTenant", enabled = false)
	public void createTenantTest(Hashtable<String, String> data) {
		// Declare variable
		String URL = data.get("URL");
		String userId = data.get("UserId");
		String pwd = data.get("Password");
		String tenantId = data.get("TenantId");
		String env = data.get("Environment");
		String tenantName = data.get("TenantName");
		String salutation = data.get("Salutation");
		String fname = data.get("Fname");
		String lname = data.get("Lname");
		String title = data.get("Title");
		String email = data.get("Email");
		String loginId = data.get("LoginId");
		String pack = data.get("Package");
		String result = "";

		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			loginPg = new LoginPage();
			homePg = loginPg.login(URL, userId, pwd, tenantId, env);
			platformConfigPg = homePg.openConfigurator();
			platformConfigPg.openCreateTenantTab();
			platformConfigPg.setTenantInfo(tenantName);
			platformConfigPg.setTenantAddress();
			platformConfigPg.setMainContactInfo();
			platformConfigPg.setTenantPackage(pack);
			platformConfigPg.setTenantAdminInfo(salutation, fname, lname, title, email, loginId);
			if (Util.isElementDisplayed(platformConfigPg.alertPopupModal)) {
				result = homePg.alertMessage.getText();
				MyLogger.log.info(result);
				homePg.closePopupModel();
			}
			s_assert.assertTrue(result.contains('"' + tenantName + '"' + " was created successfully"),
					"Tenant creation failed");

		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		} finally {
			s_assert.assertAll();
		}
	}
	

}
