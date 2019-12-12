package be.vinci.ipl.persistence;


import java.sql.PreparedStatement;
import java.util.List;

import be.vinci.ipl.business.SheetItem;


public class SheetItemPersistence {
	
	private static IPersistenceBackendService backend = new PersistenceServiceImpl();
	
	
	public SheetItemPersistence() {
		
	}
	
	public boolean updateSheetItemById(int id, boolean love, boolean help, boolean change, boolean favorite, String comment) {
		try {
			PreparedStatement ps = backend.getPreparedStatement("UPDATE public.\"Sheets_items\" SET \"Love_it\"=?, \"Need_help\"=?, \"Wanna_change\"=?, \"Favorite\"=?, \"Comment\"=? WHERE \"Id\"=?");
			ps.setBoolean(1, love);
			ps.setBoolean(2, help);
			ps.setBoolean(3, change);
			ps.setBoolean(4, favorite);
			ps.setString(5, comment);
			ps.setInt(6, id);
			return ps.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean insertEndGameItems(int sheetId, List<SheetItem> items) {
		
		for(SheetItem i : items) {
			
			try {
				PreparedStatement ps = backend.getPreparedStatement("INSERT public.\"Sheets_items\" SET \"Love_it\"=?, \"Need_help\"=?, \"Wanna_change\"=?, \"Favorite\"=?, \"Comment\"=? WHERE \"Id\"=?");
				ps.setBoolean(1, i.isLoveIt());
				ps.setBoolean(2, i.isNeedHelp());
				ps.setBoolean(3, i.isWannaChange());
				ps.setBoolean(4, i.isFavorite());
				ps.setString(5, i.getComment());
				ps.setInt(6, sheetId);
				ps.execute();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	
}
