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


public class ConnectivityPage extends PlatformConfigPage {

	@FindBy(xpath = "//*[@id='connectivity']/table/tbody/tr[3]/td/div/a")
	WebElement editBtn;
	
	@FindBy(xpath = "//*[@id='config']")
	WebElement URLText;

	@FindBy(xpath = "//*[@id='companyAuthForm']/div[2]/a[1]")
	WebElement updateBtn;
	
	@FindBy(xpath = "//*[@id='companyAuthForm']/div[2]/a[2]")
	WebElement cancelBtn;

	@FindBy(xpath = "//*[@id='authId']/option[1]")
	WebElement fmLst;
	
	@FindBy(xpath = "//*[@id='authId']/option[2]")
	WebElement ssoLst;

	@FindBy(xpath = "//*[@id='authId']/option[3]")
	WebElement ldapLst;
	
	@FindBy(xpath = "//*[@id='connectivitydetails']/div[1]/a")
	WebElement addLDAPBtn;
	
	
	@FindBy(xpath = "//*[@id='ldapAuthForm']//input[@id='url']")
	WebElement urlTxt;

	@FindBy(xpath = "//*[@id='ldapAuthForm']//input[@id='servername']")
	WebElement servernameTxt;

	@FindBy(xpath = "//*[@id='ldapAuthForm']//input[@id='basedn']")
	WebElement basednTxt;

	@FindBy(xpath = "//*[@id='ldapAuthForm']//input[@id='authfilter']")
	WebElement authfilterTxt;

	@FindBy(xpath = "//*[@id='ldaptextconbtn']")
	WebElement testConnectionBtn;

	@FindBy(xpath = "//*[@id='ldapconfigbtn']")
	WebElement addLDAPConfigBtn;

	@FindBy(xpath = "//*[@id='ldapAuthForm']/div[2]/a[2]")
	WebElement cancelLDAPBtn;
	
	@FindBy(xpath = "//*[@id='connectivityTableBody']/tr[1]/td[5]/a[2]/img")
	WebElement delConnection1;
	
	@FindBy(xpath = "//*[@id='connectivityTableBody']/tr[2]/td[5]/a[2]/img")
	WebElement delConnection2;
	
	@FindBy(xpath = "//*[@id='authDeleteModel']/div[2]/a[1]")
	WebElement yesBtn;
	
	@FindBy(xpath = "//*[@id='ldaptestcon']")
	WebElement testconnectionmessageTxt;
	
	@FindBy(xpath = "//*[@id='principals']")
	WebElement principal;

	@FindBy(xpath = "//*[@id='credentials']")
	WebElement principalpassword;
	
	private HomePage hmePg;

	public ConnectivityPage() {
		super();
		hmePg = new HomePage();
	}

	public HomePage getHmePage(){
		return hmePg;
	}

	public boolean isEditBtnExists() throws Exception{
		boolean exists=false;
		try 
		{
			Util.delay(1000);
			if(editBtn.isDisplayed())
			{
				log.info("Edit button found");
				exists = true;
			}
		} 
		catch (Exception e) {
			log.info("Unable to find edit button");
			throw(e);
		}
		return exists;
	}
	
	public boolean FMAuthentication(String URL) throws Exception{
		boolean result=false;
		try 
		{
			if(isEditBtnExists())
			{
				editBtn.click();
				Util.delay(2000);
				log.info("Edit button found");
				fmLst.click();
				URLText.clear();
				URLText.sendKeys(URL);
				updateBtn.click();
				log.info("FM URL was saved");
				Util.delay(3000);
				result = true;
			}
			else
			{
				log.info("FM URL saving was failed");
			}
		} 
		catch (Exception e) {
			log.info("Edit button not found");
			throw(e);
		}
		return result;
	}
	
	public boolean SSOAuthentication(String URL) throws Exception{
		boolean result=false;
		try 
		{
			if(isEditBtnExists())
			{
				editBtn.click();
				Util.delay(2000);
				log.info("Edit button found");
				ssoLst.click();
				URLText.clear();
				URLText.sendKeys(URL);
				updateBtn.click();
				log.info("FM URL was saved");
				Util.delay(2000);
				result = true;
			}
			else
			{
				log.info("FM URL saving was failed");
			}
		} 
		catch (Exception e) {
			log.info("");
			throw(e);
		}
		return result;
	}
	
	public boolean LDAPAuthentication(String ladpSrvName, String ldapURL, String ldapBaseDN, String ldapAuth, String ADFSSrvName, String ADFSURL, String ADFSBaseDN, String ADFSAuth) throws Exception{
		boolean result=false;
		try 
		{
			if(isEditBtnExists())
			{
				editBtn.click();
				Util.delay(2000);
				log.info("Edit button found");
				ldapLst.click();
				Util.delay(1000);
				updateBtn.click();
				Util.delay(2000);
				if(driver.findElements(By.xpath("//*[@id='connectivityTableBody']/tr[1]/td[5]/a[2]/img")).size()==1)
				{					
					delConnection1.click();
					Util.delay(2000);
					yesBtn.click();
				}
				Util.delay(2000);
				if(driver.findElements(By.xpath("//*[@id='connectivityTableBody']/tr[2]/td[5]/a[2]/img")).size()==1)
				{					
					delConnection1.click();
					Util.delay(2000);
					yesBtn.click();
				}
				Util.delay(2000);
				if(driver.findElements(By.xpath("//*[@id='connectivityTableBody']/tr/td[5]/a[2]/img")).size()==1)
				{					
					delConnection1.click();
					Util.delay(2000);
					yesBtn.click();
				}
				Util.delay(2000);
				addLDAPBtn.click();
				Util.delay(2000);
				urlTxt.sendKeys(ldapURL);
				servernameTxt.sendKeys(ladpSrvName);
				basednTxt.sendKeys(ldapBaseDN);
				authfilterTxt.sendKeys(ldapAuth);
				addLDAPConfigBtn.click();
				Util.delay(2000);
				addLDAPBtn.click();
				Util.delay(2000);
				urlTxt.clear();
				urlTxt.sendKeys(ADFSURL);				
				servernameTxt.clear();
				servernameTxt.sendKeys(ADFSSrvName);
				basednTxt.clear();
				basednTxt.sendKeys(ADFSBaseDN);
				authfilterTxt.clear();
				authfilterTxt.sendKeys(ADFSAuth);
				addLDAPConfigBtn.click();
				Util.delay(2000);
				log.info("LDAP config was saved");
				result = true;
			}
		} 
		catch (Exception e) {
			log.info("LDAP config was not saved");
			throw(e);
		}
		return result;
	}
	
	public boolean LDAPTestConnection(String ladpSrvName, String ldapURL, String ldapBaseDN, String ldapAuth, String ldapUserPassword) throws Exception{
		boolean result=false;
		String message = "Connection Success";
		try 
		{
			if(isEditBtnExists())
			{
				editBtn.click();
				Util.delay(2000);
				log.info("Edit button found");
				ldapLst.click();
				Util.delay(1000);
				updateBtn.click();
				Util.delay(2000);
			/*	if(driver.findElements(By.xpath("//*[@id='connectivityTableBody']/tr[1]/td[5]/a[2]/img")).size()==1)
				{					
					delConnection1.click();
					Util.delay(2000);
					yesBtn.click();
				}
				Util.delay(2000);
				if(driver.findElements(By.xpath("//*[@id='connectivityTableBody']/tr[2]/td[5]/a[2]/img")).size()==1)
				{					
					delConnection1.click();
					Util.delay(2000);
					yesBtn.click();
				}
				Util.delay(2000);
				if(driver.findElements(By.xpath("//*[@id='connectivityTableBody']/tr/td[5]/a[2]/img")).size()==1)
				{					
					delConnection1.click();
					Util.delay(2000);
					yesBtn.click();
				}*/
				Util.delay(2000);
				addLDAPBtn.click();
				Util.delay(2000);
				urlTxt.sendKeys(ldapURL);
				servernameTxt.sendKeys(ladpSrvName);
				basednTxt.sendKeys(ldapBaseDN);
				authfilterTxt.sendKeys(ldapAuth);
				principal.sendKeys(ldapBaseDN);
				principalpassword.sendKeys(ldapUserPassword);
				testConnectionBtn.click();
				Util.delay(1000);
				
				if(testconnectionmessageTxt.isDisplayed() && testconnectionmessageTxt.getText().equals(message))
				{
				//	String message = testconnectionmessageTxt.getText();
				//	log.info(message);
					log.info("LDAP connesction test was success");
					cancelLDAPBtn.click();
					result = true;
				}
				else
				{
					String messages = testconnectionmessageTxt.getText();
					log.info(messages);
					log.info("LDAP connesction test was falied");
					cancelLDAPBtn.click();
				}
			}
		} 
		catch (Exception e) {
			log.info("LDAP connesction test was not configurable");
			throw(e);
		}
		return result;
	}
}
