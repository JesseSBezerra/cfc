package br.com.jdsb.negocio.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.jdsb.negocio.Banco;

public interface BancoDao extends CrudRepository<Banco, Long> {

	List<Banco> findAll();

}
