package com.app5.tnt.jpa.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

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
	private Calendar dateOfBirth;
	@Column(name = "GENDER", nullable = false)
	private Character gender;
	@Column(name = "EMAIL", unique = true, length = 128, nullable = false )
	private String email;
	@Type(type="yes_no")
	@Column(name = "EMAIL_VALIDATED", nullable = false)
	private boolean emailValidated = false;
	@Column(name = "PHONE", length = 16, nullable = true)
	private String phone;
	@Column(length = 64, nullable = false)
	private String password;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


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


	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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


	public boolean isEmailValidated() {
		return emailValidated;
	}


	public void setEmailValidated(boolean emailValidated) {
		this.emailValidated = emailValidated;
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


	@Override
	public boolean equals(Object obj){
		if (obj ==  null || obj.getClass() != User.class ) return false;
		User user = (User) obj ;
		if (
				id.longValue() == user.id.longValue()
			&&	firstName.equals(user.firstName)
			&&  lastName.equals(user.lastName)
			&&	email.equals(user.email)
			&&  gender.charValue() == user.gender.charValue()
			&&  password.equals(user.password)
			&&  dateOfBirth.get(Calendar.YEAR) == user.dateOfBirth.get(Calendar.YEAR)
			&&  dateOfBirth.get(Calendar.MONTH) == user.dateOfBirth.get(Calendar.MONTH)
			&&  dateOfBirth.get(Calendar.DAY_OF_MONTH) == user.dateOfBirth.get(Calendar.DAY_OF_MONTH)
			&&  emailValidated == user.emailValidated
			&&  ((phone == null && user.phone == null)|| phone.equals(user.phone))
				)return true;
		return false;
	}
	
}
