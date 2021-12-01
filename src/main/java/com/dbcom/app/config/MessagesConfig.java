package com.dbcom.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Configuración de los mensajes de los properties
 * 
 * @author jalv
 */
@Configuration
public class MessagesConfig {
	
	/**
	 * Obtenemos la codificación del fichero del application.properties que a su vez la obtiene del fichero pom.xml
	 */
	@Value("${spring.messages.encoding}")
	private String encoding;
	
	/**
	 * Método con el que indicamos la ruta del fichero de mensajes y la codificación
	 * @return MessageSource con la ruta del fichero de mensajes y la codificación
	 */
	@Bean
	public MessageSource messageSource() {		
	    final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasenames("classpath:messages/validations", "classpath:messages/messages");
	    messageSource.setDefaultEncoding(this.encoding);  
	    return messageSource;
	}
	
	/**
	 * Método con el que podemos mostrar en la vista los mensajes de los properties
	 * @return Validación
	 */
	@Bean
    public LocalValidatorFactoryBean getValidator() {
        final LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

}
