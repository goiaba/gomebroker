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
 * The persistent class for the conta database table.
 * 
 */
@Entity
public class Conta implements BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONTA_ID_GENERATOR", sequenceName="CONTA_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONTA_ID_GENERATOR")
	private Integer id;

	private String conta;

	private Timestamp datacadastro;

	private Timestamp datadesativacao;

	private String nome;

	private String obs;

	//bi-directional many-to-one association to Titular
    @ManyToOne
	private Titular titular;

	//bi-directional many-to-one association to Operador
	@OneToMany(mappedBy="conta")
	private Set<Operador> operadores;

    public Conta() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConta() {
		return this.conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public Timestamp getDatacadastro() {
		return this.datacadastro;
	}

	public void setDatacadastro(Timestamp datacadastro) {
		this.datacadastro = datacadastro;
	}

	public Timestamp getDatadesativacao() {
		return this.datadesativacao;
	}

	public void setDatadesativacao(Timestamp datadesativacao) {
		this.datadesativacao = datadesativacao;
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