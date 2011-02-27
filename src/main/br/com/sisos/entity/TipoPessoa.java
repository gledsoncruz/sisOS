package br.com.sisos.entity;

/**
 * @author Gledson Cruz
 * @version 1.0
 * @created 23-fev-2011 14:06:32
 */
public enum TipoPessoa {
	FISICA ("Fisica"),
	JURIDICA ("Juridica");
	
	private String valor;

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	private TipoPessoa(String valor) {
		this.valor = valor;
	}
}