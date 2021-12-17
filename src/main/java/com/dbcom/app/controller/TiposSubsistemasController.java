package com.dbcom.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dbcom.app.constants.ControllerConstants;
import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.constants.MessagesConstants;
import com.dbcom.app.model.dto.TipoSubsistemaDto;
import com.dbcom.app.service.TiposSubsistemasService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jgm
 * Enlace entre la vista y la lógica de negocio
 */
@Slf4j
@Controller
public class TiposSubsistemasController {

	// Atributos de la vista
	private static final String ATTRIBUTE_TIPO_SUBSISTEMA = "tipoSubsistema";
		
	// Vistas	
	private static final String VIEW_TIPO_SUBSISTEMA = ControllerConstants.MAP_PATH_MENU_SISTEMAS + ATTRIBUTE_TIPO_SUBSISTEMA;
	private static final String VIEW_TIPOS_SUBSISTEMAS = ControllerConstants.MAP_PATH_MENU_SISTEMAS + "tiposSubsistemas";
		
	// Mapeo de las acciones
	public static final String MAP_CREATE_TIPO_SUBSISTEMA = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPO_SUBSISTEMA 
			+ ControllerConstants.MAP_ACTION_CREAR;
	public static final String MAP_DELETE_TIPO_SUBSISTEMA = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPO_SUBSISTEMA 
			+ ControllerConstants.MAP_ACTION_BORRAR;
	public static final String MAP_SAVE_TIPO_SUBSISTEMA = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPO_SUBSISTEMA 
			+ ControllerConstants.MAP_ACTION_GUARDAR;
	public static final String MAP_UPDATE_TIPO_SUBSISTEMA = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPO_SUBSISTEMA 
			+ ControllerConstants.MAP_ACTION_MODIFICAR;
	public static final String MAP_READ_TIPO_SUBSISTEMA = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPO_SUBSISTEMA;
	public static final String MAP_READALL_TIPOS_SUBSISTEMAS = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPOS_SUBSISTEMAS;
		
	private final TiposSubsistemasService tiposSubsistemasService;
	
	@Autowired
	public TiposSubsistemasController(TiposSubsistemasService tiposSubsistemasService) {
		this.tiposSubsistemasService = tiposSubsistemasService;
	}
	
	/**
	 * Obtenemos un listado de los tipos de subsistemas
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READALL_TIPOS_SUBSISTEMAS)
	public String readAll(final Model model) {
		
		// Contenido
		model.addAttribute(ControllerConstants.ATTRIBUTE_LISTA, this.tiposSubsistemasService.readAll());		

		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_LEER, MAP_READ_TIPO_SUBSISTEMA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_AGNADIR, MAP_CREATE_TIPO_SUBSISTEMA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_MODIFICAR, MAP_UPDATE_TIPO_SUBSISTEMA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_BORRAR, MAP_DELETE_TIPO_SUBSISTEMA);
					
		log.info(LoggerConstants.LOG_READALL);
		
		return VIEW_TIPOS_SUBSISTEMAS;		
	}
	
	/**
	 * Creamos un tipo de subsistema sin persistencia
	 * @param model modelo
	 * @return Vista
	 */
	@GetMapping(value = MAP_CREATE_TIPO_SUBSISTEMA)
	public String create(final Model model) {

		// Creamos el registro
		model.addAttribute(ATTRIBUTE_TIPO_SUBSISTEMA, this.tiposSubsistemasService.create());
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_TIPO_SUBSISTEMA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS_SUBSISTEMAS);
					
		log.info(LoggerConstants.LOG_CREATE);
		
		return VIEW_TIPO_SUBSISTEMA;		
	}
	
	/**
	 * Persistimos el tipo de subsistema pasado como parámetro
	 * @param tipoSubsistemaDto Tipo de subsistema a persistir
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_SAVE_TIPO_SUBSISTEMA)
	public String save(@Valid @ModelAttribute(ATTRIBUTE_TIPO_SUBSISTEMA) final TipoSubsistemaDto tipoSubsistemaDto, 
			final BindingResult bindingResult, final Model model) {	
		
		final String vista;
		if (bindingResult.hasErrors()) {
			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_TIPOS_SUBSISTEMAS_VISIBLE, Boolean.FALSE);
			
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_TIPO_SUBSISTEMA);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS_SUBSISTEMAS);
		
			vista = VIEW_TIPO_SUBSISTEMA;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());	
	
		} else {		
			this.tiposSubsistemasService.save(tipoSubsistemaDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_TIPOS_SUBSISTEMAS);
			log.info(LoggerConstants.LOG_SAVE, tipoSubsistemaDto.getId());
		}
		
		return vista;
	}
	
	/**
	 * Obtenemos el tipo de subsistema con el id facilitado
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READ_TIPO_SUBSISTEMA + "/{id}")
	public String read(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_TIPO_SUBSISTEMA, this.tiposSubsistemasService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_TIPOS_SUBSISTEMAS_VISIBLE, Boolean.TRUE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_ELIMINAR, MAP_READALL_TIPOS_SUBSISTEMAS);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS_SUBSISTEMAS);
					
		log.info(LoggerConstants.LOG_READ);
		
		return VIEW_TIPO_SUBSISTEMA;
		
	}
	
	/**
	 * Preparamos la vista para la actulización del tipo de subsistema pasado como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_UPDATE_TIPO_SUBSISTEMA + "/{id}")
	public String updateGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_TIPO_SUBSISTEMA, this.tiposSubsistemasService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_TIPOS_SUBSISTEMAS_VISIBLE, Boolean.TRUE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_TIPO_SUBSISTEMA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS_SUBSISTEMAS);
					
		log.info(LoggerConstants.LOG_UPDATE);
		
		return VIEW_TIPO_SUBSISTEMA;
	}
	
	/**
	 * Actualizamos el tipo de subsistema pasado como parámetro
	 * @param tipoSubsistemaDto Tipo de subsistema a actualizar
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_UPDATE_TIPO_SUBSISTEMA)
	public String updatePOST(@Valid @ModelAttribute(ATTRIBUTE_TIPO_SUBSISTEMA) final TipoSubsistemaDto tipoSubsistemaDto, 
			final BindingResult bindingResult, final Model model) {		
		
		final String vista;
		if (bindingResult.hasErrors()) {			

			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_TIPOS_SUBSISTEMAS_VISIBLE, Boolean.TRUE);
	
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_TIPO_SUBSISTEMA);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS_SUBSISTEMAS);
		
			vista = VIEW_TIPO_SUBSISTEMA;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());		
		
		} else {
			this.tiposSubsistemasService.update(tipoSubsistemaDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_TIPOS_SUBSISTEMAS);
			log.info(LoggerConstants.LOG_UPDATE, tipoSubsistemaDto.getId());			
		}

		return vista;		
	}
	
	/**
	 * Preparamos la vista para la eliminación del tipo de subsistema pasado como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_DELETE_TIPO_SUBSISTEMA + "/{id}")
	public String deleteGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_TIPO_SUBSISTEMA, this.tiposSubsistemasService.read(id));
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_PREGUNTA, 
				MessagesConstants.POPUP_ELIMINAR_TIPO_SUBSISTEMA_PREGUNTA);
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_TIPOS_SUBSISTEMAS_VISIBLE, Boolean.TRUE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_DELETE_TIPO_SUBSISTEMA
				.concat(ControllerConstants.MAP_ACTION_SLASH).concat(String.valueOf(id)));
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS_SUBSISTEMAS);
					
		log.info(LoggerConstants.LOG_DELETE);
		
		return VIEW_TIPO_SUBSISTEMA;		
	}
	
	/**
	 * Eliminación del tipo de subsistema pasado como parámetro
	 * @param id Identificador
	 * @return Vista
	 */
	@PostMapping(MAP_DELETE_TIPO_SUBSISTEMA + "/{id}")
	public String deletePOST(@PathVariable("id") final Long id) {		
		this.tiposSubsistemasService.delete(id);					
		log.info(LoggerConstants.LOG_DELETE);		
		return ControllerConstants.REDIRECT.concat(MAP_READALL_TIPOS_SUBSISTEMAS);		
	}
	
}
