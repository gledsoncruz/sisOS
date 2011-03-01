package br.com.sisos.action;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import br.com.sisos.entity.Cliente;
import br.com.sisos.entity.Servico;
import br.com.sisos.services.ClienteService;
import br.com.sisos.services.ServicoService;

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

	
	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public List<Cliente> complete(Object event) {
		String texto = event.toString();
		List<Cliente> clientes = this.clienteService.carregarAutoComplete(texto);
		return clientes;

	}

}
