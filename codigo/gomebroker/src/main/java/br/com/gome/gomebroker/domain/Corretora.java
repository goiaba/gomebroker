package br.com.gome.gomebroker.domain;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the corretora database table.
 * 
 */
@Entity
public class Corretora implements BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CORRETORA_ID_GENERATOR", sequenceName="CORRETORA_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CORRETORA_ID_GENERATOR")
	private Integer id;

	private Timestamp datacadastro;

	private Timestamp datadesativacao;

	private String descricao;

	private String moduloconexao;

	private String nome;

	private String obs;

	private String parametrosconexao;

	//bi-directional many-to-one association to Titular
	@OneToMany(mappedBy="corretora")
	private Set<Titular> titulares;

    public Corretora() {
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

	public Timestamp getDatadesativacao() {
		return this.datadesativacao;
	}

	public void setDatadesativacao(Timestamp datadesativacao) {
		this.datadesativacao = datadesativacao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getModuloconexao() {
		return this.moduloconexao;
	}

	public void setModuloconexao(String moduloconexao) {
		this.moduloconexao = moduloconexao;
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

	public String getParametrosconexao() {
		return this.parametrosconexao;
	}

	public void setParametrosconexao(String parametrosconexao) {
		this.parametrosconexao = parametrosconexao;
	}

	public Set<Titular> getTitulares() {
		return this.titulares;
	}

	public void setTitulares(Set<Titular> titulares) {
		this.titulares = titulares;
	}
	
}