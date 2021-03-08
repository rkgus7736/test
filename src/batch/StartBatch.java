package batch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 * Application Lifecycle Listener implementation class StartBatch
 *
 */
@WebListener
public class StartBatch implements ServletContextListener {
	CronTriggerMaker js;
	
    public StartBatch() {
    	
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("MemberManager End");
    	js.shutdownScheduler(); //스케줄러 종료
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("MemberManager Start");
    	js = new CronTriggerMaker("0 0 12 1 1 ? *", SalaryUpJob.class);
		js.createTrigger();
    }
	
}
