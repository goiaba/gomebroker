package br.com.gome.gomebroker.domain.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import br.com.gome.gomebroker.domain.BaseEntity;

@Audited
@Entity
@Table(name="sec_itemMenu")
public class ItemMenu implements BaseEntity<Long>, Comparable<ItemMenu> {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ITEMMENU_ID_GENERATOR", sequenceName = "SEC_ITEMMENU_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEMMENU_ID_GENERATOR")
	private Long id;

	/**
	 * Referência ao item pai desse item
	 */
	@JoinColumn(name = "itemMenu_id_pai")
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH })
	private ItemMenu pai;

	/**
	 * Lista dos itens filhos
	 */
	@OneToMany(mappedBy = "pai", fetch = FetchType.EAGER, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private List<ItemMenu> filhos;

	/**
	 * Chave para recuperar a descrição internacionalizada do item
	 */
	private String descricaoKey;

	/**
	 * URL para a qual o item vai apontar
	 */
	private String url;

	@ManyToMany(mappedBy = "itensDeMenu", fetch = FetchType.LAZY)
	private List<Papel> papeis;

	/**
	 * Diz se o item é apenas um separador
	 */
	private Boolean separador;

	/**
	 * Diz se o item é um submenu, e não um item que aponta para uma URL
	 */
	private Boolean submenu;

	/**
	 * Utilizado para ordenar os items
	 */
	private Integer ordem;

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDesativacao;

	public ItemMenu() {
	}

	public ItemMenu(String descricaoKey, String url, Boolean separador,
			Boolean submenu, Integer ordem) {
		this.descricaoKey = descricaoKey;
		this.url = url;
		this.separador = separador;
		this.submenu = submenu;
		this.ordem = ordem;

	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public ItemMenu getPai() {
		return pai;
	}

	public void setPai(ItemMenu pai) {
		this.pai = pai;
	}

	public List<ItemMenu> getFilhos() {
		return filhos;
	}

	public void setFilhos(List<ItemMenu> filhos) {
		this.filhos = filhos;
	}

	public String getDescricaoKey() {
		return descricaoKey;
	}

	public void setDescricaoKey(String descricaoKey) {
		this.descricaoKey = descricaoKey;
	}

	public String getUrl() {
		return url;
	}

	public Boolean isSeparador() {
		return separador;
	}

	public void setSeparador(Boolean separador) {
		this.separador = separador;
	}

	public Boolean isSubMenu() {
		return submenu;
	}

	public void setSubMenu(Boolean submenu) {
		this.submenu = submenu;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataDesativacao() {
		return dataDesativacao;
	}

	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

	public void addFilho(ItemMenu filho) {

		filho.setPai(this);

		if (null == this.filhos)
			this.filhos = new ArrayList<ItemMenu>();

		this.filhos.add(filho);

	}

	public void removeFilho(ItemMenu filho) {

		filho.setPai(null);

		if (null != this.filhos)
			this.filhos.remove(filho);

	}

	@Override
	public int compareTo(ItemMenu itemMenu) {

		if (this.getOrdem() < itemMenu.getOrdem()) {
			return -1;
		}
		if (this.getOrdem() > itemMenu.getOrdem()) {
			return 1;
		}
		return 0;

	}

	public boolean equals(Object o) {

		if ((null == o) || (o.getClass() != this.getClass())) {
			return false;
		}

		if (o == this) {
			return true;
		}

		ItemMenu that = (ItemMenu) o;

		return new EqualsBuilder().append(this.id, that.id)
				.append(this.descricaoKey, that.descricaoKey)
				.append(this.url, that.url).isEquals();

	}

	public int hashCode() {

		return new HashCodeBuilder(17, 31).append(this.id)
				.append(this.descricaoKey).append(this.url).toHashCode();

	}

}
