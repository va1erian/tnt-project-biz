package com.app5.tnt.ws.address.jaxb.output;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "result" })
@XmlRootElement(name = "")
public class AddAddressResInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1453423901951147753L;

	private int result;

	public AddAddressResInfo() {

	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

}
