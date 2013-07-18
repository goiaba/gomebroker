package br.com.gome.gomebroker.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;


/**
 * The persistent class for the ativoofertas database table.
 * 
 */
@Entity
public class AtivoOfertas implements Serializable, BaseEntity<AtivoOfertasPK> {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AtivoOfertasPK id;

	private Integer quantidade;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ativo_id", referencedColumnName="id", insertable=false, updatable=false)
	private Ativo ativo;

    public AtivoOfertas() {
    }

	public AtivoOfertasPK getId() {
		return this.id;
	}

	public void setId(AtivoOfertasPK id) {
		this.id = id;
	}
	
	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Ativo getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}
	
}