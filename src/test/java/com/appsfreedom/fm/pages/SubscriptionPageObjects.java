package com.appsfreedom.fm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubscriptionPageObjects extends BasePage {

	@FindBy(xpath="//*[@id='subscriptionTable']")
	WebElement subscriptionTbl;
	@FindBy(xpath = "//*[@id='subscriptionTable']/preceding::i[@class='icon-white icon-search'][1]")
	WebElement searchBtn;
	@FindBy(xpath = "//*[@id='subscriptionTable']/preceding::i[@class='icon-white icon-refresh'][1]")
	WebElement refreshBtn;
	@FindBy(xpath = "//*[@id='subscriptionTable']//input[@data-column='name']")
	WebElement packageNameColumn;
	@FindBy(xpath = "//div[@id='packages']//a[contains(text(),'Add Subscription')]")
	WebElement addSubscriptionBtn;	
	@FindBy(xpath="//div[@id='packageDetailsEditor']")
	WebElement packageEditorForm;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//input[@id='packageType']")
	WebElement packageName;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//select[@id='productId']")
	WebElement productId;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//select[@id='methodsId']")
	WebElement methodtId;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//table[@id='permissionsTable']//input[@id='parentPermissions_1']")
	WebElement appDesignerPermission;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//table[@id='permissionsTable']//input[@id='parentPermissions_2']")
	WebElement builderPermission;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//table[@id='permissionsTable']//input[@id='parentPermissions_3']")
	WebElement configuratorPermission;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//table[@id='permissionsTable']//input[@id='parentPermissions_15']")
	WebElement AppLibPermission;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//table[@id='permissionsTable']//input[@id='parentPermissions_21']")
	WebElement AppWatchPermission;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//table[@id='permissionsTable']//input[@id='parentPermissions_30']")
	WebElement dbDesignerPermission;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//table[@id='permissionsTable']//input[@id='parentPermissions_32']")
	WebElement processModelerPermission;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//select[@id='environments']")
	WebElement environment;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//input[@id='tenantCount']")
	WebElement tenantCount;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//input[@id='storageLimit']")
	WebElement storageLimit;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//input[@id='dataTransfer']")
	WebElement dataTransfer;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//input[@id='actionCount']")
	WebElement actionCount;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//input[@id='bosCreated']")
	WebElement bosCreated;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//input[@id='bosAccessCount']")
	WebElement bosAccessCount;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//input[@id='appCount']")
	WebElement appCount;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//input[@id='tableCount']")
	WebElement tableCount;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//input[@id='platformUserCount']")
	WebElement platformUserCount;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//input[@id='businessUserCount']")
	WebElement businessUserCount;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//input[@id='pmaxFileSize']")
	WebElement maxFileSize;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//input[@id='pmaxStorageSize']")
	WebElement maxStorageSize;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//a[@id='saveOrUpdatePack']")
	WebElement saveOrUpdatePackBtn;
	@FindBy(xpath="//div[@id='packageDetailsEditor']//a[contains(text(), 'Cancel')]")
	WebElement closePackageDetailkBtn;
	@FindBy(xpath="//div[@id='alertPopupModal']")
	WebElement alertPopup;
	@FindBy(xpath="//div[@id='alertPopupModal']//a[contains(text(), 'Ok')]")
	WebElement alertOkBtn;
	@FindBy(xpath="//div[@id='alertPopupModal']//div[@id='alertMessage']")
	WebElement alertMsg;
	@FindBy(xpath = "//*[@id='subscriptionDeleteModal']")
	WebElement deleteModal;
	@FindBy(xpath = "//*[@id='subscriptionDeleteModal']//p")
	WebElement deleteMsg;
	@FindBy(xpath = "//*[@id='subscriptionDeleteModal']//a[text()='Yes']")
	WebElement deleteYesBtn;
	@FindBy(xpath = "//*[@id='subscriptionDeleteModal']//a[text()='No']")
	WebElement deleteNoBtn;
	@FindBy(xpath="//div[@id='loadingImage']")
	WebElement spinner;
	


}
