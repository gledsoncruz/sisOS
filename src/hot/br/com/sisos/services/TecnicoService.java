package br.com.sisos.services;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sisos.entity.Tecnico;
import br.com.sisos.util.Criptografia;

@Name("tecnicoService")
@Scope(ScopeType.STATELESS)
@AutoCreate
public class TecnicoService extends BaseService implements BaseInterfaceService<Tecnico, Serializable>{

	private static final long serialVersionUID = 1L;

	public void alterar(Tecnico obj) {
		hibernateSession.update(obj);		
		
	}

	public Tecnico carregarPorId(Integer id) {
		return (Tecnico) hibernateSession.load(Tecnico.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Tecnico> carregarTodos() {
		Criteria criteria = hibernateSession.createCriteria(Tecnico.class);
		criteria.addOrder(Order.asc("nome"));
		return criteria.list();
	}

	public void excluir(Tecnico obj) {
		hibernateSession.delete(obj);
		
	}

	public void salvar(Tecnico obj) {
		hibernateSession.save(obj);
		
	}
	
	public Tecnico carregarPorEmail(Tecnico tecnico) {
		Criteria criteria = hibernateSession.createCriteria(Tecnico.class);
		criteria.add(Restrictions.eq("email", tecnico.getEmail()));
		return (Tecnico) criteria.uniqueResult();
	}
	
	public boolean carregarPorEmail(String email) {
		Criteria criteria = hibernateSession.createCriteria(Tecnico.class);
		criteria.add(Restrictions.eq("email", email));
		if (criteria.list().size() > 0) {
			return false;
		}
		return true;
	}
	
	public Tecnico carregarPorCPF(Tecnico tecnico) {
		Criteria criteria = hibernateSession.createCriteria(Tecnico.class);
		criteria.add(Restrictions.eq("cpf", tecnico.getCpf()));
		return (Tecnico) criteria.uniqueResult();
	}

	public boolean carregarPorCPF(String cpf) {
		Criteria criteria = hibernateSession.createCriteria(Tecnico.class);
		criteria.add(Restrictions.eq("cpf", cpf));
		if (criteria.list().size() > 0) {
			return false;
		}
		return true;
	}

	public boolean carregarPorLogin(String login) {
		Criteria criteria = hibernateSession.createCriteria(Tecnico.class);
		criteria.add(Restrictions.eq("login", login));
		if (criteria.list().size() > 0) {
			return false;
		}
		return true;
	}

	public boolean carregarPorSenhaAtual(String login, String senha) {
		Criteria criteria = hibernateSession.createCriteria(Tecnico.class);
		criteria.add(Restrictions.eq("login", login));
		criteria.add(Restrictions.eq("senha", Criptografia.criptografar(senha)));
		if (criteria.uniqueResult() != null) {
			return true;
		}
		return false;
	}

}
