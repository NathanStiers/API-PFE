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
	
}
