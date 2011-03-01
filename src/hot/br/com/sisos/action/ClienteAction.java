package br.com.sisos.action;

import java.util.List;

import javax.faces.application.FacesMessage;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.security.Restrict;

import br.com.sisos.entity.Cliente;
import br.com.sisos.services.ClienteService;

@Name(value = "clienteAction")
@Scope(ScopeType.PAGE)
public class ClienteAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	@In(create = true)
	@Out(scope = ScopeType.CONVERSATION, required = false)
	private Cliente cliente;
	@In
	private ClienteService clienteService;
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Begin(join = true)
	@Restrict("#{!s:hasRole('demo')}")
	public void salvar(Cliente cliente){
		
		this.clienteService.salvar(cliente);
		this.cliente = new Cliente();
		this.addMsgBundle(FacesMessage.SEVERITY_INFO, "crudSaveSucess");
		
	}
	
	@End
	public void alterar(Cliente cliente){
		
		this.clienteService.alterar(cliente);
		this.cliente = new Cliente();
		this.addMsgBundle(FacesMessage.SEVERITY_INFO, "crudEditSucess");
	}
	
	@End
	public void cancelar(){
		this.cliente = new Cliente();		
	}
	
	public List<Cliente> carregarTodos() {
		return this.clienteService.carregarTodos();
	}
	
	@Begin(join = true)
	public void selecionarCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void excluir(Cliente cliente) {
		this.clienteService.excluir(cliente);
		this.cliente = new Cliente();
		this.facesMessages.add("Deletado com sucesso");
	}
	
	@Begin(join=true)
	public boolean converterPFJ(){
		
		if (this.cliente.getTipoPessoa().getValor().equals("Fisica")){
			return true;
		}
		
		return false;
		
	}
	

}
