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
 * The persistent class for the perfil database table.
 * 
 */
@Entity
public class Perfil implements BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PERFIL_ID_GENERATOR", sequenceName="PERFIL_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERFIL_ID_GENERATOR")
	private Integer id;

	private Timestamp datacadastro;

	private Timestamp datadesativacao;

	private String definicoes;

	private String descricao;

	private String nome;

	private String obs;

	//bi-directional many-to-one association to Relusuarioperfil
	@OneToMany(mappedBy="perfil")
	private Set<UsuariopPerfil> usuarioPerfis;

    public Perfil() {
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

	public String getDefinicoes() {
		return this.definicoes;
	}

	public void setDefinicoes(String definicoes) {
		this.definicoes = definicoes;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Set<UsuariopPerfil> getUsuarioPerfis() {
		return this.usuarioPerfis;
	}

	public void setUsuarioPerfis(Set<UsuariopPerfil> usuarioPerfis) {
		this.usuarioPerfis = usuarioPerfis;
	}
	
}