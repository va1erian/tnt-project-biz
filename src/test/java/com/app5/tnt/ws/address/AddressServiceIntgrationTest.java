package com.app5.tnt.ws.address;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;

import com.app5.tnt.jpa.model.Address;
import com.app5.tnt.jpa.service.DBInit;
import com.app5.tnt.ws.address.jaxb.output.AddressData;
import com.app5.tnt.ws.address.jaxb.output.ListAddressResInfo;

import test.util.TestContextInitialisator;

public class AddressServiceIntgrationTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestContextInitialisator.initContext();
	}
	
	@Test
	public void testList_UserExsis_manyAddresses() {
		List<Address> expected = new ArrayList<Address>();
		expected.add(DBInit.a1);
		expected.add(DBInit.a3);
		expected.add(DBInit.a4);
		String input = DBInit.u1.getId()+"";
		AddressService service = new AddressService();
		Response r = service.list(input);
		List<AddressData> result = ((ListAddressResInfo) r.getEntity()).getDataAddressList();	
		
		assertEquals(200, r.getStatus());
		assertEquals(expected.size(), result.size());
		
		for ( Address a : expected){
			boolean exsist = false; 
			for( AddressData ad : result ){
				boolean matche = true ;
				matche = matche && (a.getId().longValue() == ad.getIdAddress().longValue());
				matche = matche && (a.getGpsLatitude().doubleValue()	== ad.getGpsLatitude().doubleValue());
				matche = matche && (a.getGpsLongitude().doubleValue()	== ad.getGpsLongitude().doubleValue());
				matche = matche && (a.getFormatedAddress().equals(ad.getFormattedAddress()));
				exsist = exsist || matche;
			}
			assertTrue("->" + a.getId() + ':' + a.getFormatedAddress(), exsist);
		}		
	}
	
	@Test
	public void testList_UserExsist_noAddress() {
		
		String input = DBInit.u2.getId()+"";
		AddressService service = new AddressService();
		Response r = service.list(input);
		List<AddressData> result = ((ListAddressResInfo) r.getEntity()).getDataAddressList();	
		
		assertEquals(200, r.getStatus());
		assertEquals(0, result.size());
				
	}
	
	@Test
	public void testList_UserExsist_oneAddresse() {
		List<Address> expected = new ArrayList<Address>();
		expected.add(DBInit.a5);
		
		String input = DBInit.u3.getId()+"";
		AddressService service = new AddressService();
		Response r = service.list(input);
		List<AddressData> result = ((ListAddressResInfo) r.getEntity()).getDataAddressList();	
		
		assertEquals(200, r.getStatus());
		assertEquals(expected.size(), result.size());
		
		for ( Address a : expected){
			boolean exsist = false; 
			for( AddressData ad : result ){
				boolean matche = true ;
				matche = matche && (a.getId().longValue() == ad.getIdAddress().longValue());
				matche = matche && (a.getGpsLatitude().doubleValue()	== ad.getGpsLatitude().doubleValue());
				matche = matche && (a.getGpsLongitude().doubleValue()	== ad.getGpsLongitude().doubleValue());
				matche = matche && (a.getFormatedAddress().equals(ad.getFormattedAddress()));
				exsist = exsist || matche;
			}
			assertTrue("->" + a.getId() + ':' + a.getFormatedAddress(), exsist);
		}		
	}
	
	@Test
	public void testList_UserDoNoTExist() {
		String input = "0";
		AddressService service = new AddressService();
		Response r = service.list(input);	
		assertEquals(500, r.getStatus());
		
	}
}
