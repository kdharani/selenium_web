package com.appsfreedom.fm.pages;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.appsfreedom.utils.SQLConnector;
import com.appsfreedom.utils.Util;

public class PlatformUserPage extends BasePage {

	private HomePage hmePg;
	Logger log = Logger.getLogger("devpinoyLogger");

	@FindBy(xpath = "//div[@id='alertPopupModal']")
	WebElement customAlertWindow;
	@FindBy(xpath = "//div[@id='alertPopupModal']//div[@id='alertMessage']")
	WebElement customAlertMsg;
	@FindBy(xpath = "//div[@id='alertPopupModal']//a[contains(text(),'Ok')]")
	WebElement customAlertOkBtn;
	@FindBy(xpath = "//*[@id='platformuser']/table/tbody/tr[4]/td/div/table/tbody/tr/td/table/tbody/tr/td/a[1]")
	WebElement createUserBtn;
	@FindBy(xpath = "//*[@id='platformuser']/table/tbody/tr[4]/td/div/table/tbody/tr/td/table/tbody/tr/td/a[2]")
	WebElement copyUserBtn;
	@FindBy(xpath = "//*[@id='platformuser']/table/tbody/tr[4]/td/div/table/tbody/tr/td/table/tbody/tr/td/a[3]")
	WebElement lockUserBtn;
	@FindBy(xpath = "//*[@id='platformuser']/table/tbody/tr[4]/td/div/table/tbody/tr/td/table/tbody/tr/td/a[4]")
	WebElement unlockUserBtn;
	@FindBy(xpath = "//*[@id='platformuser']/table/tbody/tr[4]/td/div/table/tbody/tr/td/table/tbody/tr/td/a[5]")
	WebElement changePwdBtn;
	@FindBy(xpath = "//*[@id='userForm']")
	WebElement createusrForm;
	@FindBy(xpath = "//*[@id='mainpulockbody']")
	WebElement lockusrForm;
	@FindBy(xpath = "//*[@id='mainpuunlockbody']")
	WebElement unlockform;
	@FindBy(xpath = "//*[@id='changePWForm']")
	WebElement changePwdForm;
	@FindBy(xpath = "//*[@id='platformuser_generalinfo']")
	WebElement pfusrGenInfodiv;
	@FindBy(xpath = "//*[@id='assignedroles']")
	WebElement pfAssignedRolesdiv;
	@FindBy(xpath = "//*[@id='userAssignedRoleForm']")
	WebElement pfAddAssignRoleFrm;
	@FindBy(xpath = "//*[@id='deleteAssignedRoleForm']")
	WebElement pfRemoveAssignedRoleFrm;
	@FindBy(xpath = "//*[@id='alertMessage']")
	WebElement subscribedAppsEmptyForm;
	@FindBy(xpath = "//*[@id='addUserMappingForm']")
	WebElement addUserMappingForm;
	@FindBy(xpath = "//*[@id='assignAppsPFUserForm']")
	WebElement subscribedAppsAddForm;
	@FindBy(xpath = "//*[@id='userAssignedAppsDeleteForm']")
	WebElement userAssignedAppsDeleteForm;
	@FindBy(xpath = "//*[@id='deletePFUserMappingForm']")
	WebElement deletePFUserMappingForm;

	@FindBy(xpath = "//*[@id='firstName']")
	WebElement firstNametxtbx;
	@FindBy(xpath = "//*[@id='lastName']")
	WebElement lastNametxtbx;
	@FindBy(xpath = "//*[@id='email']")
	WebElement emailtxtbx;
	@FindBy(xpath = "//*[@id='loginName']")
	WebElement logintxtbx;
	@FindBy(xpath = "//*[@id='title']")
	WebElement titletxtbx;
	@FindBy(xpath = "//*[@id='password']")
	WebElement passwordtxtbx;
	@FindBy(xpath = "//*[@id='updateOrCreateUser']")
	WebElement updateOrCreateUserbtn;
	@FindBy(xpath = "//form[@id='userForm']//a[contains(text(),'Cancel')]")
	WebElement pUserCreateCancelBtn;
	@FindBy(xpath = "//*[@id='userBody']/tr[1]/td[1]/input")
	WebElement selectAUser;
	@FindBy(xpath = "//*[@id='mainpulockfooter']/a[1]")
	WebElement locksubmitbtn;
	@FindBy(xpath = "//*[@id='mainpuunlockfooter']/a[1]")
	WebElement unlocksubmitbtn;
	@FindBy(xpath = "//*[@id='passwordCP']")
	WebElement changepasswordtxtbx;
	@FindBy(xpath = "//*[@id='changePWForm']/div[2]/a[1]")
	WebElement chagePwdSubtbtn;
	@FindBy(xpath = "//*[@id='platformuser_generalinfo']/div/p/a")
	WebElement editGenInfoBtn;
	@FindBy(xpath = "//*[@id='contents']/ul/li[2]/a")
	WebElement assignedRolesTab;
	@FindBy(xpath = "//*[@id='contents']/ul/li[3]/a")
	WebElement subscribedAppsTab;
	@FindBy(xpath = "//*[@id='contents']/ul/li[6]/a")
	WebElement userMappingTab;
	@FindBy(xpath = "//*[@id='assignedroles']/div[1]/p/a")
	WebElement addAssignedRolesBtn;
	@FindBy(xpath = "//*[@id='userAssignedRoleForm']/div[2]/a[1]")
	WebElement saveAssignedRolebtn;
	@FindBy(xpath = "//*[@id='userTable']/preceding::i[@class='icon-white icon-search']")
	WebElement searchBtn;
	@FindBy(xpath = "//*[@id='userTable']//input[@data-column='loginid']")
	WebElement userColumn;

	public PlatformUserPage() {

		super();
		hmePg = new HomePage();
	}

	public HomePage getHmePage() {
		return hmePg;
	}

	public boolean isUserExists(String name) throws Exception {
		boolean exists = false;
		try {
			searchBtn.click();
			log.info("Search button clicked");
			userColumn.sendKeys(name);
			log.info("User name entered");
			if (driver.findElements(By.xpath("//*[@id='userBody']//td[text()='" + name + "']")).size() == 1) {
				exists = true;
				log.info("User " + name + " exists");
			}
		} catch (Exception e) {
			log.info("Unable to check user");
			throw (e);
		}
		return exists;
	}

	public PlatformUserPage selectUser(String name) throws Exception {
		String xpath = "//*[@id='userBody']//a[text()='" + name + "']";
		try {
			if (isUserExists(name)) {
				// ((JavascriptExecutor) driver).executeScript("window.confirm =
				// function(msg) { return true; }");
				driver.findElement(By
						.xpath("//*[@id='userBody']//td[text()='" + name + "']/preceding::input[@name='selectRole']"))
						.click();
				log.info("User selected");
			} else
				log.info("User " + name + " not found");
		} catch (Exception e) {
			throw (e);
		}

		return new PlatformUserPage();
	}

	public PlatformUserPage clickOnCreateUser(String loginid, String emailaddress, String salutation, String firstname,
			String lastname, String title, String pwd, String tenantId) throws Exception {

		try {
			createUserBtn.click();
			log.info("Create User button clicked");
			if ((Util.isElementDisplayed(createusrForm))) {
				log.info("Create user Form loaded succussfully");

				setSalutation(salutation);
				setFirstName(firstname);
				setLastName(lastname);
				setTitle(salutation);
				setLoginName(loginid);
				setEmail(emailaddress);
				setPassword(pwd);

				updateOrCreateUserbtn.click();
				Util.delay(1000);
				if (customAlertWindow.isDisplayed()) {
					log.info(customAlertMsg.getText());
					customAlertOkBtn.click();
					pUserCreateCancelBtn.click();
				}
			} else {
				log.info("Create user Form not loaded");
			}

		} catch (Exception e) {
			log.error("Create User button does not exist");
			throw (e);
		}

		return new PlatformUserPage();
	}

	public boolean isUserCreated(String tenantId, String loginid, String emailaddress) throws Exception {
		boolean created = false;
		try {
			Map<String, String> userdetails = SQLConnector.getuserDetails(tenantId, loginid, emailaddress);
			if (userdetails.size() > 0) {
				created = true;
				log.info("platform User created successfully");
			}

		} catch (Exception e) {
			log.error("create User button does not exist");
			throw (e);
		}
		return created;
	}

	public PlatformUserPage clickOnCopyUser(String loginid, String emailaddress, String salutation, String firstname,
			String lastname, String title, String pwd, String tenantId) throws Exception {
		try {

			/*
			 * List<WebElement> rows =
			 * driver.findElements(By.xpath("//*[@id='userBody']/tr")); for(int
			 * k=0;k<=rows.size();k++){ List<WebElement> cols =
			 * driver.findElements(By.xpath("//*[@id='userBody']/tr["+k+"]/td"))
			 * ; log.info("Platform copy user col size is " + cols.size());
			 * if(cols.size()>0){
			 * if(cols.get(1).getText().equalsIgnoreCase(firstname) &&
			 * cols.get(2).getText().equalsIgnoreCase(lastname) &&
			 * cols.get(3).getText().equalsIgnoreCase(loginid) &&
			 * cols.get(4).getText().equalsIgnoreCase(emailaddress)){ WebElement
			 * selecteduser =
			 * driver.findElement(By.xpath("//*[@id='userBody']/tr["+k+
			 * "]/td[1]/input")); selecteduser.click(); log.info(
			 * "Platform users selected for copy."); copyUserBtn.click(); break;
			 * } }
			 */
			selectUser(loginid);
			copyUserBtn.click();
			log.info("Clicked copy button");

			if ((Util.isElementDisplayed(createusrForm))) {

				setSalutation(salutation);
				setFirstName(firstname);
				setLastName(lastname);
				setTitle(title);
				setLoginName(loginid);
				setEmail(emailaddress);
				setPassword(pwd);

				updateOrCreateUserbtn.click();
				Util.delay(1000);
				if (customAlertWindow.isDisplayed()) {
					log.info(customAlertMsg.getText());
					customAlertOkBtn.click();
					pUserCreateCancelBtn.click();
				}

			} else {
				log.info("Copy user Form not loaded");
			}
		} catch (Exception e) {
			log.error("Copy User button does not exist");
			throw (e);
		}
		return new PlatformUserPage();
	}

	public boolean isCopied(String tenantId, String loginid, String emailaddress) throws Exception {
		boolean copied = false;
		try {

			Map<String, String> userdetails = SQLConnector.getuserDetails(tenantId, loginid, emailaddress);
			if (userdetails.size() > 0) {
				copied = true;
				log.info("platform User Copy successfully");
			}

		} catch (Exception e) {
			log.error("copy User button does not exist");
			throw (e);
		}
		return copied;
	}

	public PlatformUserPage clickOnLockUser(String loginid, String emailaddress, String firstname, String lastname,
			String tenantId) throws Exception {
		try {
			/*// selectAUser.click();

			List<WebElement> rows = driver.findElements(By.xpath("//*[@id='userBody']/tr"));
			for (int k = 0; k <= rows.size(); k++) {
				List<WebElement> cols = driver.findElements(By.xpath("//*[@id='userBody']/tr[" + k + "]/td"));
				log.info("Platform Lock user col size is " + cols.size());
				if (cols.size() > 0) {
					if (cols.get(1).getText().equalsIgnoreCase(firstname)
							&& cols.get(2).getText().equalsIgnoreCase(lastname)
							&& cols.get(3).getText().equalsIgnoreCase(loginid)
							&& cols.get(4).getText().equalsIgnoreCase(emailaddress)) {
						WebElement selecteduser = driver
								.findElement(By.xpath("//*[@id='userBody']/tr[" + k + "]/td[1]/input"));
						selecteduser.click();
						log.info("Platform users selected for Locking.");
						lockUserBtn.click();
						break;
					}
				}
			}*/
			selectUser(loginid);
			lockUserBtn.click();
			log.info("Clicked lock user button");
			// lockUserBtn.click();
			if ((Util.isElementDisplayed(lockusrForm))) {
				locksubmitbtn.click();
				if ((Util.isAlertPresent())) {
					// Check if alert exists
					BufferedImage image = new Robot()
							.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
					ImageIO.write(image, "png",
							new File(System.getProperty("user.dir") + "\\screenshots\\" + "Lock User" + ".png"));
					Util.closeAlert();
				}

			}
		} catch (Exception e) {
			log.error("Lock User button does not exist");
			throw (e);
		}
		return new PlatformUserPage();
	}

	public boolean isLocked(String tenantId, String loginid, String emailaddress) throws Exception {
		boolean locked = false;
		try {
			Map<String, String> userdetails = SQLConnector.getuserDetails(tenantId, loginid, emailaddress);
			if (userdetails.size() > 0) {
				boolean isActive = Boolean.parseBoolean(userdetails.get("isActive"));
				if (isActive) {
					locked = false;
					log.info("platform User Lock failed");
				} else {
					locked = true;
					log.info("platform User Locked successfully");
				}
			} else {
				log.info("platform User Lock failed. User does not exist.");
			}

		} catch (Exception e) {
			log.error("Lock User button does not exist");
			throw (e);
		}
		return locked;
	}

	public PlatformUserPage clickOnUnlockUser(String loginid, String emailaddress, String firstname, String lastname,
			String tenantId) throws Exception {
		try {
			/*// selectAUser.click();

			List<WebElement> rows = driver.findElements(By.xpath("//*[@id='userBody']/tr"));
			for (int k = 0; k <= rows.size(); k++) {
				List<WebElement> cols = driver.findElements(By.xpath("//*[@id='userBody']/tr[" + k + "]/td"));
				log.info("Platform Unlock user col size is " + cols.size());
				if (cols.size() > 0) {
					if (cols.get(1).getText().equalsIgnoreCase(firstname)
							&& cols.get(2).getText().equalsIgnoreCase(lastname)
							&& cols.get(3).getText().equalsIgnoreCase(loginid)
							&& cols.get(4).getText().equalsIgnoreCase(emailaddress)) {
						WebElement selecteduser = driver
								.findElement(By.xpath("//*[@id='userBody']/tr[" + k + "]/td[1]/input"));
						selecteduser.click();
						log.info("Platform users selected for Unlocking.");
						unlockUserBtn.click();
						break;
					}
				}
			}*/
			selectUser(loginid);
			unlockUserBtn.click();
			log.info("Clicked unlock user button");
			// lockUserBtn.click();
			if ((Util.isElementDisplayed(unlockform))) {
				unlocksubmitbtn.click();
				if ((Util.isAlertPresent())) {
					// Check if alert exists
					BufferedImage image = new Robot()
							.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
					ImageIO.write(image, "png",
							new File(System.getProperty("user.dir") + "\\screenshots\\" + "UnLock User" + ".png"));
					Util.closeAlert();
				}

			}
		} catch (Exception e) {
			log.error("UnLock User button does not exist");
			throw (e);
		}
		return new PlatformUserPage();
	}

	public boolean isUnlocked(String tenantId, String loginid, String emailaddress) throws Exception {
		boolean unlocked = false;
		try {

			Map<String, String> userdetails = SQLConnector.getuserDetails(tenantId, loginid, emailaddress);
			if (userdetails.size() > 0) {
				boolean isActive = Boolean.parseBoolean(userdetails.get("isActive"));
				if (isActive) {
					unlocked = true;
					log.info("platform User UnLocked successfully");
				}
			} else {
				log.info("platform User UnLock failed. User does not exist.");
			}
		} catch (Exception e) {
			log.error("Unlock button does not exist");
			throw (e);
		}
		return unlocked;
	}

	public PlatformUserPage clickOnChangePwd(String loginid, String emailaddress, String firstname, String lastname,
			String pwd, String tenantId) throws Exception {
		try {
			/*// selectAUser.click();

			List<WebElement> rows = driver.findElements(By.xpath("//*[@id='userBody']/tr"));
			for (int k = 0; k <= rows.size(); k++) {
				List<WebElement> cols = driver.findElements(By.xpath("//*[@id='userBody']/tr[" + k + "]/td"));
				log.info("Platform change password col size is " + cols.size());
				if (cols.size() > 0) {
					if (cols.get(1).getText().equalsIgnoreCase(firstname)
							&& cols.get(2).getText().equalsIgnoreCase(lastname)
							&& cols.get(3).getText().equalsIgnoreCase(loginid)
							&& cols.get(4).getText().equalsIgnoreCase(emailaddress)) {
						WebElement selecteduser = driver
								.findElement(By.xpath("//*[@id='userBody']/tr[" + k + "]/td[1]/input"));
						selecteduser.click();
						log.info("Platform users selected for change password.");
						changePwdBtn.click();
						break;
					}
				}
			}*/
			selectUser(loginid);
			changePwdBtn.click();
			log.info("Clicked change password button");
			// lockUserBtn.click();
			if ((Util.isElementDisplayed(changePwdForm))) {
				setChangePassword(pwd);
				chagePwdSubtbtn.click();
				if ((Util.isAlertPresent())) {
					// Check if alert exists
					BufferedImage image = new Robot()
							.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
					ImageIO.write(image, "png",
							new File(System.getProperty("user.dir") + "\\screenshots\\" + "UnLock User" + ".png"));
					Util.closeAlert();
				}

			}
		} catch (Exception e) {
			log.error("Change password button does not exist");
			throw (e);
		}
		return new PlatformUserPage();
	}

	public boolean ispwdchanged(String tenantId, String loginid, String emailaddress, String password)
			throws Exception {
		boolean ischanged = false;
		try {

			Map<String, String> userdetails = SQLConnector.getuserDetails(tenantId, loginid, emailaddress);
			if (userdetails.size() > 0) {
				String pwd = userdetails.get("password");
				if (pwd.equalsIgnoreCase(password)) {
					ischanged = true;
					log.info("platform User password changed successfully");
				}
			} else {
				log.info("platform User password change failed. User does not exist.");
			}

		} catch (Exception e) {
			log.error("Password change button does not exist");
			throw (e);
		}
		return ischanged;
	}

	public PlatformUserPage editGeneralInfo(String loginid, String emailaddress, String salutation, String firstname,
			String lastname, String title, String pwd, String tenantId) throws Exception {
		try {
			/*// selectAUser.click();

			List<WebElement> rows = driver.findElements(By.xpath("//*[@id='userBody']/tr"));
			for (int k = 0; k <= rows.size(); k++) {
				List<WebElement> cols = driver.findElements(By.xpath("//*[@id='userBody']/tr[" + k + "]/td"));
				log.info("Platform general info col size is " + cols.size());
				if (cols.size() > 0) {
					if (cols.get(3).getText().equalsIgnoreCase(loginid)
							&& cols.get(4).getText().equalsIgnoreCase(emailaddress)) {
						WebElement selecteduser = driver
								.findElement(By.xpath("//*[@id='userBody']/tr[" + k + "]/td[1]/input"));
						selecteduser.click();
						log.info("Platform users selected for Editing general info.");
						break;
					}
				}
			}*/
			selectUser(loginid);
			log.info("Platform users selected for Editing general info.");
			
			// lockUserBtn.click();
			if ((Util.isElementDisplayed(pfusrGenInfodiv))) {
				// editGenInfoBtn.click();
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click()", editGenInfoBtn);

				if ((Util.isElementDisplayed(createusrForm))) {
					log.info("Edit user Form loaded succussfully");
					setSalutation(salutation);
					setFirstName(firstname);
					setLastName(lastname);
					setTitle(title);
					setLoginName(loginid);
					setEmail(emailaddress);
					setPassword(pwd);

					updateOrCreateUserbtn.click();
					if ((Util.isAlertPresent())) {
						// Check if alert exists
						BufferedImage image = new Robot()
								.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
						ImageIO.write(image, "png",
								new File(System.getProperty("user.dir") + "\\screenshots\\" + "Create User" + ".png"));
						Util.closeAlert();
					}
				} else {
					log.info("Edit user Form not loaded");
				}

			}
		} catch (Exception e) {
			log.error("Edit general info button does not exist");
			throw (e);
		}
		return new PlatformUserPage();
	}

	public boolean isGenInfoEdited(String tenantId, String loginid, String emailaddress) throws Exception {
		boolean iseditied = false;
		Map<String, String> userdetails = SQLConnector.getuserDetails(tenantId, loginid, emailaddress);
		if (userdetails.size() > 0) {
			iseditied = true;
			log.info("platform User Edited successfully");
		}
		return iseditied;
	}

	public PlatformUserPage assignRoles(String loginid, String emailaddress, String assignedrole, String tenantId)
			throws Exception {
		try {
			// selectAUser.click();

			List<String> assignedrolevalues = null;
			/*List<WebElement> rows = driver.findElements(By.xpath("//*[@id='userBody']/tr"));
			for (int k = 0; k <= rows.size(); k++) {
				List<WebElement> cols = driver.findElements(By.xpath("//*[@id='userBody']/tr[" + k + "]/td"));
				log.info("Platform assign roles col size is " + cols.size());
				if (cols.size() > 0) {
					if (cols.get(3).getText().equalsIgnoreCase(loginid)
							&& cols.get(4).getText().equalsIgnoreCase(emailaddress)) {
						WebElement selecteduser = driver
								.findElement(By.xpath("//*[@id='userBody']/tr[" + k + "]/td[1]/input"));
						selecteduser.click();
						log.info("Platform users selected for assigning roles.");
						break;
					}
				}
			}*/
			selectUser(loginid);
			log.info("Platform users selected for assigning roles.");
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView()", assignedRolesTab);
			assignedRolesTab.click();
			if ((Util.isElementDisplayed(pfAssignedRolesdiv))) {
				addAssignedRolesBtn.click();
				if ((Util.isElementDisplayed(pfAddAssignRoleFrm))) {
					log.info("Assigned roles add Form loaded succussfully");
					if (assignedrole != null) {
						assignedrolevalues = new ArrayList<String>(Arrays.asList(assignedrole.split(",")));
						List<WebElement> selected_roleids = setAssignedRole(assignedrolevalues);
						if (selected_roleids != null) {
							saveAssignedRolebtn.click();
							log.info("sve btn clicked");
							if ((Util.isAlertPresent())) {
								// Check if alert exists
								BufferedImage image = new Robot().createScreenCapture(
										new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
								ImageIO.write(image, "png", new File(System.getProperty("user.dir") + "\\screenshots\\"
										+ "Add Assigned Role" + ".png"));
								Util.closeAlert();
							}
						} else {
							log.info("No roles selected");
						}
					} else {
						log.info("Assigned roles input null;");
					}
				} else {
					log.info("Edit user Form not loaded");
				}

			}
		} catch (Exception e) {
			log.error("Edit general info button does not exist");
			throw (e);
		}
		return new PlatformUserPage();
	}

	public boolean isRolesAssigned(String assignedrole) throws Exception {
		boolean isrolesassigned = false;
		try {
			List<String> assignedrolevalues = new ArrayList<String>(Arrays.asList(assignedrole.split(",")));
			int inputrolessize = assignedrolevalues.size();
			List<WebElement> assignedrows = driver.findElements(By.xpath("//*[@id='userAssignedRolesTable']/tbody/tr"));
			List<String> updatedroles = new ArrayList<String>();
			for (int k = 0; k <= assignedrows.size(); k++) {
				List<WebElement> assignedcols = driver
						.findElements(By.xpath("//*[@id='userAssignedRolesTable']/tbody/tr[" + k + "]/td"));
				log.info("Platform assign roles col size is " + assignedcols.size());
				if (assignedcols.size() > 0) {
					log.info("Platform assign roles are:" + assignedcols.get(0).getText());
					updatedroles.add(assignedcols.get(0).getText());

				}
			}
			assignedrolevalues.retainAll(updatedroles);
			if (inputrolessize == assignedrolevalues.size()) {
				isrolesassigned = true;
				log.info("Roles added successfully");
			}

		} catch (Exception e) {
			isrolesassigned = false;
			log.error("assign roles button does not exist");
			throw (e);
		}
		return isrolesassigned;
	}

	public PlatformUserPage deleteRoles(String loginid, String emailaddress, String removerole, String tenantId)
			throws Exception {
		try {
			// selectAUser.click();

			/*List<WebElement> rows = driver.findElements(By.xpath("//*[@id='userBody']/tr"));
			for (int k = 0; k <= rows.size(); k++) {
				List<WebElement> cols = driver.findElements(By.xpath("//*[@id='userBody']/tr[" + k + "]/td"));
				log.info("Platform delete roles col size is " + cols.size());
				if (cols.size() > 0) {
					if (cols.get(3).getText().equalsIgnoreCase(loginid)
							&& cols.get(4).getText().equalsIgnoreCase(emailaddress)) {
						WebElement selecteduser = driver
								.findElement(By.xpath("//*[@id='userBody']/tr[" + k + "]/td[1]/input"));
						selecteduser.click();
						log.info("Platform users selected for deleting roles.");
						break;
					}
				}
			}*/
			selectUser(loginid);
			log.info("Platform users selected for deleting roles.");
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView()", assignedRolesTab);
			assignedRolesTab.click();
			if ((Util.isElementDisplayed(pfAssignedRolesdiv))) {
				if (removerole != null) {
					WebElement removeRolebtn = null;
					List<WebElement> assignedrows = driver
							.findElements(By.xpath("//*[@id='userAssignedRolesTable']/tbody/tr"));
					for (int k = 0; k <= assignedrows.size(); k++) {
						List<WebElement> assignedcols = driver
								.findElements(By.xpath("//*[@id='userAssignedRolesTable']/tbody/tr[" + k + "]/td"));
						log.info("Platform delete roles col size is " + assignedcols.size());
						if (assignedcols.size() > 0) {
							log.info("Platform delete roles are:" + assignedcols.get(0).getText());
							if (removerole.equalsIgnoreCase(assignedcols.get(0).getText())) {
								removeRolebtn = driver.findElement(
										By.xpath("//*[@id='userAssignedRolesTable']/tbody/tr[" + k + "]/td[4]/a/img"));
								log.info("Platform  roles selected to delete:" + assignedcols.get(0).getText());
								break;
							}
						}
					}
					if (removeRolebtn != null) {
						removeRolebtn.click();
						log.info("delete btn clicked");
						if ((Util.isAlertPresent())) {
							log.info("delete alert popsup");
							// Check if alert exists
							BufferedImage image = new Robot()
									.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
							ImageIO.write(image, "png", new File(System.getProperty("user.dir") + "\\screenshots\\"
									+ "Remove Assigned Role" + ".png"));
							Util.closeAlert();

						} else {
							log.info("no alert");
							if ((Util.isElementDisplayed(pfRemoveAssignedRoleFrm))) {
								WebElement confirmbtn = driver
										.findElement(By.xpath("//*[@id='deleteAssignedRoleForm']/div[2]/a[1]"));// *[@id="deleteAssignedRoleForm"]/div[2]/a[1]
								if (confirmbtn != null) {
									log.info("delete confirm btn is not null");
									confirmbtn.click();
									log.info("delete btn clicked confirm");

								} else {
									log.info("delete confirm btn is null");
								}
							} else {
								log.info("No delete confirmation form");
							}
						}
					} else {
						log.info("Roles not available to delete.");
					}
				} else {
					log.info("remove roles input null;");
				}

			}
		} catch (Exception e) {
			log.error("delete assigned roles button does not exist");
			throw (e);
		}
		return new PlatformUserPage();
	}

	public boolean isRoleDeleted(String removerole) throws Exception {
		boolean isroledeleted = true;
		try {
			List<WebElement> assignedrows1 = driver
					.findElements(By.xpath("//*[@id='userAssignedRolesTable']/tbody/tr"));
			boolean roledeleted = true;
			for (int j = 0; j <= assignedrows1.size(); j++) {
				List<WebElement> assignedcols1 = driver
						.findElements(By.xpath("//*[@id='userAssignedRolesTable']/tbody/tr[" + j + "]/td"));
				log.info("Platform delete roles col size is " + assignedcols1.size());
				if (assignedcols1.size() > 0) {

					if (removerole.equalsIgnoreCase(assignedcols1.get(0).getText())) {
						roledeleted = false;
						log.info("Platform delete roles are:" + assignedcols1.get(0).getText());
						break;
					}

				}
			}

			if (roledeleted) {
				log.info("Roles deleted successfully");
			} else {
				log.info("Roles not deleted successfully");
			}
		} catch (Exception e) {
			isroledeleted = false;
			log.info("delete role failed failed.");
			throw (e);
		}
		return isroledeleted;
	}

	public PlatformUserPage subscribedAppsAdd(String loginid, String emailaddress, String addapp, String tenantId)
			throws Exception {
		try {
			/*// selectAUser.click();

			List<WebElement> rows = driver.findElements(By.xpath("//*[@id='userBody']/tr"));
			for (int k = 0; k <= rows.size(); k++) {
				List<WebElement> cols = driver.findElements(By.xpath("//*[@id='userBody']/tr[" + k + "]/td"));
				log.info("Platform subscribed apps col size is " + cols.size());
				if (cols.size() > 0) {
					if (cols.get(3).getText().equalsIgnoreCase(loginid)
							&& cols.get(4).getText().equalsIgnoreCase(emailaddress)) {
						WebElement selecteduser = driver
								.findElement(By.xpath("//*[@id='userBody']/tr[" + k + "]/td[1]/input"));
						selecteduser.click();
						log.info("Platform users selected for subscribed apps.");
						break;
					}
				}
			}*/
			selectUser(loginid);
			log.info("Platform users selected for subscribed apps.");
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView()", subscribedAppsTab);
			subscribedAppsTab.click();
			WebElement okbtn = null;
			if (subscribedAppsEmptyForm.isDisplayed()) {
				log.info("subscribed app list empty alert");
				okbtn = driver.findElement(By.xpath("//*[@id='alertPopupModal']/div[3]/a"));
			}
			if (okbtn != null) {
				log.info("subscribed app list empty alert okbtn clicked");
				okbtn.click();

			}
			WebElement addbtn = driver.findElement(By.xpath("//*[@id='assignedapps']/div[1]/p/a"));
			if (addbtn != null) {
				addbtn.click();
				Util.delay(2000);

				if (subscribedAppsEmptyForm.isDisplayed()) {
					log.info("No More apps can be assigned to the user");
					okbtn = driver.findElement(By.xpath("//*[@id='alertPopupModal']/div[3]/a"));
					if (okbtn != null) {
						okbtn.click();
					}
				} else if (subscribedAppsAddForm.isDisplayed()) {
					log.info("Select an app to add from the list");
					// WebElement appselect =
					// driver.findElement(By.xpath("//*[@id='availablePFApps']"));
					Select dropdown = new Select(driver.findElement(By.id("availablePFApps")));
					if (dropdown.getOptions().size() > 0) {
						List<WebElement> options = dropdown.getOptions();
						for (WebElement we : options) {
							log.info("option:" + we.getText());
							if (addapp != null && addapp.equalsIgnoreCase(we.getText())) {
								dropdown.selectByVisibleText(addapp);
								log.info("App selected :" + addapp);
								WebElement savebtn = driver
										.findElement(By.xpath("//*[@id='assignAppsPFUserForm']/div[2]/a[1]"));
								savebtn.click();
								// *[@id="assignedAppsTable"]/tbody/tr[1]
								break;
							}
						}
					}

				}
			}
		} catch (Exception e) {
			log.error("subscribed apps button does not exist");
			throw (e);
		}
		return new PlatformUserPage();

	}

	public boolean isAppAdded(String addapp) throws Exception {
		boolean isAdded = false;
		try {
			List<WebElement> appsrows = driver.findElements(By.xpath("//*[@id='assignedAppsTable']/tbody/tr"));
			for (int k = 0; k <= appsrows.size(); k++) {
				List<WebElement> appscols = driver
						.findElements(By.xpath("//*[@id='assignedAppsTable']/tbody/tr[" + k + "]/td"));
				log.info("Platform subscribed apps col size is " + appscols.size());
				if (appscols.size() > 0) {
					log.info("subscribed apps:" + appscols.get(0).getText() + " aaaa " + appscols.get(1).getText());
					if (appscols.get(0).getText().equalsIgnoreCase(addapp)
							|| appscols.get(1).getText().equalsIgnoreCase(addapp)) {
						log.info("New app added for the user");
						isAdded = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			log.info("Subscription App add failed.");
			throw (e);
		}
		return isAdded;
	}

	public PlatformUserPage subscribedAppsRemove(String loginid, String emailaddress, String removeapp, String tenantId)
			throws Exception {
		try {
			/*// selectAUser.click();

			List<WebElement> rows = driver.findElements(By.xpath("//*[@id='userBody']/tr"));
			for (int k = 0; k <= rows.size(); k++) {
				List<WebElement> cols = driver.findElements(By.xpath("//*[@id='userBody']/tr[" + k + "]/td"));
				log.info("Platform subscribed apps col size is " + cols.size());
				if (cols.size() > 0) {
					if (cols.get(3).getText().equalsIgnoreCase(loginid)
							&& cols.get(4).getText().equalsIgnoreCase(emailaddress)) {
						WebElement selecteduser = driver
								.findElement(By.xpath("//*[@id='userBody']/tr[" + k + "]/td[1]/input"));
						selecteduser.click();
						log.info("Platform users selected for subscribed apps.");
						break;
					}
				}
			}*/
			selectUser(loginid);
			log.info("Platform users selected for subscribed apps.");
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView()", subscribedAppsTab);
			subscribedAppsTab.click();

			List<WebElement> appsrows = driver.findElements(By.xpath("//*[@id='assignedAppsTable']/tbody/tr"));
			for (int k = 0; k <= appsrows.size(); k++) {
				List<WebElement> appscols = driver
						.findElements(By.xpath("//*[@id='assignedAppsTable']/tbody/tr[" + k + "]/td"));
				log.info("Platform subscribed apps col size is " + appscols.size());
				if (appscols.size() > 0) {
					log.info("subscribed apps remove from list:" + appscols.get(0).getText() + " == " + removeapp);
					if (appscols.get(0).getText().equalsIgnoreCase(removeapp)) {
						log.info("equal");
						// *[@id="assignedAppsTable"]/tbody/tr[8]/td[3]/a
						WebElement removebtn = driver
								.findElement(By.xpath("//*[@id='assignedAppsTable']/tbody/tr[" + k + "]/td[3]/a"));

						if (removebtn != null) {
							((JavascriptExecutor)driver).executeScript("arguments[0].click()", removebtn);
							//removebtn.click();
							log.info("removebtn clicked");
							if ((Util.isElementDisplayed(userAssignedAppsDeleteForm))) {
								WebElement confirmbtn = driver
										.findElement(By.xpath("//*[@id='userAssignedAppsDeleteForm']/div[2]/a[1]"));
								if (confirmbtn != null) {
									confirmbtn.click();
									log.info("App removed");
								} else {
									log.info("confirmbtn is null");
								}
							}
						} else {
							log.info("removebtn is null");
						}
						break;
					} else {
						log.info("not equal");
					}
				}
			}

		} catch (Exception e) {
			log.error("subscribed apps button does not exist");
			throw (e);
		}
		return new PlatformUserPage();

	}

	public boolean isAppRemoved(String removeapp) throws Exception {
		boolean removed = true;
		try {
			List<WebElement> appsrows = driver.findElements(By.xpath("//*[@id='assignedAppsTable']/tbody/tr"));
			for (int k = 0; k <= appsrows.size(); k++) {
				List<WebElement> appscols = driver
						.findElements(By.xpath("//*[@id='assignedAppsTable']/tbody/tr[" + k + "]/td"));
				log.info("Platform subscribed apps col size is " + appscols.size());
				if (appscols.size() > 0) {
					log.info("subscribed apps:" + appscols.get(0).getText() + " aaaa " + appscols.get(1).getText());
					if (appscols.get(0).getText().equalsIgnoreCase(removeapp)) {
						log.info("App not removed");
						removed = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			log.info("Subscription App add failed.");
			throw (e);
		}
		return removed;
	}

	public PlatformUserPage userMappingAdd(String loginid, String emailaddress, String usermappingrfc,
			String usermappinguserid, String tenantId) throws Exception {
		try {
			// selectAUser.click();

			/*List<WebElement> rows = driver.findElements(By.xpath("//*[@id='userBody']/tr"));
			for (int k = 0; k <= rows.size(); k++) {
				List<WebElement> cols = driver.findElements(By.xpath("//*[@id='userBody']/tr[" + k + "]/td"));
				log.info("Platform user mapping col size is " + cols.size());
				if (cols.size() > 0) {
					if (cols.get(3).getText().equalsIgnoreCase(loginid)
							&& cols.get(4).getText().equalsIgnoreCase(emailaddress)) {
						WebElement selecteduser = driver
								.findElement(By.xpath("//*[@id='userBody']/tr[" + k + "]/td[1]/input"));
						selecteduser.click();

						log.info("Platform users selected for user mapping.");
						break;
					}
				}
			}*/
			selectUser(loginid);
			log.info("Platform users selected for user mapping.");
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView()", userMappingTab);
			userMappingTab.click();

			WebElement addbtn = driver.findElement(By.xpath("//*[@id='usermapping']/div[1]/p/a"));
			if (addbtn != null) {
				addbtn.click();
				Util.delay(2000);

				if (addUserMappingForm.isDisplayed()) {
					log.info("Select an rfc to add from the list");
					// WebElement appselect =
					// driver.findElement(By.xpath("//*[@id='availablePFApps']"));
					// *[@id="connectionId"]
					Select dropdown = new Select(driver.findElement(By.id("connectionId")));
					if (dropdown.getOptions().size() > 0) {
						List<WebElement> options = dropdown.getOptions();
						for (WebElement we : options) {
							log.info("option:" + we.getText());
							if (usermappingrfc != null && usermappingrfc.equalsIgnoreCase(we.getText())) {
								dropdown.selectByVisibleText(usermappingrfc);
								log.info("RFC selected :" + usermappingrfc);
								// *[@id="ssUserId"]
								WebElement useridinput = driver.findElement(By.id("ssUserId"));
								useridinput.sendKeys(usermappinguserid);
								log.info("usermappinguserid set :" + usermappingrfc);
								// *[@id="addUserMappingForm"]/div[2]/a[1]
								WebElement savebtn = driver
										.findElement(By.xpath("//*[@id='addUserMappingForm']/div[2]/a[1]"));
								savebtn.click();
								log.info("new usermapping done");
								// *[@id="assignedAppsTable"]/tbody/tr[1]
								break;
							}
						}
					}

				}
			}
		} catch (Exception e) {
			log.error("User mapping Error");
			throw (e);
		}
		return new PlatformUserPage();

	}

	public boolean isUserMappingDone(String usermappingrfc) throws Exception {
		boolean isUserMappingDone = false;
		try {
			// *[@id="userMappingTable"]/tbody/tr[1]
			List<WebElement> appsrows = driver.findElements(By.xpath("//*[@id='userMappingTable']/tbody/tr"));
			for (int k = 0; k <= appsrows.size(); k++) {
				List<WebElement> appscols = driver
						.findElements(By.xpath("//*[@id='userMappingTable']/tbody/tr[" + k + "]/td"));
				log.info("User mapping col size is " + appscols.size());
				if (appscols.size() > 0) {
					log.info("User mappings:" + appscols.get(0).getText());
					if (appscols.get(0).getText().equalsIgnoreCase(usermappingrfc)) {
						log.info("user mapping exists");
						isUserMappingDone = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			log.info("Subscription App add failed.");
			throw (e);
		}
		return isUserMappingDone;
	}

	public PlatformUserPage userMappingRemove(String loginid, String emailaddress, String usermappingrfcremove,
			String tenantId) throws Exception {
		try {
			/*// selectAUser.click();

			List<WebElement> rows = driver.findElements(By.xpath("//*[@id='userBody']/tr"));
			for (int k = 0; k <= rows.size(); k++) {
				List<WebElement> cols = driver.findElements(By.xpath("//*[@id='userBody']/tr[" + k + "]/td"));
				log.info("Platform user mapping col size is " + cols.size());
				if (cols.size() > 0) {
					if (cols.get(3).getText().equalsIgnoreCase(loginid)
							&& cols.get(4).getText().equalsIgnoreCase(emailaddress)) {
						WebElement selecteduser = driver
								.findElement(By.xpath("//*[@id='userBody']/tr[" + k + "]/td[1]/input"));
						selecteduser.click();
						log.info("Platform users selected for user mapping.");
						break;
					}
				}
			}*/
			selectUser(loginid);
			log.info("Platform users selected for removing user mapping.");
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView()", userMappingTab);
			userMappingTab.click();

			List<WebElement> appsrows = driver.findElements(By.xpath("//*[@id='userMappingTable']/tbody/tr"));
			for (int k = 0; k <= appsrows.size(); k++) {
				List<WebElement> appscols = driver
						.findElements(By.xpath("//*[@id='userMappingTable']/tbody/tr[" + k + "]/td"));
				log.info("Platform user mapping col size is " + appscols.size());
				if (appscols.size() > 0) {
					log.info("user mapping remove from list:" + appscols.get(0).getText() + " == "
							+ usermappingrfcremove);
					if (appscols.get(0).getText().equalsIgnoreCase(usermappingrfcremove)) {
						log.info("equal");
						// *[@id="assignedAppsTable"]/tbody/tr[8]/td[3]/a
						WebElement removebtn = driver
								.findElement(By.xpath("//*[@id='userMappingTable']/tbody/tr[" + k + "]/td[3]/a"));

						if (removebtn != null) {
							removebtn.click();
							log.info("removebtn clicked");
							if ((Util.isElementDisplayed(deletePFUserMappingForm))) {
								// *[@id="deletePFUserMappingForm"]/div[2]/a[1]
								WebElement confirmbtn = driver
										.findElement(By.xpath("//*[@id='deletePFUserMappingForm']/div[2]/a[1]"));
								if (confirmbtn != null) {
									confirmbtn.click();
									log.info("user mapping removed");
									break;
								} else {
									log.info("confirmbtn is null");
								}
							}
						} else {
							log.info("removebtn is null");
						}
						break;
					} else {
						log.info("not equal");
					}
				}
			}

		} catch (Exception e) {
			log.error("User mapping error");
			throw (e);
		}
		return new PlatformUserPage();

	}

	public boolean isUsermappingRemoved(String usermappingrfcremove) throws Exception {
		boolean isremoved = true;
		try {
			// *[@id="userMappingTable"]/tbody/tr[1]
			List<WebElement> appsrows = driver.findElements(By.xpath("//*[@id='userMappingTable']/tbody/tr"));
			for (int k = 0; k <= appsrows.size(); k++) {
				List<WebElement> appscols = driver
						.findElements(By.xpath("//*[@id='userMappingTable']/tbody/tr[" + k + "]/td"));
				log.info("User mapping col size is " + appscols.size());
				if (appscols.size() > 0) {
					log.info("User mappings:" + appscols.get(0).getText());
					if (appscols.get(0).getText().equalsIgnoreCase(usermappingrfcremove)) {
						log.info("user mapping exists");
						isremoved = false;
						break;
					}
				}
			}
		} catch (Exception e) {
			log.info("Subscription App add failed.");
			throw (e);
		}
		return isremoved;
	}

	public PlatformUserPage setSalutation(String salutation) throws Exception {

		try {
			Select dropdown = new Select(driver.findElement(By.id("salutation")));
			dropdown.selectByValue(salutation);
			log.info("Salutation entered");
		} catch (Exception e) {
			log.info("salutation input field not found");
			throw (e);
		}
		return this;
	}

	public PlatformUserPage setFirstName(String firstName) throws Exception {

		try {
			firstNametxtbx.clear();
			firstNametxtbx.sendKeys(firstName);
			log.info("First name entered");
		} catch (Exception e) {
			log.info("First name input field not found");
			throw (e);
		}
		return this;
	}

	public PlatformUserPage setLastName(String lastName) throws Exception {

		try {
			lastNametxtbx.clear();
			lastNametxtbx.sendKeys(lastName);
			log.info("Last Name entered");
		} catch (Exception e) {
			log.info("Last Name input field not found");
			throw (e);
		}
		return this;
	}

	public PlatformUserPage setEmail(String email) throws Exception {

		try {
			emailtxtbx.clear();
			emailtxtbx.sendKeys(email);
			log.info("Email entered");
		} catch (Exception e) {
			log.info("Email input field not found");
			throw (e);
		}
		return this;
	}

	public PlatformUserPage setLoginName(String loginName) throws Exception {

		try {
			logintxtbx.clear();
			logintxtbx.sendKeys(loginName);
			log.info("loginName entered");
		} catch (Exception e) {
			log.info("loginName input field not found");
			throw (e);
		}
		return this;
	}

	public PlatformUserPage setTitle(String title) throws Exception {

		try {
			titletxtbx.clear();
			titletxtbx.sendKeys(title);
			log.info("title entered");
		} catch (Exception e) {
			log.info("title input field not found");
			throw (e);
		}
		return this;
	}

	public PlatformUserPage setPassword(String password) throws Exception {

		try {
			passwordtxtbx.clear();
			passwordtxtbx.sendKeys(password);
			log.info("password entered");
		} catch (Exception e) {
			log.info("password input field not found");
			throw (e);
		}
		return this;
	}

	public PlatformUserPage setChangePassword(String password) throws Exception {

		try {
			changepasswordtxtbx.clear();
			changepasswordtxtbx.sendKeys(password);
			log.info("Change password entered");
		} catch (Exception e) {
			log.info("Change password input field not found");
			throw (e);
		}
		return this;
	}

	public List<WebElement> setAssignedRole(List<String> assignedroles) throws Exception {
		List<WebElement> selected_roles = null;
		try {
			Select dropdown = new Select(driver.findElement(By.id("userAssingedRoleId")));
			for (String s : assignedroles) {
				dropdown.selectByVisibleText(s);
				log.info("assignedrole selected");
			}
			selected_roles = dropdown.getAllSelectedOptions();
			log.info("assignedrole selected size:" + selected_roles.size());
		} catch (Exception e) {
			log.info("Add Assign Role input field not found");
			throw (e);
		}
		return selected_roles;
	}
}
