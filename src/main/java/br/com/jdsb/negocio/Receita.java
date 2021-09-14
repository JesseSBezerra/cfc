package br.com.jdsb.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RECEITA")
public class Receita implements Comparable<Receita>{

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

	@Override
	public String toString() {
		return dsReceita;
	}

	@Override
	public int compareTo(Receita o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdReceita == null) ? 0 : cdReceita.hashCode());
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
		Receita other = (Receita) obj;
		if (cdReceita == null) {
			if (other.cdReceita != null)
				return false;
		} else if (!cdReceita.equals(other.cdReceita))
			return false;
		return true;
	}


}
