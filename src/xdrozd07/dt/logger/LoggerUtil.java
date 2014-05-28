package xdrozd07.dt.logger;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerUtil {

	private static String outputFile = "./client.log";
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public static void log(Exception e){
		try {
			
			PrintWriter out = new PrintWriter(outputFile, "UTF-8");
			out.println(dateFormat.format(new Date())+": "+e.getMessage());
			out.close();
			
			e.printStackTrace();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
