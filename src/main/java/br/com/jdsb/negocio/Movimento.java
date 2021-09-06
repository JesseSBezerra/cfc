package br.com.jdsb.negocio;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private Date dtMovimento;

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

	@Column(name="VL_MOVIMENTO")
	private BigDecimal vlMovimento;


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

	public Date getDtMovimento() {
		return dtMovimento;
	}

	public void setDtMovimento(Date dtMovimento) {
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





}
