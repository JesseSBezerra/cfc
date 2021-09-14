package br.com.jdsb.negocio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FluxoCaixaTotalTo {

	private String tipoMovimentoCaixa;

	private BigDecimal vlTotalMeta;
	private BigDecimal vlTotalMovimento;

	private BigDecimal vlParcialMeta;
	private BigDecimal vlParcialMovimento;

	private BigDecimal vlPercentualRealizado;

	private BigDecimal vlPercentualParcial;

	public String getTipoMovimentoCaixa() {
		return tipoMovimentoCaixa;
	}
	public void setTipoMovimentoCaixa(String tipoMovimentoCaixa) {
		this.tipoMovimentoCaixa = tipoMovimentoCaixa;
	}
	public BigDecimal getVlTotalMeta() {
		if(this.vlTotalMeta==null){
			this.vlTotalMeta = BigDecimal.ZERO;
		}
		return vlTotalMeta;
	}

	public void setVlTotalMeta(BigDecimal vlTotalMeta) {
		this.vlTotalMeta = vlTotalMeta;
	}

	public BigDecimal getVlTotalMovimento() {
		if(this.vlTotalMovimento==null){
			this.vlTotalMovimento = BigDecimal.ZERO;
		}
		return vlTotalMovimento;
	}

	public void setVlTotalMovimento(BigDecimal vlTotalMovimento) {
		this.vlTotalMovimento = vlTotalMovimento;
	}
	public BigDecimal getVlParcialMeta() {
		if(this.vlParcialMeta==null){
			this.vlParcialMeta = BigDecimal.ZERO;
		}
		return vlParcialMeta;
	}

	public void setVlParcialMeta(BigDecimal vlParcialMeta) {
		this.vlParcialMeta = vlParcialMeta;
	}
	public BigDecimal getVlParcialMovimento() {
		if(this.vlParcialMovimento==null){
			this.vlParcialMovimento = BigDecimal.ZERO;
		}
		return vlParcialMovimento;
	}
	public void setVlParcialMovimento(BigDecimal vlParcialMovimento) {
		this.vlParcialMovimento = vlParcialMovimento;
	}


	public BigDecimal getVlPercentualRealizado() {
		try{
	        this.vlPercentualRealizado = (((this.vlTotalMeta.subtract(this.vlTotalMovimento)).multiply(new BigDecimal(100))).divide(this.vlTotalMeta, 5, RoundingMode.HALF_UP)).subtract(new BigDecimal(100));//.multiply(new BigDecimal(10));
		}catch (Exception e) {
		     this.vlPercentualRealizado = BigDecimal.ZERO;
		}
		return vlPercentualRealizado.abs();
	}
	public void setVlPercentualRealizado(BigDecimal vlPercentualRealizado) {
		this.vlPercentualRealizado = vlPercentualRealizado;
	}

	public BigDecimal getVlPercentualParcial() {
		try{
	        this.vlPercentualParcial = (((this.vlParcialMeta.subtract(this.vlParcialMovimento)).multiply(new BigDecimal(100))).divide(this.vlParcialMeta, 5, RoundingMode.HALF_UP)).subtract(new BigDecimal(100));//.multiply(new BigDecimal(10));
		}catch (Exception e) {
		     this.vlPercentualParcial = BigDecimal.ZERO;
		}
		return vlPercentualParcial.abs();
	}
	public void setVlPercentualParcial(BigDecimal vlPercentualParcial) {
		this.vlPercentualParcial = vlPercentualParcial;
	}

	public String getVlTotalMetaFormatada(){
		if(getTipoMovimentoCaixa().equals("Lucratividade")){
			DecimalFormat df = new java.text.DecimalFormat("###,####.####");
			return df.format(getVlTotalMeta()).concat("%");
		}
		return NumberFormat.getCurrencyInstance().format(getVlTotalMeta());
	}

	public String getVlParcialMetaFormatada(){
		return NumberFormat.getCurrencyInstance().format(getVlParcialMeta());
	}

	public String getVlTotalMovimentoFormatada(){
		if(getTipoMovimentoCaixa().equals("Lucratividade")){
			DecimalFormat df = new java.text.DecimalFormat("###,####.####");
			return df.format(getVlTotalMovimento()).concat("%");
		}
		return NumberFormat.getCurrencyInstance().format(getVlTotalMovimento());
	}

	public String getVlParcialMovimentoFormatada(){
		return NumberFormat.getCurrencyInstance().format(getVlParcialMovimento());
	}

	public String getVlPercentualRealizadoFormamada(){
		DecimalFormat df = new java.text.DecimalFormat("###,####.####");
		return df.format(getVlPercentualRealizado()).concat("%");
	}

	public String getVlPercentualDiferencaFormamada(){
		DecimalFormat df = new java.text.DecimalFormat("###,####.####");
		return df.format(getVlPercentualRealizado().subtract(new BigDecimal(100)).abs()).concat("%");
	}

	public String getVlPercentualParcialDiferencaFormamada(){
		DecimalFormat df = new java.text.DecimalFormat("###,####.####");
		return df.format(getVlPercentualParcial().subtract(new BigDecimal(100)).abs()).concat("%");
	}

	public String getVlPercentualMargemDeErroRealizadoFormamada(){
		DecimalFormat df = new java.text.DecimalFormat("###,####.####");
		if(getVlPercentualRealizado().intValue()<100){
			return df.format((new BigDecimal(100)).subtract(getVlPercentualRealizado())).concat("%");
		}
		return df.format(getVlPercentualRealizado()).concat("%");
	}

	public String getDsMensagemMargemErro(){
		String mensagemRetorno = "";
		if(getTipoMovimentoCaixa().equals("Receitas") && (getVlPercentualRealizado()).longValue() < 40 ){
			mensagemRetorno = "Avalie suas metas % de receita < 40";
		}else if (getTipoMovimentoCaixa().equals("Receitas") && (getVlPercentualRealizado()).longValue() > 40 ){
			mensagemRetorno = "Você esta indo bem % de receita > 40";
		}

		if(getTipoMovimentoCaixa().equals("Despesas") && (getVlPercentualRealizado()).longValue() < 40 ){
			mensagemRetorno = "Você esta indo bem % de despesa < 40";
		}else if (getTipoMovimentoCaixa().equals("Despesas") && (getVlPercentualRealizado()).longValue() > 40 ){
			mensagemRetorno = "Avalie suas metas % de despesa > 40";
		}

		if(getTipoMovimentoCaixa().equals("Lucro/Prejuízo") && (getVlPercentualRealizado()).longValue() > 40 ){
			mensagemRetorno = "Você esta indo bem % de margem de luco > 40";
		}else if (getTipoMovimentoCaixa().equals("Lucro/Prejuízo") && (getVlPercentualRealizado()).longValue() < 40 ){
			mensagemRetorno = "Avalie suas metas % de margem de luco < 40";
		}

		if(getTipoMovimentoCaixa().equals("Lucratividade") && (getVlPercentualRealizado()).longValue() > 40 ){
			mensagemRetorno = "Você esta indo bem % de margem de lucratividade > 40";
		}else if (getTipoMovimentoCaixa().equals("Lucratividade") && (getVlPercentualRealizado()).longValue() < 40 ){
			mensagemRetorno = "Avalie suas metas % de margem de lucratividade < 40";
		}

		return mensagemRetorno;
	}


	@Override
	public String toString() {
		return "FluxoCaixaTotalTo [tipoMovimentoCaixa=" + tipoMovimentoCaixa + ", vlTotalMeta=" + vlTotalMeta
				+ ", vlTotalMovimento=" + vlTotalMovimento + ", vlParcialMeta=" + vlParcialMeta
				+ ", vlParcialMovimento=" + vlParcialMovimento + ", vlPercentualRealizado=" + getVlPercentualRealizado()
				+ "]";
	}






}
