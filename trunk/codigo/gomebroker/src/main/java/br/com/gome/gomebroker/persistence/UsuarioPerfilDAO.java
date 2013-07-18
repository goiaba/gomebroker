package br.com.gome.gomebroker.persistence;

import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.domain.UsuarioPerfil;

public interface UsuarioPerfilDAO extends BaseDAO<UsuarioPerfil, Long> {

	UsuarioPerfil findUsuarioPerfilPadrao(Usuario usuario);
	
	void setUsuarioPerfilPadrao(UsuarioPerfil usuarioPerfil);

}
