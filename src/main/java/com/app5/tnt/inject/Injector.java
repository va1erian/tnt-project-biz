package com.app5.tnt.inject;

import com.app5.tnt.jpa.service.Service;

public class Injector {

	static public Service injectService (){
		return ContextManager.currentContext.getService(); 
	}
}
