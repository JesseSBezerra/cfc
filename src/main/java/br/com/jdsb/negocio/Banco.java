package br.com.jdsb.negocio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BANCO")
public class Banco implements Comparable<Banco>{

	@Id
	@Column(name="CD_BANCO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdBanco;

	@Column(name="DS_CODIGO_BANCO")
	private String dsCodigoBanco;

	@Column(name="NM_BANCO")
	private String nmBanco;

	@Column(name="VL_SALDO_ATUAL")
	private BigDecimal vlSaldoAtual;


	public Long getCdBanco() {
		return cdBanco;
	}

	public void setCdBanco(Long cdBanco) {
		this.cdBanco = cdBanco;
	}

	public String getNmBanco() {
		return nmBanco;
	}

	public void setNmBanco(String nmBanco) {
		this.nmBanco = nmBanco;
	}
	public String getDsCodigoBanco() {
		return dsCodigoBanco;
	}

	public void setDsCodigoBanco(String dsCodigoBanco) {
		this.dsCodigoBanco = dsCodigoBanco;
	}

	public BigDecimal getVlSaldoAtual() {
		return vlSaldoAtual;
	}

	public void setVlSaldoAtual(BigDecimal vlSaldoAtual) {
		this.vlSaldoAtual = vlSaldoAtual;
	}

	@Override
	public String toString() {
		return dsCodigoBanco + "-"+nmBanco;
	}

	@Override
	public int compareTo(Banco o) {
		if(this.getNmBanco().equals(o.nmBanco)){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dsCodigoBanco == null) ? 0 : dsCodigoBanco.hashCode());
		result = prime * result + ((nmBanco == null) ? 0 : nmBanco.hashCode());
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
		Banco other = (Banco) obj;
		if (dsCodigoBanco == null) {
			if (other.dsCodigoBanco != null)
				return false;
		} else if (!dsCodigoBanco.equals(other.dsCodigoBanco))
			return false;
		if (nmBanco == null) {
			if (other.nmBanco != null)
				return false;
		} else if (!nmBanco.equals(other.nmBanco))
			return false;
		return true;
	}







}
