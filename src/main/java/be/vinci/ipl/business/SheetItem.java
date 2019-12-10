package be.vinci.ipl.business;



import be.vinci.ipl.persistence.IPersistenceService;
import be.vinci.ipl.persistence.PersistenceServiceImpl;
import be.vinci.ipl.persistence.SheetItemPersistence;

public class SheetItem {

	private int id;
	private int sheetId;
	private int itemId;
	private boolean loveIt;
	private boolean needHelp;
	private boolean wannaChange;
	private boolean favorite;
	private String comment;
	private static IPersistenceService service = new PersistenceServiceImpl();
	private static SheetItemPersistence sheetItemBack = new SheetItemPersistence();
	/**
	 * Initialize booleans to false // Pas plutot mieux de ne pas les initialiser du
	 * coup ?
	 * 
	 * @param id
	 * @param sheetId
	 * @param itemId
	 */
	public SheetItem(int id, int sheetId, int itemId) {
		super();
		this.id = id;
		this.sheetId = sheetId;
		this.itemId = itemId;
//		this.loveIt = false;
//		this.needHelp = false;
//		this.wannaChange = false;
	}
	
	
	public SheetItem() {
		// TODO Auto-generated constructor stub
	}


	public boolean updateSheetItem(SheetItem i) {
		try {
			this.service.startTransaction();
			 return this.sheetItemBack.updateSheetItemById(i.getId(), i.isLoveIt(), i.isNeedHelp(), i.isWannaChange(), i.isFavorite(), i.getComment());

		} catch (Exception exc) {
			System.out.println("Error in update sheet item: " + exc);
			exc.printStackTrace();
			this.service.rollbackTransaction();
			return false;
		} finally {
			this.service.commitTransaction();
		}
		
	}
	
	public String toString() {
		return this.id + " " + this.comment + " " + this.favorite + " " + this.loveIt;
	}
	

	public String getComment() {
		return comment;
	}


	public int getId() {
		return id;
	}

	public int getSheetId() {
		return sheetId;
	}

	public int getItemId() {
		return itemId;
	}

	public boolean isLoveIt() {
		return loveIt;
	}

	public boolean isNeedHelp() {
		return needHelp;
	}

	public boolean isWannaChange() {
		return wannaChange;
	}
	
	public boolean isFavorite() {
		return favorite;
	}

	public void setSheetId(int sheetId) {
		this.sheetId = sheetId;
	}

	public void setNeedHelp(boolean needHelp) {
		this.needHelp = needHelp;
	}

	public void setWannaChange(boolean wannaChange) {
		this.wannaChange = wannaChange;
	}
	
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

}
