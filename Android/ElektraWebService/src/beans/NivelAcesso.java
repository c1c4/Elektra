package beans;

import java.io.Serializable;

public class NivelAcesso implements Serializable{
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public ComodoComponente getComodoComponente() {
		return comodoComponente;
	}
	public void setComodoComponente(ComodoComponente comodoComponente) {
		this.comodoComponente = comodoComponente;
	}
	private ComodoComponente comodoComponente;
}
