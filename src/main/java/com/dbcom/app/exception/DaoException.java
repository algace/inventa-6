package com.dbcom.app.exception;

/**
 * Excepción personalizada del acceso a datos
 * 
 * @author jalv
 */
public final class DaoException extends RuntimeException {

	/**
	 * Identificador de la clase
	 */
	private static final long serialVersionUID = 5501181391095374852L;
	
	/**
	 * Contructor con el mensaje personalizado
	 * @param message Mensaje personalizado
	 */
	public DaoException(final String message) {
		super(message);
	}
	

}
