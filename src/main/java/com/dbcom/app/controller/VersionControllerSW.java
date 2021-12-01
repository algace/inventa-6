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
import com.dbcom.app.model.dto.VersionSWDto;
import com.dbcom.app.service.VersionSWService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jose.vallve 
 * Enlace entre la vista y la lógica de negocio
 */
@Slf4j
@Controller
public final class VersionControllerSW {

	// Atributos de la vista
	private static final String ATTRIBUTE_VERSION = "versionSW";

	// Vistas	
	private static final String VIEW_VERSION = ControllerConstants.MAP_PATH_MENU_CONTROLVERSIONESSW + ATTRIBUTE_VERSION;
	private static final String VIEW_VERSIONES = ControllerConstants.MAP_PATH_MENU_CONTROLVERSIONESSW + "versionesSW";
	
	// Mapeo de las acciones
	public static final String MAP_CREATE_VERSION = ControllerConstants.MAP_ACTION_SLASH + VIEW_VERSION 
			+ ControllerConstants.MAP_ACTION_CREAR;
	public static final String MAP_DELETE_VERSION = ControllerConstants.MAP_ACTION_SLASH + VIEW_VERSION 
			+ ControllerConstants.MAP_ACTION_BORRAR;
	public static final String MAP_SAVE_VERSION = ControllerConstants.MAP_ACTION_SLASH + VIEW_VERSION 
			+ ControllerConstants.MAP_ACTION_GUARDAR;
	public static final String MAP_UPDATE_VERSION = ControllerConstants.MAP_ACTION_SLASH + VIEW_VERSION 
			+ ControllerConstants.MAP_ACTION_MODIFICAR;
	public static final String MAP_READ_VERSION = ControllerConstants.MAP_ACTION_SLASH + VIEW_VERSION;
	public static final String MAP_READALL_VERSIONES = ControllerConstants.MAP_ACTION_SLASH + VIEW_VERSIONES;

	private final VersionSWService versionService;
	
	@Autowired
	public VersionControllerSW(VersionSWService versionService) {
		this.versionService = versionService;
	}
	
	/**
	 * Obtenemos un listado de las versiones
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READALL_VERSIONES)
	public String readAll(final Model model) {
		
		// Contenido
		model.addAttribute(ControllerConstants.ATTRIBUTE_LISTA, this.versionService.readAll());		

		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_LEER, MAP_READ_VERSION);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_AGNADIR, MAP_CREATE_VERSION);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_MODIFICAR, MAP_UPDATE_VERSION);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_BORRAR, MAP_DELETE_VERSION);
					
		log.info(LoggerConstants.LOG_READALL);
		
		return VIEW_VERSIONES;		
	}
	
	/**
	 * Creamos una versión sin persistencia
	 * @param model modelo
	 * @return Vista
	 */
	@GetMapping(value = MAP_CREATE_VERSION)
	public String create(final Model model) {

		// Creamos el registro
		model.addAttribute(ATTRIBUTE_VERSION, this.versionService.create());
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_VERSION);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_VERSIONES);
					
		log.info(LoggerConstants.LOG_CREATE);
		
		return VIEW_VERSION;		
	}
	
	/**
	 * Persistimos la versión pasada como parámetro
	 * @param versionDto Versión a persistir
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_SAVE_VERSION)
	public String save(@Valid @ModelAttribute(ATTRIBUTE_VERSION) final VersionSWDto versionDto, 
			final BindingResult bindingResult, final Model model) {	
		
		final String vista;
		if (bindingResult.hasErrors()) {
			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_VERSION);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_VERSIONES);
		
			vista = VIEW_VERSION;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());	
	
		} else {		
			this.versionService.save(versionDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_VERSIONES);
			log.info(LoggerConstants.LOG_SAVE, versionDto.getId());
		}
		
		return vista;
	}
	
	/**
	 * Obtenemos la versión con el id facilitado
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READ_VERSION + "/{id}")
	public String read(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_VERSION, this.versionService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_ELIMINAR, MAP_READALL_VERSIONES);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_VERSIONES);
					
		log.info(LoggerConstants.LOG_READ);
		
		return VIEW_VERSION;
		
	}
	
	/**
	 * Preparamos la vista para la actulización la versión pasada como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_UPDATE_VERSION + "/{id}")
	public String updateGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_VERSION, this.versionService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_VERSION);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_VERSIONES);
					
		log.info(LoggerConstants.LOG_UPDATE);
		
		return VIEW_VERSION;
		
	}
	
	/**
	 * Actualizamos la versión pasada como parámetro
	 * @param versionDto Versión a actualizar
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_UPDATE_VERSION)
	public String updatePOST(@Valid @ModelAttribute(ATTRIBUTE_VERSION) final VersionSWDto versionDto, 
			final BindingResult bindingResult, final Model model) {		
		
		final String vista;
		if (bindingResult.hasErrors()) {			

			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
	
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_VERSION);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_VERSIONES);
		
			vista = VIEW_VERSION;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());		
		
		} else {
			this.versionService.update(versionDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_VERSIONES);
			log.info(LoggerConstants.LOG_UPDATE, versionDto.getId());			
		}

		return vista;		
	}
	
	/**
	 * Preparamos la vista para la eliminación la versión pasada como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_DELETE_VERSION + "/{id}")
	public String deleteGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_VERSION, this.versionService.read(id));
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_PREGUNTA, 
				MessagesConstants.POPUP_ELIMINAR_VERSION_PREGUNTA);
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.TRUE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_DELETE_VERSION
				.concat(ControllerConstants.MAP_ACTION_SLASH).concat(String.valueOf(id)));
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_VERSIONES);
					
		log.info(LoggerConstants.LOG_DELETE);
		
		return VIEW_VERSION;		
	}
	
	/**
	 * Eliminación de la versión pasada como parámetro
	 * @param id Identificador
	 * @return Vista
	 */
	@PostMapping(MAP_DELETE_VERSION + "/{id}")
	public String deletePOST(@PathVariable("id") final Long id) {		
		this.versionService.delete(id);					
		log.info(LoggerConstants.LOG_DELETE);		
		return ControllerConstants.REDIRECT.concat(MAP_READALL_VERSIONES);		
	}
	
}
