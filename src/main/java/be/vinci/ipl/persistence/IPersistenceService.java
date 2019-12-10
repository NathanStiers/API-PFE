package be.vinci.ipl.persistence;

public interface IPersistenceService {
	
	public void startTransaction();
	public void commitTransaction();
	public void rollbackTransaction();

}
