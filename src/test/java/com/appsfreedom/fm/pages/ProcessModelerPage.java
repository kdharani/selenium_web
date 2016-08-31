package com.appsfreedom.fm.pages;

import org.openqa.selenium.WebDriver;

import com.appsfreedom.utils.Util;

public class ProcessModelerPage extends BasePage{
	
	public ProcessModelerPage() {
		super ();
	}
	
	public HomePage closeProcessModeler() throws Exception{
		try {
			driver.close();
			Util.switchToParentTab();
			log.info("Closed Process modeler window");
		} catch (Exception e) {
			log.info("Process modeler window not closed");
			throw(e);
		}
		return new HomePage();
	}
}
