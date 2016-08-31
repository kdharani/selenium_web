package com.appsfreedom.utils;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util extends Driver {

	public static Logger log = Logger.getLogger("devpinoyLogger");

	public static String getMethodName() {

		return Thread.currentThread().getStackTrace()[1].getMethodName();

	}

	public static void refreshPage() throws Exception {
		try {
			driver.navigate().refresh();
			log.info("Page refreshed");

		} catch (Exception e) {
			log.info("Page not refreshed");
			throw (e);
		}
	}

	public static void closeAlert() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			log.debug(alert.getText());
			alert.accept();
		} catch (Exception e) {
			// exception handling
		}
	}

	public static boolean isAlertPresent() {
		boolean foundAlert = false;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		} catch (TimeoutException eTO) {
			foundAlert = false;
		}
		return foundAlert;
	}

	public static void delay(long wait) {
		try {
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getscreenshot(String name) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir") + "\\screenshots\\" + name + ".png";
		File newFile = null;
		// The below method will save the screen shot in d drive with name
		// "screenshot.png"
		try {

			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (scrFile.isFile()) {
			return filePath;
		}

		return "No file";
	}

	public static void switchToTab() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		// Switch to new window
		driver.switchTo().window(tabs.get(1));
		// driver.close();//do some action in new window(2nd tab)
		System.out.println("Switched to " + driver.getTitle() + " tab");

	}

	public static void switchToParentTab() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		// Switch to main/parent window
		driver.switchTo().window(tabs.get(0));
		System.out.println("Switched to " + driver.getTitle() + " tab");

	}

	public static boolean isElementDisplayed(WebElement element) {

		boolean result = false;

		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
			result = true;
		} catch (Exception e) {

		}

		return result;

	}
	
	public static boolean isElementClickable(WebElement element) {

		boolean result = false;
		
		try {
			element.click();
			result = true;
		} catch (Exception e) {

		}

		return result;

	}

	public static boolean isNotDisplayed(WebElement element) {

		boolean result = false;

		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
			result = true;
		} catch (Exception e) {

		}

		return result;

	}

	public static boolean isOptionExist(Select mySelect, String value) {
		boolean result = false;
		// Select mySelect= new Select(element);
		List<WebElement> options = mySelect.getOptions();
		for (WebElement option : options) {
			//System.out.println(option.getText());
			if (option.getText().equals(value)){
			result = true;
			break;
			}
		}

		return result;
	}

	public static WebElement waitForElement(WebElement element) throws Exception {
		WebElement obj = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			obj = wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			return obj;
		}

		return obj;

	}

	public static WebElement scrollToElement(WebElement element) throws Exception {

		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Util.waitForElement(element);
			return element;
		} catch (Exception e) {
			return null;
		}

	}

	public static void addJquery() {
		// Check for jQuery on the page, add it if need be
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("if (!window.jQuery) {"
				+ "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
				+ "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
				+ "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");

		// Use jQuery to add jquery-growl to the page
		js.executeScript("$.getScript('https://qm2.bifreedom.com/v4.4/fmService/js/vendor/jquery.growl.js')");

		// Use jQuery to add jquery-growl styles to the page
		js.executeScript(
				"$('head').append('<link rel=\"stylesheet\" href=\"https://qm2.bifreedom.com/v4.4/fmService/css/jquery.growl.css\" type=\"text/css\" />');");

		// jquery-growl w/ no frills
		// js.executeScript("$.growl({ title: 'GET', message: '/' });");
	}

	public static void showNotification(String type, String msg) {
		// jquery-growl w/ colorized output
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		if (type.equals("ERROR"))
			js.executeScript("$.growl.error({ title: 'ERROR', message: '" + msg + "' });");
		else if (type.equals("NOTICE"))
			js.executeScript("$.growl.notice({ title: 'NOTICE', message: '" + msg + "' });");
		else
			js.executeScript("$.growl.warning({ title: 'WARNING!', message: '" + msg + "' });");
		delay(5000);
	}

	public static void highlightElement(WebElement element) {
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: red; border: 3px solid red;");
			delay(500);
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		}
	}

	public static void markElement(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
				"color: red; border: 3px solid red;");

	}

	public static String trimZero(String str) {
		// String [] i = str.split("\\.");
		// return i[0];
		return str.split("\\.")[0];

	}

}
