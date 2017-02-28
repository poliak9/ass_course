package ass.poliape2.dependencyinjection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class FileLogger implements Logger {

	protected String logFilePath = "bla.txt";
	
	protected File logFile; 
	protected PrintWriter pw;
	
	public void init() {
		try {
			pw = new PrintWriter(new FileOutputStream(logFilePath, true));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void log(String message) {
		if(pw == null) init();
		pw.println(message);
		pw.flush();
	}

}
