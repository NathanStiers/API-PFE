package be.vinci.ipl.business;

public class UserProfessional {

	private int id;
	private String user;
	private int professional;
	
	public UserProfessional(int id, String user, int professional) {
		super();
		this.id = id;
		this.user = user;
		this.professional = professional;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getProfessional() {
		return professional;
	}

	public void setProfessional(int professional) {
		this.professional = professional;
	}
	
}
