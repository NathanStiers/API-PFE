package be.vinci.ipl.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IPersistenceBackendService {

	public PreparedStatement getPreparedStatement(String query) throws Exception;
}
