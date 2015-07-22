package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Usuario;

public class DAOLog {
	Connection connection = new DBConexao().getConnection();
	
	public void gravaUsuario(int idUsuario)
	{	
		Usuario usu = new Usuario();
		try {
			PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO log (idusuario) VALUES (?)");
			stmt.setInt(1, idUsuario);
			
			stmt.execute();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
