package com.appsfreedom.fm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.appsfreedom.utils.Util;

public class ConnectionManagerPage extends BasePage {
	private HomePage hmePg;
	
	//Plugin
		@FindBy(xpath="//div[@id='addPluginDiv']//a[contains(text(),'Add')]")
		WebElement addPluginBtn;
		@FindBy(xpath="//form[@id='plugInForm']//input[@id='name']")
		WebElement pluginName;
		@FindBy(xpath="//form[@id='plugInForm']//textarea[@id='description']")
		WebElement pluginDesc;
		@FindBy(xpath="//form[@id='plugInForm']//textarea[@id='url']")
		WebElement pluginUrl;
		@FindBy(xpath="//form[@id='plugInForm']//input[@id='userId']")
		WebElement pluginUserId;
		@FindBy(xpath="//form[@id='plugInForm']//input[@id='password']")
		WebElement pluginPwd;
		@FindBy(xpath="//form[@id='plugInForm']//a[contains(text(),'Save')]")
		WebElement savePlguinBtn;
		@FindBy(xpath="//form[@id='plugInForm']//a[contains(text(),'Update')]")
		WebElement updatePlguinBtn;
		@FindBy(xpath="//form[@id='plugInForm']//a[contains(text(),'Cancel')]")
		WebElement cancelPlguinBtn;
		@FindBy(xpath = "//*[@id='pluginTable']/preceding::i[@class='icon-white icon-search']")
		WebElement searchBtn;
		@FindBy(xpath = "//*[@id='pluginTable']/preceding::i[@class='icon-white icon-refresh']")
		WebElement refreshBtn;
		@FindBy(xpath = "//*[@id='pluginTable']//input[@data-column='name']")
		WebElement userColumn;
		@FindBy(xpath = "//*[@id='pluginTable']//input[@data-column='description']")
		WebElement descColumn;
		
	
	// Connection Manager //
		@FindBy(xpath = "//div[@id='freedomplugin']//a[@onclick='javascript:showAddRFCConnectionForm();']")
		WebElement addConnectionBtn;
		@FindBy(xpath = "//div[@id='addRFCConnection']//select[@id='type']")
		WebElement systemType;
		@FindBy(xpath = "//div[@id='addRFCConnection']//input[@id='name']")
		WebElement connectionNme;
		@FindBy(xpath = "//div[@id='addRFCConnection']//input[@id='domain']")
		WebElement domain;
		@FindBy(xpath = "//div[@id='addRFCConnection']//input[@id='resturi']")
		WebElement restURI;
		@FindBy(xpath = "//div[@id='addRFCConnection']//select[@id='outputtype']")
		WebElement outputType;
		@FindBy(xpath = "//div[@id='addRFCConnection']//select[@id='method']")
		WebElement restMethod;
		@FindBy(xpath = "//div[@id='addRFCConnection']//input[@id='pwdreqd']")
		WebElement pwdRequired;
		@FindBy(xpath = "//div[@id='addRFCConnection']//textarea[@id='sampleoutput']")
		WebElement sampleOutput;
		@FindBy(xpath = "//div[@id='addRFCConnection']//input[@id='wsdl']")
		WebElement wsdlURI;
		@FindBy(xpath = "//div[@id='addRFCConnection']//a[@onclick='javascript:loadSoapUri();']")
		WebElement loadSoapURI;
		@FindBy(xpath = "//div[@id='addRFCConnection']//textarea[@id='description']")
		WebElement description;
		@FindBy(xpath = "//div[@id='addRFCConnection']//textarea[@id='parameters']")
		WebElement rfc_destination;
		@FindBy(xpath = "//div[@id='addRFCConnection']//input[@id='isRemote']")
		WebElement onPrimise;
		@FindBy(xpath = "//div[@id='addRFCConnection']//input[@id='dataSource']")
		WebElement dataSource;
		@FindBy(xpath = "//div[@id='addRFCConnection']//select[@id='dbType']")
		WebElement dbType;
		@FindBy(xpath = "//div[@id='addRFCConnection']//select[@id='pluginId']")
		WebElement plugin;
		@FindBy(xpath = "//div[@id='addRFCConnection']//a[@onclick='javascript:savePluginConnection();']")
		WebElement saveRFC;
		@FindBy(xpath = "//div[@id='addRFCConnection']//a[@onclick='javascript:closeConnectionEditor();']")
		WebElement cancelRFC;
		@FindBy(xpath = "//table[@id='connectionsTable']")
		WebElement connectionsTbl;
		
		
	public ConnectionManagerPage() {
		super();
		hmePg = new HomePage();
	}
	
	public HomePage getHmePage(){
		return hmePg;
	}
	
	public ConnectionManagerPage selectDbType(String type) throws Exception {
		try {
			Select select = new Select(dbType);
			select.selectByVisibleText(type);
			log.info("DB type selected");
		} catch (Exception e) {
			log.info("DB type field not found");
			throw (e);
		}
		return this;

	}

	public ConnectionManagerPage enterdataSource(String source) throws Exception {
		try {
			dataSource.sendKeys(source);
			log.info("Data source entered");
		} catch (Exception e) {
			log.info("Date source field not found");
			throw (e);
		}
		return this;

	}

	public ConnectionManagerPage enterSampleOutput(String sample) throws Exception {
		try {
			sampleOutput.sendKeys(sample);
			log.info("Sample output entered");
		} catch (Exception e) {
			log.info("Sample outuput field not found");
			throw (e);
		}
		return this;

	}

	public ConnectionManagerPage enablePwdRequired() throws Exception {
		try {
			pwdRequired.click();
			log.info("Password enabled");
		} catch (Exception e) {
			log.info("Required password field not found");
			throw (e);
		}
		return this;

	}

	public ConnectionManagerPage selectOutpuType(String type) throws Exception {
		try {
			Select select = new Select(outputType);
			select.selectByVisibleText(type);
			log.info("Output type selected");
		} catch (Exception e) {
			log.info("Output type field not found");
			throw (e);
		}
		return this;

	}

	public ConnectionManagerPage selectRestMethod(String method) throws Exception {
		try {
			Select select = new Select(restMethod);
			select.selectByVisibleText(method);
			log.info("Rest method selected");
		} catch (Exception e) {
			log.info("Method field not found");
			throw (e);
		}
		return this;

	}

	public ConnectionManagerPage enterDomain(String restDomain) throws Exception {
		try {
			domain.sendKeys(restDomain);
			log.info("Domain entered");
		} catch (Exception e) {
			log.info("Domain field not found");
			throw (e);
		}
		return this;

	}

	public ConnectionManagerPage enterRestURI(String URI) throws Exception {
		try {
			restURI.sendKeys(URI);
			log.info("Rest URI entered");
		} catch (Exception e) {
			log.info("URI field not found");
			throw (e);
		}
		return this;

	}

	public boolean IsConnectionExists(String connection_name) throws Exception {
		boolean result = false;
		if (driver
				.findElements(
						By.xpath("//table[@id='connectionsTable']/tbody//td[text()=" + "'" + connection_name + "']"))
				.size() > 0) {
			log.info("Connection exists");
			result = true;
		} else {
			log.info("Connection not exists");
		}

		return result;

	}

	public ConnectionManagerPage saveConnection() throws Exception {
		try {
			saveRFC.click();
			log.info("Connection saved");
		} catch (Exception e) {
			log.info("Save connection button not found");
			throw (e);
		}
		return this;

	}

	public ConnectionManagerPage closeConnectionForm() throws Exception {
		try {
			Util.delay(2000);
			cancelRFC.click();
			log.info("Connection form closed");
		} catch (Exception e) {
			log.info("Cancel button  not found");
			throw (e);
		}
		return this;

	}

	public ConnectionManagerPage selectPlugin(String pluginNme) throws Exception {
		try {
			Select select = new Select(plugin);
			select.selectByVisibleText(pluginNme);
			log.info("Plugin selected");
		} catch (Exception e) {
			log.info("Plugin field not found");
			throw (e);
		}
		return this;

	}

	public ConnectionManagerPage enableOnPrimise() throws Exception {
		try {
			onPrimise.click();
			log.info("On Premise enabled");
		} catch (Exception e) {
			log.info("Onpremise field not found");
			throw (e);
		}
		return this;

	}

	public ConnectionManagerPage enterRFCDestination(String rfc) throws Exception {
		try {
			rfc_destination.sendKeys(rfc);
			log.info("RFC destination entered");
		} catch (Exception e) {
			log.info("RFC destination field not found");
			throw (e);
		}
		return this;

	}

	public ConnectionManagerPage enterConnectionDesc(String desc) throws Exception {
		try {
			description.sendKeys(desc);
			log.info("Connection desc entered");
		} catch (Exception e) {
			log.info("Connection desc field not found");
			throw (e);
		}
		return this;

	}

	public ConnectionManagerPage loadWebSerives() throws Exception {
		try {
			loadSoapURI.click();
			log.info("Webservices loaded");
		} catch (Exception e) {
			log.info("Load webservice field not found");
			throw (e);
		}
		return this;

	}

	public ConnectionManagerPage enterWsdlURI(String URI) throws Exception {
		try {
			wsdlURI.sendKeys(URI);
			log.info("WSDL URI entered");
		} catch (Exception e) {
			log.info("WSDL URI field not found");
			throw (e);
		}
		return this;

	}

	public ConnectionManagerPage enterConnectionNme(String name) throws Exception {
		try {
			connectionNme.sendKeys(name);
			log.info("Connection name entered");
		} catch (Exception e) {
			log.info("Connection name field not found");
			throw (e);
		}
		return this;

	}

	public ConnectionManagerPage selectSystem(String system) throws Exception {
		try {
			Util.waitForElement(systemType);
			Select select = new Select(systemType);
			select.selectByVisibleText(system);
			log.info("System selected");
		} catch (Exception e) {
			log.info("system field not found");
			throw (e);
		}
		return this;

	}

	public ConnectionManagerPage openAddconnectionForm() throws Exception {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", addConnectionBtn);
			// addConnectionBtn.click();
			log.info("Add connection form opened");
		} catch (Exception e) {
			log.info("Add connection form not found");
			throw (e);
		}
		return this;

	}
	
	public ConnectionManagerPage openAddPluginForm() throws Exception {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", addPluginBtn);
			// addConnectionBtn.click();
			log.info("Add plugin form opened");
			Util.delay(1000);
		} catch (Exception e) {
			log.info("Add plugin form not opened");
			throw (e);
		}
		return this;

	}
	
	public ConnectionManagerPage enterPluginNme(String name) throws Exception {
		try {
			pluginName.clear();
			pluginName.sendKeys(name);
			log.info("Plugin name entered");
		} catch (Exception e) {
			log.info("Plugin name field not found");
			throw (e);
		}
		return this;

	}
	
	public ConnectionManagerPage enterPluginDesc(String desc) throws Exception {
		try {
			pluginDesc.clear();
			pluginDesc.sendKeys(desc);
			log.info("Plugin description entered");
		} catch (Exception e) {
			log.info("Plugin name desc not found");
			throw (e);
		}
		return this;

	}
	
	public ConnectionManagerPage enterPluginUrl(String URL) throws Exception {
		try {
			pluginUrl.clear();
			pluginUrl.sendKeys(URL);
			log.info("Plugin URL entered");
		} catch (Exception e) {
			log.info("Plugin URL field not found");
			throw (e);
		}
		return this;

	}
	
	public ConnectionManagerPage enterPluginUserId(String userId) throws Exception {
		try {
			pluginUserId.clear();
			pluginUserId.sendKeys(userId);
			log.info("Plugin user id entered");
		} catch (Exception e) {
			log.info("Plugin userId field not found");
			throw (e);
		}
		return this;

	}
	
	public ConnectionManagerPage enterPluginPwd(String pwd) throws Exception {
		try {
			pluginPwd.clear();
			pluginPwd.sendKeys(pwd);
			log.info("Plugin password entered");
		} catch (Exception e) {
			log.info("Plugin password field not found");
			throw (e);
		}
		return this;

	}
	
	public ConnectionManagerPage savePlugin() throws Exception {
		try {
			savePlguinBtn.click();
			log.info("Plugin saved");
		} catch (Exception e) {
			log.info("Save plugin button not found");
			throw (e);
		}
		return this;

	}
	
	public ConnectionManagerPage updatePlugin() throws Exception {
		try {
			((JavascriptExecutor) driver).executeScript("window.confirm = function(msg) { return true; }");
			updatePlguinBtn.click();
			log.info("Plugin updated");
		} catch (Exception e) {
			log.info("update plugin button not found");
			throw (e);
		}
		return this;

	}
	
	public ConnectionManagerPage unsavePlugin() throws Exception {
		try {
			cancelPlguinBtn.click();
			log.info("Clicked on unsave plugin button");
		} catch (Exception e) {
			log.info("unsave plugin button not found");
			throw (e);
		}
		return this;

	}
	
	public boolean isPluginExists(String name) throws Exception {
		boolean exists = false;
		try {
			searchBtn.click();
			log.info("Search button clicked");
			userColumn.sendKeys(name);
			log.info("Plugin name entered");
			if (driver.findElements(By.xpath("//*[@id='pluginBody']//td[text()='" + name + "']")).size() == 1) {
				exists = true;
				log.info("User " + name + " exists");
			}
		} catch (Exception e) {
			log.info("Unable to check user");
			throw (e);
		}
		return exists;
	}
	
	public void selectPluginForEdit(String name) throws Exception {
		boolean exists = false;
		try {
			searchBtn.click();
			log.info("Search button clicked");
			userColumn.sendKeys(name);
			log.info("Plugin name entered");
			WebElement editImg = driver.findElement(By.xpath("//*[@id='pluginBody']//td[contains(text(),'"+name+"')]/following::a[1]"));
			editImg.click();
			Util.delay(1000);
			log.info("Plugin " + name + " selected for edit");			
		} catch (Exception e) {
			log.info("Unable to select plugin");
			throw (e);
		}
	}
	
	public boolean isPluginUpdated(String desc) throws Exception {
		boolean exists = false;
		try {
			refreshBtn.click();
			Util.delay(1000);
			searchBtn.click();
			searchBtn.click();
			log.info("Search button clicked");
			descColumn.sendKeys(desc);
			log.info("Plugin desc entered");
			if (driver.findElements(By.xpath("//*[@id='pluginBody']//td[text()='" + desc + "']")).size() == 1) {
				exists = true;
				log.info("Plugin description updated");
			}
		} catch (Exception e) {
			log.info("Unable to find desc");
			throw (e);
		}
		return exists;
	}


}
