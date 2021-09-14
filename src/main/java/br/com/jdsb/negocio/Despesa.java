package br.com.jdsb.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DESPESA")
public class Despesa implements Comparable<Despesa>{

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

	@Override
	public int compareTo(Despesa o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return dsDespesa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdDespesa == null) ? 0 : cdDespesa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Despesa other = (Despesa) obj;
		if (cdDespesa == null) {
			if (other.cdDespesa != null)
				return false;
		} else if (!cdDespesa.equals(other.cdDespesa))
			return false;
		return true;
	}



}
