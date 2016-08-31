package com.appsfreedom.fm.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.appsfreedom.utils.Util;

public class FreedomAppsPage extends BasePage {
	@FindBy(xpath = "//tbody[@id='appsBody']/tr[1]/td[1]/div/a/img")
	WebElement selectApp;

	@FindBy(xpath = "//div[@id='content']/ul/li/a[@href='#assignedusers']")
	WebElement assignedAppUser;

	@FindBy(xpath = "//div[@id='content']/ul/li/a[@href='#addusersorgroup']")
	WebElement addUserGroup;

	@FindBy(xpath = "//form[@id='assignedAppstoreUserForm']//input[@value='user']")
	WebElement selectAddUser;

	@FindBy(xpath = "//select[@id='availableUsersForAppstore']//option[@value='16625']")
	WebElement selectAvailableUser;

	@FindBy(xpath = "//form[@id='assignedAppstoreUserForm']/div/table/tbody/tr[4]/td/a")
	WebElement addAvailableUser;

	@FindBy(xpath = "//table[@id='assignedUsersTable']/div/table/tbody/tr[4]/td/a")
	WebElement removeAvailableUser;

	@FindBy(xpath = "//div[@id='content']/ul/li/a[@href='#supportlanguages']")
	WebElement supportLanguage;

	@FindBy(xpath = "//a[@id='addLangButton']")
	WebElement addLanguage;

	@FindBy(xpath = "//select[@id='target']")
	WebElement targetLanguage;

	@FindBy(xpath = "//form[@id='languageaddForm']/div[2]/a[1]")
	WebElement translateLanguage;

	@FindBy(xpath = "//div[@id='content']/ul/li/a[@href='#customuri']")
	WebElement customUri;

	@FindBy(xpath = "//div[@id='content']/ul/li/a[@href='#assignca']")
	WebElement assignConApp;

	@FindBy(xpath = "//div[@id='assignca']/a")
	WebElement addContainerapp;

	@FindBy(xpath = "//div[@id='appsDetailsContent']/a")
	WebElement closeAppUserDialog;

	public FreedomAppsPage() {
		super();
	}

	public FreedomAppsPage removeUser() throws Exception{
		try{
			if ((Util.isElementDisplayed(assignedAppUser))) {
				log.info("assignedAppUser tab loaded succussfully");
				List<WebElement> userrows = driver.findElements(By.xpath("//table[@id='assignedUsersTable']/tbody/tr"));
				log.info("userrows size:"+userrows.size());
				for(int j=1;j<=userrows.size();j++){
					String userName = driver.findElement(By.xpath("//table[@id='assignedUsersTable']/tbody/tr["+j+"]/td[1]")).getText();
					log.info("USER "+userName);
					if(userName.equals("User a")){
						log.info("USER 111"+userName);
						driver.findElement(By.xpath("//table[@id='assignedUsersTable']/tbody/tr["+j+"]//a")).click();
						Util.delay(2000);
						driver.findElement(By.xpath("//form[@id='deleteAssingedUserAppsForm']/div[2]/a[1]")).click();
					}
						
				}
			}
			
		}catch(Exception e){
			log.info("User not removed");
			throw (e);
		}
		return this;
	}
	public FreedomAppsPage addLanguage(){

		try {
//			addLanguage.click();
			//Util.delay(1000);
			//targetLanguage.click();
			//Util.delay(1000);
			
			//To add Language
//			Select select = new Select(driver.findElement(By.xpath("//select[@id='target']")));
//			select.selectByValue("zh-CN");
//			Util.delay(2000);
//			translateLanguage.click();
			
			// To Edit Language
			List<WebElement> langList = driver.findElements(By.xpath("//ul[@id='supportLanguesList']/table"));
			for(int i=1;i<=langList.size();i++){
				String langEn = driver.findElement(By.xpath("//ul[@id='supportLanguesList']/table["+i+"]/tbody/tr/td[1]/li")).getText();
				log.info("Added languages"+langEn);
				if(langEn.equals("English")){
					driver.findElement(By.xpath("//ul[@id='supportLanguesList']/table["+i+"]/tbody/tr/td[2]/a")).click();
					break;
				}
			}
			Util.delay(3000);
			Select select = new Select(driver.findElement(By.xpath("//select[@id='compareLang']")));
			select.selectByVisibleText("English");
			Util.delay(3000);
			
			List<WebElement> langPreviewTable = driver.findElements(By.xpath("//table[@id='langPreviewTable']/tbody/tr"));
			for(int i=1;i<=langPreviewTable.size();i++){
				String langcolumn = driver.findElement(By.xpath("//table[@id='langPreviewTable']/tbody/tr["+i+"]/td[1]")).getText();
				if(langcolumn.equalsIgnoreCase("column1")){
					driver.findElement(By.xpath("//table[@id='langPreviewTable']/tbody/tr["+i+"]/td[2]/input[2]")).clear();
					driver.findElement(By.xpath("//table[@id='langPreviewTable']/tbody/tr["+i+"]/td[2]/input[2]")).sendKeys("Column1");
				}
			}
			driver.findElement(By.xpath("//form[@id='previewaddForm']/div[2]/a[1]")).click();
			//driver.findElement(By.xpath("//form[@id='previewaddForm']/div[2]/a[2]")).click();
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch(UnhandledAlertException ue){
			Alert alert = driver.switchTo().alert(); 
	        String alertText = alert.getText().trim();
	        System.out.println("Alert data: "+ alertText);
	        alert.dismiss();
		}catch(Exception e){
			log.error("Update Language not clicked."+e);
		}

		return this;
	}
	
	public FreedomAppsPage clickFreedomApps() throws Exception {

		try {
			selectApp.click();
			log.info("Clicked Freedom Apps");
		} catch (Exception e) {
			log.error("Freedom Apps not found");
			throw (e);
		}

		return this;
	}
	public FreedomAppsPage clickFreedomAppUser() throws Exception {

		try {
			assignedAppUser.click();
			log.info("Clicked Freedom App User");
		} catch (Exception e) {
			log.error("Freedom App User not clicked");
			throw (e);
		}

		return this;
	}
	public FreedomAppsPage clickAddUserGroup() throws Exception {

		try {
			addUserGroup.click();
			log.info("Clicked Add User Group");
		} catch (Exception e) {
			log.error("Add User Group not clicked");
			throw (e);
		}

		return this;
	}
	public FreedomAppsPage clickSelectAppUser() throws Exception {

		try {
			selectAddUser.click();
			log.info("Clicked Add User");
		} catch (Exception e) {
			log.error("Add User not clicked");
			throw (e);
		}

		return this;
	}
	public FreedomAppsPage clickSelectAvailableUser() throws Exception {

		try {
			selectAvailableUser.click();
			log.info("Clicked Add User Group");
		} catch (Exception e) {
			log.error("Add User Group not clicked");
			throw (e);
		}

		return this;
	}
	public FreedomAppsPage clickSaveAvailableUser() throws Exception {

		try {
			addAvailableUser.click();
			log.info("Clicked Save User Group");
		} catch (Exception e) {
			log.error("Save User Group not clicked");
			throw (e);
		}

		return this;
	}
	public FreedomAppsPage clickLanguage() throws Exception {

		try {
			supportLanguage.click();
			log.info("Clicked App Library Langauges");
		} catch (Exception e) {
			log.error("App Library Langauges not clicked");
			throw (e);
		}

		return this;
	}
	
	public FreedomAppsPage clickCustomUri() throws Exception {

		try {
			customUri.click();
			log.info("Clicked App Library Custom URI");
		} catch (Exception e) {
			log.error("App Library Custom URI not clicked");
			throw (e);
		}

		return this;
	}
	public FreedomAppsPage clickAssignContainerApps(){

		try {
			assignConApp.click();
			Util.delay(2000);
			addContainerapp.click();
			Util.delay(2000);
			//To Select app
			List<WebElement> conApps = driver.findElements(By.xpath("//select[@id='availableCAs']/option"));
			for(int i=1;i<=conApps.size();i++){
				String optValue = driver.findElement(By.xpath("//select[@id='availableCAs']/option["+i+"]")).getText();
				if(optValue.equals("aprfour")){
					driver.findElement(By.xpath("//select[@id='availableCAs']/option["+i+"]")).click();
					break;
				}
			}
			driver.findElement(By.xpath("//div[@id='newCADiv']/table/tbody/tr[2]/td/a[1]")).click();
		} catch(Exception e){
			log.error("clickAssignContainerApps "+e);
			//driver.switchTo().defaultContent();
//			if ((Util.isAlertPresent(driver))) {
//				Util.closeAlert(driver);
//			}
			Alert alert = driver.switchTo().alert(); 
	        String alertText = alert.getText().trim();
	        System.out.println("Alert data: "+ alertText);
	        alert.dismiss();
		}

		return this;
	}
	public FreedomAppsPage removeContainerApp(){
		try{
			assignConApp.click();
			Util.delay(2000);
			List<WebElement> assignedApps = driver.findElements(By.xpath("//table[@id='assignedCAtbl']/tbody/tr"));
			for(int i=1;i<=assignedApps.size();i++){
				String optValue = driver.findElement(By.xpath("//table[@id='assignedCAtbl']/tbody/tr["+i+"]/td[1]")).getText();
				if(optValue.equals("aprfour")){
					driver.findElement(By.xpath("//table[@id='assignedCAtbl']/tbody/tr["+i+"]/td[2]/a")).click();
					break;
				}
			}
		}catch(UnhandledAlertException ue){
			Alert alert = driver.switchTo().alert(); 
	        String alertText = alert.getText().trim();
	        System.out.println("Alert data: "+ alertText);
	        alert.dismiss();
		}catch(Exception e){
			log.error("Remove Container Apps "+e);
		}
		return this;
	}
	public FreedomAppsPage closeFreedomAppsDialog() throws Exception {

		try {
			closeAppUserDialog.click();
			log.info("Clicked App Library Close Button");
		} catch (Exception e) {
			log.error("App Library Close Button not clicked");
			throw (e);
		}

		return this;
	}
}
