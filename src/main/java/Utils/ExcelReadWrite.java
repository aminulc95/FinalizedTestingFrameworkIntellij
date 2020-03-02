package Utils;


import jxl.Sheet;
import jxl.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class ExcelReadWrite {
	private static String file;
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;

	public ExcelReadWrite(String excelFileName) {
		file = excelFileName;
	}

	public ExcelReadWrite() {
	}

	public static void setExcelFile(String sheetName) {
		try {
			ExcelWBook = new XSSFWorkbook();
			ExcelWSheet = ExcelWBook.createSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getCellData(int RowNum, int ColNum){
		String cellData = null;
		try{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			cellData = Cell.getStringCellValue();
		}catch(Exception e){
			e.printStackTrace();
		}
		return cellData;
	}
	
	@SuppressWarnings("static-access")
	public static void setCellData(String data, int RowNum, int ColNum) {
		FileOutputStream fileOut = null;
		try {
			Row = ExcelWSheet.createRow(RowNum);
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(data);

				fileOut = new FileOutputStream(file);
				ExcelWBook.write(fileOut);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileOut.flush();
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String[][] getExcelData(String fileName, String sheetName) {
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);

			int totalCols = sh.getColumns();
			int totalRows = sh.getRows();

			arrayExcelData = new String[totalRows - 1][totalCols];
			for (int i = 1; i < totalRows; i++) {
				for (int j = 0; j < totalCols; j++) {
					arrayExcelData[i - 1][j] = sh.getCell(j, i).getContents();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}
}
