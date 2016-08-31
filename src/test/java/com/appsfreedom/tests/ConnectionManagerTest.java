package com.appsfreedom.tests;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.appsfreedom.fm.pages.HomePage;
import com.appsfreedom.utils.DataProviderClass;
import com.appsfreedom.utils.MyLogger;
import com.appsfreedom.utils.ReadExcel;
import com.appsfreedom.utils.SQLConnector;
import com.appsfreedom.utils.Util;

public class ConnectionManagerTest extends BaseTest {

	@Factory(dataProviderClass=DataProviderClass.class, dataProvider="login")
	public ConnectionManagerTest(Hashtable<String, String> data) {
		this.url = data.get("URL");
		this.userId = data.get("UserId");
		this.pwd = data.get("Password");
		this.tenantId = data.get("TenantId");
		this.env = data.get("Environment");
		this.browser = data.get("Browser");
	}
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider ="createPlugin", enabled = true, priority=0)
	public void createPluginTest(Hashtable<String, String> data){
		// Declare variable
		SoftAssert s_assert = new SoftAssert();
		String name = data.get("PluginName");
		String desc = data.get("PluginDesc");
		String URL = data.get("URL");
		String userId = data.get("UserId");
		String pwd = data.get("Password");
		String result="";
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		MyLogger.log.info("Starting test "+methodName);
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		
		try {
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			SQLConnector.cleanUpPlugin(name);
			connectionMngrPg = platformConfigPg.openConnectionMngrTab();
			connectionMngrPg.openAddPluginForm();
			connectionMngrPg.enterPluginNme(name);
			connectionMngrPg.enterPluginDesc(desc);
			connectionMngrPg.enterPluginUrl(URL);
			connectionMngrPg.enterPluginUserId(userId);
			connectionMngrPg.enterPluginPwd(pwd);
			connectionMngrPg.savePlugin();
			if (Util.isElementDisplayed(platformConfigPg.alertPopupModal)) {
				result = homePg.alertMessage.getText();
				MyLogger.log.info(result);
				homePg.closePopupModel();
				connectionMngrPg.unsavePlugin();
			} else {
				s_assert.assertTrue(connectionMngrPg.isPluginExists(name), "Plugin creation failed");
			}
			homePg = connectionMngrPg.getHmePage();
			Util.delay(2000);
			homePg.gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
			
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider ="createPlugin", enabled = true, priority=1)
	public void updatePluginTest(Hashtable<String, String> data){
		// Declare variable
		SoftAssert s_assert = new SoftAssert();
		String desc = data.get("NewPluginDesc");
		String name = data.get("PluginName");
		String result="";
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		
		try {
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			connectionMngrPg = platformConfigPg.openConnectionMngrTab();
			connectionMngrPg.selectPluginForEdit(name);
			connectionMngrPg.enterPluginDesc(desc);
			connectionMngrPg.updatePlugin();
			Util.delay(1000);
			if (Util.isElementDisplayed(platformConfigPg.alertPopupModal)) {
				result = homePg.alertMessage.getText();
				MyLogger.log.info(result);
				homePg.closePopupModel();
				connectionMngrPg.unsavePlugin();
			} else {
				s_assert.assertTrue(connectionMngrPg.isPluginUpdated(desc), "Plugin update failed");
			}
			homePg = connectionMngrPg.getHmePage();
			Util.delay(2000);
			homePg.gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
			
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		}
	}
	
	@Test(dataProviderClass=DataProviderClass.class, dataProvider = "createRFCConnection", enabled = true, priority=2)
	public void createConnectionsTest(Hashtable<String, String> data) {
		// Declare variable
		SoftAssert s_assert = new SoftAssert();
		String URL = data.get("URL");
		String userId = data.get("UserId");
		String pwd = data.get("Password");
		String tenantId = data.get("TenantId");
		String env = data.get("Environment");
		String system = data.get("System");
		String connection_name = data.get("ConnectionName");
		String wsdl = data.get("WSDL");
		String desc = data.get("Description");
		String isRemote = data.get("IsRemote");
		String plugin = data.get("Plugin");
		String result = "";
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		MyLogger.log.info("***************************Starting test "+methodName+"*********************************");
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Run Mode is No");
		}
		try {
			homePg = new HomePage();
			platformConfigPg = homePg.openConfigurator();
			SQLConnector.cleanUpConnections();
			connectionMngrPg = platformConfigPg.openConnectionMngrTab();
			connectionMngrPg.openAddconnectionForm();
			switch (system) {
			case "Web Service":
				connectionMngrPg.selectSystem(system);
				connectionMngrPg.enterConnectionNme(connection_name);
				connectionMngrPg.enablePwdRequired();
				connectionMngrPg.enterWsdlURI(wsdl);
				connectionMngrPg.loadWebSerives();
				connectionMngrPg.enterConnectionDesc(desc);
				if (isRemote.equalsIgnoreCase("Y")) {
					connectionMngrPg.selectPlugin(plugin);
				} else {
					connectionMngrPg.enableOnPrimise();
				}
				connectionMngrPg.saveConnection();
				break;
			case "Database":
				connectionMngrPg.selectSystem(system);
				connectionMngrPg.enterConnectionNme(connection_name);
				connectionMngrPg.enterConnectionDesc(desc);
				connectionMngrPg.enterdataSource(data.get("DataSource"));
				connectionMngrPg.selectDbType(data.get("DbType"));
				if (isRemote.equalsIgnoreCase("Y")) {
					connectionMngrPg.selectPlugin(plugin);
				} else {
					connectionMngrPg.enableOnPrimise();
				}
				connectionMngrPg.saveConnection();
				break;
			case "CRM":
			case "ERP":
			case "SRM":
				connectionMngrPg.selectSystem(system);
				connectionMngrPg.enterConnectionNme(connection_name);
				connectionMngrPg.enableOnPrimise();
				connectionMngrPg.enterConnectionDesc(desc);
				connectionMngrPg.enterRFCDestination(data.get("RFC"));
				connectionMngrPg.selectPlugin(plugin);
				connectionMngrPg.saveConnection();
				break;
			case "Rest Service":
				connectionMngrPg.selectSystem(system);
				connectionMngrPg.enterConnectionNme(connection_name);
				connectionMngrPg.enterDomain(data.get("Domain"));
				connectionMngrPg.enterRestURI(data.get("URI"));
				connectionMngrPg.selectRestMethod(data.get("Method"));
				if (data.get("PwdReq").equalsIgnoreCase("Y")) {
					connectionMngrPg.enablePwdRequired();
				}
				connectionMngrPg.selectOutpuType(data.get("OutputType"));
				connectionMngrPg.enterSampleOutput(data.get("SampleOutput"));
				connectionMngrPg.saveConnection();
				break;
			default:
				connectionMngrPg.closeConnectionForm();
				break;
			}
			if (Util.isElementDisplayed(platformConfigPg.alertPopupModal)) {
				result = homePg.alertMessage.getText();
				MyLogger.log.info(result);
				homePg.closePopupModel();
			} else {
				s_assert.assertTrue(connectionMngrPg.IsConnectionExists(connection_name), "Connection creation failed");
			}
			homePg = connectionMngrPg.getHmePage();
			Util.delay(2000);
			homePg.gotoHome();
			s_assert.assertAll();
			MyLogger.log.info("***************************Finishing test "+methodName+"*********************************");
		} catch (Exception e) {
			MyLogger.log.error(e.getClass().toString(), e);
			Util.getscreenshot(e.getClass().toString());
			Assert.fail(e.getLocalizedMessage());
		} finally {
			s_assert.assertAll();
		}
	}

}
