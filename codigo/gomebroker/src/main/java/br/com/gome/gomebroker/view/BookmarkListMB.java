package br.com.gome.gomebroker.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.com.gome.gomebroker.business.IncidenciaBC;
import br.com.gome.gomebroker.domain.Incidencia;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@NextView("./bookmark_edit.xhtml")
@PreviousView("./bookmark_list.xhtml")
public class BookmarkListMB extends AbstractListPageBean<Incidencia, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private IncidenciaBC bc;

	@Override
	@Transactional
	protected List<Incidencia> handleResultList() {
		
		return this.bc.findAll();
		
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);

			if (delete) {
				bc.remove(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}
