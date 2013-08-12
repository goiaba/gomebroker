package br.com.gome.gomebroker.view.security;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.com.gome.gomebroker.business.RecursoBC;
import br.com.gome.gomebroker.domain.security.Recurso;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;

@ViewController
@NextView("./edit.xhtml")
@PreviousView("./list.xhtml")
public class RecursoListMB extends AbstractListPageBean<Recurso, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String searchString = null;

	@Inject private RecursoBC recursoBC;
	
	@Override
	protected List<Recurso> handleResultList() {

		if (null != getSearchString()) {
		
			return recursoBC.find(getSearchString());
			
		}
		
		return this.recursoBC.findAll();
		
	}
	
	public String deleteSelection() {
	
		boolean delete;
		
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			
			Long id = iter.next();
			
			delete = getSelection().get(id);

			if (delete) {
				
				recursoBC.remove(id);
				iter.remove();
				
			}
			
		}
		
		return getPreviousView();
		
	}
	
	public Boolean containSelection() {
		
		return !getSelection().containsValue(Boolean.TRUE);
		
	}
	
	public void clearSearch() {
		
		this.setSearchString(null);
		this.clear();
		
	}
	
	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
}
