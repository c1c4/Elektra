package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ComodoComponente;
import beans.NivelAcesso;
import beans.Usuario;

public class DAONivelAcesso {
	Connection connection = new DBConexao().getConnection();
	
	public List<NivelAcesso> nivelAcesso(int idUsuario)
	{	
		DAOComodoComponente cc = new DAOComodoComponente(); 
		NivelAcesso nv = null;
		List<NivelAcesso> listNA = new ArrayList<NivelAcesso>();
		int idComodoComponente;
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement("Select * from nivel_acesso where idusuario = ?");
			stmt.setInt(1, idUsuario);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) 
			{
				nv = new NivelAcesso();
				idComodoComponente = rs.getInt("idcomodo_componente");
				
				if(idComodoComponente > 0)
					nv.setComodoComponente(cc.recuperaComodoComponente(idComodoComponente));
				
				listNA.add(nv);
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listNA;
	}
}
