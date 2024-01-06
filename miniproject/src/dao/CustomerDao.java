package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Customer;

public class CustomerDao extends Connector{
	public Customer validate(String username, String password) {
		String sql = "select * from customer where username = ? and password = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setAmount(rs.getDouble("amount"));
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
				
				return customer;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		CustomerDao customerDao = new CustomerDao();
//		Customer customer = customerDao.validate("a", "123");
//		System.out.println(customer);
//	}
}