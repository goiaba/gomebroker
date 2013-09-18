package br.com.gome.gomebroker.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gome.gomebroker.business.PapelBC;
import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.domain.security.Papel;
import br.com.gome.gomebroker.domain.security.Recurso;
import br.com.gome.gomebroker.persistence.PapelDAO;

@Named
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
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

	@Override
	public List<Papel> getPapeisAtivosComAcessoAoRecurso(Recurso recurso) {

		return papelDao.findPapeisAtivosComAcessoAoRecurso(recurso);
		
	}

	@Override
	public List<Papel> getPapeisAtivosSemAcessoAoRecurso(Recurso recurso) {

		return papelDao.findPapeisAtivosSemAcessoAoRecurso(recurso);
		
	}

	@Override
	public List<Papel> getPapeisAtivos() {

		return papelDao.findPapeisAtivos();
		
	}

}
