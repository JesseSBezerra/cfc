package br.com.jdsb.negocio.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.jdsb.negocio.Banco;

public interface BancoDao extends CrudRepository<Banco, Long> {

}
