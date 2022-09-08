package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class LocalEntityManagerFactory
 *
 */
@WebListener
public class LocalEntityManager implements ServletContextListener {

	private static EntityManagerFactory emf;
    /**
     * Default constructor. 
     */
    public LocalEntityManager() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
 

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
            	
    	emf = Persistence.createEntityManagerFactory("neviPU");
    	
    }
    
    public static EntityManager getEntityManger() {
    	
    	
    		return emf.createEntityManager(); 
    	
    

    	 
    }

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		
		
		ServletContextListener.super.contextDestroyed(sce);
	}
    
    
   
	
}
