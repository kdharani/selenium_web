package com.appsfreedom.tests;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import com.appsfreedom.fm.pages.AppDesignerPage;
import com.appsfreedom.fm.pages.AppLibPage;
import com.appsfreedom.fm.pages.AppWatchPage;
import com.appsfreedom.fm.pages.BusinessGroupPage;
import com.appsfreedom.fm.pages.ConnectionManagerPage;
import com.appsfreedom.fm.pages.ConnectivityPage;
import com.appsfreedom.fm.pages.DbDesignerPage;
import com.appsfreedom.fm.pages.DrivePage;
import com.appsfreedom.fm.pages.FreedomAppsPage;
import com.appsfreedom.fm.pages.HomePage;
import com.appsfreedom.fm.pages.IntegrationBuilderPage;
import com.appsfreedom.fm.pages.LoginPage;
import com.appsfreedom.fm.pages.LogsPage;
import com.appsfreedom.fm.pages.MailPage;
import com.appsfreedom.fm.pages.OrganizationPage;
import com.appsfreedom.fm.pages.PlatformConfigPage;
import com.appsfreedom.fm.pages.PlatformRolePage;
import com.appsfreedom.fm.pages.PlatformUserPage;
import com.appsfreedom.fm.pages.ProcessModelerPage;
import com.appsfreedom.fm.pages.ProjectTransportPage;
import com.appsfreedom.fm.pages.SchedulerPage;
import com.appsfreedom.fm.pages.SubscriptionPage;
import com.appsfreedom.fm.pages.TenantAdminPage;
import com.appsfreedom.utils.Driver;
import com.appsfreedom.utils.Email;
import com.appsfreedom.utils.Excel;
import com.appsfreedom.utils.ExtentManager;
import com.appsfreedom.utils.MyLogger;
import com.appsfreedom.utils.ReadExcel;
import com.appsfreedom.utils.Util;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest extends Driver {

	public String browser;
	public String url;
	public String userId;
	public String pwd;
	public String tenantId;
	public String env;

	// public WebDriver driver;
	//protected ExtentReports extent;
	//protected ExtentTest test;
	// public Properties prop;
	final String reportPath = System.getProperty("user.dir") + "\\log\\report.html";
	final String snapshotPath = System.getProperty("user.dir") + "\\screenshots";
	public LoginPage loginPg;
	public HomePage homePg;
	public IntegrationBuilderPage builderPg;
	public AppDesignerPage appDesignPg;
	public ProcessModelerPage processModelerPg;
	public DbDesignerPage dbDesignerPg;
	public DrivePage drivePg;
	public PlatformConfigPage platformConfigPg;
	public AppLibPage appLibPg;
	public PlatformRolePage platformRolePg;
	public AppWatchPage appWatchPg;
	public TenantAdminPage tenantAdminTab;
	// public TenantAdminTab tenantAdminTab;
	public OrganizationPage OrganizationPg;
	public ProjectTransportPage projTranPage;
	public PlatformUserPage pfUserPg;
	public LogsPage logsPg;
	public MailPage mailsPg;
	public BusinessGroupPage businessGrpPg;
	public FreedomAppsPage freedomAppsPg;
	ConnectionManagerPage connectionMngrPg;
	public ConnectivityPage connectivityPg;
	public SchedulerPage schedulerPg;
	SubscriptionPage subscriptionPg;

	//Logger log = Logger.getLogger("devpinoyLogger");
	SoftAssert s_assert = new SoftAssert();

	@BeforeSuite()
	public void setup() throws Exception {
		// prop = Initializer.loadPropFile();
		extent = ExtentManager.getReporter(reportPath);
		driver = startDriver(browser);
		System.out.println("driver started");
	}

	@AfterSuite()
	public void teardown() {
		stopDriver();
		MyLogger.log.info("Driver stoped");
		extent.close();
		MyLogger.log.info("Extent report closed");
		Email.sendReport();
	}

	@BeforeTest()
	public void doLogin() {
		MyLogger.log.info("Inside before test");
		try {
			loginPg = new LoginPage();
			homePg = loginPg.login(url, userId, pwd, tenantId, env);

		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
		}
	}

	@AfterTest()
	public void doLogout() {
		MyLogger.log.info("Inside after test");
		try {
			
			homePg.logout();
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
		}
	}
	
	@BeforeMethod
	public void beforeMethod(){
		MyLogger.log.info("Inside before method");

			try {
				if(!homePg.isAtHomePage()){
					MyLogger.log.info("Home page not exists");
					stopDriver();
				driver = startDriver(browser);
				loginPg = new LoginPage();
				homePg = loginPg.login(url, userId, pwd, tenantId, env);
				}else{
					MyLogger.log.info("Home page exists");
				}
			} catch (Exception e) {
				MyLogger.log.error(e.getClass().toString(), e);
			}
		
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		MyLogger.log.info("Inside after method");
		try {
			homePg.gotoHome();
			MyLogger.log.info("Test "+result.getName()+" finished");
			extent.endTest(test);
			extent.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}

