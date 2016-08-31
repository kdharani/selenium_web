package com.appsfreedom.tests;

import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.appsfreedom.fm.pages.HomePage;
import com.appsfreedom.fm.pages.LoginPage;
import com.appsfreedom.utils.DataProviderClass;
import com.appsfreedom.utils.MyLogger;
import com.appsfreedom.utils.ReadExcel;
import com.appsfreedom.utils.SQLConnector;
import com.appsfreedom.utils.Util;

public class AppWatchTest extends BaseTest {
	//Logger log = Logger.getLogger("devpinoyLogger");
	//SoftAssert s_assert = new SoftAssert();
	@Factory(dataProviderClass=DataProviderClass.class, dataProvider="login")
	public AppWatchTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "appWatch", enabled = true)
	public void driveusageReport(Hashtable<String, String> data) {
		// Declare variable
		float usage=0;
		float available=0;
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			homePg = new HomePage();
			appWatchPg = homePg.openAppWatch();
			appWatchPg.openUsageReport();
			usage = appWatchPg.getUsedSapce();
			available = appWatchPg.getAvailableSapce();
			
			s_assert.assertEquals(usage,SQLConnector.getDriveUsage(data.get("TenantId")), "Usage mismatch");
			s_assert.assertEquals(available, SQLConnector.getAvailableSpace(data.get("TenantId")), "Available space mismatch");
			appWatchPg.getHmePage().gotoHome();
			s_assert.assertAll();

		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "appWatch", enabled = true)
	public void freedomAppReport(Hashtable<String, String> data) {
		// Declare variable
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			homePg = new HomePage();
			appWatchPg = homePg.openAppWatch();
			appWatchPg.freedomAppReport();
			List<String> ui_userList = appWatchPg.getTopUsers();
			List<String> db_userList = SQLConnector.getTopUsers(data.get("TenantId"));
			if(ui_userList.size()==db_userList.size()){
				for(int k=0;k<ui_userList.size();k++){
					s_assert.assertEquals(ui_userList.get(k),db_userList.get(k), "Top user mismatch");
					/*if(ui_userList.get(k).equalsIgnoreCase(db_userList.get(k))){
						log.info(ui_userList.get(k)+" is equal to "+db_userList.get(k));
					}else{
						log.info(ui_userList.get(k)+" is NOT equal to "+db_userList.get(k));
					}*/
				}
			}else{
				MyLogger.log.error("Top user count mismatch");
			}
			
			List<String> ui_appList = appWatchPg.getTopApps();
			List<String> db_appList = SQLConnector.getTopApps(data.get("TenantId"));
			if(ui_appList.size()==db_appList.size()){
				for(int k=0;k<ui_appList.size();k++){
					s_assert.assertEquals(ui_appList.get(k),db_appList.get(k), "Top Apps mismatch");
					/*if(ui_appList.get(k).equalsIgnoreCase(db_appList.get(k))){
						log.info(ui_appList.get(k)+" is equal to "+db_appList.get(k));
					}else{
						log.info(ui_appList.get(k)+" is NOT equal to "+db_appList.get(k));
					}*/
				}
			}else{
				MyLogger.log.error("Top Apps count mismatch");
			}
			
			/*String appid="",devicetype="";
			List<WebElement> amaintblrows = driver.findElements(By.xpath("//*[@id='access_report_table_body']/tr"));
			
			Map<String,String> maintblresultset = SQLConnector.getMainTable(data.get("TenantId"));
			log.info("freedomAppReport maintbl size:"+amaintblrows.size()+" db size:"+maintblresultset.size());
			if(maintblresultset.size()==amaintblrows.size()){
				String usercount ="";
				log.info("freedomAppReport maintbl record count matches");
				for(int k=0;k<=amaintblrows.size();k++){
					List<WebElement> cols = driver.findElements(By.xpath("//*[@id='access_report_table_body']/tr["+k+"]/td"));
					log.info("freedomAppReport maintbl col size is " + cols.size());
					if(cols.size()>0){
						if(appid.equalsIgnoreCase(cols.get(1).getText()) && devicetype.equalsIgnoreCase(cols.get(3).getText())){
							log.info("freedomAppReport maintbl input available in UI Table");
							usercount = cols.get(4).getText();
						}
					}
				}
				String la_de = maintblresultset.get(appid+"-"+devicetype);
				if(la_de!=null){
					log.info("freedomAppReport maintbl input available in DB Table");
					if(la_de.equalsIgnoreCase(usercount)){
						log.info("freedomAppReport maintbl user count are equal in both UI and DB");
					}
				}
			}else{
				log.info("freedomAppReport maintbl record count mismatches");
			}*/
			appWatchPg.getHmePage().gotoHome();
			//s_assert.assertAll();

		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "appWatch", enabled = true)
	public void appDetailsReport(Hashtable<String, String> data) {
		// Declare variable
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			homePg = new HomePage();
			appWatchPg = homePg.openAppWatch();
			appWatchPg.appDetailsReport(data.get("AppName"));
			appWatchPg.getHmePage().gotoHome();
			//s_assert.assertAll();

		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "appWatch", enabled = true)
	public void accessReport(Hashtable<String, String> data) {
		// Declare variable
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			homePg = new HomePage();
			appWatchPg = homePg.openAppWatch();
			appWatchPg.accessReport(data.get("LoginName"),data.get("AppName"),data.get("TenantId"));
			appWatchPg.getHmePage().gotoHome();
			//s_assert.assertAll();

		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}
	

}
