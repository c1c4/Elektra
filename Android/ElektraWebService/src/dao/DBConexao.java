package dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.management.RuntimeErrorException;

public class DBConexao {
	private static String conexao = null;
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/elektra", "root", "123456");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
