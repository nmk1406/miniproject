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

import dao.ProductDao;
import dto.Cart;
import dto.Item;
import dto.Product;

@WebServlet("/process")
public class ProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDao productDao = new ProductDao();
		List<Product> products = productDao.getAllProduct();

		Cookie[] cookies = request.getCookies();
		String txt = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("cart")) {
					txt += cookie.getValue();
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}

		Cart cart = new Cart(txt, products);
		
		String numRaw = request.getParameter("num");
		String idRaw = request.getParameter("id");
		int id, num;
		
		try {
			id = Integer.parseInt(idRaw);
			Product product = productDao.getProductById(id);
			int numStore = product.getQuantity();
			
			num = Integer.parseInt(numRaw);
			if (num == -1 && (cart.getQuantityById(id) <= 1)) {
				cart.removeItem(id);
			} else {
				if (num == 1 && (cart.getQuantityById(id) >= numStore)) {
					num = 0;
				}
				double price = product.getPrice() * 2;
				Item item = new Item(product, num, price);
				cart.addItem(item);
			}
		} catch (NumberFormatException e) {
			System.out.println(e);
		}
		List<Item> items = cart.getItems();
		txt = "";
		if (items.size() > 0) {
			txt = items.get(0).getProduct().getId() + ":" + items.get(0).getQuantity();
			for (int i = 1; i < items.size(); i++) {
				txt += "/" + items.get(i).getProduct().getId() + ":" + items.get(i).getQuantity();
			}
		}
		Cookie cookie = new Cookie("cart", txt);
		cookie.setMaxAge(30*24*60*60);
		response.addCookie(cookie);
		request.setAttribute("cart", cart);
		
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("cart.jsp");
		rd.forward(request, response);
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
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		
		String id = request.getParameter("id");
		String[] ids = txt.split("/");
		String out = "";
		for (int i = 0; i < ids.length; i++) {
			String[] s = ids[i].split(":");
			if (!s[0].equals(id)) {
				if (out.isEmpty()) {
					out = ids[i];
				} else {
					out += "/" + ids[i];
				}
			}
		}
		
		if (!out.isEmpty()) {
			Cookie cookie = new Cookie("cart", out);
			cookie.setMaxAge(30*24*60*60);
			response.addCookie(cookie);
		}
		Cart cart = new Cart(out, products);
		request.setAttribute("cart", cart);
		
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("cart.jsp");
		rd.forward(request, response);
	}
}