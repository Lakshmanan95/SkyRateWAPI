package com.skyrate.clib.util;


import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class BankingApplication {
	public static void main(String[] args) 
	{
		try
		{
		File Excelfile = new File("C:/Users/lakshmanan/Downloads/Skyrate-Defects_Check.xlsx");
		FileInputStream fis = new FileInputStream(Excelfile);
	
		//workbook creation
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		//getting sheet
		XSSFSheet sh = wb.getSheetAt(0);
		Iterator<Row> rowIt = sh.iterator();
		while(rowIt.hasNext())
		{
			Row row = rowIt.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext())
			{
				Cell cell = cellIterator.next();
				switch(cell.getCellType())
				{
				case Cell.CELL_TYPE_BOOLEAN:
					System.out.print(cell.getBooleanCellValue() + "\t\t");
					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue() + "\t\t");
                    break;
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue() + "\t\t");
					break;
				}
				
			}
			System.out.println("");
		}
		wb.close();
		fis.close();
//		FileOutputStream fos = new FileOutputStream(Excelfile);
//		wb.write(fos);
//		fos.close();
	}
catch (Exception e)
		{
	e.printStackTrace();
		}
	}

}
