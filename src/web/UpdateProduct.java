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

@WebServlet("/update-product")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateProduct() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// add nav bar
		request.getRequestDispatcher("index.html").include(request, response);
		// load add product
		request.getRequestDispatcher("update-product.html").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//fetch request params
		String productName = request.getParameter("name");
		String productPrice = request.getParameter("price");
		String productId = request.getParameter("id");
		
		request.getRequestDispatcher("index.html").include(request, response);
		
		//create product
		try {
			ProductDAO.updatePorduct("ecom_products",productId, productName, productPrice);
			// add nav bar
			out.print("<h3 style='color:green'> Product data updated sucessfully <h3>");
		} catch (SQLException e) {
			out.print("<h3 style='color:red'> Sorry, Product data updation failed ! <h3>"+e);
		}
	}

}
