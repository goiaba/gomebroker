package br.com.gome.gomebroker.view.managedbean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import br.com.gome.gomebroker.constant.TipoArquivoBovespa;
import br.com.gome.gomebroker.managedbean.ImportadorBovespaMB.TaskImportacaoBovespaInfo;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@Named
@RequestScoped
public class ImportadorBovespaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject br.com.gome.gomebroker.managedbean.ImportadorBovespaMB importadorBovespa;
	@Inject private ResourceBundle bundle;
	@Inject Logger logger;
	
	private TaskImportacaoBovespaInfo taskSelecionada;
	private TipoArquivoBovespa tipoArquivoSelecionado;
	private Date dataImportacao = new Date();
	
	public void teste() {
		importadorBovespa.getUsuarioResponsavelTask();
	}
	
	public void importar() {
		
		String result = importadorBovespa.importar(getTipoArquivoSelecionado(), getDataImportacao());
		
		if ("task-pool-is-empty".equals(result)) {
		
			FacesContext.getCurrentInstance().addMessage(null,
														 new FacesMessage(FacesMessage.SEVERITY_INFO,
																 		  bundle.getString("view.importacao.bovespa.message.limite-importacoes-em-execucao", br.com.gome.gomebroker.managedbean.ImportadorBovespaMB.POOL_JOBS_IMPORTACAO_MAX_SIZE),
																 		  null));

		} else {

			FacesContext.getCurrentInstance().addMessage(null,
														 new FacesMessage(FacesMessage.SEVERITY_INFO,
																		  bundle.getString("view.importacao.bovespa.message.importacao-iniciada"),
																		  null));

		}
			
	}
	
	public void cancelar() {
		
		if (null != getTaskSelecionada() && !getTaskSelecionada().getStatusImportacao().isDone()) {
		
			getTaskSelecionada().getStatusImportacao().cancel(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("core.importacao.bovespa.message.cancelada")));
			
		}
		
	}
	
	public List<TipoArquivoBovespa> getTiposArquivoBovespa() {
		return Arrays.asList(TipoArquivoBovespa.values());
	}
	
	public Date getDataImportacao() {
		return dataImportacao;
	}

	public void setDataImportacao(Date dataImportacao) {
		this.dataImportacao = dataImportacao;
	}

	public TaskImportacaoBovespaInfo getTaskSelecionada() {
		return taskSelecionada;
	}

	public void setTaskSelecionada(TaskImportacaoBovespaInfo taskSelecionada) {
		this.taskSelecionada = taskSelecionada;
	}

	public TipoArquivoBovespa getTipoArquivoSelecionado() {
		return tipoArquivoSelecionado;
	}

	public void setTipoArquivoSelecionado(TipoArquivoBovespa tipoArquivo) {
		this.tipoArquivoSelecionado = tipoArquivo;
	}

}
