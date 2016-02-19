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

import com.app5.tnt.jpa.model.Address;
import com.app5.tnt.jpa.model.User;
import com.app5.tnt.jpa.model.UserAddress;
import com.app5.tnt.jpa.service.CommitOperation;
import com.app5.tnt.jpa.service.Service;
import com.app5.tnt.utils.NumericUtil;
import com.app5.tnt.ws.address.jaxb.input.AddAddressNestedReqInfo;
import com.app5.tnt.ws.address.jaxb.input.AddAddressReqInfo;
import com.app5.tnt.ws.address.jaxb.output.AddAddressResInfo;
import com.app5.tnt.ws.address.jaxb.output.AddressData;
import com.app5.tnt.ws.address.jaxb.output.ListAddressResInfo;

@Path("/address")
public class AddressService {

	@Path("/list")
	@GET
	@Produces("application/json")
	public Response list(@QueryParam("idUser") String idUser) {
		try {
			// Init result object
			ListAddressResInfo result = new ListAddressResInfo();

			// Init JPA service
			Service s = new Service();

			// Init params map
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(UserAddress.UserIdName, NumericUtil.convertLong(idUser));

			// Call JPA service
			List<UserAddress> list = s.getResultList(UserAddress.class,
					UserAddress.GetByUserIdQueryName, params);

			// Processing returned data from JPA service
			List<AddressData> data = new ArrayList<AddressData>();
			for (UserAddress userAddress : list) {
				AddressData ad = new AddressData();

				ad.setIdAddress(userAddress.getAddress().getId());
				ad.setFormattedAddress(userAddress.getAddress()
						.getFormatedAddress());
				ad.setGpsLatitude(userAddress.getAddress().getGpsLatitude());
				ad.setGpsLongitude(userAddress.getAddress().getGpsLengitude());

				data.add(ad);
			}
			result.setDataAddressList(data);

			// Return response
			return Response.ok(result, MediaType.APPLICATION_JSON).build();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.serverError().entity("Error").build();
		}
	}

	// TODO Robin finir
	@Path("/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(@FormParam("input") AddAddressReqInfo addAddressReqInfo) {
		try {
			// Init result object
			AddAddressResInfo result = new AddAddressResInfo();

			// Init JPA service
			Service s = new Service();

			// Init params map
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(UserAddress.UserIdName,
					(long) addAddressReqInfo.getIdUser());

			// Getting the user we are interested about
			User u = s.findById(User.class, params);

			// Getting the user's addresses
			List<UserAddress> list = s.getResultList(UserAddress.class,
					UserAddress.GetByUserIdQueryName, params);

			// Checking that the address we want to add does not already exists
			// in the user's addresses
			for (UserAddress userAddress : list) {

				// Comparing our formatted addresses
				if (addAddressReqInfo.getAddrUser().getFormattedAddress()
						.equals(userAddress.getAddress().getFormatedAddress())) {

					// If the address already exists for the user, returning 0
					result.setResult(0);
					return Response.ok(result, MediaType.APPLICATION_JSON)
							.build();
				}

			}

			// Building the UserAddress
			AddAddressNestedReqInfo addressToAdd = addAddressReqInfo
					.getAddrUser();

			Address newAddress = new Address();
			newAddress.setCity(addressToAdd.getCity());
			newAddress.setCountry(addressToAdd.getCountry());
			newAddress.setFormatedAddress(addressToAdd.getFormattedAddress());
			newAddress.setPostalCode(addressToAdd.getPostalCode());
			newAddress.setGpsLatitude(addressToAdd.getGpsLatitude());
			newAddress.setGpsLengitude(addressToAdd.getGpsLongitude());

			UserAddress newUserAddress = new UserAddress();
			newUserAddress.setUser(u);
			newUserAddress.setAddress(newAddress);

			// TODO: Vérifier avec Younes et Nabil: en input on a pas de "name"
			// qu'il faudrait setter à cette UserAddress ?

			// Adding it
			s.commit(CommitOperation.Persist, newUserAddress);

			// Building our result object
			result.setResult(1);

			// Return response
			return Response.ok(result, MediaType.APPLICATION_JSON).build();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Response.serverError().entity("Error").build();
		}
	}
}
