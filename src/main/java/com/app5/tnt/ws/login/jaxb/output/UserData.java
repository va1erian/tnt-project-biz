package com.app5.tnt.ws.login.jaxb.output;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.app5.tnt.ws.adapter.DateAdapter;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = {
    "firstName",
    "lastName",
    "birthDate",
    "gender",
    "email",
    "idUser"
})

@XmlRootElement(name="user")
public class UserData implements Serializable {

	private static final long serialVersionUID = 5028942651663831659L;
	
	private String firstName;
	private String lastName;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date birthDate;
	private Character gender;
	private String email;
	private Long idUser;
	
	public UserData() {
		
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

}
