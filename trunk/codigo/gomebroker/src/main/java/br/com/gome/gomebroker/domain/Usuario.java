package br.com.gome.gomebroker.domain;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
public class Usuario implements BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_ID_GENERATOR", sequenceName="USUARIO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_ID_GENERATOR")
	private Integer id;

	private Timestamp dataCadastro;

	private Timestamp dataDesativacao;

	private String email;

	private String nome;

	private String nomeCompleto;

	private String obs;

	private String senha;

	//bi-directional many-to-one association to Historico
	@OneToMany(mappedBy="usuario")
	private Set<Historico> historico;

	//bi-directional many-to-one association to Operador
	@OneToMany(mappedBy="usuario")
	private Set<Operador> operadores;

	//bi-directional many-to-one association to Ordemsolicitacoe
	@OneToMany(mappedBy="usuario")
	private Set<OrdemSolicitacao> solicitacoes;

	//bi-directional many-to-one association to Relusuarioperfil
	@OneToMany(mappedBy="usuario")
	private Set<UsuariopPerfil> usuarioPerfis;

	//bi-directional many-to-one association to Titular
	@OneToMany(mappedBy="usuario")
	private Set<Titular> titulares;

    public Usuario() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Timestamp getDataDesativacao() {
		return this.dataDesativacao;
	}

	public void setDataDesativacao(Timestamp dataDesativacao) {
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

	public Set<Historico> getHistorico() {
		return this.historico;
	}

	public void setHistorico(Set<Historico> historico) {
		this.historico = historico;
	}
	
	public Set<Operador> getOperadores() {
		return this.operadores;
	}

	public void setOperadores(Set<Operador> operadores) {
		this.operadores = operadores;
	}
	
	public Set<OrdemSolicitacao> getSolicitacoes() {
		return this.solicitacoes;
	}

	public void setSolicitacoes(Set<OrdemSolicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}
	
	public Set<UsuariopPerfil> getUsuarioPerfis() {
		return this.usuarioPerfis;
	}

	public void setUsuarioPerfis(Set<UsuariopPerfil> usuarioPerfis) {
		this.usuarioPerfis = usuarioPerfis;
	}
	
	public Set<Titular> getTitulares() {
		return this.titulares;
	}

	public void setTitulares(Set<Titular> titulares) {
		this.titulares = titulares;
	}
	
}