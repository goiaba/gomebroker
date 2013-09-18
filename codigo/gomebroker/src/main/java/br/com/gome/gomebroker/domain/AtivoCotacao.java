package br.com.gome.gomebroker.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.gome.gomebroker.util.StringConverter;


/**
 * The persistent class for the ativocotacoes database table.
 * 
 */
@Entity
@Table(name="ativoCotacoes")
public class AtivoCotacao implements Serializable, BaseEntity<AtivoCotacaoPK>, Comparable<AtivoCotacao> {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AtivoCotacaoPK id = new AtivoCotacaoPK();

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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ativo_id", referencedColumnName="id", insertable=false, updatable=false)
	private Ativo ativo;

    public AtivoCotacao() {
    }

    public AtivoCotacao(Ativo ativo, Date data) {
    	
    	this.id.setAtivo(ativo.getId());
    	this.id.setData(data);
    	
    	setAtivo(ativo);
    	
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
	
	public void setAbertura(String abertura) {
		setAbertura(StringConverter.convert(abertura, Double.class));
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
	
	public void setMaximo(String maximo) {
		setMaximo(StringConverter.convert(maximo, Double.class));
	}

	public double getMedio() {
		return this.medio;
	}

	public void setMedio(double medio) {
		this.medio = medio;
	}
	
	public void setMedio(String medio) {
		setMedio(StringConverter.convert(medio, Double.class));
	}

	public double getMelhorOfertaCompra() {
		return this.melhorOfertaCompra;
	}

	public void setMelhorOfertaCompra(double melhorOfertaCompra) {
		this.melhorOfertaCompra = melhorOfertaCompra;
	}
	
	public void setMelhorOfertaCompra(String melhorOfertaCompra) {
		setMelhorOfertaCompra(StringConverter.convert(melhorOfertaCompra, Double.class));
	}

	public double getMelhorOfertaVenda() {
		return this.melhorOfertaVenda;
	}

	public void setMelhorOfertaVenda(double melhorOfertaVenda) {
		this.melhorOfertaVenda = melhorOfertaVenda;
	}
	
	public void setMelhorOfertaVenda(String melhorOfertaVenda) {
		setMelhorOfertaVenda(StringConverter.convert(melhorOfertaVenda, Double.class));
	}

	public double getMinimo() {
		return this.minimo;
	}

	public void setMinimo(double minimo) {
		this.minimo = minimo;
	}
	
	public void setMinimo(String minimo) {
		setMinimo(StringConverter.convert(minimo, Double.class));
	}

	public Integer getQuantidadeNegocios() {
		return this.quantidadeNegocios;
	}

	public void setQuantidadeNegocios(Integer quantidadeNegocios) {
		this.quantidadeNegocios = quantidadeNegocios;
	}
	
	public void setQuantidadeNegocios(String quantidadeNegocios) {
		setQuantidadeNegocios(StringConverter.convert(quantidadeNegocios, Integer.class));
	}

	public Integer getQuantidadePapeis() {
		return this.quantidadePapeis;
	}

	public void setQuantidadePapeis(Integer quantidadePapeis) {
		this.quantidadePapeis = quantidadePapeis;
	}
	
	public void setQuantidadePapeis(String quantidadePapeis) {
		setQuantidadePapeis(StringConverter.convert(quantidadePapeis, Integer.class));
	}

	public double getUltimo() {
		return this.ultimo;
	}

	public void setUltimo(double ultimo) {
		this.ultimo = ultimo;
	}
	
	public void setUltimo(String ultimo) {
		setUltimo(StringConverter.convert(ultimo, Double.class));
	}

	public double getVariacao() {
		return this.variacao;
	}

	public void setVariacao(double variacao) {
		this.variacao = variacao;
	}
	
	public void setVariacao(String variacao) {
		setVariacao(StringConverter.convert(variacao, Double.class));
	}

	public double getVolume() {
		return this.volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	public void setVolume(String volume) {
		setVolume(StringConverter.convert(volume, Double.class));
	}

	public Ativo getAtivo() {
		return this.ativo;
	}

	public void setAtivo(Ativo ativo) {

		//Remove this da lista de cotacoes do ativo
		if (this.ativo != null)
			this.ativo.internalRemoveCotacao(this);
		
		this.ativo = ativo;
		
		//Adiciona this à lista de cotacoes do ativo
		if (ativo != null)
			ativo.internalAddCotacao(this);
	
	}	

	public boolean equals(Object o) {

		if ((null == o) || (o.getClass() != this.getClass())) {
			return false;
		}

		if (o == this) {
			return true;
		}

		AtivoCotacao that = (AtivoCotacao) o;

		return new EqualsBuilder()
				.append(this.id, that.id)
				.append(this.abertura, that.abertura)
				.append(this.intraDiario, that.intraDiario)
				.append(this.maximo, that.maximo)
				.append(this.medio, that.medio)
				.append(this.melhorOfertaCompra, that.melhorOfertaCompra)
				.append(this.melhorOfertaVenda, that.melhorOfertaVenda)
				.append(this.minimo, that.minimo)
				.append(this.quantidadeNegocios, that.quantidadeNegocios)
				.append(this.quantidadePapeis, that.quantidadePapeis)
				.append(this.ultimo, that.ultimo)
				.append(this.variacao, that.variacao)
				.append(this.volume, that.volume)
				.isEquals();

	}

	public int hashCode() {

		return new HashCodeBuilder(17, 31)
				.append(this.id)
				.append(this.abertura)
				.append(this.intraDiario)
				.append(this.maximo)
				.append(this.medio)
				.append(this.melhorOfertaCompra)
				.append(this.melhorOfertaVenda)
				.append(this.minimo)
				.append(this.quantidadeNegocios)
				.append(this.quantidadePapeis)
				.append(this.ultimo)
				.append(this.variacao)
				.append(this.volume)
				.toHashCode();

	}

	@Override
	public int compareTo(AtivoCotacao o) {
		
		if (this.getId().getData().before(o.getId().getData()))
			return -1;
	
		if (this.getId().getData().after(o.getId().getData()))
			return 1;
	
		return 0;
		
	}
	
	
	
}
