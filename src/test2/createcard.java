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


public class createcard extends JFrame {

	private JPanel contentPane;
	private JTextField cardNumber;
	private JTextField cardHolderName;
	private JTextField expiryDate;
	private JButton submit;
	private JLabel cardNumberLbl;
	private JLabel cardHolderNameLbl;
	private JLabel expiryDateLbl;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createcard frame = new createcard();
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
	public createcard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		
		// initializing components
		cardNumber = new JTextField(20);
		cardHolderName = new JTextField(20);
		expiryDate = new JTextField(20);
		cardNumberLbl = new JLabel();
		cardHolderNameLbl = new JLabel();
		expiryDateLbl = new JLabel();	
		submit = new JButton();
		
		// setting up components
		cardNumberLbl.setBounds(120,100, 100,30);
		cardNumberLbl.setText("Card Number");
		cardNumber.setBounds(220,100, 150,30);
		cardHolderNameLbl.setBounds(100,160, 120,30);
		cardHolderNameLbl.setText("Card Holder Name");
		cardHolderName.setBounds(220,160, 150,30);
		expiryDateLbl.setBounds(70,220, 150,30);
		expiryDateLbl.setText("Expiry Date (YY/MM)");
		expiryDate.setBounds(220,220, 150,30);
		submit.setText("Create");
		submit.setBounds(215,300, 100,30);
		
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
		setContentPane(contentPane);
		
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				// checks if details are empty
				if(cardNumber.getText().matches("") || cardHolderName.getText().matches("") || expiryDate.getText().matches("")) {	
					JOptionPane.showMessageDialog(contentPane, "Details Empty");
					return;
				}
				// checks if the date follows the YY/MM format
				if(!isDateValid(expiryDate.getText())) {
					JOptionPane.showMessageDialog(contentPane, "Invalid Date");
					return;
					
				}
				// check if card number does not contain any character other than numbers
				if(!isNumber(cardNumber.getText())) {
					JOptionPane.showMessageDialog(contentPane, "Invalid Card Number");
					return;
				}
				
				String sql = "INSERT INTO cards VALUES('"+cardNumber.getText()+"','"+cardHolderName.getText()+"','"+expiryDate.getText()+"','"+home.userLoggedIn+"')";
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
		            if(e1.getErrorCode()==19) {
		            	// if primary key constraint is violated then card already exists
		            	JOptionPane.showMessageDialog(contentPane, "Card Number Already Exists");
		            	
		            }else {
		            	
		            	JOptionPane.showMessageDialog(contentPane, e1.getMessage());
		            }
		            
		            	
		            
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
		
		
		 
		
		
		

	}
// check is date follows the YY/MM format
	public boolean isDateValid(String date){
		
		if(!date.matches("\\d\\d\\/\\d\\d")) {
			return false;
		}
		int year = Integer.parseInt(date.split("/")[0]);
        int month = Integer.parseInt(date.split("/")[1]);
        if(month>12) {
        	return false;
        }
        return true;
	}
	
	// checks if a string has numbers
	public boolean isNumber(String num) {
		try {
		long n = Long.parseLong(num);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
