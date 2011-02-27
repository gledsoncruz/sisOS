package br.com.sisos.entity;

/**
 * @author Gledson Cruz
 * @version 1.0
 * @created 23-fev-2011 14:06:31
 */
public enum Perfil {
	ADMIN ("Administrador"),
	TECNICO ("Tecnico"),
	DEMO ("Demonstração");
	
	private String valor;

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	private Perfil(String valor) {
		this.valor = valor;
	}
	
	
	
}