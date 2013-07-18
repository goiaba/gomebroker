package br.com.gome.gomebroker.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.envers.Audited;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the recurso database table.
 * 
 */
@Entity
@Audited
public class Recurso implements Serializable, BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RECURSO_ID_GENERATOR", sequenceName="RECURSO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RECURSO_ID_GENERATOR")
	private Long id;

	@Column(updatable=false)
	private Timestamp dataCadastro;

	private Timestamp dataDesativacao;

	private String descricao;

	private String nome;

	private String valor;

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

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public List<PapelRecurso> getPapelRecurso() {
		return this.papelRecurso;
	}

	public void setPapelRecurso(List<PapelRecurso> papelRecurso) {
		this.papelRecurso = papelRecurso;
	}
	
}