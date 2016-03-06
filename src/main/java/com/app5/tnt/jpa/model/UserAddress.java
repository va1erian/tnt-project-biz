package com.app5.tnt.jpa.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name = UserAddress.GetByUserIdQueryName, query = "SELECT ua " + "FROM UserAddress ua " + "WHERE ua.user.id = :userId"),
	})
public class UserAddress {
	static public final String GetByUserIdQueryName = "UserAddress.GetByUserId";
	static public final String UserIdName = "userId";
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="NAME", length = 32, nullable=false)
	private String name;
	//@Column(name="USER", nullable=false)
	@ManyToOne
	private User user;
	//@Column(name="ADDRESS", nullable=false)
	@ManyToOne
	private Address address;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="START_DATE", nullable=false)
	private Calendar startDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="END_DdATE", nullable=true)
	private Calendar endDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
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
