package br.com.gome.gomebroker.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the incidencia database table.
 * 
 */
@Entity
public class Incidencia implements BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INCIDENCIA_ID_GENERATOR", sequenceName="INCIDENCIA_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INCIDENCIA_ID_GENERATOR")
	private Integer id;

	private Timestamp datacadastro;

	private Timestamp datavalidade;

	private Timestamp datavigencia;

	private String nome;

	private String obs;

	private String sobre;

	private String tipo;

	private double valor;

    public Incidencia() {
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

	public Timestamp getDatavalidade() {
		return this.datavalidade;
	}

	public void setDatavalidade(Timestamp datavalidade) {
		this.datavalidade = datavalidade;
	}

	public Timestamp getDatavigencia() {
		return this.datavigencia;
	}

	public void setDatavigencia(Timestamp datavigencia) {
		this.datavigencia = datavigencia;
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