package br.com.gome.gomebroker.security;

import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.com.gome.gomebroker.business.PerfilBC;
import br.com.gome.gomebroker.business.UsuarioBC;
import br.com.gome.gomebroker.domain.Perfil;
import br.com.gome.gomebroker.domain.Usuario;
import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.User;

@SessionScoped
public class Autenticador implements Authenticator {

	private static final long serialVersionUID = 1L;
	
	@Inject private Credencial credencial;
	@Inject private UsuarioBC usuarioBC;
	@Inject private PerfilBC perfilBC;

	private User usuarioSistema;

	@Override
	public boolean authenticate() {

		boolean authenticated = false;

		String username = credencial.getUsername();
		String password = credencial.getPassword();
		
		Usuario usuario = usuarioBC.autentica(username, password);
		
		if (null != usuario) {
			
			Set<Perfil> perfisUsuario = perfilBC.getPerfisValidosDoUsuario(usuario);

			if (!perfisUsuario.isEmpty()) {
				
				usuarioSistema = new UsuarioGomebroker(username);
				usuarioSistema.setAttribute(SecurityConstants.USUARIO_SESSAO_KEY, usuario);
				usuarioSistema.setAttribute(SecurityConstants.PERFIL_USUARIO_SESSAO_KEY, perfilBC.getPerfilPadrao(usuario));
				
				authenticated = true;
				
			}
			
		}

		return authenticated;
		
	}

	@Override
	public void unAuthenticate() {

		usuarioSistema = null;
		credencial.clear();
		
	}

	@Override
	public User getUser() {

		return usuarioSistema;
		
	}

}
