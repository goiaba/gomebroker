package br.com.gome.gomebroker.persistence;

import br.com.gome.gomebroker.domain.Ativo;

public interface AtivoDAO extends BaseDAO<Ativo, Long> {

	Ativo findByCodigoNegociacao(String codigoNegociacao);

}
