package br.com.gome.gomebroker.business.impl;

import javax.inject.Inject;

import br.com.gome.gomebroker.business.UsuarioPerfilBC;
import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.domain.UsuarioPerfil;
import br.com.gome.gomebroker.persistence.UsuarioPerfilDAO;

public class UsuarioPerfilImplBC extends BaseBCImpl<UsuarioPerfil, Long, UsuarioPerfilDAO> implements UsuarioPerfilBC {

	private static final long serialVersionUID = 1L;
	
	@Inject UsuarioPerfilDAO usuarioPerfilDao;

	@Override
	public UsuarioPerfil getUsuarioPerfilPadrao(Usuario usuario) {

		return usuarioPerfilDao.findUsuarioPerfilPadrao(usuario);
		
	}

}
