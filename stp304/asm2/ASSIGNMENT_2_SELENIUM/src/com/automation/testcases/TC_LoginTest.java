package com.automation.testcases;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.automation.base.DriverInstance;
import com.automation.datagenerator.DataGenerator;
import com.automation.pom.HomePage;
import com.automation.pom.Login_Page;
import com.automation.utils.CaptureSreenshot;
import com.automation.utils.PropertiesFileUtils;
import com.automation.utils.Verify;


public class TC_LoginTest extends DriverInstance {
	
//	@Test(dataProvider = "Static", dataProviderClass = DataGenerator.class)
	@Test(dataProvider = "Excel", dataProviderClass = DataGenerator.class)
	public void tc_001_Login_Test(String email, String pass) throws IOException, InterruptedException {
		// Verify logged status
		HomePage home = new HomePage(driver);
		if (Verify.checkElementExists(driver, "xpath" , PropertiesFileUtils.locatorReader("hp_logout_button_xpath")))
			home.clickLogoutButton();
		// Navigate to Login Page
		home.clickLoginupPage();

		final long DELAY_TIME_MILISECOND = Long.parseLong(PropertiesFileUtils.configReader("DELAY_TIME_MILISECOND"));
//		Thread.sleep(DELAY_TIME_MILISECOND);
		
		Login_Page login = new Login_Page(driver);
		login.enterLoginEmail(email);
		login.enterPassword(pass);
		login.clickLogin();
		Thread.sleep(DELAY_TIME_MILISECOND);
		
		home.clickLogoutButton();
//		Thread.sleep(DELAY_TIME_MILISECOND);
	}
	
	@AfterMethod
	public void takeScreenshot(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				String email = (String) result.getParameters()[0];
				System.out.println("Lỗi xảy ra screenshot " + email );
				int index = email.indexOf('@');
				String accountName = null;
				accountName = index > 0? email.substring(0, index): email;
				CaptureSreenshot.takeSreenshot(driver, accountName);

			} catch (Exception e) {
				System.out.println("Lỗi xảy ra screenshot " + e.getMessage());
			}
		}
	}
	
	/*
	 * <listeners>
		<listener class-name="org.uncommons.reportng.HTMLReporter" />
		<listener class-name="org.uncommons.reportng.JUnitXMLReporter" />
	</listeners>
	
	<class name="automation.datagenerator.DataGenerator" />
	 * */
}
