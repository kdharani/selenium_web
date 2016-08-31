package com.appsfreedom.fm.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.appsfreedom.utils.Util;

public class OrganizationPage extends BasePage {

	private HomePage hmePg;
	//Logger log = Logger.getLogger("devpinoyLogger");
	

	// Organization page --> Address Tab
	@FindBy(xpath = "//img[@title='Edit' and @onclick='javascript:showUpdateCompanyInfo();']")
	WebElement companyInfoEditImg;
	@FindBy(xpath = "//div[@id='updateCompanyInfoModal']//select[@id='dateFormat']")
	WebElement orgDateFormat;
	@FindBy(xpath = "//div[@id='updateCompanyInfoModal']//a[@onclick='javascript:updateCompany();']")
	WebElement orgInfoUpdateBtn;
	@FindBy(xpath = "//select[@id='dateFormatDisp']")
	WebElement orgDisplayDateFormate;

	@FindBy(xpath = "//div[@id='content']//a[text()='Address']")
	WebElement orgAddressTab;
	@FindBy(xpath = "//*[@id='orgAddressTable']/tbody/tr/td[6]/a")
	WebElement editOrgAddressImg;
	@FindBy(xpath = "//div[@id='editAddress']//input[@id='street1']")
	WebElement orgStreet;
	@FindBy(xpath = "//div[@id='editAddress']//input[@id='state']")
	WebElement orgState;
	@FindBy(xpath = "//div[@id='editAddress']//input[@id='country']")
	WebElement orgCountry;
	@FindBy(xpath = "//div[@id='editAddress']//input[@id='city']")
	WebElement orgCity;
	@FindBy(xpath = "//div[@id='editAddress']//input[@id='zip']")
	WebElement orgZip;
	@FindBy(xpath = "//a[@onclick='javascript:updateAddress();']")
	WebElement orgAddUpdateBtn;
	@FindBy(xpath = "//*[@id='orgAddressTable']/tbody/tr/td[1]")
	public WebElement displayStreet;
	@FindBy(xpath = "//*[@id='orgAddressTable']/tbody/tr/td[3]")
	public WebElement displayState;
	@FindBy(xpath = "//*[@id='orgAddressTable']/tbody/tr/td[5]")
	public WebElement displayCountry;
	@FindBy(xpath = "//*[@id='orgAddressTable']/tbody/tr/td[2]")
	public WebElement displayCity;
	@FindBy(xpath = "//*[@id='orgAddressTable']/tbody/tr/td[4]")
	public WebElement displayZip;

	// Organization page --> Contacts tab
	@FindBy(xpath = "//div[@id='content']//a[text()='Contacts']")
	WebElement orgContactsTab;
	@FindBy(xpath = "//div[@id='contacts']//a[text()='Add Contact']")
	WebElement orgAddContactBtn;
	@FindBy(xpath = "//*[@id='contactsTable']/tbody")
	WebElement orgContactsTbl;
	@FindBy(xpath = "//div[@id='contactaddEdit']//select[@id='salutation']")
	WebElement orgContactSalutation;
	@FindBy(xpath = "//div[@id='contactaddEdit']//input[@id='firstName']")
	WebElement orgContactFname;
	@FindBy(xpath = "//div[@id='contactaddEdit']//input[@id='lastName']")
	WebElement orgContactLname;
	@FindBy(xpath = "//div[@id='contactaddEdit']//input[@id='email']")
	WebElement orgContactEmail;
	@FindBy(xpath = "//div[@id='contactaddEdit']//input[@id='department']")
	WebElement orgContactDept;
	@FindBy(xpath = "//div[@id='contactaddEdit']//input[@id='title']")
	WebElement orgContactTitle;
	@FindBy(xpath = "//div[@id='contactaddEdit']//input[@id='workPhNo']")
	WebElement orgContactWorkPh;
	@FindBy(xpath = "//div[@id='contactaddEdit']//input[@id='mobilePhNo']")
	WebElement orgContactMobilePh;
	@FindBy(xpath = "//div[@id='contactaddEdit']//input[@id='isPrimary']")
	WebElement orgContactIsPrimary;
	@FindBy(xpath = "//div[@id='contactaddEdit']//input[@id='street1']")
	WebElement orgContactStreet;
	@FindBy(xpath = "//div[@id='contactaddEdit']//input[@id='city']")
	WebElement orgContactCity;
	@FindBy(xpath = "//div[@id='contactaddEdit']//input[@id='state']")
	WebElement orgContactState;
	@FindBy(xpath = "//div[@id='contactaddEdit']//input[@id='contZip']")
	WebElement orgContactZip;
	@FindBy(xpath = "//div[@id='contactaddEdit']//input[@id='contCountry']")
	WebElement orgContactCountry;
	@FindBy(xpath = "//div[@id='contactaddEdit']//a[@id='saveOrUpdateButton']")
	WebElement orgContactSaveBtn;
	@FindBy(xpath = "//div[@id='contactDeleteModal']")
	WebElement deleteContactModal;
	@FindBy(xpath = "//div[@id='contactDeleteModal']//a[text()='Yes']")
	WebElement deleteContactYesBtn;
	// Organization page --> Subscription tab
	@FindBy(xpath = "//div[@id='content']//a[@href='#licensinginfo']")
	WebElement orgSubscriptionTab;
	@FindBy(xpath = "//div[@id='restrionId']//li[@id='tenantsValue']")
	WebElement tenantValue;
	@FindBy(xpath = "//div[@id='restrionId']//li[@id='storageValue']")
	WebElement storageValue;
	@FindBy(xpath = "//div[@id='restrionId']//li[@id='dataTransferValue']")
	WebElement dataTransferValue;
	@FindBy(xpath = "//div[@id='restrionId']//li[@id='actionsValue']")
	WebElement actionsValue;
	@FindBy(xpath = "//div[@id='restrionId']//li[@id='bosCreatedValue']")
	WebElement bosCreatedValue;
	@FindBy(xpath = "//div[@id='restrionId']//li[@id='bosCalledValue']")
	WebElement bosCalledValue;
	@FindBy(xpath = "//div[@id='restrionId']//li[@id='appsValue']")
	WebElement appsValue;
	@FindBy(xpath = "//div[@id='restrionId']//li[@id='dbTableCreatedValue']")
	WebElement dbTableCreatedValue;
	@FindBy(xpath = "//div[@id='restrionId']//li[@id='pUserCreatedValue']")
	WebElement pUserCreatedValue;
	@FindBy(xpath = "//div[@id='restrionId']//li[@id='bUserCreatedValue']")
	WebElement bUserCreatedValue;
	@FindBy(xpath = "//div[@id='restrionId']//li[@id='otmaxFileSize']")
	WebElement otmaxFileSize;
	@FindBy(xpath = "//div[@id='restrionId']//li[@id='otmaxStorageSize']")
	WebElement otmaxStorageSize;
	@FindBy(xpath = "//div[@id='restrionId']//li[@id='otallowBusinessUser']")
	WebElement otallowBusinessUser;
	
	public OrganizationPage() {

		super();
		hmePg = new HomePage();
	}
	
	public HomePage getHmePage(){
		return hmePg;
	}
	
	public OrganizationPage openOrgSubscriptionTab() throws Exception {
		try {
			orgSubscriptionTab.click();
			log.info("Subscription sub tab opened");
			Util.delay(2000);
		} catch (Exception e) {
			log.info("Subscription tab not found");
			throw (e);
		}
		return this;
	}

	public Float getTenantVal() throws Exception{
		String[] value;
		try {
			value = tenantValue.getText().split(":");
			log.info("Tenants allowed is " + value[1].trim());
		} catch (Exception e) {
			log.info("Tenant value is not found");
			throw(e);
		}
		return Float.parseFloat(value[1].trim());
	}

	public Float getStorageVal() {
		String[] value;
		value = storageValue.getText().split(":");
		log.info("Storage allowed is " + value[1].trim());
		return Float.parseFloat(value[1].trim());
	}

	public Float getDataTransferVal() {
		String[] value;
		value = dataTransferValue.getText().split(":");
		log.info("Data Transfer allowed is " + value[1].trim());
		return Float.parseFloat(value[1].trim());
	}

	public Float getBosCalledVal() {
		String[] value;
		value = bosCalledValue.getText().split(":");
		log.info("BOS call allowed is " + value[1].trim());
		return Float.parseFloat(value[1].trim());
	}

	public Float getBosCreatedVal() {
		String[] value;
		value = bosCreatedValue.getText().split(":");
		log.info("BOS allowed is " + value[1].trim());
		return Float.parseFloat(value[1].trim());
	}

	public Float getActionsVal() {
		String[] value;
		value = actionsValue.getText().split(":");
		log.info("Actions allowed is " + value[1].trim());
		return Float.parseFloat(value[1].trim());
	}

	public Float getAppsVal() {
		String[] value;
		value = appsValue.getText().split(":");
		log.info("Apps allowed is " + value[1].trim());
		return Float.parseFloat(value[1].trim());
	}

	public Float getTableCreatedVal() {
		String[] value;
		value = dbTableCreatedValue.getText().split(":");
		log.info("Db tables allowed is " + value[1].trim());
		return Float.parseFloat(value[1].trim());
	}

	public Float getPuserCreatedVal() {
		String[] value;
		value = pUserCreatedValue.getText().split(":");
		log.info("Platform user allowed is " + value[1].trim());
		return Float.parseFloat(value[1].trim());
	}

	public Float getBuserCreatedVal() {
		String[] value;
		value = bUserCreatedValue.getText().split(":");
		log.info("Business user allowed is " + value[1].trim());
		return Float.parseFloat(value[1].trim());
	}

	public Float getMaxFileSizeVal() {
		String[] value;
		value = otmaxFileSize.getText().split(":");
		log.info("Max file size allowed is " + value[1].trim());
		return Float.parseFloat(value[1].trim());
	}

	public Float getMaxStorageSizeVal() {
		String[] value;
		value = otmaxStorageSize.getText().split(":");
		log.info("Max storage size allowed is " + value[1].trim());
		return Float.parseFloat(value[1].trim());
	}

	public Float getAllowMoreBuserVal() {
		float value = 0;
		String [] values = otallowBusinessUser.getText().split(":");
		if (values[1].trim().equalsIgnoreCase("true")) {
			value = 1;
		}
		log.info("All more business user " + value);
		return value;
	}

	public OrganizationPage openOrgContactTab() throws Exception{
		try {
			orgContactsTab.click();
			log.info("Contact sub tab opened");
			Util.delay(2000);
		} catch (Exception e) {
			log.info("Contact tab not found");
			throw (e);
		}
		return this;
	}

	public int returnOrgContactCount() {
		int count = 0;
		count = orgContactsTbl.findElements(By.tagName("tr")).size();
		log.info("Organization has " + count + " contact/s");
		return count;
	}

	public boolean checkContactExist(String name) {
		boolean result = false;
		if (driver.findElements(By.xpath("//table[@id='contactsTable']//p[contains(text(), '" + name + "')]"))
				.size() > 0) {
			result = true;
			log.info("Contact " + name + " exist");
		} else {
			log.info("Contact " + name + " not exist");
		}
		return result;
	}

	public boolean checkIfContactIsPrimary(String name) {
		boolean result = false;
		if (driver.findElements(By.xpath("//p[contains(text(),'" + name + "')]/../../td[10]/a[2]")).size() < 1) {
			result = true;
			log.info("Contact " + name + " is primary");
		} else {
			log.info("Contact " + name + " is not primary");
		}
		return result;
	}

	public OrganizationPage deleteOrgContact(String name) {
		if (checkContactExist(name)) {
			WebElement delImg = driver.findElement(By.xpath("//p[contains(text(),'" + name + "')]/../../td[10]/a[2]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", delImg);
			log.info("Contact " + name + " has been deleted");
			Util.delay(2000);
			deleteContactYesBtn.click();
			Util.delay(2000);
		} else {
			log.info("Contact " + name + " not found");
		}
		return this;
	}

	public OrganizationPage openOrgEditContactForm(String name) {
		if (checkContactExist(name)) {
			WebElement editImg = driver.findElement(By.xpath("//p[contains(text(),'" + name + "')]/../../td[10]/a[1]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", editImg);
			log.info("Contact edit form opened");
			Util.delay(2000);
		} else {
			log.info("Contact " + name + " not found");
		}
		return this;
	}

	public OrganizationPage openOrgAddContactForm() throws Exception{
		try {
			// System.out.println(driver.findElements(By.xpath("//a[@onclick='javascript:showUpdateAddressForm(321);']")).size());
			orgAddContactBtn.click();
			log.info("Org add contact form opened");
			Util.delay(2000);
		} catch (Exception e) {
			log.info("Org add contact form not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage selectOrgContactSalutation(String salutation) throws Exception {
		try {
			Select select = new Select(orgContactSalutation);
			select.selectByVisibleText(salutation);
			log.info("Salutation selected");
		} catch (Exception e) {
			log.info("Salutation field not found");
			throw (e);
		}
		return this;

	}

	public OrganizationPage setOrgContactFname(String fname) throws Exception{
		try {
			orgContactFname.clear();
			orgContactFname.sendKeys(fname);
			log.info("Org contact fname entered");
		} catch (Exception e) {
			log.info("Org contact fname not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage setOrgContactLname(String lname) throws Exception{
		try {
			orgContactLname.clear();
			orgContactLname.sendKeys(lname);
			log.info("Org contact lname entered");
		} catch (Exception e) {
			log.info("Org contact lname not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage setOrgContactEmail(String email) throws Exception{
		try {
			orgContactEmail.clear();
			orgContactEmail.sendKeys(email);
			log.info("Org contact email entered");
		} catch (Exception e) {
			log.info("Org contact email not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage setOrgContactDept(String dept) throws Exception{
		try {
			orgContactDept.clear();
			orgContactDept.sendKeys(dept);
			log.info("Org contact department entered");
		} catch (Exception e) {
			log.info("Org contact department not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage setOrgContactTitle(String title) throws Exception{
		try {
			orgContactTitle.clear();
			orgContactTitle.sendKeys(title);
			log.info("Org contact title entered");
		} catch (Exception e) {
			log.info("Org contact title not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage setOrgContactWorkPh(String workPh) throws Exception{
		try {
			orgContactWorkPh.clear();
			orgContactWorkPh.sendKeys(workPh);
			log.info("Org contact work phone entered");
		} catch (Exception e) {
			log.info("Org contact work phone not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage setOrgContactMobilePh(String mobilePh) throws Exception{
		try {
			orgContactMobilePh.clear();
			orgContactMobilePh.sendKeys(mobilePh);
			log.info("Org contact mobile phone entered");
		} catch (Exception e) {
			log.info("Org contact mobile phone not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage setOrgContactPrimary() throws Exception{
		try {
			orgContactIsPrimary.click();
			log.info("Org contact primary contct selected");
		} catch (Exception e) {
			log.info("Org contact primary contct field not found");
			throw (e);
		}
		return this;
	}
	
	public OrganizationPage setOrgContactStreet(String street) throws Exception{
		try {
			orgContactStreet.clear();
			orgContactStreet.sendKeys(street);
			log.info("Org contact street entered");
		} catch (Exception e) {
			log.info("Org contact street not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage setOrgContactCity(String city) throws Exception{
		try {
			orgContactCity.clear();
			orgContactCity.sendKeys(city);
			log.info("Org contact city entered");
		} catch (Exception e) {
			log.info("Org contact city not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage setOrgContactState(String state) throws Exception{
		try {
			orgContactState.clear();
			orgContactState.sendKeys(state);
			log.info("Org contact state entered");
		} catch (Exception e) {
			log.info("Org contact state not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage setOrgContactZip(String zip) throws Exception{
		try {
			orgContactZip.clear();
			orgContactZip.sendKeys(zip);
			log.info("Org contact zip entered");

		} catch (Exception e) {
			log.info("Org contact zip not found");
			throw (e);
		}
		return this;
	}
	
	public OrganizationPage setOrgContactCountry(String country) throws Exception{
		try {
			orgContactCountry.clear();
			orgContactCountry.sendKeys(country);
			log.info("Org contact country entered");

		} catch (Exception e) {
			log.info("Org contact country not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage saveOrgContact() throws Exception{
		try {
			orgContactSaveBtn.click();
			Util.delay(2000);
			log.info("Org contact save button clicked");

		} catch (Exception e) {
			log.info("Org contact save button not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage openOrgAddressTab() throws Exception{
		try {
			orgAddressTab.click();
			log.info("Address sub tab opened");
			Util.delay(2000);
		} catch (Exception e) {
			log.info("Address tab not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage openOrgAddUpdateForm() throws Exception{
		try {
			// System.out.println(driver.findElements(By.xpath("//a[@onclick='javascript:showUpdateAddressForm(321);']")).size());
			editOrgAddressImg.click();
			log.info("Org address update form opened");
			Util.delay(2000);
		} catch (Exception e) {
			log.info("Org address update form not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage setOrgAddStreet(String street) throws Exception{
		try {
			orgStreet.clear();
			orgStreet.sendKeys(street);
			log.info("Org address street entered");
			Util.delay(2000);
		} catch (Exception e) {
			log.info("Org address street not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage setOrgAddState(String state) throws Exception{
		try {
			orgState.clear();
			orgState.sendKeys(state);
			log.info("Org address state entered");
			Util.delay(2000);
		} catch (Exception e) {
			log.info("Org address state not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage setOrgAddCountry(String country) throws Exception{
		try {
			orgCountry.clear();
			orgCountry.sendKeys(country);
			log.info("Org address country entered");
			Util.delay(2000);
		} catch (Exception e) {
			log.info("Org address country not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage setOrgAddCity(String city) throws Exception{
		try {
			orgCity.clear();
			orgCity.sendKeys(city);
			log.info("Org address city entered");
			Util.delay(2000);
		} catch (Exception e) {
			log.info("Org address city not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage setOrgAddZip(String zip) throws Exception{
		try {
			orgZip.clear();
			orgZip.sendKeys(zip);
			log.info("Org address zip entered");
			Util.delay(2000);
		} catch (Exception e) {
			log.info("Org address zip not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage submitOrgAddUpdateForm() throws Exception{
		try {
			orgAddUpdateBtn.click();
			log.info("Org address update button clicked");
			Util.delay(4000);
		} catch (Exception e) {
			log.info("Org address update button not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage openCompanyForm() throws Exception{
		try {
			companyInfoEditImg.click();
			log.info("Clicked org info edit button");
			Util.delay(2000);
		} catch (Exception e) {
			log.info("Clicked org info edit not found");
			throw (e);
		}
		return this;
	}

	public OrganizationPage selectOrgDateFormat(String date) throws Exception {
		try {
			Select select = new Select(orgDateFormat);
			select.selectByVisibleText(date);
			log.info("Date format selected");
		} catch (Exception e) {
			log.info("Date format field not found");
			throw (e);
		}
		return this;

	}

	public String getOrgDateFormat() throws Exception {
		String selectedOption;
		try {
			selectedOption = (String) ((JavascriptExecutor) driver)
					.executeScript("return " + "document.getElementById('dateFormatDisp').value");
			log.info("Display dateformat is " + selectedOption);
		} catch (Exception e) {
			log.info("Date format field not found");
			throw (e);
		}
		return selectedOption;

	}
	
	public String getTenantId() throws Exception {
		String tenantId;
		try {
			tenantId = (String) ((JavascriptExecutor) driver)
					.executeScript("return " + "document.getElementById('tenantIdDisp').value");
			log.info("Tenant Id is " + tenantId);
		} catch (Exception e) {
			log.info("Tenant Id field not found");
			throw (e);
		}
		return tenantId;

	}

	public OrganizationPage updateOrgInfo() throws Exception {
		try {
			orgInfoUpdateBtn.click();
			log.info("Org info update button clicked");
			Util.delay(3000);
		} catch (Exception e) {
			log.info("Org info update button not found");
			throw (e);
		}
		return this;
	}

}
