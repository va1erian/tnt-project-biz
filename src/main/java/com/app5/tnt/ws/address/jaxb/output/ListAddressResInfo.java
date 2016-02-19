package com.app5.tnt.ws.address.jaxb.output;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.app5.tnt.jpa.model.UserAddress;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "data"
})
@XmlRootElement(name="")
public class ListAddressResInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1274374825702570636L;
	
	private List<AddressData> data;
	
	public ListAddressResInfo()
	{
		
	}

	public List<AddressData> getDataAddressList() {
		if(data == null)
			return new ArrayList<AddressData>();
		else
			return data;
	}

	public void setDataAddressList(List<AddressData> dataAddressList) {
		this.data = dataAddressList;
	}
	
	
	
}
