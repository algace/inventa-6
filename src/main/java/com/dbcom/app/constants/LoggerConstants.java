package com.dbcom.app.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Textos de los mensajes de log que son leídos desde el properties
 * 
 * @author jose.vallve
 */
@Component
@PropertySource("classpath:messages/messages.properties")
public final class LoggerConstants {
	
	public static String LOG_READALL;
	public static String LOG_READ;
	public static String LOG_CREATE;
	public static String LOG_UPDATE;
	public static String LOG_SAVE;
	public static String LOG_DELETE;
    
    @Value("${log.readAll}")
    public void setReadall(final String texto) {
    	LOG_READALL = texto;
    }
    
    @Value("${log.read}")
    public void setRead(final String texto) {
    	LOG_READ = texto;
    }

    @Value("${log.create}")
    public void setCreate(final String texto) {
    	LOG_CREATE = texto;
    }

    @Value("${log.update}")
    public void setUpdate(final String texto) {
    	LOG_UPDATE = texto;
    }

    @Value("${log.save}")
    public void setSave(final String texto) {
    	LOG_SAVE = texto;
    }

    @Value("${log.delete}")
    public void setDelete(final String texto) {
    	LOG_DELETE = texto;
    }
    
}
