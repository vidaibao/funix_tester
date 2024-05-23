package com.automation.utils;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageActions {
	WebDriver driver = null;
	long DELAY_PAGELOAD_SECOND;
	
	public PageActions(WebDriver driver, long waitTime) {
		this.driver = driver;
		DELAY_PAGELOAD_SECOND= waitTime;
	}
	
	public void clickButton( WebElement element) throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DELAY_PAGELOAD_SECOND));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
			CaptureSreenshot.takeSreenshot(driver, "clickButton_Error");
		}
		
	}
	
	public void enterDataIntoTextbox(WebElement element, String text) throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DELAY_PAGELOAD_SECOND));
			wait.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
			CaptureSreenshot.takeSreenshot(driver, "enterDataIntoTextbox_Error");
		}
		
	}
	
	public void selectVisibleInDropdown(WebElement element, String text) throws IOException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DELAY_PAGELOAD_SECOND));
			wait.until(ExpectedConditions.textToBePresentInElement(element, text));
			Select s = new Select(element);
			s.selectByVisibleText(text);
		} catch (Exception e) {
			e.printStackTrace();
			CaptureSreenshot.takeSreenshot(driver, "selectDropdown_Error");
		}
		
	}
}
