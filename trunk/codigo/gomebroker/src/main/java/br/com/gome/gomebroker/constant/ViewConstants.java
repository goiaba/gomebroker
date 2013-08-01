package br.com.gome.gomebroker.constant;

public interface ViewConstants {

	/**
	 * Uso interno.
	 */
	static final String FS = System.getProperty("file.separator");
	
	/**
	 * Página para a qual o usuário será redirecionado caso
	 *  tente acessar uma página restrita sem estar logado
	 *  no sistema. 
	 */
	static final String LOGIN_PAGE_JSF = FS + "login.jsf";
	
	/**
	 * String de comparação com a viewId do FacesContext ao
	 *  verificar se a página tentando ser acessada por um
	 *  usuário não logado é a de login.
	 */
    static final String LOGIN_PAGE_XHTML = FS + "login.xhtml";

    /**
     * Expressão utilizada para criar programaticamente o item
     *  de menu que invoca o método de logout da aplicação.
     */
	static final String METHOD_EXPRESSION_LOGOUT = "#{securityContext.logout()}";
	
}
