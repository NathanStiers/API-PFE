package be.vinci.ipl.business;

import java.util.List;

import java.time.LocalDate;

import be.vinci.ipl.business.User;

import be.vinci.ipl.business.Configuration;
import be.vinci.ipl.persistence.IPersistenceService;
import be.vinci.ipl.persistence.PersistenceServiceImpl;
import be.vinci.ipl.persistence.UserPersistence;

public class User {

	private String name;
	private String surname;
	private String code;
	private LocalDate birthday;
	private String language;
	private String dominance;
	private String schooling;
	private String schooling_level;
	private String schooling_type; // can be null
	private String contact_one;
	private String contact_two;
	private String contact_three;
	private static IPersistenceService service = new PersistenceServiceImpl();
	private static UserPersistence userBack = new UserPersistence();

	public User(String name, String surname, String code, LocalDate birthday, String language, String dominance,
			String schooling, String schooling_type, String schooling_level, String contact_one, String contact_two,
			String contact_three) {
		this.name = name;
		this.surname = surname;
		this.code = code;
		this.birthday = birthday;
		this.language = language;
		this.dominance = dominance;
		this.schooling = schooling;
		this.schooling_type = schooling_type;
		this.schooling_level = schooling_level;
		this.contact_one = contact_one;
		this.contact_two = contact_two;
		this.contact_three = contact_three;
		service = new PersistenceServiceImpl();
		userBack = new UserPersistence();
	}

	public User() {
		super();
	}

	public boolean registerUser(User u) {
		try {
			service.startTransaction();
			userBack.registerUser(u);

		} catch (Exception exc) {
			System.out.println("Error in register user : " + exc);
			exc.printStackTrace();
			service.rollbackTransaction();
			return false;
		} finally {
			service.commitTransaction();
		}
		return true;

	}

	public boolean connectUser(User u) {
		try {
			service.startTransaction();
			return userBack.connectUser(u);
		} catch (Exception exc) {
			System.out.println("Error in connecting user : " + exc);
			exc.printStackTrace();
			service.rollbackTransaction();
			return false;
		} finally {
			service.commitTransaction();
		}
	}
	
	public boolean connectPro(User u) {
		try {
			service.startTransaction();
			return userBack.connectPro(u);
		} catch (Exception exc) {
			System.out.println("Error in connecting pro : " + exc);
			exc.printStackTrace();
			service.rollbackTransaction();
			return false;
		} finally {
			service.commitTransaction();
		}
	}
	
	public boolean connectContact(User u) {
		try {
			service.startTransaction();
			return userBack.connectContact(u);
		} catch (Exception exc) {
			System.out.println("Error in connecting contact : " + exc);
			exc.printStackTrace();
			service.rollbackTransaction();
			return false;
		} finally {
			service.commitTransaction();
		}
	}

	public User getAllInfo(String code) {
		try {
			service.startTransaction();
			User u = userBack.getAllInfo(code);
			if (u != null) {
				return u;
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

	public Configuration getConfig(User u) {
		try {
			service.startTransaction();
			Configuration config = userBack.getConfigForUser(u);

			return config;

		} catch (Exception exc) {
			System.out.println("Error in connecting user : " + exc);
			exc.printStackTrace();
			service.rollbackTransaction();
			return null;
		} finally {
			service.commitTransaction();
		}
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getCode() {
		return code;
	}

	public List<User> getAllUser() {
		try {
			service.startTransaction();
			List<User> list = userBack.getAllusers();
			if (list != null) {
				return list;
			} else {
				return null;
			}
		} catch (Exception exc) {
			System.out.println("Error in getting images : " + exc);
			exc.printStackTrace();
			service.rollbackTransaction();
			return null;
		} finally {
			// this.service.commitTransaction();
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + " " + this.surname + " " + this.code + " " + this.birthday + " " + this.language;
	}

}
