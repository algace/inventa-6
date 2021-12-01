package com.dbcom.app.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Textos de los mensajes para las excepciones que son leídos desde el properties
 * 
 * @author jose.vallve
 */
@Component
@PropertySource("classpath:messages/messages.properties")
public final class ExceptionConstants {

	public static String DAO_EXCEPTION;
	public static String FILE_EXCEPTION;
	public static String VALIDATION_EXCEPTION;	

    @Value("${dao.log.exception}")
    public void setDaoException(final String texto) {
    	DAO_EXCEPTION = texto;
    }

    @Value("${file.log.exception}")
    public void setFileException(final String texto) {
    	FILE_EXCEPTION = texto;
    }
    
    @Value("${validation.log.exception}")
    public void setValidationException(final String texto) {
    	VALIDATION_EXCEPTION = texto;
    }

}
