package br.com.gome.gomebroker.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.envers.Audited;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the operador database table.
 * 
 */
@Entity
@Audited
public class Operador implements Serializable, BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="OPERADOR_ID_GENERATOR", sequenceName="OPERADOR_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OPERADOR_ID_GENERATOR")
	private Long id;

	private String cota;

	@Column(updatable=false)
	private Timestamp dataCadastro;

	private Timestamp dataDesativacao;

	private String nome;

	private String obs;

	//bi-directional many-to-one association to Movimento
	@OneToMany(mappedBy="operador")
	private Set<Movimento> movimentos;

	//bi-directional many-to-one association to Conta
	@ManyToOne(fetch=FetchType.LAZY)
	private Conta conta;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario usuario;

	//bi-directional many-to-one association to Ordem
	@OneToMany(mappedBy="operador")
	private Set<Ordem> ordens;

	//bi-directional many-to-one association to Portfolio
	@OneToMany(mappedBy="operador")
	private Set<Portfolio> portfolios;

    public Operador() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCota() {
		return this.cota;
	}

	public void setCota(String cota) {
		this.cota = cota;
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

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Set<Movimento> getMovimentos() {
		return this.movimentos;
	}

	public void setMovimentos(Set<Movimento> movimentos) {
		this.movimentos = movimentos;
	}
	
	public Conta getConta() {
		return this.conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Set<Ordem> getOrdens() {
		return this.ordens;
	}

	public void setOrdens(Set<Ordem> ordens) {
		this.ordens = ordens;
	}
	
	public Set<Portfolio> getPortfolios() {
		return this.portfolios;
	}

	public void setPortfolios(Set<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}
	
}