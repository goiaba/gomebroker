package br.com.gome.gomebroker.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.com.gome.gomebroker.business.RecursoBC;
import br.com.gome.gomebroker.constant.SecurityConstants;
import br.com.gome.gomebroker.domain.security.Recurso;

@SessionScoped
public class RecursosPublicos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject private RecursoBC recursoBC;
	
	private Map<String, List<String>> recursos;
	
	@PostConstruct
	public void init() {
	
		recursos = new HashMap<String, List<String>>();
		
		recursos.put(SecurityConstants.PUBLIC_URL, new ArrayList<String>());
		recursos.put(SecurityConstants.PUBLIC_STARTS_WITH_URL, new ArrayList<String>());
		
		atualizaMapaDeRecursosPublicos();
		
	}

	public void atualizaMapaDeRecursosPublicos() {
		
		List<Recurso> listaDeRecursos = recursoBC.getRecursosPorTipo(SecurityConstants.PUBLIC_URL, SecurityConstants.PUBLIC_STARTS_WITH_URL);
		
		recursos.put(SecurityConstants.PUBLIC_URL, new ArrayList<String>());
		recursos.put(SecurityConstants.PUBLIC_STARTS_WITH_URL, new ArrayList<String>());
		
		for (Recurso recurso : listaDeRecursos) {
			
			getRecursos().get(recurso.getTipo()).add(recurso.getValor());
				
		}
		
	}

	public Map<String, List<String>> getRecursos() {
		
		return recursos;
		
	}
	
	public boolean isPublicStartsWithUrl(String url) {
		
		for (String recurso : getRecursos().get(SecurityConstants.PUBLIC_STARTS_WITH_URL)) {
			
			if (null != url && url.startsWith(recurso)) {
				return true;
			}
			
		}
		
		return false;
		
	}
	
	public boolean isPublicUrl(String url) {
		
		return getRecursos().get(SecurityConstants.PUBLIC_URL).contains(url);
		
	}
	
}
