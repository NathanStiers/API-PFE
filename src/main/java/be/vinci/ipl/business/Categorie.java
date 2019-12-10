package be.vinci.ipl.business;

public class Categorie {

	private int id;
	private String label;

	public Categorie(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return this.id;
	}

	public String getLabel() {
		return this.label;
	}
}
