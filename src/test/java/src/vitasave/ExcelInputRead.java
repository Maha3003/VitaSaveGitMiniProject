package src.vitasave;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelInputRead {

	public static String getLoginCredentials(int rowNum, int cellNum) throws IOException {
		// TODO Auto-generated method stub
		String result = null;
		File loc =new File("C:\\Users\\Maha v\\Desktop\\Excel\\ExcelInputRead.xlsx");
	    FileInputStream fs =new FileInputStream(loc);
	    Workbook wb=new XSSFWorkbook(fs);
	    Sheet s=wb.getSheet("Sheet1");
	    Row row = s.getRow(rowNum);
	    Cell cell = row.getCell(cellNum);
	    int cellType = cell.getCellType();
	    if(cellType== 1)
	    {
	    	 result = cell.getStringCellValue();
	    }
	    else if(cellType==0)
	    {
	    	if(DateUtil.isCellDateFormatted(cell))
	    	{
	    		Date dateCellValue = cell.getDateCellValue();
	    		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yy");
	    		 result = sf.format(dateCellValue);
	    	} else {
				double numericCellValue = cell.getNumericCellValue();
				long l=(long)numericCellValue;
						 result = String.valueOf(l);
			}
	    }
		return result;
	}

}
