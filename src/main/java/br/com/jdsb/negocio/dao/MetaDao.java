package br.com.jdsb.negocio.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.jdsb.negocio.AnoBase;
import br.com.jdsb.negocio.Meta;

public interface MetaDao extends CrudRepository<Meta, Long> {
	List<Meta> findAll();
	List<Meta> findByAnoBaseOrderByDtCompetencia(AnoBase anoBase);
}
