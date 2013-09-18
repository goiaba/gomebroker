package br.com.gome.gomebroker.persistence.impl;

import java.util.Date;

import br.com.gome.gomebroker.domain.FeriadoBovespa;
import br.com.gome.gomebroker.persistence.FeriadoBovespaDAO;

public class FeriadoBovespaDAOImpl extends BaseDAOImpl<FeriadoBovespa, Long> implements FeriadoBovespaDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean ehFeriadoBovespa(Date date) {

		String query = "SELECT feriado " +
						"FROM FeriadoBovespa feriado " +
						"WHERE feriado.data = :hoje";
		
		return getEntityManager()
				.createQuery(query)
				.setParameter("hoje", date)
				.getResultList().isEmpty() ? false : true;
		
	}

}
