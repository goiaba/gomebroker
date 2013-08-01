package br.com.gome.gomebroker.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import br.com.gome.gomebroker.constant.SecurityConstants;
import br.com.gome.gomebroker.constant.ViewConstants;
import br.gov.frameworkdemoiselle.security.SecurityContext;

@WebFilter(urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class AutorizadorURL implements Filter {

	@Inject	private SecurityContext securityContext;
	
	@Inject private RecursosPublicos recursosPublicos;
	
	@Inject private Logger logger;

	private HttpServletRequest request;

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		this.request = (HttpServletRequest) request;
		String url = this.request.getRequestURI().replaceAll("^/.+?/", "/");

		if (isPublicURL(url) || hasPermission(url)) {

			chain.doFilter(request, response);

		} else {

			redirect(response, getContext() + ViewConstants.LOGIN_PAGE_JSF);

		}

	}

	private boolean isPublicURL(String url) {
		
		if (recursosPublicos.isPublicUrl(url)) {
			//FIXME: Utilizar o ResourceBundle para recuperar mensagem
			info("Acesso permitido por " + SecurityConstants.PUBLIC_URL, url);
			return true;
		}
		
		if (recursosPublicos.isPublicStartsWithUrl(url)) {
			//FIXME: Utilizar o ResourceBundle para recuperar mensagem
			info("Acesso permitido por " + SecurityConstants.PUBLIC_STARTS_WITH_URL, url);
			return true;
		}
			
		return false;
		
	}
	
	private boolean hasPermission(String url) {

		if (!securityContext.isLoggedIn()) {
			//FIXME: Utilizar o ResourceBundle para recuperar mensagem
			info("Acesso negado por usuário não logado", url);
			return false;
		}
		
		if (securityContext.hasPermission(SecurityConstants.PRIVATE_URL, url)) {
			//FIXME: Utilizar o ResourceBundle para recuperar mensagem
			info("Acesso permitido por " + SecurityConstants.PRIVATE_URL, url);
			return true;
		}

		if (securityContext.hasPermission(SecurityConstants.PRIVATE_STARTS_WITH_URL, url)) {
			//FIXME: Utilizar o ResourceBundle para recuperar mensagem
			info("Acesso permitido por " + SecurityConstants.PRIVATE_STARTS_WITH_URL, url);
			return true;
		}
		
		//FIXME: Utilizar o ResourceBundle para recuperar mensagem
		info("Acesso negado", url);
		return false;
		
	}


	private void redirect(ServletResponse response, String url)	throws IOException {
		
		((HttpServletResponse) response).sendRedirect(url);
		
	}

	private void info(String message, String url) {
		
		logger.info(message + ": " + getUsername() + "@" + getSource() + getContext() + url);
		
	}

	private String getUsername() {
		
		try {
		
			if (securityContext.isLoggedIn()) {
			
				return ((UsuarioGomebroker) securityContext.getUser()).getId();
				
			}
			
		} catch (Exception e) {}
		
		return null;
		
	}

	private String getContext() {
		
		if (request.getServletContext() == null) {
		
			return null;
			
		}
		
		return request.getServletContext().getContextPath();
		
	}

	private String getSource() {
		
		return request.getRemoteHost() + ":" + request.getRemotePort();
		
	}

}
