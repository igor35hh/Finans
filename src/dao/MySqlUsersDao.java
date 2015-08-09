package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

import daouser.Users;
import daouser.UsersDao;


public class MySqlUsersDao implements UsersDao {
	private final Connection connection; 

	
	@Override  
	public Users read(String name, String pass) throws SQLException {  
		String sql = "SELECT idusers, name, password FROM users where name=? and password=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, name);
        statement.setString(2, pass);
        ResultSet rs = statement.executeQuery();
        rs.next();
        
        Users us = new Users();  
        us.setName(rs.getString("name"));   
        return us; 
	} 
	
	@Override  
	public Users getAll() throws SQLException {  
		String sql = "SELECT idusers, name, password FROM users";
		PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
		
        Users us = new Users();  
        us.setResult(rs);   
        return us; 
	} 
	
	@Override
	public void update(String id, String name, String pass) throws SQLException {
		String sql = "UPDATE users SET name='"+name+"', password='"+pass+"'  where idusers='"+id+"'";
		PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
	}

	@Override
	public void delete(String id) throws SQLException {
		String sql = "DELETE from users where idusers='"+id+"'";
		PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
	}
	
	@Override  
	public void create(String name, String pass) throws SQLException { 
		String sql = "INSERT INTO users (name, password) VALUES ('"+name+"', '"+pass+"')";
		PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
	} 

    public MySqlUsersDao(Connection connection) {
    	this.connection = connection; //super();
    }

}
