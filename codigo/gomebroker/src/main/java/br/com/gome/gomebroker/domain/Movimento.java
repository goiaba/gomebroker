package br.com.gome.gomebroker.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the movimento database table.
 * 
 */
@Entity
public class Movimento implements BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MOVIMENTO_ID_GENERATOR", sequenceName="MOVIMENTO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MOVIMENTO_ID_GENERATOR")
	private Integer id;

	private Timestamp data;

	private Timestamp dataexecucao;

	private String descricao;

	private String idexecucaoordemcorretora;

	private String obs;

	private double quantidade;

	private String tipo;

	private double valor;

	private double valorincidencia;

	//bi-directional many-to-one association to Operador
    @ManyToOne
	private Operador operador;

	//bi-directional many-to-one association to Ordem
    @ManyToOne
	private Ordem ordem;

    public Movimento() {
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

	public Timestamp getDataexecucao() {
		return this.dataexecucao;
	}

	public void setDataexecucao(Timestamp dataexecucao) {
		this.dataexecucao = dataexecucao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIdexecucaoordemcorretora() {
		return this.idexecucaoordemcorretora;
	}

	public void setIdexecucaoordemcorretora(String idexecucaoordemcorretora) {
		this.idexecucaoordemcorretora = idexecucaoordemcorretora;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public double getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValorincidencia() {
		return this.valorincidencia;
	}

	public void setValorincidencia(double valorincidencia) {
		this.valorincidencia = valorincidencia;
	}

	public Operador getOperador() {
		return this.operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}
	
	public Ordem getOrdem() {
		return this.ordem;
	}

	public void setOrdem(Ordem ordem) {
		this.ordem = ordem;
	}
	
}