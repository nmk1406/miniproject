package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import dto.Cart;
import dto.Customer;
import dto.Item;

public class OrderDao extends Connector{
	public void addOrder(Customer customer, Cart cart) {
		LocalDate curDate = LocalDate.now();
		String date = curDate.toString();
		
		String sql1 = "insert into orders (date, cid, totalmoney) values (?, ?, ?)";
		String sql2 = "select id from orders order by id desc limit 1";
		String sql3 = "insert into orderdetail (oid, pid, quantity, price) values (?, ?, ?, ?)";
		String sql4 = "update product set quantity = quantity - ? where id = ?";
		
		try {
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			ps1.setString(1, date);
			ps1.setInt(2, customer.getId());
			ps1.setDouble(3, cart.getTotalmoney());
			ps1.executeUpdate();
			
			PreparedStatement ps2 = connection.prepareStatement(sql2);
			ResultSet rs = ps2.executeQuery();
			
			if (rs.next()) {
				int oid = rs.getInt("id");
				for (Item item : cart.getItems()) {
					PreparedStatement ps3 = connection.prepareStatement(sql3);
					ps3.setInt(1, oid);
					ps3.setInt(2, item.getProduct().getId());
					ps3.setInt(3, item.getQuantity());
					ps3.setDouble(4, item.getPrice());
					ps3.executeUpdate();
				}
			}
			
			PreparedStatement ps4 = connection.prepareStatement(sql4);
			for (Item item : cart.getItems()) {
				ps4.setInt(1, item.getQuantity());
				ps4.setInt(2, item.getProduct().getId());
				ps4.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}