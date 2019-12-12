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

		String url = "jdbc:postgresql://ec2-46-137-113-157.eu-west-1.compute.amazonaws.com:5432/devnvr0qn10ecg?user=vpceiywjjzrzdv&password=c521aaa1180612f7f43e118c8a4942d1fcd898ab1087338656bd28eb4caa5afc&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory"; 
		
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
