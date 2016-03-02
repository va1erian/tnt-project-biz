package com.app5.tnt.ws;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import com.app5.tnt.inject.Context;
import com.app5.tnt.inject.ContextManager;
import com.app5.tnt.jpa.service.Service;
import com.app5.tnt.jpa.service.ServiceFactory;

/**
 *
 * @author hadrien
 */
@ApplicationPath("rest")
public class Configuration extends ResourceConfig {
    public Configuration() {
        packages(true, "com.app5.tnt.ws");
        System.out.println("fdp");
        
        // Context init 
        
        ContextManager.setContext(new Context() {
        	
			public Service getService() {
				System.out.println(" inject default service >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
				return ServiceFactory.getDefaultService();
			}
		});
        
        
    }
}
