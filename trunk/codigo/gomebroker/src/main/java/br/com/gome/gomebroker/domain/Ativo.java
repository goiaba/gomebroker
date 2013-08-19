package br.com.gome.gomebroker.domain;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import br.com.gome.gomebroker.constant.TipoMercado;


/**
 * The persistent class for the ativo database table.
 * 
 */
@Entity
@Audited
public class Ativo implements BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ATIVO_ID_GENERATOR", sequenceName="ATIVO_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ATIVO_ID_GENERATOR")
	@Column(nullable=false, updatable=false)
	private Long id;

	private String codigo;

	@Enumerated(EnumType.STRING)
	private TipoMercado tipoMercado;

	private Integer fatorCotacao;
	
	private String obs;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private Empresa empresa;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@OneToMany(mappedBy="ativo", fetch = FetchType.LAZY, targetEntity = AtivoCotacao.class)
	private Set<AtivoCotacao> ativoCotacao = new HashSet<AtivoCotacao>();

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@OneToMany(mappedBy="ativo", fetch = FetchType.LAZY, targetEntity = AtivoCotacao.class, cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Set<AtivoOfertas> ativoOfertas = new HashSet<AtivoOfertas>();

	@OneToMany(mappedBy="ativo", fetch = FetchType.LAZY, targetEntity = AtivoCotacao.class, cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Set<Ordem> ordens = new HashSet<Ordem>();

	@OneToMany(mappedBy="ativo", fetch = FetchType.LAZY, targetEntity = AtivoCotacao.class, cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Set<PortifolioAtivo> portifolioAtivos = new HashSet<PortifolioAtivo>();

	@Column(updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDesativacao;

    public Ativo() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public TipoMercado getTipoMercado() {
		return tipoMercado;
	}

	public void setTipoMercado(TipoMercado tipoMercado) {
		this.tipoMercado = tipoMercado;
	}

	public Integer getFatorCotacao() {
		return fatorCotacao;
	}

	public void setFatorCotacao(Integer fatorCotacao) {
		this.fatorCotacao = fatorCotacao;
	}

	public String getObs() {
		return this.obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public Set<AtivoCotacao> getAtivoCotacoes() {
		return Collections.unmodifiableSet(this.ativoCotacao);
	}
	
	public void addAtivoCotacao(AtivoCotacao ativoCotacao) {
		
		if (null == this.ativoCotacao) {
			this.ativoCotacao = new HashSet<AtivoCotacao>();
		}
		
		ativoCotacao.setAtivo(this);
		this.ativoCotacao.add(ativoCotacao);
		
	}
	
	public void removeAtivoCotacao(AtivoCotacao ativoCotacao) {
		
		if (null != this.ativoCotacao) {
			this.ativoCotacao.remove(ativoCotacao);
		}
		
		ativoCotacao.setAtivo(null);
		
	}

	public void setAtivoCotacoes(Set<AtivoCotacao> ativoCotacao) {
		this.ativoCotacao = ativoCotacao;
	}
	
	public Set<AtivoOfertas> getAtivoOfertas() {
		return this.ativoOfertas;
	}

	public void setAtivoOfertas(Set<AtivoOfertas> ativoOfertas) {
		this.ativoOfertas = ativoOfertas;
	}
	
	public Set<Ordem> getOrdens() {
		return this.ordens;
	}

	public void setOrdens(Set<Ordem> ordens) {
		this.ordens = ordens;
	}
	
	public Set<PortifolioAtivo> getPortifolioAtivos() {
		return this.portifolioAtivos;
	}

	public void setPortifolioAtivos(Set<PortifolioAtivo> portifolioAtivos) {
		this.portifolioAtivos = portifolioAtivos;
	}
	
	public boolean equals(Object o) {

		if ((null == o) || (o.getClass() != this.getClass())) {
			return false;
		}

		if (o == this) {
			return true;
		}

		Ativo that = (Ativo) o;

		return new EqualsBuilder()
				.append(this.id, that.id)
				.append(this.codigo, that.codigo)
				.append(this.dataCadastro, that.dataCadastro)
				.append(this.dataDesativacao, that.dataDesativacao)
				.append(this.obs, that.obs)
				.append(this.tipoMercado, that.tipoMercado)
				.append(this.fatorCotacao, that.fatorCotacao)
				.isEquals();

	}

	public int hashCode() {

		return new HashCodeBuilder(17, 31)
				.append(this.id)
				.append(this.codigo)
				.append(this.dataCadastro)
				.append(this.dataDesativacao)
				.append(this.obs)
				.append(this.tipoMercado)
				.append(this.fatorCotacao)
				.toHashCode();

	}
	
}