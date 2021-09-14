package br.com.jdsb.negocio.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.jdsb.negocio.Despesa;
import br.com.jdsb.negocio.Receita;

public interface ReceitaDao extends CrudRepository<Receita, Long> {

	List<Receita> findByTpReceita(String tpReceita);
	List<Receita> findAll();

}
