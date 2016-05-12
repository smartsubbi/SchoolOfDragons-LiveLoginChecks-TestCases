package com.SchoolOfDragons.LiveLoginChecks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Factory.BrowserFactory;
import Pages.AfterLoggedInPage;
import Pages.CommonHeader;
import Pages.LoginPage;
import Pages.NotAuthorisedPopUp;
import Utility.CaptureScreenshot;

public class TestCase3 
{
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;	
	static String testCase3Result = "No result";
	
	@BeforeClass
	public void setUp() throws Throwable
	{		
		report=ExtentManager.Instance();
	}
	
	@Parameters(value="Category")
	@Test	
	public void ValidNonAuthorisedParentLogin(String catg) throws Throwable
	{
		logger = report.startTest("Test Case 3: Live - Age 12 Player (Non Authorized User) Login to School of Dragons Live ","This will verify if a non Autorized user with age 12 can login with valid credentials");		
		driver = BrowserFactory.getBrowser("firefox");
		logger.log(LogStatus.INFO, "Browser is up and running");
		String browserOpenedScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "Application"));
		logger.log(LogStatus.INFO, browserOpenedScreenshot);		
		driver.get("http://www.schoolofdragons.com");
		logger.log(LogStatus.INFO, "Url is Loading");		
		Thread.sleep(5000);				
		CommonHeader header = PageFactory.initElements(driver, CommonHeader.class);		
		String homePageScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "Application"));
		logger.log(LogStatus.INFO, homePageScreenshot);		
		header.clickHeaderLoginLink();
		logger.log(LogStatus.INFO, "Clicked the Login Link on the Homepage header");		
		Thread.sleep(5000);		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);		
		String loginPageScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "Application"));
		logger.log(LogStatus.INFO, loginPageScreenshot);		
		loginPage.userNameType("SubbuParentNA");
		logger.log(LogStatus.INFO, "Entered username : SubbuParentNA");
		loginPage.passwordType("123456");
		logger.log(LogStatus.INFO, "Entered password : 123456");
		String afterEnteringUsernameAndPassword=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "Application"));
		logger.log(LogStatus.INFO, afterEnteringUsernameAndPassword);
		loginPage.playNowButtonClick();
		logger.log(LogStatus.INFO, "Clicked on the Play Now button after entering Username and Password");		
		Thread.sleep(5000);		
		NotAuthorisedPopUp notAuthorisedPopUpJava = PageFactory.initElements(driver, NotAuthorisedPopUp.class);
		notAuthorisedPopUpJava.authorizedPopUpValidation();
		logger.log(LogStatus.INFO, "Authorized Pop Up is displayed");
		String notAuthorisedPopUpJavaScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "Application"));
		logger.log(LogStatus.INFO, notAuthorisedPopUpJavaScreenshot);
		notAuthorisedPopUpJava.clickUnAuthorizedPopUpOkButton();
		logger.log(LogStatus.INFO, "Clicked on the non Authorized Pop Up Ok button");		
		Thread.sleep(5000);		
		AfterLoggedInPage afterLoggedInPage = PageFactory.initElements(driver, AfterLoggedInPage.class);     			
		afterLoggedInPage.currentlyLoggedInText("SubbuParentNA").isDisplayed();
		afterLoggedInPage.afterLoggedInSuccessfully();
		logger.log(LogStatus.INFO, "After Logged in Page is verified successfully");
		String afterLoggedinPageScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver, "Application"));
		logger.log(LogStatus.INFO, afterLoggedinPageScreenshot);			
		Thread.sleep(5000);		
		BrowserFactory.closeBrowser();
		logger.log(LogStatus.INFO, "Quitting the Browser Opened");		
	}
	
	@AfterMethod
	public void afterTest(ITestResult result) throws Throwable
	{
		if(result.getStatus()==ITestResult.FAILURE)	
		{		
			logger.log(LogStatus.FAIL, "<pre>" + result.getThrowable().getMessage() + "</pre>");
			String failureScreenshot=logger.addScreenCapture(CaptureScreenshot.takeScreenshot(driver,result.getName()));			  
			logger.log(LogStatus.FAIL, failureScreenshot);	
			testCase3Result = "Fail";
		}	
		testCase3Result = "Pass";
	}
	
	@AfterClass
	public void tearDown()
	{
		report.endTest(logger);
		report.flush();	
		BrowserFactory.closeBrowser();
		report.close();				
	}
}
