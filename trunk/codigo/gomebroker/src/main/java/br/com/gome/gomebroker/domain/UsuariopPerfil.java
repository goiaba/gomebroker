package br.com.gome.gomebroker.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the relusuarioperfil database table.
 * 
 */
@Entity(name="relusuarioperfil")
public class UsuariopPerfil implements BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RELUSUARIOPERFIL_ID_GENERATOR", sequenceName="RELUSUARIOPERFIL_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RELUSUARIOPERFIL_ID_GENERATOR")
	private Integer id;

	private Timestamp datacadastro;

	private Timestamp datavalidade;

	private Timestamp datavigencia;

	//bi-directional many-to-one association to Perfil
    @ManyToOne
	private Perfil perfil;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	private Usuario usuario;

    public UsuariopPerfil() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDatacadastro() {
		return this.datacadastro;
	}

	public void setDatacadastro(Timestamp datacadastro) {
		this.datacadastro = datacadastro;
	}

	public Timestamp getDatavalidade() {
		return this.datavalidade;
	}

	public void setDatavalidade(Timestamp datavalidade) {
		this.datavalidade = datavalidade;
	}

	public Timestamp getDatavigencia() {
		return this.datavigencia;
	}

	public void setDatavigencia(Timestamp datavigencia) {
		this.datavigencia = datavigencia;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}