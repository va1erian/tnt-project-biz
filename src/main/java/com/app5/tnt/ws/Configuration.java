package com.app5.tnt.ws;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author hadrien
 */
@ApplicationPath("rest")
public class Configuration extends ResourceConfig {
    public Configuration() {
        packages(true, "com.app5.tnt.ws");
        System.out.println("fdp");
        
    }
}
