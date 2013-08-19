package br.com.gome.gomebroker.constant;

public enum TipoArquivoBovespa {

	Diario("D", "ddMMyyyy"), 
	Mensal("M", "MMyyyy"), 
	Anual("A", "yyyy");

	private String sigla;
	private String mascara;

	private TipoArquivoBovespa(String sigla, String mascara) {

		this.sigla = sigla;
		this.mascara = mascara;

	}
	
	public String getSigla() {
		return this.sigla;
	}
	
	public String getMascara() {
		return this.mascara;
	}

}
