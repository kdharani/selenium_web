package com.appsfreedom.tests;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.appsfreedom.fm.pages.HomePage;
import com.appsfreedom.fm.pages.LoginPage;
import com.appsfreedom.utils.DataProviderClass;
import com.appsfreedom.utils.MyLogger;
import com.appsfreedom.utils.ReadExcel;
import com.appsfreedom.utils.Util;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.Assert;

//@Listeners(com.appsfreedom.utils.Listener.class)
public class TenantAdminTest extends BaseTest {
	// Logger log = Logger.getLogger("devpinoyLogger");
	// SoftAssert s_assert = new SoftAssert();
	@Factory(dataProviderClass = DataProviderClass.class, dataProvider = "editSubscription")
	public TenantAdminTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}

	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "loginProxy", enabled = true)
	public void proxyLoginTest(Hashtable<String, String> data) throws Throwable {
		SoftAssert softAssert = new SoftAssert();
		
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("RunMode is No");
		}

			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			tenantAdminTab = platformConfigPg.openTenantAdminTab();
			tenantAdminTab.selectTenant(data.get("ProxyTenant"));
			String path = Util.getscreenshot("ProxyLogin");
			test.log(LogStatus.INFO, "Proxy Login done" + test.addScreenCapture(path));
			OrganizationPg = platformConfigPg.openOrgTab();
			softAssert.assertEquals(OrganizationPg.getTenantId(), data.get("ProxyTenant"), "Proxy Login Failed");
			homePg = OrganizationPg.getHmePage();
			homePg.proxylogout();
			s_assert.assertAll();

	}

	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "editSubscription", enabled = true)
	public void editSubscriptionTest(Hashtable<String, String> data) throws Throwable {
		String childuserId = data.get("ChildUserId");
		String childpwd = data.get("ChildPassword");
		String childtenantId = data.get("ChildTenant");
		boolean result = false;

		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("RunMode is No");
		}

		homePg = new HomePage();
		test.log(LogStatus.INFO, "AF tenant's Homepage launched");
		platformConfigPg = homePg.openConfigurator();
		test.log(LogStatus.INFO, "AF tenant's Platform Configurator navigated");
		tenantAdminTab = platformConfigPg.openTenantAdminTab();
		test.log(LogStatus.INFO, "AF tenant's Tenant Admin Tab navigated");
		if (tenantAdminTab.editSubscription(data.get("ChildTenant"))) {
			test.log(LogStatus.INFO, "Child tenant's Process Modeler feature updated successfully");
			homePg.logout();
			test.log(LogStatus.INFO, "AF tenant logged out");
			loginPg = new LoginPage();
			loginPg.login(url, childuserId, childpwd, childtenantId, env);
			test.log(LogStatus.INFO, "Child tenant's Homepage launched");
			if (tenantAdminTab.verifyeditSubscription()) {
				test.log(LogStatus.INFO, "Process Modeler feature was enabled to the Child tenant successfully");
			} else {
				test.log(LogStatus.INFO, "Process Modeler feature was disabled to the Child tenant successfully");
			}
			homePg.logout();
			test.log(LogStatus.INFO, "Child tenant logged out");
			loginPg.login(url, userId, pwd, tenantId, env);
			test.log(LogStatus.INFO, "AF tenant logged in");
			platformConfigPg = homePg.openConfigurator();
			test.log(LogStatus.INFO, "AF tenant's Platform Configurator navigated");
			tenantAdminTab = platformConfigPg.openTenantAdminTab();
			test.log(LogStatus.INFO, "AF tenant's Tenant Admin Tab navigated");
			result = tenantAdminTab.editSubscription(data.get("ChildTenant"));
			if (result) {
				test.log(LogStatus.INFO, "Child tenant's Process Modeler feature reset successfully");
			}

		} else {
			test.log(LogStatus.INFO, "Child tenant's Process Modeler feature was not updated");
			s_assert.assertTrue(result, "Child tenant's Process Modeler feature was not updated");

		}

	}

	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "editLicenceULU", enabled = true)
	public void editLicenceTestULU(Hashtable<String, String> data) throws Throwable {
		String childuserId = data.get("ChildUserId");
		String childpwd = data.get("ChildPassword");
		String childtenantId = data.get("ChildTenant");
		String olddate = data.get("Old Date");
		String newdate = data.get("New Date");
		String devURL = data.get("devURL");
		String qaURL = data.get("qaURL");
		String prdURL = data.get("prdURL");

		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("RunMode is No");
		}

		homePg = new HomePage();
		test.log(LogStatus.INFO, "AF tenant's Homepage launched");
		platformConfigPg = homePg.openConfigurator();
		tenantAdminTab = platformConfigPg.openTenantAdminTab();
		if (tenantAdminTab.checkLicenceValidity(childtenantId)) {
			test.log(LogStatus.INFO, "Child Tenant Licence date was set to future date");
			homePg.logout();
			test.log(LogStatus.INFO, "AF tenant logged out");
			loginPg.login(url, childuserId, childpwd, childtenantId, env);
			test.log(LogStatus.INFO, "Child Tenant Logged in");
			if (homePg.verifyLicenceMessage()) {
				test.log(LogStatus.PASS, "Child Tenant Licence Exprity Message available and verified");
				homePg.logout();
				test.log(LogStatus.INFO, "Child tenant logged out");
				loginPg.login(url, userId, pwd, tenantId, env);
				test.log(LogStatus.INFO, "AF tenant logged in");
			} else {
				test.log(LogStatus.FAIL, "Tenant Licence Expriry Message not available and can not be verified");
			}
		} else {
			test.log(LogStatus.INFO, "Tenant Licence date will be set to old date");
			if (tenantAdminTab.renewLicence(childtenantId, olddate, devURL, qaURL, prdURL)) {
				Util.delay(1000);
				test.log(LogStatus.INFO, "Child Tenant Licence date was set to old date");
				homePg.logout();
				test.log(LogStatus.INFO, "AF tenant logged out");
				loginPg.login(url, childuserId, childpwd, childtenantId, env);
				test.log(LogStatus.INFO, "Child Tenant Logged in");
				if (homePg.verifyLicenceMessage()) {
					test.log(LogStatus.PASS, "Child Tenant Licence Exprity Message available and verified");
					homePg.logout();
					test.log(LogStatus.INFO, "Child tenant logged out");
					loginPg.login(url, userId, pwd, tenantId, env);
					test.log(LogStatus.INFO, "AF tenant logged in");
					platformConfigPg = homePg.openConfigurator();
					test.log(LogStatus.INFO, "AF tenant's Platform Configurator navigated");
					tenantAdminTab = platformConfigPg.openTenantAdminTab();
					test.log(LogStatus.INFO, "AF tenant's Tenant Admin Tab navigated");
					System.out.println("Going to reset date");
					tenantAdminTab.resetLicence(childtenantId, newdate, devURL, qaURL, prdURL);
					test.log(LogStatus.INFO, "Child tenant licence date was reverted back");
				} else {
					test.log(LogStatus.FAIL, "Tenant Licence Expriry Message not available and can not be verified");
				}
			} else {
				test.log(LogStatus.FAIL, "Tenant Licence can not be renewed");
			}
		}
		s_assert.assertAll();
	}

	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "editLicenceLUL", enabled = true)
	public void editLicenceTestLUL(Hashtable<String, String> data) throws Throwable {
		String childuserId = data.get("ChildUserId");
		String childpwd = data.get("ChildPassword");
		String childtenantId = data.get("ChildTenant");
		String olddate = data.get("Old Date");
		String newdate = data.get("New Date");
		String devURL = data.get("devURL");
		String qaURL = data.get("qaURL");
		String prdURL = data.get("prdURL");

		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("RunMode is No");
		}

		homePg.logout();
		test.log(LogStatus.INFO, "AF tenant logged out");
		loginPg.login(url, childuserId, childpwd, childtenantId, env);
		test.log(LogStatus.INFO, "Child Tenant Logged in");
		if (homePg.verifyLicenceMessage()) {
			test.log(LogStatus.PASS, "Child Tenant Licence Exprity Message available and verified");
			homePg.logout();
			test.log(LogStatus.INFO, "Child tenant logged out");
			loginPg.login(url, userId, pwd, tenantId, env);
			test.log(LogStatus.INFO, "AF tenant logged in");
			platformConfigPg = homePg.openConfigurator();
			tenantAdminTab = platformConfigPg.openTenantAdminTab();
			if (tenantAdminTab.checkLicenceValidity(childtenantId)) {
				tenantAdminTab.renewLicence(childtenantId, newdate, devURL, qaURL, prdURL);
				test.log(LogStatus.INFO, "Child tenant licence date was set to future date");
				homePg.logout();
				test.log(LogStatus.INFO, "AF tenant logged out");
				loginPg.login(url, childuserId, childpwd, childtenantId, env);
				test.log(LogStatus.PASS, "Child Tenant Logged in as its licence was renewed");
				homePg.logout();
				test.log(LogStatus.INFO, "Child tenant logged out");
				loginPg.login(url, userId, pwd, tenantId, env);
				test.log(LogStatus.INFO, "AF tenant logged in");
				platformConfigPg = homePg.openConfigurator();
				tenantAdminTab = platformConfigPg.openTenantAdminTab();
				tenantAdminTab.resetLicence(childtenantId, olddate, devURL, qaURL, prdURL);
				test.log(LogStatus.INFO, "Child tenant licence date was reverted back  to old date");
			} else {
				test.log(LogStatus.WARNING, "Tenant Licence modification failed");
			}
		} else {
			test.log(LogStatus.FAIL, "Tenant Licence Expriry Message not available and can not be verified");
		}

	}
}
