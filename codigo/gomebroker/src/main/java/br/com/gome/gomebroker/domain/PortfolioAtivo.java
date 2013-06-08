package br.com.gome.gomebroker.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the relportifolioativo database table.
 * 
 */
@Entity(name="relPortifolioAtivo")
public class PortfolioAtivo implements BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RELPORTIFOLIOATIVO_ID_GENERATOR", sequenceName="RELPORTIFOLIOATIVO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RELPORTIFOLIOATIVO_ID_GENERATOR")
	private Integer id;

	private Timestamp datacadastro;

	private Timestamp datadesativacao;

	private String descricao;

	private String obs;

	private String participacao;

	//bi-directional many-to-one association to Ativo
    @ManyToOne
	private Ativo ativo;

	//bi-directional many-to-one association to Portfolio
    @ManyToOne
	@JoinColumn(name="porifolio_id")
	private Portfolio portfolio;

    public PortfolioAtivo() {
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

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getParticipacao() {
		return this.participacao;
	}

	public void setParticipacao(String participacao) {
		this.participacao = participacao;
	}

	public Ativo getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}
	
	public Portfolio getPortfolio() {
		return this.portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}
	
}