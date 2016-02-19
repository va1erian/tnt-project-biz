package com.app5.tnt.ws.address.jaxb.output;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"idAddress",
    "formattedAddress",
    "gpsLatitude",
    "gpsLongitude"
})
@XmlRootElement(name="")
public class AddressData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1640013899648288128L;
	private Long idAddress;
	private String formattedAddress;
	private Double gpsLatitude;
	private Double gpsLongitude;
	
	

	public Long getIdAddress() {
		return idAddress;
	}
	
	public void setIdAddress(Long idAddress) {
		this.idAddress = idAddress;
	}
	
	public String getFormattedAddress() {
		return formattedAddress;
	}
	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}
	public Double getGpsLatitude() {
		return gpsLatitude;
	}
	public void setGpsLatitude(Double gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}
	public Double getGpsLongitude() {
		return gpsLongitude;
	}
	public void setGpsLongitude(Double gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}
}
