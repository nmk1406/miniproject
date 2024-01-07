package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Product;

public class ProductDao extends Connector{
	public List<Product> getAllProduct() {
		List<Product> products = new ArrayList<>();
		String sql = "select * from product";
		
		try (PreparedStatement ps = connection.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setQuantity(rs.getInt("quantity"));
				
				products.add(product);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return products;
	}
	
//	public static void main(String[] args) {
//		ProductDao productDao = new ProductDao();
//		List<Product> products = productDao.getAllProduct();
//		System.out.println(products);
//	}
	
	public Product getProductById(int id) {
		String sql = "select * from product where id = ?";
		
		try (PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setQuantity(rs.getInt("quantity"));
				
				return product;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		ProductDao productDao = new ProductDao();
//		Product product = productDao.getProductById(1);
//		System.out.println(product);
//	}
	
	public List<Product> search(String txt) {
		List<Product> products = new ArrayList<>();
		String sql = "select * from product where 1=1";
		
		if (txt != null && !txt.equals("")) {
			sql += " and name like '%" + txt + "%'";
		}
		
		try (PreparedStatement ps = connection.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setQuantity(rs.getInt("quantity"));
				
				products.add(product);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return products;
	}
	
//	public static void main(String[] args) {
//		ProductDao productDao = new ProductDao();
//		List<Product> products = productDao.search("coca");
//		System.out.println(products);
//	}
	
	public List<Product> getListByPage(List<Product> products, int start, int end) {
		ArrayList<Product> arr = new ArrayList<>();
		for (int i = start; i < end; i++) {
			arr.add(products.get(i));
		}
		return arr;
	}
}