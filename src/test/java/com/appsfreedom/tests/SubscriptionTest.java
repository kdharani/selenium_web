package com.appsfreedom.tests;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Factory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.appsfreedom.fm.pages.HomePage;
import com.appsfreedom.fm.pages.Menu;
import com.appsfreedom.fm.pages.SubscriptionPage;
import com.appsfreedom.utils.DataProviderClass;
import com.appsfreedom.utils.MyLogger;
import com.appsfreedom.utils.Retry;
import com.appsfreedom.utils.Util;
import com.relevantcodes.extentreports.LogStatus;

import bsh.BshClassManager.Listener;

@Listeners({com.appsfreedom.utils.Listener.class})
public class SubscriptionTest extends BaseTest {

	@Factory(dataProviderClass = DataProviderClass.class, dataProvider = "subscription" )
	public SubscriptionTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}

	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "subscription", enabled = true, priority = 0)
	public void createPackageTest(Hashtable<String, String> data) throws Throwable {
		// Declare variable
		boolean result = true;
		SoftAssert softAssert = new SoftAssert();

		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}

		//homePg = new HomePage();
		//platformConfigPg = homePg.openConfigurator();
		//subscriptionPg = platformConfigPg.openSubscriptionTab();
		Menu.PlatformConfig.Subscription.getTo();
		test.log(LogStatus.INFO, "Subscription tab opened");

		test.log(LogStatus.INFO, "Checking if package already exists");
		if (SubscriptionPage.isPackageExists(data.get("PackageName"))) {
			test.log(LogStatus.WARNING, "Package already exists");
			String path = Util.getscreenshot("Package already exists");
			test.log(LogStatus.WARNING, SubscriptionPage.getPopupMsg() + test.addScreenCapture(path));
		} else {
			
			test.log(LogStatus.INFO, "Opening package detail form");
			SubscriptionPage.openSubscriptionForm();
			test.log(LogStatus.INFO, "Filling package details");
			SubscriptionPage.fillPackageDetail(data);
			
			
			test.log(LogStatus.INFO, "Checking if popup alert is exists");
			if (SubscriptionPage.isPopupOpen()) {
				String path = Util.getscreenshot(SubscriptionPage.getPopupMsg());
				test.log(LogStatus.WARNING, SubscriptionPage.getPopupMsg() + test.addScreenCapture(path));
				SubscriptionPage.closePopup();
				SubscriptionPage.closePackageDetailForm();
				test.log(LogStatus.WARNING, "Package not created");
				result = false;
				softAssert.assertTrue(result, "Package might already exists");
			} else if (SubscriptionPage.isPackageFormOpen()) {
				String path = Util.getscreenshot("Package_From");
				test.log(LogStatus.WARNING, "Error in package detail form" + test.addScreenCapture(path));
				SubscriptionPage.closePackageDetailForm();
				test.log(LogStatus.WARNING, "Package not created");
				result = false;
				softAssert.assertTrue(result, "Missing / Wrong input data");

			}
			if (result) {
				softAssert.assertTrue(SubscriptionPage.isPackageExists(data.get("PackageName")));
				//platformConfigPg.openSubscriptionTab();
				test.log(LogStatus.INFO, "New package " + data.get("PackageName") + " created");
			}
		}
		softAssert.assertAll();

	}

	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "subscription", priority = 2)
	public void deleteSubscriptionTest(Hashtable<String, String> data) throws Throwable {
		boolean result = false;
		SoftAssert softAssert = new SoftAssert();

		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is NO");
		}

		homePg = new HomePage();
		platformConfigPg = homePg.openConfigurator();
		subscriptionPg = platformConfigPg.openSubscriptionTab();

		test.log(LogStatus.INFO, "Navigated to subscription tab");
		test.log(LogStatus.INFO, "Searching package");

		if (subscriptionPg.isPackageExists(data.get("PackageName"))) {
			test.log(LogStatus.INFO, "Package found");
			String path = Util.getscreenshot("Searching Package");
			test.log(LogStatus.INFO, "Package search" + test.addScreenCapture(path));
			test.log(LogStatus.INFO, "Deleting package");
			result = subscriptionPg.deletePackage(data.get("PackageName"));
			platformConfigPg.openSubscriptionTab();
			if (!result) {
				test.log(LogStatus.INFO, "Package has been deleted");
				softAssert.assertTrue(!result);
			}

		} else {
			test.log(LogStatus.WARNING, "No such package to delete");
			String path = Util.getscreenshot("Package " + data.get("PackageName") + " not found");
			test.log(LogStatus.FAIL, "" + test.addScreenCapture(path));
		}

		softAssert.assertAll();
	}

	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "subscription", priority = 1)
	public void editSubscriptionTest(Hashtable<String, String> data) throws Throwable {
		boolean result;
		int old_count, new_count;
		SoftAssert softAssert = new SoftAssert();

		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is NO");
		}

		homePg = new HomePage();
		platformConfigPg = homePg.openConfigurator();
		subscriptionPg = platformConfigPg.openSubscriptionTab();
		test.log(LogStatus.INFO, "Checking if package is exists");
		if (subscriptionPg.isPackageExists(data.get("PackageName"))) {
			subscriptionPg.selectPackageToEdit(data.get("PackageName"));
			test.log(LogStatus.INFO, "Package selected for update");
			old_count = subscriptionPg.getAppCount();
			subscriptionPg.changeAppCount(Util.trimZero(data.get("NewAppCount")));
			test.log(LogStatus.INFO, "App count is changed");
			subscriptionPg.savePackage();

			test.log(LogStatus.INFO, "Check if app count is changed");
			platformConfigPg.openSubscriptionTab();
			subscriptionPg.isPackageExists(data.get("PackageName"));
			subscriptionPg.selectPackageToEdit(data.get("PackageName"));

			test.log(LogStatus.INFO, "Get new app count");
			new_count = subscriptionPg.getAppCount();
			//new_count = 1;
			String path = Util.getscreenshot("New app count");
			test.log(LogStatus.INFO, "New app count" + test.addScreenCapture(path));
			subscriptionPg.closePackageDetailForm();

			if (new_count == Integer.parseInt(Util.trimZero(data.get("NewAppCount")))) {
				test.log(LogStatus.PASS, "App count updated successfully");
			} else {
				test.log(LogStatus.FAIL, "App cound not matched");
			}

			softAssert.assertTrue(new_count == Integer.parseInt(Util.trimZero(data.get("NewAppCount"))));

		} else {
			String path = Util.getscreenshot("Package not exists");
			test.log(LogStatus.WARNING, "Package not found" + test.addScreenCapture(path));
		}
		softAssert.assertAll();
	}

	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "subscription")
	public void createDuplicateSubscription(Hashtable<String, String> data) throws Throwable {
		boolean result;
		SoftAssert softAssert = new SoftAssert();

		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is NO");
		}

		homePg = new HomePage();
		platformConfigPg = homePg.openConfigurator();
		subscriptionPg = platformConfigPg.openSubscriptionTab();

		test.log(LogStatus.INFO, "Openening package detail form");
		subscriptionPg.openSubscriptionForm();

		test.log(LogStatus.INFO, "Enter package details");
		subscriptionPg.setPackageName(Util.trimZero(data.get("ExistingPackage")));
		// subscriptionPg.setPackageName("Basic");
		subscriptionPg.selectPackage(Util.trimZero(data.get("Package")));
		subscriptionPg.selectAppDesigner();
		subscriptionPg.selectBuilder();
		subscriptionPg.selectAppWatch();
		subscriptionPg.selectEnvironment(data.get("Env"));
		// System.out.println(Util.trimZero(data.get("TenantCount")));
		subscriptionPg.setTenantCount(Util.trimZero(data.get("TenantCount")));
		subscriptionPg.setStorageLimit(Util.trimZero(data.get("StorageLimit")));
		subscriptionPg.setDataTransfer(Util.trimZero(data.get("DataTransfer")));
		subscriptionPg.setActionCount(Util.trimZero(data.get("ActionCount")));
		subscriptionPg.setBOSCreated(Util.trimZero(data.get("BosCreated")));
		subscriptionPg.setBOSAccessCount(Util.trimZero(data.get("BosAccessCount")));
		subscriptionPg.setAppCount(Util.trimZero(data.get("AppCount")));
		subscriptionPg.setTableCount(Util.trimZero(data.get("TableCount")));
		subscriptionPg.setPuserCount(Util.trimZero(data.get("PuserCount")));
		subscriptionPg.setBuserCount(Util.trimZero(data.get("BuserCount")));
		subscriptionPg.setMaxFileSize(Util.trimZero(data.get("MaxFileSize")));
		subscriptionPg.setMaxStorageSize(Util.trimZero(data.get("MaxStorageSize")));
		subscriptionPg.savePackage();

		test.log(LogStatus.INFO, "Check for error message");
		result = subscriptionPg.isPopupOpen();
		if (result) {
			softAssert.assertTrue(result);
			String path = Util.getscreenshot(subscriptionPg.getPopupMsg());
			test.log(LogStatus.PASS, subscriptionPg.getPopupMsg() + test.addScreenCapture(path));
			subscriptionPg.closePopup();
			subscriptionPg.closePackageDetailForm();
		} else {
			test.log(LogStatus.FAIL, "No error message displayed for duplicate package creation");
		}

		softAssert.assertAll();
	}

	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "subscription")
	public void deleteUsedSubscription(Hashtable<String, String> data) throws Throwable {
		boolean result = false;
		SoftAssert softAssert = new SoftAssert();

		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is NO");
		}

		homePg = new HomePage();
		platformConfigPg = homePg.openConfigurator();
		subscriptionPg = platformConfigPg.openSubscriptionTab();

		test.log(LogStatus.INFO, "Searching package");
		if (subscriptionPg.isPackageExists("Basic")) {
			test.log(LogStatus.INFO, "Package found");
			String path = Util.getscreenshot("Searching Package");
			test.log(LogStatus.INFO, "Package search" + test.addScreenCapture(path));
			test.log(LogStatus.INFO, "Deleting package");
			result = subscriptionPg.deletePackage("Basic");
			platformConfigPg.openSubscriptionTab();
			if (result) {
				test.log(LogStatus.PASS, "Package not deleted");
				softAssert.assertTrue(result);
			}

		} else {
			test.log(LogStatus.WARNING, "No such package to delete");
			String path = Util.getscreenshot("Package " + data.get("PackageName") + " not found");
			test.log(LogStatus.WARNING, "" + test.addScreenCapture(path));
		}
		softAssert.assertAll();

	}

}
