package br.com.gome.gomebroker.persistence.impl;

import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.domain.UsuarioPerfil;
import br.com.gome.gomebroker.persistence.UsuarioPerfilDAO;
import br.gov.frameworkdemoiselle.transaction.Transactional;

public class UsuarioPerfilDAOImpl extends BaseDAOImpl<UsuarioPerfil, Long> implements UsuarioPerfilDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public UsuarioPerfil findUsuarioPerfilPadrao(Usuario usuario) {

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
					.createQuery(query, UsuarioPerfil.class)
					.setParameter("usuario", usuario)
					.getSingleResult();
		
	}

	@Override
	@Transactional
	public void setUsuarioPerfilPadrao(UsuarioPerfil usuarioPerfil) {

		Usuario usuario = usuarioPerfil.getUsuario();
		
		for (UsuarioPerfil up : usuario.getUsuarioPerfil()) {

			up.setPadrao(false);
			this.merge(up);
			
		}
		
		usuarioPerfil.setPadrao(true);
		this.merge(usuarioPerfil);
		
	}

}
