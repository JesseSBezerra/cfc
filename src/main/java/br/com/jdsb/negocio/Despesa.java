package br.com.jdsb.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DESPESA")
public class Despesa {

	@Id
	@Column(name="CD_DESPESA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdDespesa;

	@Column(name="DS_DESPESA")
	private String dsDespesa;

	@Column(name="TP_DESPESA")
	private String tpDespesa;

	public Long getCdDespesa() {
		return cdDespesa;
	}

	public void setCdDespesa(Long cdDespesa) {
		this.cdDespesa = cdDespesa;
	}

	public String getDsDespesa() {
		return dsDespesa;
	}

	public void setDsDespesa(String dsDespesa) {
		this.dsDespesa = dsDespesa;
	}

	public String getTpDespesa() {
		return tpDespesa;
	}

	public void setTpDespesa(String tpDespesa) {
		this.tpDespesa = tpDespesa;
	}



}
