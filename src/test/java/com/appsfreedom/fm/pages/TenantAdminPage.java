package com.appsfreedom.fm.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.appsfreedom.utils.Util;
import com.relevantcodes.extentreports.LogStatus;
import com.appsfreedom.fm.pages.HomePage;

public class TenantAdminPage extends PlatformConfigPage {

	@FindBy(xpath = "//*[@id='tenantTable']")
	WebElement tenantTbl;
	@FindBy(xpath = "//*[@id='tenantTable']//input[@data-column='tenantid']")
	WebElement tenantColumn;
	@FindBy(xpath = "//*[@id='tenantTable']/preceding::i[@class='icon-white icon-search']")
	WebElement searchBtn;
	@FindBy(xpath = "//*[@id='editButtonId']")
	WebElement tenantEditbtn;
	@FindBy(xpath = "//*[@id='tenantBody']/tr/td[1]/input")	
	WebElement tenantRadiobtn;
	@FindBy(xpath = "//*[@id='parentPermission_32']")
	WebElement tenantPermissionChkbx;
	@FindBy(xpath = "//*[@id='tenantLicenseInfoUpdate']/div[2]/a[1]")
	WebElement updateLicenceBtn;
	@FindBy(xpath = "//*[@id='accordianMenuList']")
	WebElement leftMenu;
	@FindBy(xpath = "//a[contains(text(), 'Process Modeler')]")
	WebElement processModelerLnk;
	@FindBy(xpath = "//*[@id='accordion']/div[1]/div[1]/a")
	WebElement processModelerXPath;
	@FindBy(xpath = "//*[@id='lickeyButtonId']/a")
	WebElement licencekeyBtn;
	@FindBy(xpath = "//*[@id='lic_expiry_date']")
	WebElement licencekeyExpiryDate;
	@FindBy(xpath = "//*[@id='dev_domain']")
	WebElement devURLTxtFld;
	@FindBy(xpath = "//*[@id='qa_domain']")
	WebElement qaURLTxtFld;
	@FindBy(xpath = "//*[@id='prd_domain']")
	WebElement prdURLTxtFld;
	@FindBy(xpath = "//*[@id='tenantLicensekeyUpdate']/div[2]/a[1]")
	WebElement licensekeyGenerateBtn;
	@FindBy(xpath = "//*[@id='tenantLicensekeyUpdate']/div[2]/a[2]")
	WebElement licensekeyCancelBtn;
	@FindBy(xpath = "//*[@id='lic-key-popup']/div[2]/a[1]")
	WebElement licensedwnldBtn;
	@FindBy(xpath = "//*[@id='lic-key-popup']/div[2]/a[2]")
	WebElement licensekeycloseBtn;
	@FindBy(xpath = "//*[@id='lic-activation-popup']/div[3]/button")
	WebElement licensekeyOKBtn;
	@FindBy(xpath = "//*[@id='configurator_tab_header']/li[1]/a")
	WebElement tenantAdminTabLbl;


	

	public TenantAdminPage() {
		super();
	}

	public boolean isTenantExists(String name) throws Exception{
		boolean exists=false;
		try {
			searchBtn.click();
			log.info("Search button clicked");
			tenantColumn.sendKeys(name);
			log.info("Tenant name entered");
			if(driver.findElements(By.xpath("//*[@id='tenantBody']//a[text()='" + name + "']")).size()==1){
				exists=true;
				log.info("Tenant "+name+" exists");
			}
		} catch (Exception e) {
			log.info("Unable to check tenant");
			throw(e);
		}
		return exists;
	}
	public PlatformConfigPage selectTenant(String name) throws Exception {
		String xpath = "//*[@id='tenantBody']//a[text()='" + name + "']";
		try {
			if(isTenantExists(name)){
				((JavascriptExecutor) driver).executeScript("window.confirm = function(msg) { return true; }");
				driver.findElement(By.xpath("//*[@id='tenantBody']//a[text()='"+name+"']")).click();
				log.info("Tenant selected");
			}else
				log.info("Tenant "+name+" not found");
		} catch (Exception e) {
			throw(e);
		}

		return new PlatformConfigPage();
	}

	public boolean editSubscription(String name) throws Exception{
		boolean exists=false;
		try {
			if(isTenantExists(name))
			{
				Util.delay(2000);
				tenantRadiobtn.click();
				log.info("Tenant's Radio button selected");
				if(tenantEditbtn.isDisplayed())
				{
					Util.delay(2000);
					tenantEditbtn.click();
					log.info("Tenant's Edit Subscription selected");
					if(tenantPermissionChkbx.isSelected())
					{
						tenantPermissionChkbx.click();
						log.info("If Tenant's persion is enabled then it will be deselected");
						if(updateLicenceBtn.isDisplayed())
						{
							updateLicenceBtn.click();
							log.info("Tenant's Edit Subscription updated");
							Util.delay(2000);	
							exists = true;
						}	
					}
					else
					{
						tenantPermissionChkbx.click();
						log.info("If Tenant's persion is disabled then it will be selected");
						if(updateLicenceBtn.isDisplayed())
						{
							updateLicenceBtn.click();
							log.info("Tenant's Edit Subscription updated");
							Util.delay(2000);	
							exists = true;
						}		
					}
				}
			}
			else
			{
				log.info("Unable to check tenant");				
			}
		} catch (Exception e) {
			log.info("Unable to edit the subscription of a tenant");
			throw(e);
		}
		return exists;
	}

	public boolean verifyeditSubscription() throws Exception{
		boolean exists=false;
		try {
			log.info(leftMenu.findElements(By.xpath("//a[contains(text(), 'Process Modeler')]")).size());
			int counts = leftMenu.findElements(By.xpath("//a[contains(text(), 'Process Modeler')]")).size();
			if(counts == 0){
				log.info("Process modeler link not found");
				exists = false;
			}
			else
			{
				processModelerLnk.isDisplayed();
				log.info("Process modeler link found");
				exists = true;
			}
		} catch (Exception e) {
			log.info("Process modeler link found");
			throw (e);
		}
		return exists;
	}

	public boolean checkLicenceValidity(String name) throws Exception{
		boolean result=false;
		String str_originaldate;
		String str_presentdate;
		try {
			if(isTenantExists(name))
			{
				Util.delay(5000);
				tenantRadiobtn.click();
				Util.delay(4000);
				log.info("Tenant's Radio button selected");
				if(licencekeyBtn.isDisplayed())
				{
					Util.scrollToElement(licencekeyBtn);
					licencekeyBtn.click();
					log.info("Tenant's Edit Licence selected");
					log.info(licencekeyExpiryDate.getText());
					//	Alternate Methods
					//	selectedOption = (String) ((JavascriptExecutor) driver).executeScript("return " + "document.getElementById('lic_expiry_date').value");
					JavascriptExecutor executor = (JavascriptExecutor)driver;					
					str_originaldate = executor.executeScript("return arguments[0].value;", licencekeyExpiryDate).toString();
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
					Date originaldate = sdf.parse(str_originaldate);
					str_presentdate = sdf.format(new Date());
					Date presentdate = sdf.parse(str_presentdate);		
					if(originaldate.compareTo(presentdate)!=1)
					{
						Util.delay(2000);
						log.info("Presentdate is lower than Licence Date");
						String path = Util.getscreenshot("Licence Expiry Verification");
						test.log(LogStatus.INFO, "Presentdate is higher than Licence Expiry Date" + test.addScreenCapture(path));						
						if(licensekeyCancelBtn.isDisplayed())
						{
							licensekeyCancelBtn.click();
							log.info("Cancel Button true Clicked");
							Util.delay(4000);
							result = true;
						}
						else
						{
							log.info("Cancel Button not Clicked");
						}
					}
					else
					{
						log.info("Presentdate is higher than Licence Date");
						String path = Util.getscreenshot("Licence Expiry Verification");
						test.log(LogStatus.INFO, "Presentdate is lower than Licence Expiry Date" + test.addScreenCapture(path));
						Util.delay(2000);
						if(licensekeyCancelBtn.isDisplayed())
						{
							licensekeyCancelBtn.click();
							log.info("Cancel Button false Clicked");
							Util.delay(4000);
							return result;
						}
						else
						{
							log.info("Cancel Button not Clicked");
						}
						log.info("Cancel Button false Clicked");
						return result;
					}	
				}
				else
				{
					log.info("Unable to check tenant");
				}
			}			
		} catch (Exception e) {
			log.info("Unable to edit the Licence of a tenant");
			throw(e);
		}	
		return result;
	}

	public boolean resetLicence(String name, String date, String devURL, String qaURL, String prdURL) throws Exception
	{
		log.info("In resetLicence FUNCTION");
		boolean result=false;
		try 
		{
			if(isTenantExists(name))
			{
				Util.delay(5000);
				tenantRadiobtn.click();
				Util.delay(4000);
				log.info("Tenant's Radio button selected");
				if(licencekeyBtn.isDisplayed())
				{
					Util.scrollToElement(licencekeyBtn);
					licencekeyBtn.click();
					log.info("Tenant's Edit Licence selected");
					Util.delay(2000);
					if(licencekeyExpiryDate.isEnabled() && devURLTxtFld.isEnabled() && qaURLTxtFld.isEnabled() && prdURLTxtFld.isEnabled())
					{
						licencekeyExpiryDate.clear();
						Util.delay(1000);
						licencekeyExpiryDate.sendKeys(date);
						Util.delay(1000);
						devURLTxtFld.clear();
						devURLTxtFld.sendKeys(devURL);
						Util.delay(1000);
						qaURLTxtFld.clear();
						qaURLTxtFld.sendKeys(qaURL);
						Util.delay(1000);
						prdURLTxtFld.clear();
						prdURLTxtFld.sendKeys(prdURL);
						Util.delay(1000);
						licensekeyGenerateBtn.click();
						Util.delay(3000);
						String path = Util.getscreenshot("Licence encrypted key was generated");
						test.log(LogStatus.INFO, "Licence encrypted key was generated" + test.addScreenCapture(path));
						licensedwnldBtn.click();
						Util.delay(3000);
						String path1 = Util.getscreenshot("Licence Renewed");
						test.log(LogStatus.INFO, "Licence Renewed for the expired tenant" + test.addScreenCapture(path1));
						licensekeyOKBtn.click();
						Util.delay(3000);
						result = true;
						Util.scrollToElement(tenantAdminTabLbl);
					}
					else
					{
						log.info("Unable to edit/reset the Licence parameter of a tenant in licence page");
						String path = Util.getscreenshot("Licence Edit Parameters are not configurable");
						test.log(LogStatus.INFO, "Licence Edit Parameters are not configurable" + test.addScreenCapture(path));
					}
				}
				else
				{
					log.info("Unable to edit/reset the Licence parameter of a tenant after selecting it");
					String path = Util.getscreenshot("Licence Edit Button Not found");
					test.log(LogStatus.INFO, "Licence Edit Button Not found" + test.addScreenCapture(path));

				}
			} 
			else
			{
				log.info("Unable to edit/reset the Licence parameter of a tenant before selecting it");
				String path = Util.getscreenshot("Tenant not searchable");
				test.log(LogStatus.INFO, "Tenant not searchable" + test.addScreenCapture(path));
			}
		}
		catch (Exception e) 
		{
			throw(e);
		}
		return result;
	}
	
	public boolean renewLicence(String name, String date, String devURL, String qaURL, String prdURL) throws Exception
	{
		log.info("In renewLicence FUNCTION");
		boolean result=false;
		try 
		{
			Util.delay(5000);
			tenantRadiobtn.click();
			Util.delay(4000);
			log.info("Tenant's Radio button selected");
			if(licencekeyBtn.isDisplayed())
			{
				Util.scrollToElement(licencekeyBtn);
				licencekeyBtn.click();
				log.info("Tenant's Edit Licence selected");
				Util.delay(2000);
				if(licencekeyExpiryDate.isEnabled() && devURLTxtFld.isEnabled() && qaURLTxtFld.isEnabled() && prdURLTxtFld.isEnabled())
				{
					licencekeyExpiryDate.clear();
					Util.delay(1000);
					licencekeyExpiryDate.sendKeys(date);
					Util.delay(1000);
					devURLTxtFld.clear();
					devURLTxtFld.sendKeys(devURL);
					Util.delay(1000);
					qaURLTxtFld.clear();
					qaURLTxtFld.sendKeys(qaURL);
					Util.delay(1000);
					prdURLTxtFld.clear();
					prdURLTxtFld.sendKeys(prdURL);
					Util.delay(1000);
					licensekeyGenerateBtn.click();
					Util.delay(3000);
					String path = Util.getscreenshot("Licence encrypted key was generated");
					test.log(LogStatus.INFO, "Licence encrypted key was generated" + test.addScreenCapture(path));
					licensedwnldBtn.click();
					Util.delay(3000);
					String path1 = Util.getscreenshot("Licence Renewed");
					test.log(LogStatus.INFO, "Licence Renewed for the expired tenant" + test.addScreenCapture(path1));
					licensekeyOKBtn.click();
					Util.delay(3000);
					result = true;
					Util.scrollToElement(tenantAdminTabLbl);
				}
				else
				{
					log.info("Unable to edit/reset the Licence parameter of a tenant in licence page");
					String path = Util.getscreenshot("Licence Edit Parameters are not configurable");
					test.log(LogStatus.INFO, "Licence Edit Parameters are not configurable" + test.addScreenCapture(path));
					
				}
			}
			else
			{
				log.info("Unable to edit/reset the Licence parameter of a tenant after selecting it");
				String path = Util.getscreenshot("Licence Edit Button Not found");
				test.log(LogStatus.INFO, "Licence Edit Button Not found" + test.addScreenCapture(path));
			}

		}
		catch (Exception e) 
		{
			throw(e);
		}
		return result;
	}
}	


