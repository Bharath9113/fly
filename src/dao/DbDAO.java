package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import util.InItConn;

public class DbDAO {

	// create database
	public static void createDB(String dbName) throws SQLException {
		// get db connection
		Connection conn = new InItConn().getConnection();
		// prepare sql query
		String query = "create database " + dbName;
		// create sql statement
		Statement stm = conn.createStatement();
		// execute query
		stm.execute(query);
		// close db connection
		conn.close();
	}

	// drop database
	public static void dropDB(String dbName) throws SQLException {
		// get db connection
		Connection conn = new InItConn().getConnection();
		// prepare sql query
		String query = "drop database " + dbName;
		// create sql statement
		Statement stm = conn.createStatement();
		// execute query
		stm.execute(query);
		// close db connection
		conn.close();
	}
}
