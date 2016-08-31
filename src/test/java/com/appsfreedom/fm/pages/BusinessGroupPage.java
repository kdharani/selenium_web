package com.appsfreedom.fm.pages;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.appsfreedom.utils.Util;

public class BusinessGroupPage extends BasePage {
	
	private HomePage hmePg;
	@FindBy(xpath = "//tbody[@id='groupBody']")
	WebElement groupBodyTbl;
	@FindBy(xpath = "//*[@id='bgEditBtn']")
	WebElement grpEditButton;

	@FindBys({ @FindBy(xpath = "//label[@title='Business Groups']"),
			@FindBy(xpath = "//a[contains(text(), 'Create Group')]") })
	WebElement createGroupButton;
	@FindBy(xpath = "//form[@id='bGroupForm']//input[@id='name']")
	WebElement bGrpName;
	@FindBy(xpath = "//form[@id='bGroupForm']//input[@id='description']")
	WebElement bGrpDesc;
	@FindBy(xpath = "//form[@id='bGroupForm']//a[@id='saveOrUpdateBGroup']")
	WebElement saveOrUpdateBGroup;
	
	@FindBy(xpath = "//form[@id='bGroupForm']//a[contains(text(), 'Cancel')]")
	WebElement cancelBGForm;
	
	@FindBy(xpath = "//*[@id='alertPopupModal']")
	WebElement alertPopup;
	@FindBy(xpath = "//*[@id='alertPopupModal']//div[@id='alertMessage']")
	WebElement alertMessage;
	@FindBy(xpath = "//*[@id='alertPopupModal']//a[contains(text(),'Ok')]")
	WebElement alertPopupOkBtn;
	
	@FindBy(xpath = "//a[@href='#assignedroles']")
	WebElement assignRoleTab;
	@FindBy(xpath = "//a[@id='bgARAddBtn']")
	WebElement assignRoleAddButton;
	@FindBy(xpath = "//*[@id='bGroupAddRoles']")
	WebElement bGrpRoles;
	@FindBy(xpath = "//table[@id='bgAssignedRolesTable']")
	WebElement assignedRolesTable;

	@FindBy(xpath = "//a[@href='#assignedusers']")
	WebElement assignUserTab;
	@FindBy(xpath = "//*[@id='assignedusers']//a[contains(text(), 'Add')]")
	WebElement assignUserAddButton;
	@FindBy(xpath = "//*[@id='bGroupAddUsers']")
	WebElement bGrpUsers;
	@FindBy(xpath = "//table[@id='bgAssignedUsersTable']")
	WebElement assignedUsersTable;
	
	@FindBy(xpath = "//a[@href='#availableapps']")
	WebElement assignAppTab;
	@FindBy(xpath = "//*[@id='availableapps']//a[contains(text(), 'Add')]")
	WebElement assignAppAddButton;
	@FindBy(xpath = "//*[@id='bgAvailableAppsAdd']")
	WebElement bGrpApps;
	@FindBy(xpath = "//table[@id='bgAvaiAppsTable']")
	WebElement assignedAppsTable;
	@FindBy(xpath = "//form[@id='deleteBGroupForm']//a[contains(text(),'Yes')]")
	WebElement deleteConfirmBtn;
	@FindBy(xpath = "//form[@id='deleteBGroupForm']//a[contains(text(),'No')]")
	WebElement deleteCancelBtn;
	@FindBy(xpath="//form[@id='bGroupAssignedRoleDelForm']//a[contains(text(),'Yes')]")
	WebElement deleteRoleConfirmBtn;
	@FindBy(xpath="//form[@id='bGroupAssignedRoleDelForm']//a[contains(text(),'No')]")
	WebElement deleteRoleCancelBtn;
	@FindBy(xpath="//form[@id='bGroupAssignedUserDelForm']//a[contains(text(),'Yes')]")
	WebElement deleteUserConfirmBtn;
	@FindBy(xpath="//form[@id='bGroupAssignedUserDelForm']//a[contains(text(),'No')]")
	WebElement deleteUserCancelBtn;
	@FindBy(xpath="//form[@id='availableAppDelForm']//a[contains(text(),'Yes')]")
	WebElement deleteAppConfirmBtn;
	@FindBy(xpath="//form[@id='availableAppDelForm']//a[contains(text(),'No')]")
	WebElement deleteAppCancelBtn;

	public BusinessGroupPage() {
		super();
		hmePg = new HomePage();
	}
	public HomePage getHmePage() {
		return hmePg;
	}
	public boolean checkEdit() {

		if (!grpEditButton.isDisplayed()) {
			return true;
		} else
			return false;
	}

	public boolean checkBGExists(String gName) throws Exception {
		try {
			if ((Util.isElementDisplayed(groupBodyTbl))) {
				if (groupBodyTbl.findElements(By.xpath("//*[@id='groupBody']//td[contains(text(),'"+gName+"')]")).size()>0)
					return true;

			}
		} catch (Exception e) {
			log.info("Business Group Not Found..");
			throw (e);
		}
		return false;

	}

	/*public BusinessGroupPage selectBG(WebElement bgrpRadio) throws Exception {
		try {
			bgrpRadio.click();
			log.info("Business Group selected");
		} catch (Exception e) {
			log.info("Business Group not found");
			throw (e);
		}
		return this;
	}*/

	public BusinessGroupPage openAddBGForm() throws Exception {
		try {
			createGroupButton.click();
			log.info("Add Business Group button clicked");
		} catch (Exception e) {
			log.info("Add Business Group button not found");
			throw (e);
		}
		return this;
	}

	public BusinessGroupPage setBGName(String bgrp) throws Exception {
		try {
			bGrpName.clear();
			bGrpName.sendKeys(bgrp);
			log.info("Business Group Name entered");
		} catch (Exception e) {
			log.info("Business Group input field not found");
			throw (e);
		}
		return this;
	}

	public BusinessGroupPage setBGDesc(String bgrpDes) throws Exception {
		try {
			bGrpDesc.clear();
			bGrpDesc.sendKeys(bgrpDes);
			log.info("Business Group Description entered");
		} catch (Exception e) {
			log.info("Business Group Description input field not found");
			throw (e);
		}
		return this;
	}

	public BusinessGroupPage saveOrEditBG() throws Exception {
		try {
			saveOrUpdateBGroup.click();
			log.info("Save Business Group button clicked");
		} catch (Exception e) {
			log.info("Save Business Group button not found");
			throw (e);
		}
		return this;
	}

	public BusinessGroupPage openEditBGForm() throws Exception {
		try {
			saveOrUpdateBGroup.click();
			log.info("Update Business Group button clicked");
		} catch (Exception e) {
			log.info("Update Business Group button not found");
			throw (e);
		}
		return this;
	}

	public void selectBusinessGroup(String gName) throws Exception {
		try {
			if ((Util.isElementDisplayed(groupBodyTbl))) {
				driver.findElement(By.xpath(
						"//*[@id='groupBody']//td[contains(text(), '" + gName + "')]/preceding-sibling::td/input")).click();
				/*WebElement bgrpRadio = driver.findElement(By.xpath(
						"//*[@id='groupBody']//td[contains(text(), '" + gName + "')]/preceding-sibling::td/input"));
				selectBG(bgrpRadio);*/
				log.info("Business group "+gName+" selected");
			}
		} catch (Exception e) {
			log.info("Business Group not found..");
			throw (e);
		}
	}
	
	public void clickDeleteBGroup(String gName) throws Exception {
		try {
			if ((Util.isElementDisplayed(groupBodyTbl))) {
				WebElement deleteImg = driver.findElement(By.xpath(
						"//*[@id='groupBody']//td[contains(text(), '" + gName + "')]/following-sibling::td/a[@class='businessgroup_delete']"));
				((JavascriptExecutor)driver).executeScript("arguments[0].click()", deleteImg);
				log.info("Clicked on "+gName+" delete image");
				Util.delay(1000);
			}
		} catch (Exception e) {
			log.info("Business Group not found..");
			throw (e);
		}
	}
	
	public void selectRoleForBG(String role) throws Exception{
		try {
			bGrpRoles.findElement(By.xpath("//*/select/option[contains(text(),'"+role+"')]")).click();
			Util.delay(2000);
			bGrpRoles.findElement(By.xpath("//a[contains(text(),'Save')]")).click();
			Util.delay(2000);
		}
		catch (Exception e){
			log.error("Given Role '"+role+"' Not Found..");
			throw (e);
		}
	}
	
	public void selectUserForBG(String username) throws Exception{
		try {
			bGrpUsers.findElement(By.xpath("//*[@id='availableBGroupUsers']/option[contains(text(),'" + username + "')]")).click();
			//bGrpUsers.findElement(By.xpath("//*/select/option[contains(text(),'"+username+"')]")).click();
			Util.delay(2000);
			bGrpUsers.findElement(By.xpath("//*[@id='bGroupAddUsers']/div/a[contains(text(),'Save')]")).click();
			//bGrpUsers.findElement(By.xpath("//a[contains(text(),'Save')]")).click();
			Util.delay(2000);
		}
		catch (Exception e){
			log.error("Given User Name '"+username+"' Not Found..");
			throw (e);
		}
	}
	
	public void selectAppForBG(String appName) throws Exception{
		try {
			bGrpApps.findElement(By.xpath("//*[@id='availableAppsId']/option[contains(text(),'" + appName + "')]")).click();
			//bGrpApps.findElement(By.xpath("//*/select/option[contains(text(),'"+appName+"')]")).click();
			Util.delay(2000);
			bGrpApps.findElement(By.xpath("//*[@id='bgAvailableAppsAdd']/div/a[contains(text(),'Save')]")).click();
			//bGrpApps.findElement(By.xpath("//a[contains(text(),'Save')]")).click();
			Util.delay(2000);
		}
		catch (Exception e){
			log.error("Given Role '"+appName+"' Not Found..");
			throw (e);
		}
	}

	public boolean createBG(String gName, String gDesc) throws Exception {
		try {
			/*
			 * boolean availstatus=checkBusinessGroup(gName);
			 * log.info("GrpStatus::"+availstatus); if(!availstatus){
			 */
			openAddBGForm();
			Util.delay(1000);
			setBGName(gName);
			setBGDesc(gDesc);
			saveOrEditBG();
			Util.delay(1000);
			if(alertPopup.isDisplayed()){
				alertPopup.findElement(By.xpath("//*/a[contains(text(),'Ok')]")).click();
				cancelBGForm.click();
				Util.delay(1000);
				return false;
			}
			boolean status = checkBGExists(gName);
			return status;

		} catch (Exception e) {
			log.info("Create Group Button not loaded properly");
			throw (e);
		}
	}

	public boolean editBG(String gName, String editgName, String editgDesc) throws Exception {
		try {
			selectBusinessGroup(gName);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView()", grpEditButton);
			Util.delay(4000);
			grpEditButton.click();
			Util.delay(4000);
			setBGName(editgName);
			setBGDesc(editgDesc);
			saveOrEditBG();
			Util.delay(1000);
			if(alertPopup.isDisplayed()){
				alertPopup.findElement(By.xpath("//*/a[contains(text(),'Ok')]")).click();
				cancelBGForm.click();
				Util.delay(1000);
				return false;
			}
			boolean status = checkBGExists(editgName);
			return status;
		} catch (Exception e) {
			log.info("Edit Group Button not loaded properly");
			e.printStackTrace();
			throw (e);
		}

	}

	public boolean assignRoleToBG(String gname, String role) throws Exception {
		try {
			selectBusinessGroup(gname);
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", assignUserTab);
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", assignRoleTab);
			//assignUserTab.click();
			//assignRoleTab.click();
			Util.delay(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", assignRoleAddButton);
			//assignRoleAddButton.click();
			Util.delay(1000);
			selectRoleForBG(role);
			if (assignedRolesTable.findElement(By.xpath("//*/tbody/tr/td[contains(text(),'"+role+"')]"))
					.isDisplayed()) {
				return true;
			}
			else
				return false;
			
		} catch (Exception e) {
			log.error("Unable to Assign Role for Business Group");
			e.printStackTrace();
			throw (e);
		}
	}
	
	public boolean removeRoleFrmBG(String gname, String role) throws Exception {
		try {
			selectBusinessGroup(gname);
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", assignUserTab);
			log.info("Clicked on assigned users tab");
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", assignRoleTab);
			log.info("Clicked on assigned roles tab");
			//assignUserTab.click();
			//assignRoleTab.click();
			Util.delay(3000);
			WebElement deleteImg = assignedRolesTable.findElement(By.xpath("//td[contains(text(),'"+role+"')]/following::a[@class='businessgroup_delete']"));
			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", deleteImg);
			log.info("Clicked on delete image");
			Util.delay(1000);
			deleteRoleConfirmBtn.click();
			log.info("Clicked on confirm button");
			Util.delay(1000);
			//selectRoleForBG(role);
			if (!(assignedRolesTable.findElements(By.xpath("//*/tbody/tr/td[contains(text(),'"+role+"')]"))
					.size()>1)) {
				//log.info("Role deleted successfully");
				return true;
			}
			else
				return false;
			
		} catch (Exception e) {
			log.error("Unable to delte Role form Business Group");
			e.printStackTrace();
			throw (e);
		}
	}

	public boolean removeUserFrmBG(String gname, String username, String loginName) throws Exception {
		try {
			selectBusinessGroup(gname);
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", assignRoleTab);
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", assignUserTab);
			//assignRoleTab.click();
			//assignUserTab.click();
			WebElement deleteImg = assignedUsersTable.findElement(By.xpath("//td[contains(text(),'"+loginName+"')]/following::a[@class='businessgroup_delete']"));
			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", deleteImg);
			log.info("Clicked on delete image");
			Util.delay(1000);
			deleteUserConfirmBtn.click();
			log.info("Clicked on confirm button");
			Util.delay(1000);
			if (assignedUsersTable.findElements(By.xpath("//*/tbody/tr/td[contains(text(),'"+loginName+"')]"))
					.size()<1) {
				return true;
			}
			else
				return false;
		} 
		catch (Exception e) {
			log.error("Unable to delete User for Business Group");
			throw (e);
		}
	}
	
	public boolean assignUserToBG(String gname, String username, String loginName) throws Exception {
		try {
			selectBusinessGroup(gname);
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", assignRoleTab);
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", assignUserTab);
			//assignRoleTab.click();
			//assignUserTab.click();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView()", assignUserAddButton);
			Util.delay(2000);
			assignUserAddButton.click();
			Util.delay(2000);
			selectUserForBG(username);
			if (assignedUsersTable.findElement(By.xpath("//*/tbody/tr/td[contains(text(),'"+loginName+"')]"))
					.isDisplayed()) {
				return true;
			}
			else
				return false;
		} 
		catch (Exception e) {
			log.error("Unable to Assign User for Business Group");
			throw (e);
		}
	}

	public boolean removeAppFrmBG(String gname, String appName) throws Exception {
		try {
			selectBusinessGroup(gname);
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", assignUserTab);
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", assignAppTab);
			//assignUserTab.click();
			//assignAppTab.click();
			Util.delay(3000);
			WebElement deleteImg = assignedAppsTable.findElement(By.xpath("//td[contains(text(),'"+appName+"')]/following::a[@class='businessgroup_delete']"));
			
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", deleteImg);
			log.info("Clicked on delete image");
			Util.delay(1000);
			deleteAppConfirmBtn.click();
			log.info("Clicked on confirm button");
			Util.delay(1000);
			if (assignedAppsTable.findElements(By.xpath("//*/tbody/tr/td[contains(text(),'"+appName+"')]"))
					.size()<1) {
				return true;
			}
			else
				return false;
		} 
	
		catch (Exception e) {
			log.error("Unable to Assign App for Business Group");
			throw (e);
		}
	}
	public boolean assignAppToBG(String gname, String appName) throws Exception {
		try {
			selectBusinessGroup(gname);
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", assignUserTab);
			((JavascriptExecutor)driver).executeScript("arguments[0].click()", assignAppTab);
			//assignUserTab.click();
			//assignAppTab.click();
			Util.delay(3000);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView()", assignAppAddButton);
			Util.delay(2000);
			assignAppAddButton.click();
			Util.delay(3000);
			selectAppForBG(appName);
			if (assignedAppsTable.findElement(By.xpath("//*/tbody/tr/td[contains(text(),'"+appName+"')]"))
					.isDisplayed()) {
				return true;
			}
			else
				return false;
		} 
	
		catch (Exception e) {
			log.error("Unable to Assign App for Business Group");
			throw (e);
		}
	}
	
	public String deleteBGroup(String gname) throws Exception {
		String result = "Deleted";
		try {
			clickDeleteBGroup(gname);
			deleteConfirmBtn.click();
			log.info("Clicked on delete confirm button");
			Util.delay(2000);
			if(alertPopup.isDisplayed()){
				log.info(alertMessage.getText());
				result = alertMessage.getText();
				alertPopupOkBtn.click();
				log.info("Clicked on alert popput OK button");
				deleteCancelBtn.click();
				Util.delay(1000);
			}
			return result;
			} 
	
		catch (Exception e) {
			log.error("Unable to delete the Business Group");
			throw (e);
		}
	}

}
