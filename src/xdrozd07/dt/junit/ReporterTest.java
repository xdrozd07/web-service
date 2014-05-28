package xdrozd07.dt.junit;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.junit.Test;

import xdrozd07.dt.reporter.Mailer;
import xdrozd07.dt.reporter.MailerConfiguration;
import xdrozd07.dt.reporter.Pokus;
import xdrozd07.dt.reporter.Reporter;
public class ReporterTest {

	private ScheduledExecutorService scheduler;
	
	@Test
	public void test() {
/*
		Reporter r = new Reporter();
		r.makeReports(); 
		*/
		
		try {
			MailerConfiguration mc = MailerConfiguration.getConfigration();
			System.out.println(mc.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
