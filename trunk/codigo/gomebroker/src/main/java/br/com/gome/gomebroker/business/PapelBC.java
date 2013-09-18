package br.com.gome.gomebroker.business;

import java.util.List;

import javax.ejb.Local;

import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.domain.security.Papel;
import br.com.gome.gomebroker.domain.security.Recurso;
import br.com.gome.gomebroker.persistence.PapelDAO;

@Local
public interface PapelBC extends BaseBC<Papel, Long, PapelDAO> {

	List<Papel> getPapeisValidosDoUsuario(Usuario usuario);

	Papel getPapelPadrao(Usuario usuario);

	List<Papel> getPapeisAtivosComAcessoAoRecurso(Recurso recurso);

	List<Papel> getPapeisAtivosSemAcessoAoRecurso(Recurso recurso);

	List<Papel> getPapeisAtivos();

}
