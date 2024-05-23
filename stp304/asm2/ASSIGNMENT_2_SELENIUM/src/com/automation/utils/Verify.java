package com.automation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Verify {
	
	public static boolean validateElementVisible(WebDriver driver, String locatorType, String locatorValue) {
	    By locator = getBy(locatorType, locatorValue);
	    WebElement element = null;
	    try {
	        element = driver.findElement(locator);
	        return element.isDisplayed();
	    } catch (NoSuchElementException e) {
	        System.out.println("Element " + locatorType + ": " + locatorValue + " not found");
	    } catch (Exception e) {
	        System.out.println("Exception occurred while checking for element visibility " + locatorType + ": " + locatorValue);
	        e.printStackTrace();
	    }
	    return false;
	}

	
	public static boolean validateAttribute(WebDriver driver, String locatorType, String locatorValue, String attributeName, String expectedValue) {
	    boolean isAttributeValid = false;
	    WebElement element = null;
	    By locator = null;
	    try {
	        locator = getBy(locatorType, locatorValue);
	        element = driver.findElement(locator);
	        String actualValue = element.getAttribute(attributeName);
	        if (actualValue != null && actualValue.equals(expectedValue)) {
	            isAttributeValid = true;
	        }
	    } catch (NoSuchElementException e) {
	        System.out.println("Element " + locatorType + ": " + locatorValue + " not found");
	    } catch (Exception e) {
	        System.out.println("Exception occurred while validating attribute " + attributeName + " on element " + locatorType + ": " + locatorValue);
	        e.printStackTrace();
	    }
	    return isAttributeValid;
	}

	
	public static boolean validateTextOnPage(WebDriver driver, String expText) {
		return driver.getPageSource().contains(expText);
	}
	
	public static boolean validateTextPresentOnElement(WebDriver driver, String locatorType, String locatorValue, String expText) {
	    boolean isTextPresent = false;
	    WebElement element = null;
	    By locator = null;
	    try {
	        locator = getBy(locatorType, locatorValue);
	        element = driver.findElement(locator);
	        String actText = element.getText().trim();
	        isTextPresent = actText.contains(expText);
	        if (isTextPresent) {
	            System.out.println("Text '" + expText + "' found on element " + locatorType + ": " + locatorValue);
	        } else {
	            System.out.println("Text '" + expText + "' not found on element " + locatorType + ": " + locatorValue);
	        }
	    } catch (NoSuchElementException e) {
	        System.out.println("Element " + locatorType + ": " + locatorValue + " not found");
	    } catch (Exception e) {
	        System.out.println("Exception occurred while checking for text on element " + locatorType + ": " + locatorValue);
	        e.printStackTrace();
	    }
	    return isTextPresent;
	}

	
	public static String validateTextPresentOnElement(WebDriver driver, String locatorType, String locatorValue) {
	    String actualText = "";
	    WebElement element = null;
	    By locator = null;
	    try {
	        locator = getBy(locatorType, locatorValue);
	        element = driver.findElement(locator);
	        actualText = element.getText().trim();
	    } catch (NoSuchElementException e) {
	        System.out.println("Element " + locatorType + ": " + locatorValue + " not found");
	    } catch (Exception e) {
	        System.out.println("Exception occurred while checking for text on element " + locatorType + ": " + locatorValue);
	        e.printStackTrace();
	    }
	    return actualText;
	}

	private static By getBy(String locatorType, String locatorValue) {
	    By locator = null;
	    switch (locatorType.toUpperCase()) {
	        case "ID":
	            locator = By.id(locatorValue);
	            break;
	        case "NAME":
	            locator = By.name(locatorValue);
	            break;
	        case "CLASSNAME":
	        case "CLASS":
	            locator = By.className(locatorValue);
	            break;
	        case "TAGNAME":
	        case "TAG":
	            locator = By.tagName(locatorValue);
	            break;
	        case "LINKTEXT":
	        case "LINK":
	            locator = By.linkText(locatorValue);
	            break;
	        case "PARTIALLINKTEXT":
	        case "PARTIALLINK":
	            locator = By.partialLinkText(locatorValue);
	            break;
	        case "CSSSELECTOR":
	        case "CSS":
	            locator = By.cssSelector(locatorValue);
	            break;
	        case "XPATH":
	            locator = By.xpath(locatorValue);
	            break;
	        default:
	            System.out.println("Invalid locator type: " + locatorType);
	    }
	    return locator;
	}

	
	public static boolean validateURL(WebDriver driver, String expectedURL) {
		return driver.getCurrentUrl().equalsIgnoreCase(expectedURL);
	}
	
	public static boolean validateTitle(WebDriver driver, String expTitle) {
		return driver.getTitle().equalsIgnoreCase(expTitle);
	}
	
	public static boolean checkElementExists(WebDriver driver, String locatorType, String locatorValue) {
	    By locator = null;
	    switch (locatorType.toLowerCase()) {
	        case "id":
	            locator = By.id(locatorValue);
	            break;
	        case "name":
	            locator = By.name(locatorValue);
	            break;
	        case "class":
	        case "classname":
	            locator = By.className(locatorValue);
	            break;
	        case "tag":
	        case "tagname":
	            locator = By.tagName(locatorValue);
	            break;
	        case "link":
	        case "linktext":
	            locator = By.linkText(locatorValue);
	            break;
	        case "partiallink":
	        case "partiallinktext":
	            locator = By.partialLinkText(locatorValue);
	            break;
	        case "css":
	        case "cssselector":
	            locator = By.cssSelector(locatorValue);
	            break;
	        case "xpath":
	            locator = By.xpath(locatorValue);
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid locator type: " + locatorType);
	    }
	    return !driver.findElements(locator).isEmpty();
	}

}
