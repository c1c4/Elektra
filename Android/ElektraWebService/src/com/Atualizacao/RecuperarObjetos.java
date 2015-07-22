package com.Atualizacao;

import java.util.List;

import beans.NivelAcesso;
import beans.Usuario;
import dao.DAONivelAcesso;

public class RecuperarObjetos {
	
	public List<NivelAcesso> recuperaUsuario(String idUsuario)
	{
		int i = Integer.parseInt(idUsuario);
		DAONivelAcesso acesso = new DAONivelAcesso();
		return acesso.nivelAcesso(i);
	}
}
