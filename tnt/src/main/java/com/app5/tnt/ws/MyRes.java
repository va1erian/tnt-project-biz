package com.app5.tnt.ws;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/hello")
public class MyRes {

	@GET
	@Produces("text/plain")
	public String sayHelloPlain( @QueryParam("name") @DefaultValue("toto") String name) {
		return "Hello " + name;
	}
	
}