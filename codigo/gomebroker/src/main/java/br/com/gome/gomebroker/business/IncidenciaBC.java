package br.com.gome.gomebroker.business;

import javax.ejb.Local;

import br.com.gome.gomebroker.domain.Incidencia;
import br.com.gome.gomebroker.persistence.IncidenciaDAO;

@Local
public interface IncidenciaBC extends BaseBC<Incidencia, Long, IncidenciaDAO> {

	void persist(Incidencia bean);
	
}
