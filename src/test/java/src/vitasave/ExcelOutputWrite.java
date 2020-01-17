package src.vitasave;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOutputWrite {
public static void printItemDetailsFromExcel(String productName,String productPrice) throws IOException
{
	FileOutputStream fs=new FileOutputStream("C:\\Users\\Maha v\\Desktop\\Excel\\ExcelOutputWrite.xlsx");
	Workbook w=new XSSFWorkbook();
	Sheet s=w.createSheet("VitaSave");
	Row r = s.createRow(0);
	Cell productPriceCell = r.createCell(0);
	productPriceCell.setCellValue("product Name");
	Cell productCell = r.createCell(1);
	productCell.setCellValue("product Price");
	Row printRow = s.createRow(1);
	Cell printCell = printRow.createCell(0);
	printCell.setCellValue(productName);
	Cell createCell = printRow.createCell(1);
	createCell.setCellValue(productPrice);
	w.write(fs);
	
}
}
