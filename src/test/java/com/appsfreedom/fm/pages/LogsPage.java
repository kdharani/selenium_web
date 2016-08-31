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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import com.appsfreedom.utils.SQLConnector;
import com.appsfreedom.utils.Util;

public class LogsPage extends BasePage 
{
	private HomePage hmePg;
	Logger log = Logger.getLogger("devpinoyLogger");
	
	@FindBy(xpath = "//*[@id='logPageNumberString']")//logs string
	WebElement pageCount;
	
	@FindBy(xpath = "//*[@class='icon-white icon-search']")//logs string after search 
	WebElement searchIcon;

	@FindBy(xpath = "//*[@data-column='details']")//Enter search value 
	WebElement searchDetailsBox;
	
	public LogsPage() {

		super();
		hmePg = new HomePage();
	}
	
	public HomePage getHmePage(){
		return hmePg;
	}
	
	public int getLogsCounts() throws Exception 
	{
		try {
			String lgsCount = "";	
			String res[];
			lgsCount = pageCount.getText();
			log.info(lgsCount);
			res = lgsCount.split(" ");
			String name = res[6];
			log.info(name);
			log.info("Count read");
			return Integer.valueOf(name);
		} catch (Exception e) {
			log.info("Count not read");
			throw (e);
		}
	}
	

	public int searchLogEntries () throws Exception 
	{
		try {
			
			String lgsCount = "";	
			String textSearch = "admin Logout";
			String res[];
			
			searchIcon.click();
			searchDetailsBox.click();
			Util.delay(500);
			searchDetailsBox.sendKeys(textSearch);	
			lgsCount = pageCount.getText();
			log.info(lgsCount);
			res = lgsCount.split(" ");
			String name = res[6];
			log.info(name);
			log.info("Count read");
			return Integer.valueOf(name);
		} catch (Exception e) {
			log.info("Count not read");
			throw (e);
		}
	}
	

	
}
