package com.appsfreedom.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.relevantcodes.extentreports.LogStatus;

public class Listener extends Driver implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.startTest(result.getName()).assignCategory(result.getTestClass().toString().split("\\.")[3].replace("Test]", ""));
		test.log(LogStatus.INFO, "Test "+result.getName()+" started");
		MyLogger.log.info("Test "+result.getName()+" started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, "Test passed");
		MyLogger.log.info("Test "+result.getName()+" passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println(result.getThrowable().toString());
		String path = Util.getscreenshot(result.getName() + "_" + result.getThrowable().toString().split(":")[1]);
		test.log(LogStatus.FAIL, result.getThrowable().getMessage() + test.addScreenCapture(path));
		MyLogger.log.info("Test "+result.getName()+" failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		MyLogger.log.info("Test "+result.getName()+" skipped");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		MyLogger.log.info("Calling onStart method");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		MyLogger.log.info("Calling onFinish method");
		
	}

}
