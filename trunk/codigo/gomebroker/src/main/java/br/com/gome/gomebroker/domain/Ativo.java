package br.com.gome.gomebroker.domain;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the ativo database table.
 * 
 */
@Entity
public class Ativo implements BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ATIVO_ID_GENERATOR", sequenceName="ATIVO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ATIVO_ID_GENERATOR")
	private Integer id;

	private String codigo;

	private Timestamp dataCadastro;

	private Timestamp dataDesativacao;

	private String descricao;

	private String obs;

	//bi-directional many-to-one association to Empresa
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private Empresa empresa;

	//bi-directional many-to-one association to Ativocotacoe
	@OneToMany(mappedBy="ativo")
	private Set<AtivoCotacao> ativoCotacoes;

	//bi-directional many-to-one association to Ativooferta
	@OneToMany(mappedBy="ativo")
	private Set<AtivoOferta> ativoOfertas;

	//bi-directional many-to-one association to Ordem
	@OneToMany(mappedBy="ativo")
	private Set<Ordem> ordens;

	//bi-directional many-to-one association to Relportifolioativo
	@OneToMany(mappedBy="ativo")
	private Set<PortfolioAtivo> portifolioAtivos;

    public Ativo() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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
	
	public Set<AtivoCotacao> getAtivoCotacoes() {
		return Collections.unmodifiableSet(this.ativoCotacoes);
	}
	
	public void addAtivoCotacao(AtivoCotacao ativoCotacao) {
		ativoCotacao.setAtivo(this);
	}
	
	public void removeAtivoCotacao(AtivoCotacao ativoCotacao) {
		ativoCotacao.setAtivo(null);
	}
	
	public void internalAddAtivoCotacao(AtivoCotacao ativoCotacao) {
		this.ativoCotacoes.add(ativoCotacao);
	}
	
	public void internalRemoveAtivoCotacao(AtivoCotacao ativoCotacao) {
		this.ativoCotacoes.remove(ativoCotacao);
	}

	public Set<AtivoOferta> getAtivoOfertas() {
		return Collections.unmodifiableSet(this.ativoOfertas);
	}
	
	public void addAtivoOferta(AtivoOferta ativoOferta) {
		ativoOferta.setAtivo(this);
	}
	
	public void removeAtivoOferta(AtivoOferta ativoOferta) {
		ativoOferta.setAtivo(null);
	}
	
	public void internalAddAtivoOferta(AtivoOferta ativoOferta) {
		this.ativoOfertas.add(ativoOferta);
	}
	
	public void internalRemoveAtivoOferta(AtivoOferta ativoOferta) {
		this.ativoOfertas.remove(ativoOfertas);
	}

	public Set<Ordem> getOrdens() {
		return Collections.unmodifiableSet(this.ordens);
	}
	
	public void addOrdem(Ordem ordem) {
		ordem.setAtivo(this);
	}
	
	public void removeOrdem(Ordem ordem) {
		ordem.setAtivo(null);
	}
	
	public void internalAddOrdem(Ordem ordem) {
		this.ordens.add(ordem);
	}
	
	public void internalRemoveOrdem(Ordem ordem) {
		this.ordens.remove(ordem);
	}

	public Set<PortfolioAtivo> getPortifolioAtivos() {
		return Collections.unmodifiableSet(this.portifolioAtivos);
	}
	
	public void addPortfolioAtivo(PortfolioAtivo portfolioAtivo) {
		portfolioAtivo.setAtivo(this);
	}

	public void removePortfolioAtivo(PortfolioAtivo portfolioAtivo) {
		portfolioAtivo.setAtivo(null);
	}
	
	public void internalAddPortfolioAtivo(PortfolioAtivo portfolioAtivo) {
		this.portifolioAtivos.add(portfolioAtivo);
	}
	
	public void internalRemovePortfolioAtivo(PortfolioAtivo portfolioAtivo) {
		this.portifolioAtivos.remove(portfolioAtivo);
	}
}