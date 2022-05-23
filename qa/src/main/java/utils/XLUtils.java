package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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

}
