/*
 * 로직 순서
	1. Filewatcher 생성(파일 변경 감지 객체)
	2. ExcelToDBFolder 메소드 실행
		2-1. 파일리스트를 읽어온다.
		2-2. 파일리스트의 파일에 이하 로직을 실행한다.
			2-2-1. 파일 읽어오기
			2-2-2. 읽어온 리스트가 null이 아니면 DB에 넣는다.
 */
//16.12.07 변경 로직
	//변경 감지될때의 동작
	//1. readPath 변경을 감지한다.
	//2. 변경 감지 시 writePath로 파일을 복사한다.
	//3. 복사한 파일을 DB에 업로드한다.
import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ExcelUploader {

	private static String readPath="Z:\\DA모델링\\4.데이터이행\\컬럼매핑정의서";
	private static String writePath="C:\\컬럼매핑정의서";
	private static long timeStamp;
	
	public static void main(String[] args){
		
		File read=new File(readPath);
		File write=new File(writePath);
		
		timeStamp=read.lastModified();
		
		System.out.println("최초실행 timeStamp: "+timeStamp);
		
		ExcelUploader uploader=new ExcelUploader();
		
		Timer timer=new Timer();
		TimerTask task=new TimerTask() {
			
			@Override
			public void run() {
				
				long timeStampCk=read.lastModified();
				System.out.println("Task timeStamp: "+timeStampCk+" run!! ");
				
				if(timeStampCk!=0 && timeStamp!=timeStampCk) //커넥션 끊길 경우 timeStampCk가 0이므로 체크한다.
				{
					timeStamp=timeStampCk;
					uploader.readAndWriteToDB(read, write);
				}
				
			}
		};
		
		timer.scheduleAtFixedRate(task, new Date(), 10000); 
		// scheduleAtFixedRate(TimerTask task, long delay, long period)
	}
	
	//최종 액션 묶음
	public void readAndWriteToDB(File read, File write)
	{
		FileRefFunction function=new FileRefFunction();
		try{
			function.readAndCopyFile(read, write);
			function.excelToDBFolder(writePath);
			
		}catch(Exception e){
			System.out.println(new Date()+" Exception: "+e.getMessage());
		}
	}
	
	
	

}




















