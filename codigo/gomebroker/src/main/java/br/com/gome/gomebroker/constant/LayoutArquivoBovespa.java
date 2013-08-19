package br.com.gome.gomebroker.constant;

public enum LayoutArquivoBovespa {
	
	TipoDoRegistro(0,2,0),
	
	Header_NomeDoArquivo(2, 15, 0),
	Header_CodigoDaOrigem(15, 23, 0),
	Header_DataDaGeracaoDoArquivo(23, 31, 0),
	Header_Reserva(31, 245, 0),
	
	Cotacao_DataDoPregao(2, 10, 0),
	Cotacao_CodigoBDI(10, 12, 0),
	Cotacao_CodigoDeNegociacaoDoPapel(12, 24, 0),
	Cotacao_TipoDeMercado(24, 27, 0),
	Cotacao_NomeResumidoDaEmpresaEmissoraDoPapel(27, 39, 0),
	Cotacao_EspecificacaoDoPapel(39, 49, 0),
	Cotacao_PrazoEmDiasDoMercadoATermo(49, 52, 0),
	Cotacao_MoedaDeReferencia(52, 56, 0),
	Cotacao_PrecoDeAbertura(56, 69, 2),
	Cotacao_PrecoMaximo(69, 82, 2),
	Cotacao_PrecoMinimo(82, 95, 2),
	Cotacao_PrecoMedio(95, 108, 2),
	Cotacao_PrecoDoUltimoNegocio(108, 121, 2),
	Cotacao_PrecoDaMelhorOfertaCompra(121, 134, 2),
	Cotacao_PrecoDaMelhorOfertaVenda(134, 147, 2),
	Cotacao_NumeroDeNegocios(147, 152, 0),
	Cotacao_QuantidadeDeTitulosNegociados(152, 170, 0),
	Cotacao_VolumeTotal(170, 188, 2),
	Cotacao_PrecoDeExercicioOpcaoOuValorContratoTermo(188, 201, 2),
	Cotacao_IndicadorDeCorrecaoDePrecosDeExercicioOuValoresDeContrato(201, 202, 0),
	Cotacao_DataDeVencimento(202, 210, 0),
	Cotacao_FatorDeCotacao(210, 217, 0),
	Cotacao_PrecoDeExercicioEmPontosOpcaoOuValorContratoPontosTermo(217, 230, 6),
	Cotacao_CodigoISIN(230, 242, 0),
	Cotacao_NumeroDeDistribuicaoDoPapel(242, 245, 0),		
	
	Trailer_NomeDoArquivo(2, 15, 0),        
	Trailer_CodigoDaOrigem(15, 23, 0),      
	Trailer_DataDaGeracaoDoArquivo(23, 31, 0),
	Trailer_TotalDeRegistros(31, 42, 0),
	Trailer_Reserva(31, 245, 0);
	
	private int inicio;
	private int fim;
	private int numeroCasasDecimais;
	
	private LayoutArquivoBovespa(int inicio, int fim, int numeroCasasDecimais) {
		
		this.inicio = inicio;
		this.fim = fim;
		this.numeroCasasDecimais = numeroCasasDecimais;
		
	}

	public int getInicio() {
		return inicio;
	}

	public int getFim() {
		return fim;
	}

	public int getNumeroCasasDecimais() {
		return numeroCasasDecimais;
	}

}