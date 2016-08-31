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
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AppTest extends BaseTest {

	
	@Factory(dataProviderClass=DataProviderClass.class, dataProvider="login")
	public AppTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}
	
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "getData",enabled = false)
	public void loginTest(Hashtable<String, String> data) {
		// Declare variable
		test = extent.startTest("Login Test", "Login test starting");
		if(!data.get("RunMode").equalsIgnoreCase("Y")){
			throw new SkipException("Run Mode is No");
		}

		try {
			loginPg = new LoginPage();
			homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
					data.get("Environment"));
			test.log(LogStatus.PASS, "Login successfull");
			loginPg = homePg.logout();
			test.log(LogStatus.PASS, "Logout successfull");
			Util.delay(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Util.getscreenshot(e.getMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "getData", enabled = true)
	public void BuilderTest(Hashtable<String, String> data) {
		test = extent.startTest("Builder Test", "Builder test starting");

		try {
			homePg = new HomePage();
			builderPg = homePg.lauchBuilder();
			test.log(LogStatus.INFO, "Builder launched  successfully");
			builderPg.switchToMyToolbx();
			test.log(LogStatus.INFO, "Switched to MyToolbox successfully");
			builderPg.expandFreedomDb();
			builderPg.addSelectSQL();
			Util.delay(5000);
			homePg = builderPg.closeBuilder();
			test.log(LogStatus.INFO, "Builder successfull");
			homePg.switchToFrame();
			test.log(LogStatus.INFO, "Switched back to FM successfully");
			homePg.gotoHome();
			test.log(LogStatus.INFO, "Navigated to homepage successfully");
			Util.delay(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Util.getscreenshot(e.getMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "getData", enabled = true)
	public void appDesignerTest(Hashtable<String, String> data) {
		test = extent.startTest("App Designer", "App Designer test starting");
		try {
			homePg = new HomePage();
			appDesignPg = homePg.lauchAppDesigner();
			test.log(LogStatus.INFO, "Launched App Designer");
			Util.delay(5000);
			homePg = appDesignPg.closeAppDesigner();
			test.log(LogStatus.INFO, "Closed App Designer");
			homePg.switchToFrame();
			homePg.gotoHome();
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "getData", enabled = true)
	public void processModelerTest(Hashtable<String, String> data) {
		test = extent.startTest("Process Modeler Test", "Process Modeler test starting");
		try {
			homePg = new HomePage();
			processModelerPg = homePg.lauchProcessModeler();
			test.log(LogStatus.INFO, "Launched Process modeler");
			Util.delay(5000);
			homePg = processModelerPg.closeProcessModeler();
			test.log(LogStatus.INFO, "Closed Process modeler");
			homePg.switchToFrame();
			homePg.gotoHome();
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "getData", enabled = true)
	public void dbDesignerTest(Hashtable<String, String> data) {
		test = extent.startTest("Db Designer Test", "Db designer test starting");
		try {
			homePg = new HomePage();
			dbDesignerPg = homePg.lauchDbDesigner();
			test.log(LogStatus.INFO, "Launched Db designer");
			Util.delay(5000);
			homePg = dbDesignerPg.closeDbDesigner();
			test.log(LogStatus.INFO, "Closed Db designer");
			homePg.switchToFrame();
			homePg.gotoHome();
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "getData", enabled = true)
	public void driveTest(Hashtable<String, String> data) {
		test = extent.startTest("Driver Test", "Driver test starting");
		try {
			homePg = new HomePage();
			drivePg = homePg.lauchDrive();
			test.log(LogStatus.INFO, "Launched Driver");
			Util.delay(5000);
			homePg = drivePg.closeDrive();
			test.log(LogStatus.INFO, "Closed Driver");
			homePg.switchToFrame();
			homePg.gotoHome();
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "getData", enabled = true)
	public void configuratorTest(Hashtable<String, String> data) {
		// Declare variable
		test = extent.startTest("Configurator Test", "Configurator test starting");
		try {
			homePg = new HomePage();
			
			platformConfigPg = homePg.openConfigurator();
			pfUserPg = platformConfigPg.openPuserTab();
			String img = test.addScreenCapture(snapshotPath+"\\Platform User.png");
			System.out.println(img);
			test.log(LogStatus.INFO, "Platform user page opened" + img);
			//test.log(LogStatus.INFO, "Snapshot below: " + test.addScreenCapture("screenshot-path"));
			homePg = pfUserPg.getHmePage().gotoHome();
			
			platformConfigPg = homePg.openConfigurator();
			connectionMngrPg = platformConfigPg.openConnectionMngrTab();
			test.log(LogStatus.INFO, "Connection manager page opened");
			homePg = connectionMngrPg.getHmePage().gotoHome();
			
			platformConfigPg = homePg.openConfigurator();
			connectivityPg = platformConfigPg.openConnectivityTab();
			test.log(LogStatus.INFO, "Connectivity page opened");
			homePg = connectivityPg.getHmePage().gotoHome();

			platformConfigPg = homePg.openConfigurator();
			logsPg  = platformConfigPg.openLogsTab();
			test.log(LogStatus.INFO, "Logs page opened");
			homePg = logsPg.getHmePage().gotoHome();
	
			platformConfigPg = homePg.openConfigurator();
			mailsPg = platformConfigPg.openMailTab();
			test.log(LogStatus.INFO, "Mail page opened");
			homePg = mailsPg.getHmePage().gotoHome();

			//platformConfigPg.openNotifyTab();
			platformConfigPg = homePg.openConfigurator();
			projTranPage = platformConfigPg.openPrjTransportTab();
			test.log(LogStatus.INFO, "Project and Transport page opened");
			homePg = projTranPage.getHmePage().gotoHome();
			
			platformConfigPg = homePg.openConfigurator();
			platformRolePg = platformConfigPg.openProleTab();
			test.log(LogStatus.INFO, "Platform role page opened");
			homePg = platformRolePg.getHmePage().gotoHome();
			
			platformConfigPg = homePg.openConfigurator();
			schedulerPg = platformConfigPg.openSchedulerTab();
			test.log(LogStatus.INFO, "Scheduler page opened");
			homePg = schedulerPg.getHmePage().gotoHome();
			
			//platformConfigPg.openSyncTab();
			platformConfigPg = homePg.openConfigurator();
			OrganizationPg = platformConfigPg.openOrgTab();
			test.log(LogStatus.INFO, "organization page opened");
			homePg = OrganizationPg.getHmePage().gotoHome();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Util.getscreenshot(e.getMessage());
		}
	}

}
