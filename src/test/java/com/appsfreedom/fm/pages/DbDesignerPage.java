package com.appsfreedom.fm.pages;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import com.appsfreedom.utils.Util;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DbDesignerPage extends BasePage{

	
	@FindBy(xpath = "//*[@id='menu-bar']/li[1]/a")
	WebElement menuFile;
	
	
	@FindBy(xpath = "//*[@id='menu-bar']/li[2]/a")
	WebElement menuTest;
	
	@FindBy(xpath = "//*[@id='menu-bar']/li[1]/ul/li[1]/a")
	WebElement submenuNewTable;
	
	@FindBy(xpath = "//*[@id='menu-bar']/li[1]/ul/li[2]/a")
	WebElement submenuNewEditor;
	
	@FindBy(xpath = "//*[@id='menu-bar']/li[1]/ul/li[3]/a")
	WebElement submenuNewView;
	
	@FindBy(xpath = "//*[@id='menu-bar']/li[1]/ul/li[4]/a")
	WebElement submenuSave;
	
	@FindBy(xpath = "//*[@id='menu-bar']/li[1]/ul/li[5]/a")
	WebElement submenuImport;
	
	@FindBy(xpath = "//*[@id='menu-bar']/li[1]/ul/li[6]/a")
	WebElement submenuExport;
	
	@FindBy(xpath = "//*[@id='menu-bar']/li[1]/ul/li[7]/a")
	WebElement submenuCopySystemTable;
	
	@FindBy(xpath = "//*[@id='menu-bar']/li[2]/ul/li/a")
	WebElement submenuExecute;
	
	@FindBy(xpath = "//*[@id='ctable_0']/div/div[1]/div[1]/input[1]")
	WebElement testboxTableName;
	
	@FindBy(xpath = "//*[@id='ctable_0']/div/div[1]/div[1]/input[2]")
	WebElement buttonAddColumn;
	
	@FindBy(xpath = "//*[@id='cATBLA']/div/div[1]/div[1]/input[2]")
	WebElement buttonAlterColumn;
	
	@FindBy(xpath = "//*[@id='ctable_0']/div/div[1]/div[1]/table/tr/td[1]")
	WebElement textfieldC1;
	
	@FindBy(xpath = "//*[@id='ctable_0']/div/div[1]/div[1]/table/tr/td[2]")
	WebElement textfieldDT1;
	
	@FindBy(xpath = "//*[@id='ctable_0']/div/div[1]/div[1]/table/tr/td[3]")
	WebElement textfieldLN1;
	
	@FindBy(xpath = "//*[@id='ctable_0']/div/div[1]/div[1]/table/tr/td[4]/input")
	WebElement checkboxPK1;
	
	@FindBy(xpath = "//*[@id='ctable_0']/div/div[1]/div[1]/table/tr/td[5]/input")
	WebElement checkboxNN1;
	
	@FindBy(xpath = "//*[@id='ctable_0']/div/div[1]/div[1]/table/tr/td[6]/input")
	WebElement checkboxAI1;
	
	@FindBy(xpath = "//*[@id='ctable_0']/div/div[1]/div[1]/table/tr[2]/td[1]")
	WebElement textfieldC2;
	
	@FindBy(xpath = "//*[@id='ctable_0']/div/div[1]/div[1]/table/tr[2]/td[2]")
	WebElement textfieldDT2;
	
	@FindBy(xpath = "//*[@id='ctable_0']/div/div[1]/div[1]/table/tr[2]/td[3]")
	WebElement textfieldLN2;
	
	@FindBy(xpath = "//*[@id='ctable_0']/div/div[1]/div[1]/table/tr[2]/td[4]/input")
	WebElement checkboxPK2;
	
	@FindBy(xpath = "//*[@id='ctable_0']/div/div[1]/div[1]/table/tr[2]/td[5]/input")
	WebElement checkboxNN2;
	
	@FindBy(xpath = "//*[@id='ctable_0']/div/div[1]/div[1]/table/tr[2]/td[6]/input")
	WebElement checkboxAI2;
	
	@FindBy(xpath = "//*[@id='cATBLA']/div/div[1]/div[1]/table/tr[3]/td[1]")
	WebElement textfieldC3;
	
	@FindBy(xpath = "//*[@id='cATBLA']/div/div[1]/div[1]/table/tr[3]/td[2]")
	WebElement textfieldDT3;
	
	@FindBy(xpath = "//*[@id='cATBLA']/div/div[1]/div[1]/table/tr[3]/td[3]")
	WebElement textfieldLN3;
	
	@FindBy(xpath = "//*[@id='ctable_0']/div/div[1]/div[3]/input")
	WebElement buttonSave;
	
	@FindBy(xpath = "//*[@id='cATBLA']/div/div[1]/div[3]/input")
	WebElement buttonAlterSave;	
		
	@FindBy(xpath = "//*[@id='cSQL_0']/div/div[1]/textarea")
	WebElement testArea;

	@FindBy(xpath = "//*[@id='Freedom DBroot']/li/span")
	WebElement dbrootArrow;	

	@FindBy(xpath = "//*[@id='Freedom DB2']/li/span")
	WebElement tablestArrow;	
	
	@FindBy(xpath = "//*[@id='Freedom DB3']/li/span")
	WebElement tableName;
	
	@FindBy(xpath = "//*[@id='propertiesContextMenu']/ul/li[4]/a")
	WebElement rightclickmenuDelete;
	
	@FindBy(xpath = "//*[@id='propertiesContextMenu']/ul/li[3]/a")
	WebElement rightclickmenuExport;
	
	@FindBy(xpath = "//*[@id='propertiesContextMenu']/ul/li[2]/a")
	WebElement rightclickmenuRetreive;
	
	@FindBy(xpath = "//*[@id='propertiesContextMenu']/ul/li[1]/a")
	WebElement rightclickmenuAlter;
	
	@FindBy(xpath = "//*[@id='message']")
	WebElement queryMessage;	
	
	public DbDesignerPage() {
		
		super();
	}
	

	public DbDesignerPage deleteTableTestDbDesigner (String t, ExtentTest test) throws Exception
	{	

		try 
		{			
			if(TableVerifyTestDbDesigner(t,test))
			{
				Actions build = new Actions(driver);
				build.contextClick(tableName).build().perform();
				rightclickmenuDelete.click();
				Util.closeAlert();	
				log.info("Table was deleted successfully");
			}
			else
			{				
				log.info("Table was not found to delete");
			}			
		}
		catch (Exception e) 
		{
			log.info("Table is not available to delete");
			throw(e);
		}
		return this;
	}

	public boolean TableVerifyTestDbDesigner (String t, ExtentTest test) throws Exception
	{	
		try 
		{
			Util.refreshPage();
			if(tablestArrow.isDisplayed())
			{
				List<WebElement> tables = tablestArrow.findElements(By.xpath("//label[contains(text(),'"+t+"')]"));
				int counts = tables.size();
				if (counts==1)
				{
					log.info("Table was found");	
					test.log(LogStatus.INFO, "Table was found");
					return true;
				}	
				else
				{
					log.info("Table was not found");
					test.log(LogStatus.INFO, "Table was not found");
					return false;
				}				
			}
			else if(dbrootArrow.isDisplayed())
			{
				dbrootArrow.click();
				Util.delay(500);
				tablestArrow.click();
				Util.delay(2000);				
				List<WebElement> tables = tablestArrow.findElements(By.xpath("//label[contains(text(),'"+t+"')]"));
				int counts = tables.size();
				if (counts==1)
				{
					log.info("Table was found");
					test.log(LogStatus.INFO, "Table was found");
					return true;	
				}
				else
				{
					log.info("Table was not found");
					test.log(LogStatus.INFO, "Table was not found");
					return false;	
				}
			}	
			else
			{
				log.info("Table was not found");
				test.log(LogStatus.INFO, "Table was not found");
				return false;
			}
		}
		catch (Exception e) 
		{
			log.info("Table is not available");
			test.log(LogStatus.INFO, "Table is not available");
			throw(e);
		}		
	}
	
	public boolean AlterVerifyTestDbDesigner (String t, String c3, String d3, String l3) throws Exception
	{	
		try 
		{
			Util.refreshPage();
			if(tablestArrow.isDisplayed())
			{
				List<WebElement> tables = tablestArrow.findElements(By.xpath("//label[contains(text(),'"+t+"')]"));
				int counts = tables.size();
				if (counts==1)
				{
					Actions build = new Actions(driver);
					build.contextClick(tableName).build().perform();
					rightclickmenuAlter.click();
					Util.delay(500);
					
					if(textfieldC3.getText().equalsIgnoreCase(c3) && textfieldDT3.getText().equalsIgnoreCase(d3) && textfieldLN3.getText().equalsIgnoreCase(l3))
					{						
						return true;
					}
					else
					{	
						return false;						
					}					
				}	
				else
				{
					log.info("Deatils was not found");
					return false;
				}				
			}
			else if(dbrootArrow.isDisplayed())
			{
				dbrootArrow.click();
				Util.delay(500);
				tablestArrow.click();
				Util.delay(2000);				
				List<WebElement> tables = tablestArrow.findElements(By.xpath("//label[contains(text(),'"+t+"')]"));
				int counts = tables.size();
				if (counts==1)
				{
					log.info("Table was found");
					return true;	
				}
				else
				{
					log.info("Table was not found");
					return false;	
				}
			}	
			else
			{
				log.info("Table was not found");
				return false;
			}
		}
		catch (Exception e) 
		{
			log.info("Table is not available");
			throw(e);
		}		
	}
	
	public DbDesignerPage createTable (String t, String c1, String d1, String l1, String pk1, String nn1, String ai1, String c2, String d2, String l2, ExtentTest test) throws Exception{
		try 
		{
			if(TableVerifyTestDbDesigner(t,test))
			{
				deleteTableTestDbDesigner(t,test);
			}
			
			menuFile.click();
			Util.delay(500);
			log.info("File clicked");
			test.log(LogStatus.INFO, "File clicked");
			
			submenuNewTable.click();
			Util.delay(500);
			log.info("NewTable clicked");
			test.log(LogStatus.INFO, "NewTable clicked");
			
			testboxTableName.clear();
			Util.delay(500);
			log.info("TableName cleared");
			test.log(LogStatus.INFO, "TableName cleared");
			
			testboxTableName.sendKeys(t);
			Util.delay(500);
			log.info("TableName filled");
			test.log(LogStatus.INFO, "TableName filled");
			
			textfieldC1.click();
			Util.delay(500);
			log.info("Textfield clicked");
			test.log(LogStatus.INFO, "Textfield clicked");
			
			WebElement C1_text = driver.findElement(By.xpath("//*[@id='ctable_0']/div/div[1]/div[1]/table/tr/td[1]/input"));
			C1_text.sendKeys(c1);
			Util.delay(500);
			log.info("C1 filled");
			test.log(LogStatus.INFO, "C1 filled");
			
			textfieldDT1.click();
			Util.delay(500);
			log.info("DT1 field clicked");
			test.log(LogStatus.INFO, "DT1 field clicked");
			
			WebElement D1_text = driver.findElement(By.xpath("//*[@id='ctable_0']/div/div[1]/div[1]/table/tr/td[2]/select"));
			D1_text.sendKeys(d1);
			Util.delay(500);
			log.info("DT1 filled");
			test.log(LogStatus.INFO, "DT1 filled");
						
			textfieldLN1.click();
			Util.delay(500);
			log.info("L1 clicked");
			test.log(LogStatus.INFO, "L1 clicked");
			
			WebElement L1_text = driver.findElement(By.xpath("//*[@id='ctable_0']/div/div[1]/div[1]/table/tr/td[3]/input"));
			L1_text.sendKeys(l1);
			Util.delay(500);
			log.info("L1 filled");
			test.log(LogStatus.INFO, "L1 filled");
			
			if(nn1.equals("Y"))
			{
				checkboxNN1.click();
				checkboxNN1.click();
				Util.delay(500);
				log.info("NotNull field clicked");
				test.log(LogStatus.INFO, "NotNull field clicked");
				
			}
			
			if(ai1.equals("Y"))
			{
				checkboxAI1.click();
				Util.delay(500);
				log.info("AutoIncrement field clicked");
				test.log(LogStatus.INFO, "AutoIncrement field clicked");
			}
			
			if(pk1.equals("Y"))
			{
				Util.delay(500);
				checkboxPK1.click();
				Util.delay(500);
				log.info("PrimaryKey field clicked");
				test.log(LogStatus.INFO, "PrimaryKey field clicked");
			}

			buttonAddColumn.click();
			Util.delay(500);
			log.info("Add Button clicked");
			test.log(LogStatus.INFO, "Add Button clicked");
			
			textfieldC2.click();
			Util.delay(500);
			log.info("Textfield clicked");
			test.log(LogStatus.INFO, "Textfield clicked");
			
			WebElement C2_text = driver.findElement(By.xpath("//*[@id='ctable_0']/div/div[1]/div[1]/table/tr[2]/td[1]/input"));
			C2_text.sendKeys(c2);
			Util.delay(500);
			log.info("C2 filled");
			test.log(LogStatus.INFO, "C2 filled");
			
			textfieldDT2.click();
			Util.delay(500);
			log.info("DTfield clicked");
			test.log(LogStatus.INFO, "DTfield clicked");
			
			WebElement D2_text = driver.findElement(By.xpath("//*[@id='ctable_0']/div/div[1]/div[1]/table/tr[2]/td[2]/select"));
			D2_text.sendKeys(d2);
			Util.delay(500);
			log.info("DT filled");
			test.log(LogStatus.INFO, "DT filled");
						
			textfieldLN2.click();
			Util.delay(500);
			log.info("Length field clicked");
			test.log(LogStatus.INFO, "Length field clicked");
			
			WebElement L2_text = driver.findElement(By.xpath("//*[@id='ctable_0']/div/div[1]/div[1]/table/tr[2]/td[3]/input"));
			L2_text.sendKeys(l2);
			Util.delay(500);
			log.info("Length filled");
			test.log(LogStatus.INFO, "Length filled");

			buttonSave.click();
			Util.delay(500);
			log.info("Save Button clicked");
			test.log(LogStatus.INFO, "Save Button clicked");
			
			TableVerifyTestDbDesigner(t,test);

		} 
		catch (Exception e) 
		{
			log.info("New Table Editor not available");
			throw(e);
		}
		return this;
	}
	
	public DbDesignerPage executeQuery (String q) throws Exception{
		try 
		{
			menuFile.click();
			Util.delay(500);
			log.info("File menu clicked");
			submenuNewEditor.click();
			Util.delay(500);
			log.info("Sub menu New Editor was clicked");
			testArea.click();
			Util.delay(500);
			log.info("Text Area was clicked");
			testArea.sendKeys(q);
			menuTest.click();
			Util.delay(500);
			log.info("Text Area was clicked");
			submenuExecute.click();
			Util.delay(500);
			log.info("Text Area was clicked");			
			String message = queryMessage.getText();
			if (message.contains("Query Executed Successfully."))
			{
				log.info("Query Executer is working fine");
			}
			else
			{
				log.info("Query Executer was not worked correctly");
				Util.closeAlert();	
			}
		} 
		catch (Exception e) 
		{
			log.info("New Editor not available or not clicked");
			throw(e);
		}
		return this;
	}


	public DbDesignerPage exportTableTestDbDesigner (String t, ExtentTest test) throws Exception{
		try {
			if(TableVerifyTestDbDesigner(t,test))
			{
				Actions build = new Actions(driver);
				build.contextClick(tableName).build().perform();
				rightclickmenuExport.click();
				Util.closeAlert();	
				log.info("Table Exported Successfully");
			}		
		} catch (Exception e) {
			log.info("Table was not Exported Successfully");
			throw(e);
		}
		return this;
	}
	
	public HomePage closeDbDesigner() throws Exception{
		try {
			driver.close();
			Util.switchToParentTab();
			log.info("DB Designer window closed");
		} catch (Exception e) {
			log.info("DB Designer window not closed");
			throw(e);
		}
		return new HomePage();
	}


	public DbDesignerPage alterTableTestDbDesigner(String t, String c3, String d3, String l3, ExtentTest test) throws Exception{
		try
		{
			if(TableVerifyTestDbDesigner(t, test))
			{
				Actions build = new Actions(driver);
				build.contextClick(tableName).build().perform();
				rightclickmenuAlter.click();
				Util.delay(500);
				
				buttonAlterColumn.click();
				Util.delay(500);
				log.info("New Column Added");
				
				textfieldC3.click();
				Util.delay(500);
				log.info("Textfield clicked");
				
				WebElement C3_text = driver.findElement(By.xpath("//*[@id='cATBLA']/div/div[1]/div[1]/table/tr[3]/td[1]/input"));
				C3_text.sendKeys(c3);
				Util.delay(500);
				log.info("C3 filled");
				
				textfieldDT3.click();
				Util.delay(500);
				log.info("DT3 field clicked");
				
				WebElement D3_text = driver.findElement(By.xpath("//*[@id='cATBLA']/div/div[1]/div[1]/table/tr[3]/td[2]/select"));
				D3_text.sendKeys(d3);
				Util.delay(500);
				log.info("DT3 filled");
							
				textfieldLN3.click();
				Util.delay(500);
				log.info("L3 clicked");
				
				WebElement L3_text = driver.findElement(By.xpath("//*[@id='cATBLA']/div/div[1]/div[1]/table/tr[3]/td[3]/input"));
				L3_text.sendKeys(l3);
				Util.delay(500);
				log.info("L3 filled");
				
				buttonAlterSave.click();
				Util.delay(500);
				log.info("Save Button clicked");	
				
				if (AlterVerifyTestDbDesigner(t,c3,d3,l3))
				{
					log.info("Table altered Successfully");
				}
				else
				{
					log.info("Table was not altered Successfully");
				}
			}
			else{
				log.info("Table was not altered Successfully");
			}
		}catch (Exception e) {
			log.info("Table was not altered");
			throw(e);
		}
		return this;
	}	
}
