package br.com.sisos.action;

import java.util.List;

import javax.faces.application.FacesMessage;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.web.RequestParameter;

import br.com.sisos.entity.Cliente;
import br.com.sisos.entity.Servico;
import br.com.sisos.entity.Tecnico;
import br.com.sisos.services.ClienteService;
import br.com.sisos.services.ServicoService;
import br.com.sisos.services.TecnicoService;

@Name(value = "servicoAction")
@Scope(ScopeType.CONVERSATION)
public class ServicoAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@In(create = true)
	@Out(scope = ScopeType.CONVERSATION, required = false)
	private Servico servico;

	@In
	private ServicoService servicoService;
	@In
	private ClienteService clienteService;
	@In
	private TecnicoService tecnicoService;
	
	@RequestParameter
	private Integer idCliente;
	
	@RequestParameter
	private Integer idTecnico;
	
	

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdTecnico() {
		return idTecnico;
	}

	public void setIdTecnico(Integer idTecnico) {
		this.idTecnico = idTecnico;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public List<Cliente> complete(Object event) {
		String texto = event.toString();
		List<Cliente> clientes = this.clienteService.carregarClienteLike(texto);
		return clientes;

	}

	@Begin(join = true)
	public void salvar(Servico servico) {
		
		Tecnico tec = new Tecnico();
		Cliente cli = new Cliente();
		tec = this.tecnicoService.carregarPorId(this.idTecnico);		
		cli = this.clienteService.carregarPorId(this.idCliente);
		servico.setTecnico(tec);
		servico.setCliente(cli);
		this.servicoService.salvar(servico);
		this.servico = new Servico();
		this.addMsgBundle(FacesMessage.SEVERITY_INFO, "crudSaveSucess");
	}
	
	public List<Servico> carregarServicoPorTecnico(Integer id){
		
		Tecnico tec = new Tecnico();
		tec = this.tecnicoService.carregarPorId(id);
		return tec.getServicos();
		
	}

}
