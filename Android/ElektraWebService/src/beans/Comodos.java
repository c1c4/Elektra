package beans;

import java.io.Serializable;

public class Comodos implements Serializable{
	private static final long serialVersionUID = 1L;
	private int idComodo;
	private String descricao;
	public int getIdComodo() {
		return idComodo;
	}
	public void setIdComodo(int idComodo) {
		this.idComodo = idComodo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
