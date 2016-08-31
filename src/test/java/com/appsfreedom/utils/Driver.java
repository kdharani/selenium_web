package com.appsfreedom.utils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Driver {
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;

	protected Driver() {

	}

	public static WebDriver startDriver(String browser) throws Exception{
		try {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + File.separator+"driver"+File.separator+"chromedriver.exe");
				driver = new ChromeDriver(dc);
			} else if (browser.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver(dc);
			} else if (browser.equalsIgnoreCase("iexplore")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + File.separator+"driver"+File.separator+"IEDriverServer.exe");
				driver = new InternetExplorerDriver(dc);
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			// chromeDriver.get("http://www.google.com");
		} catch (Exception e) {
			MyLogger.log.debug("FAIL : Some problem in launching the driver. Pleae check the path");
			//System.out.println("FAIL : Some problem in launching the driver. Pleae check the path");
			throw(e);
		}
		return driver;
	}

	public static String stopDriver() {

		if (!(driver == null)) {

			driver.quit();
		}

		else {
			return ("FAIL : No driver instance to stop");
		}

		return ("PASS");
	}

}
