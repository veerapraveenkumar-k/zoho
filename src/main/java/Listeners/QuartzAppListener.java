package Listeners;

import com.handlers.QuartzSchedularInitializer;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class QuartzAppListener implements ServletContextListener{
	public void contextInitialized(ServletContextEvent sce) {
		try {
			QuartzSchedularInitializer.startScheduler();
			System.out.println("Started");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			QuartzSchedularInitializer.stopScheduler();
			System.out.println("Stoped");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
