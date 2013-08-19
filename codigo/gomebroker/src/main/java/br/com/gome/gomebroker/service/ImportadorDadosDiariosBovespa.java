package br.com.gome.gomebroker.service;

import java.util.Date;

import javax.ejb.Local;

import br.com.gome.gomebroker.constant.TipoArquivoBovespa;
import br.com.gome.gomebroker.exception.FeriadoBovespaException;

@Local
public interface ImportadorDadosDiariosBovespa {
	
	void baixarEImportarArquivoBovespa(TipoArquivoBovespa tipoArquivo, Date data) throws FeriadoBovespaException;

}
