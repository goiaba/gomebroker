package br.com.gome.gomebroker.domain;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the empresa database table.
 * 
 */
@Entity
public class Empresa implements BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EMPRESA_ID_GENERATOR", sequenceName="EMPRESA_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMPRESA_ID_GENERATOR")
	private Integer id;

	private String cnpj;

	private Timestamp datacadastro;

	private Timestamp datadesativacao;

	private String nome;

	private String nomecompleto;

	private String obs;

	private String url;

	//bi-directional many-to-one association to Ativo
	@OneToMany(mappedBy="empresa", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private Set<Ativo> ativos;

    public Empresa() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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

	public String getNomecompleto() {
		return this.nomecompleto;
	}

	public void setNomecompleto(String nomecompleto) {
		this.nomecompleto = nomecompleto;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Ativo> getAtivos() {
		return this.ativos;
	}

	public void setAtivos(Set<Ativo> ativos) {
		this.ativos = ativos;
	}
	
}