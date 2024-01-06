package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDao;
import dto.Customer;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		CustomerDao customerDao = new CustomerDao();
		Customer customer = customerDao.validate(username, password);
		if (customer == null) {
			request.setAttribute("error", "password error");
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("account", customer);
			response.sendRedirect("shop");
		}
	}

}