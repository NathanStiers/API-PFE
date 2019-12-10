package be.vinci.ipl.persistence;


import java.sql.PreparedStatement;
import java.sql.ResultSet;



import be.vinci.ipl.business.Item;
import be.vinci.ipl.business.Sheet;
import be.vinci.ipl.business.SheetItem;

public class SheetPersistence {

	private static IPersistenceBackendService backend = new PersistenceServiceImpl();

	public Sheet getSheetForDate(java.sql.Date date, String code) {

		PreparedStatement ps;

		try {
			ps = backend.getPreparedStatement(
					"SELECT * FROM public.\"Sheets\" s LEFT JOIN public.\"Sheets_items\" si ON s.\"Id\" = si.\"Sheet_id\" LEFT JOIN public.\"Items\" i on si.\"Item_id\" = i.\"Id\" LEFT JOIN public.\"Categories\" c on c.\"Id\" = i.\"Category\" WHERE s.\"User\"= ? AND s.\"Date\"= ?");
			ps.setString(1, code);
			ps.setDate(2, date);
			ResultSet rs = ps.executeQuery();

			rs.next();

			Sheet sheetToReturn = new Sheet(rs.getInt(1),rs.getString(2), rs.getDate(3).toLocalDate());
			sheetToReturn.addItem(new Item(rs.getInt(6), rs.getInt(12), rs.getString(14), rs.getString(15)));
			sheetToReturn.addSheetItem(new SheetItem(rs.getInt(4), rs.getInt(5), rs.getInt(6)));
			while(rs.next()) {
				sheetToReturn.addItem(new Item(rs.getInt(6), rs.getInt(12), rs.getString(14), rs.getString(15)));
				sheetToReturn.addSheetItem(new SheetItem(rs.getInt(4), rs.getInt(5), rs.getInt(6)));
			}

			return sheetToReturn;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// Premier filtre
	public Sheet getSheetForName(String name) {

		PreparedStatement ps;

		try {
			ps = backend.getPreparedStatement(
					"SELECT * FROM public.\"Sheets\" s LEFT JOIN public.\"Sheets_items\" si ON s.\"Id\" = si.\"Sheet_id\" LEFT JOIN public.\"Items\" i on si.\"Item_id\" = i.\"Id\" LEFT JOIN public.\"Categories\" c on c.\"Id\" = i.\"Category\" WHERE s.\"Name\" LIKE ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Sheet sheetToReturn = new Sheet(rs.getInt(1),rs.getString(2), (rs.getDate(3).toLocalDate()));
			sheetToReturn.addItem(new Item(rs.getInt(6), rs.getInt(12), rs.getString(14), rs.getString(15)));
			sheetToReturn.addSheetItem(new SheetItem(rs.getInt(4), rs.getInt(5), rs.getInt(6)));
			while(rs.next()) {
				sheetToReturn.addItem(new Item(rs.getInt(6), rs.getInt(12), rs.getString(14), rs.getString(15)));
				sheetToReturn.addSheetItem(new SheetItem(rs.getInt(4), rs.getInt(5), rs.getInt(6)));
			}

			return sheetToReturn;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
