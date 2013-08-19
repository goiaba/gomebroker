package br.com.gome.gomebroker.exception;

import java.util.Date;

public class FeriadoBovespaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private Date data;
	
	public FeriadoBovespaException() {}
	
	public FeriadoBovespaException(Date data) {
		super();
		this.data = data;
	}
	
	public FeriadoBovespaException(String message, Date data) {
		super(message);
		this.data = data;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}
