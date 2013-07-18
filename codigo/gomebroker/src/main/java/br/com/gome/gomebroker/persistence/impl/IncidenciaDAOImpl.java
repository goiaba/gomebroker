package br.com.gome.gomebroker.persistence.impl;

import br.com.gome.gomebroker.domain.Incidencia;
import br.com.gome.gomebroker.persistence.IncidenciaDAO;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class IncidenciaDAOImpl extends BaseDAOImpl<Incidencia, Long> implements IncidenciaDAO {

	private static final long serialVersionUID = 1L;

}
