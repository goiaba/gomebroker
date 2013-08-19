package br.com.gome.gomebroker.business;

import java.util.Date;

import br.com.gome.gomebroker.domain.FeriadoBovespa;
import br.com.gome.gomebroker.persistence.FeriadoBovespaDAO;

public interface FeriadoBovespaBC extends BaseBC<FeriadoBovespa, Long, FeriadoBovespaDAO> {

	boolean verificaSeEhFeriado(Date date);

}
