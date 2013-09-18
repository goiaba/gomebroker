package br.com.gome.gomebroker.business.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Named;

import br.com.gome.gomebroker.business.IncidenciaBC;
import br.com.gome.gomebroker.domain.Incidencia;
import br.com.gome.gomebroker.persistence.IncidenciaDAO;

@Named
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class IncidenciaBCImpl extends BaseBCImpl<Incidencia, Long, IncidenciaDAO> implements IncidenciaBC {

	private static final long serialVersionUID = 1L;

}
