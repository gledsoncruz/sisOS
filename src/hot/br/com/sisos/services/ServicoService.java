package br.com.sisos.services;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sisos.entity.Servico;

@Name("servicoService")
@Scope(ScopeType.STATELESS)
@AutoCreate
public class ServicoService extends BaseService implements BaseInterfaceService<Servico, Serializable>{

	private static final long serialVersionUID = 1L;

	public void alterar(Servico obj) {
		hibernateSession.update(obj);
		
	}

	public Servico carregarPorId(Integer id) {
		return (Servico) hibernateSession.load(Servico.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Servico> carregarTodos() {
		Criteria criteria = hibernateSession.createCriteria(Servico.class);
		criteria.addOrder(Order.asc("dtaEntrada"));
		return criteria.list();
	}

	public void excluir(Servico obj) {
		hibernateSession.delete(obj);
		
	}

	public void salvar(Servico obj) {
		hibernateSession.save(obj);
		
	}
	
	

}
