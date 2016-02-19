package com.app5.tnt.ws.user.jaxb.input;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.app5.tnt.ws.adapter.DateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idUser",
	"firstName",
    "lastName",
    "birthDate",
    "gender",
    "email"
})
@XmlRootElement
public class UpdateUserProfileReqInfo implements Serializable {
	
	private static final long serialVersionUID = 5028942651663831659L;
	
	private String idUser;
	private String firstName;
	private String lastName;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date birthDate;
	private String gender;
	private String email;
	
	public UpdateUserProfileReqInfo() {
		
	}
	
	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UpdateUserProfileReqInfo [idUser=" + idUser + ", firstName="
				+ firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", gender=" + gender + ", email=" + email + "]";
	}
}
