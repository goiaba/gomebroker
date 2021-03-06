package br.com.gome.gomebroker.business;

import java.util.List;

import javax.ejb.Local;

import br.com.gome.gomebroker.domain.security.Papel;
import br.com.gome.gomebroker.domain.security.Recurso;
import br.com.gome.gomebroker.persistence.RecursoDAO;

@Local
public interface RecursoBC extends BaseBC<Recurso, Long, RecursoDAO> {

	List<Recurso> getRecursosDisponiveis(Papel papel);

	List<Recurso> getRecursosPorTipo(boolean apenasRecursosAtivos, String... tiposRecursos);

	List<Recurso> find(String searchString);

	void merge(Recurso recurso, List<Papel> listaDePapeisAtivosComAcessoAoRecurso);

	void persist(Recurso recurso, List<Papel> listaDePapeisAtivosComAcessoAoRecurso);

}
