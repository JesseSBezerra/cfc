package br.com.jdsb.negocio.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.jdsb.negocio.CentroCusto;

public interface CentroCustoDao extends CrudRepository<CentroCusto, Long> {
	List<CentroCusto> findAll();
}
