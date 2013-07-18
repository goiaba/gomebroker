package br.com.gome.gomebroker.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.envers.Audited;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the portfolio database table.
 * 
 */
@Entity
@Audited
public class Portfolio implements Serializable, BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PORTFOLIO_ID_GENERATOR", sequenceName="PORTFOLIO_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PORTFOLIO_ID_GENERATOR")
	private Long id;

	@Column(updatable=false)
	private Timestamp dataCadastro;

	private Timestamp dataDesativacao;

	private String nome;

	private String obs;

	//bi-directional many-to-one association to Ordem
	@OneToMany(mappedBy="portfolio")
	private Set<Ordem> ordens;

	//bi-directional many-to-one association to Operador
	@ManyToOne(fetch=FetchType.LAZY)
	private Operador operador;

	//bi-directional many-to-one association to PortifolioAtivo
	@OneToMany(mappedBy="portfolio")
	private Set<PortifolioAtivo> portifolioAtivos;

    public Portfolio() {
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
	
	public Set<PortifolioAtivo> getPortifolioAtivos() {
		return this.portifolioAtivos;
	}

	public void setPortifolioAtivos(Set<PortifolioAtivo> portifolioAtivos) {
		this.portifolioAtivos = portifolioAtivos;
	}
	
}