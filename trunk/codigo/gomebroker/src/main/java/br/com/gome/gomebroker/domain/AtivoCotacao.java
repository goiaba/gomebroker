package br.com.gome.gomebroker.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the ativocotacoes database table.
 * 
 */
@Entity
@Table(name="ativocotacoes")
public class AtivoCotacao implements BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AtivoCotacaoPK id;

	private double abertura;

	private Boolean intradiario;

	private double maximo;

	private double medio;

	private double melhorofertacompra;

	private double melhorofertavenda;

	private double minimo;

	private Integer qtdnegocios;

	private Integer qtdpapeis;

	private double ultimo;

	private double variacao;

	private double volume;

	//bi-directional many-to-one association to Ativo
    @ManyToOne
	private Ativo ativo;

    public AtivoCotacao() {
    }

	public AtivoCotacaoPK getId() {
		return this.id;
	}

	public void setId(AtivoCotacaoPK id) {
		this.id = id;
	}
	
	public double getAbertura() {
		return this.abertura;
	}

	public void setAbertura(double abertura) {
		this.abertura = abertura;
	}

	public Boolean getIntradiario() {
		return this.intradiario;
	}

	public void setIntradiario(Boolean intradiario) {
		this.intradiario = intradiario;
	}

	public double getMaximo() {
		return this.maximo;
	}

	public void setMaximo(double maximo) {
		this.maximo = maximo;
	}

	public double getMedio() {
		return this.medio;
	}

	public void setMedio(double medio) {
		this.medio = medio;
	}

	public double getMelhorofertacompra() {
		return this.melhorofertacompra;
	}

	public void setMelhorofertacompra(double melhorofertacompra) {
		this.melhorofertacompra = melhorofertacompra;
	}

	public double getMelhorofertavenda() {
		return this.melhorofertavenda;
	}

	public void setMelhorofertavenda(double melhorofertavenda) {
		this.melhorofertavenda = melhorofertavenda;
	}

	public double getMinimo() {
		return this.minimo;
	}

	public void setMinimo(double minimo) {
		this.minimo = minimo;
	}

	public Integer getQtdnegocios() {
		return this.qtdnegocios;
	}

	public void setQtdnegocios(Integer qtdnegocios) {
		this.qtdnegocios = qtdnegocios;
	}

	public Integer getQtdpapeis() {
		return this.qtdpapeis;
	}

	public void setQtdpapeis(Integer qtdpapeis) {
		this.qtdpapeis = qtdpapeis;
	}

	public double getUltimo() {
		return this.ultimo;
	}

	public void setUltimo(double ultimo) {
		this.ultimo = ultimo;
	}

	public double getVariacao() {
		return this.variacao;
	}

	public void setVariacao(double variacao) {
		this.variacao = variacao;
	}

	public double getVolume() {
		return this.volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public Ativo getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}
	
}