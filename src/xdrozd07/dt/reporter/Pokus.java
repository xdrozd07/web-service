package xdrozd07.dt.reporter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class Pokus extends TimerTask{

	@Override
	public void run() {


		DateFormat allDay = new SimpleDateFormat("y/M/d k:mm:ss");
		
		System.out.println(allDay.format(new Date()));
	}
	
}
