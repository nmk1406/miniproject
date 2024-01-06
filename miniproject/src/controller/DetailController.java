package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import dto.Product;

@WebServlet("/detail")
public class DetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idRaw = request.getParameter("id");
		int id;
		
		try {
			id = Integer.parseInt(idRaw);
			ProductDao productDao = new ProductDao();
			Product product = productDao.getProductById(id);
			request.setAttribute("product", product);

			RequestDispatcher rd;
			rd = request.getRequestDispatcher("detail.jsp");
			rd.forward(request, response);
		} catch (NumberFormatException e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}