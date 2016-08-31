package com.appsfreedom.fm.pages;

import org.openqa.selenium.WebDriver;

import com.appsfreedom.utils.Util;

public class DrivePage extends BasePage{

	
	public DrivePage() {
		
		super();
	}
	
	public HomePage closeDrive(){
		driver.close();
		Util.switchToParentTab();
		return new HomePage();
	}

}
