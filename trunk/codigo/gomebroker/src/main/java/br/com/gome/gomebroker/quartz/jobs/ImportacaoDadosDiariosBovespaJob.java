package br.com.gome.gomebroker.quartz.jobs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;

import br.com.gome.gomebroker.constant.TipoArquivoBovespa;
import br.com.gome.gomebroker.managedbean.ImportadorBovespaMB;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

public class ImportacaoDadosDiariosBovespaJob implements Job {

	private Logger logger = Beans.getReference(Logger.class);
	private ResourceBundle bundle = Beans.getReference(ResourceBundle.class);
	private ImportadorBovespaMB importadorBovespa = Beans.getReference(ImportadorBovespaMB.class);
	
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

		logger.info(bundle.getString("core.quartz.job.importacao.bovespa.message.log.inicio", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())));
		importadorBovespa.importar(TipoArquivoBovespa.Diario, new Date(), true);
		logger.info(bundle.getString("core.quartz.job.importacao.bovespa.message.log.fim", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())));
		
	}
	
}
