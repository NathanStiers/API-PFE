package be.vinci.ipl.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import be.vinci.ipl.persistence.IPersistenceService;
import be.vinci.ipl.persistence.PersistenceServiceImpl;
import be.vinci.ipl.persistence.SheetPersistence;

public class Sheet {

	private int id;
	private String user;
	private LocalDate date;
	private List<Item> items = new ArrayList<Item>();
	private List<SheetItem> sheetItems = new ArrayList<SheetItem>();
	private static IPersistenceService service = new PersistenceServiceImpl();
	private static SheetPersistence sheetBack = new SheetPersistence();

	public Sheet(int id, String user, LocalDate date) {
		this.id = id;
		this.user = user;
		this.date = date;

	}

	public Sheet() {

	}

	public boolean addSheetItem(SheetItem si) {
		return this.sheetItems.add(si);
	}

	public boolean addItem(Item i) {
		return this.items.add(i);
	}

	/**
	 * Initialize date at current date
	 * 
	 * @param id
	 * @param user
	 */
	public Sheet(int id, String user) {
		this.id = id;
		this.user = user;
		this.date = LocalDate.now();
	}
	
	/**
	 * 
	 * @param user : the code PK for the user
	 * @return the newly created sheet id
	 */
	public int newSheetForUser(String user) {
		try {
			this.service.startTransaction();
			return this.sheetBack.insertNewSheetForUser(user);
		} catch (Exception exc) {
			System.out.println("Error in newSheetForUser : " + exc);
			exc.printStackTrace();
			this.service.rollbackTransaction();
			
		} finally {
			this.service.commitTransaction();
		}
		return -1;

	}

	public Sheet getSheetForDate(java.sql.Date date, String code) {
		try {
			service.startTransaction();
			Sheet sheet = sheetBack.getSheetForDate(date, code);
			if (sheet != null) {
				return sheet;
			} else {
				return null;
			}
		} catch (Exception exc) {
			System.out.println("Error in connecting user : " + exc);
			exc.printStackTrace();
			service.rollbackTransaction();
			return null;
		} finally {
			service.commitTransaction();
		}

	}
	
	
	
	

	// Premier filtre
	public Sheet getSheetForName(String name) {
		try {
			service.startTransaction();
			Sheet sheet = sheetBack.getSheetForName(name);
			if (sheet != null) {
				return sheet;
			} else {
				return null;
			}
		} catch (Exception exc) {
			System.out.println("Error in connecting user : " + exc);
			exc.printStackTrace();
			service.rollbackTransaction();
			return null;
		} finally {
			// this.service.commitTransaction();
		}

	}

}
