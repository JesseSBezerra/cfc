package br.com.jdsb.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CENTRO_CUSTO")
public class CentroCusto implements Comparable<CentroCusto>{

	@Id
	@Column(name="CD_CENTRO_CUSTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdCentroCusto;

	@Column(name="DS_CENTRO_CUSTO")
	private String dsCentroCusto;

	@Column(name="SN_ATIVO")
	private String snAtivo;

	public Long getCdCentroCusto() {
		return cdCentroCusto;
	}

	public void setCdCentroCusto(Long cdCentroCusto) {
		this.cdCentroCusto = cdCentroCusto;
	}

	public String getDsCentroCusto() {
		return dsCentroCusto;
	}

	public void setDsCentroCusto(String dsCentroCusto) {
		this.dsCentroCusto = dsCentroCusto;
	}

	public String getSnAtivo() {
		return snAtivo;
	}

	public void setSnAtivo(String snAtivo) {
		this.snAtivo = snAtivo;
	}

	@Override
	public String toString() {
		return dsCentroCusto;
	}

	@Override
	public int compareTo(CentroCusto o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdCentroCusto == null) ? 0 : cdCentroCusto.hashCode());
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
		CentroCusto other = (CentroCusto) obj;
		if (cdCentroCusto == null) {
			if (other.cdCentroCusto != null)
				return false;
		} else if (!cdCentroCusto.equals(other.cdCentroCusto))
			return false;
		return true;
	}









}
