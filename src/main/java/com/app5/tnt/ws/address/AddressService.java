package com.app5.tnt.ws.address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app5.tnt.jpa.model.UserAddress;
import com.app5.tnt.jpa.service.Service;
import com.app5.tnt.utils.GoogleApiUtil;
import com.app5.tnt.utils.NumericUtil;
import com.app5.tnt.ws.address.jaxb.input.AddressReqInfo;
import com.app5.tnt.ws.address.jaxb.output.AddressData;
import com.app5.tnt.ws.address.jaxb.output.ListAddressResInfo;
import com.google.maps.GeoApiContext;

@Path("/address")
public class AddressService {

	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list(@QueryParam("idUser") String idUser)
	{
		try
		{
			// Init result object
			ListAddressResInfo result = new ListAddressResInfo();
			
			// Init JPA service
			Service s = new Service();
			
			// Init params map
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(UserAddress.UserIdName, NumericUtil.convertLong(idUser));
			
			// Call JPA service
			List<UserAddress> list = s.getResultList(UserAddress.class, UserAddress.GetByUserIdQueryName, params);
			
			// Processing returned data from JPA service
			List<AddressData> data = new ArrayList<AddressData>();
			for (UserAddress userAddress : list)
			{
				AddressData ad = new AddressData();
				
				ad.setIdAddress(userAddress.getAddress().getId());
				ad.setFormattedAddress(userAddress.getAddress().getFormatedAddress());
				ad.setGpsLatitude(userAddress.getAddress().getGpsLatitude());
				ad.setGpsLongitude(userAddress.getAddress().getGpsLengitude());
				
				data.add(ad);
			}
			result.setDataAddressList(data);
			
			// Return response
			return Response.ok(result, MediaType.APPLICATION_JSON).build();
		}
		
		catch(Exception e) {
			System.out.println(e.getMessage());
			return Response.serverError().entity("Error").build();
		}
	}
	
	@Path("/checkAddress")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkAddress(@FormParam("input") AddressReqInfo addressReqInfo)
	{
		System.out.println(addressReqInfo.getCity());
		System.out.println(addressReqInfo.getCountry());
		try
		{
//			JAXBContext jc = JAXBContext.newInstance(AddressReqInfo.class);
//			Unmarshaller unmarshaller = jc.createUnmarshaller();
//			unmarshaller.setValidating(true);
//			 
//			Bibliotheque bibliotheque = (Bibliotheque) unmarshaller.unmarshal(new File("test.xml"));
			
			
			// Init result object
			ListAddressResInfo result = new ListAddressResInfo();
			
			// Call Google Web Service
			GeoApiContext context = new GeoApiContext().setApiKey(GoogleApiUtil.PRODUCTION_API_KEY);
			
//			GeocodingResult[] googleResults = GeocodingApi.newRequest(context).address(addressReqInfo.toString()).await();
//			
//			for(int i = 0; i < googleResults.length; i++)
//			{
//				AddressData ad = new AddressData();
//				
//				ad.setFormattedAddress(googleResults[i].formattedAddress);
//				ad.setGpsLatitude(googleResults[i].addressComponents[0].);
//			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return Response.serverError().entity("Error").build();
		}
		
		return null;
	}
}
