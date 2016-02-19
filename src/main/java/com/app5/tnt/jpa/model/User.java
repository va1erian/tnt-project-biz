package com.app5.tnt.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
		@NamedQuery(name = User.GetByEmailQueryName, query = "SELECT u " + "FROM User u " + "WHERE u.email = :email"),
		@NamedQuery(name = User.GetByIdAndEmailQueryName, query = "SELECT u " + "FROM User u "
				+ "WHERE u.id = :id AND u.email = :email") })
public class User {
	public static final String EmailParameterName = "email";
	public static final String IdParameterName = "id";
	public static final String GetByEmailQueryName = "User.GetByEmail";
	public static final String GetByIdAndEmailQueryName = "User.GetByIdAndEmail";

	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "FIRST_NAME", length = 64, nullable = false)
	private String firstName;
	@Column(name = "LAST_NAME", length = 64, nullable = false)
	private String lastName;
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_BIRTH", nullable = false)
	private Date birthOfDate;
	@Column(name = "GENDER", nullable = false)
	private Character gender;
	@Column(name = "EMAIL", unique = true, length = 128)
	private String email;
	@Column(name = "EMAIL_VALIDATED", nullable = false)
	private Boolean emailValitated;

	
	public Boolean getEmailValitated() {
		return emailValitated;
	}

	public void setEmailValitated(Boolean emailValitated) {
		this.emailValitated = emailValitated;
	}

	public Long getId() {
		return id;
	}

	@Column(name = "PHONE", length = 16, nullable = true)
	private String phone;
	@Column(length = 64, nullable = false)
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthOfDate() {
		return birthOfDate;
	}

	public void setBirthOfDate(Date birthOfDate) {
		this.birthOfDate = birthOfDate;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
