package com.appsfreedom.tests;

import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.appsfreedom.fm.pages.HomePage;
import com.appsfreedom.fm.pages.LoginPage;
import com.appsfreedom.fm.pages.PlatformUserPage;
import com.appsfreedom.utils.DataProviderClass;
import com.appsfreedom.utils.MyLogger;
import com.appsfreedom.utils.ReadExcel;
import com.appsfreedom.utils.SQLConnector;
import com.appsfreedom.utils.Util;

public class PlatformUserTest extends BaseTest {
	// Logger log = Logger.getLogger("devpinoyLogger");
	// SoftAssert s_assert = new SoftAssert();

	@Factory(dataProviderClass=DataProviderClass.class, dataProvider = "login")
	public PlatformUserTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "platformUser", enabled = true, groups = "PuserTest", priority = 0)
	public void createPuserTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			String loginid = data.get("loginName"), emailaddress = data.get("email");
			String salutation = data.get("salutation");
			String firstname = data.get("firstname");
			String lastname = data.get("lastname");
			String title = data.get("title");
			String pwd = data.get("ipassword");

			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			SQLConnector.cleanUpUser(loginid);
			pfUserPg = platformConfigPg.openPuserTab();

			if (loginid != null && emailaddress != null && data.get("TenantId") != null) {
				pfUserPg.clickOnCreateUser(loginid, emailaddress, salutation, firstname, lastname, title, pwd,
						data.get("TenantId"));
				boolean created = pfUserPg.isUserCreated(data.get("TenantId"), loginid, emailaddress);
				if (created)
					s_assert.assertTrue(created, "Platform User created Successfully");
				else
					s_assert.assertTrue(created, "Platform User create failed");
			} else {
				s_assert.assertTrue(false, "Platform User create input null");
			}
			homePg = pfUserPg.getHmePage().gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "platformUser", enabled = true, groups = "PuserTest", priority = 1)
	public void copyPuserTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			// loginPg = new LoginPage(driver);
			// homePg = loginPg.login(data.get("URL"), data.get("UserId"),
			// data.get("Password"), data.get("TenantId"),
			// data.get("Environment"));
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			pfUserPg = platformConfigPg.openPuserTab();
			// pfUserPg = new PlatformUserPage(driver);
			String loginid = data.get("loginName"), emailaddress = data.get("email");
			String salutation = data.get("salutation");
			String firstname = data.get("firstname");
			String lastname = data.get("lastname");
			String title = data.get("title");
			String pwd = data.get("ipassword");
			if (loginid != null && emailaddress != null && data.get("TenantId") != null) {
				pfUserPg.clickOnCopyUser(loginid, emailaddress, salutation, firstname, lastname, title, pwd,
						data.get("TenantId"));
				boolean copied = pfUserPg.isCopied(data.get("TenantId"), loginid, emailaddress);
				if (copied)
					s_assert.assertTrue(copied, "Platform User copied Successfully");
				else
					s_assert.assertTrue(copied, "Platform User copy failed");
			} else {
				s_assert.assertTrue(false, "Platform User copy input null");
			}
			homePg = pfUserPg.getHmePage().gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "platformUser", enabled = true, groups = "PuserTest", priority = 2)
	public void lockPuserTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			// loginPg = new LoginPage(driver);
			// homePg = loginPg.login(data.get("URL"), data.get("UserId"),
			// data.get("Password"), data.get("TenantId"),
			// data.get("Environment"));
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			platformConfigPg.openPuserTab();
			pfUserPg = new PlatformUserPage();
			String loginid = data.get("loginName"), emailaddress = data.get("email");
			String firstname = data.get("firstname");
			String lastname = data.get("lastname");

			if (loginid != null && emailaddress != null && data.get("TenantId") != null) {
				pfUserPg.clickOnLockUser(loginid, emailaddress, firstname, lastname, data.get("TenantId"));
				boolean done = pfUserPg.isLocked(data.get("TenantId"), loginid, emailaddress);
				if (done)
					s_assert.assertTrue(done, "Platform User locked Successfully");
				else
					s_assert.assertTrue(done, "Platform User lock failed");
			} else {
				s_assert.assertTrue(false, "Platform User lock input null");
			}
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
			homePg = pfUserPg.getHmePage().gotoHome();
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "platformUser", enabled = true, groups = "PuserTest", priority = 3)
	public void unlockPuserTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			// loginPg = new LoginPage(driver);
			// homePg = loginPg.login(data.get("URL"), data.get("UserId"),
			// data.get("Password"), data.get("TenantId"),
			// data.get("Environment"));
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			platformConfigPg.openPuserTab();
			pfUserPg = new PlatformUserPage();
			String loginid = data.get("loginName"), emailaddress = data.get("email");
			String firstname = data.get("firstname");
			String lastname = data.get("lastname");

			if (loginid != null && emailaddress != null && data.get("TenantId") != null) {
				pfUserPg.clickOnUnlockUser(loginid, emailaddress, firstname, lastname, data.get("TenantId"));
				boolean done = pfUserPg.isUnlocked(data.get("TenantId"), loginid, emailaddress);
				if (done)
					s_assert.assertTrue(done, "Platform User unlocked Successfully");
				else
					s_assert.assertTrue(done, "Platform User unlock failed");
			} else {
				s_assert.assertTrue(false, "Platform User unlock input null");
			}
			homePg = pfUserPg.getHmePage().gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "platformUser", enabled = true, groups = "PuserTest", priority = 4)
	public void changePuserPwdTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			// loginPg = new LoginPage(driver);
			// homePg = loginPg.login(data.get("URL"), data.get("UserId"),
			// data.get("Password"), data.get("TenantId"),
			// data.get("Environment"));
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			platformConfigPg.openPuserTab();
			pfUserPg = new PlatformUserPage();
			String loginid = data.get("loginName"), emailaddress = data.get("email");
			String firstname = data.get("firstname");
			String lastname = data.get("lastname");
			String pwd = data.get("ipassword");

			if (loginid != null && emailaddress != null && data.get("TenantId") != null) {
				pfUserPg.clickOnChangePwd(loginid, emailaddress, firstname, lastname, pwd, data.get("TenantId"));
				boolean ischanged = pfUserPg.ispwdchanged(data.get("TenantId"), loginid, emailaddress, pwd);
				if (ischanged)
					s_assert.assertTrue(ischanged, "Platform User password changed Successfully");
				else
					s_assert.assertTrue(ischanged, "Platform User password change failed");
			} else {
				s_assert.assertTrue(false, "Platform User  password change input null");
			}
			homePg = pfUserPg.getHmePage().gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "platformUser", enabled = true, groups = "PuserTest", priority = 5)
	public void editPuserGeneralInfoTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			// loginPg = new LoginPage(driver);
			// homePg = loginPg.login(data.get("URL"), data.get("UserId"),
			// data.get("Password"), data.get("TenantId"),
			// data.get("Environment"));
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			platformConfigPg.openPuserTab();
			pfUserPg = new PlatformUserPage();
			String loginid = data.get("loginName"), emailaddress = data.get("email");
			String salutation = data.get("salutation");
			String firstname = data.get("firstname");
			String lastname = data.get("lastname");
			String title = data.get("title");
			String pwd = data.get("ipassword");
			if (loginid != null && emailaddress != null && data.get("TenantId") != null) {
				pfUserPg.editGeneralInfo(loginid, emailaddress, salutation, firstname, lastname, title, pwd,
						data.get("TenantId"));
				boolean iseditied = pfUserPg.isGenInfoEdited(data.get("TenantId"), loginid, emailaddress);
				if (iseditied)
					s_assert.assertTrue(iseditied, "Platform User general info edited Successfully");
				else
					s_assert.assertTrue(iseditied, "Platform User  general info edit failed");
			} else {
				s_assert.assertTrue(false, "Platform User  general info input null");
			}
			homePg = pfUserPg.getHmePage().gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "platformUser", enabled = true, groups = "PuserTest", priority = 6)
	public void assignRoleToPuserTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			// loginPg = new LoginPage(driver);
			// homePg = loginPg.login(data.get("URL"), data.get("UserId"),
			// data.get("Password"), data.get("TenantId"),
			// data.get("Environment"));
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			platformConfigPg.openPuserTab();
			pfUserPg = new PlatformUserPage();
			String loginid = data.get("loginName"), emailaddress = data.get("email"),
					assignedrole = data.get("assignedrole");
			if (assignedrole != null) {
				pfUserPg.assignRoles(loginid, emailaddress, assignedrole, data.get("TenantId"));
				boolean isrolesassigned = pfUserPg.isRolesAssigned(assignedrole);
				if (isrolesassigned)
					s_assert.assertTrue(isrolesassigned, "Platform User role assigned Successfully");
				else
					s_assert.assertTrue(isrolesassigned, "Platform User role assign failed");
			} else {
				s_assert.assertTrue(false, "Platform User role input null");
			}
			homePg = pfUserPg.getHmePage().gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "platformUser", enabled = true, groups = "PuserTest", priority = 7)
	public void removeRoleFrmPuserTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			// loginPg = new LoginPage(driver);
			// homePg = loginPg.login(data.get("URL"), data.get("UserId"),
			// data.get("Password"), data.get("TenantId"),
			// data.get("Environment"));
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			platformConfigPg.openPuserTab();
			pfUserPg = new PlatformUserPage();
			String loginid = data.get("loginName"), emailaddress = data.get("email");
			String removerole = data.get("removerole");
			if (removerole != null) {
				pfUserPg.deleteRoles(loginid, emailaddress, removerole, data.get("TenantId"));

				boolean isRoleDeleted = pfUserPg.isRoleDeleted(removerole);
				if (isRoleDeleted)
					s_assert.assertTrue(isRoleDeleted, "Platform User role deleted Successfully");
				else
					s_assert.assertTrue(isRoleDeleted, "Platform User role delete failed");
			} else {
				s_assert.assertTrue(false, "Platform User role input null");
			}
			homePg = pfUserPg.getHmePage().gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "platformUser", enabled = true, groups = "PuserTest", priority = 8)
	public void addAppsToPuserTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			// loginPg = new LoginPage(driver);
			// homePg = loginPg.login(data.get("URL"), data.get("UserId"),
			// data.get("Password"), data.get("TenantId"),
			// data.get("Environment"));
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			platformConfigPg.openPuserTab();
			pfUserPg = new PlatformUserPage();
			String loginid = data.get("loginName"), emailaddress = data.get("email");
			String addapp = data.get("addapp");
			if (addapp != null) {
				pfUserPg.subscribedAppsAdd("admin", emailaddress, addapp, data.get("TenantId"));
				String appName[] = addapp.split("-");
				boolean isAdded = pfUserPg.isAppAdded(appName[0]);
				if (isAdded)
					s_assert.assertTrue(isAdded, "Platform User Subscribed App Added Successfully");
				else
					s_assert.assertTrue(isAdded, "Platform User Subscribed App Add failed");
			} else {
				s_assert.assertTrue(false, "Platform User Subscribed App input null");
			}
			homePg = pfUserPg.getHmePage();
			homePg.gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "platformUser", enabled = true, groups = "PuserTest", priority = 9)
	public void removeAppsFrmPuserTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			// loginPg = new LoginPage(driver);
			// homePg = loginPg.login(data.get("URL"), data.get("UserId"),
			// data.get("Password"), data.get("TenantId"),
			// data.get("Environment"));
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			platformConfigPg.openPuserTab();
			pfUserPg = new PlatformUserPage();
			String loginid = data.get("loginName"), emailaddress = data.get("email");
			String removeapp = data.get("removeapp");
			if (removeapp != null) {
				pfUserPg.subscribedAppsRemove("admin", emailaddress, removeapp, data.get("TenantId"));
				boolean isremoved = pfUserPg.isAppRemoved(removeapp);
				if (isremoved)
					s_assert.assertTrue(isremoved, "Platform User Subscribed App removed Successfully");
				else
					s_assert.assertTrue(isremoved, "Platform User Subscribed App remove failed");
			} else {
				s_assert.assertTrue(false, "Platform User Subscribed App input null");
			}
			homePg = pfUserPg.getHmePage().gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "platformUser", enabled = true, groups = "PuserTest", priority = 10)
	public void addUserMappingToPuserTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			// loginPg = new LoginPage(driver);
			// homePg = loginPg.login(data.get("URL"), data.get("UserId"),
			// data.get("Password"), data.get("TenantId"),
			// data.get("Environment"));
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			platformConfigPg.openPuserTab();
			pfUserPg = new PlatformUserPage();
			String loginid = data.get("loginName"), emailaddress = data.get("email");
			String usermappingrfc = data.get("usermappingrfc"), usermappinguserid = data.get("usermappinguserid");
			if (usermappingrfc != null) {
				pfUserPg.userMappingAdd(loginid, emailaddress, usermappingrfc, usermappinguserid, data.get("TenantId"));
				boolean isUserMappingDone = pfUserPg.isUserMappingDone(usermappingrfc);
				if (isUserMappingDone)
					s_assert.assertTrue(isUserMappingDone, "Platform user mapping done Successfully");
				else
					s_assert.assertTrue(isUserMappingDone, "Platform user mapping failed");
			} else {
				s_assert.assertTrue(false, "Platform user mapping input null");
			}
			homePg = pfUserPg.getHmePage().gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "platformUser", enabled = true, groups = "PuserTest", priority = 11)
	public void removeUserMappingFrmPuserTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			// MyLogger.loginPg = new LoginPage(driver);
			// homePg = loginPg.login(data.get("URL"), data.get("UserId"),
			// data.get("Password"), data.get("TenantId"),
			// data.get("Environment"));
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			platformConfigPg.openPuserTab();
			pfUserPg = new PlatformUserPage();
			String loginid = data.get("loginName"), emailaddress = data.get("email");
			String usermappingrfcremove = data.get("usermappingrfcremove");
			if (usermappingrfcremove != null) {
				pfUserPg.userMappingRemove(loginid, emailaddress, usermappingrfcremove, data.get("TenantId"));
				boolean isremoved = pfUserPg.isUsermappingRemoved(usermappingrfcremove);
				if (isremoved)
					s_assert.assertTrue(isremoved, "Platform user mapping removed Successfully");
				else
					s_assert.assertTrue(isremoved, "Platform user mapping remove failed");
			} else {
				s_assert.assertTrue(false, "Platform User Subscribed App input null");
			}
			homePg = pfUserPg.getHmePage().gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		}
	}

}
