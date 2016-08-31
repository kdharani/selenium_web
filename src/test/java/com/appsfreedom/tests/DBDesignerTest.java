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
import com.appsfreedom.utils.ReadExcel;
import com.appsfreedom.utils.Util;
import com.relevantcodes.extentreports.LogStatus;

public class DBDesignerTest extends BaseTest {

	Logger log = Logger.getLogger("devpinoyLogger");
	
	@Factory(dataProviderClass=DataProviderClass.class, dataProvider="login")
	public DBDesignerTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "dbDesigner", enabled = true, priority=0)
	public void createTableTest(Hashtable<String, String> data) {
		// Declare variable
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		test = extent.startTest("Create DB Table Test", "Create Db table test starting");
		log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}

		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			homePg = new HomePage();
			dbDesignerPg = homePg.lauchDbDesigner();
			test.log(LogStatus.INFO, "DB Designer launched");
			dbDesignerPg.createTable(data.get("TableName"), data.get("Column1"), data.get("Datatype1"),
					data.get("Length1"), data.get("PK1"), data.get("NN1"), data.get("AI1"), data.get("Column2"),
					data.get("Datatype2"), data.get("Length2"), test);
			test.log(LogStatus.INFO, "Db table created");
			homePg = dbDesignerPg.closeDbDesigner();
			test.log(LogStatus.INFO, "DB Designer closed");
			homePg.switchToFrame();
			test.log(LogStatus.INFO, "Navigated back to home page");
			Util.delay(5000);
			log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			log.error(e.getClass().getSimpleName(), e);
			String path = Util.getscreenshot(methodName+"_"+e.getClass().getSimpleName());
			test.log(LogStatus.FAIL, "Create DB table failed" + test.addScreenCapture(path));
			Assert.fail(e.getClass().getSimpleName(),e);
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "dbDesigner", enabled = true, priority=1)
	public void alterTableTest(Hashtable<String, String> data) {
		// Declare variable
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}

		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			homePg = new HomePage();
			dbDesignerPg = homePg.lauchDbDesigner();
			dbDesignerPg.alterTableTestDbDesigner(data.get("TableName"), data.get("Column3"), data.get("Datatype3"),
					data.get("Length3"), test);
			homePg = dbDesignerPg.closeDbDesigner();
			homePg.switchToFrame();
			Util.delay(5000);
			log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "dbDesigner", enabled = true, priority=2)
	public void executeQueryTest(Hashtable<String, String> data) {
		// Declare variable
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}

		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			dbDesignerPg = homePg.lauchDbDesigner();
			dbDesignerPg.executeQuery(data.get("Query"));
			homePg = dbDesignerPg.closeDbDesigner();
			homePg.switchToFrame();
			Util.delay(5000);
			log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "dbDesigner", enabled = true, priority=4)
	public void deleteTableTest(Hashtable<String, String> data) {
		// Declare variable
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}

		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			dbDesignerPg = homePg.lauchDbDesigner();
			dbDesignerPg.deleteTableTestDbDesigner(data.get("TableName"), test);
			homePg = dbDesignerPg.closeDbDesigner();
			homePg.switchToFrame();
			log.info("***************************Finishing test "+methodName+"*********************************");
			Util.delay(5000);
		} catch (Exception e) {
			log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "dbDesigner", enabled = true, priority=3)
	public void exportTableTest(Hashtable<String, String> data) {
		// Declare variable
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}

		try {
			//loginPg = new LoginPage(driver);
			//homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
			//		data.get("Environment"));
			dbDesignerPg = homePg.lauchDbDesigner();
			Util.delay(5000);
			dbDesignerPg.exportTableTestDbDesigner(data.get("Query"), test);
			Util.delay(5000);
			homePg = dbDesignerPg.closeDbDesigner();
			homePg.switchToFrame();
			Util.delay(5000);
			log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}

}
