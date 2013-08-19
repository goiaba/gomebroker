package br.com.gome.gomebroker.business;

import javax.ejb.Local;

import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.domain.security.UsuarioPapel;
import br.com.gome.gomebroker.persistence.UsuarioPerfilDAO;

@Local
public interface UsuarioPerfilBC extends BaseBC<UsuarioPapel, Long, UsuarioPerfilDAO> {

	UsuarioPapel getUsuarioPerfilPadrao(Usuario usuario);
	
	void setUsuarioPerfilPadrao(UsuarioPapel usuarioPapel);

}
