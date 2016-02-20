package com.app5.tnt.ws.address.jaxb.input;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"number",
    "street",
    "postalCode",
    "city",
    "country"
})
@XmlRootElement(name="")
public class AddressReqInfo implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5176651218488425926L;
	private Integer number;
	private String street;
	private String postalCode;
	private String city;
	private String country;
	
	public AddressReqInfo()
	{
		
	}
	
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString()
	{
		return number + "," + street + "," + postalCode + "," + city + "," + country;
	}
	

}
