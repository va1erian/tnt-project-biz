package com.app5.tnt.jpa.service;

public class ServiceFactory {

	static private Service defaultService = null;
	
	static private Service restService = null;
	
	static public Service getDefaultService (){
		if (defaultService == null) defaultService = new ServiceImpl();
		return defaultService;
	}
	
	static public Service getResetService  (){
		if (restService == null) restService = new ServiceImpl("remote-server-reset");
		return restService ;
	}
	
	
}
