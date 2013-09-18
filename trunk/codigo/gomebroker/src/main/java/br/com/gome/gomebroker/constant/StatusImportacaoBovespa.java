package br.com.gome.gomebroker.constant;

public enum StatusImportacaoBovespa {

	FERIADO_BOVESPA("core.importacao.bovespa.message.feriado-bovespa"),
	ARQUIVO_INEXISTENTE_DOWNLOAD("core.importacao.bovespa.message.arquivo-download-inexistente"),
	FALHA("core.importacao.bovespa.message.falha-inesperada"),
	SUCESSO("core.importacao.bovespa.message.finalizada-sucesso"), 
	CANCELADA("core.importacao.bovespa.message.cancelada");

	private String messageKey;
	
	private StatusImportacaoBovespa(String messageKey) {
		this.messageKey = messageKey;
	}
	
	public String getMessageKey() {
		return this.messageKey;
	}
	
}
