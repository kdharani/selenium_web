package com.appsfreedom.fm.pages;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.appsfreedom.utils.SQLConnector;
import com.appsfreedom.utils.Util;

public class AppWatchPage extends BasePage {

	private HomePage hmePg;
	Logger log = Logger.getLogger("devpinoyLogger");

	@FindBy(xpath = "//ul[@id='analyzer_tab_header']//label[@title='Drive Usage Report']")
	WebElement driveUsageTab;
	@FindBy(xpath = "//ul[@id='analyzer_tab_header']//label[@title='Freedom App Report']")
	WebElement freedomAppReportTab;
	@FindBy(xpath = "//*[@id='driveusage_piechart_div']/canvas[2]")
	WebElement pieChart;

	@FindBy(xpath = "//*[@id='driveusage_table_body']/tr/td[3]")
	WebElement usedSpace;

	@FindBy(xpath = "//*[@id='driveusage_table_body']/tr/td[4]")
	WebElement availableSpace;
	
	@FindBy(xpath = "//*[@id='topusers_div']/canvas[6]") 
	WebElement topuserpieChart;
	
	@FindBy(xpath = "//*[@id='topapps_div']/canvas[6]")
	WebElement topappspieChart;
	
	@FindBy(xpath = "//div[@id='topusers_div']/table/tbody/tr")
	WebElement topusers;
	
	@FindBy(xpath = "//ul[@id='analyzer_tab_header']//label[@title='App Details Report']")
	WebElement appDetailsReportTab;
	
	@FindBy(xpath = "//*[@id='app_details_searchby_name']")
	WebElement appnameTxt;
	
	@FindBy(xpath = "//button[@id='app_details_searchButton']")
	WebElement searchBTN;
	
	@FindBy(xpath = "//*[@id='app_details_topusers_div']/canvas[2]")
	WebElement appsDetailReporttopuserpieChart;
	
	@FindBy(xpath = "//*[@id='app_details_topapps_div']/canvas[2]")
	WebElement appsDetailReporttopappspieChart;
	
	@FindBy(xpath = "//*[@id='app_details_topusers_bar_div']/canvas[5]")
	WebElement appsDetailReporttopuserbarChart;
	
	@FindBy(xpath = "//*[@id='app_details_topapps_bar_div']/canvas[5]")
	WebElement appsDetailReporttopappsbarChart;
	
	@FindBy(xpath = "//ul[@id='analyzer_tab_header']//label[@title='Access Report']")
	WebElement accessReportTab;
	
	@FindBy(xpath = "//*[@id='user_access_select_app_menu']/div/div/div/div/div/button[2]")
	WebElement accessReportAllAppsBTN;
	 
	
	public AppWatchPage() {

		super();
		hmePg = new HomePage();
	}
	
	public HomePage getHmePage(){
		return hmePg;
	}

	public float getUsedSapce() throws Exception {
		float used = 0;
		String temp[];
		try {
			temp = usedSpace.getText().split(" ");
			used = Float.valueOf(temp[0]);
			log.info("Used sapce is " + used);
		} catch (Exception e) {
			log.info("Used space is not displayed");
			throw(e);
		}
		return used;
	}
	
	public float getAvailableSapce() throws Exception {
		float available = 0;
		String temp[];
		try {
			temp = availableSpace.getText().split(" ");
			available = Float.valueOf(temp[0]);
			log.info("Available sapce is " + available);
		} catch (Exception e) {
			log.info("Available space is not displayed");
			throw(e);
		}
		return available;
	}

	public AppWatchPage clickDriveUsageTab() throws Exception {

		try {
			driveUsageTab.click();
			log.info("Clicked Drive usage report tab");
		} catch (Exception e) {
			log.error("Drive usage report tab found");
			throw (e);
		}

		return new AppWatchPage();
	}
	public List<String> getTopUsers() throws Exception {
		List<String> userList = new ArrayList<String>();
		try {
			//temp = usedSpace.getText().split(" ");
			//used = Float.valueOf(temp[0]);
			int rowCount=driver.findElements(By.xpath("//div[@id='topusers_div']/table/tbody/tr")).size();
			List<WebElement> rows = driver.findElements(By.xpath("//div[@id='topusers_div']/table/tbody/tr"));
			log.info("Top users table row size is " + rowCount);
			for(int k=0;k<=rows.size();k++){
				List<WebElement> cols = driver.findElements(By.xpath("//div[@id='topusers_div']/table/tbody/tr["+k+"]/td"));
				log.info("Top users table col size is " + cols.size());
				if(cols.size()>0){
					log.info("val are :"+cols.get(1).getText());
					userList.add(cols.get(1).getText());
				}
			}
			
		} catch (Exception e) {
			log.info("Top users table is not displayed");
			throw(e);
		}
		return userList;
	}
	public List<String> getTopApps() throws Exception {
		List<String> appList = new ArrayList<String>();
		try {
			//temp = usedSpace.getText().split(" ");
			//used = Float.valueOf(temp[0]);
			int rowCount=driver.findElements(By.xpath("//div[@id='topapps_div']/table/tbody/tr")).size();
			List<WebElement> rows = driver.findElements(By.xpath("//div[@id='topapps_div']/table/tbody/tr"));
			log.info("Top users table row size is " + rowCount);
			for(int k=0;k<=rows.size();k++){
				List<WebElement> cols = driver.findElements(By.xpath("//div[@id='topapps_div']/table/tbody/tr["+k+"]/td"));
				log.info("Top users table col size is " + cols.size());
				if(cols.size()>0){
					log.info("val are :"+cols.get(1).getText());
					appList.add(cols.get(1).getText());
				}
			}
			
		} catch (Exception e) {
			log.info("Top users table is not displayed");
			throw(e);
		}
		return appList;
	}
	public AppWatchPage clickFreedomAppReportTab() throws Exception {

		try {
			freedomAppReportTab.click();
			log.info("Clicked Freedom App Report tab");
		} catch (Exception e) {
			log.error("Freedom App report tab not found");
			throw (e);
		}

		return new AppWatchPage();
	}
	
	public AppWatchPage clickAppDetailsReportTab() throws Exception {

		try {
			appDetailsReportTab.click();
			log.info("Clicked App Details Report tab");
		} catch (Exception e) {
			log.error("App Details Report tab not found");
			throw (e);
		}

		return new AppWatchPage();
	}
	public AppWatchPage clickAccessReportTab() throws Exception {

		try {
			accessReportTab.click();
			log.info("Clicked Access Report tab");
		} catch (Exception e) {
			log.error("Access Report tab not found");
			throw (e);
		}

		return new AppWatchPage();
	}

	public AppWatchPage openUsageReport() throws Exception {

		clickDriveUsageTab();
		try {
			if ((Util.isElementDisplayed(pieChart))) {
				log.info("Drive usage report loaded succussfully");
			}
		} catch (Exception e) {
			log.info("Driver usage report not loaded properly");
			throw (e);
		}

		/*
		 * int tryCount = 0; while (tryCount < 20) { if
		 * (driveUsageTab.findElements(By.tagName("tr")).size() > 0) {
		 * //System.out.println("Organization page loaded"); log.info(
		 * "Organization page loaded"); Util.getscreenshot(driver,
		 * "Organization"); break; } else { Util.delay(1000); tryCount++; }
		 * 
		 * if (tryCount > 20) { //System.out.println(
		 * "Organization page not loaded"); log.debug(
		 * "Organization page not loaded"); Util.getscreenshot(driver,
		 * "OrganizationFail"); } }
		 */
		return this;
	}
	public AppWatchPage freedomAppReport() throws Exception {

		clickFreedomAppReportTab();
		try {
			if ((Util.isElementDisplayed(topuserpieChart) || Util.isElementDisplayed(topappspieChart))) {
				log.info("Freedom App Report loaded succussfully");
			}
		} catch (Exception e) {
			log.info("Freedom App Report not loaded properly");
			throw (e);
		}
		return this;
	}
	public AppWatchPage setAppName(String appName) throws Exception {

		try {
			appnameTxt.clear();
			appnameTxt.sendKeys(appName);
			log.info("appName entered");
		} catch (Exception e) {
			log.info("appName input field not found");
			throw (e);
		}
		return this;
	}
	public AppWatchPage clickSearch() throws Exception {
		try {
			searchBTN.click();
			log.info("searchBTN button clicked");
		} catch (Exception e) {
			log.info("searchBTN button not found");
			throw (e);
		}
		return new AppWatchPage();
	}
	public AppWatchPage appDetailsReport(String appName) throws Exception {

		clickAppDetailsReportTab();
		try {
			log.info("App Details Report loaded succussfully");
			 setAppName(appName);
			 clickSearch();
			 log.info("App Details Report isAlertPresent :"+Util.isAlertPresent());
			 if (!(Util.isAlertPresent())) {
					//hmePg.switchToFrame();
					try {
						if ((Util.isElementDisplayed(appsDetailReporttopappsbarChart) || Util.isElementDisplayed(appsDetailReporttopappsbarChart))) {
							log.info("App Details Report charts loaded succussfully");
							List<WebElement> rows = driver.findElements(By.xpath("//*[@id='app_details_table_body']/tr"));
							log.info("App Details Report search size:"+rows.size());
						}else{
							log.info("App Details Report search No data available ");
						}
					} catch (Exception e) {
						log.info("App Details Report search unsuccessful");
						throw (e);
					}
				} else {
					// Check if alert exists
					log.info("App Details Report - No data available for searched appName");
					BufferedImage image = new Robot()
							.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
					ImageIO.write(image, "png",
							new File(System.getProperty("user.dir") + "\\screenshots\\" + "appDetailsReport_noData" + ".png"));
					Util.closeAlert();
				}
			
			/*if ((Util.isElementDisplayed(topuserpieChart, driver) || Util.isElementDisplayed(topappspieChart, driver))) {
				log.info("Freedom App Report loaded succussfully");
			}*/
		} catch (Exception e) {
			log.info("App Details Report not loaded properly");
			throw (e);
		}
		return this;
	}
	public AppWatchPage clickAccessreportAllApps() throws Exception {

		try {
			accessReportAllAppsBTN.click();
			log.info("Clicked Access Report All Apps button");
		} catch (Exception e) {
			log.error(" Access Report All Apps button not found");
			throw (e);
		}

		return new AppWatchPage();
	}
	public AppWatchPage accessReport(String LoginName,String AppName,String tenantId) throws Exception {

		clickAccessReportTab();
		try {
			log.info("Access Report loaded succussfully");
			List<WebElement> allusersrows = driver.findElements(By.xpath("//*[@id='access_report_table_body']/tr"));
			
			Map<String,String> resultset = SQLConnector.getAccessreport(tenantId, "users");
			log.info("Access Report all users size:"+allusersrows.size()+" db size:"+resultset.size());
			if(resultset.size()==allusersrows.size()){
				String lastAccessed ="",days_elapsed="";
				log.info("Access Report all users record count matches");
				for(int k=0;k<=allusersrows.size();k++){
					List<WebElement> cols = driver.findElements(By.xpath("//*[@id='access_report_table_body']/tr["+k+"]/td"));
					log.info("Access Report all users col size is " + cols.size());
					if(cols.size()>0){
						if(LoginName.equalsIgnoreCase(cols.get(2).getText()) && AppName.equalsIgnoreCase(cols.get(3).getText())){
							log.info("Access Report all users input available in UI Table");
							lastAccessed = cols.get(4).getText();
							days_elapsed = cols.get(5).getText();
						}
					}
				}
				String la_de = resultset.get(LoginName+"-"+AppName);
				if(la_de!=null){
					log.info("Access Report all users input available in DB Table");
					if(la_de.equalsIgnoreCase(lastAccessed+"-"+days_elapsed)){
						log.info("Access Report all users LastAccessed and DaysElapsed are equal in both UI and DB");
					}
				}
			}else{
				log.info("Access Report all users record count mismatches");
			}
			clickAccessreportAllApps();
			List<WebElement> allappsrows = driver.findElements(By.xpath("//*[@id='access_report_table_body']/tr"));
			
			Map<String,String> allappsresultset = SQLConnector.getAccessreport(tenantId, "apps");
			log.info("Access Report all apps size:"+allappsrows.size()+" db size:"+allappsresultset.size());
			if(allappsresultset.size()==allappsrows.size()){
				String lastAccessed ="",days_elapsed="";
				log.info("Access Report all apps record count matches");
				for(int k=0;k<=allappsrows.size();k++){
					List<WebElement> cols = driver.findElements(By.xpath("//*[@id='access_report_table_body']/tr["+k+"]/td"));
					log.info("Access Report all apps col size is " + cols.size());
					if(cols.size()>0){
						if(LoginName.equalsIgnoreCase(cols.get(2).getText()) && AppName.equalsIgnoreCase(cols.get(3).getText())){
							log.info("Access Report all apps input available in UI Table");
							lastAccessed = cols.get(4).getText();
							days_elapsed = cols.get(5).getText();
						}
					}
				}
				String la_de = allappsresultset.get(LoginName+"-"+AppName);
				if(la_de!=null){
					log.info("Access Report all apps input available in DB Table");
					if(la_de.equalsIgnoreCase(lastAccessed+"-"+days_elapsed)){
						log.info("Access Report all apps LastAccessed and DaysElapsed are equal in both UI and DB");
					}
				}
			}else{
				log.info("Access Report all users record count mismatches");
			}
			 
		} catch (Exception e) {
			log.info("App Details Report not loaded properly");
			throw (e);
		}
		return this;
	}

}
