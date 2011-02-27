package br.com.sisos.services;

import java.io.Serializable;
import java.util.List;

public interface BaseInterfaceService<T, ID extends Serializable> {
	
	
	public void salvar(T obj);
	public void alterar(T obj);
	public void excluir(T obj);
	public T carregarPorId(Integer id);
	public List<T> carregarTodos();

}
