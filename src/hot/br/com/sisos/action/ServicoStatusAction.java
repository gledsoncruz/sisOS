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

import br.com.sisos.entity.ServicoStatus;
import br.com.sisos.services.ServicoStatusService;

@Name(value = "servicoStatusAction")
@Scope(ScopeType.CONVERSATION)
public class ServicoStatusAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@In(create = true)
	@Out(scope = ScopeType.CONVERSATION, required = false)
	private ServicoStatus servicoStatus;

	@In
	private ServicoStatusService servicoStatusService;

	public ServicoStatus getServicoStatus() {
		return servicoStatus;
	}

	public void setServicoStatus(ServicoStatus servicoStatus) {
		this.servicoStatus = servicoStatus;
	}

	@Begin(join = true)
	public void salvar(ServicoStatus servicoStatus) {

		this.servicoStatusService.salvar(servicoStatus);
		this.servicoStatus = new ServicoStatus();
		this.addMsgBundle(FacesMessage.SEVERITY_INFO, "crudSaveSucess");
	}

	public List<ServicoStatus> carregarTodos() {
		return servicoStatusService.carregarTodos();
	}

	@End
	public void alterar(ServicoStatus servicoStatus) {

		this.servicoStatusService.alterar(servicoStatus);
		this.servicoStatus = new ServicoStatus();
		this.addMsgBundle(FacesMessage.SEVERITY_INFO, "crudEditSucess");
	}

	@End
	public void cancelar() {
		this.servicoStatus = new ServicoStatus();
	}

	@Begin(join = true)
	public void selecionarServicoStatus(ServicoStatus servicoStatus) {
		this.servicoStatus = servicoStatus;
	}

	public void excluir(ServicoStatus servicoStatus) {
		this.servicoStatusService.excluir(servicoStatus);
		this.servicoStatus = new ServicoStatus();
		this.addMsgBundle(FacesMessage.SEVERITY_INFO, "crudDeleteSucess");
	}

}
