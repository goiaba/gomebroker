package br.com.gome.gomebroker.domain;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;


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
	@Column(updatable=false)
	private Long id;

	private String codigo;

	@Column(updatable=false)
	private Timestamp dataCadastro;

	private Timestamp dataDesativacao;

	private String descricao;

	private String obs;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	private Empresa empresa;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@OneToMany(mappedBy="ativo")
	private Set<AtivoCotacoes> ativoCotacoes;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@OneToMany(mappedBy="ativo")
	private Set<AtivoOfertas> ativoOfertas;

	@OneToMany(mappedBy="ativo")
	private Set<Ordem> ordens;

	@OneToMany(mappedBy="ativo")
	private Set<PortifolioAtivo> portifolioAtivos;

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
	
	public Set<AtivoCotacoes> getAtivoCotacoes() {
		return this.ativoCotacoes;
	}

	public void setAtivoCotacoes(Set<AtivoCotacoes> ativoCotacoes) {
		this.ativoCotacoes = ativoCotacoes;
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
	
}