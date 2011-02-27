package br.com.sisos.services;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sisos.entity.Usuario;


@Name("loginService")
@Scope(ScopeType.STATELESS)
public class LoginService extends BaseService{

	private static final long serialVersionUID = 1L;
	
	public Usuario login(String login, String senha){
		
		Criteria criteria = hibernateSession.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("login", login));
		criteria.add(Restrictions.eq("senha", senha));
		
		if (criteria.uniqueResult() != null){
			return (Usuario)criteria.uniqueResult();
		}
		return null;
		
	}
	
	

}
