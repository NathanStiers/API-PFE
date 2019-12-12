package be.vinci.ipl.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersistenceServiceImpl implements IPersistenceBackendService, IPersistenceService {

	

	private static ThreadLocal<Connection> localConnection = new ThreadLocal<Connection>();
	
	public PersistenceServiceImpl() {
		localConnection.set(getConnection());
	}
	
	
	private Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver PostgreSQL manquant !");
			
		}

		String url = "jdbc:postgresql://ec2-54-217-221-21.eu-west-1.compute.amazonaws.com:5432/d6r1vac3d8aagf?user=smgnrtsxjzzkks&password=be8d3cfeccc60b1e9d68156098d5bd5b0e33330c27260814a9b0e941abff4b6e&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory"; 
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url); 
			
		} catch (SQLException e) {
			System.out.println("Impossible de joindre le server !");
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	@Override
	public void startTransaction() {
		  try {
		      if (localConnection.get() == null) {
		        localConnection.set(this.getConnection());
		      }
		      localConnection.get().setAutoCommit(false);
		    } catch (Exception exc) {
		      System.out.println("Error in starting transaction : " + exc);
		    }
		
	}

	@Override
	public void commitTransaction() {
		try {
		      if (localConnection.get() != null) {
		        localConnection.get().commit();
		        localConnection.get().setAutoCommit(true);
		        localConnection.get().close();
		        localConnection.set(null);
		      }

		    } catch (SQLException exc) {
		    	System.out.println("Error in commit transaction : " + exc);
		    }finally {
		    	try {
		    		if(localConnection.get() != null)
		    			localConnection.get().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		
	}

	@Override
	public void rollbackTransaction() {
		try {
		      if (localConnection.get() != null) {
		        localConnection.get().rollback();
		        localConnection.get().setAutoCommit(true);
		        localConnection.get().close();
		        localConnection.set(null);
		      }
		    } catch (SQLException exc) {
		    	System.out.println("Error in rollback transaction : " + exc);
		    }finally {
		    	try {
		    		if(localConnection.get() != null)
		    			localConnection.get().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		
	}

	@Override
	public PreparedStatement getPreparedStatement(String query) throws SQLException {
		
		return localConnection.get().prepareStatement(query);
	}
	
	
	

}
