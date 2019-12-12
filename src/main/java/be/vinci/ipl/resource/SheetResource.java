package be.vinci.ipl.resource;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import be.vinci.ipl.business.Sheet;

@Path("sheet")
public class SheetResource {
	
	
	@GET
	@Path("/date/{code}/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSheetForDate(@PathParam("code") String code, @PathParam("date") java.sql.Date date) {
		
		Gson gson = new Gson();
		Sheet s = new Sheet();
		
		Sheet sheetToReturn = s.getSheetForDate(date, code);
		if(sheetToReturn != null) {
			return  Response.status(Response.Status.OK).entity(gson.toJson(sheetToReturn)).build();
		} else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}	
	}
	
	@GET
	@Path("/name/{name}")
	@Produces(MediaType.APPLICATION_JSON) // en fait si je réflechis c'est probablement un job pour le front directement
	public Response getSheetForName(@PathParam("name") String name) {
		
		Gson gson = new Gson();
		Sheet s = new Sheet();
		
		Sheet sheetToReturn = s.getSheetForName(name);
		if(sheetToReturn != null) {
			return  Response.status(Response.Status.OK).entity(gson.toJson(sheetToReturn)).build();
		} else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}	
	}
	
	//Après réflexion, mettre le userCode en clair dans une requête GET est TRES TRES MAUVAIS mais pas le temps de changer
	@GET
	@Path("new/{userCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNewSheetForUser(@PathParam("userCode") String code) {
		
		Sheet s = new Sheet();
		int toReturn = s.newSheetForUser(code);
		if(toReturn != -1) {
			return Response.status(Response.Status.CREATED).entity(toReturn).build();
		}else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
}
