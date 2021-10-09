package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import util.InItConn;

@WebServlet("/create-product-table")
public class ProductTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductTable() {
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
			ProductDAO.createTable("ecom_products");
			out.print("<h3 style='color:green'> Product table is created sucessfully <h3>");
		} catch (SQLException e) {
			out.print("<h3 style='color:red'> Sorry, Product table creation failed ! <h3>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
