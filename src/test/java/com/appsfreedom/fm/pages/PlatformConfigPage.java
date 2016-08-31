package com.appsfreedom.fm.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.appsfreedom.utils.CustomException;
import com.appsfreedom.utils.Util;

public class PlatformConfigPage extends BasePage {
	public HomePage hmePg;
	Logger log = Logger.getLogger("devpinoyLogger");

	// Create OTA
	@FindBy(xpath = "//tbody[@id='tenantBody']")
	WebElement tenantBodyTbl;

	@FindBy(xpath = "//form[@id='mailForm']")
	WebElement mailForm;
	
	@FindBy(xpath ="//*[@id='editButtonId']/a")
	WebElement editSubscription;

	@FindBy(xpath = "//tbody[@id='schedulerBody']")
	WebElement schedulerBdyTbl;

	@FindBy(xpath = "//tbody[@id='logsTableBody']")
	WebElement logsBdyTbl;

	@FindBy(xpath = "//tbody[@id='pluginBody']")
	WebElement pluginBody;

	@FindBy(xpath = "//*[@id='connectivity']/table/tbody/tr[4]/td/div/table/tbody")
	WebElement connectionTbody;

	@FindBy(xpath = "//*[@id='projectmanagement']/table/tbody")
	WebElement prjMgntTbody;

	@FindBy(xpath = "//*[@id='roleBody']")
	WebElement roleTbody;

	@FindBy(xpath = "//*[@id='userBody']")
	WebElement userTbody;

	@FindBy(xpath = "//*[@id='organization']/table/tbody")
	WebElement orgTbody;

	@FindBy(xpath = "//*[@id='createtenant']/table/tbody")
	WebElement createTenantTbody;

	@FindBy(xpath = "//*[@id='subscriptionTable']/tbody")
	WebElement subscriptionTbody;

	@FindBy(xpath = "//*[@id='alertPopupModal']") // *[@id="alertPopupModal"]/div[3]/a
	public	WebElement alertPopupModal;

	@FindBy(xpath = "//*[@id='alertMessage']") // *[@id="alertPopupModal"]/div[3]/a
	WebElement alertMessage;

	@FindBy(xpath = "//*[@id='alertPopupModal']/div[3]/a")
	WebElement alertPopupOkBtn;

	@FindBy(xpath = "//*[@id='otalinks']/p[1]/a")
	WebElement otaCreateBtn;

	@FindBy(xpath = "//*[@id='addContainerAppForm']")
	WebElement addContainerAppForm;

	@FindBy(xpath = "//*[@id='appId']")
	WebElement appIdInputBx;

	@FindBy(xpath = "//*[@id='appName']")
	WebElement appNameInputBx;

	@FindBy(xpath = "//input[@id='externalLinks']")
	WebElement externalLinksChkBx;

	@FindBy(xpath = "//input[@id='totaicon_upload']")
	WebElement selectIconFile;

	@FindBy(xpath = "//input[@id='totasscreen_tablet_upload']")
	WebElement uploadTableIcon;

	@FindBy(xpath = "//input[@id='totasscreen_iphone4_upload']")
	WebElement uploadIphone4Icon;

	@FindBy(xpath = "//input[@id='totasscreen_iphone5_upload']")
	WebElement uploadIphone5Icon;

	@FindBy(xpath = "//input[@id='totasscreen_iphone6_upload']")
	WebElement uploadIphone6Icon;

	@FindBy(xpath = "//input[@id='totasscreen_iphone6plus_upload']")
	WebElement uploadIphone6PIcon;

	@FindBy(xpath = "//input[@id='totasscreen_android_upload']")
	WebElement uploadAndroidIcon;

	@FindBy(xpath = "//input[@id='totacertificate_upload']")
	WebElement uploadAppleCertificate;

	@FindBy(xpath = "//input[@id='certifpassword']")
	WebElement applePwdInput;

	@FindBy(xpath = "//input[@id='totamobilepro_upload']")
	WebElement uploadAppleMobileProv;

	@FindBy(xpath = "//input[@id='totapushcert_upload']")
	WebElement uploadPushCertificate;

	@FindBy(xpath = "//input[@id='pushpassword']")
	WebElement pushPwdInput;

	@FindBy(xpath = "//input[@id='totakeystore_upload']")
	WebElement uploadKeystoreCertificate;

	@FindBy(xpath = "//input[@id='keystorepassword']")
	WebElement keystorePwdInput;

	@FindBy(xpath = "//input[@id='gcmproject']")
	WebElement gcmPrjInput;

	@FindBy(xpath = "//input[@id='androidapikey']")
	WebElement androidapikeyInput;

	@FindBy(xpath = "//a[@id='generateContainerAppBtnId']") // *[@id="orgOTATable"]/tbody
	WebElement generateContainerAppBtn;

	@FindBys({ @FindBy(xpath = "//div[@id='otalinks']"), @FindBy(xpath = "//a[contains(text(), 'Refresh')]") })
	WebElement otaRefreshBtn;

	@FindBy(xpath = "//div[@id='otalinks']//a[text()='Configuration']")
	WebElement configBtn;

	@FindBy(xpath = "//div[@id='splash_container']")
	public WebElement splashContainer;
	@FindBy(xpath = "//input[@id='ota_theme_file']")
	WebElement uploadThemeFile;
	@FindBy(xpath = "//input[@id='ota_hdr_file']")
	WebElement uploadLogoFile;
	@FindBy(xpath = "//a[@id='saveContainerAppConfigBtnId']")
	public WebElement saveConfigFiles;

	/*// Configurator Tabs

	@FindBy(xpath = "//tbody[@id='tenantBody']")
	WebElement tenantBodyTbl;

	@FindBy(xpath = "//form[@id='mailForm']")
	WebElement mailForm;

	@FindBy(xpath = "//tbody[@id='schedulerBody']")
	WebElement schedulerBdyTbl;

	@FindBy(xpath = "//tbody[@id='logsTableBody']")
	WebElement logsBdyTbl;

	@FindBy(xpath = "//tbody[@id='pluginBody']")
	WebElement pluginBody;

	@FindBy(xpath = "//*[@id='connectivity']/table/tbody/tr[4]/td/div/table/tbody")
	WebElement connectionTbody;

	@FindBy(xpath = "//*[@id='projectmanagement']/table/tbody")
	WebElement prjMgntTbody;

	@FindBy(xpath = "//*[@id='roleBody']")
	WebElement roleTbody;

	@FindBy(xpath = "//*[@id='userBody']")
	WebElement userTbody;

	@FindBy(xpath = "//*[@id='organization']/table/tbody")
	WebElement orgTbody;

	@FindBy(xpath = "//*[@id='createtenant']/table/tbody")
	WebElement createTenantTbody;

	@FindBy(xpath = "//*[@id='subscriptionTable']/tbody")
	WebElement subscriptionTbody;

	@FindBy(xpath = "//*[@id='alertPopupModal']") // *[@id="alertPopupModal"]/div[3]/a
	public WebElement alertPopupModal;

	@FindBy(xpath = "//*[@id='alertMessage']") // *[@id="alertPopupModal"]/div[3]/a
	WebElement alertMessage;

	@FindBy(xpath = "//*[@id='alertPopupModal']/div[3]/a")
	WebElement alertPopupOkBtn;*/

	@FindBy(xpath = "//*[@id='orgOTATable']/tbody")
	WebElement orgOTATbody;

	@FindBys({ @FindBy(xpath = "//ul[@id='configurator_tab_header']"),
			@FindBy(xpath = "//label[@title='Organization']") })
	WebElement orgTab;

	@FindBys({ @FindBy(xpath = "//ul[@id='configurator_tab_header']"),
			@FindBy(xpath = "//label[@title='Platform Role']") })
	WebElement proleTab;

	@FindBys({ @FindBy(xpath = "//ul[@id='configurator_tab_header']"),
			@FindBy(xpath = "//label[@title='Platform User']") })
	WebElement puserTab;

	@FindBys({ @FindBy(xpath = "//ul[@id='configurator_tab_header']"),
			@FindBy(xpath = "//label[@title='Projects & Transports']") })
	WebElement prjTransportTab;

	@FindBys({ @FindBy(xpath = "//ul[@id='configurator_tab_header']"),
			@FindBy(xpath = "//label[@title='Connectivity']") })
	WebElement connectivityTab;

	@FindBys({ @FindBy(xpath = "//ul[@id='configurator_tab_header']"),
			@FindBy(xpath = "//label[@title='Connection Manager']") })
	WebElement connectionMngrTab;

	@FindBys({ @FindBy(xpath = "//ul[@id='configurator_tab_header']"), @FindBy(xpath = "//label[@title='Logs']") })
	WebElement logsTab;

	@FindBys({ @FindBy(xpath = "//ul[@id='configurator_tab_header']"),
			@FindBy(xpath = "//label[@title='Synchronize']") })
	WebElement syncTab;

	@FindBys({ @FindBy(xpath = "//ul[@id='configurator_tab_header']"),
			@FindBy(xpath = "//label[@title='Notification']") })
	WebElement notifyTab;

	@FindBys({ @FindBy(xpath = "//ul[@id='configurator_tab_header']"),
			@FindBy(xpath = "//label[@title='BOS Scheduler']") })
	WebElement schedulerTab;

	@FindBys({ @FindBy(xpath = "//ul[@id='configurator_tab_header']"), @FindBy(xpath = "//label[@title='Mail']") })
	WebElement mailTab;

	@FindBys({ @FindBy(xpath = "//ul[@id='configurator_tab_header']"),
			@FindBy(xpath = "//label[@title='Tenant Administration']") })
	WebElement tenantAdminTab;

	@FindBys({ @FindBy(xpath = "//ul[@id='configurator_tab_header']"),
			@FindBy(xpath = "//label[@title='Create Tenant']") })
	WebElement createTenantTab;

	@FindBys({ @FindBy(xpath = "//ul[@id='configurator_tab_header']"),
			@FindBy(xpath = "//label[@title='Subscriptions']") })
	WebElement subscriptionTab;

	@FindBys({ @FindBy(xpath = "//div[@id='configurator_tab_cont']"),
			@FindBy(xpath = "//div[@class='lableleftallign' and contains(text(), 'Tenant Administration')]") })
	WebElement tenantAdminLbl;

	// Create Tenant//
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='tenantForm1']//input[@id='compname']")
	WebElement tenantNme;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='tenantForm1']//input[@id='msp']")
	WebElement msp;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='tenantForm1']//select[@id='tenantDateFormat']")
	WebElement tenantDateFormat;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='tenantForm1']//select[@id='tenantCurFormat']")
	WebElement tenantCurrencyFormat;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='tenantForm1']//input[@id='tenantDunsNumber']")
	WebElement tenantDunsNum;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='tenantForm1']//select[@id='tenantTimeZone']")
	WebElement tenantTimeZone;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='tenantForm1']//select[@id='tenantLanguage']")
	WebElement tenantLang;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='tenantForm1']//label[@id='compAddress']")
	WebElement tenantAddLink;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='contactAddressForm']//input[@id='contactAddrStreet']")
	WebElement contactAddrStreet;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='contactAddressForm']//input[@id='contactAddrState']")
	WebElement contactAddrState;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='contactAddressForm']//input[@id='contactAddrCountry']")
	WebElement contactAddrCountry;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='contactAddressForm']//input[@id='contactAddrCity']")
	WebElement contactAddrCity;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='contactAddressForm']//input[@id='contactAddrZip']")
	WebElement contactAddrZip;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='contactAddressForm']//a[@id='saveAddressButton']")
	WebElement saveAddressButton;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='tenantForm1']//label[@id='mainContact']")
	WebElement tenantMainContactLink;
	@FindBy(xpath = "//div[@id='contactCreate']//select[@id='contactSalutation']")
	WebElement contactSalutation;
	@FindBy(xpath = "//div[@id='contactCreate']//input[@id='contactFName']")
	WebElement contactFName;
	@FindBy(xpath = "//div[@id='contactCreate']//input[@id='contactLName']")
	WebElement contactLName;
	@FindBy(xpath = "//div[@id='contactCreate']//input[@id='contactEmail']")
	WebElement contactEmail;
	@FindBy(xpath = "//div[@id='contactCreate']//input[@id='contactDept']")
	WebElement contactDept;
	@FindBy(xpath = "//div[@id='contactCreate']//input[@id='contactTitle']")
	WebElement contactTitle;
	@FindBy(xpath = "//div[@id='contactCreate']//input[@id='contactOffice']")
	WebElement contactOffice;
	@FindBy(xpath = "//div[@id='contactCreate']//input[@id='contactMobile']")
	WebElement mobilePhNo;
	@FindBy(xpath = "//div[@id='contactCreate']//input[@id='contactStreet']")
	WebElement contactStreet;
	@FindBy(xpath = "//div[@id='contactCreate']//input[@id='contactCity']")
	WebElement contactCity;
	@FindBy(xpath = "//div[@id='contactCreate']//input[@id='contactState']")
	WebElement contactState;
	@FindBy(xpath = "//div[@id='contactCreate']//input[@id='contactZip']")
	WebElement contactZip;
	@FindBy(xpath = "//div[@id='contactCreate']//input[@id='contactCountry']")
	WebElement contactCountry;
	@FindBy(xpath = "//div[@id='contactCreate']//a[@id='saveContactButton']")
	WebElement saveContactButton;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='tenantForm1']//label[@id='enterSubscriptionDetailsLink']")
	WebElement tenantPackageLink;
	@FindBy(xpath = "//div[@id='tenantSubscriptionModal']//select[@id='packageId']")
	WebElement tenantPackage;
	@FindBy(xpath = "//div[@id='tenantSubscriptionModal']//a[@id='saveContactButton']")
	WebElement tenantPackSaveBtn;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='tenantForm2']//select[@id='tenantSalutation']")
	WebElement tenantSalutation;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='tenantForm2']//input[@id='tenantFN']")
	WebElement tenantFN;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='tenantForm2']//input[@id='tenantLN']")
	WebElement tenantLN;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='tenantForm2']//input[@id='tenantTitle']")
	WebElement tenantTitle;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='tenantForm2']//input[@id='tenantEmail']")
	WebElement tenantEmail;
	@FindBy(xpath = "//div[@id='createtenant']//form[@id='tenantForm2']//input[@id='tenantLoginName']")
	WebElement tenantLoginName;
	@FindBy(xpath = "//*[@id='createtenant']//a[@onclick='javascript:createTenant();']")
	WebElement tenantSaveBtn;
	


	public PlatformConfigPage() {

		super();
		hmePg = new HomePage();
	}

	
	
	public PlatformConfigPage doProxyLogin(){
		//  //*[@id="tenantBody"]/tr[1]/td[2]/a
		if (tenantBodyTbl.findElements(By.tagName("tr")).size() > 0) {
			WebElement tenantId = tenantBodyTbl.findElement(By.xpath("tr[1]/td[2]/a"));
			try{
				tenantId.click();
				Util.delay(2000);
			}catch(UnhandledAlertException e){
				
			}finally{
				Util.closeAlert();
			}
		}
		return this;
	}
	
	public PlatformConfigPage editTenantSubcription(){
		//tr[1]/td[1]/input
		if (tenantBodyTbl.findElements(By.tagName("tr")).size() > 0) {
			WebElement tenantRadio = tenantBodyTbl.findElement(By.xpath("tr[1]/td[1]/input"));
			tenantRadio.click();
			Util.delay(2000);
			Assert.assertTrue(editSubscription.isDisplayed());
			log.info("Edit Subscription Button Exits");
			editSubscription.click();
		
		}
		return this;
	}

	

	

	public OrganizationPage openOrgTab() throws Exception {

		try {
			orgTab.click();
			int tryCount = 0;
			while (tryCount < 20) {
				if (orgTbody.findElements(By.tagName("tr")).size() > 0) {
					// System.out.println("Organization page loaded");
					log.info("Organization page loaded");
					Util.getscreenshot("Organization");
					break;
				} else {
					Util.delay(1000);
					tryCount++;
				}

				if (tryCount > 20) {
					// System.out.println("Organization page not loaded");
					log.debug("Organization page not loaded");
					Util.getscreenshot("OrganizationFail");
				}
			}
		} catch (Exception e) {
			log.info("Org tab not found");
			throw (e);
		}
		return new OrganizationPage();
	}

	public PlatformUserPage openPuserTab() throws Exception {
		try {
			puserTab.click();
			int tryCount = 0;
			while (tryCount < 20) {
				if (userTbody.findElements(By.tagName("tr")).size() > 0) {
					// System.out.println("Platform user page loaded");
					log.info("Platform user page loaded");
					Util.getscreenshot("Platform User");
					break;
				} else {
					Util.delay(1000);
					tryCount++;
				}

				if (tryCount > 20) {
					// System.out.println("Platform user page not loaded");
					log.debug("Platform user page not loaded");
					Util.getscreenshot("PlatformUserFail");
				}
			}
		} catch (Exception e) {
			log.info("Platform user tab not found");
			throw (e);
		}
		return new PlatformUserPage();
	}

	public PlatformRolePage openProleTab() throws Exception {
		try {
			proleTab.click();
			int tryCount = 0;
			while (tryCount < 20) {
				if (roleTbody.findElements(By.tagName("td")).size() > 0) {
					// System.out.println("Platform role page loaded");
					log.info("Platform role page loaded");
					Util.getscreenshot("Platform Role");
					break;
				} else {
					Util.delay(1000);
					tryCount++;
				}

				if (tryCount > 20) {
					// System.out.println("Platform role page not loaded");
					log.debug("Platform role page not loaded");
					Util.getscreenshot("PlatformRoleFail");
				}
			}
		} catch (Exception e) {
			log.info("Platform role tab not found");
			throw (e);
		}
		return new PlatformRolePage();
	}

	public ProjectTransportPage openPrjTransportTab() throws Exception {
		try {
			prjTransportTab.click();
			int tryCount = 0;
			while (tryCount < 20) {
				if (prjMgntTbody.findElements(By.tagName("tr")).size() > 0) {
					// System.out.println("Project & Transport page loaded");
					log.info("Project & Transport page loaded");
					Util.getscreenshot("Project & Transport");
					break;
				} else {
					Util.delay(1000);
					tryCount++;
				}

				if (tryCount > 20) {
					// System.out.println("Project & Transport page not
					// loaded");
					log.debug("Project & Transport page not loaded");
					Util.getscreenshot("Project&TransportFail");
				}
			}
		} catch (Exception e) {
			log.info("Projects and Transport tab not found");
			throw (e);
		}
		return new ProjectTransportPage();
	}

	public ConnectivityPage openConnectivityTab() throws Exception {
		try {
			connectivityTab.click();
			int tryCount = 0;
			while (tryCount < 20) {
				if (connectionTbody.findElements(By.tagName("tr")).size() > 0) {
					// System.out.println("Connection page loaded");
					log.info("Connection page loaded");
					Util.getscreenshot("Connection");
					break;
				} else {
					Util.delay(1000);
					tryCount++;
				}

				if (tryCount > 20) {
					// System.out.println("Connection page not loaded");
					log.debug("Connection page not loaded");
					Util.getscreenshot("ConnectionFail");
				}
			}
		} catch (Exception e) {
			log.info("Connectivity tab not found");
			throw (e);
		}
		return new ConnectivityPage();
	}

	public void closePopupIfPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(alertPopupModal));
			String message = alertMessage.getText();
			alertPopupOkBtn.click();
			log.debug(message);
			Util.delay(2000);
		} catch (Exception e) {
			log.info("No popup modal open");

		}
	}

	public ConnectionManagerPage openConnectionMngrTab() throws Exception {
		try {
			connectionMngrTab.click();
			closePopupIfPresent();
			int tryCount = 0;
			while (tryCount < 20) {
				if (pluginBody.findElements(By.tagName("tr")).size() > 0) {
					// System.out.println("Plugin page loaded");
					log.info("Plugin page loaded");
					Util.getscreenshot("Logs");
					break;
				} else {
					Util.delay(1000);
					tryCount++;
				}

				if (tryCount > 20) {
					// System.out.println("Plugin page not loaded");
					log.debug("Plugin page not loaded");
					Util.getscreenshot("PluginFail");
				}
			}
		} catch (Exception e) {
			log.info("Connection manager tab not found");
			throw (e);
		}
		return new ConnectionManagerPage();
	}

	public LogsPage openLogsTab() throws Exception {
		try {
			logsTab.click();
			int tryCount = 0;
			while (tryCount < 20) {
				if (logsBdyTbl.findElements(By.tagName("tr")).size() > 0) {
					// System.out.println("Logs page loaded");
					log.info("Logs page loaded");
					Util.getscreenshot("Logs");
					break;
				} else {
					Util.delay(1000);
					tryCount++;
				}

				if (tryCount > 20) {
					System.out.println("Logs page not loaded");
					log.debug("Logs page not loaded");
					Util.getscreenshot("LogsFail");
				}
			}
		} catch (Exception e) {
			log.info("Logs tab not found");
			throw (e);
		}
		return new LogsPage();
	}

	public PlatformConfigPage openSyncTab() throws Exception {
		try {
			syncTab.click();
			log.info("Sync tab opened");
		} catch (Exception e) {
			log.info("sync tab not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage openNotifyTab() throws Exception {
		try {
			notifyTab.click();
			log.info("Notification tab opened");
		} catch (Exception e) {
			log.info("Notification tab not found");
			throw (e);
		}
		return this;
	}

	public SchedulerPage openSchedulerTab() throws Exception {
		try {
			schedulerTab.click();
			int tryCount = 0;
			while (tryCount < 20) {
				if (schedulerBdyTbl.findElements(By.tagName("tr")).size() > 0) {
					// System.out.println("Scheduler page loaded");
					log.info("Scheduler page loaded");
					Util.getscreenshot("Scheduler");
					break;
				} else {
					Util.delay(1000);
					tryCount++;
				}

				if (tryCount > 20) {
					// System.out.println("Scheduler page not loaded");
					log.debug("Scheduler page not loaded");
					Util.getscreenshot("SchedulerFail");
				}
			}
		} catch (Exception e) {
			log.info("Scheduler tab not found");
			throw (e);
		}
		return new SchedulerPage();
	}

	public MailPage openMailTab() throws Exception {
		try {
			mailTab.click();
			int tryCount = 0;
			while (tryCount < 20) {
				if (mailForm.findElements(By.tagName("tr")).size() > 0) {
					// System.out.println("Mail page loaded");
					log.info("Mail page loaded");
					Util.getscreenshot("Mail");
					break;
				} else {
					Util.delay(1000);
					tryCount++;
				}

				if (tryCount > 20) {
					// System.out.println("Mail page not loaded");
					log.debug("Mail page not loaded");
					Util.getscreenshot("MailFail");
				}
			}
		} catch (Exception e) {
			log.info("Mail tab not found");
			throw (e);
		}
		return new MailPage();
	}

	public TenantAdminPage openTenantAdminTab() throws Exception {
		try {
			tenantAdminTab.click();

			int tryCount = 0;
			while (tryCount < 20) {
				if (tenantBodyTbl.findElements(By.tagName("tr")).size() > 0) {
					// System.out.println("Tenant Admin page loaded");
					log.info("Tenant Admin page loaded");
					Util.getscreenshot("TenantAdmin");
					break;
				} else {
					Util.delay(1000);
					tryCount++;
				}

				if (tryCount > 20) {
					// System.out.println("Tenant Admin page not loaded");
					log.debug("Tenant Admin page not loaded");
					Util.getscreenshot("TenantAdminFail");
				}
			}
		} catch (Exception e) {
			log.info("Tenant admin tab not found");
			throw (e);
		}

		/*
		 * int tryCount = 0; boolean desiredResponseReceived = false; while
		 * (desiredResponseReceived == false && tryCount < 20) { String
		 * readyState = (String) ((JavascriptExecutor)driver).executeScript(
		 * "return xhr.readyState;"); System.out.println(readyState); if
		 * (readyState.equals("4")) { desiredResponseReceived = true; } else {
		 * Util.delay(1000); tryCount++; } }
		 */
		return new TenantAdminPage();
	}

	public PlatformConfigPage openCreateTenantTab() throws Exception {
		try {
			createTenantTab.click();
			int tryCount = 0;
			while (tryCount < 20) {
				if (createTenantTbody.findElements(By.tagName("tr")).size() > 0) {
					// System.out.println("Create tenant page loaded");
					log.info("Create tenant page loaded");
					Util.getscreenshot("CreateTenant");
					break;
				} else {
					Util.delay(1000);
					tryCount++;
				}

				if (tryCount > 20) {
					// System.out.println("Create Tenant page not loaded");
					log.debug("Create Tenant page not loaded");
					Util.getscreenshot("CreateTenantFail");
				}
			}
		} catch (Exception e) {
			log.info("Create tenant tab not found");
			throw (e);
		}
		return this;
	}

	public SubscriptionPage openSubscriptionTab() throws Exception {
		try {
			subscriptionTab.click();
			int tryCount = 0;
			while (tryCount < 20) {
				if (subscriptionTbody.findElements(By.tagName("tr")).size() > 0) {
					// System.out.println("Subscription page loaded");
					log.info("Subscription page loaded");
					Util.getscreenshot("Subscription");
					break;
				} else {
					Util.delay(1000);
					tryCount++;
				}

				if (tryCount > 20) {
					// System.out.println("Subscription page not loaded");
					log.debug("Subscription page not loaded");
					Util.getscreenshot("SubscriptionFail");
				}
			}
		} catch (Exception e) {
			log.info("Subscription tab not found");
			throw (e);
		}
		return new SubscriptionPage();
	}

	public PlatformConfigPage openOTATab() throws Exception {
		try {
			openOrgTab();
			WebElement otaLnk = orgTbody.findElement(By.linkText("OTA"));
			otaLnk.click();
			Util.delay(2000);
			if (alertPopupModal.isDisplayed()) {
				// System.out.println(alertMessage.getText());
				log.info(alertMessage.getText());
				alertPopupOkBtn.click();
			} else {
				System.out.println("No popup alert");
			}
		} catch (Exception e) {
			log.info("OTA tab not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage clickCreateBtn() throws Exception {
		try {
			Util.delay(2000);
			otaCreateBtn.click();
			log.info("Created button clicked");
			Util.delay(2000);
			// System.out.println(driver.findElements(By.xpath("//input[@id='ota_theme_file']")).size());
			/*
			 * if ((addContainerAppForm.isDisplayed())) { log.info(
			 * "Add OTA form opened"); } else { log.info(
			 * "Add OTA form not opened"); throw new CustomException(
			 * "Add OTA form not opened");
			 * 
			 * }
			 */
		} catch (Exception e) {
			log.info("Create button not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterAppId(String appId) throws Exception {
		try {
			appIdInputBx.sendKeys(appId);
			log.info("App Id entered");
			Util.delay(2000);
		} catch (Exception e) {
			log.info("App Id input field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterAppName(String appName) throws Exception {
		try {
			appNameInputBx.sendKeys(appName);
			log.info("App name entered");
			Util.delay(2000);
		} catch (Exception e) {
			log.info("App name input field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage selectMicroApp(String microapp) throws Exception {
		boolean found = true;
		try {
			WebElement microappTbl = addContainerAppForm.findElement(By.id("microappstbl"));
			List<WebElement> microappRows = microappTbl.findElements(By.tagName("tr"));

			for (WebElement row : microappRows) {
				List<WebElement> td = row.findElements(By.tagName("td"));
				for (WebElement td1 : td) {
					if (td1.getText().equalsIgnoreCase(microapp)) {
						row.findElement(By.tagName("input")).click();
						log.info("Micro app selected");
						found = false;
						break;
					}
				}
			}
			if (found) {
				System.out.println("Micro app not found in the list");
				log.info("Micro app not found in the list");
			}
		} catch (Exception e) {
			log.info("Micro app checkbox not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage checkExternalLinks() throws Exception {
		try {
			List<WebElement> lbls = addContainerAppForm.findElements(By.tagName("label"));
			for (WebElement lbl : lbls) {
				if (lbl.getText().equalsIgnoreCase("Advanced")) {
					lbl.click();
					externalLinksChkBx.click();
					log.info("External link checkbox selected");
					break;
				}
			}
		} catch (Exception e) {
			log.info("External link check failed");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage uploadAppIcon() throws Exception {
		try {
			selectIconFile.sendKeys(System.getProperty("user.dir") + "\\resources\\appicon.png");
			log.info("App icon uploaded");
		} catch (Exception e) {
			log.info("App icon upload failed");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage uploadSplashIcon() throws Exception {
		try {
			List<WebElement> lbls = addContainerAppForm.findElements(By.tagName("label"));
			for (WebElement lbl : lbls) {
				if (lbl.getText().equalsIgnoreCase("Splash Screen")) {
					lbl.click();
					log.info("Expanded Splash screen tab");
					uploadTableIcon.sendKeys(System.getProperty("user.dir") + "\\resources\\tablet.png");
					log.info("Uploaded splash screen for tablet");
					uploadIphone4Icon.sendKeys(System.getProperty("user.dir") + "\\resources\\iphone4.png");
					log.info("Uploaded splash screen for iphone4");
					uploadIphone5Icon.sendKeys(System.getProperty("user.dir") + "\\resources\\iphone5.png");
					log.info("Uploaded splash screen for iphone5");
					uploadIphone6Icon.sendKeys(System.getProperty("user.dir") + "\\resources\\iphone6.png");
					log.info("Uploaded splash screen for iphone6");
					uploadIphone6PIcon.sendKeys(System.getProperty("user.dir") + "\\resources\\iphone6p.png");
					log.info("Uploaded splash screen for iphone6p");
					uploadAndroidIcon.sendKeys(System.getProperty("user.dir") + "\\resources\\android.png");
					log.info("Uploaded splash screen for Android");
					break;
				}
			}
		} catch (Exception e) {
			log.info("Splash screen upload failed");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage uploadConfigurationFiles() throws Exception {
		try {
			uploadThemeFile.sendKeys(System.getProperty("user.dir") + "\\resources\\cypress.css");
			log.info("CSS file uploaded");
			uploadLogoFile.sendKeys(System.getProperty("user.dir") + "\\resources\\qsilogo.png");
			log.info("Header logo uploaded");
			saveConfigFiles.click();
			log.info("Save config button clicked");
		} catch (Exception e) {
			log.info("Configuration files upload failed");
			throw (e);
		}

		return this;
	}

	public PlatformConfigPage uploadAppleCertificates(String push) throws Exception {
		try {
			List<WebElement> lbls = addContainerAppForm.findElements(By.tagName("label"));
			for (WebElement lbl : lbls) {
				if (lbl.getText().equalsIgnoreCase("Apple")) {
					lbl.click();
					log.info("Expanded Apple tab");
					uploadAppleCertificate
							.sendKeys(System.getProperty("user.dir") + "\\resources\\apple_certificate.p12");
					log.info("Uploaded apple certificate");
					applePwdInput.sendKeys("sealindia");
					log.info("Entered apple certificate password");
					uploadAppleMobileProv
							.sendKeys(System.getProperty("user.dir") + "\\resources\\General.mobileprovision");
					log.info("Uploaded app mobile provision");
					if (push.equalsIgnoreCase("Y")) {
						uploadPushCertificate.sendKeys(System.getProperty("user.dir") + "\\resources\\push.p12");
						log.info("Uploaded apple push certificate");
						pushPwdInput.sendKeys("sealindia");
						log.info("Entered apple push password");
					}
					break;
				}
			}
		} catch (Exception e) {
			log.info("Apple certificate upload failed");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage uploadAndroidCertificates(String push) throws Exception {
		boolean found = false;
		try {
			List<WebElement> lbls = addContainerAppForm.findElements(By.tagName("label"));
			for (WebElement lbl : lbls) {
				if (lbl.getText().equalsIgnoreCase("Android")) {
					lbl.click();
					log.info("Expanded Android tab");
					if (push.equalsIgnoreCase("Y")) {
						uploadKeystoreCertificate
								.sendKeys(System.getProperty("user.dir") + "\\resources\\push.keystore");
						log.info("Uploaded android push keystore file");
						keystorePwdInput.sendKeys("apps1234");
						log.info("Entered android keystore password");
					} else {
						uploadKeystoreCertificate
								.sendKeys(System.getProperty("user.dir") + "\\resources\\android.keystore");
						log.info("Uploaded android keystore file");
						keystorePwdInput.sendKeys("apps!@34");
						log.info("Entered android keystore password");
					}

					gcmPrjInput.sendKeys("269748727998");
					log.info("Entered GCM project number");
					androidapikeyInput.sendKeys("AIzaSyC7ojWWeCO17zYhsxiAbxFuVwh_qRF2aAQ");
					log.info("Entered API key");
					break;
				}
			}
		} catch (Exception e) {
			log.info("Upload android certificates failed");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage clickGenerate() throws Exception {
		String message = "";
		try {
			generateContainerAppBtn.click();
			log.info("Generate button clicked");

			while (true) {
				if (!generateContainerAppBtn.isDisplayed()) {
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
			log.info("Generate button not fould");
			throw (e);
		}
		return this;
	}

	public String checkOTAStatus(String appName) throws Exception {
		String status = "";
		try {
			List<WebElement> rows = orgOTATbody.findElements(By.tagName("tr"));
			for (WebElement row : rows) {
				List<WebElement> tds = row.findElements(By.tagName("td"));
				if (tds.get(1).getText().equalsIgnoreCase(appName)) {
					status = tds.get(4).getText();
				}
			}
		} catch (Exception e) {
			log.info("OTA status check failed");
			throw (e);
		}
		return status;
	}

	public PlatformConfigPage refreshOTATable() throws Exception {
		try {
			otaRefreshBtn.click();
			log.info("Refresh button clicked");
		} catch (Exception e) {
			log.info("Refresh button not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage openSplashContainer() throws Exception {
		try {
			configBtn.click();
			log.info("Configuration button clicked");
		} catch (Exception e) {
			log.info("Configuration button not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterTenantNme(String name) throws Exception {
		try {
			tenantNme.sendKeys(name);
			log.info("Tenant name entered");
		} catch (Exception e) {
			log.info("Tenant name field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enableMSP() throws Exception {
		try {
			msp.click();
			log.info("MSP enabled");
		} catch (Exception e) {
			log.info("MSP checkbox not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage selectTenantDateFmt() throws Exception {
		try {
			Select select = new Select(tenantDateFormat);
			select.selectByIndex(1);
			log.info("Tenant dateformat selected");
		} catch (Exception e) {
			log.info("Tenant dateformat field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage selectTenantCurrencyFmt() throws Exception {
		try {
			Select select = new Select(tenantCurrencyFormat);
			select.selectByIndex(1);
			log.info("Tenant currency format selected");
		} catch (Exception e) {
			log.info("Tenant currency format field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterDunsNum(String duns) throws Exception {
		try {
			tenantDunsNum.sendKeys(duns);
			log.info("DUNS number entered");
		} catch (Exception e) {
			log.info("DUNS field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage selectTenantTimezone() throws Exception {
		try {
			Select select = new Select(tenantTimeZone);
			select.selectByIndex(1);
			log.info("Tenant timezone selected");
		} catch (Exception e) {
			log.info("TImezone field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage selectTenantLang() throws Exception {
		try {
			Select select = new Select(tenantLang);
			select.selectByIndex(1);
			log.info("Tenant language selected");
		} catch (Exception e) {
			log.info("Language field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage openTenantAddForm() throws Exception {
		try {
			tenantAddLink.click();
			log.info("Tenant address form opened");
		} catch (Exception e) {
			log.info("Tenant add link not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterContactStreet(String street) throws Exception {
		try {
			contactAddrStreet.sendKeys(street);
			log.info("Tenant contact street entered");
		} catch (Exception e) {
			log.info("Tenant contact street field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterContactState(String state) throws Exception {
		try {
			contactAddrState.sendKeys(state);
			log.info("Tenant contact state entered");
		} catch (Exception e) {
			log.info("Tenant contact state field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterContactCountry(String country) throws Exception {
		try {
			contactAddrCountry.sendKeys(country);
			log.info("Tenant contact country entered");
		} catch (Exception e) {
			log.info("Tenant contact country field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterContactCity(String city) throws Exception {
		try {
			contactAddrCity.sendKeys(city);
			log.info("Tenant contact city entered");
		} catch (Exception e) {
			log.info("Tenant contact city field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterContactZip(String zip) throws Exception {
		try {
			contactAddrZip.sendKeys(zip);
			log.info("Tenant contact zip entered");
		} catch (Exception e) {
			log.info("Tenant contact zip field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage saveContactAdd() throws Exception {
		try {
			saveAddressButton.click();
			log.info("Tenant contact address saved");
		} catch (Exception e) {
			log.info("Tenant contact address save button not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage openPrimaryContactForm() throws Exception {
		try {
			Util.waitForElement(tenantMainContactLink);
			Util.delay(1000);
			tenantMainContactLink.click();
			log.info("Prmary contact form opened");
		} catch (Exception e) {
			log.info("Primary Contact form not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage selectPrimaryConactSalutation() throws Exception {
		try {
			Select select = new Select(contactSalutation);
			select.selectByIndex(1);
			log.info("Primary contact salutation selected");
		} catch (Exception e) {
			log.info("Primary Contact salutation field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterPrimaryContactFname(String fname) throws Exception {
		try {
			contactFName.sendKeys(fname);
			log.info("Primary contact fname entered");
		} catch (Exception e) {
			log.info("Primary Contact fname field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterPrimaryContactLname(String lname) throws Exception {
		try {
			contactLName.sendKeys(lname);
			log.info("Primary contact lname entered");
		} catch (Exception e) {
			log.info("Primary Contact lname field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterPrimaryContactEmail(String email) throws Exception {
		try {
			contactEmail.sendKeys(email);
			log.info("Primary contact email entered");
		} catch (Exception e) {
			log.info("Primary Contact email field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterPrimaryContactDept(String dept) throws Exception {
		try {
			contactDept.sendKeys(dept);
			log.info("Primary contact department entered");
		} catch (Exception e) {
			log.info("Primary contact department field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterPrimaryContactTitle(String title) throws Exception {
		try {
			contactTitle.sendKeys(title);
			log.info("Primary contact title entered");
		} catch (Exception e) {
			log.info("Primary contact title field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterPrimaryContactOffPh(String OffPh) throws Exception {
		try {
			contactOffice.sendKeys(OffPh);
			log.info("Primary contact offline ph entered");
		} catch (Exception e) {
			log.info("Primary contact offline ph field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterPrimaryContactCellPh(String CellPh) throws Exception {
		try {
			mobilePhNo.sendKeys(CellPh);
			log.info("Primary contact cell ph entered");
		} catch (Exception e) {
			log.info("Primary contact cell ph field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterPrimaryContactStreet(String street) throws Exception {
		try {
			contactStreet.sendKeys(street);
			log.info("Primary contact street entered");
		} catch (Exception e) {
			log.info("Primary contact street field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterPrimaryContactCity(String city) throws Exception {
		try {
			contactCity.sendKeys(city);
			log.info("Primary contact city entered");
		} catch (Exception e) {
			log.info("Primary contact city field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterPrimaryContactState(String state) throws Exception {
		try {
			contactState.sendKeys(state);
			log.info("Primary contact state entered");
		} catch (Exception e) {
			log.info("Primary contact state field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterPrimaryContactZip(String zip) throws Exception {
		try {
			contactZip.sendKeys(zip);
			log.info("Primary contact zip entered");
		} catch (Exception e) {
			log.info("Primary contact zip field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterPrimaryContactCountry(String country) throws Exception {
		try {
			contactCountry.sendKeys(country);
			log.info("Primary contact country entered");
		} catch (Exception e) {
			log.info("Primary contact country field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage savePrimaryContact() throws Exception {
		try {
			saveContactButton.click();
			log.info("Primary contact saved");
		} catch (Exception e) {
			log.info("Primary contact save button not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage openTenantPackageForm() throws Exception {
		try {
			Util.waitForElement(tenantPackageLink);
			Util.delay(1000);
			tenantPackageLink.click();
			log.info("Package form opened");
		} catch (Exception e) {
			log.info("Package link not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage selectPackage(String pack) throws Exception {
		try {
			Select select = new Select(tenantPackage);
			select.selectByVisibleText(pack);
			;
			log.info("Package selected");
		} catch (Exception e) {
			log.info("Package field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage savePackage() throws Exception {
		try {
			tenantPackSaveBtn.click();
			log.info("Package saved");
		} catch (Exception e) {
			log.info("Package save button not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage selectTenantAdminSalutation(String salutation) throws Exception {
		try {
			Select select = new Select(tenantSalutation);
			select.deselectByVisibleText(salutation);
			log.info("Tenant admin salutation selected");
		} catch (Exception e) {
			log.info("Tenant admin salutation field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterTenantAdminFname(String fname) throws Exception {
		try {
			tenantFN.sendKeys(fname);
			log.info("Tenant admin fname emtered");
		} catch (Exception e) {
			log.info("Tenant admin fname field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterTenantAdminLname(String lname) throws Exception {
		try {
			tenantLN.sendKeys(lname);
			log.info("Tenant admin fname emtered");
		} catch (Exception e) {
			log.info("Tenant admin lname field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterTenantAdminTitle(String title) throws Exception {
		try {
			tenantTitle.sendKeys(title);
			log.info("Tenant admin title emtered");
		} catch (Exception e) {
			log.info("Tenant admin title field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterTenantAdminEmail(String email) throws Exception {
		try {
			tenantEmail.sendKeys(email);
			log.info("Tenant admin email emtered");
		} catch (Exception e) {
			log.info("Tenant admin email field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage enterTenantAdminLoginId(String loginId) throws Exception {
		try {
			tenantLoginName.sendKeys(loginId);
			log.info("Tenant admin loginId emtered");
		} catch (Exception e) {
			log.info("Tenant admin loginId field not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage saveTenant() throws Exception {
		try {
			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].scrollIntoView(true);",
			// tenantSaveBtn);
			Util.scrollToElement(tenantSaveBtn);
			tenantSaveBtn.click();
			log.info("New tenant saved");
		} catch (Exception e) {
			log.info("Tenant save button not found");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage setTenantInfo(String tenantName) throws Exception {
		try {
			enterTenantNme(tenantName);
			enableMSP();
			selectTenantDateFmt();
			selectTenantCurrencyFmt();
			enterDunsNum("456789266");
			selectTenantTimezone();
			selectTenantLang();
		} catch (Exception e) {
			log.info("Tenant info not set");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage setTenantAddress() throws Exception {
		try {
			openTenantAddForm();
			enterContactStreet("Rose wood street");
			enterContactState("Illinois");
			enterContactCountry("USA");
			enterContactCity("New York");
			enterContactZip("60605");
			saveContactAdd();
		} catch (Exception e) {
			log.info("Tenant address not set");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage setMainContactInfo() throws Exception {
		try {
			openPrimaryContactForm();
			selectPrimaryConactSalutation();
			enterPrimaryContactFname("Kannan");
			enterPrimaryContactLname("Dharani");
			enterPrimaryContactEmail("kannan.dharani@appsfreedom.com");
			enterPrimaryContactDept("Quality");
			enterPrimaryContactTitle("QA");
			enterPrimaryContactOffPh("45869321");
			enterPrimaryContactCellPh("9962594852");
			enterPrimaryContactStreet("Rose wood street");
			enterPrimaryContactCity("New York");
			enterPrimaryContactState("Illinois");
			enterPrimaryContactZip("60605");
			enterPrimaryContactCountry("USA");
			savePrimaryContact();
		} catch (Exception e) {
			log.info("Primary contact info not set");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage setTenantPackage(String pack) throws Exception {
		try {
			openTenantPackageForm();
			selectPackage(pack);
			savePackage();
		} catch (Exception e) {
			log.info("Tenant package not set");
			throw (e);
		}
		return this;
	}

	public PlatformConfigPage setTenantAdminInfo(String salutation, String fname, String lname, String title,
			String email, String loginId) throws Exception {
		try {
			selectTenantAdminSalutation(salutation);
			enterTenantAdminFname(fname);
			enterTenantAdminLname(lname);
			enterTenantAdminTitle(title);
			enterTenantAdminEmail(email);
			enterTenantAdminLoginId(loginId);
			saveTenant();
		} catch (Exception e) {
			log.info("Tenant admin info not set");
			throw (e);
		}
		return this;
	}

}
