package com.appsfreedom.fm.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;

import com.appsfreedom.utils.Util;

public class AppLibPage extends BasePage {

	@FindBy(xpath = "//ul[@id='store_tab_header']//label[@title='BaaS']")
	WebElement baasTab;
	@FindBy(xpath = "//*[@id='bosTable']")
	WebElement bosTable;
	@FindBy(xpath = "//ul[@id='store_tab_header']//label[@title='Business Groups']")
	WebElement businessGrpTab;
	@FindBy(xpath = "//ul[@id='store_tab_header']//label[@title='Freedom Apps']")
	WebElement freedomAppsTab;

	public AppLibPage() {
		super();
	}

	public AppLibPage openBaasTab(String PrjName, String BOSName) throws Exception {
		clickBaasTab();
		try {
			if ((Util.isElementDisplayed(bosTable))) {
				log.info("BaaS tab loaded succussfully");
				List<WebElement> bosrows = driver.findElements(By.xpath("//*[@id='bosBody']/tr"));
				log.info("bosrows size:" + bosrows.size());
				for (int k = 0; k < bosrows.size(); k++) {

					List<WebElement> boscols = null;
					if (bosrows.size() == 1) {
						boscols = driver.findElements(By.xpath("//*[@id='bosBody']/tr/td"));
					} else {
						boscols = driver.findElements(By.xpath("//*[@id='bosBody']/tr[" + k + "]/td"));
					}
					log.info("boscols size:" + boscols.size());
					for (int j = 1; j <= boscols.size(); j++) {
						String prjBosNames = driver.findElement(By.xpath("//*[@id='bosBody']/tr/td[" + j + "]/div"))
								.getText();
						log.info("prjBosNames:"
								+ driver.findElement(By.xpath("//*[@id='bosBody']/tr/td[" + j + "]/div")).getText());
						if ((PrjName + "." + BOSName).equalsIgnoreCase(prjBosNames)) {
							log.info("BOS " + prjBosNames + " has been successfully published");
						}

					}
				}
			}
		} catch (Exception e) {
			log.info("BaaS tab not loaded properly");
			throw (e);
		}
		return this;
	}

	public AppLibPage clickBaasTab() throws Exception {

		try {
			baasTab.click();
			log.info("Clicked BaaS tab");
		} catch (Exception e) {
			log.error("BaaS tab not found");
			throw (e);
		}

		return new AppLibPage();
	}

	public BusinessGroupPage openBusinessGrpTab() throws Exception {

		try {
			businessGrpTab.click();
			log.info("Clicked Business Group tab");
		} catch (Exception e) {
			log.error("Business Group tab not found");
			throw (e);
		}

		return new BusinessGroupPage();
	}

	public FreedomAppsPage openFreedomAppsTab() throws Exception {

		try {
			freedomAppsTab.click();
			log.info("Clicked Freedom Apps tab");
		} catch (Exception e) {
			log.error("Freedom Apps tab not found");
			throw (e);
		}

		return new FreedomAppsPage();
	}

}
