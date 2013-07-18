package br.com.gome.gomebroker.util.envers.listener;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.envers.RevisionListener;

import br.com.gome.gomebroker.constant.ViewConstants;
import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.util.envers.entity.CustomEntity;

public class CustomListener implements RevisionListener {

	@Override
	public void newRevision(Object object) {

		CustomEntity ce = (CustomEntity) object;
	
		Usuario usuarioLogado = getUsuarioLogadoJSF();
		
		ce.setUsuario((null != usuarioLogado) ? usuarioLogado.getNome() : null);
		
	}
	
	private Usuario getUsuarioLogadoJSF() {

		if (null == FacesContext.getCurrentInstance()) return null;
		
		HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		return (Usuario) httpSession.getAttribute(ViewConstants.USUARIO_LOGADO);
		
	}
	

}
