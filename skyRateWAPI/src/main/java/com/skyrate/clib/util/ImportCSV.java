package com.skyrate.clib.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ImportCSV {
	public static void main(String[] args) {
					
					PreparedStatement ps=null;
					BufferedReader br=null;

					 try{
						  
						 Connection connection = DBConnection.getConnection(); 
						    br=new BufferedReader(new FileReader("/C:/Users/lakshmanan/Downloads/ASA.csv"));
						    String sql = "INSERT INTO `business` (`ID`,`NAME`,`CITY`,`STATE`,`COUNTRY`,`EXPIRATION_DATE`,`BUSINESS_TYPE`)  VALUES(?,?,?,?,?,?,2)";
			                
						    ps=connection.prepareStatement(sql);
						    String line=null;
						    int i=20000;
						    while((line=br.readLine())!=null){
						    
						    	String[] value=line.split(",");
						    	
						    	System.out.println(i++ +":len:"+value.length+"-Loading.."+ line+value[3]);
						    	
						    	
						    	ps.setLong(1, i);
						    	ps.setString(2,  value[0]);
						    	ps.setString(3, value[1]);
						    	ps.setString(4,  value[2]);
						    	ps.setString(5, value[3]);
						    	ps.setString(6, value[4]);

						    	ps.executeUpdate();
						    	i++;

							 }
						    
							    br.close();
							    ps.close();
							    connection.close();
//						  FileInputStream input = new FileInputStream("C:/Users/lakshmanan/Pictures/Airframe.xlsx");
//				            POIFSFileSystem fs = new POIFSFileSystem( input );
				/*		 OPCPackage pkg = OPCPackage.open("C:/Users/lakshmanan/Downloads/ASA.xls");
				            XSSFWorkbook wb = new XSSFWorkbook(pkg);
				            XSSFSheet sheet = wb.getSheetAt(0);
				            Row row;
				            for(int i=1; i<=sheet.getLastRowNum(); i++){
				                row = sheet.getRow(i);*/
//				                System.out.println("1 "+row.getCell(1).getStringCellValue()+" 2 "+row.getCell(2).getStringCellValue()+" 3. "+row.getCell(3).getStringCellValue()+".4."+row.getCell(4).getStringCellValue()+",5,"+row.getCell(5).getStringCellValue());
//				                System.out.println("1 "+row.getCell(6)+" 2 "+row.getCell(7).getStringCellValue()+" 3. "+row.getCell(8).getStringCellValue()+".4."+row.getCell(9).getStringCellValue()+",5,");
//				                String name = row.getCell(0).getStringCellValue();
//				                String address = row.getCell(1).getStringCellValue();
//				                String address2 = row.getCell(2).getStringCellValue();
//				                String address3 = row.getCell(3).getStringCellValue();
//				                String city = row.getCell(4).getStringCellValue();
//				                String state = row.getCell(5).getStringCellValue();
//				                String zipcode = String.valueOf(row.getCell(6));
//				                String designator = row.getCell(7).getStringCellValue();
//				                String certifiacteOffice = row.getCell(8).getStringCellValue();
//				                String certificateNo = row.getCell(9).getStringCellValue();
				               /* String sql = "INSERT INTO `business_sample` (`NAME`,`CITY`,`STATE`,`COUNTRY`,`EXPIRATION_DATE`)  VALUES('"+ row.getCell(0)+"','"+ row.getCell(1)+"','"+ row.getCell(2)+"','"+ row.getCell(3)+"','"+ row.getCell(4)+"')";
				                ps = (PreparedStatement) connection.prepareStatement(sql);
				                ps.execute();
				                System.out.println("Import rows "+i);
				            }
				            connection.commit();
				            ps.close();
				            connection.close();
				            pkg.close();
				            System.out.println("Success import excel to mysql table");
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }*/
				}
					 catch(Exception e){
						 e.printStackTrace();
					 }
	}
}
