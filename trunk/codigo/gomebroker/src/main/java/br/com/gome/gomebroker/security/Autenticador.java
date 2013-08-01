package br.com.gome.gomebroker.security;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.gome.gomebroker.business.PapelBC;
import br.com.gome.gomebroker.business.RecursoBC;
import br.com.gome.gomebroker.business.UsuarioBC;
import br.com.gome.gomebroker.constant.SecurityConstants;
import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.domain.security.Papel;
import br.com.gome.gomebroker.domain.security.Recurso;
import br.com.gome.gomebroker.exception.UsuarioDesativadoException;
import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.User;

@SessionScoped
public class Autenticador implements Authenticator {

	private static final long serialVersionUID = 1L;
	
	@Inject private Credencial credencial;
	@Inject private UsuarioBC usuarioBC;
	@Inject private RecursoBC recursoBC;
	@Inject private PapelBC papelBC;

	private User usuarioSistema;
	
	@Override
	public boolean authenticate() {

		boolean authenticated = false;

		String username = credencial.getUsername();
		String password = credencial.getPassword();
		
		try {
		
			Usuario usuario = usuarioBC.autentica(username, password);
			
			if (null != usuario) {
				
				List<Papel> papeisUsuario = papelBC.getPapeisValidosDoUsuario(usuario);

				if (!papeisUsuario.isEmpty()) {
					
					Papel papel = papelBC.getPapelPadrao(usuario);
					
					usuarioSistema = new UsuarioGomebroker(username);
					usuarioSistema.setAttribute(SecurityConstants.USUARIO_SESSAO_KEY, usuario);
					usuarioSistema.setAttribute(SecurityConstants.PAPEL_USUARIO_SESSAO_KEY, papel);
					usuarioSistema.setAttribute(SecurityConstants.PAPEIS_DISPONIVEIS_KEY, toPapeisStringList(papeisUsuario));
					usuarioSistema.setAttribute(SecurityConstants.RECURSOS_DISPONIVEIS_KEY, toRecursosStringList(recursoBC.getRecursosDisponiveis(papel)));
					
					authenticated = true;
					
				}
				
			} else {
				//FIXME: Utilizar o ResourceBundle para recuperar mensagem
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário/senha inválidos"));
				
			}
			
		} catch (UsuarioDesativadoException e) {

			String nome = e.getUsuario().getNome();
			String dataDesativacao = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(e.getUsuario().getDataDesativacao());
			//FIXME: Utilizar o ResourceBundle para recuperar mensagem
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O usuário " + nome + " não possui acesso ao sistema desde " + dataDesativacao));
			
		}
		
		return authenticated;
		
	}

	private Map<String, String> toRecursosStringList(List<Recurso> recursosDisponiveis) {
		
		Map<String, String> recursos = new HashMap<String, String>();
		
		for (Recurso recurso : recursosDisponiveis) {
			
			recursos.put(recurso.getValor(), recurso.getTipo());
			
		}
		
		return recursos;
		
	}

	private Set<String> toPapeisStringList(List<Papel> papeisUsuario) {
		
		Set<String> papeis = new HashSet<String>();
		
		for (Papel papel : papeisUsuario) {
			
			papeis.add(papel.getNome());
			
		}
		
		return papeis;
		
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
