package com.app5.tnt.ws.address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app5.tnt.jpa.model.UserAddress;
import com.app5.tnt.jpa.service.Service;
import com.app5.tnt.utils.NumericUtil;
import com.app5.tnt.ws.address.jaxb.output.AddressData;
import com.app5.tnt.ws.address.jaxb.output.ListAddressResInfo;

@Path("/address")
public class AddressService {

	@Path("/list")
	@GET
	@Produces("application/json")
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
	
	
	
}
