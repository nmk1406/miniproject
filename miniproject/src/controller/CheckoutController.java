package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDao;
import dao.ProductDao;
import dto.Cart;
import dto.Customer;
import dto.Product;

@WebServlet("/checkout")
public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDao productDao = new ProductDao();
		List<Product> products = productDao.getAllProduct();
		
		Cookie[] cookies = request.getCookies();
		String txt = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("cart")) {
					txt += cookie.getValue();
				}
			}
		}
		
		Cart cart = new Cart(txt, products);
		HttpSession session = request.getSession();
		Customer customer = (Customer)session.getAttribute("account");
		
		if (customer == null) {
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		} else {
			OrderDao orderDao = new OrderDao();
			orderDao.addOrder(customer, cart);
			Cookie cookie = new Cookie("cart", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			response.sendRedirect("shop");
		}
	}
}
