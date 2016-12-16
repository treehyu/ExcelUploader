import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
					
					//3번 셀이 한글일 경우
					if(i+1==3 && (rowContent.getCell(i+1).getStringCellValue().charAt(0)>='가' && rowContent.getCell(i+1).getStringCellValue().charAt(0)<='힣'))
					{
						arr[2]=rowContent.getCell(4).getStringCellValue();
						arr[3]=rowContent.getCell(3).getStringCellValue();
						
						i=3;
					}
					
					//10번 셀이 한글일 경우
					if(i+1==10 && (rowContent.getCell(i+1).getStringCellValue().charAt(0)>='가' && rowContent.getCell(i+1).getStringCellValue().charAt(0)<='힣'))
					{
						arr[9]=rowContent.getCell(11).getStringCellValue();
						arr[10]=rowContent.getCell(10).getStringCellValue();
						
						i=10;
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
		
		
		for(int row=2; row<sheet.getPhysicalNumberOfRows(); row++) //세번째줄부터 읽어온다.
		{
			XSSFRow rowContent=sheet.getRow(row);
			
			//빈 열 걸러내기
			if(rowContent==null)
				continue;
				
			//내용이 없는 열 걸러내기
			if(getCellData(rowContent.getCell(1))=="" && getCellData(rowContent.getCell(9))=="")
				continue;
			
			String[] arr=new String[17];
				
			for(int i=0; i<arr.length; i++)
			{
				if(rowContent.getCell(i+1)==null)
					continue;
				
				//빈 셀 걸러내기
				if(getCellData(rowContent.getCell(i+1))=="")
					continue;
				
				
				//========================================================템플릿 맞추면 지울 부분
				//3번 셀 한글일 경우(첫글자,막글자 체크)
				if(i==2 && getCellData(rowContent.getCell(i+1)).length()>0)
				{
					char first=getCellData(rowContent.getCell(i+1)).charAt(0);
					char last=getCellData(rowContent.getCell(i+1)).charAt(getCellData(rowContent.getCell(i+1)).length()-1);
					
					if((first>='가' && first<='힣') || (last>='가' && last<='힣'))
					{
						arr[2]=getCellData(rowContent.getCell(4));
						arr[3]=getCellData(rowContent.getCell(3));
						
						i=3;
						continue;
					}
				}
					
				//10번 셀 한글일 경우(첫글자, 막글자 확인)
				if(i==9 && getCellData(rowContent.getCell(i+1)).length()>0)
				{
					char first=getCellData(rowContent.getCell(i+1)).charAt(0);
					char last=getCellData(rowContent.getCell(i+1)).charAt(getCellData(rowContent.getCell(i+1)).length()-1);
					
					if((first>='가' && first<='힣') || (last>='가' && last<='힣'))
					{
						arr[9]=getCellData(rowContent.getCell(11));
						arr[10]=getCellData(rowContent.getCell(10));
						
						i=10;
						continue;
					}
					
				}
				//====================================================================
					
					arr[i]=getCellData(rowContent.getCell(i+1));
			}
				
				result.add(arr);
		}
		
		if(workbook!=null)
			workbook.close();
		
		return result;
	}
	
	
	public String getCellData(XSSFCell cell)
	{
		if(cell==null)
			return "";
		
		CellType type=cell.getCellTypeEnum();
		
		if(type==CellType.STRING)
			return cell.getStringCellValue();
		
		if(type==CellType.NUMERIC)
			return cell.getNumericCellValue()+"";
		
		if(type==CellType.BOOLEAN)
			return cell.getBooleanCellValue()+"";
		
		if(type==CellType.ERROR)
			return "error";
		

		return "";
	}
	
	
	
	

}



















