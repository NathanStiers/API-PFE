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
	private IPersistenceService service = new PersistenceServiceImpl();
	private SheetPersistence sheetBack = new SheetPersistence();

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

	public Sheet getSheetForDate(java.sql.Date date, String code) {
		try {
			this.service.startTransaction();
			Sheet sheet = this.sheetBack.getSheetForDate(date, code);
			if (sheet != null) {
				return sheet;
			} else {
				return null;
			}
		} catch (Exception exc) {
			System.out.println("Error in connecting user : " + exc);
			exc.printStackTrace();
			this.service.rollbackTransaction();
			return null;
		} finally {
			this.service.commitTransaction();
		}

	}

	// Premier filtre
	public Sheet getSheetForName(String name) {
		try {
			this.service.startTransaction();
			Sheet sheet = this.sheetBack.getSheetForName(name);
			if (sheet != null) {
				return sheet;
			} else {
				return null;
			}
		} catch (Exception exc) {
			System.out.println("Error in connecting user : " + exc);
			exc.printStackTrace();
			this.service.rollbackTransaction();
			return null;
		} finally {
			// this.service.commitTransaction();
		}

	}

}
