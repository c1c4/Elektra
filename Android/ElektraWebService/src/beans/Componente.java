package beans;

import java.io.Serializable;

public class Componente implements Serializable{
	private static final long serialVersionUID = 1L;
	private int idComponente;
	private String descricao;
	public int getIdComponente() {
		return idComponente;
	}
	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
}
