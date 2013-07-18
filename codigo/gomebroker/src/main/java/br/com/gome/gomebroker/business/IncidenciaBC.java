package br.com.gome.gomebroker.business;

import br.com.gome.gomebroker.domain.Incidencia;
import br.com.gome.gomebroker.persistence.IncidenciaDAO;

public interface IncidenciaBC extends BaseBC<Incidencia, Long, IncidenciaDAO> {

	void persist(Incidencia bean);
	
}
