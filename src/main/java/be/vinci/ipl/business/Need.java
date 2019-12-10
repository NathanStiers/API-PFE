package be.vinci.ipl.business;

public class Need {

	private int id;
	private String child;
	private String need;

	public Need(int id, String child, String need) {
		super();
		this.id = id;
		this.child = child;
		this.need = need;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChild() {
		return child;
	}

	public void setChild(String child) {
		this.child = child;
	}

	public String getNeed() {
		return need;
	}

	public void setNeed(String need) {
		this.need = need;
	}

}
