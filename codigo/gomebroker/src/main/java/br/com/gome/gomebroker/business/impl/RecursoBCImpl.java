package br.com.gome.gomebroker.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.gome.gomebroker.business.PapelBC;
import br.com.gome.gomebroker.business.RecursoBC;
import br.com.gome.gomebroker.domain.security.Papel;
import br.com.gome.gomebroker.domain.security.Recurso;
import br.com.gome.gomebroker.persistence.RecursoDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;

@Stateless
@BusinessController
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RecursoBCImpl extends BaseBCImpl<Recurso, Long, RecursoDAO> implements RecursoBC {

	private static final long serialVersionUID = 1L;

	@Inject private RecursoDAO recursoDao;
	@Inject private PapelBC papelBC;
	
	@Override
	public List<Recurso> getRecursosDisponiveis(Papel papel) {

		return recursoDao.findRecursosDisponiveis(papel);
		
	}

	@Override
	public List<Recurso> getRecursosPorTipo(boolean apenasRecursosAtivos, String... tiposRecurso) {

		return recursoDao.findRecursosPorTipo(apenasRecursosAtivos, tiposRecurso);
		
	}

	@Override
	public List<Recurso> find(String searchString) {

		return recursoDao.find(searchString);
		
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Recurso recurso) {
		
		recurso = this.attach(recurso);
				
		for (Papel papel : recurso.getPapeis()) {
			
			recurso.removePapel(papel);
			
		}
		
		super.remove(recurso);
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void merge(Recurso recurso, List<Papel> listaDePapeisAtivosComAcessoAoRecurso) {

		recurso = this.attach(recurso);
		
		for (Papel papel : recurso.getPapeis()) {

			recurso.removePapel(papel);
			
		}
		
		for (Papel papel : listaDePapeisAtivosComAcessoAoRecurso) {

			papel = papelBC.attach(papel);
			recurso.addPapel(papel);
			
		}
		
		merge(recurso);
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(Recurso recurso, List<Papel> listaDePapeisAtivosComAcessoAoRecurso) {
		
		for (Papel papel : listaDePapeisAtivosComAcessoAoRecurso) {

			papel = papelBC.attach(papel);
			recurso.addPapel(papel);
			
		}
		
		persist(recurso);
		
	}

}
