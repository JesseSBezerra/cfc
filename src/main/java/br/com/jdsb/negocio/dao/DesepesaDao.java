package br.com.jdsb.negocio.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.jdsb.negocio.ClienteFornecedor;
import br.com.jdsb.negocio.Despesa;
import br.com.jdsb.negocio.Receita;

public interface DesepesaDao extends CrudRepository<Despesa, Long> {
	List<Despesa> findByTpDespesa(String tpDespesa);
	List<Despesa> findAll();
}
