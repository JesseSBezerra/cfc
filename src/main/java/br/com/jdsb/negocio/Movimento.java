package br.com.jdsb.negocio;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="MOVIMENTO")
public class Movimento {

	@Id
	@Column(name="CD_MOVIMENTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdMovimento;

	@Column(name="TP_MOVIMENTO")
	private String tpMovimento;

	@Column(name="DT_MOVIMENTO")
	private LocalDateTime dtMovimento;

	@Column(name="DS_DESTINATARIO")
	private String dsDestinatario;

	@ManyToOne
	@JoinColumn(name = "CD_BANCO")
	private Banco banco;

	@Column(name="DS_OBSERVACAO_MOVIMENTO")
	private String dsObservacaoMovimento;

	@ManyToOne
	@JoinColumn(name = "CD_BANCO_DESTINO")
	private Banco bancoDestino;

	@Column(name="VL_SALDO_ANTERIOR")
	private BigDecimal vlSaldoAnterior;

	@Column(name="VL_MOVIMENTO")
	private BigDecimal vlMovimento;

	@Column(name="DS_FORMA_PAGAMENTO")
	private String dsFormaPagamento;

	@ManyToOne
	@JoinColumn(name = "CD_CENTRO_CUSTO")
	private CentroCusto centroCusto;

	@ManyToOne
	@JoinColumn(name = "CD_CLIENTE_FORNECEDOR")
	private ClienteFornecedor clienteFornecedor;

	@ManyToOne
	@JoinColumn(name = "CD_RECEITA")
	private Receita receita;

	@ManyToOne
	@JoinColumn(name = "CD_DESPESA")
	private Despesa despesa;

	@Transient
	private String vlMovimentoFormatado;

	@Transient
	private String vlSaldoAnteriorFormatado;

	@Transient
	private String dtMovimentoFormatado;

	@Transient
	private String classificacao;


	public Long getCdMovimento() {
		return cdMovimento;
	}

	public void setCdMovimento(Long cdMovimento) {
		this.cdMovimento = cdMovimento;
	}

	public String getTpMovimento() {
		return tpMovimento;
	}

	public void setTpMovimento(String tpMovimento) {
		this.tpMovimento = tpMovimento;
	}

	public LocalDateTime getDtMovimento() {
		return dtMovimento;
	}

	public void setDtMovimento(LocalDateTime dtMovimento) {
		this.dtMovimento = dtMovimento;
	}

	public String getDsDestinatario() {
		return dsDestinatario;
	}

	public void setDsDestinatario(String dsDestinatario) {
		this.dsDestinatario = dsDestinatario;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public String getDsObservacaoMovimento() {
		return dsObservacaoMovimento;
	}

	public void setDsObservacaoMovimento(String dsObservacaoMovimento) {
		this.dsObservacaoMovimento = dsObservacaoMovimento;
	}

	public Banco getBancoDestino() {
		return bancoDestino;
	}

	public void setBancoDestino(Banco bancoDestino) {
		this.bancoDestino = bancoDestino;
	}

	public BigDecimal getVlMovimento() {
		return vlMovimento;
	}

	public void setVlMovimento(BigDecimal vlMovimento) {
		this.vlMovimento = vlMovimento;
	}

	public BigDecimal getVlSaldoAnterior() {
		return vlSaldoAnterior;
	}

	public void setVlSaldoAnterior(BigDecimal vlSaldoAnterior) {
		this.vlSaldoAnterior = vlSaldoAnterior;
	}

	public String getVlMovimentoFormatado() {
		return NumberFormat.getCurrencyInstance().format(this.vlMovimento);
	}

	public void setVlMovimentoFormatado(String vlMovimentoFormatado) {
		this.vlMovimentoFormatado = vlMovimentoFormatado;
	}

	public String getVlSaldoAnteriorFormatado() {
		return NumberFormat.getCurrencyInstance().format(this.vlSaldoAnterior);
	}

	public void setVlSaldoAnteriorFormatado(String vlSaldoAnteriorFormatado) {
		this.vlSaldoAnteriorFormatado = vlSaldoAnteriorFormatado;
	}

	public String getDtMovimentoFormatado() {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		 return dtMovimento.format(formatter);
	}

	public void setDtMovimentoFormatado(String dtMovimentoFormatado) {
		this.dtMovimentoFormatado = dtMovimentoFormatado;
	}

	public String getDsFormaPagamento() {
		return dsFormaPagamento;
	}

	public void setDsFormaPagamento(String dsFormaPagamento) {
		this.dsFormaPagamento = dsFormaPagamento;
	}

	public CentroCusto getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(CentroCusto centroCusto) {
		this.centroCusto = centroCusto;
	}

	public ClienteFornecedor getClienteFornecedor() {
		return clienteFornecedor;
	}

	public void setClienteFornecedor(ClienteFornecedor clienteFornecedor) {
		this.clienteFornecedor = clienteFornecedor;
	}

	public String getClassificacao() {
		if(getReceita()!=null){
			return getReceita().getTpReceita();
		}else if(getDespesa()!=null){
			return getDespesa().getTpDespesa();
		}else if (getTpMovimento().toLowerCase().equals("inventário")){
			return "Inventário";
		}
		return classificacao;
	}

	public void setClassificacao(String classficacao) {
		this.classificacao = classficacao;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}


}
