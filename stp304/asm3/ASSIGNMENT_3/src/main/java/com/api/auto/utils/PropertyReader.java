package com.api.auto.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PropertyReader {
	
	private static String CONFIG_PATH = "./ConfigurationFiles/configs.properties";
	private static String TOKEN_PATH = "./ConfigurationFiles/token.properties";
//	private static String JSON_PATH1 = "./RequestPayload/CreateWork.json";
			
	public static String getProperty(String key) throws IOException {
		File f = new File(CONFIG_PATH);
		FileReader fr = new FileReader(f);
		Properties prop = new Properties();
		prop.load(fr);
		String value = prop.getProperty(key);
	    if (value == null) {
	        throw new IllegalArgumentException("Key '" + key + "' not found in the properties file.");
	    }
	    return value;
	}
	
	
	public static String getJsonData(String json_path, String key) throws IOException {
		String value = null;
		try {
			// Create a File object for the JSON file
	        File bodyCreateWork = new File(getProperty(json_path));
			// Create an ObjectMapper object for parsing JSON data
	        ObjectMapper mapper = new ObjectMapper();
	        // Parse the JSON data from the file into a JsonNode object
	        JsonNode rootNode = mapper.readTree(bodyCreateWork);
	        // Get the value of the "nameWork" field from the JsonNode object
	        value = rootNode.get(key).asText();
	        if (value == null) {
		        throw new IllegalArgumentException("Key '" + key + "' value not found in the json file.");
		    }
		} catch (Exception e) {
			System.out.println("An error occurred while reading the json file.");
            throw e;
		}
		return value;
	}
	
	public static String getToken() throws IOException {
		
		try (BufferedReader reader = new BufferedReader(new FileReader(TOKEN_PATH))) {
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }

            return sb.toString();

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            throw e;
        }
	}
	
	
	public static void saveToken(String token) {
		
		try {
			System.out.println("Save token to file...");
			
			FileWriter writer = new FileWriter(TOKEN_PATH, StandardCharsets.UTF_8);
			
			writer.write(token); // writer.write(item + System.lineSeparator()); // xuống hàng tương thích các OS
			
			writer.close();
			System.out.println("Write token file successfully!!!");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
		}
		
	    
	}
}
