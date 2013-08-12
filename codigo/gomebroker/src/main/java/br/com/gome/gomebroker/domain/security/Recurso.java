package br.com.gome.gomebroker.domain.security;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

import br.com.gome.gomebroker.domain.BaseEntity;


/**
 * The persistent class for the recurso database table.
 * 
 */
@Audited
@Entity
@Table(name="sec_recurso")
public class Recurso implements Serializable, BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RECURSO_ID_GENERATOR", sequenceName="SEC_RECURSO_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RECURSO_ID_GENERATOR")
	private Long id;

	@Column(updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDesativacao;

	private String descricao;

	private String nome;

	private String valor;
	
	private String tipo;
	
	@OneToMany(mappedBy="recurso")
	private List<PapelRecurso> papelRecurso;

    public Recurso() {
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

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<PapelRecurso> getPapelRecurso() {
		return this.papelRecurso;
	}

	public void setPapelRecurso(List<PapelRecurso> papelRecurso) {
		this.papelRecurso = papelRecurso;
	}
	
}