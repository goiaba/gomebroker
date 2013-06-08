package br.com.gome.gomebroker.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the ordemsolicitacoes database table.
 * 
 */
@Entity
@Table(name="ordemsolicitacoes")
public class OrdemSolicitacao implements BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ORDEMSOLICITACOES_ID_GENERATOR", sequenceName="ORDEMSOLICITACOES_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDEMSOLICITACOES_ID_GENERATOR")
	private Integer id;

	private Timestamp data;

	private String mensagemcorretora;

	private String resposta;

	private String tiposolicitacao;

	//bi-directional many-to-one association to Ordem
    @ManyToOne
	private Ordem ordem;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	private Usuario usuario;

    public OrdemSolicitacao() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getData() {
		return this.data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public String getMensagemcorretora() {
		return this.mensagemcorretora;
	}

	public void setMensagemcorretora(String mensagemcorretora) {
		this.mensagemcorretora = mensagemcorretora;
	}

	public String getResposta() {
		return this.resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public String getTiposolicitacao() {
		return this.tiposolicitacao;
	}

	public void setTiposolicitacao(String tiposolicitacao) {
		this.tiposolicitacao = tiposolicitacao;
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