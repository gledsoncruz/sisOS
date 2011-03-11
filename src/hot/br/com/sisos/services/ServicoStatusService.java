package br.com.sisos.services;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import br.com.sisos.entity.ServicoStatus;


@Name("servicoStatusService")
@Scope(ScopeType.STATELESS)
@AutoCreate
public class ServicoStatusService extends BaseService implements BaseInterfaceService<ServicoStatus, Serializable>{

	private static final long serialVersionUID = 1L;

	public void alterar(ServicoStatus obj) {
		hibernateSession.update(obj);
		
	}

	public ServicoStatus carregarPorId(Integer id) {
		return (ServicoStatus) hibernateSession.load(ServicoStatus.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<ServicoStatus> carregarTodos() {
		Criteria criteria = hibernateSession.createCriteria(ServicoStatus.class);
		criteria.addOrder(Order.asc("descricao"));
		return criteria.list();
	}

	public void excluir(ServicoStatus obj) {
		this.hibernateSession.delete(obj);
		
	}

	public void salvar(ServicoStatus obj) {
		this.hibernateSession.save(obj);
		
	}

	

}
