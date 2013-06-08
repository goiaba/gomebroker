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
 * The persistent class for the ordem database table.
 * 
 */
@Entity
public class Ordem implements BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ORDEM_ID_GENERATOR", sequenceName="ORDEM_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDEM_ID_GENERATOR")
	private Integer id;

	private Timestamp data;

	private Timestamp datacorretora;

	private Timestamp datavalidade;

	private String idcorretora;

	private String mercado;

	private String obs;

	private String operacao;

	private String parametros;

	private double quantidade;

	private String situacao;

	private String tipo;

	private String ultimamensagemcorretora;

	private double valor;

	//bi-directional many-to-one association to Movimento
	@OneToMany(mappedBy="ordem")
	private Set<Movimento> movimentos;

	//bi-directional many-to-one association to Ativo
    @ManyToOne
	private Ativo ativo;

	//bi-directional many-to-one association to Operador
    @ManyToOne
	private Operador operador;

	//bi-directional many-to-one association to Portfolio
    @ManyToOne
	private Portfolio portfolio;

	//bi-directional many-to-one association to Ordemsolicitacoe
	@OneToMany(mappedBy="ordem")
	private Set<OrdemSolicitacao> solicitacoes;

    public Ordem() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getData() {
		return this.data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public Timestamp getDatacorretora() {
		return this.datacorretora;
	}

	public void setDatacorretora(Timestamp datacorretora) {
		this.datacorretora = datacorretora;
	}

	public Timestamp getDatavalidade() {
		return this.datavalidade;
	}

	public void setDatavalidade(Timestamp datavalidade) {
		this.datavalidade = datavalidade;
	}

	public String getIdcorretora() {
		return this.idcorretora;
	}

	public void setIdcorretora(String idcorretora) {
		this.idcorretora = idcorretora;
	}

	public String getMercado() {
		return this.mercado;
	}

	public void setMercado(String mercado) {
		this.mercado = mercado;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getOperacao() {
		return this.operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getParametros() {
		return this.parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
	}

	public double getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUltimamensagemcorretora() {
		return this.ultimamensagemcorretora;
	}

	public void setUltimamensagemcorretora(String ultimamensagemcorretora) {
		this.ultimamensagemcorretora = ultimamensagemcorretora;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Set<Movimento> getMovimentos() {
		return this.movimentos;
	}

	public void setMovimentos(Set<Movimento> movimentos) {
		this.movimentos = movimentos;
	}
	
	public Ativo getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}
	
	public Operador getOperador() {
		return this.operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}
	
	public Portfolio getPortfolio() {
		return this.portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}
	
	public Set<OrdemSolicitacao> getSolicitacoes() {
		return this.solicitacoes;
	}

	public void setSolicitacoes(Set<OrdemSolicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}
	
}