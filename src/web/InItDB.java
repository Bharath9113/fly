package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.InItConn;

@WebServlet("/init-connection")
public class InItDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InItDB() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// add nav bar
		request.getRequestDispatcher("index.html").include(request, response);
		
		// create db connection
		try {
			InItConn conn = new InItConn();
			if (conn.getConnection() != null) {
				out.print("<h3 style='color:green'> Your DB connection successfully initiated !<h3>");
			}

		} catch (Exception e) {
			out.print("<h3 style='color:red'> Sorry, DB connection failed ! <h3>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
