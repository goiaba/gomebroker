package br.com.gome.gomebroker.domain;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the operador database table.
 * 
 */
@Entity
public class Operador implements BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="OPERADOR_ID_GENERATOR", sequenceName="OPERADOR_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OPERADOR_ID_GENERATOR")
	private Integer id;

	private String cota;

	private Timestamp datacadastro;

	private Timestamp datadesativacao;

	private String nome;

	private String obs;

	//bi-directional many-to-one association to Movimento
	@OneToMany(mappedBy="operador")
	private Set<Movimento> movimentos;

	//bi-directional many-to-one association to Conta
    @ManyToOne
	private Conta conta;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	private Usuario usuario;

	//bi-directional many-to-one association to Ordem
	@OneToMany(mappedBy="operador")
	private Set<Ordem> ordens;

	//bi-directional many-to-one association to Portfolio
	@OneToMany(mappedBy="operador")
	private Set<Portfolio> portfolios;

    public Operador() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCota() {
		return this.cota;
	}

	public void setCota(String cota) {
		this.cota = cota;
	}

	public Timestamp getDatacadastro() {
		return this.datacadastro;
	}

	public void setDatacadastro(Timestamp datacadastro) {
		this.datacadastro = datacadastro;
	}

	public Timestamp getDatadesativacao() {
		return this.datadesativacao;
	}

	public void setDatadesativacao(Timestamp datadesativacao) {
		this.datadesativacao = datadesativacao;
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