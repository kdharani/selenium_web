package com.appsfreedom.tests;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.appsfreedom.fm.pages.HomePage;
import com.appsfreedom.utils.DataProviderClass;
import com.appsfreedom.utils.MyLogger;
import com.appsfreedom.utils.ReadExcel;
import com.appsfreedom.utils.SQLConnector;
import com.appsfreedom.utils.Util;
import com.relevantcodes.extentreports.LogStatus;

//@Listeners(com.appsfreedom.utils.Listener.class)
public class ConnectivityTest extends BaseTest {
	// Logger log = Logger.getLogger("devpinoyLogger");
	// SoftAssert s_assert = new SoftAssert();
	@Factory(dataProviderClass = DataProviderClass.class, dataProvider = "connectivity")
	public ConnectivityTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");		
	}

	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "fmauthentication", enabled = true)
	public void fmauthenticationTest(Hashtable<String, String> data) throws Throwable {
		String URL = data.get("FMURL");
		String runmode = data.get("RunMode");
		String fmUserId = data.get("UserId");
		String compID = data.get("Company ID");		


		if (!runmode.equalsIgnoreCase("Y")) {
			throw new SkipException("RunMode is No");
		}
		if(SQLConnector.doesUserExists(fmUserId,compID))
		{
			homePg = new HomePage();
			test.log(LogStatus.INFO, "Tenant's Homepage launched");
			platformConfigPg = homePg.openConfigurator();
			test.log(LogStatus.INFO, "Tenant's Configurator link opened");
			connectivityPg = platformConfigPg.openConnectivityTab();
			test.log(LogStatus.INFO, "Connectivity Tab opened");
			if(connectivityPg.FMAuthentication(URL))
			{
				test.log(LogStatus.INFO, "FM Authentication cennection setup done");
				homePg.logout();
				test.log(LogStatus.INFO, "Tenant logged out");
				loginPg.login(url, fmUserId, pwd, tenantId, env);
				test.log(LogStatus.INFO, "Tenant's Homepage launched with FM Authentication");
			}
			else
			{
				test.log(LogStatus.INFO, "FM Authentication cennection setup failed");
			}
		}
		else
		{
			test.log(LogStatus.INFO, "SSO user not created in FM");
		}		

	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "ssoauthentication", enabled = true)
	public void ssoauthenticationTest(Hashtable<String, String> data) throws Throwable {

		String runmode = data.get("RunMode");
		String FMURL = data.get("FMURL");
		String SSOURL = data.get("SSOURL");
		String SSOUserId = data.get("UserId");
		String SSOPassword = data.get("Password");
		String compID = data.get("Company ID");		
		String ssoinvlaidUserId = data.get("InvalidUserId");
		String ssoinvlaidUserPassword = data.get("InvalidPassword");



		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		test = extent.startTest("SSO NW Authentication Test", "SSO NW Authentication test is starting");
		MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!runmode.equalsIgnoreCase("Y")) {
			throw new SkipException("RunMode is No");
		}

		if(SQLConnector.doesUserExists(SSOUserId,compID))
		{
			homePg = new HomePage();
			test.log(LogStatus.INFO, "Tenant's Homepage launched");
			platformConfigPg = homePg.openConfigurator();
			test.log(LogStatus.INFO, "Tenant's Configurator link opened");
			connectivityPg = platformConfigPg.openConnectivityTab();
			test.log(LogStatus.INFO, "Connectivity Tab opened");
			if(connectivityPg.SSOAuthentication(SSOURL))
			{
				test.log(LogStatus.INFO, "SSO Authentication cennection setup done");
				homePg.logout();
				test.log(LogStatus.INFO, "Tenant logged out");
				loginPg.login(url, SSOUserId, SSOPassword, tenantId, env);
				test.log(LogStatus.INFO, "Tenant's Homepage launched with SSO Authentication");
				homePg = new HomePage();
				test.log(LogStatus.INFO, "Tenant's Homepage launched");
				homePg.logout();
				test.log(LogStatus.INFO, "Tenant logged out");
				if(!loginPg.loginvalidation(url, ssoinvlaidUserId, ssoinvlaidUserPassword, tenantId, env))
				{
					test.log(LogStatus.INFO, "Invalid SSO Authentication verified successfully");
					loginPg.login(url, SSOUserId, SSOPassword, tenantId, env);
					test.log(LogStatus.INFO, "Tenant's Homepage launched with SSO Authentication");
					platformConfigPg = homePg.openConfigurator();
					test.log(LogStatus.INFO, "Tenant's Configurator link opened");
					connectivityPg = platformConfigPg.openConnectivityTab();
					test.log(LogStatus.INFO, "Connectivity Tab opened");
					connectivityPg.FMAuthentication(FMURL);
				}
				else
				{
					test.log(LogStatus.INFO, "Invalid SSO Authentication verification was failed");
				}					
			}
			else
			{
				test.log(LogStatus.INFO, "SSO Authentication cennection setup failed");
			}
		}
		else
		{
			test.log(LogStatus.INFO, "SSO user not created in FM");
		}


	}

	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "ldapauthentication", enabled = true)
	public void ldapauthenticationTest(Hashtable<String, String> data) throws Throwable {
		String ldapUserId = data.get("UserId");
		String ldapUserPassword = data.get("Password");
		String ldapinvlaidUserId = data.get("Invalid UserId");
		String ldapinvlaidUserPassword = data.get("Invalid Password");
		String ladpSrvName = data.get("LDAP SERVER NAME");
		String ldapURL = data.get("LDAP URL");
		String ldapBaseDN = data.get("LDAP BASE DN");
		String ldapAuth = data.get("LDAP AUTHENTICATION SEARCH FILTER");
		String ADFSSrvName = data.get("ADFS SERVER NAME");
		String ADFSURL = data.get("ADFS URL");
		String ADFSBaseDN = data.get("ADFS BASE DN");
		String ADFSAuth = data.get("ADFS AUTHENTICATION SEARCH FILTER");
		String fmURL = data.get("FMURL");		
		String compID = data.get("Company ID");	


		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("RunMode is No");
		}

		if(SQLConnector.doesUserExists(ldapUserId,compID))
		{
			homePg = new HomePage();
			test.log(LogStatus.INFO, "Tenant's Homepage launched");
			platformConfigPg = homePg.openConfigurator();
			test.log(LogStatus.INFO, "Tenant's Configurator link opened");
			connectivityPg = platformConfigPg.openConnectivityTab();
			test.log(LogStatus.INFO, "Connectivity Tab opened");
			if(connectivityPg.LDAPAuthentication(ladpSrvName, ldapURL, ldapBaseDN, ldapAuth, ADFSSrvName, ADFSURL, ADFSBaseDN, ADFSAuth))
			{
				test.log(LogStatus.INFO, "LDAP Authentication cennection setup passed");
				homePg.logout();
				test.log(LogStatus.INFO, "Parent Tenant's main user logged out");
				loginPg.login(url, ldapUserId, ldapUserPassword, tenantId, env);
				test.log(LogStatus.INFO, "Tenant's LDAP user launched");
				homePg = new HomePage();
				test.log(LogStatus.INFO, "Tenant's Homepage launched");
				if(!loginPg.loginvalidation(url, ldapinvlaidUserId, ldapinvlaidUserPassword, tenantId, env))
				{
					test.log(LogStatus.INFO, "Invalid LDAP Authentication verified successfully");
					loginPg.login(url, ldapUserId, ldapUserPassword, tenantId, env);
					test.log(LogStatus.INFO, "Tenant's Homepage launched with SSO Authentication");
					platformConfigPg = homePg.openConfigurator();
					test.log(LogStatus.INFO, "Tenant's Configurator link opened");
					connectivityPg = platformConfigPg.openConnectivityTab();
					test.log(LogStatus.INFO, "Connectivity Tab opened");
					connectivityPg.FMAuthentication(fmURL);
				}
				else
				{
					test.log(LogStatus.INFO, "Invalid SSO Authentication verification was failed");
				}
			}
			else
			{
				test.log(LogStatus.INFO, "LDAP Authentication cennection setup failed");
				connectivityPg.FMAuthentication(fmURL);
			}
		}
		else
		{
			test.log(LogStatus.INFO, "LDAP user not created in FM");
		}

	}


	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "ldapauthentication", enabled = false)
	public void testConnectionTest(Hashtable<String, String> data) throws Throwable {
		String ldapUserId = data.get("UserId");
		String ldapUserPassword = data.get("Password");
		String ladpSrvName = data.get("LDAP SERVER NAME");
		String ldapURL = data.get("LDAP URL");
		String ldapBaseDN = data.get("LDAP BASE DN");
		String ldapAuth = data.get("LDAP AUTHENTICATION SEARCH FILTER");
		String fmURL = data.get("FMURL");
		String compID = data.get("Company ID");			

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		test = extent.startTest("LDAP Authentication Test", "LDAP Authentication test is starting");
		MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("RunMode is No");
		}
		try {
			if(SQLConnector.doesUserExists(ldapUserId,compID))
			{
				homePg = new HomePage();
				test.log(LogStatus.INFO, "Tenant's Homepage launched");
				platformConfigPg = homePg.openConfigurator();
				test.log(LogStatus.INFO, "Tenant's Configurator link opened");
				connectivityPg = platformConfigPg.openConnectivityTab();
				test.log(LogStatus.INFO, "Connectivity Tab opened");
				if(connectivityPg.LDAPTestConnection(ladpSrvName, ldapURL, ldapBaseDN, ldapAuth, ldapUserPassword))
				{
					test.log(LogStatus.INFO, "LDAP Authentication Testing was success");
					connectivityPg.FMAuthentication(fmURL);
				}
				else
				{
					test.log(LogStatus.INFO, "LDAP Authentication Testing was failed");
					connectivityPg.FMAuthentication(fmURL);
				}
			}
			else
			{
				test.log(LogStatus.INFO, "LDAP user not created in FM");
			}
		} 
		catch (Exception e) {
			//		connectivityPg.FMAuthentication(fmURL);
			MyLogger.log.error(e.getClass().getSimpleName(), e);
			String path = Util.getscreenshot( methodName+"_"+e.getClass().getSimpleName());
			test.log(LogStatus.FAIL, "LDAP Authentication Connection testing Failed" + test.addScreenCapture(path));
			Assert.fail(e.getClass().getSimpleName(),e);	

		}
	}
}