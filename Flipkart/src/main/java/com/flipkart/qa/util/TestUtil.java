package com.flipkart.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.flipkart.qa.base.TestBase;
import com.opencsv.CSVWriter;

public class TestUtil extends TestBase{
	
	public static long PAGE_LOAD_TINEOUT = 10;
	public static long IMPLICIT_WAIT = 20;
	
//	public static String TESTDATA_SHEET_PATH = "//FreeCRM//src//main//java//com//crm//qa//testdata//testdata.xlsx";
	public static String TESTDATA_SHEET_PATH = "D:\\Automation\\FreeCRM\\src\\main\\java\\com\\crm\\qa\\testdata\\testdata.xlsx";
	
	static Workbook book;
	static Sheet sheet;
	
	
	
	public static Object[][] getTestData(String sheetName)
	{
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0;i<sheet.getLastRowNum();i++)
		{
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
			{
				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException
	{
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(srcFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

	public static void writeCSV(List<String[]> data) throws Exception
	{
		CSVWriter writer = new CSVWriter(new FileWriter("D://output.csv"));
		//Writing data to the csv file
		writer.writeAll(data);
		writer.flush();
		System.out.println("Data entered");
	}
	
	public static void sortDataByIndex(List<String[]> list, final int index)
	{
		Collections.sort(list,new Comparator<String[]>() {
			public int compare(String[] strings, String[] otherStrings) {
				return strings[index].compareTo(otherStrings[index]);
			}
		});
		for (String[] sa : list) {
			System.out.println(Arrays.toString(sa));
		}
	}
}
