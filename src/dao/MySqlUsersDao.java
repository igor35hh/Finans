package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

import daouser.User;


public class MySqlUsersDao implements UsersDao {
	private final Connection connection; 

	
	@Override  
	public User read(String name, String pass) throws SQLException {  
		String sql = "SELECT idusers, name, password FROM users where name=? and password=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, name);
        statement.setString(2, pass);
        ResultSet rs = statement.executeQuery();
        rs.next();
        
        User us = new User();  
        us.setName(rs.getString("name"));   
        return us; 
	} 
	
	@Override  
	public ResultSet getAll() throws SQLException {  
		String sql = "SELECT idusers, name, password FROM users";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		
        return rs;
      
	} 
	
	@Override
	public void update(String id, String name, String pass) throws SQLException {
		String sql = "UPDATE users SET name=?, password=? where idusers=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, name);
        statement.setString(2, pass);
        statement.setString(3, id);
        statement.executeUpdate();
	}

	@Override
	public void delete(String id) throws SQLException {
		String sql = "DELETE from users where idusers=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, id);
        statement.executeUpdate();
	}
	
	@Override  
	public void create(String name, String pass) throws SQLException { 
		//String sql = "INSERT INTO users (name, password) VALUES (name=?, password=?)";
		String sql = "INSERT INTO users SET name=?, password=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		 statement.setString(1, name);
		 statement.setString(2, pass);
        statement.executeUpdate();
	} 

    public MySqlUsersDao(Connection connection) {
    	this.connection = connection; //super();
    }

}
