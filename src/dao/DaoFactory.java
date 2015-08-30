package dao;

import java.sql.Connection; 
import java.sql.SQLException;  

/** Fabric object for work with data base */ 
public interface DaoFactory {   
	/** Return connect to the database */  
	public Connection getConnection() throws SQLException;  
	
	/** Return object for manage persistent state of the object User */  
	public UsersDao getUsersDao(Connection connection);
	
} 

