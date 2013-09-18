package br.com.gome.gomebroker.business.impl;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gome.gomebroker.business.EmpresaBC;
import br.com.gome.gomebroker.domain.Empresa;
import br.com.gome.gomebroker.persistence.EmpresaDAO;

@Named
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EmpresaBCImpl extends BaseBCImpl<Empresa, Long, EmpresaDAO> implements EmpresaBC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject private EmpresaDAO empresaDao;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Empresa findByNomeResumido(String nomeResumido) {
	
		return empresaDao.findByNomeResumido(nomeResumido);
		
	}

}
