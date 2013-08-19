package br.com.gome.gomebroker.business.impl;

import javax.inject.Inject;

import br.com.gome.gomebroker.business.EmpresaBC;
import br.com.gome.gomebroker.domain.Empresa;
import br.com.gome.gomebroker.persistence.EmpresaDAO;

public class EmpresaBCImpl extends BaseBCImpl<Empresa, Long, EmpresaDAO> implements EmpresaBC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject private EmpresaDAO empresaDao;
	
	@Override
	public Empresa findByNomeResumido(String nomeResumido) {
	
		return empresaDao.findByNomeResumido(nomeResumido);
		
	}

}
