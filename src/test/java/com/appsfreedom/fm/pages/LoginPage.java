package com.appsfreedom.fm.pages;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.appsfreedom.utils.Initializer;
import com.appsfreedom.utils.Util;

public class LoginPage extends BasePage {

	@FindBy(xpath = "//*[@id='loginForm']")
	WebElement loginForm;
	@FindBy(xpath = "//*[@id='userId']")
	WebElement userIdTxtbx;
	@FindBy(xpath = "//*[@id='password']")
	WebElement pwdTxtbx;
	@FindBy(xpath = "//*[@id='tenantId']")
	WebElement tenantIdTxtbx;
	@FindBy(xpath = "//*[@id='landscape']")
	WebElement envSelectbx;
	@FindBy(xpath = "//button[@class='signIn-button' and @value='SignIn']")
	WebElement signInBtn;

	public LoginPage() {
		super();
	}

	public void checkLoginPage() throws Exception {
		
		try {
			if ((Util.isElementDisplayed(loginForm))) {
				log.info("Login page loaded");
			}
		} catch (Exception e) {
			log.info("Login page not loaded");
			throw (e);
		}
/*		String result = "FAIL";

		if (loginForm.isDisplayed()) {
			result = "PASS";
		}
		return result;*/
	}

	public LoginPage setUserId(String userId) throws Exception {

		try {
			userIdTxtbx.clear();
			userIdTxtbx.sendKeys(userId);
			log.info("UserId entered");
		} catch (Exception e) {
			log.info("UserId input field not found");
			throw (e);
		}
		return this;
	}

	public LoginPage setPassword(String pwd) throws Exception {
		try {
			pwdTxtbx.clear();
			pwdTxtbx.sendKeys(pwd);
			log.info("Password entered");
		} catch (Exception e) {
			log.info("Password input field not found");
			throw (e);
		}
		return this;
	}

	public LoginPage setTenantId(String tenantId) throws Exception {
		try {
			tenantIdTxtbx.clear();
			tenantIdTxtbx.sendKeys(tenantId);
			log.info("Tenant Id entered");
		} catch (Exception e) {
			log.info("TenantId input field not found");
			throw (e);
		}
		return this;
	}

	public LoginPage selectEnv(String env) throws Exception {
		try {
			envSelectbx.sendKeys(env);
			log.info("Environment selected");
		} catch (Exception e) {
			log.info("Environment select field not found");
			throw (e);
		}
		return this;
	}

	public HomePage clickSignIn() throws Exception {
		try {
			signInBtn.click();
			log.info("SignIn button clicked");
		} catch (Exception e) {
			log.info("SignIn button not found");
			throw (e);
		}
		return new HomePage();
	}

	public HomePage login(String url, String user_id, String pwd, String tenant_id, String environment)
			throws Exception {

		// launch Freedom Manager
		// launchFM(url);
		driver.get(url);

		// Check if login form exists
		checkLoginPage();
/*		if (!(checkLoginPage().equals("PASS"))) {
			// System.out.println("Login page not loaded");
			log.debug("Login page not loaded");
		}*/
		// Enter User Id
		setUserId(user_id);
		// Enter password
		setPassword(pwd);
		// Enter tenant id
		setTenantId(tenant_id);
		// Select the environment
		selectEnv(environment);
		// Click login button
		HomePage hmePg = clickSignIn();
		// Check if home page is loaded
		if (!(Util.isAlertPresent())) {
			hmePg.switchToFrame();
			try {
				if ((Util.isElementDisplayed(hmePg.logoutBtn))) {
					log.info("Login successful");
				}
			} catch (Exception e) {
				log.info("Login unsuccessful");
				throw (e);
			}
		} else {
			// Check if alert exists
			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image, "png",
					new File(System.getProperty("user.dir") + "\\screenshots\\" + "Invalid credentials" + ".png"));
			Util.closeAlert();
		}
		return hmePg;

	}
	
	public boolean loginvalidation(String url, String user_id, String pwd, String tenant_id, String environment)
			throws Exception {
		boolean result = false;

		// launch Freedom Manager
		// launchFM(url);
		driver.get(url);

		// Check if login form exists
		checkLoginPage();
/*		if (!(checkLoginPage().equals("PASS"))) {
			// System.out.println("Login page not loaded");
			log.debug("Login page not loaded");
		}*/
		// Enter User Id
		setUserId(user_id);
		// Enter password
		setPassword(pwd);
		// Enter tenant id
		setTenantId(tenant_id);
		// Select the environment
		selectEnv(environment);
		// Click login button
		HomePage hmePg = clickSignIn();
		// Check if home page is loaded
		if (!(Util.isAlertPresent())) {
			hmePg.switchToFrame();
			try {
				if ((Util.isElementDisplayed(hmePg.logoutBtn))) {
					log.info("Login successful");
					result = true;
				}
			} catch (Exception e) {
				log.info("Login unsuccessful");
				throw (e);
			}
		} else {
			// Check if alert exists
			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image, "png",
					new File(System.getProperty("user.dir") + "\\screenshots\\" + "Invalid credentials" + ".png"));
			Util.closeAlert();
		}
		return result;

	}

}
