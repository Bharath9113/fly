package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import util.InItConn;

@WebServlet("/read-products-counts")
public class ReadProductsAndCount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReadProductsAndCount() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// add nav bar
		request.getRequestDispatcher("index.html").include(request, response);
		
		// create table
		try {
			// get db connection
			Connection conn = new InItConn().getConnection();
			
			// create callable statement
			CallableStatement cstm = conn.prepareCall("{ call get_all_products_and_count() }");
			
			ResultSet rst1 = cstm.executeQuery();
			while(rst1.next()) {
				out.print("<p>"+rst1.getInt("id") +"  ,  "+rst1.getString("name") +"  ,  "
						+rst1.getDouble("price") +" ,  "+rst1.getDate("date_added") +"</p>");
			}
			
			cstm.getMoreResults();
			ResultSet rst2 = cstm.getResultSet();
			while(rst2.next()) {
				out.print("<h3>The Total Products : "+rst2.getInt("total_products") +"</h3>");
			}
		} catch (SQLException e) {
			out.print("<h3 style='color:red'> Sorry, Product table reads failed ! <h3>"+e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
