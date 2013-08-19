package br.com.gome.gomebroker.business;

import br.com.gome.gomebroker.domain.Empresa;
import br.com.gome.gomebroker.persistence.EmpresaDAO;

public interface EmpresaBC extends BaseBC<Empresa, Long, EmpresaDAO> {

	Empresa findByNomeResumido(String nomeResumido);

}
