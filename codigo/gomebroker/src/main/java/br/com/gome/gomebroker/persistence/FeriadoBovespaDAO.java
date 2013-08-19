package br.com.gome.gomebroker.persistence;

import java.util.Date;

import br.com.gome.gomebroker.domain.FeriadoBovespa;

public interface FeriadoBovespaDAO extends BaseDAO<FeriadoBovespa, Long> {

	boolean verificaSeEhFeriado(Date date);

}
