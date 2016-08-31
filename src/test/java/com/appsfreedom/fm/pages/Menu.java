package com.appsfreedom.fm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.appsfreedom.utils.Driver;
import com.appsfreedom.utils.Util;

public class Menu extends Driver {

	@FindBy(xpath = "//a[contains(text(), 'Platform Configurator')]")
	public static WebElement platformConfigLnk;
	@FindBys({ @FindBy(xpath = "//ul[@id='configurator_tab_header']"),
			@FindBy(xpath = "//label[@title='Subscriptions']") })
	public static  WebElement subscriptionTab;

	public static class PlatformConfig {

		public static class Subscription {

			public static void getTo() {
			
				try {
					//WebDriver driver = Driver.startDriver("chrome");
					//new LoginPage().login("https://delb.bifreedom.com", "admin", "v4AFM125", "AF10001", "Development");
					PageFactory.initElements(driver, Menu.class);
					platformConfigLnk.click();
					Util.delay(5000);
					subscriptionTab.click();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				

			}
		}
	}
}
