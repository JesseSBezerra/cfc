package br.com.jdsb.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RECEITA")
public class Receita {

	@Id
	@Column(name="CD_RECEITA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdReceita;

	@Column(name="DS_RECEITA")
	private String dsReceita;

	@Column(name="TP_RECEITA")
	private String tpReceita;

	public Long getCdReceita() {
		return cdReceita;
	}

	public void setCdReceita(Long cdReceita) {
		this.cdReceita = cdReceita;
	}

	public String getDsReceita() {
		return dsReceita;
	}

	public void setDsReceita(String dsReceita) {
		this.dsReceita = dsReceita;
	}

	public String getTpReceita() {
		return tpReceita;
	}

	public void setTpReceita(String tpReceita) {
		this.tpReceita = tpReceita;
	}



}
