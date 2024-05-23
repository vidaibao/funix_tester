package com.automation.pom;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.utils.PageActions;
import com.automation.utils.PropertiesFileUtils;



public class HomePage {
	
	WebDriver driver = null;
	PageActions action;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		action = new PageActions(driver, 20);
	}

	public void clickMenuAccount() throws IOException {
		action.clickButton(driver.findElement(By.id(PropertiesFileUtils.locatorReader("hp_menu_account_id"))));
		
	}

	public void clickLogoutButton() throws IOException {
		WebElement element = driver.findElement(By.xpath(PropertiesFileUtils.locatorReader("hp_logout_button_xpath")));
		waitExplicitElementClickable(element);
		action.clickButton(element);
	}

	public void clickLoginupPage() throws IOException {
		action.clickButton(driver.findElement(By.xpath(PropertiesFileUtils.locatorReader("hp_menu_signin_up_xpath"))));
		//waitExplicitElement();
	}
	
	private void waitExplicitElementClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

//	private void waitExplicitPopup(WebElement element) throws IOException {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//		wait.until(ExpectedConditions.invisibilityOf(element)));
//	}
}
