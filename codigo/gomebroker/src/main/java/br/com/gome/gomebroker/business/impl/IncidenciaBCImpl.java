package br.com.gome.gomebroker.business.impl;

import br.com.gome.gomebroker.business.IncidenciaBC;
import br.com.gome.gomebroker.domain.Incidencia;
import br.com.gome.gomebroker.persistence.IncidenciaDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;

@BusinessController
public class IncidenciaBCImpl extends BaseBCImpl<Incidencia, Long, IncidenciaDAO> implements IncidenciaBC {

	private static final long serialVersionUID = 1L;

}
