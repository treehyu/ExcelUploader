import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public List<ExcelVO> getData(String filePath) throws IOException
	{
		List<ExcelVO> result=null;
		
		//임시파일 거르기
		if(filePath.contains("~$"))
			return result;
		
		FileInputStream fileInput=new FileInputStream(filePath);
		String format=filePath.substring(filePath.lastIndexOf(".")+1);
		
		if(format.equalsIgnoreCase("xls"))
			result=getXls(fileInput);
		
		if(format.equalsIgnoreCase("xlsx"))
			result=getXlsx(fileInput);
		
		return result;
	}
	
	public List<ExcelVO> getXls(FileInputStream file) throws IOException
	{
		List<ExcelVO> result=new ArrayList<ExcelVO>();
		
		HSSFWorkbook workbook=new HSSFWorkbook(file);
		HSSFSheet sheet=workbook.getSheetAt(workbook.getNumberOfSheets()-1); //마지막 시트를 가져온다
		
		//세번째줄부터 읽어온다.
		for(int row=2; row<sheet.getPhysicalNumberOfRows(); row++)
		{
			HSSFRow rowContent=sheet.getRow(row);
			
			if(rowContent!=null && rowContent.getCell(1).getStringCellValue()!="")
			{
				ExcelVO vo=new ExcelVO();
				vo.setT_dbName(rowContent.getCell(1).getStringCellValue());
				vo.setT_topic(rowContent.getCell(2).getStringCellValue());
				vo.setT_tableName(rowContent.getCell(3).getStringCellValue());
				vo.setT_tableNameKo(rowContent.getCell(4).getStringCellValue());
				vo.setT_attrName(rowContent.getCell(5).getStringCellValue());
				vo.setT_colName(rowContent.getCell(6).getStringCellValue());
				vo.setT_dataType(rowContent.getCell(7).getStringCellValue());
				vo.setT_note(rowContent.getCell(8).getStringCellValue());
				vo.setS_dbName(rowContent.getCell(9).getStringCellValue());
				vo.setS_tableName(rowContent.getCell(10).getStringCellValue());
				vo.setS_tableNameKo(rowContent.getCell(11).getStringCellValue());
				vo.setS_attrName(rowContent.getCell(12).getStringCellValue());
				vo.setS_colName(rowContent.getCell(13).getStringCellValue());
				vo.setS_dataType(rowContent.getCell(14).getStringCellValue());
				vo.setS_note(rowContent.getCell(15).getStringCellValue());
				vo.setM_terms(rowContent.getCell(16).getStringCellValue());
				vo.setM_note(rowContent.getCell(17).getStringCellValue());
				
				result.add(vo);
			}
		}
		
		workbook.close();
		
		return result;
	}
	
	public List<ExcelVO> getXlsx(FileInputStream file) throws IOException
	{
		List<ExcelVO> result=new ArrayList<ExcelVO>();
		
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheetAt(workbook.getNumberOfSheets()-1); //마지막 시트를 가져온다
		
		//세번째줄부터 읽어온다.
		for(int row=2; row<sheet.getPhysicalNumberOfRows(); row++)
		{
			XSSFRow rowContent=sheet.getRow(row);
			
			if(rowContent!=null && rowContent.getCell(1).getStringCellValue()!="")
			{
				ExcelVO vo=new ExcelVO();
				vo.setT_dbName(rowContent.getCell(1).getStringCellValue());
				vo.setT_topic(rowContent.getCell(2).getStringCellValue());
				vo.setT_tableName(rowContent.getCell(3).getStringCellValue());
				vo.setT_tableNameKo(rowContent.getCell(4).getStringCellValue());
				vo.setT_attrName(rowContent.getCell(5).getStringCellValue());
				vo.setT_colName(rowContent.getCell(6).getStringCellValue());
				vo.setT_dataType(rowContent.getCell(7).getStringCellValue());
				vo.setT_note(rowContent.getCell(8).getStringCellValue());
				vo.setS_dbName(rowContent.getCell(9).getStringCellValue());
				vo.setS_tableName(rowContent.getCell(10).getStringCellValue());
				vo.setS_tableNameKo(rowContent.getCell(11).getStringCellValue());
				vo.setS_attrName(rowContent.getCell(12).getStringCellValue());
				vo.setS_colName(rowContent.getCell(13).getStringCellValue());
				vo.setS_dataType(rowContent.getCell(14).getStringCellValue());
				vo.setS_note(rowContent.getCell(15).getStringCellValue());
				vo.setM_terms(rowContent.getCell(16).getStringCellValue());
				vo.setM_note(rowContent.getCell(17).getStringCellValue());
				
				result.add(vo);
			}
		}
		
		workbook.close();
		
		return result;
	}

}
