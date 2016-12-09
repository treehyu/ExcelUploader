import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.List;

public class FileRefFunction {

	//파일단위
		public void excelToDB(String filepath) throws Exception
		{
			//파일 읽어오기
			ExcelReader reader=new ExcelReader();
			List<ExcelVO> voList=null;
			
			System.out.println("해당 경로의 엑셀 파일을 읽어옵니다: "+filepath);
			
			voList=reader.getData(filepath);
			
			
			//DB 액션
			ExcelDAO dao=new ExcelDAO();
			boolean dbResult=false;
			
			dao.deleteAll();
			dbResult=dao.insertAll(voList);
				
			if(!dbResult)
				System.out.println("DB insert fail!!");
			else
				System.out.println("DB insert success");

		}
		
		//폴더단위
		public void excelToDBFolder(String folderpath) throws Exception
		{
			//2-1. 파일리스트를 읽어온다.
			//2-2. 파일리스트의 파일에 이하 로직을 실행한다.
			//2-2-1. 파일 읽어오기
			//2-2-2. 읽어온 리스트가 null이 아니면 DB에 넣는다.
			
			File file=new File(folderpath);
			File[] fileList=file.listFiles();
			
			//DB내용 전체 삭제
			ExcelDAO dao=new ExcelDAO();
			dao.deleteAll();
				
			
			//파일리스트의 파일에 이하 로직을 실행한다.
			ExcelReader reader=new ExcelReader();
			
			System.out.println("이하경로의 파일을 읽어옵니다: ");
			for(int i=0; i<fileList.length; i++)
			{
				System.out.println("\t"+fileList[i].getPath());
				
				List<ExcelVO> voList=reader.getData(fileList[i].getPath());
					
				if(voList!=null)
					dao.insertAll(voList);
				
			}
			
			System.out.println("이하경로의 파일에 대한 DB insert를 모두 완료하였습니다: ");
			System.out.println("\t"+folderpath);
			
		}
		
		//읽어온 경로의 파일을 복사한다.
		public void readAndCopyFile(File readPath, File writePath) throws Exception
		{
			//write 경로 없으면 생성한다.
			if(!writePath.exists())
				writePath.mkdir();
			
			//write 경로의 파일을 삭제한다.
			File[] writeFileList=writePath.listFiles();
			
			for(int i=0; i<writeFileList.length; i++)
			{
				System.out.print("다음을 삭제합니다: ");
				System.out.print("\t"+writeFileList[i].getPath()+"\n");
				writeFileList[i].delete();
			}
			
			System.out.println("이하경로의 파일을 모두 삭제하였습니다: ");
			System.out.println("\t"+writePath.getPath());
			
			//read경로 못 찾으면 Exception 발생
			if(readPath.listFiles()==null)
				throw new Exception("read 경로를 찾지 못했습니다!");
			
			File[] readFileList=readPath.listFiles();
			
			FileChannel inputCh=null;
			FileChannel outputCh=null;
			
			//read경로의 파일을 한개씩 읽는다
			for(int i=0; i<readFileList.length; i++)
			{
				FileInputStream input=new FileInputStream(readFileList[i]);
				FileOutputStream output=new FileOutputStream(writePath+"\\"+readFileList[i].getName());
				
				inputCh=input.getChannel();
				outputCh=output.getChannel();
				
				long size=inputCh.size();
				
				inputCh.transferTo(0, size, outputCh);
				
				//스트림리더 자원반환
				if(input!=null)
					input.close();
				if(output!=null)
					output.close();
			}
			
			//채널 자원반환
			if(inputCh!=null)
				inputCh.close();
			if(outputCh!=null)
				outputCh.close();
			
			System.out.println("이하 경로의 파일을 모두 복사하였습니다: ");
			System.out.println("\t"+readPath.getPath());
		}
}
