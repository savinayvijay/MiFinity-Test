package test2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class searchcard extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField search;
	private JLabel searchLbl;
	private DefaultTableModel model;
	private JButton searchBtn;
	private JButton editBtn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					searchcard frame = new searchcard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public searchcard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		
		//initializing components
		contentPane = new JPanel();
		search = new JTextField(20);
		searchLbl = new JLabel();
		model = new DefaultTableModel();
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		searchBtn = new JButton();
		editBtn = new JButton();
		
		
		//setting up components
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		scrollPane.setBounds(50,150,400,200);
		searchLbl.setBounds(20,100, 200,30);
		searchLbl.setText("Search a card number");
		search.setBounds(160,100, 200,30);
		editBtn.setText("Edit");
		editBtn.setBounds(215,390, 100,30);
		searchBtn.setText("Search");
		searchBtn.setBounds(380,100, 100,30);
		
		

		// setting up table model
		model.addColumn("Card Number");
        model.addColumn("Name");
        model.addColumn("Date");
        model.addColumn("Username");
       
		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				// checks is the search box is empty
				if(search.getText().matches("")) {
					
					JOptionPane.showMessageDialog(contentPane, "Enter Card Number to Search");
					return;
				}
				
				//deletes all rows before populating the table
				model.setRowCount(0);
				String sql = "";
				
				//if admin is logged in the retrieve all records otherwise retrieve records where the logged in user has created the card
				if(!home.userLoggedIn.matches("admin")) {
					sql = "Select * from cards WHERE username = '"+home.userLoggedIn+"' AND number LIKE '%"+search.getText()+"%'";
				}else {
					sql = "Select * from cards WHERE number LIKE '%"+search.getText()+"%'";
				}
				
			
				Connection conn = null;  
		        try {  
		            // db parameters  
		            String url = "jdbc:sqlite:db\\testdb.db";  
		            // create a connection to the database  
		            conn = DriverManager.getConnection(url);  
		            java.sql.Statement stmt = conn.createStatement();
			          // execute query
			         ResultSet rs = stmt.executeQuery(sql);
			         // add rows to table
			         while(rs.next()) {
			        	 model.addRow(new Object[]{rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4)});
			        	
			         }
			 
			          
		             
		              
		        } catch (SQLException e1) {  
		            System.out.println(e1.getMessage());  
		        } finally {  
		            try {  
		                if (conn != null) {  
		                    conn.close();  
		                }  
		            } catch (SQLException ex) {  
		                System.out.println(ex.getMessage());  
		            }  
		        }
		        
		    
				
			}
			
			
		});
		

		editBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(table.getSelectionModel().isSelectionEmpty()) {
					
					JOptionPane.showMessageDialog(contentPane, "Select a row to edit");
					return;
					
				}
				updatecard uc = new updatecard(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), table.getModel().getValueAt(table.getSelectedRow(), 1).toString(),table.getModel().getValueAt(table.getSelectedRow(), 2).toString());
				uc.setVisible(true);
				
				
				
			}
			
			
			
		});
		
		contentPane.add(scrollPane);
		contentPane.add(editBtn);
		contentPane.add(search);
		contentPane.add(searchLbl);
		contentPane.add(searchBtn);
		setContentPane(contentPane);
	}
	
	
		
	

}
