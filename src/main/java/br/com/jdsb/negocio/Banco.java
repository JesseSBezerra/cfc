package br.com.jdsb.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BANCO")
public class Banco {

	@Id
	@Column(name="CD_BANCO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdBanco;

	@Column(name="DS_CODIGO_BANCO")
	private String dsCodigoBanco;

	@Column(name="NM_BANCO")
	private String nmBanco;


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








}
