package form;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.*;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import com.mysql.jdbc.Connection;

import customer.*;
import dao.DaoFactory;
import dao.MySqlDaoFactory;
import dao.MySqlUsersDao;
import user.*;

public class ClassFrame extends Applet {
	

	private static final long serialVersionUID = 1L;
	
	Connection con;
	DaoFactory daoFactory = new MySqlDaoFactory();
	
	JTabbedPane tabbedPane = new JTabbedPane();
	
	//user
	TableModel userdataModel = new UserTableModel(this,daoFactory);
    JTable usertable = new JTable(userdataModel);
    JScrollPane userscrollpane = new JScrollPane(usertable); 
    
    //customer
    TableModel customerdataModel = new CustomerTableModel();
    JTable customertable = new JTable(customerdataModel);
    JScrollPane customerscrollpane = new JScrollPane(customertable);
    
    
    
	public void init(){
		
		//user
        JPanel userbuttons = new JPanel();
       
        JButton useradd = new JButton("Insert");
        useradd.addActionListener(new UserInsertActionListener());
        userbuttons.add(useradd);
       
        JButton useredit = new JButton("Edit");
        useredit.addActionListener(new UserEditActionListener());
        userbuttons.add(useredit);
        
        JButton userremove = new JButton("Delete");
        userremove.addActionListener(new UserDeleteActionListener());
        userbuttons.add(userremove);
        
        JPanel userjpfortab = new JPanel();
        userjpfortab.add(userbuttons,BorderLayout.NORTH);
        userjpfortab.add(userscrollpane,BorderLayout.SOUTH);
        
        tabbedPane.addTab("User", userjpfortab);
   
        //customer
        JPanel customerbuttons = new JPanel();
       
        JButton customeradd = new JButton("Insert");
        customeradd.addActionListener(new CustomerInsertActionListener());
        customerbuttons.add(customeradd);
       
        JButton customeredit = new JButton("Edit");
        customeredit.addActionListener(new CustomerEditActionListener());
        customerbuttons.add(customeredit);
        
        JButton customerremove = new JButton("Delete");
        customerremove.addActionListener(new CustomerDeleteActionListener());
        customerbuttons.add(customerremove);
        
        JPanel customerjpfortab = new JPanel();
        customerjpfortab.add(customerbuttons,BorderLayout.NORTH);
        customerjpfortab.add(customerscrollpane,BorderLayout.SOUTH);
        
        tabbedPane.addTab("Customer", customerjpfortab);
        
        
        //all
        tabbedPane.setPreferredSize(new Dimension(500, 300));

        add(tabbedPane);
        //add(content, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
        setSize(new Dimension(550, 350));
  		
	}
	
	//user
	public class UserDeleteActionListener implements ActionListener {
		
		int sel = 0;
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
	        sel = usertable.getSelectedRow();
	        
	        if (sel >= 0) {
	        	
	        	final String id = (String) usertable.getValueAt(sel,0);
	        	
	        	try {

					con = (Connection) daoFactory.getConnection();
					
					MySqlUsersDao GetRes = new MySqlUsersDao(con);
					GetRes.delete(id);
	        		
                } catch (SQLException e1) {
                	e1.printStackTrace();
                }

	        }
			
		}

		
	}
	
	public class UserEditActionListener implements ActionListener {
		
		int sel = 0;
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			sel = usertable.getSelectedRow();
			 
			if (sel >= 0) {
	        	
				final String id = (String) usertable.getValueAt(sel,0);
				Object name  = usertable.getValueAt(sel,1);
				Object pass  = usertable.getValueAt(sel,2);
				
				
				final JFrame jf = new JFrame("Dialog user");
				
				final JTextField uname = new JTextField((String) name);
		
				final JPasswordField upass = new JPasswordField((String) pass);
				upass.setEchoChar('@');
				
				JButton usaveyes = new JButton("Save");
				usaveyes.addActionListener(
						new ActionListener() {
						@SuppressWarnings("deprecation")
						public void actionPerformed(ActionEvent e) {
							
							try {
								
								con = (Connection) daoFactory.getConnection();
								
								MySqlUsersDao GetRes = new MySqlUsersDao(con);
								GetRes.update(id,uname.getText(),upass.getText());
								
								jf.setVisible(false);
								
								} catch (SQLException e1) {		
								
								e1.printStackTrace();
								} 
					
							}
						}
				);
				
				JButton usavecancel = new JButton("Cansel");
				usavecancel.addActionListener(
						new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							jf.setVisible(false);
							}
						}
				);
				
				
				JPanel jp_top = new JPanel(new GridLayout(3, 2));

				jp_top.add(new JLabel("Enter user name"));

				jp_top.add(uname);

				jp_top.add(new JLabel("Enter password"));
				jp_top.add(upass);
				
				jp_top.add(usaveyes);
				jp_top.add(usavecancel);
				
				jf.getContentPane().add(jp_top, BorderLayout.CENTER);

				jf.pack();

				jf.setVisible(true);

	        }
			
		}
		
	}

	public class UserInsertActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			final JFrame jf = new JFrame("Dialog user");
			
			final JTextField uname = new JTextField();
	
			final JPasswordField upass = new JPasswordField();
			upass.setEchoChar('@');
			
			JButton usaveyes = new JButton("Save");
			usaveyes.addActionListener(
					new ActionListener() {
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e) {
						
						try {
							
							con = (Connection) daoFactory.getConnection();
							
							MySqlUsersDao GetRes = new MySqlUsersDao(con);
							GetRes.create(uname.getText(),upass.getText());
							
							jf.setVisible(false);
							
							} catch (SQLException e1) {		
							
							e1.printStackTrace();
							} 
				
						}
					}
			);
			
			JButton usavecancel = new JButton("Cansel");
			usavecancel.addActionListener(
					new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						jf.setVisible(false);
						}
					}
			);
			
			
			JPanel jp_top = new JPanel(new GridLayout(3, 2));

			jp_top.add(new JLabel("Enter user name"));

			jp_top.add(uname);

			jp_top.add(new JLabel("Enter password"));
			jp_top.add(upass);
			
			jp_top.add(usaveyes);
			jp_top.add(usavecancel);
			
			jf.getContentPane().add(jp_top, BorderLayout.CENTER);

			jf.pack();

			jf.setVisible(true);
			
		}
		
	}

	//customer
	public class CustomerDeleteActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			System.out.println("Delete");
			
		}
	}
	
	public class CustomerEditActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			System.out.println("Edit");
			
		}
	}

	public class CustomerInsertActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			System.out.println("Insert");
		}
	}

}





