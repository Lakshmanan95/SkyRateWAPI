package com.skyrate.clib.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class ImportXls  {
	
	 public static void main(String[] args) throws Exception {

	        try {
	            java.sql.Connection con = DBConnection.getConnection();
	            con.setAutoCommit(false);
	            PreparedStatement pstm = null;
	            FileInputStream input = new FileInputStream("C:/Users/lakshmanan/Downloads/AeroStar.xlsx");
	            POIFSFileSystem fs = new POIFSFileSystem(input);
	            HSSFWorkbook wb = new HSSFWorkbook(fs);

	            HSSFSheet sheet = wb.getSheetAt(0);
	            Row row;
	            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	            	row = sheet.getRow(i);
	            	System.out.println(row.getCell(0));	            	
	            	String sql = "INSERT INTO parts_details (business_id, parts_number, parts_description) VALUES('1 ','"+row.getCell(0)+"','"+row.getCell(1)+"')";
	                pstm = (PreparedStatement) con.prepareStatement(sql);
	                pstm.execute();
	            }
	            con.commit();
	            pstm.close();
	            con.close();
	            input.close();
	            System.out.println("Success import excel to mysql table");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
