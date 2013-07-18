package br.com.gome.gomebroker.business.impl;

import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.gome.gomebroker.business.UsuarioBC;
import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.persistence.UsuarioDAO;

public class UsuarioBCImpl extends BaseBCImpl<Usuario, Long, UsuarioDAO> implements UsuarioBC {

	private static final long serialVersionUID = 1L;
	
	@Inject private UsuarioDAO usuarioDAO;

	@Override
	public Usuario autentica(String usuario, String senha) {
		
		return usuarioDAO.autentica(usuario, DigestUtils.sha512Hex(senha));
		
	}
	
	public static void main(String[] args) {
		System.out.println(DigestUtils.sha512Hex("senha".getBytes()));
	}

}
