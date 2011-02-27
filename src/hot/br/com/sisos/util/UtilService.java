package br.com.sisos.util;


import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sisos.entity.Estado;
import br.com.sisos.entity.Perfil;
import br.com.sisos.entity.TipoPessoa;
import br.com.sisos.services.BaseService;


@AutoCreate
@Scope(ScopeType.STATELESS)
@Name(value = "utilService")
public class UtilService extends BaseService {

	private static final long serialVersionUID = 1270830237254765325L;
	
	@Factory("perfilFactory")
	public Perfil[] getPerfis() {
		return Perfil.values();
	}	
	
	@Factory("estadoFactory")
	public Estado[] getEstados() {
		return Estado.values();
	}
	
	@Factory("tipoPessoaFactory")
	public TipoPessoa[] getTipoPessoa() {
		return TipoPessoa.values();
	}
	
}
