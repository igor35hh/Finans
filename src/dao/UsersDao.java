package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import daouser.User;

//import java.util.List; 

public interface UsersDao {

	/** Create a new record and corresponding object */  
	public void create(String name, String pass) throws SQLException;   
	
	/** Return object corresponding record with primary key key or null */  
	public User read(String name, String pass) throws SQLException;  
	
	/** Save state object group in the database */  
	public void update(String id, String name, String pass) throws SQLException; 
	
	/** Delete record the object from the database */  
	public void delete(String id) throws SQLException;  
	
	/** Return list of objects corresponding to all the records in the database */ 
	public ArrayList<String[]> getAll() throws SQLException; 
		
}
