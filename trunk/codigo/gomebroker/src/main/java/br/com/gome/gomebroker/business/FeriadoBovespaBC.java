package br.com.gome.gomebroker.business;

import java.util.Date;

import javax.ejb.Local;

import br.com.gome.gomebroker.domain.FeriadoBovespa;
import br.com.gome.gomebroker.persistence.FeriadoBovespaDAO;

@Local
public interface FeriadoBovespaBC extends BaseBC<FeriadoBovespa, Long, FeriadoBovespaDAO> {

	boolean ehFeriadoBovespa(Date date);

}
