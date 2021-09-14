package br.com.jdsb.negocio.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.jdsb.negocio.CentroCusto;
import br.com.jdsb.negocio.ClienteFornecedor;

public interface ClienteFornecDao extends CrudRepository<ClienteFornecedor, Long> {
	List<ClienteFornecedor> findByTpClienteFornec(String tpClienteFornec);
	List<ClienteFornecedor> findAll();
}
