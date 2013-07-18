package br.com.gome.gomebroker.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.envers.Audited;


/**
 * The persistent class for the incidencia database table.
 * 
 */
@Entity
@Audited
public class Incidencia implements BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INCIDENCIA_ID_GENERATOR", sequenceName="INCIDENCIA_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INCIDENCIA_ID_GENERATOR")
	private Long id;

	@Column(updatable=false)
	private Timestamp dataCadastro;

	private Timestamp dataValidade;

	private Timestamp dataVigencia;

	private String nome;

	private String obs;

	private String sobre;

	private String tipo;

	private double valor;

    public Incidencia() {
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

	public Timestamp getDataValidade() {
		return this.dataValidade;
	}

	public void setDataValidade(Timestamp dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Timestamp getDataVigencia() {
		return this.dataVigencia;
	}

	public void setDataVigencia(Timestamp dataVigencia) {
		this.dataVigencia = dataVigencia;
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

	public String getSobre() {
		return this.sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
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

}