package com.app5.tnt.jpa.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class UserJourney {


	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Journey journey;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="START_DATE", nullable=false)
	private Calendar startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="END_DATE", nullable=true)
	private Calendar endDate;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Journey getJourney() {
		return journey;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
}
