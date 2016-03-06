package com.app5.tnt.inject;

public class ContextManager {

	static Context currentContext = null ;
	
	static public boolean contextNull(){
		return currentContext == null;
	}
	static public void setContext(Context context) {
		currentContext = context ;
	}
}
