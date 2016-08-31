package com.appsfreedom.tests;

import java.util.Hashtable;

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

public class PlatformRoleTest extends BaseTest {
	
	//Logger log = Logger.getLogger("devpinoyLogger");
	//SoftAssert s_assert = new SoftAssert();
	
	@Factory(dataProviderClass=DataProviderClass.class, dataProvider="login")
	public PlatformRoleTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "createPlatformRole", enabled = true)
	public void createPlatformRoleTest(Hashtable<String, String> data) {
		// Declare variable
		SoftAssert s_assert = new SoftAssert();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			/*MyLogger.loginPg = new LoginPage(driver);
			homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
					data.get("Environment"));*/
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			Util.delay(5000);
			SQLConnector.cleanUpRole(tenantId, data.get("UpdatedRoleName"));
			platformRolePg=platformConfigPg.openProleTab();
			platformRolePg.clickRoleCreateBtn();
			platformRolePg.enterRoleName(data.get("RoleName"));
			platformRolePg.enterRoleDesc(data.get("RoleDesc"));
			platformRolePg.clickSaveNewRole();
			s_assert.assertTrue(platformRolePg.checkPlatFormRoleAdded(data.get("RoleName")),"Role Not Created!!");
			homePg.gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "editPlatformRole", enabled = true)
	public void editPlatformRoleTest(Hashtable<String, String> data) {
		// Declare variable
		SoftAssert s_assert = new SoftAssert();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			/*loginPg = new LoginPage(driver);
			homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
					data.get("Environment"));*/
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			Util.delay(3000);
			platformRolePg=platformConfigPg.openProleTab();
			Util.delay(5000);
			platformRolePg.editPRole(data.get("RoleName"));
			platformRolePg.clickRoleEditBtn();
			platformRolePg.enterEditRoleName(data.get("NewRoleName"));
			platformRolePg.enteEditRoleDesc(data.get("NewRoleDesc"));
			platformRolePg.clickSaveEditRole();
			Util.delay(3000);
			s_assert.assertTrue(platformRolePg.checkPlatFormRoleUpdate(data.get("NewRoleName")),"Role Not Updated!!");
			homePg.gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");

		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "pRAssignedUsers", enabled = true)
	public void checkAssignedUsersToRoleTest(Hashtable<String, String> data) {
		// Declare variable
		SoftAssert s_assert = new SoftAssert();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			platformRolePg=platformConfigPg.openProleTab();
			platformRolePg.checkAssignedRoles(data.get("RoleName"));
			s_assert.assertTrue(platformRolePg.checkAssignedUsers(data.get("AssignedUsers")),"Role Not Updated!!");
			homePg.gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "pRAssignedGroups", enabled = true)
	public void platformRoleAssignedGroupsTest(Hashtable<String, String> data) {
		// Declare variable
		SoftAssert s_assert = new SoftAssert();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			/*loginPg = new LoginPage(driver);
			homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
					data.get("Environment"));*/
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			Util.delay(2000);
			platformRolePg=platformConfigPg.openProleTab();
			Util.delay(1500);
			platformRolePg.editPRoleAssginedGroups(data.get("RoleName"));
			Util.delay(1500);
			platformRolePg.clickAssignedGroupsBtn();
			Util.delay(500);
			platformRolePg.addNewAssignGroup(data.get("AddAssignGroup"));
			Util.delay(1000);
			platformRolePg.saveNewAssignGroup();
			Util.delay(3000);
			s_assert.assertTrue(platformRolePg.checkAssignedGroupsAdded(data.get("AddAssignGroup")),"Role Not Updated!!");
			platformRolePg.deleteAddedAssignGroup(data.get("DeleteAssignGroup"));
			//platformRolePg.deleteSelectedAssignGroup();
			s_assert.assertTrue(platformRolePg.checkAssignedGroupsDelete(data.get("DeleteAssignGroup")),"Role Not Deelted!!");
			homePg.gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "pRAssignedAccess", enabled = true)
	public void platformRoleAssignedAccessTest(Hashtable<String, String> data) {
		// Declare variable
		SoftAssert s_assert = new SoftAssert();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			/*loginPg = new LoginPage(driver);
			homePg = loginPg.login(data.get("URL"), data.get("UserId"), data.get("Password"), data.get("TenantId"),
					data.get("Environment"));*/
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			Util.delay(2000);
			platformRolePg=platformConfigPg.openProleTab();
			Util.delay(1500);
			platformRolePg.editPRoleAssginedAccess(data.get("RoleName"));
			Util.delay(1500);
			platformRolePg.clickAssignedAccessUpdateBtn();
			Util.delay(500);
			platformRolePg.addNewAssignedAccess(data.get("AddAssignedAccess"),data.get("AddAssignedAccessId"));
			//Util.delay(1000);
			platformRolePg.saveNewAssignedAccess();
			Util.delay(3000);
			s_assert.assertTrue(platformRolePg.checkAssignedAccessUpdated(data.get("AddAssignedAccess")),"Role Not Updated!!");
			homePg.gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}

}
