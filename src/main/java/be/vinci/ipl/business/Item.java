package be.vinci.ipl.business;

import java.util.List;

import be.vinci.ipl.persistence.IPersistenceService;
import be.vinci.ipl.persistence.ItemPersistence;
import be.vinci.ipl.persistence.PersistenceServiceImpl;

public class Item {

	private int id;
	private int category;
	private String image;
	private String name;
	private String note;
	private IPersistenceService service = new PersistenceServiceImpl();
	private ItemPersistence itemBack = new ItemPersistence();

	public Item() {
	}

	public Item(int id, int category, String image, String name) {
		this.id = id;
		this.category = category;
		this.image = image;
		this.name = name;
	}

	public Item(int id, int category, String image, String name, String note) {
		this.id = id;
		this.category = category;
		this.image = image;
		this.name = name;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public int getCategory() {
		return category;
	}

	public String getImage() {
		return image;
	}

	public String getName() {
		return name;
	}

	public List<Item> getAllItems() {
		try {
			this.service.startTransaction();
			List<Item> list = this.itemBack.getAllItems();
			if (list != null) {
				return list;
			} else {
				return null;
			}
		} catch (Exception exc) {
			System.out.println("Error in getting images : " + exc);
			exc.printStackTrace();
			this.service.rollbackTransaction();
			return null;
		} finally {
			this.service.commitTransaction();
		}

	}

}
