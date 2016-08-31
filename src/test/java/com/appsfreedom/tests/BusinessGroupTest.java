package com.appsfreedom.tests;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.appsfreedom.fm.pages.HomePage;
import com.appsfreedom.utils.DataProviderClass;
import com.appsfreedom.utils.ReadExcel;
import com.appsfreedom.utils.Util;

public class BusinessGroupTest extends BaseTest {
	Logger log = Logger.getLogger("devpinoyLogger");
	// SoftAssert s_assert = new SoftAssert();

	@Factory(dataProviderClass=DataProviderClass.class, dataProvider = "login")
	public BusinessGroupTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "appLib", enabled = true, priority = 0)
	public void checkAdminGrpIsNotEditableTest(Hashtable<String, String> data) throws Exception {
		SoftAssert s_assert = new SoftAssert();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			homePg = new HomePage();
			appLibPg = homePg.openAppLib();
			Util.delay(3000);
			businessGrpPg = appLibPg.openBusinessGrpTab();
			businessGrpPg.selectBusinessGroup("Admin");
			boolean adminBGStatus = businessGrpPg.checkEdit();
			if (adminBGStatus)
				log.info("Admin general info is Non-editable!");
			s_assert.assertTrue(adminBGStatus, "Admin general info is Editable");
			homePg = businessGrpPg.getHmePage();
			homePg.gotoHome();
			s_assert.assertAll();
			log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			log.info("Unable to check Business Group general Info");
			throw (e);
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "appLib", enabled = true, priority = 1)
	public void createBGroupTest(Hashtable<String, String> data) throws Exception {
		SoftAssert s_assert = new SoftAssert();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			homePg = new HomePage();
			appLibPg = homePg.openAppLib();
			Util.delay(3000);
			businessGrpPg = appLibPg.openBusinessGrpTab();
			boolean createdStatus = businessGrpPg.createBG(data.get("GroupName"), data.get("GroupDesc"));
			if (createdStatus)
				log.info("Business Group '" + data.get("GroupName") + "' created successfully!");
			s_assert.assertTrue(createdStatus, "Business Group '" + data.get("GroupName") + "' already exists!");
			homePg = businessGrpPg.getHmePage();
			homePg.gotoHome();
			s_assert.assertAll();
			log.info("***************************Finishing test "+methodName+"*********************************");
		}

		catch (Exception e) {
			log.info("Create Group Button not loaded properly");
			throw (e);
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "appLib", enabled = true, priority = 2)
	public void editBGroupInfoTest(Hashtable<String, String> data) throws Exception {
		SoftAssert s_assert = new SoftAssert();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			homePg = new HomePage();
			appLibPg = homePg.openAppLib();
			Util.delay(3000);
			businessGrpPg = appLibPg.openBusinessGrpTab();
			boolean editedStatus = businessGrpPg.editBG(data.get("GroupName"), data.get("EditGroupName"),
					data.get("EditGroupDesc"));
			if (editedStatus)
				log.info("Business Group edited successfully!");
			s_assert.assertTrue(editedStatus, "Unable to edit Business Group General Info/ Business Group '"
					+ data.get("EditGroupName") + "' already exists!");
			homePg = businessGrpPg.getHmePage();
			homePg.gotoHome();
			s_assert.assertAll();
			log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			log.error("Unable to edit Business Group");
			throw (e);
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "appLib", enabled = true, priority = 3)
	public void assignRoleToBGroupTest(Hashtable<String, String> data) throws Exception {
		SoftAssert s_assert = new SoftAssert();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.info("***************************Starting test "+methodName+"*********************************");
		try {
			homePg = new HomePage();
			appLibPg = homePg.openAppLib();
			Util.delay(3000);
			businessGrpPg = appLibPg.openBusinessGrpTab();
			boolean roleStatus = businessGrpPg.assignRoleToBG(data.get("EditGroupName"), data.get("Assign Role"));
			if (roleStatus)
				log.info("Role '" + data.get("Assign Role") + "' assigned for Business Group!");
			s_assert.assertTrue(roleStatus, "Unable to assign Business Role");
			homePg = businessGrpPg.getHmePage();
			homePg.gotoHome();
			s_assert.assertAll();
			log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			log.error("Unable to find Business Group");
			throw (e);
		}
	}
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "appLib", enabled = true, priority = 4)
	public void removeRoleFrmBGroupTest(Hashtable<String, String> data) throws Exception {
		SoftAssert s_assert = new SoftAssert();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.info("***************************Starting test "+methodName+"*********************************");
		try {
			homePg = new HomePage();
			appLibPg = homePg.openAppLib();
			Util.delay(3000);
			businessGrpPg = appLibPg.openBusinessGrpTab();
			boolean roleStatus = businessGrpPg.removeRoleFrmBG(data.get("EditGroupName"), data.get("Assign Role"));
			if (roleStatus)
				log.info("Role '" + data.get("Assign Role") + "' removed from Business Group!");
			s_assert.assertTrue(roleStatus, "Unable to delete Business Role");
			homePg = businessGrpPg.getHmePage();
			homePg.gotoHome();
			s_assert.assertAll();
			log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			log.error("Unable to find Business Group");
			throw (e);
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "appLib", enabled = true, priority = 5)
	public void assignUserToBGroupTest(Hashtable<String, String> data) throws Exception {
		SoftAssert s_assert = new SoftAssert();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.info("***************************Starting test "+methodName+"*********************************");
		try {
			homePg = new HomePage();
			appLibPg = homePg.openAppLib();
			Util.delay(3000);
			businessGrpPg = appLibPg.openBusinessGrpTab();
			boolean userStatus = businessGrpPg.assignUserToBG(data.get("EditGroupName"), data.get("Assign User"),
					data.get("UserId"));
			if (userStatus)
				log.info("User '" + data.get("Assign User") + "' assigned for Business Group!");
			s_assert.assertTrue(userStatus, "Unable to assign User");
			homePg = businessGrpPg.getHmePage();
			homePg.gotoHome();
			s_assert.assertAll();
			log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			log.error("Unable to add Business User");
			throw (e);
		}
	}
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "appLib", enabled = true, priority = 6)
	public void removeUserFrmBGroupTest(Hashtable<String, String> data) throws Exception {
		SoftAssert s_assert = new SoftAssert();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.info("***************************Starting test "+methodName+"*********************************");
		try {
			homePg = new HomePage();
			appLibPg = homePg.openAppLib();
			Util.delay(3000);
			businessGrpPg = appLibPg.openBusinessGrpTab();
			boolean userStatus = businessGrpPg.removeUserFrmBG(data.get("EditGroupName"), data.get("Assign User"),
					data.get("UserId"));
			if (userStatus)
				log.info("User '" + data.get("Assign User") + "' deleted from Business Group!");
			s_assert.assertTrue(userStatus, "Unable to delete User");
			homePg = businessGrpPg.getHmePage();
			homePg.gotoHome();
			s_assert.assertAll();
			log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			log.error("Unable to add Business User");
			throw (e);
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "appLib", enabled = true, priority = 7)
	public void assignAppToBGroupTest(Hashtable<String, String> data) throws Exception {
		SoftAssert s_assert = new SoftAssert();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.info("***************************Starting test "+methodName+"*********************************");
		try {
			homePg = new HomePage();
			appLibPg = homePg.openAppLib();
			Util.delay(3000);
			businessGrpPg = appLibPg.openBusinessGrpTab();
			boolean appStatus = businessGrpPg.assignAppToBG(data.get("EditGroupName"), data.get("Assign App"));
			if (appStatus)
				log.info("App '" + data.get("Assign App") + "' assigned for Business Group!");
			s_assert.assertTrue(appStatus, "Unable to assign the App '" + data.get("Assign App") + "'");
			homePg = businessGrpPg.getHmePage();
			homePg.gotoHome();
			s_assert.assertAll();
			log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			log.error("Unable to assign App");
			throw (e);
		}
	}
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "appLib", enabled = true, priority = 8)
	public void removeAppFrmBGroupTest(Hashtable<String, String> data) throws Exception {
		SoftAssert s_assert = new SoftAssert();
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.info("***************************Starting test "+methodName+"*********************************");
		try {
			homePg = new HomePage();
			appLibPg = homePg.openAppLib();
			Util.delay(3000);
			businessGrpPg = appLibPg.openBusinessGrpTab();
			boolean appStatus = businessGrpPg.removeAppFrmBG(data.get("EditGroupName"), data.get("Assign App"));
			if (appStatus)
				log.info("App '" + data.get("Assign App") + "' delted from Business Group!");
			s_assert.assertTrue(appStatus, "Unable to delete the App '" + data.get("Assign App") + "'");
			homePg = businessGrpPg.getHmePage();
			homePg.gotoHome();
			s_assert.assertAll();
			log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			log.error("Unable to assign App");
			throw (e);
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "appLib", enabled = true, priority = 9)
	public void deleteBGroupTest(Hashtable<String, String> data) throws Exception {
		SoftAssert s_assert = new SoftAssert();
		String deleted;
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.info("***************************Starting test "+methodName+"*********************************");
		try {
			homePg = new HomePage();
			appLibPg = homePg.openAppLib();
			Util.delay(3000);
			businessGrpPg = appLibPg.openBusinessGrpTab();
			deleted = businessGrpPg.deleteBGroup(data.get("EditGroupName"));
			if ((deleted.equalsIgnoreCase("This Group is assigned with Users or Role"))) {
				log.info("Business Group" + data.get("EditGroupName") + " cannot be deleted");
			} else {
				appLibPg.openBusinessGrpTab();
				boolean delteStataus = businessGrpPg.checkBGExists(data.get("EditGroupName"));
				s_assert.assertFalse(delteStataus, "Unable to delete " + data.get("EditGroupName") + " group");
			}
			homePg = businessGrpPg.getHmePage();
			homePg.gotoHome();
			s_assert.assertAll();
			log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			log.error("Unable to delete group");
			throw (e);
		}
	}

}
