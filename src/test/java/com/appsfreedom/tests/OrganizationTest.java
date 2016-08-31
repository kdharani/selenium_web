package com.appsfreedom.tests;

import java.util.Hashtable;

import org.apache.log4j.Logger;
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
import com.appsfreedom.fm.pages.PlatformConfigPage;
import com.appsfreedom.utils.DataProviderClass;
import com.appsfreedom.utils.MyLogger;
import com.appsfreedom.utils.ReadExcel;
import com.appsfreedom.utils.SQLConnector;
import com.appsfreedom.utils.Util;

public class OrganizationTest extends BaseTest {

	@Factory(dataProviderClass=DataProviderClass.class, dataProvider="login")
	public OrganizationTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "editOrgInfo", enabled = true, groups="OrgTest")
	public void updateOrgInfoTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
		String dateFormat = data.get("DateFormat");
		String result = "";
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		MyLogger.log.info("***************************Starting test "+methodName+"*********************************");

		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			OrganizationPg = platformConfigPg.openOrgTab();
			OrganizationPg.openCompanyForm();
			OrganizationPg.selectOrgDateFormat(dateFormat);
			OrganizationPg.updateOrgInfo();
			platformConfigPg.openOrgTab();
			s_assert.assertEquals(dateFormat, OrganizationPg.getOrgDateFormat(), "Dateformat not matched");
			homePg= OrganizationPg.getHmePage().gotoHome();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		} finally {
			s_assert.assertAll();
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "editOrgAddress", enabled = true, groups="OrgTest")
	public void updateOrgAddressTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
		String street = data.get("Street");
		String state = data.get("State");
		String country = data.get("Country");
		String city = data.get("City");
		String zip = data.get("Zip");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			OrganizationPg = platformConfigPg.openOrgTab();
			OrganizationPg.openOrgAddressTab();
			OrganizationPg.openOrgAddUpdateForm();
			OrganizationPg.setOrgAddStreet(street);
			OrganizationPg.setOrgAddState(state);
			OrganizationPg.setOrgAddCountry(country);
			OrganizationPg.setOrgAddCity(city);
			// platformConfigPg.setOrgAddZip(zip);
			OrganizationPg.submitOrgAddUpdateForm();
			platformConfigPg.openOrgTab();

			s_assert.assertEquals(street, OrganizationPg.displayStreet.getText(), "Street not matched");
			s_assert.assertEquals(state, OrganizationPg.displayState.getText(), "State not matched");
			s_assert.assertEquals(country, OrganizationPg.displayCountry.getText(), "Country not matched");
			s_assert.assertEquals(city, OrganizationPg.displayCity.getText(), "City not matched");
			homePg= OrganizationPg.getHmePage().gotoHome();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		} finally {
			s_assert.assertAll();
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "addOrgContact", enabled = true, groups="OrgTest")
	public void addOrgContactTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
		String salutation = data.get("Salutation");
		String fname = data.get("Fname");
		String lname = data.get("Lname");
		String email = data.get("Email");
		String dept = data.get("Department");
		String title = data.get("Title");
		String workPh = data.get("WorkPh");
		String mobilePh = data.get("MobilePh");
		String street = data.get("Street");
		String state = data.get("State");
		String country = data.get("Country");
		String city = data.get("City");
		String zip = data.get("Zip");
		int count = 0;
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			OrganizationPg = platformConfigPg.openOrgTab();
			OrganizationPg.openOrgContactTab();
			count = OrganizationPg.returnOrgContactCount();
			OrganizationPg.openOrgAddContactForm();
			OrganizationPg.selectOrgContactSalutation(salutation);
			OrganizationPg.setOrgContactFname(fname);
			OrganizationPg.setOrgContactLname(lname);
			OrganizationPg.setOrgContactEmail(email);
			OrganizationPg.setOrgContactTitle(title);
			OrganizationPg.setOrgContactDept(dept);
			OrganizationPg.setOrgContactWorkPh(workPh);
			OrganizationPg.setOrgContactMobilePh(mobilePh);
			// OrganizationPg.setOrgContactPrimary();
			OrganizationPg.setOrgContactStreet(street);
			OrganizationPg.setOrgContactState(state);
			OrganizationPg.setOrgContactCountry(country);
			OrganizationPg.setOrgContactCity(city);
			OrganizationPg.setOrgContactZip(zip);
			OrganizationPg.saveOrgContact();
			platformConfigPg.openOrgTab();
			OrganizationPg.openOrgContactTab();

			s_assert.assertTrue(OrganizationPg.returnOrgContactCount() > count, "Contact creation failed");
			s_assert.assertTrue(OrganizationPg.checkContactExist(fname), "Contact firstname not found");
			homePg= OrganizationPg.getHmePage().gotoHome();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		} finally {
			s_assert.assertAll();
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "editOrgContact", enabled = true, groups="OrgTest")
	public void editOrgContactTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
		String oldName = data.get("OldName");
		String newName = data.get("NewName");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			OrganizationPg = platformConfigPg.openOrgTab();
			OrganizationPg.openOrgContactTab();
			OrganizationPg.openOrgEditContactForm(oldName);
			OrganizationPg.setOrgContactFname(newName);
			OrganizationPg.saveOrgContact();
			platformConfigPg.openOrgTab();
			OrganizationPg.openOrgContactTab();

			s_assert.assertTrue(OrganizationPg.checkContactExist(newName), "Contact firstname not edited");
			homePg= OrganizationPg.getHmePage().gotoHome();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		} finally {
			s_assert.assertAll();
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "changeOrgPrimaryContact", enabled = true, groups="OrgTest")
	public void changePrimaryContactTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
		String fname = data.get("Fname");
		String fname1 = data.get("Fname1");
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			OrganizationPg = platformConfigPg.openOrgTab();
			OrganizationPg.openOrgContactTab();
			OrganizationPg.openOrgEditContactForm(fname);
			OrganizationPg.setOrgContactPrimary();
			OrganizationPg.saveOrgContact();
			platformConfigPg.openOrgTab();
			OrganizationPg.openOrgContactTab();
			s_assert.assertTrue(OrganizationPg.checkIfContactIsPrimary(fname), "Contact not changed to primary");
			OrganizationPg.openOrgEditContactForm(fname1);
			OrganizationPg.setOrgContactPrimary();
			OrganizationPg.saveOrgContact();
			homePg= OrganizationPg.getHmePage().gotoHome();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		} finally {
			s_assert.assertAll();
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "deleteOrgContact", enabled = true, groups="OrgTest")
	public void deleteOrgContactTest(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
		String contactName = data.get("ContactName");
		int count = 0;
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			OrganizationPg = platformConfigPg.openOrgTab();
			OrganizationPg.openOrgContactTab();
			count = OrganizationPg.returnOrgContactCount();
			OrganizationPg.deleteOrgContact(contactName);
			platformConfigPg.openOrgTab();
			OrganizationPg.openOrgContactTab();

			s_assert.assertTrue(OrganizationPg.returnOrgContactCount() < count, "Contact delete failed");
			s_assert.assertFalse(OrganizationPg.checkContactExist(contactName), "Contact still exist");
			homePg= OrganizationPg.getHmePage().gotoHome();
			MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		} finally {
			s_assert.assertAll();
		}
	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "subscriptionTest", enabled = true, groups="OrgTest")
	public void checkTenantRestrictions(Hashtable<String, String> data) {
		// Declare variable
		 SoftAssert s_assert = new SoftAssert();
		Hashtable<String, Float> restrictions;
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			OrganizationPg = platformConfigPg.openOrgTab();
			OrganizationPg.openOrgSubscriptionTab();
			restrictions = SQLConnector.getCompanyRestrictions(data.get("TenantId"));

			s_assert.assertEquals((restrictions.get("Tenants")), OrganizationPg.getTenantVal(),
					"Tenant count not matched");
			s_assert.assertEquals((restrictions.get("Storage")), OrganizationPg.getStorageVal(), "Storage not matched");
			s_assert.assertEquals((restrictions.get("DataTransfer")), OrganizationPg.getDataTransferVal(),
					"DataTransfer not matched");
			s_assert.assertEquals((restrictions.get("Actions")), OrganizationPg.getActionsVal(),
					"Actions count not matched");
			s_assert.assertEquals((restrictions.get("BOSCreated")), OrganizationPg.getBosCreatedVal(),
					"BOSCreated count not matched");
			s_assert.assertEquals((restrictions.get("BOSCalled")), OrganizationPg.getBosCalledVal(),
					"BOSCalled count not matched");
			s_assert.assertEquals((restrictions.get("AppsAllowed")), OrganizationPg.getAppsVal(),
					"AppsAllowed count not matched");
			s_assert.assertEquals((restrictions.get("DBTablesCreated")), OrganizationPg.getTableCreatedVal(),
					"DBTablesCreated count not matched");
			s_assert.assertEquals((restrictions.get("PlatformUsersCreated")), OrganizationPg.getPuserCreatedVal(),
					"PlatformUsersCreated count not matched");
			s_assert.assertEquals((restrictions.get("BusinessUsersCreated")), OrganizationPg.getBuserCreatedVal(),
					"BusinessUsersCreated count not matched");
			s_assert.assertEquals((restrictions.get("DriveUploadFileLimit")), OrganizationPg.getMaxFileSizeVal(),
					"DriveUploadFileLimit count not matched");
			s_assert.assertEquals((restrictions.get("DriveStorageLimit")), OrganizationPg.getMaxStorageSizeVal(),
					"DriveStorageLimit count not matched");
			s_assert.assertEquals((restrictions.get("AllowToAddBusinessUsers")), OrganizationPg.getAllowMoreBuserVal(),
					"AllowToAddBusinessUsers count not matched");
			homePg= OrganizationPg.getHmePage().gotoHome();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().getSimpleName());
			Assert.fail(e.getLocalizedMessage());
		} finally {
			s_assert.assertAll();
		}

	}


}
