package br.com.gome.gomebroker.view.security;

import javax.inject.Inject;

import br.com.gome.gomebroker.business.RecursoBC;
import br.com.gome.gomebroker.domain.security.Recurso;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;

@ViewController
@PreviousView("./list.xhtml")
public class RecursoEditMB extends AbstractEditPageBean<Recurso, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject private RecursoBC recursoBC;
	
	@Override
	public String delete() {
		
		this.recursoBC.remove(getId());
		return getPreviousView();
	}
	
	@Override
	public String insert() {
		this.recursoBC.persist(getBean());
		return getPreviousView();
	}

	@Override
	public String update() {
		this.recursoBC.merge(getBean());
		return getPreviousView();
	}

	@Override
	protected void handleLoad() {
		setBean(this.recursoBC.find(getId()));
	}

}
