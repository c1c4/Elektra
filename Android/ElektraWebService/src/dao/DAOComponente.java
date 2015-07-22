package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Comodos;
import beans.Componente;

public class DAOComponente {
	
Connection connection = new DBConexao().getConnection();
	
	public Componente recuperaComponente(int idComponente)
	{	
		Componente c = new Componente();
		try {
			PreparedStatement stmt = this.connection.prepareStatement("Select * from componente where idcomponente = ?");
			stmt.setInt(1, idComponente);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) 
			{
				c.setIdComponente(rs.getInt("idcomponente"));
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
