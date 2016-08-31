package com.appsfreedom.fm.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.appsfreedom.utils.Driver;

public class BasePage extends Driver {
	// public WebDriver driver;
	public Logger log;

	public BasePage() {
		//this.driver = driver;
		loadElements();
		log = Logger.getLogger("devpinoyLogger");
		// PropertyConfigurator.configure(System.getProperty("user.dir")+"\\log\\log4j.properties");

	}

	/*public BasePage() {
		System.out.println("Base page constructor called");
	}*/

	public void loadElements() {
		PageFactory.initElements(driver, this);
	}

}
