package br.com.gome.gomebroker.business.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gome.gomebroker.business.AtivoBC;
import br.com.gome.gomebroker.domain.Ativo;
import br.com.gome.gomebroker.persistence.AtivoDAO;

@Named
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AtivoBCImpl extends BaseBCImpl<Ativo, Long, AtivoDAO> implements AtivoBC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject AtivoDAO ativoDao;
	
	@Override
	public Ativo findByCodigoNegociacao(String codigoNegociacao) {

		return ativoDao.findByCodigoNegociacao(codigoNegociacao);
		
	}

}
