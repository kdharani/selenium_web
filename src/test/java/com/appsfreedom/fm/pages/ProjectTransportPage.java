package com.appsfreedom.fm.pages;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.appsfreedom.utils.MyLogger;
import com.appsfreedom.utils.SQLConnector;
import com.appsfreedom.utils.Util;
import com.relevantcodes.extentreports.LogStatus;

public class ProjectTransportPage extends BasePage {

	// *[@id="saveUpdateProjectForm"]

	private HomePage hmePg;
	// Logger log = Logger.getLogger("devpinoyLogger");

	@FindBy(xpath = "//*[@id='saveUpdateProjectForm']")
	WebElement createProjectForm;

	@FindBy(xpath = "//*[@id='alertPublishBOSSuccessModal']")
	WebElement alertPublishBOSSuccessModal;

	@FindBy(xpath = "//*[@id='alertPublishBOSSuccessModal']/div[2]/a")
	WebElement alertPublishBOSSuccessModalOkBtn;

	@FindBy(xpath = "//*[@id='alertPublishSuccessModal']")
	WebElement alertPublishSuccessModal;

	@FindBy(xpath = "//*[@id='alertPublishSuccessModal']/div[2]/a")
	WebElement alertPublishSuccessModalOkBtn;

	@FindBy(xpath = "//*[@id='alertTransportSuccessModal']")
	WebElement alertTransportSuccessModal;

	@FindBy(xpath = "//*[@id='alertTransportSuccessModal']/div[2]/a")
	WebElement alertTransportSuccessModalOkBtn;

	@FindBy(xpath = "//*[@id='alertTransportBOSSuccessModal']")
	WebElement alertTransportBOSSuccessModal;

	@FindBy(xpath = "//*[@id='alertTransportBOSSuccessModal']/div[2]/a")
	WebElement alertTransportBOSSuccessModalOkBtn;

	@FindBy(xpath = "//*[@id='projectmanagement_generalinfo']/div/p/a")
	WebElement editGeneralInfobtn;

	@FindBy(xpath = "//*[@id='includeInactiveProjs']")
	WebElement includeInactiveProjectsbtn;

	@FindBy(xpath = "//*[@id='projectAccessAddForm']")
	WebElement projectAccessAddForm;
	@FindBy(xpath = "//div[@id='loadingImage']")
	WebElement spinner;

	public ProjectTransportPage() {

		super();
		hmePg = new HomePage();
	}

	public HomePage getHmePage() {
		return hmePg;
	}

	public ProjectTransportPage clickOnAProject(String xpath) throws Exception {

		try {
			WebElement projectbtn = driver.findElement(By.xpath(xpath));
			projectbtn.click();
			MyLogger.log.info("selected project");
		} catch (Exception e) {
			MyLogger.log.error("No project for selection");
			throw (e);
		}

		return new ProjectTransportPage();
	}

	public ProjectTransportPage clickOnTransportTab() throws Exception {

		try {
			WebElement projectbtn = driver.findElement(By.xpath("//*[@id='projectManagementGeneralInfo']/ul/li[2]/a"));
			projectbtn.click();
			MyLogger.log.info("selected tranport tab");
		} catch (Exception e) {
			MyLogger.log.error("Trasnport tab does not exist");
			throw (e);
		}

		return new ProjectTransportPage();
	}

	public ProjectTransportPage clickOnGeneralInfoTab() throws Exception {

		try {
			WebElement projectbtn = driver.findElement(By.xpath("//*[@id='projectManagementGeneralInfo']/ul/li[1]/a"));
			projectbtn.click();
			MyLogger.log.info("selected tranport tab");
		} catch (Exception e) {
			MyLogger.log.error("General Info tab does not exist");
			throw (e);
		}

		return new ProjectTransportPage();
	}

	public ProjectTransportPage createProject(String projectname) throws Exception {

		try {

			WebElement projectbtn = driver
					.findElement(By.xpath("//*[@id='projectmanagement']/table/tbody/tr[3]/td/table/tbody/tr/td[1]/a"));
			projectbtn.click();
			MyLogger.log.info("create project btn clicked");
			if ((Util.isElementDisplayed(createProjectForm))) {
				WebElement projnametxtbox = driver.findElement(By.xpath("//*[@id='name']"));
				projnametxtbox.sendKeys(projectname);
				WebElement createbtn = driver.findElement(By.xpath("//*[@id='saveOrUpdate']"));
				createbtn.click();
				if ((Util.isAlertPresent())) {
					// Check if alert exists
					BufferedImage image = new Robot()
							.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
					ImageIO.write(image, "png",
							new File(System.getProperty("user.dir") + "\\screenshots\\" + "Create project" + ".png"));
					Util.closeAlert();
				}

			}
		} catch (Exception e) {
			MyLogger.log.error("Create project btn does not exist");
			throw (e);
		}

		return new ProjectTransportPage();
	}

	public boolean isProjectCreated(String projectname) throws Exception {
		boolean created = false;
		try {
			List<WebElement> rows = driver.findElements(By.xpath("//*[@id='projectsDisplayTable']/tbody/tr"));
			for (int k = 0; k <= rows.size(); k++) {
				List<WebElement> cols = driver
						.findElements(By.xpath("//*[@id='projectsDisplayTable']/tbody/tr[" + k + "]/td"));
				MyLogger.log.info("project tbl col size is " + cols.size());
				if (cols.size() > 0) {
					MyLogger.log.info("isProjectCreated 0=" + cols.get(0).getText() + " 1=" + cols.get(1).getText());
					if (cols.get(1).getText().equalsIgnoreCase(projectname)) {
						created = true;
						MyLogger.log.info("Project created successfully.");
					}
				}
			}
		} catch (Exception e) {
			MyLogger.log.error("isProjectCreated error");
			throw (e);
		}
		return created;
	}

	public ProjectTransportPage selectproject(String projectname) throws Exception {

		try {
			List<WebElement> rows = driver.findElements(By.xpath("//*[@id='projectsDisplayTable']/tbody/tr"));
			for (int k = 0; k <= rows.size(); k++) {
				List<WebElement> cols = driver
						.findElements(By.xpath("//*[@id='projectsDisplayTable']/tbody/tr[" + k + "]/td"));
				MyLogger.log.info("selectprojectsTransportTabcol size is " + cols.size());
				if (cols.size() > 0) {
					MyLogger.log.info("selectprojectsTransportTab - projectnames:" + cols.get(1).getText());
					if (cols.get(1).getText().equalsIgnoreCase(projectname)) {
						String xpath = "//*[@id='projectsDisplayTable']/tbody/tr[" + k + "]/td[1]/input";
						clickOnAProject(xpath);
						break;
					}
				}
			}

		} catch (Exception e) {
			MyLogger.log.error("selectprojectsTransportTab error");
			throw (e);
		}

		return new ProjectTransportPage();
	}

	public boolean enableBaas(String tenantid, String prjname, String bosname) throws Exception {
		boolean isBaasEnabled = false;
		try {
			if (SQLConnector.doesBosExists(tenantid, prjname, bosname)) {
				MyLogger.log.info(
						"Project and transport - projectnames:" + prjname + " and bosname:" + bosname + " exists");
				List<WebElement> ttrows = driver
						.findElements(By.xpath("//*[@id='projectsTransportDisplayTable']/tbody/tr"));
				MyLogger.log.info("Project and transport ttrows size:" + ttrows.size());
				for (int kk = 1; kk <= ttrows.size(); kk++) {// *[@id="projectsTransportDisplayTable"]/tbody/tr[1]/td[1]
					List<WebElement> ttcols = driver
							.findElements(By.xpath("//*[@id='projectsTransportDisplayTable']/tbody/tr[" + kk + "]/td"));
					MyLogger.log.info("Project and transport ttcols size:" + ttcols.size() + " bosname:"
							+ ttcols.get(0).getText() + " type:" + ttcols.get(3).getText());
					if (ttcols.get(0).getText().equalsIgnoreCase(bosname)
							&& ttcols.get(3).getText().equalsIgnoreCase("BOS")) {
						WebElement BAASEnable_chkbox = driver.findElement(
								By.xpath("//*[@id='projectsTransportDisplayTable']/tbody/tr[" + kk + "]/td[6]/input"));
						MyLogger.log.info("Project and transport BOS available to Enable BAAS ");
						MyLogger.log.info("Project and transport BOS chkbox isEnabled:" + BAASEnable_chkbox.isEnabled()
								+ " isSelected:" + BAASEnable_chkbox.isSelected());
						if (BAASEnable_chkbox.isEnabled()) {
							if (!BAASEnable_chkbox.isSelected()) {
								BAASEnable_chkbox.click();
								if ((Util.isAlertPresent())) {
									BufferedImage image = new Robot().createScreenCapture(
											new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
									ImageIO.write(image, "png", new File(System.getProperty("user.dir")
											+ "\\screenshots\\" + "Enable_BAAS" + ".png"));
									Util.closeAlert();
								}

							}
							isBaasEnabled = true;
						}
						break;
					}
				}
			} else {
				MyLogger.log.info("Project and transport - projectnames:" + prjname + " and bosname:" + bosname
						+ " does not exists");
			}

		} catch (Exception e) {
			MyLogger.log.error("enableBaas error");
			throw (e);
		}

		return isBaasEnabled;
	}

	public boolean publishBos(String tenantid, String prjname, String bosname) throws Exception {
		boolean isPublished = false;
		try {
			if (SQLConnector.doesBosExists(tenantid, prjname, bosname)) {
				MyLogger.log.info(
						"Project and transport - projectnames:" + prjname + " and bosname:" + bosname + " exists");
				List<WebElement> ttrows = driver
						.findElements(By.xpath("//*[@id='projectsTransportDisplayTable']/tbody/tr"));
				MyLogger.log.info("Project and transport ttrows size:" + ttrows.size());
				for (int kk = 1; kk <= ttrows.size(); kk++) {// *[@id="projectsTransportDisplayTable"]/tbody/tr[1]/td[1]
					List<WebElement> ttcols = driver
							.findElements(By.xpath("//*[@id='projectsTransportDisplayTable']/tbody/tr[" + kk + "]/td"));
					MyLogger.log.info("Project and transport ttcols size:" + ttcols.size() + " bosname:"
							+ ttcols.get(0).getText() + " type:" + ttcols.get(3).getText());
					if (ttcols.get(0).getText().equalsIgnoreCase(bosname)
							&& ttcols.get(3).getText().equalsIgnoreCase("BOS")) {
						WebElement publishbtn = driver.findElement(
								By.xpath("//*[@id='projectsTransportDisplayTable']/tbody/tr[" + kk + "]/td[7]/a[1]"));
						publishbtn.click();

						String msg = closePublishBOSSuccessPopupIfPresent();
						if (msg.equalsIgnoreCase("BOS has been published successfully!.."))
							isPublished = true;

						break;
					}
				}
			} else {
				MyLogger.log.info("Project and transport - projectnames:" + prjname + " and bosname:" + bosname
						+ " does not exists");
			}

		} catch (Exception e) {
			MyLogger.log.error("enableBaas error");
			throw (e);
		}
		return isPublished;
	}

	public boolean publishApp(String tenantid, String prjname, String appName) throws Exception {
		boolean isPublished = false;
		try {

			MyLogger.log.info("Project and transport - projectnames:" + prjname + " and appName:" + appName);
			List<WebElement> ttrows = driver
					.findElements(By.xpath("//*[@id='projectsTransportDisplayTable']/tbody/tr"));
			MyLogger.log.info("Project and transport ttrows size:" + ttrows.size());
			for (int kk = 1; kk <= ttrows.size(); kk++) {// *[@id="projectsTransportDisplayTable"]/tbody/tr[1]/td[1]
				List<WebElement> ttcols = driver
						.findElements(By.xpath("//*[@id='projectsTransportDisplayTable']/tbody/tr[" + kk + "]/td"));
				MyLogger.log.info("Project and transport ttcols size:" + ttcols.size() + " bosname:"
						+ ttcols.get(0).getText() + " type:" + ttcols.get(3).getText());
				if (ttcols.get(0).getText().equalsIgnoreCase(appName)
						&& ttcols.get(3).getText().equalsIgnoreCase("APP")) {
					WebElement publishbtn = driver.findElement(
							By.xpath("//*[@id='projectsTransportDisplayTable']/tbody/tr[" + kk + "]/td[7]/a[1]"));
					publishbtn.click();
					Util.delay(5000);
					String msg = closePublishSuccessPopupIfPresent();
					if (msg.equalsIgnoreCase("App has been published successfully!.."))
						isPublished = true;

					break;
				}
			}

		} catch (Exception e) {
			MyLogger.log.error("publishApp error");
			throw (e);
		}
		return isPublished;
	}

	public boolean transportApp(String tenantid, String prjname, String appName) throws Exception {
		boolean isTrasnported = false;
		try {

			MyLogger.log.info("Project and transport - projectnames:" + prjname + " and appName:" + appName);
			List<WebElement> ttrows = driver
					.findElements(By.xpath("//*[@id='projectsTransportDisplayTable']/tbody/tr"));
			MyLogger.log.info("Project and transport ttrows size:" + ttrows.size());
			for (int kk = 1; kk <= ttrows.size(); kk++) {// *[@id="projectsTransportDisplayTable"]/tbody/tr[1]/td[1]
				List<WebElement> ttcols = driver
						.findElements(By.xpath("//*[@id='projectsTransportDisplayTable']/tbody/tr[" + kk + "]/td"));
				MyLogger.log.info("Project and transport ttcols size:" + ttcols.size() + " bosname:"
						+ ttcols.get(0).getText() + " type:" + ttcols.get(3).getText());
				if (ttcols.get(0).getText().equalsIgnoreCase(appName)
						&& ttcols.get(3).getText().equalsIgnoreCase("APP")) {
					WebElement transportbtn = driver.findElement(
							By.xpath("//*[@id='projectsTransportDisplayTable']/tbody/tr[" + kk + "]/td[7]/a[2]"));
					transportbtn.click();
					Util.delay(5000);
					String msg = closeTransportSuccessPopupIfPresent();
					if (msg.equalsIgnoreCase("App has been transported successfully!.."))
						isTrasnported = true;

					break;
				}
			}

		} catch (Exception e) {
			MyLogger.log.error("transportApp error");
			throw (e);
		}
		return isTrasnported;
	}

	public boolean transportBOS(String tenantid, String prjname, String appName) throws Exception {
		boolean isTrasnported = false;
		try {

			MyLogger.log.info("Project and transport - projectnames:" + prjname + " and appName:" + appName);
			List<WebElement> ttrows = driver
					.findElements(By.xpath("//*[@id='projectsTransportDisplayTable']/tbody/tr"));
			MyLogger.log.info("Project and transport ttrows size:" + ttrows.size());
			for (int kk = 1; kk <= ttrows.size(); kk++) {// *[@id="projectsTransportDisplayTable"]/tbody/tr[1]/td[1]
				List<WebElement> ttcols = driver
						.findElements(By.xpath("//*[@id='projectsTransportDisplayTable']/tbody/tr[" + kk + "]/td"));
				MyLogger.log.info("Project and transport ttcols size:" + ttcols.size() + " bosname:"
						+ ttcols.get(0).getText() + " type:" + ttcols.get(3).getText());
				if (ttcols.get(0).getText().equalsIgnoreCase(appName)
						&& ttcols.get(3).getText().equalsIgnoreCase("BOS")) {
					WebElement transportbtn = driver.findElement(
							By.xpath("//*[@id='projectsTransportDisplayTable']/tbody/tr[" + kk + "]/td[7]/a[2]"));
					transportbtn.click();
					Util.delay(5000);
					String msg = closeTransportBOSSuccessPopupIfPresent();
					if (msg.equalsIgnoreCase("BOS has been transported successfully!.."))
						isTrasnported = true;

					break;
				}
			}

		} catch (Exception e) {
			MyLogger.log.error("transportApp error");
			throw (e);
		}
		return isTrasnported;
	}

	public String closePublishBOSSuccessPopupIfPresent() {
		String message = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(alertPublishBOSSuccessModal));
			message = alertPublishBOSSuccessModal.getText();
			alertPublishBOSSuccessModalOkBtn.click();
			MyLogger.log.debug(message);
			Util.delay(2000);
		} catch (Exception e) {
			MyLogger.log.info("No PublishBOSSuccess popup modal open");

		}
		return message;
	}

	public String closePublishSuccessPopupIfPresent() {
		String message = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOf(alertPublishSuccessModal));
			message = alertPublishSuccessModal.getText();
			alertPublishSuccessModalOkBtn.click();
			MyLogger.log.debug(message);
			Util.delay(2000);
		} catch (Exception e) {
			MyLogger.log.info("No PublishSuccess popup modal open");

		}
		return message;
	}

	public String closeTransportSuccessPopupIfPresent() {
		String message = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOf(alertTransportSuccessModal));
			message = alertTransportSuccessModal.getText();
			alertTransportSuccessModalOkBtn.click();
			MyLogger.log.debug(message);
			Util.delay(2000);
		} catch (Exception e) {
			MyLogger.log.info("No TransportSuccess popup modal open");

		}
		return message;
	}

	public String closeTransportBOSSuccessPopupIfPresent() {
		String message = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOf(alertTransportBOSSuccessModal));
			message = alertTransportBOSSuccessModal.getText();
			alertTransportBOSSuccessModalOkBtn.click();
			MyLogger.log.debug(message);
			Util.delay(2000);
		} catch (Exception e) {
			MyLogger.log.info("No TransportBOSSuccess popup modal open");

		}
		return message;
	}

	public boolean editProject(String projectDesc) throws Exception {
		boolean edited = false;
		try {

			editGeneralInfobtn.click();
			MyLogger.log.info("edit project btn clicked");
			if ((Util.isElementDisplayed(createProjectForm))) {
				WebElement projdesctxtbox = driver.findElement(By.xpath("//*[@id='description']"));
				projdesctxtbox.clear();
				projdesctxtbox.sendKeys(projectDesc);
				WebElement createbtn = driver.findElement(By.xpath("//*[@id='saveOrUpdate']"));
				createbtn.click();
				WebElement desctxtbox = driver.findElement(By.xpath("//*[@id='descriptionDisp']"));
				if (desctxtbox.getText().equalsIgnoreCase("projectDesc"))
					edited = true;

			}
		} catch (Exception e) {
			MyLogger.log.error("Edit project btn does not exist");
			throw (e);
		}

		return edited;
	}

	public boolean includeInactiveProject() throws Exception {
		boolean includeInactiveProjects = false;
		try {

			if (!includeInactiveProjectsbtn.isSelected()) {
				includeInactiveProjectsbtn.click();
				MyLogger.log.info("Include Inactive Projects Checked.");
			}
			includeInactiveProjects = true;
		} catch (Exception e) {
			MyLogger.log.error("Include Inactive Projects checkbox does not exist");
			throw (e);
		}

		return includeInactiveProjects;
	}

	public ProjectTransportPage clickOnSDKTab() throws Exception {

		try {
			WebElement projectbtn = driver.findElement(By.xpath("//*[@id='projectManagementGeneralInfo']/ul/li[4]/a"));
			Util.scrollToElement(projectbtn);
			Util.isElementDisplayed(projectbtn);
			projectbtn.click();
			MyLogger.log.info("selected SDK tab");
			WebElement exportbtn = driver.findElement(By.xpath("//*[@id='dbschemaexportlink']"));
			exportbtn.click();
		} catch (Exception e) {
			MyLogger.log.error("SDK tab does not exist");
			throw (e);
		}

		return new ProjectTransportPage();
	}

	public ProjectTransportPage clickOnAccessInfoTab() throws Exception {

		try {
			WebElement projectbtn = driver.findElement(By.xpath("//*[@id='projectManagementGeneralInfo']/ul/li[3]/a"));
			Util.scrollToElement(projectbtn).click();
			Util.isNotDisplayed(spinner);
			MyLogger.log.info("selected Access Info tab");
		} catch (Exception e) {
			MyLogger.log.error("Access Info tab does not exist");
			throw (e);
		}

		return new ProjectTransportPage();
	}

	public boolean AddProjectToGroup(String GroupName) throws Exception {
		boolean groupadded = false;
		try {
			WebElement projectbtn = driver.findElement(By.xpath("//*[@id='accessinfo']/div[1]/p/a"));
			projectbtn.click();
			MyLogger.log.info("Add button clicked");
			if ((Util.isElementDisplayed(projectAccessAddForm))) {
				WebElement groupbtn = projectAccessAddForm.findElement(By.xpath("//input[@id='radioTypeGroup']"));
				Util.highlightElement(groupbtn);
				if (!groupbtn.isSelected()) {
					Util.highlightElement(groupbtn);
					groupbtn.click();
				}
				MyLogger.log.info("Group Option Selected");
				Select dropdown = new Select(
						projectAccessAddForm.findElement(By.xpath("//select[@id='availableProjectGroups']")));
				dropdown.selectByVisibleText(GroupName);
				MyLogger.log.info("Group Selected");
				WebElement savebtn = driver.findElement(By.xpath("//*[@id='projectAccessAddModal']/div[2]/a[1]"));
				savebtn.click();
				MyLogger.log.info("Save btn clicked");

				List<WebElement> ttrows = driver.findElements(By.xpath("//*[@id='projectAccessTable']/tbody/tr"));
				for (int kk = 1; kk <= ttrows.size(); kk++) {
					List<WebElement> ttcols = driver
							.findElements(By.xpath("//*[@id='projectAccessTable']/tbody/tr[" + kk + "]/td"));
					MyLogger.log.info("groupname:" + ttcols.get(0).getText());
					if (ttcols.get(0).getText().equalsIgnoreCase(GroupName)) {
						groupadded = true;
						break;
					}
				}

			}
		} catch (Exception e) {
			MyLogger.log.error("AddProjectToGroup failed");
			throw (e);
		}

		return groupadded;
	}

	public boolean AddProjectToUser(String UserName) throws Exception {
		boolean useradded = false;
		try {
			WebElement projectbtn = driver.findElement(By.xpath("//*[@id='accessinfo']/div[1]/p/a"));
			projectbtn.click();
			MyLogger.log.info("Add button clicked");
			if ((Util.isElementDisplayed(projectAccessAddForm))) {
				WebElement userbtn = driver.findElement(By.xpath("//*[@id='radioType']"));
				if (!userbtn.isSelected()) {
					userbtn.click();
				}
				MyLogger.log.info("User Option Selected");
				Select dropdown = new Select(driver.findElement(By.xpath("//*[@id='availableProjectUsers']")));
				if (Util.isOptionExist(dropdown, UserName)) {
					dropdown.selectByVisibleText(UserName);
					MyLogger.log.info("Option " + UserName + " is selected");
					MyLogger.log.info("User " + UserName + " Selected");
					WebElement savebtn = driver.findElement(By.xpath("//*[@id='projectAccessAddModal']/div[2]/a[1]"));
					savebtn.click();
					Util.isNotDisplayed(spinner);
					MyLogger.log.info("Save btn clicked");
					useradded = true;
				} else {
					MyLogger.log.info("Option " + UserName + " not found in the dropdown");
					WebElement cancelbtn = driver.findElement(By.xpath("//*[@id='projectAccessAddModal']/div[2]/a[2]"));
					cancelbtn.click();
					Util.isNotDisplayed(spinner);
				}

			}
		} catch (Exception e) {
			MyLogger.log.error("AddProjectToUser failed");
			throw (e);
		}

		return useradded;
	}

	public boolean importDBSchema(String schemaFilePath) throws Exception {
		boolean dbschemaimport = false;
		try {
			WebElement importdbschemabtn = driver.findElement(By.xpath("//*[@id='dbschemaimportlink']"));
			importdbschemabtn.click();
			MyLogger.log.info("import dbschema button clicked");
			WebElement element = driver.findElement(By.name("dbschema_upload"));
			Util.waitForElement(element).sendKeys(schemaFilePath);// C:\Users\50798\Downloads\WAT110173.sql
			MyLogger.log.info("dbschema filepath entered");
			((JavascriptExecutor) driver).executeScript("window.alert = function(msg) { return true; }");
			WebElement submitschemabtn = driver.findElement(By.xpath("//*[@id='dbSchemaImportForm']/div[4]/a"));
			submitschemabtn.click();
			Util.delay(1000);
			dbschemaimport = true;
			MyLogger.log.info("dbschema submit button clicked");
		} catch (Exception e) {
			MyLogger.log.error("importDBSchema does not exist");
			throw (e);
		}

		return dbschemaimport;
	}

	public boolean importDBData(String dataFilePath, String tablename, String columnname) throws Exception {
		boolean dbdataimport = false;
		try {
			WebElement importdatabtn = driver.findElement(By.xpath("//*[@id='dbdataimportlink']"));
			importdatabtn.click();
			MyLogger.log.info("import db data button clicked");
			Select dropdown = new Select(driver.findElement(By.xpath("//*[@id='tableList']")));
			dropdown.selectByVisibleText(tablename);
			MyLogger.log.info("selected a table for import");
			WebElement element = driver
					.findElement(By.xpath("//button[text()='Load']//preceding::input[@id='file_upload']"));
			element.sendKeys(dataFilePath);// C:\Users\50798\Downloads\tblname.csv
			MyLogger.log.info("csv filepath entered");
			WebElement loadbtn = driver.findElement(By.xpath("//*[@id='popupModal']/div[2]/div/button"));
			loadbtn.click();
			MyLogger.log.info("load button clicked");
			Select columnmapselect = new Select(
					driver.findElement(By.xpath("//*[@id='popupModal']/div[2]/div/table/tbody/tr[1]/td[2]/select")));// For
																														// testing
																														// purpose
																														// just
																														// once
																														// column
																														// table
			columnmapselect.selectByVisibleText(columnname);
			MyLogger.log.info("db column mapping done");
			((JavascriptExecutor) driver).executeScript("window.alert = function(msg) { return true; }");
			WebElement okbtn = driver.findElement(By.xpath("//*[@id='popupModal']/div[3]/a[1]"));
			okbtn.click();
			dbdataimport = true;
			MyLogger.log.info("dbimport ok button clicked");
		} catch (Exception e) {
			MyLogger.log.error("importDBData does not exist");
			throw (e);
		}

		return dbdataimport;
	}
}
