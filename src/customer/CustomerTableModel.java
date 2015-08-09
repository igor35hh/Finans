package customer;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class CustomerTableModel extends AbstractTableModel implements Runnable{
	
private static final long serialVersionUID = 1L;
	
	private int columnCount = 3;
	private ArrayList<String []> dataArrayList;
	
	public CustomerTableModel() {
		
		new Thread(this).start();
		
		String[] str = new String[columnCount];
		str[0] = "0";
		str[1] = "Dan";
		str[2] = "QQQ";
		
		dataArrayList = new ArrayList<String []>();
		for(int i=0; i<5; i++){
			addDate(str);
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
	**/
	
	public void delDate(String [] row) {
		
		String []rowTable = new String[getColumnCount()];
		rowTable = row;
		dataArrayList.remove(rowTable);
		
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
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
