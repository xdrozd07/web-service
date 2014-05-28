/**
 * This is a servlet context listener, which contains method run only in startup
 * It is used for reporter scheduling
 */

package xdrozd07.dt.reporter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements javax.servlet.ServletContextListener  {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		ScheduledExecutorService scheduler  = Executors.newSingleThreadScheduledExecutor();

		System.out.println("Setting up a reporter scheduler");
		
		// schedule a Reporter task to be fired every 60 minutes, first after 5 minutes of application running
		scheduler.scheduleAtFixedRate(new Reporter(), 5, 60, TimeUnit.MINUTES);
		
	}

}
