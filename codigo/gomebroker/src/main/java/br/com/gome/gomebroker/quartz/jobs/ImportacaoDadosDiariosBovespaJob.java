package br.com.gome.gomebroker.quartz.jobs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;

import br.com.gome.gomebroker.constant.TipoArquivoBovespa;
import br.com.gome.gomebroker.service.ImportadorDadosDiariosBovespa;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

public class ImportacaoDadosDiariosBovespaJob implements Job {

	private Logger logger = Beans.getReference(Logger.class);
	private ResourceBundle bundle = Beans.getReference(ResourceBundle.class);
	private ImportadorDadosDiariosBovespa importadorDiarioBovespa = Beans.getReference(ImportadorDadosDiariosBovespa.class);
	
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

		logger.trace(bundle.getString("core.job.inicio.importacao-dados-bovespa", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())));
		
		importadorDiarioBovespa.baixarEImportarArquivoBovespa(TipoArquivoBovespa.Diario, new Date());
			
		logger.trace(bundle.getString("core.job.fim.importacao-dados-bovespa", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())));
		
	}
	
}
