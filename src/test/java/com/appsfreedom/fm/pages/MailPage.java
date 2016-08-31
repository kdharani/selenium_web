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

public class MailPage extends BasePage 
{
	private HomePage hmePg;
	Logger log = Logger.getLogger("devpinoyLogger");
	
	@FindBy(xpath = "//*[@id='mconfig_email']")//Enter mailAddress value 
	WebElement mailAddress;
	
	@FindBy(xpath = "//*[@id='smtpHost']")//Enter smtpHost value 
	WebElement smtpHost;
	
	@FindBy(xpath = "//*[@id='smtpPort']")//Enter smtpPort value 
	WebElement smtpPort;
	
	@FindBy(xpath = "//*[@id='userName']")//Enter userName value 
	WebElement userName;
	
	@FindBy(xpath = "//*[@id='mconfig_password']")//Enter mconfig_password value 
	WebElement mconfig_password;
	
	@FindBy(xpath = "//*[@id='isEnabled']")//Enter isEnabled value 
	WebElement isEnabled;
	
	@FindBy(xpath = "//*[@id='mailForm']/table/tbody/tr[9]/td[2]/a")//Enter isEnabled value 
	WebElement mailSave;
	
	public MailPage() {

		super();
		hmePg = new HomePage();
	}
	
	public HomePage getHmePage(){
		return hmePg;
	}
	
	public void setMailParameters(String Mail_Address, String SMTP_Host, String SMTP_Port, String User_Name, String Password) throws Exception 
	{
		try 
		{
			mailAddress.clear();
			smtpHost.clear();
			smtpPort.clear();
			userName.clear();
			mconfig_password.clear();		
			mailAddress.sendKeys(Mail_Address);
			smtpHost.sendKeys(SMTP_Host);
			smtpPort.sendKeys(SMTP_Port);
			userName.sendKeys(User_Name);
			mconfig_password.sendKeys(Password);
			mailSave.click();
			Util.closeAlert();
		}
		
		catch (Exception e) {
			log.info("Mail Parameters not set and saved");
			Util.closeAlert();
			//throw (e);
		} 
	}
	
	
	//TODO	
	public void verifyMailParameters(String Mail_Address, String SMTP_Host, String SMTP_Port, String User_Name) throws Exception 
	{
		try 
		{			
			String receive_mailAddress =(String) ((JavascriptExecutor)driver).executeScript("return arguments[0].value", mailAddress);
			String receive_smtpHost =(String) ((JavascriptExecutor)driver).executeScript("return arguments[0].value", smtpHost);
			String receive_smtpPort =(String) ((JavascriptExecutor)driver).executeScript("return arguments[0].value", smtpPort);
			String receive_userName =(String) ((JavascriptExecutor)driver).executeScript("return arguments[0].value", userName);
				
			if ((receive_mailAddress.equalsIgnoreCase(Mail_Address))||(receive_smtpHost.equalsIgnoreCase(SMTP_Host))||(receive_smtpPort.equalsIgnoreCase(SMTP_Port))||(receive_userName.equalsIgnoreCase(User_Name)))
			{
				log.info("Mail Parameters are verified");			
			}
			else
			{
				log.info("Mail Parameters are not verified");		
			}

		Util.closeAlert();
		}
		
		catch (Exception e) {
			log.info("Mail Parameters are not verified");
			Util.closeAlert();
			//throw (e);
		} 
	}

	

	
}
