package be.vinci.ipl.persistence;

import java.sql.PreparedStatement;

public interface IPersistenceBackendService {

	public PreparedStatement getPreparedStatement(String query) throws Exception;
}
