package user;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import dao.*;
import daouser.Users;
import form.ClassFrame;

import com.mysql.jdbc.Connection;

public class UserTableModel extends AbstractTableModel implements Runnable{

	private static final long serialVersionUID = 1L;
	
	private int columnCount = 3;
	private ArrayList<String []> dataArrayList;
	
	private final ClassFrame frameapp;
	
	public UserTableModel(ClassFrame frameapp) {
		
		this.frameapp = frameapp;
		
		new Thread(this).start();
		
		upDate();
	 
	}
	
    public void upDate() {
    	
		try {	
		
			Connection con;
			DaoFactory daoFactory = new MySqlDaoFactory();
			con = (Connection) daoFactory.getConnection();
			
			MySqlUsersDao GetRes = new MySqlUsersDao(con);
			Users result = GetRes.getAll();
			
	    	dataArrayList = new ArrayList<String []>();
    	
	    	while(result.rs.next()){
	    		
	    		String[] str = new String[columnCount];
	    		
	    		str[0] = result.rs.getString("idusers");
	    		str[1] = result.rs.getString("name");
	    		str[2] = result.rs.getString("password");
	
	    		addDate(str);
	    	}
    	
    	result.rs.close();
    	
		} catch (SQLException e) {		
			
			e.printStackTrace();
		} 	
		
	}
	
	
	@Override
	public int getColumnCount() {
		
		return columnCount;
	}

	@Override
	public int getRowCount() {
		
		return dataArrayList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		String []row = dataArrayList.get(rowIndex);
		return row[columnIndex];
	
	}

	public void addDate(String [] row) {
		
		String []rowTable = new String[getColumnCount()];
		rowTable = row;
		dataArrayList.add(rowTable);
		
	}
	
	/**
	public void addData(ConnectionDB connect) {
		
		ResultSet result = connect.resultSetQuery("Select * FROM users");
		try{
			while(result.next()){
				String []row = (
						result.getString("id"),
						result.getString("login"),
						result.getString("password")
						);
			};
			addDate(row);
			
		} 
		
		result.close();
		
		catch(SQLException e){
			e.printStackTrace();
		}	
	}
	 * @return 
	**/
	
	public void delDate(String [] row) {
		
		dataArrayList.remove(row);
		
	}
	
	public String getColumnName(int columnIndex) {
		
		switch(columnIndex){
			case 0: return "#id";
			case 1: return "login";
			case 2: return "password";
		}
		
		return "";
		
	}
	
	@Override
	public void run() {
		
		while(true){
			try {
				upDate();
				frameapp.repaint();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
