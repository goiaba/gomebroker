package br.com.gome.gomebroker.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.envers.Audited;


/**
 * The persistent class for the perfil database table.
 * 
 */
@Entity
@Audited
public class Perfil implements BaseEntity<Long> {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PERFIL_ID_GENERATOR", sequenceName="PERFIL_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERFIL_ID_GENERATOR")
	private Long id;

	@Column(updatable=false)
	private Timestamp dataCadastro;

	private Timestamp dataDesativacao;

	private String descricao;

	private String nome;

	private String obs;
	
	@OneToMany(mappedBy="usuario")
	private List<UsuarioPerfil> usuariosPerfil;
	
	@OneToMany(mappedBy="perfil")
	private List<PerfilPapel> papeisPerfil;

    public Perfil() {
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

	public List<UsuarioPerfil> getUsuariosPerfil() {
		return usuariosPerfil;
	}

	public void setUsuariosPerfil(List<UsuarioPerfil> usuariosPerfil) {
		this.usuariosPerfil = usuariosPerfil;
	}

	public List<PerfilPapel> getPapeisPerfil() {
		return this.papeisPerfil;
	}

	public void setPapeisPerfil(List<PerfilPapel> papeisPerfil) {
		this.papeisPerfil = papeisPerfil;
	}
	
}