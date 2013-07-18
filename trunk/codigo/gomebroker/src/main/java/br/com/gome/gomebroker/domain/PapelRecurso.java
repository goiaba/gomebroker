package br.com.gome.gomebroker.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.envers.Audited;


/**
 * The persistent class for the relpapelrecurso database table.
 * 
 */
@Entity
@Table(name="relpapelrecurso")
@Audited
public class PapelRecurso implements Serializable, BaseEntity<Long> {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RELPAPELRECURSO_ID_GENERATOR", sequenceName="RELPAPELRECURSO_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RELPAPELRECURSO_ID_GENERATOR")
	private Long id;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	private Papel papel;

	@ManyToOne(fetch=FetchType.LAZY)
	private Recurso recurso;

    public PapelRecurso() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Papel getPapel() {
		return this.papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}
	
	public Recurso getRecurso() {
		return this.recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}
	
}