package com.app5.tnt.ws.login.jaxb.output;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "success",
    "user"
})

@XmlRootElement(name="")
public class AuthentificateResInfo implements Serializable {
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
