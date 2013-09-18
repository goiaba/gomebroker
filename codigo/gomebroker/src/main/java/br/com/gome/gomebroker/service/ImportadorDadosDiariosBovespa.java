package br.com.gome.gomebroker.service;

import java.util.Date;
import java.util.concurrent.Future;

import javax.ejb.Local;

import br.com.gome.gomebroker.constant.StatusImportacaoBovespa;
import br.com.gome.gomebroker.constant.TipoArquivoBovespa;

@Local
public interface ImportadorDadosDiariosBovespa {
	
	Future<StatusImportacaoBovespa> baixarEImportarArquivoBovespa(TipoArquivoBovespa tipoArquivo, Date data);
	
}
