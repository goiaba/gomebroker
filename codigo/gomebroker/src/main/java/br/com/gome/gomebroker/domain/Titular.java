package br.com.gome.gomebroker.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

/**
 * The persistent class for the titular database table.
 * 
 */
@Entity
@Audited
public class Titular implements BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TITULAR_ID_GENERATOR", sequenceName = "TITULAR_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TITULAR_ID_GENERATOR")
	private Long id;

	private String assinatura;

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDesativacao;

	private String login;

	private String obs;

	private String parametros;

	private String senha;

	// bi-directional many-to-one association to Conta
	@OneToMany(mappedBy = "titular")
	private Set<Conta> contas;

	// bi-directional many-to-one association to Corretora
	@ManyToOne(fetch = FetchType.LAZY)
	private Corretora corretora;

	// bi-directional many-to-one association to Usuario
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	public Titular() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssinatura() {
		return this.assinatura;
	}

	public void setAssinatura(String assinatura) {
		this.assinatura = assinatura;
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

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getParametros() {
		return this.parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Conta> getContas() {
		return this.contas;
	}

	public void setContas(Set<Conta> contas) {
		this.contas = contas;
	}

	public Corretora getCorretora() {
		return this.corretora;
	}

	public void setCorretora(Corretora corretora) {
		this.corretora = corretora;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}