import java.io.File;
import java.util.TimerTask;

public abstract class FileWatcher extends TimerTask {
	
	private long timeStamp;
	private File file;
	
	public FileWatcher(File file)
	{
		this.file=file;
		timeStamp=file.lastModified();
	}
	
	public final void run()
	{
		long time=file.lastModified();
		
		if(time!=timeStamp)
		{
			timeStamp=time;
			onChange();
		}
	}
	
	protected abstract void onChange();

}
