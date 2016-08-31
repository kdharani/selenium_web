package com.appsfreedom.fm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TenantAdminstrationPage extends BasePage{
	
	public HomePage home;
	
	@FindBy(xpath = "//tbody[@id='tenantBody']")
	WebElement tenantBodyTbl;
	
	

}
