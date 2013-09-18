package br.com.gome.gomebroker.domain.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
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
	
	@ManyToMany(mappedBy="recursos", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private List<Papel> papeis = new ArrayList<Papel>();

    public Recurso() {
    }

    public void addPapel(Papel papel) {
    	
    	if (!papeis.contains(papel)) {
    		this.internalAddPapel(papel);
    	}
    	
    	if (!papel.getRecursos().contains(this)) {
    		papel.addRecurso(this);
    	}
    	
	}
	
	public void removePapel(Papel papel) {
		
		if (papeis.contains(papel)) {
			papel.internalRemoveRecurso(this);
		}
		
		if (papel.getRecursos().contains(this)) {
			papel.removeRecurso(this);
		}
		
	}

	protected void internalAddPapel(Papel papel) {
		papeis.add(papel);
	}

	protected void internalRemovePapel(Papel papel) {
		papeis.remove(papel);
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

	public List<Papel> getPapeis() {
		return this.papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}
	
	public boolean equals(Object o) {

		if ((null == o) || (o.getClass() != this.getClass())) {
			return false;
		}

		if (o == this) {
			return true;
		}

		Recurso that = (Recurso) o;

		return new EqualsBuilder().append(this.id, that.id)
				.append(this.nome, that.nome)
				.append(this.dataCadastro, that.dataCadastro)
				.append(this.dataDesativacao, that.dataDesativacao)
				.append(this.descricao, that.descricao)
				.append(this.tipo, that.tipo)
				.append(this.valor, that.valor)
				.isEquals();

	}

	public int hashCode() {

		return new HashCodeBuilder(17, 31)
				.append(this.id)
				.append(this.nome)
				.append(this.dataCadastro)
				.append(this.dataDesativacao)
				.append(this.descricao)
				.append(this.tipo)
				.append(this.valor)
				.toHashCode();

	}
	
}