package be.vinci.ipl.resource;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import be.vinci.ipl.business.SheetItem;
import be.vinci.ipl.business.User;

@Path("sheetItem")
public class ItemSheetResource {
	
	@GET
	@Path("livret")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getLivret(User u) {
		return null;
	}
	
	@POST
	@Path("updateSheetItem")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSheetItem(String json) {
		
		Gson gson = new Gson();
		SheetItem si = new SheetItem();
		SheetItem itemToUpdate = gson.fromJson(json, SheetItem.class);
		System.out.println(itemToUpdate.toString());
		
		if(si.updateSheetItem(itemToUpdate)) {
			return Response.status(Response.Status.OK).build();
		}else {
			return Response.status(Response.Status.NOT_MODIFIED).build();
		}
				
	}
	
	@POST
	@Path("endGame")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertEndGameItems(String json) {
		Gson gson = new Gson();
		SheetItem si = new SheetItem();
		
		SheetAndItem requestBody = gson.fromJson(json, SheetAndItem.class);
		System.out.println("Request body : " + requestBody.toString());
		
		if(si.insertEndGameItems(requestBody.getId(), requestBody.getSheetItems())) {
			return Response.status(Response.Status.OK).build();
		}else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		
		
		
	}
	
	
	private class SheetAndItem {
		private int sheetId;
		private List<SheetItem> sheetItems;
		
		public SheetAndItem(int id) {
			this.sheetId = id;
			this.sheetItems = new ArrayList<SheetItem>();
		}
		
		public List<SheetItem> getSheetItems(){
			return this.sheetItems;
		}
		
		public int getId() {
			return this.sheetId;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "id : " + this.sheetId + "liste : " + sheetItems.toString();
		}
		
	}
	
	

}
