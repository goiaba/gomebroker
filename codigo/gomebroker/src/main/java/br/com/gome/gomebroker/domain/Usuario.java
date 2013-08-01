package br.com.gome.gomebroker.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import br.com.gome.gomebroker.domain.security.UsuarioPapel;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Audited
public class Usuario implements BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "USUARIO_ID_GENERATOR", sequenceName = "USUARIO_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_ID_GENERATOR")
	private Long id;

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDesativacao;

	private String email;

	@Column
	private String nome;

	private String nomeCompleto;

	private String obs;

	private String senha;

	@OneToMany(mappedBy = "usuario")
	private List<Operador> operadores;

	@OneToMany(mappedBy = "usuario")
	private List<OrdemSolicitacoes> ordemSolicitacoes;

	@OneToMany(mappedBy = "usuario")
	private List<UsuarioPapel> usuarioPapel;

	@OneToMany(mappedBy = "usuario")
	private List<Titular> titulares;

	public Usuario() {
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Operador> getOperadores() {
		return this.operadores;
	}

	public void setOperadores(List<Operador> operadores) {
		this.operadores = operadores;
	}

	public List<OrdemSolicitacoes> getOrdemSolicitacoes() {
		return this.ordemSolicitacoes;
	}

	public void setOrdemSolicitacoes(List<OrdemSolicitacoes> ordemSolicitacoes) {
		this.ordemSolicitacoes = ordemSolicitacoes;
	}

	public List<UsuarioPapel> getUsuarioPerfil() {
		return this.usuarioPapel;
	}

	public void setUsuarioPerfil(List<UsuarioPapel> usuarioPapel) {
		this.usuarioPapel = usuarioPapel;
	}

	public List<Titular> getTitulares() {
		return this.titulares;
	}

	public void setTitulares(List<Titular> titulares) {
		this.titulares = titulares;
	}

	public boolean equals(Object o) {

		if ((null == o) || (o.getClass() != this.getClass())) {
			return false;
		}

		if (o == this) {
			return true;
		}

		Usuario that = (Usuario) o;

		return new EqualsBuilder().append(this.id, that.id)
				.append(this.nome, that.nome)
				.append(this.dataCadastro, that.dataCadastro)
				.append(this.dataDesativacao, that.dataDesativacao)
				.append(this.email, that.email)
				.append(this.nomeCompleto, that.nomeCompleto)
				.append(this.obs, that.obs).append(this.senha, that.senha)
				.isEquals();

	}

	public int hashCode() {

		return new HashCodeBuilder(17, 31).append(this.id).append(this.nome)
				.append(this.dataCadastro).append(this.dataDesativacao)
				.append(this.email).append(this.nomeCompleto).append(this.obs)
				.append(this.senha).toHashCode();

	}

}