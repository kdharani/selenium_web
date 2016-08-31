package com.appsfreedom.fm.pages;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.appsfreedom.utils.MyLogger;
import com.appsfreedom.utils.Util;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SubscriptionPage extends BasePage {

	private static SubscriptionPageObjects uiObject = new SubscriptionPageObjects();

	public static SubscriptionPage openSubscriptionForm() {
		try {

			uiObject.addSubscriptionBtn.click();
			if (Util.isElementDisplayed(uiObject.packageEditorForm)) {
				MyLogger.log.debug("Add subscription form opened");
			} else {
				MyLogger.log.debug("Add Subscription button not displayed");
			}
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage setPackageName(String value) {
		try {
			uiObject.packageName.clear();
			uiObject.packageName.sendKeys(value);
			MyLogger.log.debug("Package name set");
		} catch (Throwable e) {
			throw (e);
		}

		return new SubscriptionPage();
	}

	public static SubscriptionPage selectPackage(String value) {
		Select select = new Select(uiObject.productId);
		if (Util.isOptionExist(select, value)) {
			select.selectByVisibleText(value);
			MyLogger.log.debug("Option " + value + " is selected");
		} else {
			MyLogger.log.debug("Option " + value + " not found in the dropdown");
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage selectMethod(String value) {
		Select select = new Select(uiObject.methodtId);
		if (Util.isOptionExist(select, value)) {
			select.selectByVisibleText(value);
			MyLogger.log.debug("Option " + value + " is selected");
		} else {
			MyLogger.log.debug("Option " + value + " not found in the dropdown");
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage selectAppDesigner() {
		try {
			uiObject.appDesignerPermission.click();
			MyLogger.log.debug("App designer permission selected");
		} catch (Throwable e) {
			throw (e);
		}

		return new SubscriptionPage();
	}

	public static SubscriptionPage selectBuilder() {
		try {
			uiObject.builderPermission.click();
			MyLogger.log.debug("Builder permission selected");
		} catch (Throwable e) {
			throw (e);
		}

		return new SubscriptionPage();
	}

	public SubscriptionPage selectConfigurator() {
		try {
			uiObject.configuratorPermission.click();
			MyLogger.log.debug("Configurator permission selected");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public SubscriptionPage selectAppLibrary() {
		try {
			uiObject.AppLibPermission.click();
			MyLogger.log.debug("App Library permission selected");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage selectAppWatch() {
		try {
			uiObject.AppWatchPermission.click();
			MyLogger.log.debug("App Watch permission selected");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage selectDbDesigner() {
		try {
			uiObject.dbDesignerPermission.click();
			MyLogger.log.debug("DB Designer permission selected");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public SubscriptionPage selectProcessModeler() {
		try {
			uiObject.processModelerPermission.click();
			MyLogger.log.debug("Process modeler permission selected");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage selectEnvironment(String value) {
		Select select = new Select(uiObject.environment);
		if (Util.isOptionExist(select, value)) {
			select.selectByVisibleText(value);
			MyLogger.log.debug("Option " + value + " is selected");
		} else {
			MyLogger.log.debug("Option " + value + " not found in the dropdown");
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage setTenantCount(String value) {
		try {
			uiObject.tenantCount.clear();
			uiObject.tenantCount.sendKeys(value);
			MyLogger.log.debug("Tenant count set");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage setStorageLimit(String value) {
		try {
			uiObject.storageLimit.clear();
			uiObject.storageLimit.sendKeys(value);
			MyLogger.log.debug("Storage limit set");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage setDataTransfer(String value) {
		try {
			uiObject.dataTransfer.clear();
			uiObject.dataTransfer.sendKeys(value);
			MyLogger.log.debug("Data Transfer set");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage setActionCount(String value) {
		try {
			uiObject.actionCount.clear();
			uiObject.actionCount.sendKeys(value);
			MyLogger.log.debug("Action count set");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage setBOSCreated(String value) {
		try {
			uiObject.bosCreated.clear();
			uiObject.bosCreated.sendKeys(value);
			MyLogger.log.debug("BOS Created set");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage setBOSAccessCount(String value) {
		try {
			uiObject.bosAccessCount.clear();
			uiObject.bosAccessCount.sendKeys(value);
			MyLogger.log.debug("BOS Created set");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage setAppCount(String value) {
		try {
			uiObject.appCount.clear();
			uiObject.appCount.sendKeys(value);
			MyLogger.log.debug("App count set");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}
	
	public int getAppCount() {
		int count=0;
		try {
			Util.markElement(uiObject.appCount);
			count = Integer.parseInt((String) ((JavascriptExecutor)driver).executeScript("return arguments[0].value;", uiObject.appCount));
			MyLogger.log.debug("App count is returned ");
		} catch (Throwable e) {
			throw (e);
		}
		return count;
	}
	
	public SubscriptionPage changeAppCount(String value) {
		try {
			//Util.markElement(uiObject.appCount);
			String path = Util.getscreenshot("Old app count");
			test.log(LogStatus.INFO, "Old app count" + test.addScreenCapture(path));
			uiObject.appCount.clear();
			uiObject.appCount.sendKeys(value);
			path = Util.getscreenshot("New app count");
			test.log(LogStatus.INFO, "New app count" + test.addScreenCapture(path));
			MyLogger.log.debug("App count set");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage setTableCount(String value) {
		try {
			uiObject.tableCount.clear();
			uiObject.tableCount.sendKeys(value);
			MyLogger.log.debug("Table count set");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage setPuserCount(String value) {
		try {
			uiObject.platformUserCount.clear();
			uiObject.platformUserCount.sendKeys(value);
			MyLogger.log.debug("Puser count set");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage setBuserCount(String value) {
		try {
			uiObject.businessUserCount.clear();
			uiObject.businessUserCount.sendKeys(value);
			MyLogger.log.debug("Buser count set");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage setMaxFileSize(String value) {
		try {
			uiObject.maxFileSize.clear();
			uiObject.maxFileSize.sendKeys(value);
			MyLogger.log.debug("Max file size set");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage setMaxStorageSize(String value) {
		try {
			uiObject.maxStorageSize.clear();
			uiObject.maxStorageSize.sendKeys(value);
			MyLogger.log.debug("Max storage size set");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage savePackage() {
		try {
			((JavascriptExecutor) driver).executeScript("window.alert = function(msg) { return true; }");
			uiObject.saveOrUpdatePackBtn.click();
			Util.isNotDisplayed(uiObject.saveOrUpdatePackBtn);
			Util.isNotDisplayed(uiObject.spinner);
			MyLogger.log.debug("Package saved");
		} catch (Throwable e) {
			throw (e);
		}
		return new SubscriptionPage();
	}

	public static SubscriptionPage closePackageDetailForm() {
		try {
			uiObject.closePackageDetailkBtn.click();
			MyLogger.log.debug("Package detail form closed");
			;
		} catch (Throwable e) {
			throw (e);
		}

		return new SubscriptionPage();
	}

	public static String closePopup() {
		String msg = uiObject.alertMsg.getText();
		uiObject.alertOkBtn.click();
		return msg;
	}

	public static boolean isPopupOpen() {

		return Util.isElementDisplayed(uiObject.alertPopup);
	}

	public static boolean isPackageFormOpen() {

		return Util.isElementDisplayed(uiObject.packageEditorForm);
	}

	public static String getPopupMsg() {
		return uiObject.alertMsg.getText();
	}

	public static boolean isPackageExists(String name) throws Exception {
		boolean exists = false;
		try {
			Util.highlightElement(uiObject.searchBtn);
			uiObject.searchBtn.click();
			MyLogger.log.info("Search button clicked");
			uiObject.packageNameColumn.sendKeys(name);
			MyLogger.log.info("Package name entered");
			if (driver.findElements(By.xpath("//*[@id='subscriptionTable']//td[text()='" + name + "']")).size() == 1) {
				exists = true;
				MyLogger.log.info("Package " + name + " exists");
				// uiObject.refreshBtn.click();
				// uiObject.searchBtn.click();
			}
		} catch (Exception e) {
			MyLogger.log.info("Unable to check package");
			throw (e);
		}
		clearSearch();
		return exists;
	}

	public SubscriptionPage selectPackageToEdit(String packageName) throws Exception{
		try {
			uiObject.subscriptionTbl
					.findElement(By.xpath("//td[text()='" + packageName + "']/following-sibling::td[3]/a[1]")).click();
			MyLogger.log.info("Package edit button clicked");
			Util.waitForElement(uiObject.packageEditorForm);
			test.log(LogStatus.INFO, "Package detail form opened");
		} catch (Throwable e) {
			MyLogger.log.debug("Select package failed");
			throw (e);
			
		}
		return new SubscriptionPage();
	}

	public boolean deletePackage(String packageName) throws Throwable {
		boolean result = false;
		// child = extent.startTest("Delete Package");
		try {

			uiObject.subscriptionTbl
					.findElement(By.xpath("//td[text()='" + packageName + "']/following-sibling::td[3]/a[2]")).click();
			if (Util.isElementDisplayed(uiObject.deleteModal)) {
				String path = Util.getscreenshot("Delete Confirm");
				test.log(LogStatus.INFO, "Delete confirm" + test.addScreenCapture(path));
				uiObject.deleteYesBtn.click();
				Util.isNotDisplayed(uiObject.deleteYesBtn);
				Util.delay(1000);

				if (Util.isElementDisplayed(uiObject.alertPopup)) {
					path = Util.getscreenshot("Delete Error");
					test.log(LogStatus.INFO, uiObject.alertMsg.getText() + test.addScreenCapture(path));
					uiObject.alertOkBtn.click();
					Util.delay(1000);
				}

				Util.waitForElement(uiObject.refreshBtn).click();
				// child.log(LogStatus.INFO, "Clicked on refresh button");
				Util.waitForElement(uiObject.searchBtn).click();
			}
			result = isPackageExists(packageName);
			if (!result) {
				String path = Util.getscreenshot("Delete Package");
				test.log(LogStatus.INFO, "Package deleted" + test.addScreenCapture(path));
			}

		} catch (Throwable e) {
			throw (e);
		}
		// test.appendChild(child);
		// extent.endTest(child);
		return result;
	}
	
	public static SubscriptionPage fillPackageDetail(Hashtable <String, String> data){
		setPackageName(data.get("PackageName"));
		selectPackage(data.get("Package"));
		selectMethod(data.get("Method"));
		selectAppDesigner();
		selectBuilder();
		selectAppWatch();
		selectEnvironment(data.get("Env"));
		setTenantCount(Util.trimZero(data.get("TenantCount")));
		setStorageLimit(Util.trimZero(data.get("StorageLimit")));
		setDataTransfer(Util.trimZero(data.get("DataTransfer")));
		setActionCount(Util.trimZero(data.get("ActionCount")));
		setBOSCreated(Util.trimZero(data.get("BosCreated")));
		setBOSAccessCount(Util.trimZero(data.get("BosAccessCount")));
		setAppCount(Util.trimZero(data.get("AppCount")));
		setTableCount(Util.trimZero(data.get("TableCount")));
		setPuserCount(Util.trimZero(data.get("PuserCount")));
		setBuserCount(Util.trimZero(data.get("BuserCount")));
		setMaxFileSize(Util.trimZero(data.get("MaxFileSize")));
		setMaxStorageSize(Util.trimZero(data.get("MaxStorageSize")));
		savePackage();
		return new SubscriptionPage();
	}
	
	public static void clearSearch(){
		uiObject.refreshBtn.click();
		Util.delay(3000);
		uiObject.searchBtn.click();
		
	}

}
