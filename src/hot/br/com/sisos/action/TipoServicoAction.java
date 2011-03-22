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

import br.com.sisos.entity.TipoServico;
import br.com.sisos.services.TipoServicoService;

@Name(value = "tipoServicoAction")
@Scope(ScopeType.CONVERSATION)
public class TipoServicoAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@In(create = true)
	@Out(scope = ScopeType.CONVERSATION, required = false)
	private TipoServico tipoServico;

	@In
	private TipoServicoService tipoServicoService;

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	@Begin (join = true)
	public void salvar(TipoServico tipoServico) {

		this.tipoServicoService.salvar(tipoServico);
		this.tipoServico = new TipoServico();
		this.addMsgBundle(FacesMessage.SEVERITY_INFO, "crudSaveSucess");

	}

	public List<TipoServico> carregarTodos() {
		return this.tipoServicoService.carregarTodos();
	}

	@End
	public void alterar(TipoServico tipoServico) {

		this.tipoServicoService.alterar(tipoServico);
		this.tipoServico = new TipoServico();
		this.addMsgBundle(FacesMessage.SEVERITY_INFO, "crudEditSucess");
	}

	@End
	public void cancelar() {
		this.tipoServico = new TipoServico();
	}

	@Begin(join = true)
	public void selecionarTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public void excluir(TipoServico tipoServico) {
		this.tipoServicoService.excluir(tipoServico);
		this.tipoServico = new TipoServico();
		this.addMsgBundle(FacesMessage.SEVERITY_INFO, "crudDeleteSucess");
	}

}
