package test2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class register extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField password;
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	private JButton registerBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register frame = new register();
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
	public register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		//Initializing Components for JFrame
		username = new JTextField(20);
		password = new JTextField(20);
		usernameLbl = new JLabel();
		passwordLbl = new JLabel();
		registerBtn = new JButton();
		
		// setting up components
		username.setBounds(160,100, 200,30);  
		password.setBounds(160,200, 200,30);  
		usernameLbl.setText("Username");
		usernameLbl.setBounds(230,60, 200,30);
		passwordLbl.setText("Password");
		passwordLbl.setBounds(230,160, 200,30);
		registerBtn.setText("Register");
		registerBtn.setBounds(215,250, 100,30);
		
		//register click event
		registerBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String usernames = username.getText();
				String passwords = password.getText();
				// checks if username or password is empty
				if(username.getText().matches("") || password.getText().matches("")) {
					JOptionPane.showMessageDialog(contentPane, "Username Or Password is empty");
					return;
				}
				
				String sql = "INSERT INTO users VALUES('"+usernames+"','"+passwords+"')";
				Connection conn = null;  
				try {  
		            // db parameters  
		            String url = "jdbc:sqlite:db\\testdb.db";  
		            // create a connection to the database  
		            conn = DriverManager.getConnection(url);  
		            java.sql.Statement stmt = conn.createStatement();
			         // execute query
			         stmt.execute(sql);
		            System.out.println("User Registered");
		            dispose();
		              
		        } catch (SQLException e1) {  
		            System.out.println(e1.getMessage());
		            if(e1.getErrorCode()==19) {
		            	// if primary key violation occurs then Username already exists
		            	JOptionPane.showMessageDialog(contentPane, "Username Exists");
		            	
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
		// adding components to JFrame
		contentPane.add(usernameLbl);
		contentPane.add(username);
		contentPane.add(passwordLbl);
		contentPane.add(password);
		contentPane.add(registerBtn);
		setContentPane(contentPane);
	}

}
