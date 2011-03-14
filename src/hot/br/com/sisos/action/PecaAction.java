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

import br.com.sisos.entity.Peca;
import br.com.sisos.services.PecaService;


@Name(value = "pecaAction")
@Scope(ScopeType.CONVERSATION)
public class PecaAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	@In(create = true)
	@Out(scope = ScopeType.CONVERSATION, required = false)
	private Peca peca;

	@In
	private PecaService pecaService;

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}
	
	@Begin (join = true)
	public void salvar(Peca peca){
		
		this.pecaService.salvar(peca);
		this.peca = new Peca();
		this.addMsgBundle(FacesMessage.SEVERITY_INFO, "crudSaveSucess");
		
	}
	
	public List<Peca> carregarTodos(){
		
		return this.pecaService.carregarTodos();
		
	}
	
	@End
	public void alterar(Peca peca) {

		this.pecaService.alterar(peca);
		this.peca = new Peca();
		this.addMsgBundle(FacesMessage.SEVERITY_INFO, "crudEditSucess");
	}
	
	@End
	public void cancelar() {
		this.peca = new Peca();
	}
	
	@Begin(join = true)
	public void selecionarPeca(Peca peca) {
		this.peca = peca;
	}
	
	public void excluir(Peca peca) {
		this.pecaService.excluir(peca);
		this.peca = new Peca();
		this.addMsgBundle(FacesMessage.SEVERITY_INFO, "crudDeleteSucess");
	}


}
