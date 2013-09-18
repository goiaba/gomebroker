package br.com.gome.gomebroker.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the empresa database table.
 * 
 */
@Entity
@Audited
public class Empresa implements Serializable, BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "EMPRESA_ID_GENERATOR", sequenceName = "EMPRESA_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPRESA_ID_GENERATOR")
	private Long id;

	private String cnpj;

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDesativacao;

	private String nome;

	private String nomeCompleto;

	private String obs;

	private String url;

	@OneToMany(mappedBy = "empresa", fetch=FetchType.LAZY)
	private Set<Ativo> ativos = new HashSet<Ativo>();

	public Empresa() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataDesativacao() {
		return this.dataDesativacao;
	}

	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeCompleto() {
		return this.nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
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
		return Collections.unmodifiableSet(this.ativos);
	}

	public void addAtivo(Ativo ativo) {
		ativo.setEmpresa(this);
	}

	public void removeAtivo(Ativo ativo) {
		ativo.setEmpresa(null);
	}

	protected void internalAddAtivo(Ativo ativo) {
		ativos.add(ativo);
	}

	protected void internalRemoveAtivo(Ativo ativo) {
		ativos.remove(ativo);
	}	

	public boolean equals(Object o) {

		if ((null == o) || (o.getClass() != this.getClass())) {
			return false;
		}

		if (o == this) {
			return true;
		}

		Empresa that = (Empresa) o;

		return new EqualsBuilder()
				.append(this.id, that.id)
				.append(this.cnpj, that.cnpj)
				.append(this.dataCadastro, that.dataCadastro)
				.append(this.dataDesativacao, that.dataDesativacao)
				.append(this.obs, that.obs)
				.append(this.nome, that.nome)
				.append(this.nomeCompleto, that.nomeCompleto)
				.append(this.url, that.url)
				.isEquals();

	}

	public int hashCode() {

		return new HashCodeBuilder(17, 31)
				.append(this.id)
				.append(this.cnpj)
				.append(this.dataCadastro)
				.append(this.dataDesativacao)
				.append(this.obs)
				.append(this.nome)
				.append(this.nomeCompleto)
				.append(this.url)
				.toHashCode();

	}

}
