package br.com.jdsb.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CONFIGURACAO")
public class Configuracao {

	@Id
	@Column(name="CD_CONFIGURACAO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdConfiguracao;

	@Column(name="NM_CONFIGURACAO",unique=true)
	private String nmConfiguracao;

	@Column(name="DS_CONFIGURACAO")
	private String dsConfiguracao;

	@Column(name="VL_CONFIGURACAO")
	private String vlConfiguracao;

	public Long getCdConfiguracao() {
		return cdConfiguracao;
	}

	public void setCdConfiguracao(Long cdConfiguracao) {
		this.cdConfiguracao = cdConfiguracao;
	}

	public String getNmConfiguracao() {
		return nmConfiguracao;
	}

	public void setNmConfiguracao(String nmConfiguracao) {
		this.nmConfiguracao = nmConfiguracao;
	}

	public String getDsConfiguracao() {
		return dsConfiguracao;
	}

	public void setDsConfiguracao(String dsConfiguracao) {
		this.dsConfiguracao = dsConfiguracao;
	}

	public String getVlConfiguracao() {
		return vlConfiguracao;
	}

	public void setVlConfiguracao(String vlConfiguracao) {
		this.vlConfiguracao = vlConfiguracao;
	}





}
