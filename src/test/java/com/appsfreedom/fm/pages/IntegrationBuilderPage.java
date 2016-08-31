package com.appsfreedom.fm.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.appsfreedom.utils.Util;

public class IntegrationBuilderPage extends BasePage {

	@FindBy(xpath = "//*[@id='tab_tool_header']/li[2]")
	WebElement myToolbxTab;

	@FindBy(xpath = "//*[@id='tab_tool_header']/li[1]")
	WebElement myPrjTab;

	@FindBy(xpath = "//label[contains(text(), 'Freedom DB')]")
	WebElement freedomDbLable;

	@FindBy(xpath = "//*[@id='collapseafreedomdb']")
	WebElement selectSQLImg;

	@FindBy(xpath = "//*[@id='tabpropdraggablediv']")
	WebElement canvas;

	@FindBy(xpath = "//*[@id='projtree-accordion']")
	WebElement prjTree;

	@FindBy(xpath = "//*[@id='menus']/ul/li[3]")
	WebElement testMenu;

	@FindBy(xpath = "//a[contains(text(), 'Test/Execute')]")
	WebElement testExecuteLink;

	@FindBy(xpath = "//div[@id='popupModal']")
	WebElement modalPopup;

	@FindBy(xpath = "//*[@id='popupModal']/div[3]/a[1]")
	WebElement testBtn;

	@FindBy(xpath = "//*[@id='popupModal']/div[3]/a[2]")
	WebElement cancelBtn;

	@FindBy(xpath = "//textarea[@class='queryEditor']")
	WebElement outputTxtArea;

	@FindBy(xpath = "//*[@id='menus']/ul/li[1]")
	WebElement fileMenu;

	@FindBy(xpath = "//*[@id='menus']/ul/li[1]/ul/li[9]") // *[@id="popupModal"]/div[2]/div/div/div[2]/div/div/span/span[1]
	WebElement importLink;

	@FindBy(xpath = "//*[@id='file_upload']") // *[@id='file_upload']
	WebElement uploadTxtBx;

	@FindBy(xpath = "//*[@id='popupModal']/div[3]/a[1]")
	WebElement uploadOkBtn;

	public IntegrationBuilderPage() {
		super();
	}

	public IntegrationBuilderPage openFileMenu() throws Exception {
		try {
			fileMenu.click();
			log.info("Opened File menu");
		} catch (Exception e) {
			log.info("File menu not found");
			throw (e);
		}

		return this;
	}

	public IntegrationBuilderPage clickImportOption() throws Exception {
		try {
			importLink.click();
			log.info("Import dialogue opened");
		} catch (Exception e) {
			log.info("Import menu option not found");
			throw (e);
		}
		return this;
	}

	public IntegrationBuilderPage uploadfile(String path) throws Exception {
		try {
			uploadTxtBx.sendKeys(path);
			uploadOkBtn.click();
			log.info("File uploaded");
		} catch (Exception e) {
			// TODO: handle exception
			log.info("File upload failed");
			throw (e);
		}
		return this;
	}

	public IntegrationBuilderPage importBos(String path) throws Exception {

		try {
			openFileMenu();
			clickImportOption();
			uploadfile(path);
			Util.delay(5000);
			log.info("BOS imported successfully");
		} catch (Exception e) {
			log.info("BOS import failed");
			throw (e);
		}

		return this;

		/*
		 * JavascriptExecutor executor = (JavascriptExecutor)driver;
		 * executor.executeScript("arguments[0].click();", uploadTxtBx);
		 * ((JavascriptExecutor) driver).executeScript(
		 * "arguments[0].style.visibility = 'visible'; arguments[0].style.height = '20px'; arguments[0].style.width = '200px'; arguments[0].style.opacity = 1"
		 * , uploadTxtBx); ((JavascriptExecutor) driver).executeScript(
		 * "arguments[0].value = '" + path + "'", uploadTxtBx);
		 * uploadTxtBx.sendKeys(path);
		 */

		// put path to your image in a clipboard
		/*
		 * StringSelection ss = new StringSelection(path);
		 * Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss,
		 * null);
		 * 
		 * //imitate mouse events like ENTER, CTRL+C, CTRL+V Robot robot = new
		 * Robot(); robot.keyPress(KeyEvent.VK_ENTER);
		 * robot.keyRelease(KeyEvent.VK_ENTER);
		 * robot.keyPress(KeyEvent.VK_CONTROL); robot.keyPress(KeyEvent.VK_V);
		 * robot.keyRelease(KeyEvent.VK_V);
		 * robot.keyRelease(KeyEvent.VK_CONTROL);
		 * robot.keyPress(KeyEvent.VK_ENTER);
		 * robot.keyRelease(KeyEvent.VK_ENTER);
		 */

	}

	public IntegrationBuilderPage selectBOS(String prjName, String bosName) throws Exception {
		try {
			WebElement prjLnk = prjTree.findElement(By.id(prjName));
			WebElement collapse = prjTree.findElement(By.id("collapse" + prjName));
			if (collapse.getAttribute("class").equalsIgnoreCase("accordion-body collapse")) {
				prjLnk.click();
				Util.delay(5000);
				WebElement bos = collapse.findElement(By.id("lbl" + bosName));
				// new Actions(driver).doubleClick(bos).build().perform();
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", bos);
				log.info("BOS selected");
				Util.delay(5000);
				// driver.navigate().refresh();
			}
		} catch (Exception e) {
			log.info("BOS selection failed");
			throw (e);
		}

		return this;
	}

	public boolean testBos(String input, String output) throws Exception {

		boolean result = false;
		int i = 0;
		try {
			testMenu.click();
			testExecuteLink.click();
			List<WebElement> inputBxs = modalPopup.findElements(By.tagName("input"));
			if (inputBxs.size() > 0) {
				for (WebElement inputBox : inputBxs) {
					String text[] = input.split(";");
					inputBox.sendKeys(text[i]);
					i++;
				}
			}
			testBtn.click();
			Util.delay(10000);
			//System.out.println(outputTxtArea.getText());
			log.info(outputTxtArea.getText());
			if (output.equalsIgnoreCase(outputTxtArea.getText())) {
				result = true;
				log.info("BOS executed successfully");
			} else {
				// System.out.println("BOS output not matched with expected
				// result");
				log.info("BOS output not matched with expected result");
			}
			cancelBtn.click();
		} catch (Exception e) {
			log.info("Unable to execute BOS");
			throw (e);
		}

		return result;
	}

	public IntegrationBuilderPage switchToMyToolbx() throws Exception {
		try {
			if (!(myToolbxTab.getAttribute("class").equalsIgnoreCase("active"))) {
				myToolbxTab.click();
				log.info("Switched to My Toolbox");
			}
		} catch (Exception e) {
			log.info("Switch to My Toolbox failed");
			throw (e);
		}

		return this;
	}

	public IntegrationBuilderPage switchToMyProjects() throws Exception {
		try {
			if (!(myPrjTab.getAttribute("class").equalsIgnoreCase("active"))) {
				myPrjTab.click();
				log.info("Switched to My Projects");
			}
		} catch (Exception e) {
			log.info("Switch to My Projects failed");
			throw (e);
		}

		return this;
	}

	public IntegrationBuilderPage expandFreedomDb() throws Exception {
		try {
			freedomDbLable.click();
			log.info("Freedom DB action group expanded");
		} catch (Exception e) {
			log.info("Freedom DB action group not found");
			throw (e);
		}
		return this;
	}

	public IntegrationBuilderPage addSelectSQL() throws Exception {
		try {
			System.out.println(selectSQLImg.isDisplayed());
			System.out.println(selectSQLImg.findElements(By.tagName("p")).size());
			System.out.println(canvas.isDisplayed());
			WebElement element = selectSQLImg.findElement(By.id("SQL-Select"));
			System.out.println(element.isDisplayed());
			System.out.println(element.getAttribute("title"));

			// (new Actions(driver)).dragAndDrop(element,
			// canvas).build().perform();

			System.out.println(canvas.getLocation().x);
			System.out.println(canvas.getLocation().y);
			new Actions(driver).dragAndDropBy(element, canvas.getLocation().x + 200, canvas.getLocation().y + 100)
					.build().perform();

			Util.delay(5000);

			/*
			 * Actions builder = new Actions(driver);
			 * 
			 * Action dragAndDrop = builder.clickAndHold(element)
			 * .moveToElement(canvas) .release(canvas) .build();
			 * 
			 * dragAndDrop.perform();
			 */
		} catch (Exception e) {
			log.info("Add selectSQL failed");
			throw (e);
		}
		return this;
	}

	public HomePage closeBuilder() throws Exception {
		try {
			driver.close();
			Util.switchToParentTab();
			log.info("Closed Builder window");
		} catch (Exception e) {
			log.info("Unable to close builder window");
			throw (e);
		}
		return new HomePage();
	}
}
