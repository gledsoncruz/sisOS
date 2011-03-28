package br.com.sisos.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.security.Identity;

import br.com.sisos.entity.Cliente;
import br.com.sisos.entity.IdentityCustomized;
import br.com.sisos.entity.Peca;
import br.com.sisos.entity.Servico;
import br.com.sisos.entity.Tecnico;
import br.com.sisos.services.ClienteService;
import br.com.sisos.services.PecaService;
import br.com.sisos.services.ServicoService;
import br.com.sisos.services.TecnicoService;

@Name(value = "servicoAction")
@Scope(ScopeType.CONVERSATION)
public class ServicoAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@In(create = true)
	@Out(scope = ScopeType.CONVERSATION, required = false)
	private Servico servico;

	@In(create = true)
	@Out(scope = ScopeType.CONVERSATION, required = false)
	private Cliente cliente;

	private boolean orcamentoTecnicoOK = false;

	@In
	IdentityCustomized identity;

	@In
	private ServicoService servicoService;
	@In
	private ClienteService clienteService;
	@In
	private TecnicoService tecnicoService;
	@In
	private PecaService pecaService;

	public boolean isOrcamentoTecnicoOK() {
		return orcamentoTecnicoOK;
	}

	public void setOrcamentoTecnicoOK(boolean orcamentoTecnicoOK) {
		this.orcamentoTecnicoOK = orcamentoTecnicoOK;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public List<Peca> complete(Object event) {
		String texto = event.toString();
		List<Peca> pecas = this.pecaService.carregarPecaLike(texto);
		return pecas;

	}

	public List<Cliente> carregarTodosClientes() {
		return this.clienteService.carregarTodos();
	}

	@Begin(join = true)
	public String salvar(Servico servico) {

		if (!Identity.instance().hasRole("admin")) {
			Tecnico tec = new Tecnico();
			tec = this.tecnicoService.carregarPorId(this.identity.getUsuario()
					.getId());
			this.servico.setTecnico(tec);
		}
		this.servico.setNumOrdemServico(gerarNumOS());
		this.servico.setDtaAtendimento(new Date());
		this.servico.setCliente(this.cliente);
		this.servicoService.salvar(servico);
		this.servico = new Servico();
		this.addMsgBundle(FacesMessage.SEVERITY_INFO, "crudSaveSucess");
		return "home";
	}

	public List<Servico> carregarServicoNaoFinalizadoPorTecnico(Integer id) {

		Tecnico tec = new Tecnico();
		tec = this.tecnicoService.carregarPorId(id);
		List<Servico> servicos = tec.getServicos();
		List<Servico> servicosNaoFinalizados = new ArrayList<Servico>();
		for (Servico obj : servicos) {
			if (!obj.getServicoStatus().getDescricao().equalsIgnoreCase(
					"Finalizado")) {
				servicosNaoFinalizados.add(obj);
			}
		}
		return servicosNaoFinalizados;

	}

	@Begin(join = true)
	public void selecionarCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@End
	public List<Tecnico> carregarTodosTecnicos() {
		return this.tecnicoService.carregarGrupoTecnico();
	}

	public int gerarNumOS() {
		int ano = Calendar.getInstance().get(Calendar.YEAR);
		int seg = Calendar.getInstance().get(Calendar.SECOND);
		int mili = Calendar.getInstance().get(Calendar.MILLISECOND);
		String n = String.valueOf(ano) + String.valueOf(seg)
				+ String.valueOf(mili);
		return Integer.parseInt(n);
	}

	@End
	public String cancelar() {
		this.servico = new Servico();
		return "home";
	}

	@End
	public String alterar(Servico servico) {

		this.servicoService.alterar(servico);
		this.servico = new Servico();
		this.addMsgBundle(FacesMessage.SEVERITY_INFO, "crudEditSucess");
		return "home";
	}

	public boolean editarOrcamentoTecnico() {
		if (this.orcamentoTecnicoOK) {
			return false;
		}
		return true;
	}


}
