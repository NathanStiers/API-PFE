package be.vinci.ipl.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;


import be.vinci.ipl.business.Item;

@Path("images")
public class ItemResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSheetForDate() {
		
		Gson gson = new Gson();
		Item i = new Item(); // Pas mieux de mettre ses méthodes en statique ?
		
		List<Item> itemsToReturn = i.getAllItems();
		
		if(itemsToReturn != null) {
			return  Response.status(Response.Status.OK).entity(gson.toJson(itemsToReturn)).build();
		} else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}	
	}
	
}
