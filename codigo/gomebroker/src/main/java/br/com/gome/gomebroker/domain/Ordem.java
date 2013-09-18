package br.com.gome.gomebroker.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.envers.Audited;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the ordem database table.
 * 
 */
@Entity
@Audited
public class Ordem implements Serializable, BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ORDEM_ID_GENERATOR", sequenceName="ORDEM_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDEM_ID_GENERATOR")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCorretora;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataValidade;

	private String idCorretora;

	private String mercado;

	private String obs;

	private String operacao;

	private String parametros;

	private double quantidade;

	private String situacao;

	private String tipo;

	private String ultimaMensagemCorretora;

	private double valor;

	@OneToMany(mappedBy="ordem")
	private Set<Movimento> movimentos;

	@ManyToOne(fetch=FetchType.LAZY)
	private Ativo ativo;

	@ManyToOne(fetch=FetchType.LAZY)
	private Operador operador;

	@ManyToOne(fetch=FetchType.LAZY)
	private Portfolio portfolio;

	@OneToMany(mappedBy="ordem")
	private Set<OrdemSolicitacoes> ordemSolicitacoes;

    public Ordem() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getDataCorretora() {
		return this.dataCorretora;
	}

	public void setDataCorretora(Date dataCorretora) {
		this.dataCorretora = dataCorretora;
	}

	public Date getDataValidade() {
		return this.dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getIdCorretora() {
		return this.idCorretora;
	}

	public void setIdCorretora(String idCorretora) {
		this.idCorretora = idCorretora;
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

	public String getUltimaMensagemCorretora() {
		return this.ultimaMensagemCorretora;
	}

	public void setUltimaMensagemCorretora(String ultimaMensagemCorretora) {
		this.ultimaMensagemCorretora = ultimaMensagemCorretora;
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
	
	public Set<OrdemSolicitacoes> getOrdemSolicitacoes() {
		return this.ordemSolicitacoes;
	}

	public void setOrdemSolicitacoes(Set<OrdemSolicitacoes> ordemSolicitacoes) {
		this.ordemSolicitacoes = ordemSolicitacoes;
	}
	
}