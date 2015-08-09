package dao;

import java.sql.Connection; 
import java.sql.SQLException;  

import daouser.UsersDao;

/** Фабрика объектов для работы с базой данных */ 
public interface DaoFactory {   
	/** Возвращает подключение к базе данных */  
	public Connection getConnection() throws SQLException;  
	
	/** Возвращает объект для управления персистентным состоянием объекта Student */  
	public UsersDao getUsersDao(Connection connection);
	
} 

