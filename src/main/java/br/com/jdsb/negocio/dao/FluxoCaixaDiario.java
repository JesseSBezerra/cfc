package br.com.jdsb.negocio.dao;

import java.math.BigDecimal;

public interface FluxoCaixaDiario {

	public Integer getVlDia();

	public BigDecimal getVlMovimentoReceita();

	public BigDecimal getVlMovimentoDespesa();


}
