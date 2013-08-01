package br.com.gome.gomebroker.security;

import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.gome.gomebroker.constant.SecurityConstants;
import br.gov.frameworkdemoiselle.security.Authorizer;
import br.gov.frameworkdemoiselle.security.SecurityContext;

@RequestScoped
public class Autorizador implements Authorizer {

	private static final long serialVersionUID = 1L;
	
	@Inject	private SecurityContext securityContext;
	
	@PostConstruct
	public void init() {
		
		
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean hasRole(String role) {
		
		try {
		
			Set<String> roleSet = ((Set<String>) securityContext.getUser().getAttribute(SecurityConstants.PAPEIS_DISPONIVEIS_KEY));
			
			return roleSet.contains(role);

		} catch (Exception e) {
			
			return false;
			
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean hasPermission(String resource, String operation) {
		
		try {
			
			Map<String, String> resourceMap = ((Map<String, String>) securityContext.getUser().getAttribute(SecurityConstants.RECURSOS_DISPONIVEIS_KEY));
			
			if (null != resource && resource.equals(resourceMap.get(operation)))
				
				return true;
			
		} catch (Exception e) {
			
			// Ignore
			
		}
		
		return false;
		
	}
	
}
