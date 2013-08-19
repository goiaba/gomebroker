package br.com.gome.gomebroker.business;

import javax.ejb.Local;

import br.com.gome.gomebroker.domain.Ativo;
import br.com.gome.gomebroker.persistence.AtivoDAO;

@Local
public interface AtivoBC extends BaseBC<Ativo, Long, AtivoDAO> {

	Ativo findByCodigoNegociacao(String codigoNegociacao);

}
