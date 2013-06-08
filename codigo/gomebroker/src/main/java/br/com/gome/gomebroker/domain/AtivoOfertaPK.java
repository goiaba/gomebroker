package br.com.gome.gomebroker.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the ativoofertas database table.
 * 
 */
@Embeddable
public class AtivoOfertaPK implements BaseEntity {
	
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ativo_id")
	private Integer ativoId;

    @Temporal( TemporalType.TIMESTAMP)
	private java.util.Date data;

	private double valor;

    public AtivoOfertaPK() {
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
	public double getValor() {
		return this.valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AtivoOfertaPK)) {
			return false;
		}
		AtivoOfertaPK castOther = (AtivoOfertaPK)other;
		return 
			this.ativoId.equals(castOther.ativoId)
			&& this.data.equals(castOther.data)
			&& (this.valor == castOther.valor);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ativoId.hashCode();
		hash = hash * prime + this.data.hashCode();
		hash = hash * prime + ((int) (java.lang.Double.doubleToLongBits(this.valor) ^ (java.lang.Double.doubleToLongBits(this.valor) >>> 32)));
		
		return hash;
    }
}