package test2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;  

public class login extends JFrame {

	private JPanel contentPane;
	private String adminUsername = "admin";
	private String adminPassword = "admin";
	private JTextField username;
	private JPasswordField password;
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	private JButton loginBtn;
	private JButton registerBtn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		//initializing componenets
		username = new JTextField(20);
		password = new JPasswordField(20);
		usernameLbl = new JLabel();
		passwordLbl = new JLabel();
		loginBtn = new JButton();
		registerBtn = new JButton();
		
		
		// setting up components
		username.setBounds(160,100, 200,30);  
		password.setBounds(160,200, 200,30); 
		password.setEchoChar('*');
		usernameLbl.setText("Username");
		usernameLbl.setBounds(230,60, 200,30);
		passwordLbl.setText("Password");
		passwordLbl.setBounds(230,160, 200,30);
		loginBtn.setText("Login");
		loginBtn.setBounds(215,250, 100,30);
		registerBtn.setText("Register");
		registerBtn.setBounds(215,300, 100,30);
		
		
		// Login Click event
		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// checks for empty username or password
				if(username.getText().matches("") || password.getText().matches("")) {
					JOptionPane.showMessageDialog(contentPane, "Username Or Password is empty");
					return;
				}
				
				// checks if admin credentials are being used
				if(username.getText().matches(adminUsername) && password.getText().matches(adminPassword) ) {
					// sets the logged in user as Admin
				home.userLoggedIn = "admin";
				new home().setVisible(true);
				dispose();
				return;
				}
				
				String sql = "Select * from users where username = '"+username.getText()+"' AND password = '"+password.getText()+"'";
				database db = new database();
				db.connect();
				ResultSet rs = db.executeQuery(sql);
		        try {
					if(rs.next()) 
					{
					   // sets logged in user
						home.userLoggedIn = username.getText();
					    new home().setVisible(true);
						dispose();
					    	 
					}else {
					    	 JOptionPane.showMessageDialog(contentPane, "User Not Found");
					     }
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
		});
		
		
		registerBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new register().setVisible(true);
			}
			
			
		});
		
		// adding components
		contentPane.add(usernameLbl);
		contentPane.add(username);
		contentPane.add(passwordLbl);
		contentPane.add(password);
		contentPane.add(loginBtn);
		contentPane.add(registerBtn);
		setContentPane(contentPane);
		
		
		
	}

}
