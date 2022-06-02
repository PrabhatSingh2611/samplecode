package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XLUtils {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;

	/**
	 *
	 * @param xlfile  : Excel File path to read data
	 * @param xlsheet : Excel sheet name to read data
	 * @return Number of row having data in sheet
	 */
	public static int getRowCount(String xlfile, String xlsheet) {
		int rowCount = 0;
		try {
			fi = new FileInputStream(xlfile);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(xlsheet);
			rowCount = ws.getPhysicalNumberOfRows();
			wb.close();
			fi.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rowCount;
	}

	/**
	 *
	 * @param xlfile  : Excel File path to read data
	 * @param xlsheet : Excel sheet name to read data
	 * @param rownum  : Excel row number to read cell count
	 * @return Number of cell having data in row
	 */
	public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException {
		int cellcount = 0;
		try {
			fi = new FileInputStream(xlfile);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(xlsheet);
			row = ws.getRow(rownum);
			cellcount = row.getLastCellNum();
			wb.close();
			fi.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cellcount;
	}

	/**
	 *
	 * @param xlfile  : Excel File path to read data
	 * @param xlsheet : Excel sheet name to read data
	 * @param rownum  : Excel row number to read data
	 * @param colnum  : Excel column number to read data
	 * @return Data of particular cell
	 */
	public static String getCellData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		String data;
		try {
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		} catch (Exception e) {
			data = "";
		}
		wb.close();
		fi.close();
		return data;
	}

	/**
	 *
	 * @param xlfile  : Excel File path to write data
	 * @param xlsheet : Excel sheet name to write data
	 * @param rownum  : Excel row number to write data
	 * @param colnum  : Excel column number to write data
	 *
	 */
	public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data)
			throws IOException {
		try {
			fi = new FileInputStream(xlfile);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(xlsheet);
			row = ws.getRow(rownum);
			cell = row.createCell(colnum);
			cell.setCellValue(data);
			fo = new FileOutputStream(xlfile);
			wb.write(fo);
			wb.close();
			fi.close();
			fo.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 *
	 * @param xlfile  : Excel File path to read data
	 * @param xlsheet : Excel sheet name to read data 
	 * @return Data of sheet in Object[][] format 
	 *
	 */
	public static Object[][] getExcelSheetData(String xlfile, String xlsheet) throws Exception {		        
        Object[][] obj=null;
        DataFormatter formatter = new DataFormatter();
       
		try {

			fi = new FileInputStream(xlfile);
			// Access the required test data sheet
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(xlsheet);			
			int totalRows = ws.getPhysicalNumberOfRows();		//Number of row having data in sheet 
			row = ws.getRow(0);
			int totalCols = row.getLastCellNum();				//Number of cell having data in row
	        
	        obj = new Object[totalRows-1][1];
	         
	        for (int i = 0; i < totalRows-1; i++) {
	            Map<Object, Object> datamap = new HashMap<>();
	            for (int j = 0; j < totalCols; j++) {	            	
	              datamap.put(formatter.formatCellValue(ws.getRow(0).getCell(j)), formatter.formatCellValue(ws.getRow(i+1).getCell(j)));
	            }
	            obj[i][0] = datamap;
	          }
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  obj;
	}
	
	public static Map<String, Map<String, String>> getListData(String xlfile, String xlsheet) throws Exception {		
		int totalCols = 0;
		Map<String, Map<String, String>> completeSheetData = new HashMap<String, Map<String, String>>();
        List<String> columnHeader = new ArrayList<String>();
        DataFormatter formatter = new DataFormatter();
		try {

			fi = new FileInputStream(xlfile);
			// Access the required test data sheet
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheet(xlsheet);
			int totalRows = ws.getPhysicalNumberOfRows();
			row = ws.getRow(0);
			totalCols = row.getLastCellNum();

	        for(int i=0;i<totalCols;i++) {
	        	cell = row.getCell(i);
	        	formatter = new DataFormatter();	        	
	        	columnHeader.add(formatter.formatCellValue(cell));	            
	        }	        

	        for (int i = 1; i < totalRows; i++) {
	        	
	            Map<String, String> singleRowData = new HashMap<String, String>();
	            row = ws.getRow(i);
	            for (int j = 0; j < totalCols; j++) {
	            
	                Cell cell = row.getCell(j);
	                formatter = new DataFormatter();
	                singleRowData.put(columnHeader.get(j), formatter.formatCellValue(cell));
	            }
	            
	            completeSheetData.put(String.valueOf(i), singleRowData);
	        }
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return completeSheetData;
	}

}
