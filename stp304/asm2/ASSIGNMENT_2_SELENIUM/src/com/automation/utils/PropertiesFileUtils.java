package com.automation.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils {
	
	public static String configReader(String key) throws IOException {
		File f = new File("./configuration/configs.properties");
		FileReader fr = new FileReader(f);
		Properties prop = new Properties();
		prop.load(fr);
		String value = prop.getProperty(key);
	    if (value == null) {
	        throw new IllegalArgumentException("Key '" + key + "' not found in the properties file.");
	    }
	    return value;
	}
	
	public static String locatorReader(String key) throws IOException {
		File f = new File("./ElementLocator/Locator.properties");
		FileReader fr = new FileReader(f);
		Properties prop = new Properties();
		prop.load(fr);
		String value = prop.getProperty(key);
	    if (value == null) {
	        throw new IllegalArgumentException("Key '" + key + "' not found in the properties file.");
	    }
	    return value;

	}
}
