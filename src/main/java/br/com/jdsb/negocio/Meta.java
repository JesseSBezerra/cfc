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
@Table(name="META")
public class Meta {

	@Id
	@Column(name="CD_META")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdMeta;

	@ManyToOne
	@JoinColumn(name = "CD_ANO_BASE")
	private AnoBase anoBase;

	@Column(name="DT_COMPETENCIA",unique=true)
	private LocalDateTime dtCompetencia;

	@Column(name="VL_META_RECEITA")
	private BigDecimal vlMetaReceita;

	@Column(name="VL_META_DESPESA")
	private BigDecimal vlMetaDespesa;

	@Transient
	private String dtCompetenciaFormatado;

	@Transient
	private String vlMetaReceitaFormatado;

	@Transient
	private String vlMetaDespesaFormatado;

	@Transient
	private String vlAcumuladoFormatado;

	public AnoBase getAnoBase() {
		return anoBase;
	}

	public void setAnoBase(AnoBase anoBase) {
		this.anoBase = anoBase;
	}

	public Long getCdMeta() {
		return cdMeta;
	}

	public void setCdMeta(Long cdMeta) {
		this.cdMeta = cdMeta;
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

	public String getDtCompetenciaFormatado() {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
		 return dtCompetencia.format(formatter);
	}

	public String getVlMetaReceitaFormatado() {
		return NumberFormat.getCurrencyInstance().format(this.vlMetaReceita);
	}

	public String getVlMetaDespesaFormatado() {
		return NumberFormat.getCurrencyInstance().format(this.vlMetaDespesa);
	}

	public String getVlAcumuladoFormatado() {
		return NumberFormat.getCurrencyInstance().format(this.vlMetaReceita.subtract(this.vlMetaDespesa));
	}



}
