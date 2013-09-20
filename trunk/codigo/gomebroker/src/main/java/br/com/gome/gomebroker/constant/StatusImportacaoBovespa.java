package br.com.gome.gomebroker.constant;

public enum StatusImportacaoBovespa {

	FERIADO_BOVESPA("bovespa-holiday","core.importacao.bovespa.message.feriado-bovespa"),
	ARQUIVO_INEXISTENTE_DOWNLOAD("nonexistent-download-file","core.importacao.bovespa.message.arquivo-download-inexistente"),
	FALHA("task-failed","core.importacao.bovespa.message.falha-inesperada"),
	SUCESSO("task-successfully-completed","core.importacao.bovespa.message.finalizada-sucesso"), 
	CANCELADA("task-cancelled","core.importacao.bovespa.message.cancelada");

	private String key;
	private String messageKey;
	
	private StatusImportacaoBovespa(String key, String messageKey) {
		this.key = key;
		this.messageKey = messageKey;
	}
	
	public String getMessageKey() {
		return this.messageKey;
	}
	
	public String getKey() {
		return this.key;
	}
	
}
