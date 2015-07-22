package com.Login;

import beans.Usuario;
import dao.DAOLog;
import dao.DAOLogin;

public class ServicoLogin {
	
	public String verificaUsuario(String login, String senha)
	{
		Usuario usu = new Usuario();
		int idUsuario = 0;
		DAOLogin loginDAO = new DAOLogin();
		usu = loginDAO.usuario(login, senha);
		idUsuario = usu.getIdUsuario();
		
		DAOLog logDAO = new DAOLog();
		if (idUsuario > 0) {
			logDAO.gravaUsuario(idUsuario);
		}
		
		return String.valueOf(idUsuario);
	}
}
