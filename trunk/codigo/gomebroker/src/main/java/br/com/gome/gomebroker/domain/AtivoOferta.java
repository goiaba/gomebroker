package br.com.gome.gomebroker.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the ativoofertas database table.
 * 
 */
@Entity
@Table(name="ativoofertas")
public class AtivoOferta implements BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AtivoOfertaPK id;

	private Integer quantidade;

	//bi-directional many-to-one association to Ativo
    @ManyToOne
	private Ativo ativo;

    public AtivoOferta() {
    }

	public AtivoOfertaPK getId() {
		return this.id;
	}

	public void setId(AtivoOfertaPK id) {
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