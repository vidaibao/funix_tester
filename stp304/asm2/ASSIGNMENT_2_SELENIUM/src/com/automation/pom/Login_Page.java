package com.automation.pom;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.utils.PageActions;
import com.automation.utils.PropertiesFileUtils;



public class Login_Page {

	WebDriver driver = null;
	PageActions action;
	
	public Login_Page(WebDriver driver) {
		this.driver = driver;
		action = new PageActions(driver, 20);
		
	}
	
	

	public void enterLoginEmail() throws IOException {
		action.enterDataIntoTextbox(
				driver.findElement(By.xpath(PropertiesFileUtils.locatorReader("login_email_textbox_xpath"))),
				PropertiesFileUtils.configReader("email1"));	
	}

	public void enterLoginEmail(String email) throws IOException {
		action.enterDataIntoTextbox(
				driver.findElement(By.xpath(PropertiesFileUtils.locatorReader("login_email_textbox_xpath"))), email);	
	}

	public void enterPassword() throws IOException {
		action.enterDataIntoTextbox(
				driver.findElement(By.xpath(PropertiesFileUtils.locatorReader("login_password_textbox_xpath"))),
				PropertiesFileUtils.configReader("password1")); 
	}
	
	public void enterPassword(String pass) throws IOException {
		action.enterDataIntoTextbox(
				driver.findElement(By.xpath(PropertiesFileUtils.locatorReader("login_password_textbox_xpath"))), pass); 
	}
	
	public void clickLogin() throws IOException {
		action.clickButton(driver.findElement(By.xpath(PropertiesFileUtils.locatorReader("login_button_xpath"))));
	}
	

	

}
