package daouser;

import java.sql.SQLException;

//import java.util.List; 

public interface UsersDao {

	/** ������� ����� ������ � ��������������� �� ������ */  
	public void create(String name, String pass) throws SQLException;   
	/** ���������� ������ ��������������� ������ � ��������� ������ key ��� null 
	 * @throws SQLException */  
	public Users read(String name, String pass) throws SQLException;   
	/** ��������� ��������� ������� group � ���� ������ */  
	public void update(String id, String name, String pass) throws SQLException;   
	/** ������� ������ �� ������� �� ���� ������ */  
	public void delete(String id) throws SQLException;   
	/** ���������� ������ �������� ��������������� ���� ������� � ���� ������ 
	 * @return */ 
	public Users getAll() throws SQLException; 
		
}
