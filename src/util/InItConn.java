package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InItConn {
	// data source
	private String url = "jdbc:mysql://localhost:3306/flyaway";
	private String username = "root";
	private String password = "1234";
	private Connection connection;
	
	// create db connection
	public InItConn() {
		try {
			// 1. register driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2.create connection
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//get connection
	public Connection getConnection() {
		return connection;
	}

	//close connection
	public void closeConnection() {
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
