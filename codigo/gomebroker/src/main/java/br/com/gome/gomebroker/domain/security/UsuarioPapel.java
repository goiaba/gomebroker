package br.com.gome.gomebroker.domain.security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import br.com.gome.gomebroker.domain.BaseEntity;
import br.com.gome.gomebroker.domain.Usuario;

/**
 * The persistent class for the relusuariopapel database table.
 * 
 */
@Entity
@Audited
@Table(name = "sec_relUsuarioPapel")
public class UsuarioPapel implements BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "RELUSUARIOPAPEL_ID_GENERATOR", sequenceName = "SEC_RELUSUARIOPAPEL_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RELUSUARIOPAPEL_ID_GENERATOR")
	private Long id;

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDesativacao;

	@ManyToOne
	private Papel papel;

	@ManyToOne
	private Usuario usuario;

	@Column
	private Boolean padrao;

	public UsuarioPapel() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataDesativacao() {
		return this.dataDesativacao;
	}

	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

	public Papel getPapel() {
		return this.papel;
	}

	public void setPerfil(Papel papel) {
		this.papel = papel;
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

		UsuarioPapel that = (UsuarioPapel) o;

		return new EqualsBuilder().append(this.id, that.id)
				.append(this.padrao, that.padrao)
				.append(this.dataCadastro, that.dataCadastro)
				.append(this.dataDesativacao, that.dataDesativacao).isEquals();

	}

	public int hashCode() {

		return new HashCodeBuilder(17, 31)
				.append(this.id)
				.append(this.padrao)
				.append(this.dataCadastro)
				.append(this.dataDesativacao)
				.toHashCode();

	}

}