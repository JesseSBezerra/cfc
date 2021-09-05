package br.com.jdsb.negocio.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.jdsb.negocio.Despesa;

public interface DesepesaDao extends CrudRepository<Despesa, Long> {

}
