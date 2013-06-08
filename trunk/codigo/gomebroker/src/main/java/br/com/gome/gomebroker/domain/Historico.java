package br.com.gome.gomebroker.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the historico database table.
 * 
 */
@Entity
public class Historico implements BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="HISTORICO_ID_GENERATOR", sequenceName="HISTORICO_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HISTORICO_ID_GENERATOR")
	private Integer id;

	private String alteracoes;

	private Timestamp data;

	private Integer identidade;

	private String tipoentidade;

	private String tipohistorico;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	private Usuario usuario;

    public Historico() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAlteracoes() {
		return this.alteracoes;
	}

	public void setAlteracoes(String alteracoes) {
		this.alteracoes = alteracoes;
	}

	public Timestamp getData() {
		return this.data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public Integer getIdentidade() {
		return this.identidade;
	}

	public void setIdentidade(Integer identidade) {
		this.identidade = identidade;
	}

	public String getTipoentidade() {
		return this.tipoentidade;
	}

	public void setTipoentidade(String tipoentidade) {
		this.tipoentidade = tipoentidade;
	}

	public String getTipohistorico() {
		return this.tipohistorico;
	}

	public void setTipohistorico(String tipohistorico) {
		this.tipohistorico = tipohistorico;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}