package br.com.gome.gomebroker.domain.security;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import br.com.gome.gomebroker.domain.BaseEntity;

/**
 * The persistent class for the papel database table.
 * 
 */
@Audited
@Entity
@Table(name="sec_papel")
public class Papel implements Serializable, BaseEntity<Long> {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PAPEL_ID_GENERATOR", sequenceName = "SEC_PAPEL_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAPEL_ID_GENERATOR")
	private Long id;

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDesativacao;

	private String descricao;

	private String nome;

	private String obs;

	@OneToMany(mappedBy = "papel")
	private Set<UsuarioPapel> listaUsuarioPapel;

	@OneToMany(mappedBy = "papel", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH })
	private List<PapelRecurso> papelRecurso;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sec_relPapelItemMenu", joinColumns = { @JoinColumn(name = "itemMenu_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "papel_id", referencedColumnName = "id") })
	private List<ItemMenu> itensDeMenu;

	public Papel() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<UsuarioPapel> getListaUsuarioPapel() {
		return listaUsuarioPapel;
	}

	public void setListaUsuarioPapel(Set<UsuarioPapel> listaUsuarioPapel) {
		this.listaUsuarioPapel = listaUsuarioPapel;
	}

	public List<PapelRecurso> getPapelRecurso() {
		return this.papelRecurso;
	}

	public void setPapelRecurso(List<PapelRecurso> papelRecurso) {
		this.papelRecurso = papelRecurso;
	}

	public List<ItemMenu> getItensDeMenu() {
		return itensDeMenu;
	}

	public void setItensDeMenu(List<ItemMenu> itensDeMenu) {
		this.itensDeMenu = itensDeMenu;
	}

	public boolean equals(Object o) {

		if ((null == o) || (o.getClass() != this.getClass())) {
			return false;
		}

		if (o == this) {
			return true;
		}

		Papel that = (Papel) o;

		return new EqualsBuilder().append(this.id, that.id)
				.append(this.nome, that.nome)
				.append(this.dataCadastro, that.dataCadastro)
				.append(this.dataDesativacao, that.dataDesativacao)
				.append(this.descricao, that.descricao)
				.append(this.obs, that.obs).isEquals();

	}

	public int hashCode() {

		return new HashCodeBuilder(17, 31).append(this.id).append(this.nome)
				.append(this.dataCadastro).append(this.dataDesativacao)
				.append(this.descricao).append(this.obs).toHashCode();

	}

}