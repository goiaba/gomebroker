package br.com.gome.gomebroker.view;

import javax.inject.Inject;

import br.com.gome.gomebroker.business.IncidenciaBC;
import br.com.gome.gomebroker.domain.Incidencia;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./bookmark_list.xhtml")
public class BookmarkEditMB extends AbstractEditPageBean<Incidencia, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private IncidenciaBC bc;

	@Override
	@Transactional
	public String delete() {
		this.bc.remove(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		this.bc.persist(getBean());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String update() {
		this.bc.merge(getBean());
		return getPreviousView();
	}

	@Override
	protected void handleLoad() {
		setBean(this.bc.find(getId()));
	}

}
