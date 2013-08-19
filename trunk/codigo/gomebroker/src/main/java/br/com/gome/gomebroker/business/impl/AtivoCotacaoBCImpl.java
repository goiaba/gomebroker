package br.com.gome.gomebroker.business.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Named;

import br.com.gome.gomebroker.business.AtivoCotacaoBC;
import br.com.gome.gomebroker.domain.AtivoCotacao;
import br.com.gome.gomebroker.domain.AtivoCotacaoPK;
import br.com.gome.gomebroker.persistence.AtivoCotacaoDAO;

@Named
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AtivoCotacaoBCImpl extends BaseBCImpl<AtivoCotacao, AtivoCotacaoPK, AtivoCotacaoDAO> implements AtivoCotacaoBC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
