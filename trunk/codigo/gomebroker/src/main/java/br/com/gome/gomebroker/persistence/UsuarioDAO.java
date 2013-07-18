package br.com.gome.gomebroker.persistence;

import br.com.gome.gomebroker.domain.Usuario;

public interface UsuarioDAO extends BaseDAO<Usuario, Long> {

	Usuario autentica(String usuario, String passwd);

}
