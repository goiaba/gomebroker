package br.com.gome.gomebroker.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.envers.Audited;

import java.sql.Timestamp;


/**
 * The persistent class for the movimento database table.
 * 
 */
@Entity
@Audited
public class Movimento implements Serializable, BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MOVIMENTO_ID_GENERATOR", sequenceName="MOVIMENTO_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MOVIMENTO_ID_GENERATOR")
	private Long id;

	private Timestamp data;

	private Timestamp dataExecucao;

	private String descricao;

	private String idExecucaoOrdemCorretora;

	private String obs;

	private double quantidade;

	private String tipo;

	private double valor;

	private double valorIncidencia;

	//bi-directional many-to-one association to Operador
	@ManyToOne(fetch=FetchType.LAZY)
	private Operador operador;

	//bi-directional many-to-one association to Ordem
	@ManyToOne(fetch=FetchType.LAZY)
	private Ordem ordem;

    public Movimento() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getData() {
		return this.data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public Timestamp getDataExecucao() {
		return this.dataExecucao;
	}

	public void setDataExecucao(Timestamp dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIdExecucaoOrdemCorretora() {
		return this.idExecucaoOrdemCorretora;
	}

	public void setIdExecucaoOrdemCorretora(String idExecucaoOrdemCorretora) {
		this.idExecucaoOrdemCorretora = idExecucaoOrdemCorretora;
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

	public double getValorIncidencia() {
		return this.valorIncidencia;
	}

	public void setValorIncidencia(double valorIncidencia) {
		this.valorIncidencia = valorIncidencia;
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