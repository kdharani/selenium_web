package com.appsfreedom.fm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SchedulerPage extends BasePage{
	
	private HomePage hmePg;
	
	@FindBy(xpath="//*[@id='scheduler']/table/tbody/tr[4]/td/div/table/tbody/tr/td/table/tbody/tr/td/a")
	private WebElement create;
	
	@FindBy(xpath="//*[@id='sch-type']")
	private WebElement type;
	
	@FindBy(xpath="//*[@id='schedname']")
	private WebElement name;
	
	@FindBy(xpath="//*[@id='description']")
	private WebElement description;
	
	@FindBy(xpath="//*[@id='bosName']")
	private WebElement bos;
	
	@FindBy(xpath="//*[@id='sch-bos-enabled']")
	private WebElement enabled;
	
	public SchedulerPage(){
		super();
		hmePg = new HomePage();
	}
	
	public HomePage getHmePage(){
		return hmePg;
	}

}
