package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Comodos;

public class DAOComodo {

	Connection connection = new DBConexao().getConnection();
	
	public Comodos recuperaComodos(int idComodo)
	{	
		Comodos c = new Comodos();
		try {
			PreparedStatement stmt = this.connection.prepareStatement("Select * from comodo where idcomodo = ?");
			stmt.setInt(1, idComodo);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) 
			{
				c.setIdComodo(rs.getInt("idcomodo"));
				c.setDescricao(rs.getString("descricao"));
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
}
