package br.com.gome.gomebroker.business.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gome.gomebroker.business.FeriadoBovespaBC;
import br.com.gome.gomebroker.domain.FeriadoBovespa;
import br.com.gome.gomebroker.persistence.FeriadoBovespaDAO;

@Named
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FeriadoBovespaBCImpl extends BaseBCImpl<FeriadoBovespa, Long, FeriadoBovespaDAO> implements FeriadoBovespaBC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject private FeriadoBovespaDAO feriadoBovespaDao;
	
	@Override
	public boolean ehFeriadoBovespa(Date date) {

		Calendar cal = new GregorianCalendar();
		
		cal.setTime(date);
		
		boolean ehFinalDeSemana = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
		
		return ehFinalDeSemana || feriadoBovespaDao.ehFeriadoBovespa(date);
		
	}

}
