package br.com.gome.gomebroker.util.envers.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import br.com.gome.gomebroker.util.envers.listener.CustomListener;

@Entity
@RevisionEntity(CustomListener.class)
@Table(name="detalhe_aud", schema="audit")
public class CustomEntity implements Serializable	 {
	
	private static final long serialVersionUID = 1L;

	@Id
	@RevisionNumber
	@SequenceGenerator(name="DETALHE_AUD_ID_GENERATOR", sequenceName="AUDIT.DETALHE_AUD_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DETALHE_AUD_ID_GENERATOR")
	@Column(name="verrev")
    private long id;
	
    @RevisionTimestamp
    private long dataModificacao;
    
    private String usuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Transient
	public Date getRevisionDate() {
	    return new Date(dataModificacao);
	}

	public long getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(long timestamp) {
		this.dataModificacao = timestamp;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public boolean equals(Object o) {

		if ((null == o) || (o.getClass() != this.getClass())) {
            return false;
        }

        if (o == this) {
            return true;
        }

        CustomEntity that = (CustomEntity) o;

        return new EqualsBuilder()
                    .append(this.id, that.id)
                    .append(this.dataModificacao, that.dataModificacao)
                    .append(this.usuario, that.usuario)
                    .isEquals();
	    
	}

	public int hashCode() {
		
		return new HashCodeBuilder(17,31)
        			.append(this.id)
        			.append(this.dataModificacao)
        			.append(this.usuario)
        			.toHashCode();
	    
	}

}
