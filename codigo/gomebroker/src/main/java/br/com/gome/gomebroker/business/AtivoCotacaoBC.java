package br.com.gome.gomebroker.business;

import javax.ejb.Local;

import br.com.gome.gomebroker.domain.AtivoCotacao;
import br.com.gome.gomebroker.domain.AtivoCotacaoPK;
import br.com.gome.gomebroker.persistence.AtivoCotacaoDAO;

@Local
public interface AtivoCotacaoBC extends BaseBC<AtivoCotacao, AtivoCotacaoPK, AtivoCotacaoDAO> {

}
