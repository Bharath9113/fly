package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import util.InItConn;

@WebServlet("/read-products")
public class ReadProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReadProducts() {
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
			ResultSet rst = ProductDAO.readProducts("ecom_products");
			while(rst.next()) {
				out.print("<p>"+rst.getInt("id") +"  ,  "+rst.getString("name") +"  ,  "
						+rst.getDouble("price") +" ,  "+rst.getDate("date_added") +"</p>");
			}
		} catch (SQLException e) {
			out.print("<h3 style='color:red'> Sorry, Product table reads failed ! <h3>"+e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
