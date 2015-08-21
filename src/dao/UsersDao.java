package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import daouser.User;

//import java.util.List; 

public interface UsersDao {

	/** ������� ����� ������ � ��������������� �� ������ */  
	public void create(String name, String pass) throws SQLException;   
	/** ���������� ������ ��������������� ������ � ��������� ������ key ��� null 
	 * @throws SQLException */  
	public User read(String name, String pass) throws SQLException;   
	/** ��������� ��������� ������� group � ���� ������ */  
	public void update(String id, String name, String pass) throws SQLException;   
	/** ������� ������ �� ������� �� ���� ������ */  
	public void delete(String id) throws SQLException;   
	/** ���������� ������ �������� ��������������� ���� ������� � ���� ������ 
	 * @return */ 
	public ResultSet getAll() throws SQLException; 
		
}
