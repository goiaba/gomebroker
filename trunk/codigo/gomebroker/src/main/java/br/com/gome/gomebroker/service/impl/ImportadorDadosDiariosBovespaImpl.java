package br.com.gome.gomebroker.service.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import br.com.gome.gomebroker.business.AtivoBC;
import br.com.gome.gomebroker.business.AtivoCotacaoBC;
import br.com.gome.gomebroker.business.EmpresaBC;
import br.com.gome.gomebroker.business.FeriadoBovespaBC;
import br.com.gome.gomebroker.constant.FatorCotacaoAtivo;
import br.com.gome.gomebroker.constant.LayoutArquivoBovespa;
import br.com.gome.gomebroker.constant.TipoArquivoBovespa;
import br.com.gome.gomebroker.constant.TipoMercado;
import br.com.gome.gomebroker.domain.Ativo;
import br.com.gome.gomebroker.domain.AtivoCotacao;
import br.com.gome.gomebroker.domain.Empresa;
import br.com.gome.gomebroker.exception.FeriadoBovespaException;
import br.com.gome.gomebroker.exception.FormatoArquivoInvalidoException;
import br.com.gome.gomebroker.service.ImportadorDadosDiariosBovespa;
import br.com.gome.gomebroker.util.StringConverter;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@Named
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ImportadorDadosDiariosBovespaImpl implements ImportadorDadosDiariosBovespa {

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
	
	@Inject ResourceBundle bundle;
	@Inject Logger logger;
	
	@Inject private AtivoBC	ativoBC;
	@Inject private AtivoCotacaoBC ativoCotacaoBC;
	@Inject private FeriadoBovespaBC feriadoBovespaBC;
	@Inject private EmpresaBC empresaBC;
	
	@Override
	public void baixarEImportarArquivoBovespa(TipoArquivoBovespa tipoArquivo, Date data) throws FeriadoBovespaException {
		
		String nomeArquivoDezipado = baixarArquivoDeCotacoesBovespa(tipoArquivo, data);
		
		try {
			
			importarRegistrosDoArquivoDeCotacoesBovespaParaBD(tipoArquivo, nomeArquivoDezipado, data);
			
		} catch (Exception e) {
			
			throw new RuntimeException(bundle.getString("core.exception.ativocotacaobcimpl.erro-importacao-arquivo-bovespa"), e);
			
		}
		
	}
	
	private String baixarArquivoDeCotacoesBovespa(TipoArquivoBovespa tipo, Date data) throws FeriadoBovespaException {

		if (feriadoBovespaBC.verificaSeEhFeriado(data)) {
			
			throw new FeriadoBovespaException(bundle.getString("core.exception.ativocotacaobcimpl.feriado-bovespa", data), data);
			
		}
		
		String nomeArquivo = montaTitulo(tipo, data);
		ReadableByteChannel rbc = null;
		FileOutputStream fos = null;

		try {

			URL arquivoABaixar = new URL(URL + nomeArquivo);
			rbc = Channels.newChannel(arquivoABaixar.openStream());
			fos = new FileOutputStream(PATH_ARQUIVO_LOCAL + nomeArquivo);
			
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			
			return unzipArquivoBovespa(PATH_ARQUIVO_LOCAL + nomeArquivo).get(0);

		} catch (Exception e) {

			throw new RuntimeException(bundle.getString("core.exception.ativocotacaobcimpl.baixar-arquivo-de-cotacoes-bovespa", URL + nomeArquivo) + e.getMessage(), e);
			
		} finally {

				try {
					
					if (null != fos) fos.close();
					if (null != rbc) rbc.close();
					
				} catch (IOException e) {}
			
		}
			
	}

	private List<String> unzipArquivoBovespa(String arquivoZip) throws FileNotFoundException, IOException {
		
		List<String> arquivos = new ArrayList<String>();
		
		byte[] buffer = new byte[1024];
		
		ZipInputStream zis = new ZipInputStream(new FileInputStream(arquivoZip));

		ZipEntry ze = zis.getNextEntry();

		while (null != ze) {

			String fileName = ze.getName();
			arquivos.add(fileName);
			File file = new File(PATH_ARQUIVO_LOCAL + fileName);
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
	
	private void importarRegistrosDoArquivoDeCotacoesBovespaParaBD(TipoArquivoBovespa tipo, String nomeArquivo, Date data) throws IOException, ParseException {
		
		Map<String, Ativo> cacheAtivoMap = new HashMap<String, Ativo>();
		Map<String, Empresa> cacheEmpresaMap = new HashMap<String, Empresa>();
		
		String line = null;
		File file = new File(PATH_ARQUIVO_LOCAL + nomeArquivo);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(file))));
        
        while ((line = reader.readLine()) != null) {
            
            String tipoRegistro = getStringValue(line, LayoutArquivoBovespa.TipoDoRegistro);

            if (TIPO_REGISTRO_COTACAO.equals(tipoRegistro)) {

                consomeCotacao(line, cacheEmpresaMap, cacheAtivoMap);

            } else if (TIPO_REGISTRO_HEADER.equals(tipoRegistro)) {

                consomeHeader(line);

            } else if (TIPO_REGISTRO_TRAILER.equals(tipoRegistro)) {

                consomeTrailer(line);

            } else {
                
                throw new FormatoArquivoInvalidoException(line);
                
            }
            
        }
        
	}
	
    private void consomeCotacao(String line, Map<String, Empresa> cacheEmpresaMap, Map<String, Ativo> cacheAtivoMap) throws ParseException {

        TipoMercado tipoMercado = TipoMercado.getFromCodigo(getIntegerValue(line, LayoutArquivoBovespa.Cotacao_TipoDeMercado));

        if (!(TipoMercado.VISTA.equals(tipoMercado) || TipoMercado.FRACIONARIO.equals(tipoMercado))) {
            
            return;
            
        }
        
        String codigoNegociacao = getStringValue(line, LayoutArquivoBovespa.Cotacao_CodigoDeNegociacaoDoPapel);
        
        if (codigoNegociacao != null) {
        
            Ativo ativo = null;

            if ((ativo = cacheAtivoMap.get(codigoNegociacao)) == null) {

                if ((ativo = ativoBC.findByCodigoNegociacao(codigoNegociacao)) == null) {
                
                    ativo = ativoBC.mergeInNewTransaction(populaAtivo(line, cacheEmpresaMap));
                    
                }
                
                cacheAtivoMap.put(ativo.getCodigo(), ativo);

            }
                
            ativoCotacaoBC.mergeInNewTransaction(populaAtivoCotacoes(ativo, line));

        } else {

        	logger.error(bundle.getString("core.exception.ativocotacaobcimpl.formato-arquivo-bovespa-invalido", line));
            throw new FormatoArquivoInvalidoException(line);

        }

    }
	
	private void consomeHeader(String line) {

        String nomeArquivo = getStringValue(line, LayoutArquivoBovespa.Header_NomeDoArquivo);
        String codigoOrigem = getStringValue(line, LayoutArquivoBovespa.Header_CodigoDaOrigem);
        String dataGeracaoArquivo = getStringValue(line, LayoutArquivoBovespa.Header_DataDaGeracaoDoArquivo);

        System.out.println("===================================================================================================================================");
        System.out.println("Header ==> Arquivo Origem: " + nomeArquivo + " - Código da Origem: " + codigoOrigem + " - Data da Geracão do arquivo: " + dataGeracaoArquivo);

    }

    private void consomeTrailer(String line) {

        Integer totalRegistros = getIntegerValue(line, LayoutArquivoBovespa.Trailer_TotalDeRegistros) - 2;

        System.out.println("Trailer ==> Total de Registros no arquivo: " + totalRegistros.toString());
        System.out.println("===================================================================================================================================");

    }
    
	private AtivoCotacao populaAtivoCotacoes(Ativo ativo, String line) throws ParseException {
        
        AtivoCotacao cotacao = new AtivoCotacao(ativo, getDateValue(line, LayoutArquivoBovespa.Cotacao_DataDoPregao));
        
        cotacao.setAtivo(ativo);
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
        ativo.addAtivoCotacao(cotacao);
        
        return cotacao;
        
    }
	
    private Ativo populaAtivo(String line, Map<String, Empresa> cacheEmpresaMap) throws ParseException {
        
        Ativo ativo = new Ativo();

        ativo.setCodigo(getStringValue(line, LayoutArquivoBovespa.Cotacao_CodigoDeNegociacaoDoPapel));
        ativo.setTipoMercado(TipoMercado.getFromCodigo(getIntegerValue(line, LayoutArquivoBovespa.Cotacao_TipoDeMercado)));
        ativo.setFatorCotacao(FatorCotacaoAtivo.getFromCodigo(getIntegerValue(line, LayoutArquivoBovespa.Cotacao_FatorDeCotacao)).getCodigo());
        ativo.setDataCadastro(new Date());
        populaEmpresa(ativo, cacheEmpresaMap, getStringValue(line, LayoutArquivoBovespa.Cotacao_NomeResumidoDaEmpresaEmissoraDoPapel));
        
        return ativo;
        
    }

	private void populaEmpresa(Ativo ativo, Map<String, Empresa> cacheEmpresaMap, String nomeResumido) {

		Empresa empresa = null;
		
		if ((empresa = cacheEmpresaMap.get(nomeResumido)) == null) {
		
			if ((empresa = empresaBC.findByNomeResumido(nomeResumido)) == null) {
				
				empresa = new Empresa();
				
				empresa.setNome(nomeResumido);
				empresa.setDataCadastro(new Date());
				
			} else {
			
				cacheEmpresaMap.put(nomeResumido, empresa);
				
			}
			
		}
		
		ativo.setEmpresa(empresa);
		empresa.addAtivo(ativo);
		
	}
	
    private String montaTitulo(TipoArquivoBovespa tipo, Date data) {
		
		String titulo = PREFIXO_TITULO.replace("?", tipo.getSigla()) + new SimpleDateFormat(tipo.getMascara()).format(data) + SUFIXO_TITULO;
		
		return titulo;
		
	}
    
	private static Integer getIntegerValue(String linha, LayoutArquivoBovespa campo) {
		
		String strCampo = linha.substring(campo.getInicio(), campo.getFim()).trim();
		
		return StringConverter.convert(strCampo, Integer.class);
		
	}
	
	private String getStringValue(String linha, LayoutArquivoBovespa campo) {
		
		String strCampo = linha.substring(campo.getInicio(), campo.getFim()).trim();
		
		return strCampo;
		
	}
	
	private Date getDateValue(String linha, LayoutArquivoBovespa campo) {
		
		String strCampo = linha.substring(campo.getInicio(), campo.getFim()).trim();
		
		return StringConverter.convert(strCampo, MASCARA_DATA_BOVESPA);
		
	}
	
	private static Double getDoubleValue(String linha, LayoutArquivoBovespa campo) {
		
		String strCampo = linha.substring(campo.getInicio(), campo.getFim()).trim();
		int divisor = 1;
		
		for (int i=0; i<campo.getNumeroCasasDecimais(); i++) {
			divisor *= 10; 
		}
		
		return StringConverter.convert(strCampo, Double.class) / divisor;
		
	}
	
}
