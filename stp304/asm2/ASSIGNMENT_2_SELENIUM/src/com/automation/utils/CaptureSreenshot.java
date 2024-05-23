package com.automation.utils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class CaptureSreenshot {

	static String filename = null;

	public static void takeSreenshot(WebDriver driver, String name) {
		try {
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("_yyyyMMdd_HHmmss");
			name += now.format(formatter);
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File f = screenshot.getScreenshotAs(OutputType.FILE);
			File fd = new File("./Screenshots/" + name + ".png");
			FileUtils.copyFile(f, fd);
		} catch (Exception e) {
			System.out.println("Unable to take screenshot, " + e.getMessage());
		}

		attatchSreenshotToReport();
	}

	public static void attatchSreenshotToReport() {
		try {
			System.setProperty("org.uncommons.reporting.escape-output", "false");
			File f = new File(filename);
			Reporter.log("ca title= \"Snapshot\" href=\"" + f.getAbsolutePath() + "\">"
					+ "<img width=\"418\" height=\"240\" alt=\"alternativeName\""
					+ " title=\"title\" src=\"../surefire-reports/html/screenShots/" + filename);

		} catch (Exception e) {
			System.out.println("Unable to take screenshot, " + e.getMessage());
		}
	}

}
