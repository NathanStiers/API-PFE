package be.vinci.ipl.business;

import java.util.List;

import be.vinci.ipl.persistence.CategoriePersistence;
import be.vinci.ipl.persistence.IPersistenceService;
import be.vinci.ipl.persistence.PersistenceServiceImpl;

public class Categorie {

	private int id;
	private String label;
	private static IPersistenceService service = new PersistenceServiceImpl();
	private static CategoriePersistence catBack = new CategoriePersistence();

	public Categorie(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public Categorie() {
	}
	
	public int getId() {
		return this.id;
	}

	public String getLabel() {
		return this.label;
	}
	
	public List<Categorie> getAllCategories() {
		try {
			this.service.startTransaction();
			List<Categorie> list = this.catBack.getAllCategories();
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
