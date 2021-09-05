package br.com.jdsb.negocio.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.jdsb.negocio.Receita;

public interface ReceitaDao extends CrudRepository<Receita, Long> {

}
