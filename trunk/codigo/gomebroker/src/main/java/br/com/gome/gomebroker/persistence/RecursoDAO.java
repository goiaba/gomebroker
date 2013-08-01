package br.com.gome.gomebroker.persistence;

import java.util.List;

import br.com.gome.gomebroker.domain.security.Papel;
import br.com.gome.gomebroker.domain.security.Recurso;

public interface RecursoDAO extends BaseDAO<Recurso, Long> {

	List<Recurso> findRecursosDisponiveis(Papel papel);

	List<Recurso> findRecursosPorTipo(String... tipoRecursos);

}
