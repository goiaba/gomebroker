package br.com.gome.gomebroker.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

/**
 * The persistent class for the ordemsolicitacoes database table.
 * 
 */
@Entity
@Audited
public class OrdemSolicitacoes implements BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ORDEMSOLICITACOES_ID_GENERATOR", sequenceName = "ORDEMSOLICITACOES_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDEMSOLICITACOES_ID_GENERATOR")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	private String mensagemCorretora;

	private String resposta;

	private String tipoSolicitacao;

	// bi-directional many-to-one association to Ordem
	@ManyToOne(fetch = FetchType.LAZY)
	private Ordem ordem;

	// bi-directional many-to-one association to Usuario
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	public OrdemSolicitacoes() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getMensagemCorretora() {
		return this.mensagemCorretora;
	}

	public void setMensagemCorretora(String mensagemCorretora) {
		this.mensagemCorretora = mensagemCorretora;
	}

	public String getResposta() {
		return this.resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public String getTipoSolicitacao() {
		return this.tipoSolicitacao;
	}

	public void setTipoSolicitacao(String tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public Ordem getOrdem() {
		return this.ordem;
	}

	public void setOrdem(Ordem ordem) {
		this.ordem = ordem;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}