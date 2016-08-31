package com.appsfreedom.utils;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

	public static Excel excel = new Excel(System.getProperty("user.dir")+"\\resources\\ExcelData.xlsx");
	public DataProviderClass() {
		// TODO Auto-generated constructor stub
	}
	
	@DataProvider
	public static Object[][] login() {
		return ReadExcel.getData("LoginTest", excel);
	}
	
	@DataProvider()
	public static Object[][] appDesigner() {
		return ReadExcel.getData("AppDesigner", excel);

	}
	
	@DataProvider()
	public static Object[][] getData() {
		return ReadExcel.getData("CreateBOS", excel);
	}
	
	@DataProvider()
	public static Object[][] appWatch() {
		return ReadExcel.getData("AppWatchTest", excel);

	}	
	
	@DataProvider
	public static Object[][] createBOS() {
		return ReadExcel.getData("CreateBOS", excel);

	}

	@DataProvider
	public static Object[][] executeBOS() {
		return ReadExcel.getData("ExecuteBOS", excel);

	}
	
	@DataProvider
	public static Object[][] importBOS() {

		return ReadExcel.getData("ImportBOS", excel);

	}	

	@DataProvider()
	public static Object[][] appLib() {
		return ReadExcel.getData("AppLibrary", excel);

	}
	
	@DataProvider
	public static Object[][] createCA() {
		return ReadExcel.getData("CreateContainer", excel);

	}

	@DataProvider
	public static Object[][] submitTicket() {
		return ReadExcel.getData("SupportTicketTest", excel);

	}

	@DataProvider
	public static Object[][] createTenant() {
		return ReadExcel.getData("CreateTenantTest", excel);

	}
	
	@DataProvider
	public static Object[][] createRFCConnection() {
		return ReadExcel.getData("CreateRFC", excel);

	}
	
	@DataProvider
	public static Object[][] createPlugin() {
		return ReadExcel.getData("CreatePlugin", excel);

	}

	@DataProvider()
	public static Object[][] dbDesigner() {
		return ReadExcel.getData("DBDesigner", excel);

	}
	
	@DataProvider()
	public static Object[][] freedomApps() {
		return ReadExcel.getData("LoginTest", excel);

	}
	
	@DataProvider()
	public static Object[][] freedomDrive() {
		return ReadExcel.getData("FreedomDriveTest", excel);

	}

	
	@DataProvider()
	public static Object[][] Logs() {
		return ReadExcel.getData("GetLogsCount", excel);
	}	
	
	@DataProvider
	public static Object[][] editOrgInfo() {
		return ReadExcel.getData("EditOrgInfoTest", excel);

	}
	
	@DataProvider
	public static Object[][] editOrgAddress() {
		return ReadExcel.getData("EditOrgAddressUpdateTest", excel);

	}

	@DataProvider
	public static Object[][] addOrgContact() {
		return ReadExcel.getData("AddOrgContactTest", excel);
	}

	@DataProvider
	public static Object[][] deleteOrgContact() {
		return ReadExcel.getData("DeleteOrgContactTest", excel);
	}

	@DataProvider
	public static Object[][] editOrgContact() {
		return ReadExcel.getData("EditOrgContactTest", excel);
	}

	@DataProvider
	public static Object[][] changeOrgPrimaryContact() {
		return ReadExcel.getData("ChangePrimaryContactTest", excel);
	}

	@DataProvider
	public static Object[][] subscriptionTest() {
		return ReadExcel.getData("SubscriptionTest", excel);
	}


	@DataProvider()
	public static Object[][] createPlatformRole() {
		return ReadExcel.getData("CreateRole", excel);

	}
	@DataProvider()
	public static Object[][] editPlatformRole() {
		return ReadExcel.getData("EditRole", excel);

	}
	@DataProvider()
	public static Object[][] pRAssignedUsers() {
		return ReadExcel.getData("AssignedUsers", excel);

	}
	@DataProvider()
	public static Object[][] pRAssignedGroups() {
		return ReadExcel.getData("AssignedGroups", excel);

	}
	@DataProvider()
	public static Object[][] pRAssignedAccess() {
		return ReadExcel.getData("AssignedAccess", excel);

	}	


	@DataProvider()
	public static Object[][] platformUser() {
		return ReadExcel.getData("PlatformUserTest", excel);

	}	

	@DataProvider()
	public static Object[][] processModeler() {
		return ReadExcel.getData("ProcessModelerTest", excel);

	}	

	@DataProvider()
	public static Object[][] projectTransport() {
		return ReadExcel.getData("ProjectTransportTest", excel);

	}

	@DataProvider()
	public static Object[][] loginProxy() {
		return ReadExcel.getData("ProxyLoginTest", excel);

	}

	
	@DataProvider()
	public static Object[][] editSubscription() {
		return ReadExcel.getData("EditSubscriptionTest", excel);
	}

	@DataProvider()
	public static Object[][] editLicenceULU() {
			return ReadExcel.getData("EditLicenceTestULU", excel);
	}
			
	@DataProvider()
	public static Object[][] editLicenceLUL() {
			return ReadExcel.getData("EditLicenceTestLUL", excel);
	}
	
	@DataProvider()
	public static Object[][] subscription() {
			return ReadExcel.getData("SubscriptionTest", excel);
	}
	
	@DataProvider()
	public static Object[][] connectivity() {
			return ReadExcel.getData("ConnectivityTest", excel);
	}
	
	@DataProvider()
	public static Object[][] fmauthentication() {
		return ReadExcel.getData("FM Authentication", excel);
	}
	
	@DataProvider()
	public static Object[][] ssoauthentication() {
		return ReadExcel.getData("SSO NW Authentication", excel);
	}
	
	@DataProvider()
	public static Object[][] ldapauthentication() {
		return ReadExcel.getData("LDAP Authentication", excel);
	}
	
	@DataProvider()
	public static Object[][] scheduler() {
			return ReadExcel.getData("SchedulerTest", excel);
	}
}
