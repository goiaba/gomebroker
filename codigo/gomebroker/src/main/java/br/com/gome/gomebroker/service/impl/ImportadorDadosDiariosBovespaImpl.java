package br.com.gome.gomebroker.service.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.slf4j.Logger;

import br.com.gome.gomebroker.business.AtivoBC;
import br.com.gome.gomebroker.business.AtivoCotacaoBC;
import br.com.gome.gomebroker.business.EmpresaBC;
import br.com.gome.gomebroker.business.FeriadoBovespaBC;
import br.com.gome.gomebroker.constant.FatorCotacaoAtivo;
import br.com.gome.gomebroker.constant.LayoutArquivoBovespa;
import br.com.gome.gomebroker.constant.StatusImportacaoBovespa;
import br.com.gome.gomebroker.constant.TipoArquivoBovespa;
import br.com.gome.gomebroker.constant.TipoMercado;
import br.com.gome.gomebroker.domain.Ativo;
import br.com.gome.gomebroker.domain.AtivoCotacao;
import br.com.gome.gomebroker.domain.Empresa;
import br.com.gome.gomebroker.exception.FormatoArquivoInvalidoException;
import br.com.gome.gomebroker.service.ImportadorDadosDiariosBovespa;
import br.com.gome.gomebroker.util.StringConverter;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@Named
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ImportadorDadosDiariosBovespaImpl implements ImportadorDadosDiariosBovespa {
	
	private static final int BATCH_SIZE = 1000;
	private static final String TIPO_REGISTRO_HEADER = "00";
    private static final String TIPO_REGISTRO_COTACAO = "01";
    private static final String TIPO_REGISTRO_TRAILER = "99";
	
    private static final String MASCARA_DATA_BOVESPA = "yyyyMMdd";
	private static final String PREFIXO_TITULO = "COTAHIST_?";
	private static final String SUFIXO_TITULO = ".ZIP";
	//TODO:Mover constantes abaixo para demoiselle.properties	
	private static final String URL = "http://www.bmfbovespa.com.br/InstDados/SerHist/";
	private static final String FS = System.getProperty("file.separator");
	private static final String PATH_ARQUIVO_LOCAL = FS + "tmp" + FS;
	
	@Resource UserTransaction uTx;
	@Resource SessionContext sCtx;
	@Inject ResourceBundle bundle;
	@Inject Logger logger;
	
	@Inject private AtivoBC	ativoBC;
	@Inject private AtivoCotacaoBC ativoCotacaoBC;
	@Inject private FeriadoBovespaBC feriadoBovespaBC;
	@Inject private EmpresaBC empresaBC;

	@Override
	@Asynchronous
	public Future<StatusImportacaoBovespa> baixarEImportarArquivoBovespa(TipoArquivoBovespa tipoArquivo, Date data) {
		
		if (TipoArquivoBovespa.Diario.equals(tipoArquivo) && feriadoBovespaBC.ehFeriadoBovespa(data)) {
			return new AsyncResult<StatusImportacaoBovespa>(StatusImportacaoBovespa.FERIADO_BOVESPA);
		}
		
		try {
			
			String nomeArquivo = montaTitulo(tipoArquivo, data);
			URL arquivoRemoto = new URL(URL + nomeArquivo);
			File arquivoLocal = new File(PATH_ARQUIVO_LOCAL + nomeArquivo);
			
			baixarArquivoDeCotacoesBovespa(arquivoRemoto, arquivoLocal);
			
			List<String> arquivosDescompactados = descompactarArquivoDeCotacoesBovespa(arquivoLocal);
			
			for (String nomeArquivoDescompactado : arquivosDescompactados) {
				
				importarRegistrosDoArquivoDeCotacoesBovespaParaBD(tipoArquivo, nomeArquivoDescompactado, data);
				
			}

			return new AsyncResult<StatusImportacaoBovespa>(StatusImportacaoBovespa.SUCESSO);
			
		} catch (InterruptedException e) {
		
			logger.error(e.getMessage());
			return new AsyncResult<StatusImportacaoBovespa>(StatusImportacaoBovespa.CANCELADA);
			
		} catch (FileNotFoundException e) {
			
			logger.error(e.getMessage());
			return new AsyncResult<StatusImportacaoBovespa>(StatusImportacaoBovespa.ARQUIVO_INEXISTENTE_DOWNLOAD);
		
		} catch (Exception e) {
			
			logger.error(e.getMessage());
			return new AsyncResult<StatusImportacaoBovespa>(StatusImportacaoBovespa.FALHA);
			
		}
		
	}
	
	private void baixarArquivoDeCotacoesBovespa(URL arquivoRemoto, File arquivoLocal) throws FileNotFoundException, IOException {

		ReadableByteChannel rbc = null;
		FileOutputStream fos = null;

		try {

			rbc = Channels.newChannel(arquivoRemoto.openStream());
			fos = new FileOutputStream(arquivoLocal);

			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			
		} finally {

				try {
					
					if (null != fos) fos.close();
					if (null != rbc) rbc.close();
					
				} catch (IOException e) {}
			
		}
			
	}
	
	private List<String> descompactarArquivoDeCotacoesBovespa(File arquivoZip) throws FileNotFoundException, IOException {
		
		List<String> arquivos = new ArrayList<String>();
		
		byte[] buffer = new byte[1024];
		
		ZipInputStream zis = new ZipInputStream(new FileInputStream(arquivoZip));

		ZipEntry ze = zis.getNextEntry();

		while (null != ze) {

			String fileName = PATH_ARQUIVO_LOCAL + ze.getName();
			arquivos.add(fileName);
			
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file);

			int len;
			
			while ((len = zis.read(buffer)) > 0) {
				
				fos.write(buffer, 0, len);
				
			}

			fos.close();
			
			ze = zis.getNextEntry();
			
		}

		zis.closeEntry();
		zis.close();
		
		return arquivos;
		
	}
	
	private void importarRegistrosDoArquivoDeCotacoesBovespaParaBD(TipoArquivoBovespa tipo, String nomeArquivo, Date data) throws IOException, InterruptedException, ParseException {

		File file = new File(nomeArquivo);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));
        String line = reader.readLine();
        
        consomeHeader(line);

		line = consomeCotacoes(reader);

		consomeTrailer(line);

	}
	
	private void consomeHeader(String line) {

		String tipoRegistro = getStringValue(line, LayoutArquivoBovespa.TipoDoRegistro);
		
		if (TIPO_REGISTRO_HEADER.equals(tipoRegistro)) {

			String nomeArquivo = getStringValue(line, LayoutArquivoBovespa.Header_NomeDoArquivo);
	        String codigoOrigem = getStringValue(line, LayoutArquivoBovespa.Header_CodigoDaOrigem);
	        String dataGeracaoArquivo = getStringValue(line, LayoutArquivoBovespa.Header_DataDaGeracaoDoArquivo);
	
	        logger.info("Header ==> Arquivo Origem: " + nomeArquivo + " - Código da Origem: " + codigoOrigem + " - Data da Geracão do arquivo: " + dataGeracaoArquivo);
	        
		}

    }

    private void consomeTrailer(String line) {

		String tipoRegistro = getStringValue(line, LayoutArquivoBovespa.TipoDoRegistro);
		
		if (TIPO_REGISTRO_TRAILER.equals(tipoRegistro)) {

	        Integer totalRegistros = getIntegerValue(line, LayoutArquivoBovespa.Trailer_TotalDeRegistros) - 2;

	        logger.info("Trailer ==> Total de Registros no arquivo: " + totalRegistros.toString());
	        
		}

    }
	
	private String consomeCotacoes(BufferedReader reader) throws InterruptedException {

		String line;
		int count = 0;
		Map<String, Ativo> ativos = new HashMap<String, Ativo>();
    	Map<String, Empresa> empresas = new HashMap<String, Empresa>();
		
		try {

			while ((line = reader.readLine()) != null) {

				String tipoRegistro = getStringValue(line, LayoutArquivoBovespa.TipoDoRegistro);

				if (TIPO_REGISTRO_COTACAO.equals(tipoRegistro)) {

					if (count == 0) {

						if(sCtx.wasCancelCalled()) {
							throw new InterruptedException("Importação Bovespa cancelada pelo usuário.");
						}
						
						uTx.begin();
						
					}

					consomeCotacao(line, ativos, empresas);
					count++;
					
					if (count == BATCH_SIZE) {
						uTx.commit();
						count = 0;
						empresas.clear();
						ativos.clear();
					}

				} else {
					
					if (uTx.getStatus() != Status.STATUS_COMMITTED) {
						uTx.commit();
					}
					
					break;
					
				}

			}

			return line;
			
		} catch (InterruptedException e) {
			
			try {
				if (uTx.getStatus() != 6)
					uTx.rollback();
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}

			throw e;
			
		} catch (Exception e) {

			try {
				uTx.rollback();
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}

			throw new RuntimeException(e);

		}

	}
    
    private void consomeCotacao(String line, Map<String, Ativo> ativos, Map<String, Empresa> empresas) throws ParseException {
    	
    	TipoMercado tipoMercado = TipoMercado.getFromCodigo(getIntegerValue(line, LayoutArquivoBovespa.Cotacao_TipoDeMercado));

        if (!(TipoMercado.VISTA.equals(tipoMercado) || TipoMercado.FRACIONARIO.equals(tipoMercado)))
            return;
        
        String codigoNegociacao = getStringValue(line, LayoutArquivoBovespa.Cotacao_CodigoDeNegociacaoDoPapel);
        
        if (codigoNegociacao != null) {
 
            Ativo ativo = criaOuRecuperaAtivo(line, ativos, empresas);
            
            persisteCotacao(ativo, line);

        } else {

        	logger.error(bundle.getString("core.exception.formato-arquivo-bovespa-invalido", line));
            throw new FormatoArquivoInvalidoException(line);

        }
    	
    }
	
    private Ativo criaOuRecuperaAtivo(String line, Map<String, Ativo> ativos, Map<String, Empresa> empresas) throws ParseException {
        
    	String codigoNegociacao = getStringValue(line, LayoutArquivoBovespa.Cotacao_CodigoDeNegociacaoDoPapel);
    	
    	Ativo ativo = ativos.get(codigoNegociacao);
    	
    	if (null == ativo) {
    		
    		if (null == (ativo = ativoBC.findByCodigoNegociacao(codigoNegociacao))) {
    			
    			Empresa empresa = criaOuRecuperaEmpresa(line, empresas);
    			empresa.getAtivos().size();
    			
    			ativo = new Ativo();
    			
    	        ativo.setEmpresa(empresa);
    	        ativo.setCodigo(getStringValue(line, LayoutArquivoBovespa.Cotacao_CodigoDeNegociacaoDoPapel));
    	        ativo.setTipoMercado(TipoMercado.getFromCodigo(getIntegerValue(line, LayoutArquivoBovespa.Cotacao_TipoDeMercado)));
    	        ativo.setFatorCotacao(FatorCotacaoAtivo.getFromCodigo(getIntegerValue(line, LayoutArquivoBovespa.Cotacao_FatorDeCotacao)).getCodigo());
    	        ativo.setDataCadastro(new Date());
    	        
    		}

    		ativo = ativoBC.merge(ativo);
    		ativos.put(codigoNegociacao, ativo);
    		
    	}
    	
        return ativo;
        
    }
    
	private Empresa criaOuRecuperaEmpresa(String line, Map<String, Empresa> empresas) {

		String nomeResumido = getStringValue(line, LayoutArquivoBovespa.Cotacao_NomeResumidoDaEmpresaEmissoraDoPapel);
		
		Empresa empresa = empresas.get(nomeResumido);

		if (null == empresa) {

			if (null == (empresa = empresaBC.findByNomeResumido(nomeResumido))) {
			
				empresa = new Empresa();
	
				empresa.setNome(nomeResumido);
				empresa.setDataCadastro(new Date());
				
			}

			empresa = empresaBC.merge(empresa);
			empresas.put(nomeResumido, empresa);
			
		}
		
		return empresa;
		
	}
    
	private void persisteCotacao(Ativo ativo, String line) throws ParseException {
        
        AtivoCotacao cotacao = new AtivoCotacao(ativo, getDateValue(line, LayoutArquivoBovespa.Cotacao_DataDoPregao));
        
        cotacao.setIntraDiario(false);
        cotacao.setAbertura(getDoubleValue(line, LayoutArquivoBovespa.Cotacao_PrecoDeAbertura));
        cotacao.setMaximo(getDoubleValue(line, LayoutArquivoBovespa.Cotacao_PrecoMaximo));
        cotacao.setMinimo(getDoubleValue(line, LayoutArquivoBovespa.Cotacao_PrecoMinimo));
        cotacao.setMedio(getDoubleValue(line, LayoutArquivoBovespa.Cotacao_PrecoMedio));
        cotacao.setUltimo(getDoubleValue(line, LayoutArquivoBovespa.Cotacao_PrecoDoUltimoNegocio));
        cotacao.setMelhorOfertaCompra(getDoubleValue(line, LayoutArquivoBovespa.Cotacao_PrecoDaMelhorOfertaCompra));
        cotacao.setMelhorOfertaVenda(getDoubleValue(line, LayoutArquivoBovespa.Cotacao_PrecoDaMelhorOfertaVenda));
        cotacao.setVolume(getDoubleValue(line, LayoutArquivoBovespa.Cotacao_VolumeTotal));
        cotacao.setQuantidadeNegocios(getIntegerValue(line, LayoutArquivoBovespa.Cotacao_NumeroDeNegocios));
        cotacao.setVariacao((cotacao.getUltimo()-cotacao.getAbertura())/cotacao.getUltimo());
        
        ativoCotacaoBC.merge(cotacao);
        
    }

    private String montaTitulo(TipoArquivoBovespa tipo, Date data) {
		
		String titulo = PREFIXO_TITULO.replace("?", tipo.getSigla()) + new SimpleDateFormat(tipo.getMascara()).format(data) + SUFIXO_TITULO;
		
		return titulo;
		
	}
    
	private Integer getIntegerValue(String linha, LayoutArquivoBovespa campo) {
		
		if (null == linha)
			return null;
		
		String strCampo = linha.substring(campo.getInicio(), campo.getFim()).trim();
		
		return StringConverter.convert(strCampo, Integer.class);
		
	}
	
	private String getStringValue(String linha, LayoutArquivoBovespa campo) {
		
		if (null == linha)
			return null;
		
		return linha.substring(campo.getInicio(), campo.getFim()).trim();
		
	}
	
	private Date getDateValue(String linha, LayoutArquivoBovespa campo) {
		
		if (null == linha)
			return null;
		
		String strCampo = linha.substring(campo.getInicio(), campo.getFim()).trim();
		
		return StringConverter.convert(strCampo, MASCARA_DATA_BOVESPA);
		
	}
	
	private Double getDoubleValue(String linha, LayoutArquivoBovespa campo) {
		
		if (null == linha)
			return null;
		
		String strCampo = linha.substring(campo.getInicio(), campo.getFim()).trim();
		int divisor = 1;
		
		for (int i=0; i<campo.getNumeroCasasDecimais(); i++) {
			divisor *= 10; 
		}
		
		return StringConverter.convert(strCampo, Double.class) / divisor;
		
	}
	
	@SuppressWarnings("unused")
	private Integer getNumberOfLines(File file) throws IOException {

        LineNumberReader lnr = null;
        
        try {

            lnr = new LineNumberReader(new FileReader(file));
            lnr.skip(Long.MAX_VALUE);
        
            return lnr.getLineNumber();
            
        } finally {

            if (lnr != null) {
                
                try {
                    
                    lnr.close();
                    
                } catch (IOException ex) {}
                
            }

        }
        
    }
	
}
