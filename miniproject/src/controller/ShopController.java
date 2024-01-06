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

import dao.ProductDao;
import dto.Cart;
import dto.Item;
import dto.Product;

@WebServlet("/shop")
public class ShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDao productDao = new ProductDao();
		List<Product> products = productDao.getAllProduct();
		
		String search = request.getParameter("txt");
		List<Product> productShow = productDao.search(search);
		
		int page;
		int numpage = 5;
		int num = (productShow.size() == 0) ? (productShow.size() / numpage) : ((productShow.size() / numpage) + 1);
		String xpage = request.getParameter("page");
		if (xpage == null) {
			page = 1;
		} else {
			page = Integer.parseInt(xpage);
		}
		int start = (page - 1) * numpage;
		int end = Math.min(page * numpage, productShow.size());
		
		List<Product> listProduct = productDao.getListByPage(productShow, start, end);
		request.setAttribute("products", listProduct);
		request.setAttribute("num", num);
		request.setAttribute("page", page);
		request.setAttribute("txt", search);
		
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
		List<Item> items = cart.getItems();

		int size;
		if (items != null) {
			size = items.size();
		} else {
			size = 0;
		}

		HttpSession session = request.getSession();
		session.setAttribute("size", size);

		RequestDispatcher rd;
		rd = request.getRequestDispatcher("shop.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}