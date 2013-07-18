package br.com.gome.gomebroker.security;

import java.util.HashMap;
import java.util.Map;

import br.gov.frameworkdemoiselle.security.User;

public class UsuarioGomebroker implements User {

	private static final long serialVersionUID = 1L;

	private final String nome;
	private Map<String, Object> attrs = new HashMap<String, Object>();
	
	public UsuarioGomebroker(String nome) {
		
		this.nome = nome;
	}
	
	@Override
	public String getId() {

		return this.nome;
		
	}

	@Override
	public Object getAttribute(Object key) {
		return this.attrs.get(key);
	}

	@Override
	public void setAttribute(Object key, Object value) {
		this.attrs.put((String) key, value);
	}

}
