package com.app5.tnt.jpa.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.app5.tnt.jpa.model.Address;
import com.app5.tnt.jpa.model.User;
import com.app5.tnt.jpa.model.UserAddress;

public class DBInit {


	public static void main(String[] args) {
		Service service = ServiceFactory.getDefaultService();
		fillDB (service);
	}
	
	static public User u1, u2, u3; 
	static public Address a1, a2;
	static public UserAddress ua1, ua2;
	
	public static void fillDB(Service service){
		u1 = new User();
		u1.setDateOfBirth(new GregorianCalendar( 1892, 05, 10));
		u1.setEmail("boumaaza@gmail.com");
		u1.setFirstName("Kada");
		u1.setGender('m');
		u1.setLastName("Boumaaza");
		u1.setPassword("me3zetichaba");
		u1.setPhone("071253245");
		u1.setEmailValidated(true);
		service.commit(CommitOperation.Persist, u1);
		
		u2 = new User();
		u2.setDateOfBirth(new GregorianCalendar(1984, 05, 10));
		u2.setEmail("bouh@gmail.com");
		u2.setFirstName("Halouf");
		u2.setGender('m');
		u2.setLastName("kbir");
		u2.setPassword("ghwwwit");
		u2.setPhone("072253155");
		u2.setEmailValidated(false);
		service.commit(CommitOperation.Persist, u2);
		
		a1 = new Address();
		a1.setCity("Paris");
		a1.setCountry("Frence");
		a1.setFormatedAddress("???????????????????");
		a1.setPostalCode("75015");
		a1.setGpsLatitude(47.5963);
		a1.setGpsLengitude(05.82);
		service.commit(CommitOperation.Persist, a1);
		
		a2 = new Address();
		a2.setCity("Paris");
		a2.setCountry("Frence");
		a2.setFormatedAddress("???????????????????12233544621856");
		a2.setPostalCode("75019");
		a2.setGpsLatitude(47.6314);
		a2.setGpsLengitude(05.8422);
		service.commit(CommitOperation.Persist, a2);

		ua1 = new UserAddress();
		ua1.setAddress(a1);
		ua1.setName("Domicile");
		ua1.setUser(u1);
		ua1.setStartDate(Calendar.getInstance());
		ua1.setEndDate(new GregorianCalendar(2017,05,15));
		service.commit(CommitOperation.Persist, ua1);
	}

}