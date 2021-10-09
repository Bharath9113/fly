package web;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;

@WebServlet("/FlightList")
public class FlightList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// add nav bar
		request.getRequestDispatcher("index.html").include(request, response);
		// load add product
		request.getRequestDispatcher("UserPage.jsp").include(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String from=request.getParameter("from");
		String to=request.getParameter("to");
		String departure=request.getParameter("departure");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Dao dao = new Dao();
			List<String[]> flights=dao.getAvailableFlights(from, to, departure);			
			HttpSession session=request.getSession();
			session.setAttribute("flights", flights);
//			for(String[] flight:flights){
//				out.println(flight[0]);
//				out.println(flight[1]);
//				out.println(flight[2]);
//			}
			}
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("FlightList.jsp");
	}
}
