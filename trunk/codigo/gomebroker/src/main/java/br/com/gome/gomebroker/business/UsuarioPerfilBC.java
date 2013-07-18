package br.com.gome.gomebroker.business;

import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.domain.UsuarioPerfil;
import br.com.gome.gomebroker.persistence.UsuarioPerfilDAO;

public interface UsuarioPerfilBC extends BaseBC<UsuarioPerfil, Long, UsuarioPerfilDAO> {

	UsuarioPerfil getUsuarioPerfilPadrao(Usuario usuario);

}
