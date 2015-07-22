package bean;

import java.io.Serializable;
import java.util.List;



public class Comodo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idComodo;
	private String descricao;
	private List<Comodo> comodo;
	
	public Comodo(){}
	public Comodo(int codigo, String descricao) {
		super();
		this.idComodo = codigo;
		this.descricao = descricao;
		
	}
	
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
	
	public List<Comodo> getComodo()
	{
		return comodo;
	}
}
