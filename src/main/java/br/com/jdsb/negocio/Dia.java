package br.com.jdsb.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DIA")
public class Dia {

	@Id
	@Column(name="CD_DIA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdDia;

	@Column(name="VL_DIA",unique=true)
	private Integer vlDia;

	public Long getCdDia() {
		return cdDia;
	}

	public void setCdDia(Long cdDia) {
		this.cdDia = cdDia;
	}

	public Integer getVlDia() {
		return vlDia;
	}

	public void setVlDia(Integer vlDia) {
		this.vlDia = vlDia;
	}



}
