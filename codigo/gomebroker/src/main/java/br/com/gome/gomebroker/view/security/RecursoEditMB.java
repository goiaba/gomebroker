package br.com.gome.gomebroker.view.security;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.com.gome.gomebroker.business.PapelBC;
import br.com.gome.gomebroker.business.RecursoBC;
import br.com.gome.gomebroker.domain.security.Papel;
import br.com.gome.gomebroker.domain.security.Recurso;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("./list.xhtml")
public class RecursoEditMB extends AbstractEditPageBean<Recurso, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject private RecursoBC recursoBC;
	@Inject private PapelBC papelBC;
	
	private List<Papel> papeisComAcessoAoRecurso = null;
	
	@PostConstruct
	public void init() {
		if (isUpdateMode())
			papeisComAcessoAoRecurso = papelBC.getPapeisAtivosComAcessoAoRecurso(getBean());
	}
	
	@Override
	public String delete() {
		this.recursoBC.remove(getBean());
		return getPreviousView();
	}
	
	@Override
	public String insert() {
		this.recursoBC.persist(getBean(), getListaDePapeisAtivosComAcessoAoRecurso());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String update() {
		this.recursoBC.merge(getBean(), getListaDePapeisAtivosComAcessoAoRecurso());
		return getPreviousView();
	}
	
	@Override
	protected void handleLoad() {
		setBean(this.recursoBC.find(getId()));
	}
	
	public List<Papel> getListaDePapeisAtivos() {
		return papelBC.getPapeisAtivos();
	}

	public void setListaDePapeisAtivosComAcessoAoRecurso(List<Papel> papeis) {
		papeisComAcessoAoRecurso = papeis;
	}
	
	public List<Papel> getListaDePapeisAtivosComAcessoAoRecurso() {
		return papeisComAcessoAoRecurso;
	}
	
}
