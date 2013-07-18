package br.com.gome.gomebroker.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;


/**
 * The persistent class for the ativocotacoes database table.
 * 
 */
@Entity
public class AtivoCotacoes implements Serializable, BaseEntity<AtivoCotacoesPK> {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AtivoCotacoesPK id;

	private double abertura;

	private Boolean intraDiario;

	private double maximo;

	private double medio;

	private double melhorOfertaCompra;

	private double melhorOfertaVenda;

	private double minimo;

	@Column(name="qtdnegocios")
	private Integer quantidadeNegocios;

	@Column(name="qtdpapeis")
	private Integer quantidadePapeis;

	private double ultimo;

	private double variacao;

	private double volume;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ativo_id", referencedColumnName="id", insertable=false, updatable=false)
	private Ativo ativo;

    public AtivoCotacoes() {
    }

	public AtivoCotacoesPK getId() {
		return this.id;
	}

	public void setId(AtivoCotacoesPK id) {
		this.id = id;
	}
	
	public double getAbertura() {
		return this.abertura;
	}

	public void setAbertura(double abertura) {
		this.abertura = abertura;
	}

	public Boolean getIntraDiario() {
		return this.intraDiario;
	}

	public void setIntraDiario(Boolean intraDiario) {
		this.intraDiario = intraDiario;
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

	public double getMelhorOfertaCompra() {
		return this.melhorOfertaCompra;
	}

	public void setMelhorOfertaCompra(double melhorOfertaCompra) {
		this.melhorOfertaCompra = melhorOfertaCompra;
	}

	public double getMelhorOfertaVenda() {
		return this.melhorOfertaVenda;
	}

	public void setMelhorOfertaVenda(double melhorOfertaVenda) {
		this.melhorOfertaVenda = melhorOfertaVenda;
	}

	public double getMinimo() {
		return this.minimo;
	}

	public void setMinimo(double minimo) {
		this.minimo = minimo;
	}

	public Integer getQuantidadeNegocios() {
		return this.quantidadeNegocios;
	}

	public void setQuantidadeNegocios(Integer quantidadeNegocios) {
		this.quantidadeNegocios = quantidadeNegocios;
	}

	public Integer getQuantidadePapeis() {
		return this.quantidadePapeis;
	}

	public void setQuantidadePapeis(Integer quantidadePapeis) {
		this.quantidadePapeis = quantidadePapeis;
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