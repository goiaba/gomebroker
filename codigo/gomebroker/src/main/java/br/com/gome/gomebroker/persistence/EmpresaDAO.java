package br.com.gome.gomebroker.persistence;

import br.com.gome.gomebroker.domain.Empresa;

public interface EmpresaDAO extends BaseDAO<Empresa, Long> {

	Empresa findByNomeResumido(String nomeResumido);

}
