package com.app5.tnt.jpa.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.app5.tnt.jpa.service.CommitOperation;
import com.app5.tnt.jpa.service.Service;
import com.app5.tnt.jpa.service.ServiceTestFactory;
import com.app5.tnt.utils.DateUtil;

public class UserQueryIntegrationTest {

	static private Service s;
	static private User u1, u2, u3;
	
	@BeforeClass
	static public void init(){
		s = ServiceTestFactory.getIntegrationTesService();
		u1 = new User();
		u1.setFirstName("Dalila");
		u1.setPassword("3oumriWouNmout3alih");
		u1.setDateOfBirth(DateUtil.getCalendar(new Date()));
		u1.setEmail("dalilabonne@gmail.com");
		u1.setGender('F');
		u1.setLastName("Kalou");
		
		u2 = new User();
		u2.setFirstName("Rayan");
		u2.setPassword("aya men 3and chachrates");
		u2.setDateOfBirth(DateUtil.getCalendar(new Date()));
		u2.setEmail("makadamia@gmail.com");
		u2.setGender('?');
		u2.setLastName("Maka");
		
		u3 = new User();
		u3.setFirstName("Jaouad");
		u3.setPassword("nherssek");
		u3.setDateOfBirth(DateUtil.getCalendar(new Date()));
		u3.setEmail("beljoumla@gmail.com");
		u3.setGender('M');
		u3.setLastName("Belmaamer");
		
		List<User> users = new ArrayList<User>();
		users.add(u1);
		users.add(u2);
		users.add(u3);
		
		s.commitAll(CommitOperation.Persist, users);
	}
	
	@Test
	public void testGetByEmailQuery() {
		Map<String, Object> param = new HashMap<String,Object>();
		param.put(User.EmailParameterName, u2.getEmail());
		
		User fromDB = s.getSingleResult(User.class, User.GetByEmailQueryName, param);
		assertEquals (u2, fromDB);
	}
	
	@Test
	public void testGetByIdAndEmailQuery() {
		Map<String, Object> param = new HashMap<String,Object>();
		param.put(User.EmailParameterName, u3.getEmail());
		param.put(User.IdParameterName, u3.getId());
		
		User fromDB = s.getSingleResult(User.class, User.GetByIdAndEmailQueryName, param);
		assertEquals (u3, fromDB);
	}
}
