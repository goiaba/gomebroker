package br.com.gome.gomebroker.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * The primary key class for the ativocotacoes database table.
 * 
 */
@Embeddable
public class AtivoCotacaoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="ativo_id", nullable=false, updatable=false)
	private Long ativo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false, updatable=false)
	private Date data;

    public AtivoCotacaoPK() {}
    
    public AtivoCotacaoPK(Long ativoId, Date data) {
    	this.ativo = ativoId;
    	this.data = data;
    }
    
	public Long getAtivo() {
		return this.ativo;
	}
	public void setAtivo(Long ativo) {
		this.ativo = ativo;
	}
	public java.util.Date getData() {
		return this.data;
	}
	public void setData(java.util.Date data) {
		this.data = data;
	}
	
	public boolean equals(Object o) {

		if ((null == o) || (o.getClass() != this.getClass())) {
			return false;
		}

		if (o == this) {
			return true;
		}

		AtivoCotacaoPK that = (AtivoCotacaoPK) o;

		return new EqualsBuilder()
				.append(this.ativo, that.ativo)
				.append(this.data, that.data)
				.isEquals();

	}

	public int hashCode() {

		return new HashCodeBuilder(17, 31)
				.append(this.ativo)
				.append(this.data)
				.toHashCode();

	}

}