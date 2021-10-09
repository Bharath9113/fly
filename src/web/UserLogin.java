package web;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
@WebServlet("/user-login")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// add nav bar
		request.getRequestDispatcher("index.html").include(request, response);
		// load add product
		request.getRequestDispatcher("HomePage.jsp").include(request, response);
	}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		try {
			Dao dao=new Dao();
			HashMap<String,String> user=dao.checkUser(email,password);
			HttpSession session=request.getSession();
			if(user!=null) {
				session.setAttribute("user", user);
				response.sendRedirect("HomePage.jsp");
			}
			else {
				session.setAttribute("message", "Invalid Details");
				response.sendRedirect("UserPage.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}
