package be.vinci.ipl.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import be.vinci.ipl.business.Categorie;

public class CategoriePersistence {

	private static IPersistenceBackendService backend = new PersistenceServiceImpl();

	public List<Categorie> getAllCategories() {

		PreparedStatement ps;

		try {
			ps = backend.getPreparedStatement("SELECT * FROM public.\"Categories\"");
			ResultSet rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			List<Categorie> toReturn = new ArrayList<Categorie>();
			while (rs.next()) {
				toReturn.add(new Categorie(rs.getInt(1), rs.getString(2)));
			}

			return toReturn;

		} catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}

	}
	
}
