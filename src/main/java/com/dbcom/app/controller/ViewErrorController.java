package com.dbcom.app.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dbcom.app.constants.ControllerConstants;
import com.dbcom.app.constants.MessagesConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jose.vallve
 * Clase con la que personalizamos la página de error
 */
@Controller
@Slf4j
public final class ViewErrorController implements ErrorController {
		
	private static final String VIEW_ERROR = ControllerConstants.MAP_ACTION_SLASH + "error";	
	private static final String ATTRIBUTE_MENSAJE = "mensaje";
	
	@GetMapping(VIEW_ERROR)
	@ResponseStatus(HttpStatus.OK)
	public String handleErrorGET(final Model model, final HttpServletRequest request) {
		return this.handleErrorPOST(model, request);
	}

	@PostMapping(VIEW_ERROR)
	@ResponseStatus(HttpStatus.OK)
    public String handleErrorPOST(final Model model, final HttpServletRequest request) {
		
        final Object estado = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if (null != estado) { 
         
        	final Integer statusCode = Integer.valueOf(estado.toString());
        	
        	if (statusCode == HttpStatus.BAD_REQUEST.value()) {
        		model.addAttribute(ATTRIBUTE_MENSAJE, MessagesConstants.ERROR_400); 
    			log.error(MessagesConstants.ERROR_400);
    			
        	} else if (statusCode == HttpStatus.FORBIDDEN.value()) {
            	model.addAttribute(ATTRIBUTE_MENSAJE, MessagesConstants.ERROR_403); 
            	log.error(MessagesConstants.ERROR_403);
            	
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
            	model.addAttribute(ATTRIBUTE_MENSAJE, MessagesConstants.ERROR_404); 
            	log.error(MessagesConstants.ERROR_404);
           
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            	model.addAttribute(ATTRIBUTE_MENSAJE, MessagesConstants.ERROR_500); 
            	log.error(MessagesConstants.ERROR_500);
           
            } else {
            	model.addAttribute(ATTRIBUTE_MENSAJE, MessagesConstants.ERROR_DESCONOCIDO); 
            	log.error(MessagesConstants.ERROR_DESCONOCIDO);
            }
        }
         
        return VIEW_ERROR;
    }
	
	@Override
	public String getErrorPath() {
		return  VIEW_ERROR;
	}

		
}

