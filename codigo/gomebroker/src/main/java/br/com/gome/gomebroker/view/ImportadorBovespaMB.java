package br.com.gome.gomebroker.view;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import br.com.gome.gomebroker.constant.StatusImportacaoBovespa;
import br.com.gome.gomebroker.constant.TipoArquivoBovespa;
import br.com.gome.gomebroker.service.ImportadorDadosDiariosBovespa;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@Named
@ApplicationScoped
public class ImportadorBovespaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Integer POLL_JOBS_IMPORTACAO_MAX_SIZE = 5;
	
	@Inject private ResourceBundle bundle;
	@Inject Logger logger;
	
	private Thread threadAtualizaContadorJobsImportacao;
	private Thread threadMantemHistoricoJobsImportacao;

	private JobImportacaoBovespa jobSelecionado;
	private Date dataImportacao = new Date();
	private TipoArquivoBovespa tipoArquivoSelecionado;
	private List<JobImportacaoBovespa> historicoJobsImportacao;
	private int contadorJobsImportacao;
	
	@PostConstruct
	public void init() {
		
		setHistoricoJobsImportacao(new ArrayList<JobImportacaoBovespa>());
		inciaThreadAtualizacaoContadorJobsImportacao();
		iniciaThreadMantemHistoricoJobsImportacao();
		setContadorJobsImportacao(0);
		
	}
	
	@PreDestroy
	public void destroy() {
		
		if (null != threadAtualizaContadorJobsImportacao && threadAtualizaContadorJobsImportacao.isAlive()) {
			threadAtualizaContadorJobsImportacao.interrupt();
		}
		
		if (null != threadMantemHistoricoJobsImportacao && threadMantemHistoricoJobsImportacao.isAlive()) {
			threadMantemHistoricoJobsImportacao.interrupt();
		}
		
	}
	
	public void importar() {
		
		if (getContadorJobsImportacao() == POLL_JOBS_IMPORTACAO_MAX_SIZE) {
		
			FacesContext.getCurrentInstance().addMessage(null,
														 new FacesMessage(FacesMessage.SEVERITY_INFO,
																 		  bundle.getString("view.importacao.bovespa.message.limite-importacoes-em-execucao", POLL_JOBS_IMPORTACAO_MAX_SIZE),
																 		  null));

		} else {

			incrementaContadorJobsImportacao();
			ImportadorDadosDiariosBovespa importadorBovespa = Beans.getReference(ImportadorDadosDiariosBovespa.class);
			Date inicioImportacao = new Date();
			Future<StatusImportacaoBovespa> statusImportacaoBovespa = importadorBovespa.baixarEImportarArquivoBovespa(getTipoArquivoSelecionado(), getDataImportacao());
			getHistoricoJobsImportacao().add(new JobImportacaoBovespa(statusImportacaoBovespa, inicioImportacao, getTipoArquivoSelecionado(), getDataImportacao()));
			FacesContext.getCurrentInstance().addMessage(null,
														 new FacesMessage(FacesMessage.SEVERITY_INFO,
																		  bundle.getString("view.importacao.bovespa.message.importacao-iniciada"),
																		  null));

		}
			
	}
	
	public void cancelar() {
		
		if (null != getJobSelecionado() && !getJobSelecionado().getStatusImportacao().isDone()) {
		
			getJobSelecionado().getStatusImportacao().cancel(true);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("view.importacao.bovespa.message.importacao-cancelada")));
			
		}
		
	}
	
	public void limparHistoricoJobsImportacao() {
		
		for (int i=0; i<getHistoricoJobsImportacao().size(); i++) {
			
			JobImportacaoBovespa job = getHistoricoJobsImportacao().get(i);
			
			if (job.getStatusImportacao().isDone()) {
				
				if (!job.getJaMarcadoComoTerminado()) {
					
					decrementaContadorJobsImportacao();
					job.setJaMarcadoComoTerminado(Boolean.TRUE);
					
				}
				
				getHistoricoJobsImportacao().remove(job);
				
			}
			
		}
		
	}
	
	public void iniciaThreadMantemHistoricoJobsImportacao() {
		
		threadMantemHistoricoJobsImportacao = new Thread(new Runnable() {
			
			@Override
			public void run() {

				logger.trace("Executando manutencao do historico de jobs de importacao bovespa em " + new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date()));
				
				Date baseLine = new Date();
				long _24horasEmMilisegundos = 86400000;
				baseLine.setTime(baseLine.getTime() - _24horasEmMilisegundos);
				
				for (int i=0; i<getHistoricoJobsImportacao().size(); i++) {
					
					JobImportacaoBovespa job = getHistoricoJobsImportacao().get(i);
					
					if (job.getStatusImportacao().isDone() && job.getDataDeInicio().before(baseLine)) {
						
						if (!job.getJaMarcadoComoTerminado()) {
							
							decrementaContadorJobsImportacao();
							job.setJaMarcadoComoTerminado(Boolean.TRUE);
							
						}
						
						getHistoricoJobsImportacao().remove(job);
						
					}
					
				}
				
				try {
					Thread.sleep(_24horasEmMilisegundos);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
		threadMantemHistoricoJobsImportacao.start();
		
	}

	public void inciaThreadAtualizacaoContadorJobsImportacao() {
	
		threadAtualizaContadorJobsImportacao = new Thread(new Runnable() {
			
			@Override
			public void run() {

				logger.trace("Executando atualizacao do historico de jobs de importacao bovespa em " + new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date()));
				
				for (JobImportacaoBovespa job : getHistoricoJobsImportacao()) {
					
					if (job.getStatusImportacao().isDone() && !job.getJaMarcadoComoTerminado()) {
						
						decrementaContadorJobsImportacao();
						job.setJaMarcadoComoTerminado(Boolean.TRUE);
						
					}
					
				}
				
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			
		});
		
		threadAtualizaContadorJobsImportacao.start();
		
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

	public TipoArquivoBovespa getTipoArquivoSelecionado() {
		return tipoArquivoSelecionado;
	}

	public void setTipoArquivoSelecionado(TipoArquivoBovespa tipoArquivoSelecionado) {
		this.tipoArquivoSelecionado = tipoArquivoSelecionado;
	}

	public List<JobImportacaoBovespa> getHistoricoJobsImportacao() {
		return historicoJobsImportacao;
	}

	public void setHistoricoJobsImportacao(List<JobImportacaoBovespa> historicoJobsImportacao) {
		this.historicoJobsImportacao = historicoJobsImportacao;
	}
	
	public JobImportacaoBovespa getJobSelecionado() {
		return jobSelecionado;
	}

	public void setJobSelecionado(JobImportacaoBovespa jobSelecionado) {
		this.jobSelecionado = jobSelecionado;
	}

	public int getContadorJobsImportacao() {
		return contadorJobsImportacao;
	}

	public void setContadorJobsImportacao(int contadorJobsImportacao) {
		this.contadorJobsImportacao = contadorJobsImportacao;
	}
	
	public void incrementaContadorJobsImportacao() {
		setContadorJobsImportacao(++this.contadorJobsImportacao);
	}
	
	public void decrementaContadorJobsImportacao() {
		setContadorJobsImportacao(--this.contadorJobsImportacao);
	}
	
	public class JobImportacaoBovespa {
		
		private Future<StatusImportacaoBovespa> statusImportacao;
		private Date dataDeInicio;
		private TipoArquivoBovespa tipoArquivoBovespa;
		private Date dataArquivoBovespa;
		private Boolean jaMarcadoComoTerminado;
		
		public JobImportacaoBovespa(Future<StatusImportacaoBovespa> status, Date dataInicioImportacao, TipoArquivoBovespa tipoArquivoBovespa, Date dataArquivoBovespa) {
			this.setStatusImportacao(status);
			this.setDataDeInicio(dataInicioImportacao);
			this.setTipoArquivoBovespa(tipoArquivoBovespa);
			this.setDataArquivoBovespa(dataArquivoBovespa);
		}
		
		public String status() {
			
			try {
			
				if (!getStatusImportacao().isDone()) {
				
					return bundle.getString("view.importacao.bovespa.message.em-andamento");
					
				} else {
				
					StatusImportacaoBovespa status = getStatusImportacao().get();
					return bundle.getString(status.getMessageKey());
					
				}
				
			} catch (InterruptedException e) {
				
				return bundle.getString("core.importacao.bovespa.message.cancelada");
				
			} catch (CancellationException e) {
				
				return bundle.getString("core.importacao.bovespa.message.cancelada");
				
			} catch (ExecutionException e) {
				
				return bundle.getString("view.erro-inesperado");
				
			}
			
		}

		public Date getDataDeInicio() {
			return dataDeInicio;
		}

		private void setDataDeInicio(Date dataDeInicio) {
			this.dataDeInicio = dataDeInicio;
		}
		
		public Future<StatusImportacaoBovespa> getStatusImportacao() {
			return statusImportacao;
		}

		private void setStatusImportacao(Future<StatusImportacaoBovespa> statusImportacao) {
			this.statusImportacao = statusImportacao;
		}

		public TipoArquivoBovespa getTipoArquivoBovespa() {
			return tipoArquivoBovespa;
		}

		public void setTipoArquivoBovespa(TipoArquivoBovespa tipoArquivoBovespa) {
			this.tipoArquivoBovespa = tipoArquivoBovespa;
		}

		public Date getDataArquivoBovespa() {
			return dataArquivoBovespa;
		}

		public void setDataArquivoBovespa(Date dataArquivoBovespa) {
			this.dataArquivoBovespa = dataArquivoBovespa;
		}
		
		public Boolean getJaMarcadoComoTerminado() {
			return jaMarcadoComoTerminado;
		}

		public void setJaMarcadoComoTerminado(Boolean jaMarcadoComoTerminado) {
			this.jaMarcadoComoTerminado = jaMarcadoComoTerminado;
		}
		
	}
	
}
