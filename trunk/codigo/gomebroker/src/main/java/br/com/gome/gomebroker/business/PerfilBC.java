package br.com.gome.gomebroker.business;

import java.util.Set;

import br.com.gome.gomebroker.domain.Perfil;
import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.persistence.PerfilDAO;

public interface PerfilBC extends BaseBC<Perfil, Long, PerfilDAO> {

	Set<Perfil> getPerfisValidosDoUsuario(Usuario usuario);

	Perfil getPerfilPadrao(Usuario usuario);
	
}
