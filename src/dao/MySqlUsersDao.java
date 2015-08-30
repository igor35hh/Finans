package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.logging.Logger;

import daouser.User;


public class MySqlUsersDao implements UsersDao {
	private final Connection connection;
	private ArrayList<String []> dataArrayList;
	private int columnCount;
	
	private static Logger Log = Logger.getLogger(MySqlUsersDao.class.getName());
 
	private ResultSet rs = null; 
	
	@Override  
	public ArrayList<String[]> getAll() throws SQLException {  
		
		try {
			
			dataArrayList = new ArrayList<String []>();
		
			String sql = "SELECT idusers, name, password FROM users";
			PreparedStatement statement = connection.prepareStatement(sql);
			//ResultSet rs = statement.executeQuery();
			RunSQLQuery(statement, true);
		
			while(rs.next()){
	    		
	    		String[] str = new String[columnCount];
	    		
	    		str[0] = rs.getString("idusers");
	    		str[1] = rs.getString("name");
	    		str[2] = rs.getString("password");
	
	    		addDate(str);
	    	} 
		}
		finally {
			try{
				if(rs != null){ rs.close(); }
			} catch (SQLException e) {
				Log.warning("Error close result or execute");
				e.printStackTrace();
			} 	
		}
		
        return dataArrayList;
      
	} 
	
	public void addDate(String [] row) {
		
		String []rowTable = new String[columnCount];
		rowTable = row;
		
		dataArrayList.add(rowTable);
		
	}
	
	@Override  
	public User read(String name, String pass) throws SQLException { 
		
		User us = new User(); 
		
		try {
			String sql = "SELECT idusers, name, password FROM users where name=? and password=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
	        statement.setString(2, pass);
	        //ResultSet rs = statement.executeQuery();
	        RunSQLQuery(statement, true);
	        rs.next();
	          
	        us.setName(rs.getString("name"));   
	         
		}
		finally {
			try{
				if(rs != null){ rs.close(); }
			} catch (SQLException e) {
				Log.warning("Error close result or execute");
				e.printStackTrace();
			} 	
		} 
		
		return us;
	}
	
	@Override
	public void update(String id, String name, String pass) throws SQLException {
		try {
			String sql = "UPDATE users SET name=?, password=? where idusers=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
	        statement.setString(2, pass);
	        statement.setString(3, id);
	        //statement.executeUpdate();
	        RunSQLQuery(statement, false);
		}
		finally {
			try{
				if(rs != null){ rs.close(); }
			} catch (SQLException e) {
				Log.warning("Error close result or execute");
				e.printStackTrace();
			} 	
		}     
	}

	@Override
	public void delete(String id) throws SQLException {
		try {
			String sql = "DELETE from users where idusers=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
	        //statement.executeUpdate();
	        RunSQLQuery(statement, false);
		}
		finally {
			try{
				if(rs != null){ rs.close(); }
			} catch (SQLException e) {
				Log.warning("Error close result or execute");
				e.printStackTrace();
			} 	
		}     
	}
	
	@Override  
	public void create(String name, String pass) throws SQLException {
		try {
			//String sql = "INSERT INTO users (name, password) VALUES (name=?, password=?)";
			String sql = "INSERT INTO users SET name=?, password=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, pass);
	        //statement.executeUpdate();
	        RunSQLQuery(statement, false);
		}
		finally {
			try{
				if(rs != null){ rs.close(); }
			} catch (SQLException e) {
				Log.warning("Error close result or execute");
				e.printStackTrace();
			} 	
		}         
	} 

	public synchronized void RunSQLQuery(PreparedStatement statement, boolean fullQuery) throws SQLException {
		
		if (fullQuery){
			rs = statement.executeQuery();
		}
		else{
			statement.executeUpdate();
		}
		
	}
	
    public MySqlUsersDao(DaoFactory daoFactory) throws SQLException  {
    	this.connection = daoFactory.getConnection();
    }
    
    public MySqlUsersDao(DaoFactory daoFactory, int columnCount) throws SQLException  {
    	this.connection = daoFactory.getConnection();
    	this.columnCount = columnCount;
    }
    
}
