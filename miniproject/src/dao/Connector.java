package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	private String jdbcURL = "jdbc:mysql://localhost:3306/mini";
	private String jdbcUsername = "root";
	private String jdbcPassword = "1111";
	protected Connection connection;
	
	public Connector() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}
}