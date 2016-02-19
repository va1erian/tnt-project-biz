package com.app5.tnt.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {

	@Id
	@GeneratedValue
	private Long id ;
	
	@Column(name="POSTAL_CODE", length = 16, nullable=false)
	private String postalCode ;
	@Column(name="CITY", length = 64, nullable=false)
	private String city;
	@Column(name="COUNTRY", length = 64, nullable=false)
	private String country = "Frence";
	@Column(name="FORMATED_ADDRESS", unique= true, length= 512, nullable=false)
	private String formatedAddress ;
	@Column(name="GPS_LATITUDE", nullable=true)
	private Double gpsLatitude;
	@Column(name="GPS_lENGITUDE", nullable=true)
	private Double gpsLengitude;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getFormatedAddress() {
		return formatedAddress;
	}
	public void setFormatedAddress(String formatedAddress) {
		this.formatedAddress = formatedAddress;
	}
	public Double getGpsLatitude() {
		return gpsLatitude;
	}
	public void setGpsLatitude(Double gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}
	public Double getGpsLengitude() {
		return gpsLengitude;
	}
	public void setGpsLengitude(Double gpsLengitude) {
		this.gpsLengitude = gpsLengitude;
	}

}
