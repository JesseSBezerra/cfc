package br.com.jdsb.negocio.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.jdsb.negocio.AnoBase;

public interface AnoBaseDao extends CrudRepository<AnoBase, Long> {

	List<AnoBase> findBySnLancadoOrderByVlAnoBase(String snLancado);
	AnoBase findByVlAnoBase(Integer vlAnoBase);

}
