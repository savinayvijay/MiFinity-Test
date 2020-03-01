package test2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class database {
	
	String url = "jdbc:sqlite:db\\testdb.db";  
	Connection conn = null;
	public void connect() {
		try {
		conn = DriverManager.getConnection(url);
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String query) {
		ResultSet rs = null;
		
		try {
			java.sql.Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}

}
