package br.com.gome.gomebroker.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.envers.Audited;


/**
 * The persistent class for the relperfilpapel database table.
 * 
 */
@Entity(name="relperfilpapel")
@Audited
public class PerfilPapel implements Serializable, BaseEntity<Long> {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RELPERFILPAPEL_ID_GENERATOR", sequenceName="RELPERFILPAPEL_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RELPERFILPAPEL_ID_GENERATOR")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	private Papel papel;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	private Perfil perfil;

    public PerfilPapel() {
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
	
	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
}