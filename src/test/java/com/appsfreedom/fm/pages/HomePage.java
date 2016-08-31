package com.appsfreedom.fm.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.appsfreedom.utils.Util;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage extends BasePage {

	@FindBy(xpath = "//iframe[contains(@id,'dev') or contains(@id, 'qa') or contains(@id, 'prd')]")
	WebElement iframe;
	@FindBy(xpath = "//a[contains(text(), 'Logout')]")
	public WebElement logoutBtn;
	@FindBy(xpath = "//*[@id='proxyLabel']/p/a")
	public WebElement proxyLogout;
	@FindBy(xpath = "//a[contains(text(), 'Process Modeler')]")
	WebElement processModelerLnk;
	@FindBy(xpath = "//a[contains(text(), 'App Designer')]")
	WebElement appDesignerLnk;
	@FindBy(xpath = "//a[contains(text(), 'Integration Builder')]")
	WebElement integrationBuilderLnk;
	@FindBy(xpath = "//a[contains(text(), 'Database Designer')]")
	WebElement dbDesignerLnk;
	@FindBy(xpath = "//a[contains(text(), 'Drive')]")
	WebElement freedomDriveLnk;
	@FindBy(xpath = "//a[contains(text(), 'Platform Configurator')]")
	WebElement platformConfigLnk;
	@FindBy(xpath = "//a[contains(text(), 'App Library')]")
	WebElement appLibLnk;
	@FindBy(xpath = "//a[contains(text(), 'App Watch')]")
	WebElement appWatchLnk;
	@FindBy(xpath = "//a[contains(text(), 'Support')]")
	WebElement supportLnk;
	@FindBy(xpath = "//form[@id='ticketEditorForm']")
	WebElement supportFrm;
	@FindBy(xpath = "//form[@id='ticketEditorForm']//input[@id='userName']")
	WebElement ticketName;
	@FindBy(xpath = "//form[@id='ticketEditorForm']//input[@id='userPhone']")
	WebElement ticketPh;
	@FindBy(xpath = "//form[@id='ticketEditorForm']//input[@id='tktsubject']")
	WebElement ticketSubj;
	@FindBy(xpath = "//form[@id='ticketEditorForm']//textarea[@id='tktdetails']")
	WebElement ticketDetail;
	@FindBy(xpath = "//form[@id='ticketEditorForm']//input[@id='file_upload_ticket']")
	WebElement ticketFile;
	@FindBy(xpath = "//form[@id='ticketEditorForm']//input[@id='isEmail']")
	WebElement ticketCopy;
	@FindBy(xpath = "//form[@id='ticketEditorForm']//a[contains(text(), 'Submit')]")
	WebElement ticketSubmit;
	@FindBy(xpath = "//*[@id='alertPopupModal']")
	public WebElement alertPopupModal;
	@FindBy(xpath = "//*[@id='alertMessage']") // *[@id="alertPopupModal"]/div[3]/a
	public WebElement alertMessage;
	@FindBy(xpath = "//*[@id='alertPopupModal']/div[3]/a")
	WebElement alertPopupOkBtn;
	@FindBy(xpath = "/html/body/div[1]/div/div/div[1]")
	WebElement dashboard;

	@FindBy(xpath = "//*[@id='lic-renew-unlock-btn']")
	public WebElement licenceRenuewUnloadkBtn;

	@FindBy(xpath = "//*[@id='license-expired-lbl']/text()")
	public WebElement licenceExpiredlbl;

	public HomePage() {
		super();
	}

	public void switchToFrame() throws Exception {
		try {
			driver.switchTo().frame(iframe);
			log.info("Switched to iframe");
		} catch (Exception e) {
			log.info("Switch to iframe failed");
			throw (e);
		}
	}

	public boolean isAtHomePage() {

		return Util.isElementClickable(dashboard);

	}

	public HomePage gotoHome() throws Exception {
		try {
			// switchToFrame();
			Util.delay(3000);
			dashboard.click();
			Util.delay(2000);
			log.info("Clicked on dashboard");
		} catch (Exception e) {
			log.info("Dasboard element not found");
			throw (e);
		}
		return new HomePage();
	}

	public LoginPage logout() throws Exception {
		try {
			// switchToFrame();
			logoutBtn.click();
			log.info("Clicked on Logout button");
		} catch (Exception e) {
			log.info("Logout button not found");
			throw (e);
		}
		return new LoginPage();
	}

	public HomePage proxylogout() throws Exception {
		try {
			// switchToFrame();
			proxyLogout.click();
			log.info("Clicked on Proxy Logout button");
		} catch (Exception e) {
			log.info("Logout button not found");
			throw (e);
		}
		return new HomePage();
	}

	public ProcessModelerPage lauchProcessModeler() throws Exception {
		try {
			processModelerLnk.click();
			Util.delay(5000);
			Util.switchToTab();
			log.info("launched process modeler");
		} catch (Exception e) {
			log.info("Process modeler link not found");
			throw (e);
		}
		return new ProcessModelerPage();
	}

	public AppDesignerPage lauchAppDesigner() throws Exception {
		try {
			appDesignerLnk.click();
			Util.switchToTab();
			log.info("Launched App Designer");
		} catch (Exception e) {
			log.info("App Designer link not found");
			throw (e);
		}
		return new AppDesignerPage();
	}

	public IntegrationBuilderPage lauchBuilder() throws Exception {
		try {
			integrationBuilderLnk.click();
			Util.switchToTab();
			log.info("Launched Integration builder");
		} catch (Exception e) {
			log.info("Integration Builder link not found");
			throw (e);

		}
		return new IntegrationBuilderPage();
	}

	public DbDesignerPage lauchDbDesigner() throws Exception {
		try {
			dbDesignerLnk.click();
			Util.switchToTab();
			log.info("DB Designer launched");
		} catch (Exception e) {
			log.info("DB Designer link not found");
			throw (e);
		}
		return new DbDesignerPage();
	}

	public DrivePage lauchDrive() throws Exception {
		try {
			freedomDriveLnk.click();
			Util.delay(5000);
			Util.switchToTab();
			log.info("Freedom Drive launched");
		} catch (Exception e) {
			log.info("Freedom Drive link not found");
			throw (e);
		}
		return new DrivePage();
	}

	public PlatformConfigPage openConfigurator() throws Exception {
		try {
			platformConfigLnk.click();
			Util.delay(3000);
			log.info("Opened Configurator");
		} catch (Exception e) {
			log.info("Configurator link not found");
			throw (e);
		}
		return new PlatformConfigPage();
	}

	public AppLibPage openAppLib() throws Exception {
		try {
			appLibLnk.click();
			Util.delay(5000);
			log.info("Opened App Library");
		} catch (Exception e) {
			log.info("App Library link not found");
			throw (e);
		}
		return new AppLibPage();
	}

	public AppWatchPage openAppWatch() throws Exception {
		try {
			appWatchLnk.click();
			Util.delay(5000);
			log.info("Opened App Watch");
		} catch (Exception e) {
			log.info("App Watch link not found");
			throw (e);
		}
		return new AppWatchPage();
	}

	public HomePage openSupport() throws Exception {
		try {
			supportLnk.click();
			log.info("Opened Support form");
		} catch (Exception e) {
			log.info("Support link not found");
			throw (e);
		}
		return this;
	}

	public HomePage enterUsrName(String usrName) throws Exception {
		try {
			ticketName.clear();
			ticketName.sendKeys(usrName);
			log.info("Entered user name");
		} catch (Exception e) {
			log.info("User name field not found");
			throw (e);
		}
		return this;
	}

	public HomePage enterPhNum(String phNum) throws Exception {
		try {
			ticketPh.sendKeys(phNum);
			log.info("Entered phone number");
		} catch (Exception e) {
			log.info("Phone number field not found");
			throw (e);
		}
		return this;
	}

	public HomePage enterTicketSubj(String subj) throws Exception {
		try {
			ticketSubj.sendKeys(subj);
			log.info("Entered ticket subject");
		} catch (Exception e) {
			log.info("Subject field not found");
			throw (e);
		}
		return this;
	}

	public HomePage enterTicketDetail(String detail) throws Exception {
		try {
			ticketDetail.sendKeys(detail);
			log.info("Entered ticket detail");
		} catch (Exception e) {
			log.info("Detail field not found");
			throw (e);
		}
		return this;
	}

	public HomePage uploadFile(String path) throws Exception {
		try {
			ticketFile.sendKeys(path);
			log.info("File uploded");
		} catch (Exception e) {
			log.info("File upload not found");
			throw (e);
		}
		return this;
	}

	public HomePage checkSendEmail() throws Exception {
		try {
			ticketCopy.click();
			log.info("Checked email checkbox");
		} catch (Exception e) {
			log.info("Email checkbox not found");
			throw (e);
		}
		return this;
	}

	public HomePage submitSupport() throws Exception {
		try {
			ticketSubmit.click();
			log.info("Submitted ticket");
		} catch (Exception e) {
			log.info("Submit button not found");
			throw (e);
		}
		return this;
	}

	public HomePage closePopupModel() throws Exception {
		try {
			alertPopupOkBtn.click();
			log.info("Alert popup model closed");
			Util.delay(2000);
		} catch (Exception e) {
			log.info("Popup model OK button not found");
			throw (e);
		}
		return this;
	}

	public HomePage submitSupportTkt(String usrName, String ph, String subj, String detail) throws Exception {
		try {
			openSupport();
			Util.isElementDisplayed(supportFrm);
			enterUsrName(usrName);
			enterPhNum(ph);
			enterTicketSubj(subj);
			enterTicketDetail(detail);
			uploadFile(System.getProperty("user.dir") + "\\resources\\iphone4.png");
			checkSendEmail();
			submitSupport();
		} catch (Exception e) {
			log.info("Ticket submission failed");
			throw (e);
		}
		return this;
	}

	public boolean verifyLicenceMessage() throws Exception {
		boolean result = false;
		String message = "";
		try {
			if (licenceRenuewUnloadkBtn.isDisplayed()) {
				log.info("Licence Expiry Details Verfied");
				String path = Util.getscreenshot("Licence Message displayed");
				test.log(LogStatus.INFO, "Licence Message displayed" + test.addScreenCapture(path));
				result = true;
			} else {
				log.info("Licence Expiry Details not Verfied");
			}

		} catch (Exception e) {
			log.info("Licence Message Verfication failed");
			throw (e);
		}
		return result;
	}
}
