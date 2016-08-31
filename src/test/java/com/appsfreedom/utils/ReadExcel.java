package com.appsfreedom.utils;

import java.util.Hashtable;

import org.apache.log4j.Logger;



public class ReadExcel {
	static Logger log = Logger.getLogger("devpinoyLogger");
	public static Object[][] getData(String testName, Excel xls){
		System.out.println("********************");
		// find row Num from which test starts
		// find total rows of test data in the test
		// find total columns in the test data
		// extract data for every row and put data in hashtables
		// put the hastables in object array
		
		
		// find row Num from which test starts
		int testStartRowNum=1;
		
		while(!xls.getCellData("TestData", 0, testStartRowNum).equals(testName)){
			testStartRowNum++;
			if(testStartRowNum > xls.getRowCount("TestData")){
				System.out.println("Test name not found");
				return null;
			}
				
		}
		//System.out.println("Test starts from "+ testStartRowNum);
		log.info("Test starts from "+ testStartRowNum);
		
		int testColStartRow = testStartRowNum+1; // row on which cols are present
		int testDataStartRow = testStartRowNum+2;// row on which data starts
		
		// find total rows of test data in the test
		int totalDataRows=0; //assuming
		while(!xls.getCellData("TestData", 0, (testDataStartRow+totalDataRows)).equals("")){
			totalDataRows++;
		}
		//System.out.println("Total rows ->" +totalDataRows);
		log.info("Total rows ->" +totalDataRows);
		
		// find total columns in the test data
		int totalCols=0;
		while(!xls.getCellData("TestData", totalCols, testColStartRow).equals("")){
			totalCols++;
		}
		//System.out.println("Total cols -> "+totalCols );
		log.info("Total cols -> "+totalCols);
		
		// extract data for every row and put data in hashtables
		
		Object data[][] = new Object[totalDataRows][1];
		int index=0;
		Hashtable<String,String> table = null;
		
		for(int rNum=testDataStartRow;rNum<(testDataStartRow+totalDataRows);rNum++){
			table = new Hashtable<String,String>();
			for(int cNum=0;cNum<totalCols;cNum++){
				String key = xls.getCellData("TestData", cNum, testColStartRow);
				String value = xls.getCellData("TestData", cNum, rNum);
				table.put(key, value);
				//System.out.print(xls.getCellData("Test Data", cNum, rNum)+" -- ");
			}
			//System.out.println();
			data[index][0]=table;
			index++;
			
			
		}
		
		
		
		return data;
	}
}