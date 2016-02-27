package com.app5.tnt.ws.login.jaxb.output;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class AuthentificateResInfo implements Serializable {

	private static final long serialVersionUID = 5028942651663831659L;
	
	private short success;
	private UserData user;
	
	public AuthentificateResInfo() {
	}
	
	public short isSuccess() {
		return success;
	}
	public void setSuccess(short success) {
		this.success = success;
	}
	public UserData getUser() {
		return user;
	}
	public void setUser(UserData user) {
		this.user = user;
	}
}
