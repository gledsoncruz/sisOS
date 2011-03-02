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

import br.com.sisos.entity.Tecnico;
import br.com.sisos.services.TecnicoService;
import br.com.sisos.util.Criptografia;

@Name(value = "tecnicoAction")
@Scope(ScopeType.PAGE)
public class TecnicoAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@In(create = true)
	@Out(scope = ScopeType.CONVERSATION, required = false)
	private Tecnico tecnico;
	@In
	private TecnicoService tecnicoService;

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	@Begin(join = true)
	@Restrict("#{!s:hasRole('demo')}")
	public void salvar(Tecnico tecnico) {

		/*if (Identity.instance().hasRole("demo")) {
			this.tecnico = new Tecnico();
			this.addMsgBundle(FacesMessage.SEVERITY_ERROR, "AuthorizationException");
		} else {*/
			tecnico.setSenha(Criptografia.criptografar(tecnico.getSenha()));

			tecnicoService.salvar(tecnico);
			this.tecnico = new Tecnico();
			this.addMsgBundle(FacesMessage.SEVERITY_INFO, "crudSaveSucess");
		//}

	}

	@End
	public void cancelar() {
		this.tecnico = new Tecnico();
	}


	@End
	public void alterar(Tecnico tecnico) {
		tecnico.setSenha(Criptografia.criptografar(tecnico.getSenha()));
		this.tecnicoService.alterar(tecnico);
		this.tecnico = new Tecnico();
		this.facesMessages.add("Alterado com sucesso");
		

	}

	public List<Tecnico> carregarTodos() {

		return this.tecnicoService.carregarTodos();

	}

	@Begin(join = true)
	public void selecionarTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	
	public void excluir(Tecnico tecnico) {
		this.tecnicoService.excluir(tecnico);
		this.tecnico = new Tecnico();
		this.addMsgBundle(FacesMessage.SEVERITY_INFO, "crudDeleteSucess");
	}

	public boolean verificarEmailExistente(String email) {
		if (this.tecnico.getId() == null) {
			return this.tecnicoService.carregarPorEmail(email);
		} else if (this.tecnico.getEmail().equals(email)) {
			return true;
		} else {
			return this.tecnicoService.carregarPorEmail(email);
		}
	}

	public boolean verificarLoginExistente(String login) {
		if (this.tecnico.getId() == null) {
			return this.tecnicoService.carregarPorLogin(login);
		} else if (this.tecnico.getLogin().equals(login)) {
			return true;
		} else {
			return this.tecnicoService.carregarPorLogin(login);
		}
	}

	public boolean verificarCPFExistente(String cpf) {
		if (this.tecnico.getId() == null) {
			return this.tecnicoService.carregarPorCPF(cpf);
		} else if (this.tecnico.getLogin().equals(cpf)) {
			return true;
		} else {
			return this.tecnicoService.carregarPorCPF(cpf);
		}
	}

	public Tecnico carregarPorId(Integer id) {
		return this.tecnicoService.carregarPorId(id);
	}

	public boolean verificarSenhaAtual(String login, String senha) {
		return this.tecnicoService.carregarPorSenhaAtual(login, senha);
	}
	
	public List<Tecnico> carregarGrupoTecnico(){
		return this.tecnicoService.carregarGrupoTecnico();
	}

}
