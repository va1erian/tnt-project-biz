package com.app5.tnt.ws.address.jaxb.input;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "idUser", "name" ,"addrUser" })
@XmlRootElement(name = "")
public class AddAddressReqInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7723485771328038787L;

	private Integer idUser;
	private AddAddressNestedReqInfo addrUser;
	private String name;

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public AddAddressNestedReqInfo getAddrUser() {
		return addrUser;
	}

	public void setAddrUser(AddAddressNestedReqInfo addrUser) {
		this.addrUser = addrUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
