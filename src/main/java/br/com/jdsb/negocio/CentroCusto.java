package br.com.jdsb.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CENTRO_CUSTO")
public class CentroCusto {

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





}
