package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import beans.ComodoComponente;
import beans.Comodos;
import beans.Componente;
import beans.NivelAcesso;

public class DAOComodoComponente {
	Connection connection = new DBConexao().getConnection();
	
	public ComodoComponente recuperaComodoComponente(int idComodoComponente)
	{	
		Comodos c = new Comodos();
		Componente cp = new Componente();
		ComodoComponente cc = null;
		DAOComodo daoC = new DAOComodo();
		DAOComponente daoCp = new DAOComponente();
		int idComodo;
		int idComponente;
		try {
			PreparedStatement stmt = this.connection.prepareStatement("Select * from comodo_componente where idcomodo_componente = ?");
			stmt.setInt(1, idComodoComponente);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) 
			{
				cc = new ComodoComponente();
				cc.setIdComodoComponente(rs.getInt("idcomodo_componente"));
				cc.setDataInicial(rs.getDate("data_inicial"));
				cc.setDataFinal(rs.getDate("data_final"));
				cc.setPorta(rs.getInt("ligar"));
				cc.setSensor(rs.getInt("sensor"));
				cc.setStatus(rs.getInt("status"));
				cc.setTemporizador(rs.getInt("temporizador"));
				
				idComodo = rs.getInt("idcmodo");
				idComponente = rs.getInt("idComponente");
				
				if(idComodo > 0)
					cc.setComodo(daoC.recuperaComodos(idComodo));
				
				if(idComponente > 0)
					cc.setComponentes(daoCp.recuperaComponente(idComponente));
			}
			rs.close();
			stmt.close();
			//connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cc;
	}
	
	public int recuperaStatus(int idComodoComponente, int porta) {
		int status = 0;
		try {
			PreparedStatement stmt = this.connection.prepareStatement("Select status from comodo_componente where idcomodo_componente = ? and ligar = ? or desligar = ?");
			stmt.setInt(1, idComodoComponente);
			stmt.setInt(2, porta);
			stmt.setInt(3, porta);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) 
			{
				status = rs.getInt("status");
			}
			rs.close();
			stmt.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
	public int updateStatus(int idComodoComponente, int status)
	{
		int sucesso = 0;
		try {
			PreparedStatement stmt = this.connection.prepareStatement("Update comodo_componente set status = ? where idcomodo_componente = ?");
			stmt.setInt(1, status);
			stmt.setInt(2, idComodoComponente);
			int count = stmt.executeUpdate();
			sucesso = count > 0 ? 1 : 0; 
			stmt.execute();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sucesso;
	}

}
