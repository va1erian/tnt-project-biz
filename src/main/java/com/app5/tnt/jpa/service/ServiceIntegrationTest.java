package com.app5.tnt.jpa.service;

import static org.junit.Assert.*; 

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.app5.tnt.jpa.model.User;
import com.app5.tnt.jpa.service.CommitOperation;
import com.app5.tnt.utils.DateUtil;

public class ServiceIntegrationTest {
	
	private Service s = ServiceTestFactory.getIntegrationTesService();

	@Test
	public void testCommitPersistFindById() {
		User u = new User();
		u.setFirstName("Moussa");
		u.setPassword("SupprimeCetteLigneSTP");
		u.setDateOfBirth(DateUtil.getCalendar(new Date()));
		u.setEmail("fakeAdr4@gmail.com");
		u.setGender('M');
		u.setLastName("Himri");
		s.commit(CommitOperation.Persist, u);
		assertNotNull(u.getId());	
		Long id = new Long(u.getId().longValue());
		
		User fromDB = s.findById(User.class, id);
		assertEquals (u, fromDB);
	}

	@Test
	public void testCommitPersist_FindById_CommitUpdate() {
		User u = new User();
		u.setFirstName("Housni");
		u.setPassword("mdr ptdr");
		u.setDateOfBirth(DateUtil.getCalendar(new Date()));
		u.setEmail("nininini@gmail.com");
		u.setGender('M');
		u.setLastName("Bouslip");
		s.commit(CommitOperation.Persist, u);
		assertNotNull(u.getId());	
		Long id = new Long(u.getId().longValue());
		
		User fromDB = s.findById(User.class, id);
		assertEquals (u, fromDB);
		
		fromDB.setLastName("Boustring");
		
		s.commit(CommitOperation.Update, fromDB);
		User fromUpdatedDB = s.findById(User.class, id);
		assertEquals(fromDB, fromUpdatedDB);
		
		
	}
	
	@Test
	public void testCommitPersist_GetSingleResult() {
		User u = new User();
		u.setFirstName("Nabil");
		u.setPassword("bobo");
		u.setDateOfBirth(DateUtil.getCalendar(new Date()));
		u.setEmail("himnabil@gmail.com");
		u.setGender('M');
		u.setLastName("Himri");
		s.commit(CommitOperation.Persist, u);
		assertNotNull(u.getId());
		Map<String, Object> param = new HashMap<String,Object>();
		param.put(User.EmailParameterName, "himnabil@gmail.com");
		
		User fromDB = s.getSingleResult(User.class, User.GetByEmailQueryName, param);
		assertEquals (u, fromDB);		
	}


}
