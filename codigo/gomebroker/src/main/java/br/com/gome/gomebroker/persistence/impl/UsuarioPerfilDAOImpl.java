package br.com.gome.gomebroker.persistence.impl;

import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.domain.security.UsuarioPapel;
import br.com.gome.gomebroker.persistence.UsuarioPerfilDAO;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class UsuarioPerfilDAOImpl extends BaseDAOImpl<UsuarioPapel, Long> implements UsuarioPerfilDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public UsuarioPapel findUsuarioPerfilPadrao(Usuario usuario) {

		/*
		 * SELECT u.*
		 * FROM usuario u INNER JOIN relusuarioperfil up ON u.id = up.usuario_id
		 * WHERE up.padrao = true;
		 */
		
		String query = "SELECT up " +
					   " FROM Usuario u JOIN u.usuarioPerfil up" +
					   " WHERE u = :usuario" +
					   " AND up.padrao = true";
		
		return getEntityManager()
					.createQuery(query, UsuarioPapel.class)
					.setParameter("usuario", usuario)
					.getSingleResult();
		
	}

	@Override
	public void setUsuarioPerfilPadrao(UsuarioPapel usuarioPapel) {

		Usuario usuario = usuarioPapel.getUsuario();
		
		for (UsuarioPapel up : usuario.getUsuarioPerfil()) {

			up.setPadrao(false);
			this.merge(up);
			
		}
		
		usuarioPapel.setPadrao(true);
		this.merge(usuarioPapel);
		
	}

}
