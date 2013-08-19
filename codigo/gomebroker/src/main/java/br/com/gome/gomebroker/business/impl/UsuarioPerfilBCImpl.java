package br.com.gome.gomebroker.business.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.gome.gomebroker.business.UsuarioPerfilBC;
import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.domain.security.UsuarioPapel;
import br.com.gome.gomebroker.persistence.UsuarioPerfilDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;

@Stateless
@BusinessController
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioPerfilBCImpl extends BaseBCImpl<UsuarioPapel, Long, UsuarioPerfilDAO> implements UsuarioPerfilBC {

	private static final long serialVersionUID = 1L;
	
	@Inject UsuarioPerfilDAO usuarioPerfilDao;

	@Override
	public UsuarioPapel getUsuarioPerfilPadrao(Usuario usuario) {

		return usuarioPerfilDao.findUsuarioPerfilPadrao(usuario);
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void setUsuarioPerfilPadrao(UsuarioPapel usuarioPapel) {

		usuarioPerfilDao.setUsuarioPerfilPadrao(usuarioPapel);
		
	}

}
