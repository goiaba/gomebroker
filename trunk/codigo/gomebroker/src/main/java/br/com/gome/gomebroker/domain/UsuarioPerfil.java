package br.com.gome.gomebroker.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;


/**
 * The persistent class for the relusuarioperfil database table.
 * 
 */
@Entity
@Table(name="relusuarioperfil")
@Audited
public class UsuarioPerfil implements BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RELUSUARIOPERFIL_ID_GENERATOR", sequenceName="RELUSUARIOPERFIL_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RELUSUARIOPERFIL_ID_GENERATOR")
	private Long id;

	@Column(updatable=false)
	private Timestamp dataCadastro;

	private Timestamp dataValidade;

	private Timestamp dataVigencia;

	@ManyToOne
	private Perfil perfil;

	@ManyToOne
	private Usuario usuario;
	
	@Column
	private Boolean padrao;

    public UsuarioPerfil() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Timestamp getDataValidade() {
		return this.dataValidade;
	}

	public void setDataValidade(Timestamp dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Timestamp getDataVigencia() {
		return this.dataVigencia;
	}

	public void setDataVigencia(Timestamp dataVigencia) {
		this.dataVigencia = dataVigencia;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean isPadrao() {
		return padrao;
	}

	public void setPadrao(Boolean padrao) {
		this.padrao = padrao;
	}
	
	public boolean equals(Object o) {

		if ((null == o) || (o.getClass() != this.getClass())) {
            return false;
        }

        if (o == this) {
            return true;
        }

        UsuarioPerfil that = (UsuarioPerfil) o;

        return new EqualsBuilder()
                    .append(this.id, that.id)
                    .append(this.padrao, that.padrao)
                    .append(this.dataCadastro, that.dataCadastro)
                    .append(this.dataValidade, that.dataValidade)
                    .append(this.dataVigencia, that.dataVigencia)
                    .isEquals();
	    
	}

	public int hashCode() {
		
		return new HashCodeBuilder(17,31)
			        .append(this.id)
			        .append(this.padrao)
			        .append(this.dataCadastro)
			        .append(this.dataValidade)
			        .append(this.dataVigencia)
        			.toHashCode();
	    
	}
	
}