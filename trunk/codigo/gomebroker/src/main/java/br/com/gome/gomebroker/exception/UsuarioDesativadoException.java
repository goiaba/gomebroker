package br.com.gome.gomebroker.exception;

import br.com.gome.gomebroker.domain.Usuario;

public class UsuarioDesativadoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	public UsuarioDesativadoException() {}
	
	public UsuarioDesativadoException(Usuario usuario) {
		this.setUsuario(usuario);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
