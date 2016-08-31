package com.appsfreedom.fm.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.appsfreedom.utils.CustomException;
import com.appsfreedom.utils.Util;

//******************Platform Role********************
	
public class PlatformRolePage extends BasePage {
	
	public HomePage hmePg;
	Logger log = Logger.getLogger("devpinoyLogger");
	
	@FindBy(xpath = "//*[@id='alertPopupModal']") // *[@id="alertPopupModal"]/div[3]/a
	WebElement alertPopupModal;

	@FindBy(xpath = "//*[@id='alertMessage']") // *[@id="alertPopupModal"]/div[3]/a
	WebElement alertMessage;
	
	@FindBy(xpath = "//*[@id='alertPopupModal']/div[3]/a")
	WebElement alertPopupOkBtn;
	
	/** Create Platform Role **/
	@FindBy(xpath = "//div[@id='platformrole']//a[contains(text(),'Create Role')]")
	WebElement createRoleBtn;

	@FindBy(xpath = "//form[@id='roleCreateForm']//input[@id='roleName']")
	WebElement roleNameInput;

	@FindBy(xpath = "//form[@id='roleCreateForm']//textarea[@id='roleDescription']")
	WebElement roleDescTextArea;

	@FindBy(xpath = "//form[@id='roleCreateForm']//a[contains(text(),'Save')]")
	WebElement roleSaveBtn;

	@FindBy(xpath = "//*[@id='roleTable']/tbody")
	WebElement platformRoleTable;
	
	/*** Edit Platform Role **/
	
	@FindBy(xpath = "//form[@id='platformrole_generalinfo']//input[@id='nameDisplay']")
	WebElement editRoleName;

	@FindBy(xpath = "//form[@id='platformrole_generalinfo']//textarea[@id='descriptionDisplay']")
	WebElement editRoleDesc;
	
	@FindBy(xpath = "//div[@id='platformrole_generalinfo']//a[@id='platformRoleGenEditBtn']")
	WebElement editRoleBtn;
	
	@FindBy(xpath = "//div[@id='platformrole_generalinfo']//input[@id='nameDisplay']")
	WebElement checkEditRoleValue;
	
	@FindBy(xpath = "//form[@id='roleEditForm']//input[@id='name']")
	WebElement editRoleNameInput;

	@FindBy(xpath = "//form[@id='roleEditForm']//textarea[@id='description']")
	WebElement editRoleDescTextArea;
	
	@FindBy(xpath = "//form[@id='roleEditForm']//a[contains(text(),'Update')]")
	WebElement editRoleSaveBtn;
	
	/** Platform Role Assigned Users Tab **/
	@FindBy(xpath = "//div[@id='content']//a[contains(text(),'Assigned Users')]")
	WebElement pRassginedUsersTab;
	
	/** Platform Role Assigned Groups Tab **/
	@FindBy(xpath = "//div[@id='content']//a[contains(text(),'Assigned Groups')]")
	WebElement pRassginedGroupsTab;
	
	@FindBy(xpath = "//div[@id='assignedgroups']//a[contains(text(),'Add')]")
	WebElement addAssignedUsersBtn;
	
	@FindBy(xpath = "//form[@id='roleAssignedGroupsAddForm']//select[@id='availableRoleGroups']")
	WebElement pRAddAssginedGroups;
	
	@FindBy(xpath = "//div[@id='roleGroupAdd']//a[contains(text(),'Save')]")
	WebElement addAssignedUsersSaveBtn;
	
	@FindBy(xpath = "//form[@id='deleteRoleAssgdGroupForm']//a[contains(text(),'Yes')]")
	WebElement deleteAssignedGroup;
	
	@FindBy(xpath="//table[@id='roleTable']//td[contains(text(),'Administrator')]/preceding-sibling::td/input[@name='selectRole']")
	WebElement checkboxAdmin;
	
	/** Platform Role Assigned Access Tab **/
	@FindBy(xpath = "//div[@id='content']//a[contains(text(),'Assigned Access')]")
	WebElement pRassginedAccessTab;
	
	@FindBy(xpath = "//div[@id='assignedaccess']//a[contains(text(),'Update')]")
	WebElement updateAssignedAccessBtn;
	
	@FindBy(xpath = "//div[@id='rolePermissionAddModal']//a[contains(text(),'Save')]")
	WebElement updateAssignedAccessSaveBtn;
	
	// ******************Platform Role********************
	
	public PlatformRolePage() {

		super();
		hmePg = new HomePage();
	}
	
	public HomePage getHmePage(){
		return hmePg;
	}
	/**Create Platform Role **/
	public PlatformRolePage clickRoleCreateBtn() throws Exception {
		try {
			Util.delay(2000);
			createRoleBtn.click();
			log.info("Platform Role Created button clicked");
			Util.delay(2000);
		} catch (Exception e) {
			log.info("Platform Role Create button not found");
			throw (e);
		}
		return this;
	}

	public PlatformRolePage enterRoleName(String roleName) throws Exception {
		try {
			Util.delay(2000);
			roleNameInput.sendKeys(roleName);
			log.info("Role Name entered");
		} catch (Exception e) {
			log.info("Role Name input field not found");
			throw (e);
		}
		return this;
	}

	public PlatformRolePage enterRoleDesc(String roleDesc) throws Exception {
		try {
			roleDescTextArea.sendKeys(roleDesc);
			log.info("Role Desc entered");
		} catch (Exception e) {
			log.info("Role Desc input field not found");
			throw (e);
		}
		return this;
	}

	public PlatformRolePage clickSaveNewRole() throws Exception {
		String message = "";
		try {
			roleSaveBtn.click();
			Util.delay(3000);
			log.info("Generate button clicked");

			while (true) {
				if (!roleSaveBtn.isDisplayed()) {
					break;
				}
				if (alertPopupModal.isDisplayed()) {
					message = alertMessage.getText();
					// System.out.println(message);
					log.info(message);
					alertPopupOkBtn.click();
					throw new CustomException(message);
				}
			}
		} catch (Exception e) {
			log.info("new role save button not fould");
			throw (e);
		}
		return this;
	}

	public Boolean checkPlatFormRoleAdded(String roleName) throws Exception {
		boolean result = false;
		try {
			if (driver.findElements(By.xpath("//table[@id='roleTable']//td[contains(text(), '" + roleName + "')]"))
					.size() > 0) {
				result = true;
				log.info("Role Name " + roleName + " exist");
			} else {
				log.info("Role Name " + roleName + " not exist");
			}
		} catch (Exception e) {
			log.info("Role not exists");
			throw (e);
		}
		return result;
	}

	/** Edit Platform Role **/
	public PlatformRolePage editPRole(String rN) throws Exception {
		try {
			log.info("editPRole outside Name:"+rN);
			if (checkPlatFormRoleAdded(rN)) {
				/*List<WebElement> rows = driver.findElements(By.xpath("//*[@id='roleBody']/tr"));
				for(int k=0;k<=rows.size();k++){
					List<WebElement> cols = driver.findElements(By.xpath("//*[@id='roleBody']/tr["+k+"]/td"));
					//log.info("paltform Role size is " + cols.size());
					if(cols.size()>0){
						if(cols.get(1).getText().equalsIgnoreCase(rN)){
							log.info("success Text:"+cols.get(1).getText());
							WebElement selecteduser = driver.findElement(By.xpath("//*[@id='roleBody']/tr["+k+"]/td[1]/input"));
							selecteduser.click();
							break;
						}
					}
				}*/
				log.info("editPRole Role Name:"+rN);
				driver.findElement(By.xpath("//table[@id='roleTable']//td[contains(text(),'"+rN+"')]/preceding-sibling::td/input[@name='selectRole']")).click();
				log.info("Role edit form opened");
				Util.delay(20000);
			} else {
				log.info("Role " + rN + " not found");
			}
		} catch (Exception e) {
			log.info("Platform role edit not found");
			throw (e);
		}
		return this;
	}
	public PlatformRolePage populateRoleName(String roleName) throws Exception {
		try {
			Util.delay(2000);
			editRoleName.sendKeys(roleName);
			log.info("Role Name entered");
		} catch (Exception e) {
			log.info("Edit Polulate Role Name input field not found");
			throw (e);
		}
		return this;
	}

	public PlatformRolePage populateRoleDesc(String roleDesc) throws Exception {
		try {
			editRoleDesc.sendKeys(roleDesc);
			log.info("Role Desc entered");
		} catch (Exception e) {
			log.info("Edit  Polulate  Role Desc input field not found");
			throw (e);
		}
		return this;
	}
	public PlatformRolePage clickRoleEditBtn() throws Exception {
		try {
			Util.delay(5000);
			if(Util.isElementDisplayed(editRoleBtn)){
				editRoleBtn.click();
				log.info("Platform Role Edit Dialog button clicked");
			}else{
				log.info("Platform Role Edit Dialog button not found");
				new CustomException ("Platform Role Edit Dialog button not found");
			}
		} catch (Exception e) {
			log.info("Platform Role Edit Dialog button not found");
			throw (e);
		}
		return this;
	}

	public PlatformRolePage enterEditRoleName(String roleName) throws Exception {
		try {
			Util.delay(2000);
			editRoleNameInput.clear();
			editRoleNameInput.sendKeys(roleName);
			log.info("Role Name entered");
		} catch (Exception e) {
			log.info("Edit Role Name input field not found");
			throw (e);
		}
		return this;
	}

	public PlatformRolePage enteEditRoleDesc(String roleDesc) throws Exception {
		try {
			editRoleDescTextArea.clear();
			editRoleDescTextArea.sendKeys(roleDesc);
			log.info("Role Desc entered");
		} catch (Exception e) {
			log.info("Edit Role Desc input field not found");
			throw (e);
		}
		return this;
	}
	public PlatformRolePage clickSaveEditRole() throws Exception {
		String message = "";
		try {
			editRoleSaveBtn.click();
			log.info("Edit Save Role button clicked");

			while (true) {
				if (!editRoleSaveBtn.isDisplayed()) {
					break;
				}
				Util.delay(5000);
				if (alertPopupModal.isDisplayed()) {
					message = alertMessage.getText();
					// System.out.println(message);
					log.info(message);
					alertPopupOkBtn.click();
					throw new CustomException(message);
				}
			}
		} catch (Exception e) {
			log.info("new role save button not fould");
			throw (e);
		}
		return this;
	}
	
	public Boolean checkPlatFormRoleUpdate(String roleName) throws Exception {
		boolean result = false;
		try {
			
			String uRname =(String) ((JavascriptExecutor)driver).executeScript("return arguments[0].value", checkEditRoleValue);
			 
			log.info("updated Role Name2:"+uRname);
			log.info("checkPlatFormRoleUpdate roleName2:"+roleName);
			if(uRname.equalsIgnoreCase(roleName)){
				result = true;
				log.info("Edit Role Name " + roleName + " exist");
			} else {
				log.info("Edit Role Name " + roleName + " not exist");
			}
		} catch (Exception e) {
			log.info("Updated Role not exists");
			throw (e);
		}
		return result;
	}
	
	/** Platform Role Assigned Users **/
	public PlatformRolePage checkAssignedRoles(String rN) throws Exception {
		try {
			if (checkPlatFormRoleAdded(rN)) {
/*				List<WebElement> rows = driver.findElements(By.xpath("//*[@id='roleBody']/tr"));
				
				for(int k=0;k<=rows.size();k++){
					List<WebElement> cols = driver.findElements(By.xpath("//*[@id='roleBody']/tr["+k+"]/td"));
					log.info("editPRoleAssginedUsers Role size is " + cols.size());
					if(cols.size()>0){
						if(cols.get(1).getText().equalsIgnoreCase(rN)){
							log.info("success Text:"+cols.get(1).getText());
							WebElement selecteduser = driver.findElement(By.xpath("//*[@id='roleBody']/tr["+k+"]/td[1]/input"));
							selecteduser.click();
							Util.delay(100);
							pRassginedUsersTab.click();
							Util.delay(5000);
							pRassginedUsersTab.click();
							break;
						}
					}
				}*/
				driver.findElement(By.xpath("//table[@id='roleTable']//td[contains(text(),'"+rN+"')]/preceding-sibling::td/input[@name='selectRole']")).click();
				log.info("Role edit form opened");
				Util.delay(100);
				pRassginedUsersTab.click();
				Util.delay(5000);
				pRassginedUsersTab.click();
				//Util.delay(2000);
			} else {
				log.info("Role " + rN + " not found");
			}
		} catch (Exception e) {
			log.info("Platform role edit not found");
			throw (e);
		}
		return this;
	}
	public Boolean checkAssignedUsers(String loginIds) throws Exception {
		boolean result = false;
		try {
			String[] alLoginIds=loginIds.split(";");
			for(int k=0;k<alLoginIds.length;k++){
				String loginId=alLoginIds[k];
				if (driver.findElements(By.xpath("//table[@id='roleAssignedUsersTable']//td[contains(text(), '" + loginId + "')]"))
						.size() > 0) {
					log.info(driver.findElement(By.xpath("//table[@id='roleAssignedUsersTable']//td[contains(text(), '" + loginId + "')]")).getText());
					result = true;
					log.info(" Assigned Users "+loginId+" exists");
				}
			}
		} catch (Exception e) {
			log.info("Updated Role not exists");
			throw (e);
		}
		return result;
	}
	
	/** Platform Role Assigned Groups **/
	public PlatformRolePage editPRoleAssginedGroups(String rN) throws Exception {
		try {
			if (checkPlatFormRoleAdded(rN)) {
				List<WebElement> rows = driver.findElements(By.xpath("//*[@id='roleBody']/tr"));
				for(int k=0;k<=rows.size();k++){
					List<WebElement> cols = driver.findElements(By.xpath("//*[@id='roleBody']/tr["+k+"]/td"));
					log.info("Assigned Groups Role size is " + cols.size());
					if(cols.size()>0){
						if(cols.get(1).getText().equalsIgnoreCase(rN)){
							log.info("success Text:"+cols.get(1).getText());
							WebElement selecteduser = driver.findElement(By.xpath("//*[@id='roleBody']/tr["+k+"]/td[1]/input"));
							selecteduser.click();
							Util.delay(100);
							pRassginedGroupsTab.click();
							Util.delay(3000);
							pRassginedGroupsTab.click();
							break;
						}
					}
				}
				/*driver.findElement(By.xpath("//table[@id='roleTable']//td[contains(text(),"+rN+")]/preceding-sibling::td/input[@name='selectRole']")).click();
				Util.delay(100);
				pRassginedGroupsTab.click();
				Util.delay(3000);
				pRassginedGroupsTab.click();
				log.info("Assigned Groups form opened");*/
				//Util.delay(2000);
			} else {
				log.info("Assigned Groups Role " + rN + " not found");
			}
		} catch (Exception e) {
			log.info("Assigned Groups role edit not found");
			throw (e);
		}
		return this;
	}
	public PlatformRolePage clickAssignedGroupsBtn() throws Exception {
		try {
			Util.delay(5000);
			if(Util.isElementDisplayed(addAssignedUsersBtn)){
				addAssignedUsersBtn.click();
				log.info("Assigned Groups Add Edit Dialog button clicked");
			}else{
				log.info("Assigned Groups Add Dialog button not found");
				new CustomException ("Assigned Groups Add Dialog button not found");
			}
		} catch (Exception e) {
			log.info("Assigned Groups Add button not found");
			throw (e);
		}
		return this;
	}
	public PlatformRolePage addNewAssignGroup(String assignGroups) throws Exception {
		try {
			Select addAGSelect = new Select(pRAddAssginedGroups);
			String aGs []=assignGroups.split(";");
			for(String aG:aGs){
				addAGSelect.selectByVisibleText(aG);
			}
			log.info("Added new assign group");
		} catch (Exception e) {
			log.info("Added new assign group not found");
			throw (e);
		}
		return this;
	}
	public PlatformRolePage saveNewAssignGroup() throws Exception {
		String message = "";
		try {
			addAssignedUsersSaveBtn.click();
			log.info("saveNewAssignGroup button clicked");

			while (true) {
				if (!addAssignedUsersSaveBtn.isDisplayed()) {
					break;
				}
				Util.delay(5000);
				if (alertPopupModal.isDisplayed()) {
					message = alertMessage.getText();
					// System.out.println(message);
					log.info(message);
					alertPopupOkBtn.click();
					throw new CustomException(message);
				}
			}
		} catch (Exception e) {
			log.info("saveNewAssignGroup button not fould");
			throw (e);
		}
		return this;
	}
	public Boolean checkAssignedGroupsAdded(String assignedGroups) throws Exception {
		boolean result = false;
		try {
			String[] aGs=assignedGroups.split(";");
			for(int k=0;k<aGs.length;k++){
				if (driver.findElements(By.xpath("//table[@id='roleAssignedGroupsTable']//td[contains(text(), '" + aGs[k] + "')]"))
						.size() > 0) {
					log.info(driver.findElement(By.xpath("//table[@id='roleAssignedGroupsTable']//td[contains(text(), '" + aGs[k] + "')]")).getText());
					result = true;
					log.info(" Assigned Grups "+aGs[k]+" exists");
				}
			}
			
		} catch (Exception e) {
			log.info("checkAssignedgroupsAdded not exists");
			throw (e);
		}
		return result;
	}
	public PlatformRolePage deleteAddedAssignGroup(String assignedGroups) throws Exception {
		String message = "";
		try {
			String[] aGs=assignedGroups.split(";");
			for(int i=0;i<aGs.length;i++){
				List<WebElement> rows = driver.findElements(By.xpath("//*[@id='roleAssignedGroupsTable']/tbody/tr"));
				for(int k=0;k<=rows.size();k++){
					List<WebElement> cols = driver.findElements(By.xpath("//*[@id='roleAssignedGroupsTable']/tbody/tr["+k+"]/td"));
					log.info("deleteAddedAssignGroup Groups Role size is " + cols.size());
					if(cols.size()>0){
						if(cols.get(0).getText().equalsIgnoreCase(aGs[i])){
							log.info("deleteAddedAssignGroup success Text:"+cols.get(0).getText());
							WebElement selecteduser = driver.findElement(By.xpath("//*[@id='roleAssignedGroupsTable']/tbody/tr["+k+"]/td[3]/a"));
							((JavascriptExecutor)driver).executeScript("arguments[0].click()", selecteduser);
							//selecteduser.click();
							Util.delay(3000);
							deleteAssignedGroup.click();
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			log.info("deleteAddedAssignGroup button not fould");
			throw (e);
		}
		return this;
	}
	
	public PlatformRolePage deleteSelectedAssignGroup() throws Exception {
		try {
			Util.delay(5000);
			if(Util.isElementDisplayed(deleteAssignedGroup)){
				deleteAssignedGroup.click();
				log.info("Assigned Group Delete Dialog button clicked");
			}else{
				log.info("Assigned Group Delete Dialog button not found");
				new CustomException ("Assigned Group Delete Dialog button not found");
			}
		} catch (Exception e) {
			log.info("Assigned Group Delete button not found");
			throw (e);
		}
		return this;
	}
	
	public Boolean checkAssignedGroupsDelete(String assignedGroups) throws Exception {
		boolean result = false;
		try {
			String[] aGs=assignedGroups.split(";");
			for(int k=0;k<aGs.length;k++){
				if (driver.findElements(By.xpath("//table[@id='roleAssignedGroupsTable']//td[contains(text(), '" + aGs[k] + "')]"))
						.size() <= 0) {
					//log.info(driver.findElement(By.xpath("//table[@id='roleAssignedGroupsTable']//td[contains(text(), '" + aGs[k] + "')]")).getText());
					result = true;
					log.info(" Assigned Grups "+aGs[k]+" not exists");
				}
			}
			
		} catch (Exception e) {
			log.info("checkAssignedgroupsDeleted not exists");
			throw (e);
		}
		return result;
	}
	/** Platform Role Assigned Access **/
	public PlatformRolePage editPRoleAssginedAccess(String rN) throws Exception {
		try {
			if (checkPlatFormRoleAdded(rN)) {
				List<WebElement> rows = driver.findElements(By.xpath("//*[@id='roleBody']/tr"));
				for(int k=0;k<=rows.size();k++){
					List<WebElement> cols = driver.findElements(By.xpath("//*[@id='roleBody']/tr["+k+"]/td"));
					log.info("Assigned Access Role size is " + cols.size());
					if(cols.size()>0){
						if(cols.get(1).getText().equalsIgnoreCase(rN)){
							log.info("Assigned Access success Text:"+cols.get(1).getText());
							WebElement selecteduser = driver.findElement(By.xpath("//*[@id='roleBody']/tr["+k+"]/td[1]/input"));
							selecteduser.click();
							Util.delay(100);
							pRassginedAccessTab.click();
							Util.delay(3000);
							pRassginedAccessTab.click();
							break;
						}
					}
				}
				/*driver.findElement(By.xpath("//table[@id='roleTable']//td[contains(text(),"+rN+")]/preceding-sibling::td/input[@name='selectRole']")).click();
				Util.delay(100);
				pRassginedGroupsTab.click();
				Util.delay(3000);
				pRassginedGroupsTab.click();
				log.info("Assigned Access form opened");*/
				//Util.delay(2000);
			} else {
				log.info("Assigned Access Role " + rN + " not found");
			}
		} catch (Exception e) {
			log.info("Assigned Access role edit not found");
			throw (e);
		}
		return this;
	}
	public PlatformRolePage clickAssignedAccessUpdateBtn() throws Exception {
		try {
			Util.delay(5000);
			if(Util.isElementDisplayed(updateAssignedAccessBtn)){
				updateAssignedAccessBtn.click();
				log.info("Assigned Access Update Dialog button clicked");
			}else{
				log.info("Assigned Access Update Dialog button not found");
				new CustomException ("Assigned Access Update Dialog button not found");
			}
		} catch (Exception e) {
			log.info("Assigned Access Update button not found");
			throw (e);
		}
		return this;
	}
	public PlatformRolePage addNewAssignedAccess(String assignedAccess,String assignedAccessID) throws Exception {
		try {
			String[] aAs=assignedAccess.split(";");
			String[] aAsID=assignedAccessID.split(";");
			for(int k=0;k<aAsID.length;k++){
				//log.info("new assigned Access::::"+aAs[k]);
				driver.findElement(By.xpath("//input[@id='parentPermission_"+aAsID[k]+"']")).click();
				//log.info("Dynamic Value:::"+driver.findElement(By.xpath("//input[@id='availableAccess_"+aAsID[k]+"']")).getText());
			}
			log.info("Added new assign Access");
		} catch (Exception e) {
			log.info("Added new assign Access not found");
			throw (e);
		}
		return this;
	}
	public PlatformRolePage saveNewAssignedAccess() throws Exception {
		String message = "";
		try {
			updateAssignedAccessSaveBtn.click();
			log.info("saveNewAssignedAccess button clicked");

			while (true) {
				if (!updateAssignedAccessSaveBtn.isDisplayed()) {
					break;
				}
				Util.delay(5000);
				if (alertPopupModal.isDisplayed()) {
					message = alertMessage.getText();
					// System.out.println(message);
					log.info(message);
					alertPopupOkBtn.click();
					throw new CustomException(message);
				}
			}
		} catch (Exception e) {
			log.info("saveNewAssignedAccess button not fould");
			throw (e);
		}
		return this;
	}
	public Boolean checkAssignedAccessUpdated(String assignedAccess) throws Exception {
		boolean result = false;
		try {
			String[] aAs=assignedAccess.split(";");
			for(int k=0;k<aAs.length;k++){
				if (driver.findElements(By.xpath("//table[@id='roleAssignedAccessTable']//td[contains(text(), '" + aAs[k] + "')]"))
						.size() > 0) {
					log.info(driver.findElement(By.xpath("//table[@id='roleAssignedAccessTable']//td[contains(text(), '" + aAs[k] + "')]")).getText());
					result = true;
					log.info(" Assigned Access "+aAs[k]+" exists");
				}
			}
			
		} catch (Exception e) {
			log.info("checkAssignedAccessUpdated not exists");
			throw (e);
		}
		return result;
	}
}
