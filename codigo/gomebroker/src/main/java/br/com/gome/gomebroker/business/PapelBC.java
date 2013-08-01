package br.com.gome.gomebroker.business;

import java.util.List;

import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.domain.security.Papel;
import br.com.gome.gomebroker.persistence.PapelDAO;

public interface PapelBC extends BaseBC<Papel, Long, PapelDAO> {

	List<Papel> getPapeisValidosDoUsuario(Usuario usuario);

	Papel getPapelPadrao(Usuario usuario);

}
