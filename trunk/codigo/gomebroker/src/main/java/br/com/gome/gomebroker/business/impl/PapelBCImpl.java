package br.com.gome.gomebroker.business.impl;

import java.util.List;

import javax.inject.Inject;

import br.com.gome.gomebroker.business.PapelBC;
import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.domain.security.Papel;
import br.com.gome.gomebroker.persistence.PapelDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;

@BusinessController
public class PapelBCImpl extends BaseBCImpl<Papel, Long, PapelDAO> implements PapelBC {

	private static final long serialVersionUID = 1L;
	
	@Inject private PapelDAO papelDao;

	@Override
	public List<Papel> getPapeisValidosDoUsuario(Usuario usuario) {

		return papelDao.findPapeisValidos(usuario);
		
	}

	@Override
	public Papel getPapelPadrao(Usuario usuario) {

		return papelDao.findPapelPadrao(usuario);
		
	}

}
