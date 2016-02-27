package integration.com.app5.tnt.jpa.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.app5.tnt.jpa.model.User;
import com.app5.tnt.jpa.service.CommitOperation;
import com.app5.tnt.jpa.service.Service;

public class ServiceTest {

	static Service s = new Service("remote-server-test");
	/*
	@Test
	public void testGetNewEntityManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testService() {
		fail("Not yet implemented");
	}
	*/
	//@Test
	public void testServiceString() {
		assertTrue(s != null);
	}
	
	//@Test
	public void testCommitPersist() {
		User u = new User();
		u.setFirstName("Moussa");
		u.setPassword("SupprimeCetteLigneSTP");
		u.setBirthOfDate(new Date());
		u.setEmail("fakeAdr4@gmail.com");
		u.setGender('M');
		u.setLastName("Himri");
		s.commit(CommitOperation.Persist, u);
	}
	
	//@Test
	public void testGetSingleResult() {
		User u = new User();
		u.setFirstName("Nabil");
		u.setPassword("bobo");
		u.setBirthOfDate(new Date());
		u.setEmail("himnabil@gmail.com");
		u.setGender('m');
		u.setLastName("Himri");
		
		s.commit(CommitOperation.Persist, u);
		
		Map<String, Object> param = new HashMap<String,Object>();
		param.put(User.EmailParameterName, "himnabil@gmail.com");
		
		User fromDB = s.getSingleResult(User.class, User.GetByEmailQueryName, param);
		assertEquals (u.getId(), fromDB.getId());
		
	}

	 
/*
	@Test
	public void testCommitAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSingleResult() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetResultList() {
		fail("Not yet implemented");
	}
	*/
}
