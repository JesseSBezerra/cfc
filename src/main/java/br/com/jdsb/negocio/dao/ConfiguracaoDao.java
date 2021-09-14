package br.com.jdsb.negocio.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.jdsb.negocio.Configuracao;

public interface ConfiguracaoDao extends CrudRepository<Configuracao, Long> {

	Configuracao findByNmConfiguracao(String nmConfiguracao);
}
