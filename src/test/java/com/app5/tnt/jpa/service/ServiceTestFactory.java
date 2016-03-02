package com.app5.tnt.jpa.service;

public class ServiceTestFactory {

	static private Service integrationTestService = null;
	
	static public Service getIntegrationTesService  (){
		if (integrationTestService == null) integrationTestService = new ServiceImpl("remote-server-test");
		return integrationTestService ;
	}
}
