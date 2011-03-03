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

import br.com.sisos.entity.Cliente;


@Name("clienteService")
@Scope(ScopeType.STATELESS)
@AutoCreate
public class ClienteService extends BaseService implements BaseInterfaceService<Cliente, Serializable>{

	private static final long serialVersionUID = 1L;

	public void alterar(Cliente obj) {
		hibernateSession.update(obj);
		
	}

	public Cliente carregarPorId(Integer id) {
		return (Cliente) hibernateSession.load(Cliente.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> carregarTodos() {
		Criteria criteria = hibernateSession.createCriteria(Cliente.class);
		criteria.addOrder(Order.asc("nome"));
		return criteria.list();
	}

	public void excluir(Cliente obj) {
		this.hibernateSession.delete(obj);		
	}

	public void salvar(Cliente obj) {
		this.hibernateSession.save(obj);		
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> carregarClienteLike(String param) {
		Criteria criteria = hibernateSession.createCriteria(Cliente.class);
		criteria.add(Restrictions.like("nome", param, MatchMode.START).ignoreCase());
		return criteria.list();
	}

	

}
