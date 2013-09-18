package br.com.gome.gomebroker.view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;

import br.com.gome.gomebroker.constant.ViewConstants;
import br.gov.frameworkdemoiselle.stereotype.ViewController;

@Named
@ViewController
@SessionScoped
public class SessionHelperMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void getAuthorizationFailedMessage(ComponentSystemEvent event) {

		FacesContext fc = FacesContext.getCurrentInstance();
		String message = (String) fc.getExternalContext().getSessionMap().remove(ViewConstants.AUTH_MESSAGE_KEY);
		
		if (null != message) {
			
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
			
		}
		
	}
	
}
