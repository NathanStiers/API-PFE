package be.vinci.ipl.resource;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import be.vinci.ipl.business.Configuration;
import be.vinci.ipl.business.User;



@Path("user")

public class UserResource {
	
	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerUser(String json) {
		System.out.println("test register");
		Gson gson = new Gson();
		User u = new User();
		User userRegister = gson.fromJson(json, User.class);
		System.out.println(userRegister.toString());
		if(u.registerUser(userRegister)) {
			return Response.status(Response.Status.CREATED).build(); 
		}else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@POST
	@Path("connection")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response connectUser(String json) {
		System.out.println("test connection");
		Gson gson = new Gson();
		User u = new User();
		User userToConnect = gson.fromJson(json, User.class);
		System.out.println(userToConnect.toString());
		if(u.connectUser(userToConnect)) {
			Configuration config = u.getConfig(userToConnect);
			return Response.status(Response.Status.OK).entity(gson.toJson(config)).header("Access-Control-Allow-Origin", "*").build(); 
		}else if(u.connectPro(userToConnect)) {
			Configuration config = u.getConfig(userToConnect);
			return Response.status(201).entity(gson.toJson(config)).header("Access-Control-Allow-Origin", "*").build(); 
		}else if(u.connectContact(userToConnect)) {
			Configuration config = u.getConfig(userToConnect);
			return Response.status(202).entity(gson.toJson(config)).header("Access-Control-Allow-Origin", "*").build(); 
		}else {
			return Response.status(Response.Status.UNAUTHORIZED).header("Access-Control-Allow-Origin", "*").build();
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/info/{code}")
	public Response getInfo(@PathParam("code") String code) {
		Gson gson = new Gson();
		User u = new User();
		User allInfo = u.getAllInfo(code);
		System.out.println(allInfo.toString());
		String json = gson.toJson(allInfo);
		System.out.println(json);
		
		
		return Response.status(Response.Status.OK).entity(json).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("liste")
	public Response getAllUser() {
		Gson gson = new Gson();
		User u = new User();
		String json = gson.toJson(u.getAllUser());
		return Response.status(Response.Status.OK).entity(json).build();
	}
	

}
