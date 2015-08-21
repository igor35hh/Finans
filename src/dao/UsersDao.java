package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import daouser.User;

//import java.util.List; 

public interface UsersDao {

	/** Создает новую запись и соответствующий ей объект */  
	public void create(String name, String pass) throws SQLException;   
	/** Возвращает объект соответствующий записи с первичным ключом key или null 
	 * @throws SQLException */  
	public User read(String name, String pass) throws SQLException;   
	/** Сохраняет состояние объекта group в базе данных */  
	public void update(String id, String name, String pass) throws SQLException;   
	/** Удаляет запись об объекте из базы данных */  
	public void delete(String id) throws SQLException;   
	/** Возвращает список объектов соответствующих всем записям в базе данных 
	 * @return */ 
	public ResultSet getAll() throws SQLException; 
		
}
