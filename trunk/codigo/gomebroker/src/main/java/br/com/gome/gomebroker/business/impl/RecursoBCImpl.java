package br.com.gome.gomebroker.business.impl;

import java.util.List;

import javax.inject.Inject;

import br.com.gome.gomebroker.business.RecursoBC;
import br.com.gome.gomebroker.domain.security.Papel;
import br.com.gome.gomebroker.domain.security.Recurso;
import br.com.gome.gomebroker.persistence.RecursoDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;

@BusinessController
public class RecursoBCImpl extends BaseBCImpl<Recurso, Long, RecursoDAO> implements RecursoBC {

	private static final long serialVersionUID = 1L;

	@Inject private RecursoDAO recursoDao;
	
	@Override
	public List<Recurso> getRecursosDisponiveis(Papel papel) {

		return recursoDao.findRecursosDisponiveis(papel);
		
	}

	@Override
	public List<Recurso> getRecursosPorTipo(String... tiposRecurso) {

		return recursoDao.findRecursosPorTipo(tiposRecurso);
		
	}

}
