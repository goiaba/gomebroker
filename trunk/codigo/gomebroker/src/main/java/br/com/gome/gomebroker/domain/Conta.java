package br.com.gome.gomebroker.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.envers.Audited;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the conta database table.
 * 
 */
@Entity
@Audited
public class Conta implements Serializable, BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONTA_ID_GENERATOR", sequenceName="CONTA_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONTA_ID_GENERATOR")
	private Long id;

	private String conta;

	@Column(updatable=false)
	private Timestamp dataCadastro;

	private Timestamp dataDesativacao;

	private String nome;

	private String obs;

	//bi-directional many-to-one association to Titular
	@ManyToOne(fetch=FetchType.LAZY)
	private Titular titular;

	//bi-directional many-to-one association to Operador
	@OneToMany(mappedBy="conta")
	private Set<Operador> operadores;

    public Conta() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConta() {
		return this.conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public Timestamp getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Timestamp getDataDesativacao() {
		return this.dataDesativacao;
	}

	public void setDataDesativacao(Timestamp dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
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

	public Titular getTitular() {
		return this.titular;
	}

	public void setTitular(Titular titular) {
		this.titular = titular;
	}
	
	public Set<Operador> getOperadores() {
		return this.operadores;
	}

	public void setOperadores(Set<Operador> operadores) {
		this.operadores = operadores;
	}
	
}