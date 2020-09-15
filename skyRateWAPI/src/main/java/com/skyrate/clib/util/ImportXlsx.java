package com.skyrate.clib.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.jdbc.PreparedStatement;

public class ImportXlsx {

	 public static void main(String[] args) throws Exception {

		 	String filePath = "C:/Users/lakshmanan/Downloads/AeroStar1.xls";
		 	String ext = FilenameUtils.getExtension(filePath);
	     
		 	try {
	            java.sql.Connection con = DBConnection.getConnection();
	            con.setAutoCommit(false);
	            PreparedStatement pstm = null;
	            File excelFile = new File(filePath);
	            FileInputStream fis = new FileInputStream(excelFile);
	            
 	           
	            if(ext.equals("xlsx")){

		            XSSFWorkbook workbook = new XSSFWorkbook(fis);
	 	            XSSFSheet sheet = workbook.getSheetAt(0);
	                Row row;
	 	            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	 	            	row = sheet.getRow(i);
	 	            	System.out.println(row.getCell(0));	            	
	 	            	String sql = "INSERT INTO parts_details (business_id, parts_number, parts_description) VALUES('1 ','"+row.getCell(0)+"','"+row.getCell(1)+"')";
	 	                pstm = (PreparedStatement) con.prepareStatement(sql);
	 	                pstm.execute();
	 	            }
	 	           workbook.close();
	            }
	            else{
	            	FileInputStream input = new FileInputStream(filePath);
	            	POIFSFileSystem fs = new POIFSFileSystem(input);
	 	            HSSFWorkbook wb = new HSSFWorkbook(fs);

	 	            HSSFSheet sheet1 = wb.getSheetAt(0);
	 	            Row row;
	 	            for (int i = 1; i <= sheet1.getLastRowNum(); i++) {
	 	            	row = sheet1.getRow(i);
	 	            	System.out.println(row.getCell(0));	            	
	 	            	String sql = "INSERT INTO parts_details (business_id, parts_number, parts_description) VALUES('1 ','"+row.getCell(0)+"','"+row.getCell(1)+"')";
	 	                pstm = (PreparedStatement) con.prepareStatement(sql);
	 	                pstm.execute();
	 	            }
	            }
	         
	            con.commit();
	            pstm.close();
	            con.close();
	            System.out.println("Success import excel to mysql table");
	           
	            fis.close();
	          
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
