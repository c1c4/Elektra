package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Usuario;

public class DAOLogin {

	Connection connection = new DBConexao().getConnection();
	
	public Usuario usuario(String login, String senha)
	{	
		Usuario usu = new Usuario();
		try {
			PreparedStatement stmt = this.connection.prepareStatement("Select * from usuario where login = ? and senha = ?");
			stmt.setString(1, login);
			stmt.setString(2, senha);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) 
			{
				usu.setIdUsuario(rs.getInt("idusuario"));
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usu;
	}
}
