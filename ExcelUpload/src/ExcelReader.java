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
	
	public List<ExcelVO> getVOData(String filePath) throws IOException
	{
		List<ExcelVO> result=null;
		
		//임시파일 거르기
		if(filePath.contains("~$"))
			return result;
		
		FileInputStream fileInput=new FileInputStream(filePath);
		String format=filePath.substring(filePath.lastIndexOf(".")+1);
		
		if(format.equalsIgnoreCase("xls"))
			result=getXlsVOList(fileInput);
		
		if(format.equalsIgnoreCase("xlsx"))
			result=getXlsxVOList(fileInput);
		
		return result;
	}
	
	public List<String[]> getArrData(String filePath) throws IOException
	{
		List<String[]> result=null;
		
		//임시파일 거르기
		if(filePath.contains("~$"))
			return result;
		
		FileInputStream fileInput=new FileInputStream(filePath);
		String format=filePath.substring(filePath.lastIndexOf(".")+1);
		
		if(format.equalsIgnoreCase("xls"))
			result=getXlsArrList(fileInput);
		
		if(format.equalsIgnoreCase("xlsx"))
			result=getXlsxArrList(fileInput);
		
		return result;
	}
	
	public List<ExcelVO> getXlsVOList(FileInputStream file) throws IOException
	{
		List<ExcelVO> result=new ArrayList<ExcelVO>();
		
		HSSFWorkbook workbook=new HSSFWorkbook(file);
		HSSFSheet sheet=workbook.getSheetAt(workbook.getNumberOfSheets()-1); //마지막 시트를 가져온다
		
		//세번째줄부터 읽어온다.
		loop:
		for(int row=2; row<sheet.getPhysicalNumberOfRows(); row++)
		{
			HSSFRow rowContent=sheet.getRow(row);
				
			if(rowContent==null)
			{
				System.out.println("Null Pointer!! 빈 열입니다: "+(row+1)+"row");
				continue loop;
			}
			
			for(int i=1; i<18; i++)
			{
				if(rowContent.getCell(i)==null)
				{
					System.out.println("Null Pointer!! 빈 셀입니다: "+(row+1)+"row, "+(i+1)+"cell - 해당 row를 건너뜁니다.");
					continue loop;
				}
			}
				
			if(rowContent.getCell(1).getStringCellValue()!="")
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
	
	public List<ExcelVO> getXlsxVOList(FileInputStream file) throws IOException
	{
		List<ExcelVO> result=new ArrayList<ExcelVO>();
		
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheetAt(workbook.getNumberOfSheets()-1); //마지막 시트를 가져온다
		
		//세번째줄부터 읽어온다.
		loop:
		for(int row=2; row<sheet.getPhysicalNumberOfRows(); row++)
		{
			XSSFRow rowContent=sheet.getRow(row);
			
			if(rowContent==null)
			{
				System.out.println("Null Pointer!! 빈 열입니다: "+(row+1)+"row");
				continue loop;
			}
			
			for(int i=1; i<18; i++)
			{
				if(rowContent.getCell(i)==null)
				{
					System.out.println("Null Pointer!! 빈 셀입니다: "+(row+1)+"row, "+(i+1)+"cell - 해당 row를 건너뜁니다.");
					continue loop;
				}
			}
			
			if(rowContent.getCell(1).getStringCellValue()!="")
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
		
		if(workbook!=null)
			workbook.close();
		
		return result;
	}
	
	
	// 2016.12.15 vo 사용하지 않는 메소드 생성
	public List<String[]> getXlsArrList(FileInputStream file) throws IOException
	{
		List<String[]> result=new ArrayList<String[]>();
		
		HSSFWorkbook workbook=new HSSFWorkbook(file);
		HSSFSheet sheet=workbook.getSheetAt(workbook.getNumberOfSheets()-1); //마지막 시트를 가져온다
		
		//세번째줄부터 읽어온다.
		for(int row=2; row<sheet.getPhysicalNumberOfRows(); row++)
		{
			HSSFRow rowContent=sheet.getRow(row);
			
			//빈 열 걸러내기
			if(rowContent==null)
			{
				System.out.println("Null Pointer!! 빈 열입니다: "+(row+1)+"row");
				continue;
			}
				
			//내용이 없는 열 걸러내기
			if(rowContent.getCell(1).getStringCellValue()!="")
			{
				String[] arr=new String[17];
				
				for(int i=0; i<arr.length; i++)
				{
					//빈 셀 걸러내기
					if(rowContent.getCell(i+1)==null)
					{
						System.out.println("Null Pointer!! 빈 셀입니다: "+(row+1)+"row, "+(i+1)+"cell - 해당 row를 건너뜁니다.");
						continue;
					}
					
					arr[i]=rowContent.getCell(i+1).getStringCellValue();
				}
				
				result.add(arr);
			}
		}
		
		workbook.close();
		
		return result;
	}
	
	public List<String[]> getXlsxArrList(FileInputStream file) throws IOException
	{
		List<String[]> result=new ArrayList<String[]>();
		
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheetAt(workbook.getNumberOfSheets()-1); //마지막 시트를 가져온다
		
		//세번째줄부터 읽어온다.
		for(int row=2; row<sheet.getPhysicalNumberOfRows(); row++)
		{
			XSSFRow rowContent=sheet.getRow(row);
			
			//빈 열 걸러내기
			if(rowContent==null)
			{
				System.out.println("Null Pointer!! 빈 열입니다: "+(row+1)+"row");
				continue;
			}
				
			//내용이 없는 열 걸러내기
			if(rowContent.getCell(1).getStringCellValue()!="")
			{
				String[] arr=new String[17];
				
				for(int i=0; i<arr.length; i++)
				{
					//빈 셀 걸러내기
					if(rowContent.getCell(i+1)==null)
					{
						System.out.println("Null Pointer!! 빈 셀입니다: "+(row+1)+"row, "+(i+1)+"cell - 해당 row를 건너뜁니다.");
						continue;
					}
					
					arr[i]=rowContent.getCell(i+1).getStringCellValue();
				}
				
				result.add(arr);
			}
		}
		
		if(workbook!=null)
			workbook.close();
		
		return result;
	}

}
