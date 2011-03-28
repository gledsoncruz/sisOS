package br.com.sisos.services;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sisos.entity.Peca;

@Name("pecaService")
@Scope(ScopeType.STATELESS)
@AutoCreate
public class PecaService extends BaseService implements BaseInterfaceService<Peca, Serializable>{

	private static final long serialVersionUID = 1L;

	public void alterar(Peca obj) {
		hibernateSession.update(obj);
		
	}

	public Peca carregarPorId(Integer id) {
		return (Peca) hibernateSession.load(Peca.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Peca> carregarTodos() {
		Criteria criteria = hibernateSession.createCriteria(Peca.class);
		criteria.addOrder(Order.asc("descricao"));
		return criteria.list();
	}

	public void excluir(Peca obj) {
		hibernateSession.delete(obj);
		
	}

	public void salvar(Peca obj) {
		hibernateSession.save(obj);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Peca> carregarPecaLike(String param) {
		Criteria criteria = hibernateSession.createCriteria(Peca.class);
		criteria.add(Restrictions.like("descricao", param, MatchMode.START).ignoreCase());
		return criteria.list();
	}
	

}
