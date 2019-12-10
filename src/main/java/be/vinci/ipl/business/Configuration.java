package be.vinci.ipl.business;

import java.util.ArrayList;
import java.util.List;

public class Configuration {
	private List<String> needs = new ArrayList<String>();

	public Configuration() {

	}

	public boolean addNeed(String s) {
		return this.needs.add(s);
	}

}
