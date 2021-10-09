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

@WebServlet("/add-product")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddProduct() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// add nav bar
		request.getRequestDispatcher("index.html").include(request, response);
		// load add product
		request.getRequestDispatcher("add-product.html").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//fetch request params
		String productName = request.getParameter("name");
		String productPrice = request.getParameter("price");
		
		//create product
		try {
			ProductDAO.addPorduct("ecom_products", productName, productPrice);
			// add nav bar
			request.getRequestDispatcher("index.html").include(request, response);
			out.print("<h3 style='color:green'> Product data inserted sucessfully <h3>");
		} catch (SQLException e) {
			out.print("<h3 style='color:red'> Sorry, Product data insertion failed ! <h3>");
		}
	}

}
