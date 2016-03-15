package com.app5.tnt.jpa.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.app5.tnt.jpa.model.Address;
import com.app5.tnt.jpa.model.User;
import com.app5.tnt.jpa.model.UserAddress;

public class DBInit {


	public static void main(String[] args) {
		Service service = ServiceFactory.getResetService();
		fillDB (service);
	}
	
	static public User u1, u2, u3; 
	static public Address a1, a2, a3, a4 ,a5 ;
	static public UserAddress ua1, ua2, ua3, ua4;
	
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
		
		u3 = new User();
		u3.setDateOfBirth(new GregorianCalendar(1986, 04, 27));
		u3.setEmail("malma@gmail.com");
		u3.setFirstName("Maria");
		u3.setGender('f');
		u3.setLastName("Leblanc");
		u3.setPassword("md5");
		u3.setPhone("073345155");
		u3.setEmailValidated(true);
		service.commit(CommitOperation.Persist, u3);
		
		a1 = new Address();
		a1.setCity("Paris");
		a1.setCountry("Frence");
		a1.setFormatedAddress("----1----");
		a1.setPostalCode("75015");
		a1.setGpsLatitude(47.5963);
		a1.setGpsLongitude(05.82);
		service.commit(CommitOperation.Persist, a1);
		
		a2 = new Address();
		a2.setCity("Paris");
		a2.setCountry("Frence");
		a2.setFormatedAddress("---2---");
		a2.setPostalCode("75019");
		a2.setGpsLatitude(47.6314);
		a2.setGpsLongitude(05.8422);
		service.commit(CommitOperation.Persist, a2);

		a3 = new Address();
		a3.setCity("Paris");
		a3.setCountry("Frence");
		a3.setFormatedAddress("---3---");
		a3.setPostalCode("75020");
		a3.setGpsLatitude(48.56314);
		a3.setGpsLongitude(05.84543);
		service.commit(CommitOperation.Persist, a3);

		a4 = new Address();
		a4.setCity("Paris");
		a4.setCountry("Frence");
		a4.setFormatedAddress("---4---");
		a4.setPostalCode("75007");
		a4.setGpsLatitude(47.6314);
		a4.setGpsLongitude(06.8444);
		service.commit(CommitOperation.Persist, a4);

		a5 = new Address();
		a5.setCity("Paris");
		a5.setCountry("Frence");
		a5.setFormatedAddress("---5---");
		a5.setPostalCode("75016");
		a5.setGpsLatitude(47.6314);
		a5.setGpsLongitude(07.8455);
		service.commit(CommitOperation.Persist, a5);

		
		ua1 = new UserAddress();
		ua1.setAddress(a1);
		ua1.setName("Domicile");
		ua1.setUser(u1);
		ua1.setStartDate(Calendar.getInstance());
		ua1.setEndDate(null);
		service.commit(CommitOperation.Persist, ua1);
		
		ua2 = new UserAddress();
		ua2.setAddress(a3);
		ua2.setName("Travail");
		ua2.setUser(u1);
		ua2.setStartDate(Calendar.getInstance());
		ua2.setEndDate(null);
		service.commit(CommitOperation.Persist, ua2);

		ua3 = new UserAddress();
		ua3.setAddress(a4);
		ua3.setName("QG");
		ua3.setUser(u1);
		ua3.setStartDate(Calendar.getInstance());
		ua3.setEndDate(null);
		service.commit(CommitOperation.Persist, ua3);

		ua4 = new UserAddress();
		ua4.setAddress(a5);
		ua4.setName("Domicile");
		ua4.setUser(u3);
		ua4.setStartDate(Calendar.getInstance());
		ua4.setEndDate(null);
		service.commit(CommitOperation.Persist, ua4);


	}

}