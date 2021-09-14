package br.com.jdsb.negocio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.jdsb.negocio.dao.FluxoCaixa;

public class FluxoCaixaTo {

	private LocalDateTime dtCompetencia;

	private BigDecimal vlSaldoAnterior;

	private BigDecimal vlMetaReceita;
	private BigDecimal vlMetaDespesa;

	private BigDecimal vlMovimentoReceita;
	private BigDecimal vlMovimentoDespesa;

	private BigDecimal vlMovimentoAcumulado;

	private BigDecimal vlPercentualDiferencaReceita;
	private BigDecimal vlPercentualDiferencaDespesa;

	private String dtCompetenciaFormatada;

	private String vlMetaReceitaFormatada;
	private String vlMetaDespesaFormatada;

	private String vlMovimentoReceitaFormatada;
	private String vlMovimentoDespesaFormatada;

	private String vlMovimentoAcumuladoFormatada;

	private String vlSaldoAnteriorFormatada;

	private String vlPercentualDiferencaReceitaFormatada;
	private String vlPercentualDiferencaDespesaFormatada;

	private BigDecimal vlResultadoMeta;
	private BigDecimal vlResultadoReceita;
	private BigDecimal vlPercentualDiferenca;

	private String vlResultadoMetaFormatada;
	private String vlResultadoReceitaFormatada;
	private String vlPercentualDiferencaFormatada;

	public FluxoCaixaTo() {
		// TODO Auto-generated constructor stub
	}

	public FluxoCaixaTo(FluxoCaixa fluxoCaixa){

	}

	public FluxoCaixaTo(FluxoCaixa fluxoCaixa,BigDecimal vlSaldoAnterior){
        this.vlSaldoAnterior = vlSaldoAnterior;
		atribuirFluxoCaixa(fluxoCaixa);

	}

	public void atribuirFluxoCaixa(FluxoCaixa fluxoCaixa){
		this.dtCompetencia = fluxoCaixa.getDtCompetencia();
	    this.vlMetaDespesa = fluxoCaixa.getVlMetaDespesa();
	    this.vlMetaReceita = fluxoCaixa.getVlMetaReceita();
	    this.vlMovimentoDespesa = fluxoCaixa.getVlMovimentoDespesa();
	    this.vlMovimentoReceita = fluxoCaixa.getVlMovimentoReceita();

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
	    this.dtCompetenciaFormatada = dtCompetencia.format(formatter);

	    this.vlMetaDespesaFormatada = NumberFormat.getCurrencyInstance().format(this.vlMetaDespesa);
	    this.vlMetaReceitaFormatada = NumberFormat.getCurrencyInstance().format(this.vlMetaReceita);

	    this.vlMovimentoDespesaFormatada = NumberFormat.getCurrencyInstance().format(this.vlMovimentoDespesa);
	    this.vlMovimentoReceitaFormatada = NumberFormat.getCurrencyInstance().format(this.vlMovimentoReceita);

	    try{
	    this.vlPercentualDiferencaReceita = (((this.vlMovimentoReceita.subtract(this.vlMetaReceita)).multiply(new BigDecimal(100))).divide(this.vlMetaReceita, 5, RoundingMode.HALF_UP));//.multiply(new BigDecimal(10));
	    }catch (Exception e) {
	    	 this.vlPercentualDiferencaReceita = BigDecimal.ZERO;
		}

	    try{
		    this.vlPercentualDiferencaDespesa = (((this.vlMetaDespesa.subtract(this.vlMovimentoDespesa)).multiply(new BigDecimal(100))).divide(this.vlMetaDespesa, 5, RoundingMode.HALF_UP));//.multiply(new BigDecimal(10));
		    }catch (Exception e) {
		    	 this.vlPercentualDiferencaDespesa = BigDecimal.ZERO;
			}

	    this.vlResultadoMeta = this.vlMetaReceita.subtract(this.vlMetaDespesa);
	    this.vlResultadoReceita = this.vlMovimentoReceita.subtract(this.vlMovimentoDespesa);

	    this.vlResultadoMetaFormatada = NumberFormat.getCurrencyInstance().format(this.vlResultadoMeta);
	    this.vlResultadoReceitaFormatada = NumberFormat.getCurrencyInstance().format(this.vlResultadoReceita);

	    try{
	        this.vlPercentualDiferenca = (((this.vlResultadoMeta.subtract(this.vlResultadoReceita)).multiply(new BigDecimal(100))).divide(this.vlResultadoMeta, 5, RoundingMode.HALF_UP));//.multiply(new BigDecimal(10));
		}catch (Exception e) {
		     this.vlPercentualDiferenca = BigDecimal.ZERO;
		}

	    DecimalFormat df = new java.text.DecimalFormat("###,####.####");

	    this.vlPercentualDiferencaReceitaFormatada = df.format(vlPercentualDiferencaReceita).concat("%");
	    this.vlPercentualDiferencaDespesaFormatada= df.format(vlPercentualDiferencaDespesa).concat("%");
	    this.vlPercentualDiferencaFormatada = df.format(vlPercentualDiferenca).concat("%");

	    if(this.vlSaldoAnterior==null){
	    	this.vlSaldoAnterior = BigDecimal.ZERO;
	    }
	    this.vlSaldoAnteriorFormatada = NumberFormat.getCurrencyInstance().format(this.vlSaldoAnterior);

	    this.vlMovimentoAcumulado = vlSaldoAnterior.add(this.vlMovimentoReceita).subtract(vlMovimentoDespesa);
	    this.vlMovimentoAcumuladoFormatada= NumberFormat.getCurrencyInstance().format(this.vlMovimentoAcumulado);

	}

	public LocalDateTime getDtCompetencia() {
		return dtCompetencia;
	}
	public void setDtCompetencia(LocalDateTime dtCompetencia) {
		this.dtCompetencia = dtCompetencia;
	}
	public BigDecimal getVlMetaReceita() {
		return vlMetaReceita;
	}
	public void setVlMetaReceita(BigDecimal vlMetaReceita) {
		this.vlMetaReceita = vlMetaReceita;
	}
	public BigDecimal getVlMetaDespesa() {
		return vlMetaDespesa;
	}
	public void setVlMetaDespesa(BigDecimal vlMetaDespesa) {
		this.vlMetaDespesa = vlMetaDespesa;
	}
	public BigDecimal getVlMovimentoDespesa() {
		return vlMovimentoDespesa;
	}
	public void setVlMovimentoDespesa(BigDecimal vlMovimentoDespesa) {
		this.vlMovimentoDespesa = vlMovimentoDespesa;
	}
	public BigDecimal getVlMovimentoReceita() {
		return vlMovimentoReceita;
	}
	public void setVlMovimentoReceita(BigDecimal vlMovimentoReceita) {
		this.vlMovimentoReceita = vlMovimentoReceita;
	}
	public BigDecimal getVlMovimentoAcumulado() {
		return vlMovimentoAcumulado;
	}
	public void setVlMovimentoAcumulado(BigDecimal vlMovimentoAcumulado) {
		this.vlMovimentoAcumulado = vlMovimentoAcumulado;
	}

	public BigDecimal getVlSaldoAnterior() {
		return vlSaldoAnterior;
	}
	public void setVlSaldoAnterior(BigDecimal vlSaldoAnterior) {
		this.vlSaldoAnterior = vlSaldoAnterior;
	}

	public BigDecimal getVlResultadoMeta() {
		return vlResultadoMeta;
	}

	public void setVlResultadoMeta(BigDecimal vlResultadoMeta) {
		this.vlResultadoMeta = vlResultadoMeta;
	}



	public BigDecimal getVlResultadoReceita() {
		return vlResultadoReceita;
	}

	public void setVlResultadoReceita(BigDecimal vlResultadoReceita) {
		this.vlResultadoReceita = vlResultadoReceita;
	}

	public String getDtCompetenciaFormatada() {
		return dtCompetenciaFormatada;
	}
	public String getVlMetaReceitaFormatada() {
		return vlMetaReceitaFormatada;
	}
	public String getVlMetaDespesaFormatada() {
		return vlMetaDespesaFormatada;
	}
	public String getVlMovimentoReceitaFormatada() {
		return vlMovimentoReceitaFormatada;
	}
	public String getVlMovimentoDespesaFormatada() {
		return vlMovimentoDespesaFormatada;
	}
	public String getVlMovimentoAcumuladoFormatada() {
		return vlMovimentoAcumuladoFormatada;
	}
	public String getVlSaldoAnteriorFormatada() {
		return vlSaldoAnteriorFormatada;
	}
	public String getVlPercentualDiferencaReceitaFormatada() {
		return vlPercentualDiferencaReceitaFormatada;
	}
	public String getVlPercentualDiferencaDespesaFormatada() {
		return vlPercentualDiferencaDespesaFormatada;
	}
	public String getVlResultadoMetaFormatada() {
		return vlResultadoMetaFormatada;
	}
	public String getVlResultadoReceitaFormatada() {
		return vlResultadoReceitaFormatada;
	}
	public String getVlPercentualDiferencaFormatada() {
		return vlPercentualDiferencaFormatada;
	}





}
