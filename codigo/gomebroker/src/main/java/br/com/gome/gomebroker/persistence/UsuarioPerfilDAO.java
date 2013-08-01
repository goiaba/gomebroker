package br.com.gome.gomebroker.persistence;

import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.domain.security.UsuarioPapel;

public interface UsuarioPerfilDAO extends BaseDAO<UsuarioPapel, Long> {

	UsuarioPapel findUsuarioPerfilPadrao(Usuario usuario);
	
	void setUsuarioPerfilPadrao(UsuarioPapel usuarioPapel);

}
