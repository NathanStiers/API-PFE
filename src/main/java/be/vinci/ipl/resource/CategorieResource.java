package be.vinci.ipl.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import be.vinci.ipl.business.Categorie;
import be.vinci.ipl.business.Item;

@Path("categories")
public class CategorieResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategories() {
		
		Gson gson = new Gson();
		Categorie c = new Categorie(); // Pas mieux de mettre ses méthodes en statique ?
		
		List<Item> itemsToReturn = c.get();
		
		if(itemsToReturn != null) {
			return  Response.status(Response.Status.OK).entity(gson.toJson(itemsToReturn)).build();
		} else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}	
	}
}
