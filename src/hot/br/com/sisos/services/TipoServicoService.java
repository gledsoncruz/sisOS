package br.com.sisos.services;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sisos.entity.TipoServico;

@Name("tipoServicoService")
@Scope(ScopeType.STATELESS)
@AutoCreate
public class TipoServicoService extends BaseService implements BaseInterfaceService<TipoServico, Serializable>{

	private static final long serialVersionUID = 1L;

	public void alterar(TipoServico obj) {
		hibernateSession.update(obj);
		
	}

	public TipoServico carregarPorId(Integer id) {
		return (TipoServico) hibernateSession.load(TipoServico.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TipoServico> carregarTodos() {
		Criteria criteria = hibernateSession.createCriteria(TipoServico.class);
		criteria.addOrder(Order.asc("descricao"));
		return criteria.list();
	}

	public void excluir(TipoServico obj) {
		this.hibernateSession.delete(obj);
		
	}

	public void salvar(TipoServico obj) {
		this.hibernateSession.save(obj);
		
	}
	
	
	

}
