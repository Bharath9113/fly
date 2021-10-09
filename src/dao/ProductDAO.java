package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.InItConn;

public class ProductDAO {

	// create table
	public static void createTable(String tableName) throws SQLException {
		// get db connection
		Connection conn = new InItConn().getConnection();
		// prepare sql query
		String query = "create table " + tableName + "(id bigint primary key auto_increment,"
				+ " name varchar(100) , price decimal(10,2), date_added timestamp default now())";
		// create sql statement
		Statement stm = conn.createStatement();
		// execute query
		stm.execute(query);
		// close db connection
		conn.close();
	}

	// create product
	public static void addPorduct(String tableName, String productName, String productPrice) throws SQLException {
		// get db connection
		Connection conn = new InItConn().getConnection();
		// prepare insert query
		String query = "insert into " + tableName + "(name,price) values('" + productName + "'," + productPrice + ")";
		// create statement
		Statement stm = conn.createStatement();
		// execute query
		stm.execute(query);
		// close db connection
		conn.close();
	}

	// read all products
	public static ResultSet readProducts(String tableName) throws SQLException {
		// get db connection
		Connection conn = new InItConn().getConnection();

		// prepare insert query
		String query = "select * from " + tableName;

		// create statement
		Statement stm = conn.createStatement();

		// execute query
		ResultSet response = stm.executeQuery(query);

		return response;
	}

	// update product
	public static int updatePorduct(String tableName, String productId, String productName, String productPrice)
			throws SQLException {
		// get db connection
		Connection conn = new InItConn().getConnection();

		String updateQuery = "update " + tableName + " set name=?, price=? where id= ?";

		// create prepared statement
		PreparedStatement pstm = conn.prepareStatement(updateQuery);

		// set parameters
		pstm.setString(1, productName);
		pstm.setDouble(2, Double.parseDouble(productPrice));
		pstm.setInt(3, Integer.parseInt(productId));

		// execute query
		int noOfRowsAffected = pstm.executeUpdate();
		// close db connection
		conn.close();

		return noOfRowsAffected;
	}

	// delete product
	public static void deletePorduct(String tableName, String productId) throws SQLException {

		// get db connection
		Connection conn = new InItConn().getConnection();

		String query = "delete from " + tableName + " where id=" + productId;
		// create statement
		Statement stm = conn.createStatement();
		// execute query
		stm.execute(query);
		// close db connection
		conn.close();
	}

	public static List<ResultSet> getAllProductsAndCount() throws SQLException {

		List<ResultSet> list = new ArrayList<>();
		
		// get db connection
		Connection conn = new InItConn().getConnection();
		
		// create callable statement
		CallableStatement cstm = conn.prepareCall("{ call get_all_products_and_count() }");
		
		ResultSet rst1 = cstm.executeQuery();
		list.add(rst1);
		
		cstm.getMoreResults();
		ResultSet rst2 = cstm.getResultSet();
		list.add(rst2);
		
		return list;		
	}

}
