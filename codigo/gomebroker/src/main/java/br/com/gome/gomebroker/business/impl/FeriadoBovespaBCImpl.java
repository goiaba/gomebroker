package br.com.gome.gomebroker.business.impl;

import java.util.Date;

import javax.inject.Inject;

import br.com.gome.gomebroker.business.FeriadoBovespaBC;
import br.com.gome.gomebroker.domain.FeriadoBovespa;
import br.com.gome.gomebroker.persistence.FeriadoBovespaDAO;

public class FeriadoBovespaBCImpl extends BaseBCImpl<FeriadoBovespa, Long, FeriadoBovespaDAO> implements FeriadoBovespaBC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject private FeriadoBovespaDAO feriadoBovespaDao;
	
	@Override
	public boolean verificaSeEhFeriado(Date date) {

		return feriadoBovespaDao.verificaSeEhFeriado(date);
		
	}

}
