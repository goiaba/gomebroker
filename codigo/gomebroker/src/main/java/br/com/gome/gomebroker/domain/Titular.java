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
 * The persistent class for the titular database table.
 * 
 */
@Entity
public class Titular implements BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TITULAR_ID_GENERATOR", sequenceName="TITULAR_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TITULAR_ID_GENERATOR")
	private Integer id;

	private String assinatura;

	private Timestamp datacadastro;

	private Timestamp datadesativacao;

	private String login;

	private String obs;

	private String parametros;

	private String senha;

	//bi-directional many-to-one association to Conta
	@OneToMany(mappedBy="titular")
	private Set<Conta> contas;

	//bi-directional many-to-one association to Corretora
    @ManyToOne
	private Corretora corretora;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	private Usuario usuario;

    public Titular() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAssinatura() {
		return this.assinatura;
	}

	public void setAssinatura(String assinatura) {
		this.assinatura = assinatura;
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

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getParametros() {
		return this.parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Conta> getContas() {
		return this.contas;
	}

	public void setContas(Set<Conta> contas) {
		this.contas = contas;
	}
	
	public Corretora getCorretora() {
		return this.corretora;
	}

	public void setCorretora(Corretora corretora) {
		this.corretora = corretora;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}