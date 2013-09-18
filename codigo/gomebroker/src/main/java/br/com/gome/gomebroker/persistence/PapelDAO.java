package br.com.gome.gomebroker.persistence;

import java.util.List;

import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.domain.security.Papel;
import br.com.gome.gomebroker.domain.security.Recurso;

public interface PapelDAO extends BaseDAO<Papel, Long> {

	List<Papel> findPapeisValidos(Usuario usuario);

	Papel findPapelPadrao(Usuario usuario);

	List<Papel> findPapeisAtivosComAcessoAoRecurso(Recurso recurso);

	List<Papel> findPapeisAtivosSemAcessoAoRecurso(Recurso recurso);

	List<Papel> findPapeisAtivos();

}
