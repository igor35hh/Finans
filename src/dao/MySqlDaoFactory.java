package dao;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException;

public class MySqlDaoFactory implements DaoFactory {   
	
	private String user = "root";//����� ������������  
	private String password = "mysql";//������ ������������  
	private String url = "jdbc:mysql://localhost:3306/mybank";//URL �����  
	private String driver = "com.mysql.jdbc.Driver";//��� ��������
	private static volatile Connection instance;
	
	
	private Connection RunConnection() throws SQLException {  
		return DriverManager.getConnection(url, user, password);  
	}   
	
	public Connection getConnection() throws SQLException {  
		if(instance==null)
			synchronized (MySqlDaoFactory.class) {
				if(instance==null)
					instance = (Connection) RunConnection();
			}
		return (Connection) instance;  
	} 
	
	@Override  
	public UsersDao getUsersDao(Connection connection) {  
		//return null;
		return (UsersDao) instance; 
	}   
	
	public MySqlDaoFactory() {  
		try {  Class.forName(driver);
		//������������ �������  
		} catch (ClassNotFoundException e) {  e.printStackTrace();  }  
	}
	
		
}
