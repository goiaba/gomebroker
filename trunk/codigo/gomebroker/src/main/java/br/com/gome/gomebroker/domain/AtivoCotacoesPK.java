package br.com.gome.gomebroker.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the ativocotacoes database table.
 * 
 */
@Embeddable
public class AtivoCotacoesPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ativo_id")
	private Integer ativoId;

    @Temporal( TemporalType.TIMESTAMP)
	private java.util.Date data;

    public AtivoCotacoesPK() {
    }
	public Integer getAtivoId() {
		return this.ativoId;
	}
	public void setAtivoId(Integer ativoId) {
		this.ativoId = ativoId;
	}
	public java.util.Date getData() {
		return this.data;
	}
	public void setData(java.util.Date data) {
		this.data = data;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AtivoCotacoesPK)) {
			return false;
		}
		AtivoCotacoesPK castOther = (AtivoCotacoesPK)other;
		return 
			this.ativoId.equals(castOther.ativoId)
			&& this.data.equals(castOther.data);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ativoId.hashCode();
		hash = hash * prime + this.data.hashCode();
		
		return hash;
    }
}