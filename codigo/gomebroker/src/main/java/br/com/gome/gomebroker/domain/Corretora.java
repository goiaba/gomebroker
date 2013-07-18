package br.com.gome.gomebroker.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.envers.Audited;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the corretora database table.
 * 
 */
@Entity
@Audited
public class Corretora implements Serializable, BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CORRETORA_ID_GENERATOR", sequenceName="CORRETORA_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CORRETORA_ID_GENERATOR")
	private Long id;

	@Column(updatable=false)
	private Timestamp dataCadastro;

	private Timestamp dataDesativacao;

	private String descricao;

	private String moduloConexao;

	private String nome;

	private String obs;

	private String parametrosConexao;

	//bi-directional many-to-one association to Titular
	@OneToMany(mappedBy="corretora")
	private Set<Titular> titulares;

    public Corretora() {
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

	public Timestamp getDataDesativacao() {
		return this.dataDesativacao;
	}

	public void setDataDesativacao(Timestamp dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getModuloConexao() {
		return this.moduloConexao;
	}

	public void setModuloConexao(String moduloConexao) {
		this.moduloConexao = moduloConexao;
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

	public String getParametrosConexao() {
		return this.parametrosConexao;
	}

	public void setParametrosConexao(String parametrosConexao) {
		this.parametrosConexao = parametrosConexao;
	}

	public Set<Titular> getTitulares() {
		return this.titulares;
	}

	public void setTitulares(Set<Titular> titulares) {
		this.titulares = titulares;
	}
	
}