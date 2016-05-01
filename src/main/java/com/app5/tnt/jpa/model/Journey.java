package com.app5.tnt.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Journey {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Address adrSrc;
	@ManyToOne
	private Address adDst;
	
	@Column(name="COORD_GPS", length = 300, nullable=true)
	private String coordGps ;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAdrSrc() {
		return adrSrc;
	}

	public void setAdrSrc(Address adrSrc) {
		this.adrSrc = adrSrc;
	}

	public Address getAdDst() {
		return adDst;
	}

	public void setAdDst(Address adDst) {
		this.adDst = adDst;
	}

	public String getCoordGps() {
		return coordGps;
	}

	public void setCoordGps(String coordGps) {
		this.coordGps = coordGps;
	}
	
	
}
