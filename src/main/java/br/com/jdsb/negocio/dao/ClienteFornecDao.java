package br.com.jdsb.negocio.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.jdsb.negocio.ClienteFornecedor;

public interface ClienteFornecDao extends CrudRepository<ClienteFornecedor, Long> {

}
