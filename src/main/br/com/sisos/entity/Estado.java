
package br.com.sisos.entity;

/**
 * @author Gledson Cruz
 *
 */
public enum Estado {
			
	AC("Acre"),
	AL("Alagoas"),
	AM("Amazonas"),
	AP("Amapa"),
	BA("Bahia"),
	CE("Ceara"),
	DF("Distrito Federal"),
	ES("Espirito Santo"),
	GO("Goias"),
	MA("Maranhao"),
	MG("Minas Gerais"),
	MS("Mato Grosso do Sul"),
	MT("Mato Grosso"),
	PA("Para"),
	PB("Paraiba"),
	PE("Pernambuco"),
	PI("Piaui"),
	PR("Parana"),
	RJ("Rio de Janeiro"),
	RN("Rio Grande do Norte"),
	RO("Rondonia"),
	RR("Roraima"),
	RS("Rio Grande do Sul"),
	SC("Santa Catarina"),
	SE("Sergipe"),
	SP("Sao Paulo"),
	TO("Tocantins");
	
	private String valor;

	private Estado(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
