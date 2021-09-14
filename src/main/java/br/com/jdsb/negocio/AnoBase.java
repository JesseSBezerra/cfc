package br.com.jdsb.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ANO_BASE")
public class AnoBase implements Comparable<AnoBase>{

	@Id
	@Column(name="CD_ANO_BASE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdAnoBase;

	@Column(name="VL_ANO_BASE",unique=true)
	private Integer vlAnoBase;

	@Column(name="SN_LANCADO")
	private String snLancado;

	@Column(name="SN_ATIVO")
	private String snAtivo;

	public Long getCdAnoBase() {
		return cdAnoBase;
	}

	public void setCdAnoBase(Long cdAnoBase) {
		this.cdAnoBase = cdAnoBase;
	}

	public Integer getVlAnoBase() {
		return vlAnoBase;
	}

	public void setVlAnoBase(Integer vlAnoBase) {
		this.vlAnoBase = vlAnoBase;
	}

	public String getSnLancado() {
		return snLancado;
	}

	public void setSnLancado(String snLancado) {
		this.snLancado = snLancado;
	}

	public String getSnAtivo() {
		return snAtivo;
	}

	public void setSnAtivo(String snAtivo) {
		this.snAtivo = snAtivo;
	}

	@Override
	public String toString() {
		return vlAnoBase.toString();
	}

	@Override
	public int compareTo(AnoBase o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdAnoBase == null) ? 0 : cdAnoBase.hashCode());
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
		AnoBase other = (AnoBase) obj;
		if (cdAnoBase == null) {
			if (other.cdAnoBase != null)
				return false;
		} else if (!cdAnoBase.equals(other.cdAnoBase))
			return false;
		return true;
	}

}
