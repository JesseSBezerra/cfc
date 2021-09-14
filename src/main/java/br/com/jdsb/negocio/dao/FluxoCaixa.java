package br.com.jdsb.negocio.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface FluxoCaixa {

	public BigDecimal getVlMetaReceita();

	public BigDecimal getVlMetaDespesa();

	public LocalDateTime getDtCompetencia();

	public BigDecimal getVlMovimentoDespesa();

	public BigDecimal getVlMovimentoReceita();


}
