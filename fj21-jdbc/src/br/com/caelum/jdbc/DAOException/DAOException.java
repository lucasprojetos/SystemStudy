package br.com.caelum.jdbc.DAOException;

public class DAOException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String e = "Erro, corrija";
	RuntimeException a = new RuntimeException(e);

}
