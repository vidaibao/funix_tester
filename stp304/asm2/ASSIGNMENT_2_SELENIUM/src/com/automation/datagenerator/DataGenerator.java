package com.automation.datagenerator;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataGenerator {
	
	@DataProvider(name="Static") // can name whatever     
	public static Object[][] testDataGenerator() {
		Object[][] data = {{"Uname01@abc.com", "passw01"},{"test02@company.com", "test2222"},{"Uname03", "passw03"}};
		return data;
	}
	@DataProvider(name="Excel") // can name whatever
	public static Object[][] testDataGeneratorXls(Method met) throws IOException {
		
		if (met.getName().equalsIgnoreCase("tc_001_login_functionality")) 
			System.out.println("Use 1 name data provider for multiple testcases....return data;....");
		FileInputStream file = new FileInputStream("./data/assignment2_data_test.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheetAt(0); //wb.getSheet("Login");
        Iterator<Row> iterator = sheet.iterator();
        int numberOfData = sheet.getPhysicalNumberOfRows();
        Object[][] data = new Object[numberOfData][2];
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            
            data[currentRow.getRowNum()][0] = currentRow.getCell(0).getStringCellValue();
            data[currentRow.getRowNum()][1] = currentRow.getCell(1).getStringCellValue();
            
        }
        
		/*
		 * for (int i = 0; i < numberOfData; i++) { Row row = sheet.getRow(i); Cell
		 * userName = row.getCell(0); Cell pass = row.getCell(1); data[i][0] =
		 * userName.getStringCellValue(); data[i][1] = pass.getStringCellValue(); }
		 */
        
        
        wb.close();
		
		return data;
	}
}
