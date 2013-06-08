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
 * The persistent class for the portfolio database table.
 * 
 */
@Entity
public class Portfolio implements BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PORTFOLIO_ID_GENERATOR", sequenceName="PORTFOLIO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PORTFOLIO_ID_GENERATOR")
	private Integer id;

	private Timestamp datacadastro;

	private Timestamp datadesativacao;

	private String nome;

	private String obs;

	//bi-directional many-to-one association to Ordem
	@OneToMany(mappedBy="portfolio")
	private Set<Ordem> ordens;

	//bi-directional many-to-one association to Operador
    @ManyToOne
	private Operador operador;

	//bi-directional many-to-one association to Relportifolioativo
	@OneToMany(mappedBy="portfolio")
	private Set<PortfolioAtivo> portifolioAtivos;

    public Portfolio() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set<Ordem> getOrdens() {
		return this.ordens;
	}

	public void setOrdens(Set<Ordem> ordens) {
		this.ordens = ordens;
	}
	
	public Operador getOperador() {
		return this.operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}
	
	public Set<PortfolioAtivo> getPortifolioAtivos() {
		return this.portifolioAtivos;
	}

	public void setPortifolioAtivos(Set<PortfolioAtivo> portifolioAtivos) {
		this.portifolioAtivos = portifolioAtivos;
	}
	
}