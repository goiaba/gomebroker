package br.com.gome.gomebroker.exception;

/**
*
* @author bruno
*/
public class FormatoArquivoInvalidoException extends RuntimeException {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String lineWithError;
   
   public FormatoArquivoInvalidoException() {
   }
   
   public FormatoArquivoInvalidoException(String lineWithError) {
       
       super();
       this.lineWithError = lineWithError;
       
   }

   public FormatoArquivoInvalidoException(String message, String lineWithError) {
       
       super(message);
       this.lineWithError = lineWithError;
       
   }

   public FormatoArquivoInvalidoException(Throwable cause) {
       
       super(cause);
       
   }

   public FormatoArquivoInvalidoException(String message, Throwable cause) {
       
       super(message, cause);
       
   }

   public String getLineWithError() {
       
       return lineWithError;
       
   }

   public void setLineWithError(String lineWithError) {
       
       this.lineWithError = lineWithError;
       
   }

}
