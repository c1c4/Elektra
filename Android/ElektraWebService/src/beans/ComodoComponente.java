package beans;

import java.io.Serializable;
import java.sql.Date;

public class ComodoComponente implements Serializable{
	private static final long serialVersionUID = 1L;
	private int idComodoComponente;
	private Componente componentes;
	private Comodos comodo;
	private int status;
	private int sensor;
	private int temporizador;
	private Date dataInicial;
	private Date dataFinal;
	private int porta;
	
	public int getIdComodoComponente() {
		return idComodoComponente;
	}
	public void setIdComodoComponente(int idComodoComponente) {
		this.idComodoComponente = idComodoComponente;
	}
	public Componente getComponentes() {
		return componentes;
	}
	public void setComponentes(Componente componentes) {
		this.componentes = componentes;
	}
	public Comodos getComodo() {
		return comodo;
	}
	public void setComodo(Comodos comodo) {
		this.comodo = comodo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSensor() {
		return sensor;
	}
	public void setSensor(int sensor) {
		this.sensor = sensor;
	}
	public int getTemporizador() {
		return temporizador;
	}
	public void setTemporizador(int temporizador) {
		this.temporizador = temporizador;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public int getPorta() {
		return porta;
	}
	public void setPorta(int porta) {
		this.porta = porta;
	}
}
