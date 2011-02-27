package br.com.sisos.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jboss.seam.annotations.Name;

/**
 * @author Gledson Cruz
 * @version 1.0
 * @created 23-fev-2011 14:06:31
 */
@Entity
@Table(name="SERVICO")
@Name(value="servico")
public class Servico implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
	private Integer id;
	@Column(name = "NUM_OS")
	private Integer numOrdemServico;
	@Column(name = "DTA_ATENDIMENTO")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dtaAtendimento;
	@Column(name = "DESC_EQUIPAMENTO")
	private String descrEquipamento;
	@Column(name = "MARCA")
	private String marca;
	@Column(name = "MODELO")
	private String modelo;
	@Column(name = "DTA_ENTRADA")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dtaEntrada;
	@Column(name = "DTA_SAIDA")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dtaSaida;
	@Column(name = "DEFEITO_DECLARADO")
	private String defeitoDeclarado;
	@Column(name = "DEFEITO_ENCONTRADO")
	private String defeitoEncontrado;
	@Column(name = "SOLUCAO")
	private String solucao;
	@Column(name = "VALOR_MAO_OBRA")
	private Double valorMaoObra;
	@Column(name = "OBSERVACAO")
	private String observacao;
	@JoinColumn(name = "TIPO_SERVICO_FK", referencedColumnName = "ID", nullable = false)
	@ManyToOne
	private TipoServico tipoServico;
	@JoinColumn(name = "SERVICO_STATUS_FK", referencedColumnName = "ID", nullable = false)
	@ManyToOne
	private ServicoStatus servicoStatus;
	@OneToMany(mappedBy="servico",fetch = FetchType.LAZY)
	private List<ServicoPeca> servicoPecas;
	@JoinColumn(name = "TECNICO_FK", referencedColumnName = "ID", nullable = false)
	@ManyToOne
	private Tecnico tecnico;
	@JoinColumn(name = "CLIENTE_FK", referencedColumnName = "ID", nullable = false)
	@ManyToOne
	private Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public List<ServicoPeca> getServicoPecas() {
		return servicoPecas;
	}

	public void setServicoPecas(List<ServicoPeca> servicoPecas) {
		this.servicoPecas = servicoPecas;
	}

	public Servico(){

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumOrdemServico() {
		return numOrdemServico;
	}

	public void setNumOrdemServico(Integer numOrdemServico) {
		this.numOrdemServico = numOrdemServico;
	}

	public Date getDtaAtendimento() {
		return dtaAtendimento;
	}

	public void setDtaAtendimento(Date dtaAtendimento) {
		this.dtaAtendimento = dtaAtendimento;
	}

	public String getDescrEquipamento() {
		return descrEquipamento;
	}

	public void setDescrEquipamento(String descrEquipamento) {
		this.descrEquipamento = descrEquipamento;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Date getDtaEntrada() {
		return dtaEntrada;
	}

	public void setDtaEntrada(Date dtaEntrada) {
		this.dtaEntrada = dtaEntrada;
	}

	public Date getDtaSaida() {
		return dtaSaida;
	}

	public void setDtaSaida(Date dtaSaida) {
		this.dtaSaida = dtaSaida;
	}

	public String getDefeitoDeclarado() {
		return defeitoDeclarado;
	}

	public void setDefeitoDeclarado(String defeitoDeclarado) {
		this.defeitoDeclarado = defeitoDeclarado;
	}

	public String getDefeitoEncontrado() {
		return defeitoEncontrado;
	}

	public void setDefeitoEncontrado(String defeitoEncontrado) {
		this.defeitoEncontrado = defeitoEncontrado;
	}

	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

	public Double getValorMaoObra() {
		return valorMaoObra;
	}

	public void setValorMaoObra(Double valorMaoObra) {
		this.valorMaoObra = valorMaoObra;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public ServicoStatus getServicoStatus() {
		return servicoStatus;
	}

	public void setServicoStatus(ServicoStatus servicoStatus) {
		this.servicoStatus = servicoStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servico other = (Servico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}