package br.com.gome.gomebroker.business.impl;

import java.util.Date;

import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.gome.gomebroker.business.UsuarioBC;
import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.exception.UsuarioDesativadoException;
import br.com.gome.gomebroker.persistence.UsuarioDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;

@BusinessController
public class UsuarioBCImpl extends BaseBCImpl<Usuario, Long, UsuarioDAO> implements UsuarioBC {

	private static final long serialVersionUID = 1L;
	
	@Inject private UsuarioDAO usuarioDAO;

	@Override
	public Usuario autentica(String usuario, String senha) throws UsuarioDesativadoException {
		
		Usuario u = usuarioDAO.autentica(usuario, DigestUtils.sha512Hex(senha));
		
		if (null != u && u.getDataDesativacao().before(new Date())) {
			
			throw new UsuarioDesativadoException(u);
				
		}
		
		return u;
		
	}
	
	public static void main(String[] args) {
		System.out.println(DigestUtils.sha512Hex("senha".getBytes()));
	}

}
