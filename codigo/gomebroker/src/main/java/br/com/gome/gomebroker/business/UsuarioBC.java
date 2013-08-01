package br.com.gome.gomebroker.business;

import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.exception.UsuarioDesativadoException;
import br.com.gome.gomebroker.persistence.UsuarioDAO;

public interface UsuarioBC extends BaseBC<Usuario, Long, UsuarioDAO> {

	Usuario autentica(String usuario, String senha) throws UsuarioDesativadoException;

}
