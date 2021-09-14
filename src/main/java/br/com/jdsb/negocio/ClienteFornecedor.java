package br.com.jdsb.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CLIENTE_FORNECEDOR")
public class ClienteFornecedor implements Comparable<ClienteFornecedor>{

	@Id
	@Column(name="CD_CLIENTE_FORNECEDOR")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cdClienteFornecedor;

	@Column(name="TP_CLIENTE_FORNEC")
	private String tpClienteFornec;

	@Column(name="NM_CLIENTE_FORNEC")
    private String nmClienteFornec;

    @Column(name="DS_CPF_CJPJ")
    private String dsCpfCnpj;

    @Column(name="DS_CELULAR")
    private String dsCelular;

    @Column(name="DS_TEL_FIXO")
    private String dsTelFixo;

    @Column(name="DS_EMAIL")
    private String dsEmail;

    @Column(name="DS_OBSERVACAO")
    private String dsObservacao;

	public Long getCdClienteFornecedor() {
		return cdClienteFornecedor;
	}

	public void setCdClienteFornecedor(Long cdClienteFornecedor) {
		this.cdClienteFornecedor = cdClienteFornecedor;
	}

	public String getTpClienteFornec() {
		return tpClienteFornec;
	}

	public void setTpClienteFornec(String tpClienteFornec) {
		this.tpClienteFornec = tpClienteFornec;
	}

	public String getNmClienteFornec() {
		return nmClienteFornec;
	}

	public void setNmClienteFornec(String nmClienteFornec) {
		this.nmClienteFornec = nmClienteFornec;
	}

	public String getDsCpfCnpj() {
		return dsCpfCnpj;
	}

	public void setDsCpfCnpj(String dsCpfCnpj) {
		this.dsCpfCnpj = dsCpfCnpj;
	}

	public String getDsCelular() {
		return dsCelular;
	}

	public void setDsCelular(String dsCelular) {
		this.dsCelular = dsCelular;
	}

	public String getDsTelFixo() {
		return dsTelFixo;
	}

	public void setDsTelFixo(String dsTelFixo) {
		this.dsTelFixo = dsTelFixo;
	}

	public String getDsEmail() {
		return dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

	public String getDsObservacao() {
		return dsObservacao;
	}

	public void setDsObservacao(String dsObservacao) {
		this.dsObservacao = dsObservacao;
	}

	@Override
	public String toString() {
		return nmClienteFornec;
	}

	@Override
	public int compareTo(ClienteFornecedor o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdClienteFornecedor == null) ? 0 : cdClienteFornecedor.hashCode());
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
		ClienteFornecedor other = (ClienteFornecedor) obj;
		if (cdClienteFornecedor == null) {
			if (other.cdClienteFornecedor != null)
				return false;
		} else if (!cdClienteFornecedor.equals(other.cdClienteFornecedor))
			return false;
		return true;
	}







}
