package br.com.gome.gomebroker.managedbean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import br.com.gome.gomebroker.constant.SecurityConstants;
import br.com.gome.gomebroker.constant.StatusImportacaoBovespa;
import br.com.gome.gomebroker.constant.TipoArquivoBovespa;
import br.com.gome.gomebroker.domain.Usuario;
import br.com.gome.gomebroker.service.ImportadorDadosDiariosBovespa;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.util.Beans;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@Named("importadorBovespaCore")
@ApplicationScoped
public class ImportadorBovespaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Integer POOL_JOBS_IMPORTACAO_MAX_SIZE = 5;
	
	@Inject private Logger logger;
	@Inject private ResourceBundle bundle;
	@Inject private SecurityContext securityContext;
	
	private int contadorTasksImportacao;
	private Thread threadAtualizaHistoricoTasksImportacao;
	private List<TaskImportacaoBovespaInfo> historicoTasksImportacao;
	
	@PostConstruct
	public void init() {
		
		setContadorTasksImportacao(0);
		setHistoricoTasksImportacao(new ArrayList<TaskImportacaoBovespaInfo>());

		iniciaThreadAtualizaHistoricoTasksImportacao();
		
	}
	
	@PreDestroy
	public void destroy() {
		
		if (null != threadAtualizaHistoricoTasksImportacao && threadAtualizaHistoricoTasksImportacao.isAlive()) {
			threadAtualizaHistoricoTasksImportacao.interrupt();
		}
		
	}
	
	public String importar(TipoArquivoBovespa tipo, Date data, boolean isQuartzJob) {
		
		if (!isQuartzJob && getContadorTasksImportacao() == POOL_JOBS_IMPORTACAO_MAX_SIZE) {
		
			return "task-pool-is-empty";

		} else {

			incrementaContadorTasksImportacao();
			ImportadorDadosDiariosBovespa importadorBovespa = Beans.getReference(ImportadorDadosDiariosBovespa.class);
			Date inicioImportacao = new Date();
			Future<StatusImportacaoBovespa> statusImportacaoBovespa = importadorBovespa.baixarEImportarArquivoBovespa(tipo, data);
			getHistoricoTasksImportacao().add(new TaskImportacaoBovespaInfo(statusImportacaoBovespa, inicioImportacao, tipo, data, isQuartzJob ? null : getUsuarioResponsavelTask()));
			
			return "task-started";

		}
			
	}
	
	public String importar(TipoArquivoBovespa tipo, Date data) {
		
		return importar(tipo, data, false);
		
	}

	public void limpaHistoricoTasksImportacao(long milis) {
		
		Date baseLine = new Date();
		baseLine.setTime(baseLine.getTime() - milis);
		
		for (int i=0; i<getHistoricoTasksImportacao().size(); i++) {
			
			TaskImportacaoBovespaInfo task = getHistoricoTasksImportacao().get(i);
			
			if (task.getStatusImportacao().isDone()) {
				
				if (task.getDataDeInicio().before(baseLine)) {
				
					getHistoricoTasksImportacao().remove(task);
					
				} else if (!task.getJaMarcadoComoTerminado()) {
					
					decrementaContadorTasksImportacao();
					task.setJaMarcadoComoTerminado(Boolean.TRUE);
					
				}
				
			}
			
		}
		
	}
	
	public void iniciaThreadAtualizaHistoricoTasksImportacao() {
		
		threadAtualizaHistoricoTasksImportacao = new Thread(new Runnable() {
			
			@Override
			public void run() {

				while (true) {
				
					//Remove as tasks iniciadas a mais que 24h e ja finalizadas.
					limpaHistoricoTasksImportacao(86400000);
					logger.info(bundle.getString("core.importacao.bovespa.message.log.manutencao-historico-tasks",
												 new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss").format(new Date())));
					
					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				
			}
			
		});
		
		threadAtualizaHistoricoTasksImportacao.start();
		
	}
	
	public Usuario getUsuarioResponsavelTask() {

		return (Usuario) securityContext.getUser().getAttribute(SecurityConstants.USUARIO_SESSAO_KEY);
		
	}

	public List<TaskImportacaoBovespaInfo> getHistoricoTasksImportacao() {
		return historicoTasksImportacao;
	}

	public void setHistoricoTasksImportacao(List<TaskImportacaoBovespaInfo> historicoTasksImportacao) {
		this.historicoTasksImportacao = historicoTasksImportacao;
	}
	
	public int getContadorTasksImportacao() {
		return contadorTasksImportacao;
	}

	public void setContadorTasksImportacao(int contadorTasksImportacao) {
		this.contadorTasksImportacao = contadorTasksImportacao;
	}
	
	protected void incrementaContadorTasksImportacao() {
		setContadorTasksImportacao(++this.contadorTasksImportacao);
	}
	
	protected void decrementaContadorTasksImportacao() {
		setContadorTasksImportacao(--this.contadorTasksImportacao);
	}
	
	public class TaskImportacaoBovespaInfo {
		
		private Future<StatusImportacaoBovespa> statusImportacao;
		private Usuario responsavel;
		private Date dataDeInicio;
		private TipoArquivoBovespa tipoArquivoBovespa;
		private Date dataArquivoBovespa;
		private Boolean jaMarcadoComoTerminado;
		
		public TaskImportacaoBovespaInfo(Future<StatusImportacaoBovespa> status, Date dataInicioImportacao, TipoArquivoBovespa tipoArquivoBovespa, Date dataArquivoBovespa, Usuario responsavel) {
			this.setStatusImportacao(status);
			this.setDataDeInicio(dataInicioImportacao);
			this.setTipoArquivoBovespa(tipoArquivoBovespa);
			this.setDataArquivoBovespa(dataArquivoBovespa);
			this.setResponsavel(responsavel);
			this.setJaMarcadoComoTerminado(Boolean.FALSE);
		}
		
		public String status() {
			
			try {
			
				if (!getStatusImportacao().isDone()) {
				
					return "task-running";
					
				} else {
				
					StatusImportacaoBovespa status = getStatusImportacao().get();
					return status.getKey();
					
				}
				
			} catch (InterruptedException e) {
				
				return "task-cancelled";
				
			} catch (CancellationException e) {
				
				return "task-cancelled";
				
			} catch (ExecutionException e) {
				
				return "unknown-error";
				
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

		public Usuario getResponsavel() {
			return responsavel;
		}

		public void setResponsavel(Usuario responsavel) {
			this.responsavel = responsavel;
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
