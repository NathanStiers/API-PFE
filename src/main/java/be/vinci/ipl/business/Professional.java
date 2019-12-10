package be.vinci.ipl.business;

import java.time.LocalDate;

public class Professional {

	private int id;
	private String name;
	private String surname;
	private String profession;
	private String phone;
	private String email;
	private String mandate;
	private LocalDate mandateDate;
	private String initial_ask;

	public Professional(int id, String name, String surname, String profession, String phone, String email,
			String mandate, LocalDate mandateDate, String initial_ask) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.profession = profession;
		this.phone = phone;
		this.email = email;
		this.mandate = mandate;
		this.mandateDate = mandateDate;
		this.initial_ask = initial_ask;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMandate() {
		return mandate;
	}

	public void setMandate(String mandate) {
		this.mandate = mandate;
	}

	public LocalDate getMandateDate() {
		return mandateDate;
	}

	public void setMandateDate(LocalDate mandateDate) {
		this.mandateDate = mandateDate;
	}

	public String getInitial_ask() {
		return initial_ask;
	}

	public void setInitial_ask(String initial_ask) {
		this.initial_ask = initial_ask;
	}

}
