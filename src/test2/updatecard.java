package test2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class updatecard extends JFrame {

	private JPanel contentPane;
	private JTextField cardNumber;
	private JTextField cardHolderName;
	private JTextField expiryDate;
	private JButton submit;
	private JLabel cardNumberLbl;
	private JLabel cardHolderNameLbl;
	private JLabel expiryDateLbl;
	private JButton cancelBtn;
	

	

	/**
	 * Create the frame.
	 */
	public updatecard(String cardNum, String cardHName, String expiry) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		System.out.println(cardNum);
		
		
		// initializing components
		cardNumber = new JTextField(20);
		cardHolderName = new JTextField(20);
		expiryDate = new JTextField(20);
		cardNumberLbl = new JLabel();
		cardHolderNameLbl = new JLabel();
		expiryDateLbl = new JLabel();	
		submit = new JButton();
		cancelBtn = new JButton();
		
		// setting up components
		cardNumberLbl.setBounds(120,100, 100,30);
		cardNumberLbl.setText("Card Number");
		cardNumber.setBounds(220,100, 150,30);
		cardNumber.setText(cardNum);
		cardNumber.setEditable(false);
		cardHolderNameLbl.setBounds(100,160, 120,30);
		cardHolderNameLbl.setText("Card Holder Name");
		cardHolderName.setBounds(220,160, 150,30);
		cardHolderName.setText(cardHName);
		cardHolderName.setEditable(false);
		expiryDateLbl.setBounds(70,220, 150,30);
		expiryDateLbl.setText("Expiry Date (dd-mm-yyyy)");
		expiryDate.setBounds(220,220, 150,30);
		expiryDate.setText(expiry);
		submit.setText("Submit");
		submit.setBounds(150,300, 100,30);
		cancelBtn.setText("Close");
		cancelBtn.setBounds(270,300, 100,30);
		
		// setting up frame and adding components
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.add(expiryDate);
		contentPane.add(cardHolderNameLbl);
		contentPane.add(cardHolderName);
		contentPane.add(cardNumberLbl);
		contentPane.add(expiryDateLbl);
		contentPane.add(cardNumber);
		contentPane.add(submit);
		contentPane.add(cancelBtn);
		
		
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				// checks if the date follows the dd-mm-yyyy format
				if(!isDateValid(expiryDate.getText())) {
					JOptionPane.showMessageDialog(contentPane, "Invalid Date");
					return;
					
				}
				
				
				String sql = "UPDATE cards SET expiry = '"+expiryDate.getText()+"' WHERE number = '"+cardNum+"'";
				Connection conn = null;  
				try {  
		            // db parameters  
		            String url = "jdbc:sqlite:db\\testdb.db";  
		            // create a connection to the database  
		            conn = DriverManager.getConnection(url);  
		            java.sql.Statement stmt = conn.createStatement();
			         // execute query
			        stmt.execute(sql);
		            dispose();
		              
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
				
				
				
				
			}});
		
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			dispose();	
			}
			
			
		});
		
		
		
		
		
		
		setContentPane(contentPane);
		
		
	}
	
	// check is date follows the dd-mm-yyyy format
		public boolean isDateValid(String date){
			
			Date today = new Date();
			if(date==null) {
				return false;
			}
			SimpleDateFormat sd = new SimpleDateFormat("dd-mm-yyyy");
			sd.setLenient(false);
			try {
				Date d = sd.parse(date);
				
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		}

}
