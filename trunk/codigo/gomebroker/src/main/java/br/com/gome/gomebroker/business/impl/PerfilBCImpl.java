package br.com.gome.gomebroker.business.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import br.com.gome.gomebroker.business.PerfilBC;
import br.com.gome.gomebroker.business.UsuarioBC;
import br.com.gome.gomebroker.business.UsuarioPerfilBC;
import br.com.gome.gomebroker.domain.Perfil;
import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.domain.UsuarioPerfil;
import br.com.gome.gomebroker.persistence.PerfilDAO;
import br.gov.frameworkdemoiselle.transaction.Transactional;

public class PerfilBCImpl extends BaseBCImpl<Perfil, Long, PerfilDAO> implements PerfilBC {

	private static final long serialVersionUID = 1L;
	
	@Inject private UsuarioPerfilBC usuarioPerfilBC;
	@Inject private UsuarioBC usuarioBC;
	
	@Override
	public Perfil getPerfilPadrao(Usuario usuario) {

		UsuarioPerfil usuarioPerfilPadrao = usuarioPerfilBC.getUsuarioPerfilPadrao(usuario);

		return usuarioPerfilPadrao.getPerfil();
		
	}
	
	@Override
	@Transactional
	public Set<Perfil> getPerfisValidosDoUsuario(Usuario usuario) {

		usuario = usuarioBC.attach(usuario);
		
		Set<UsuarioPerfil> usuarioPerfil = usuario.getUsuarioPerfil();
		
		Set<Perfil> perfisValidos = new HashSet<Perfil>(); 
		
		Date agora = new Date();
		
		for (UsuarioPerfil up : usuarioPerfil) {

			if (null == up.getPerfil().getDataDesativacao() || up.getPerfil().getDataDesativacao().after(agora)) {
				
				if (up.getDataVigencia().before(agora) && up.getDataValidade().after(agora)) {
					
					perfisValidos.add(up.getPerfil());
					
				}
				
			}
			
			
		}
		
		return perfisValidos;
		
	}

}
