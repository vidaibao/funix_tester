package com.automation.base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.automation.utils.PropertiesFileUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverInstance {
	public WebDriver driver;

	@BeforeMethod
	public void setUpBrowser() throws InterruptedException, IOException {

		final long DELAY_PAGELOAD_TIMEOUT = Long.parseLong(PropertiesFileUtils.configReader("DELAY_PAGELOAD_TIMEOUT"));
		final long DELAY_POPUP_SECOND = Long.parseLong(PropertiesFileUtils.configReader("DELAY_POPUP_SECOND"));
		
		if (PropertiesFileUtils.configReader("BrowserName").equalsIgnoreCase("chrome")) {
//			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			
			
			WebDriverManager.chromedriver().setup();
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--incognito"); // mở Chrome ở chế độ ẩn danh
			driver = new ChromeDriver(opt);
		} else if (PropertiesFileUtils.configReader("BrowserName").equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions opt = new EdgeOptions();
			opt.addArguments("--incognito");
			driver = new EdgeDriver(opt);
		} else if (PropertiesFileUtils.configReader("BrowserName").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions opt = new FirefoxOptions();
			opt.addArguments("-private");
			driver = new FirefoxDriver(opt);
		}

		driver.get(PropertiesFileUtils.configReader("base_URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(DELAY_PAGELOAD_TIMEOUT)); //
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DELAY_POPUP_SECOND)); // popup cac loai hien len
																							// roi catch element

	}

	@AfterMethod
	public void cleanObjects() throws InterruptedException {
		driver.quit();
	}
}
