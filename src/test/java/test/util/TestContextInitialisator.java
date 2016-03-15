package test.util;

import com.app5.tnt.inject.Context;
import com.app5.tnt.inject.ContextManager;
import com.app5.tnt.jpa.service.DBInit;
import com.app5.tnt.jpa.service.Service;
import com.app5.tnt.jpa.service.ServiceTestFactory;

public class TestContextInitialisator {

	static public void initContext (){
		if (ContextManager.contextNull()) {
			ContextManager.setContext(new Context() {
				public Service getService() {
					return ServiceTestFactory.getIntegrationTesService();
				}
			});
			DBInit.fillDB(ServiceTestFactory.getIntegrationTesService());
		}
	}
	
}
