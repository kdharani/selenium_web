package com.appsfreedom.fm.pages;

import org.openqa.selenium.WebDriver;

import com.appsfreedom.utils.Util;

public class AppDesignerPage extends BasePage{
	
	public AppDesignerPage() {
		super ();
	}
	

	public HomePage closeAppDesigner(){
		driver.close();
		Util.switchToParentTab();
		return new HomePage();
	}
	
	
}
