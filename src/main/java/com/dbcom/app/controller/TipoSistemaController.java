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
import com.dbcom.app.model.dto.TipoSistemaDto;
import com.dbcom.app.service.TipoSistemaService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author neoris
 * Enlace entre la vista y la lógica de negocio
 */
@Slf4j
@Controller
public class TipoSistemaController {

	// Atributos de la vista
	private static final String ATTRIBUTE_TIPO_SISTEMA = "tipoSistema";
	
	// Vistas	
	private static final String VIEW_TIPO_SISTEMA = ControllerConstants.MAP_PATH_MENU_SISTEMAS + ATTRIBUTE_TIPO_SISTEMA;
	private static final String VIEW_TIPOS_SISTEMAS = ControllerConstants.MAP_PATH_MENU_SISTEMAS + "tiposSistemas";
		
	// Mapeo de las acciones
	public static final String MAP_CREATE_TIPO_SISTEMA = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPO_SISTEMA 
			+ ControllerConstants.MAP_ACTION_CREAR;
	public static final String MAP_DELETE_TIPO_SISTEMA = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPO_SISTEMA 
			+ ControllerConstants.MAP_ACTION_BORRAR;
	public static final String MAP_SAVE_TIPO_SISTEMA = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPO_SISTEMA 
			+ ControllerConstants.MAP_ACTION_GUARDAR;
	public static final String MAP_UPDATE_TIPO_SISTEMA = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPO_SISTEMA 
			+ ControllerConstants.MAP_ACTION_MODIFICAR;
	public static final String MAP_READ_TIPO_SISTEMA = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPO_SISTEMA;
	public static final String MAP_READALL_TIPOS_SISTEMAS = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPOS_SISTEMAS;
		
	private final TipoSistemaService tiposSistemasService;
	
	@Autowired
	public TipoSistemaController(TipoSistemaService tiposSistemasService) {
		this.tiposSistemasService = tiposSistemasService;
	}
	
	/**
	 * Obtenemos un listado de los tipos de sistemas
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READALL_TIPOS_SISTEMAS)
	public String readAll(final Model model) {
		
		// Contenido
		model.addAttribute(ControllerConstants.ATTRIBUTE_LISTA, this.tiposSistemasService.readAll());		

		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_LEER, MAP_READ_TIPO_SISTEMA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_AGNADIR, MAP_CREATE_TIPO_SISTEMA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_MODIFICAR, MAP_UPDATE_TIPO_SISTEMA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_BORRAR, MAP_DELETE_TIPO_SISTEMA);
					
		log.info(LoggerConstants.LOG_READALL);
		
		return VIEW_TIPOS_SISTEMAS;		
	}
	
	/**
	 * Creamos un tipo de sistema sin persistencia
	 * @param model modelo
	 * @return Vista
	 */
	@GetMapping(value = MAP_CREATE_TIPO_SISTEMA)
	public String create(final Model model) {

		// Creamos el registro
		model.addAttribute(ATTRIBUTE_TIPO_SISTEMA, this.tiposSistemasService.create());
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_TIPOS_SUBSISTEMAS_VISIBLE, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_TIPO_SISTEMA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS_SISTEMAS);
					
		log.info(LoggerConstants.LOG_CREATE);
		
		return VIEW_TIPO_SISTEMA;		
	}
	
	
	/**
	 * Persistimos el tipo de sistema pasado como parámetro
	 * @param tipoSistemaDto Tipo de sistema a persistir
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_SAVE_TIPO_SISTEMA)
	public String save(@Valid @ModelAttribute(ATTRIBUTE_TIPO_SISTEMA) final TipoSistemaDto tipoSistemaDto, 
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
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_TIPO_SISTEMA);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS_SISTEMAS);
		
			vista = VIEW_TIPO_SISTEMA;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());	
	
		} else {		
			this.tiposSistemasService.save(tipoSistemaDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_TIPOS_SISTEMAS);
			log.info(LoggerConstants.LOG_SAVE, tipoSistemaDto.getId());
		}
		
		return vista;
	}
	
	/**
	 * Obtenemos el tipo de sistema con el id facilitado
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READ_TIPO_SISTEMA + "/{id}")
	public String read(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_TIPO_SISTEMA, this.tiposSistemasService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_TIPOS_SUBSISTEMAS_VISIBLE, Boolean.TRUE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_ELIMINAR, MAP_READALL_TIPOS_SISTEMAS);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS_SISTEMAS);
					
		log.info(LoggerConstants.LOG_READ);
		
		return VIEW_TIPO_SISTEMA;
		
	}
	
	/**
	 * Preparamos la vista para la actulización del tipo de sistema pasado como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_UPDATE_TIPO_SISTEMA + "/{id}")
	public String updateGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_TIPO_SISTEMA, this.tiposSistemasService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_TIPOS_SUBSISTEMAS_VISIBLE, Boolean.TRUE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_TIPO_SISTEMA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS_SISTEMAS);
					
		log.info(LoggerConstants.LOG_UPDATE);
		
		return VIEW_TIPO_SISTEMA;
		
	}
	
	/**
	 * Actualizamos el tipo de sistema pasado como parámetro
	 * @param tipoSistemaDto Tipo de sistema a actualizar
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_UPDATE_TIPO_SISTEMA)
	public String updatePOST(@Valid @ModelAttribute(ATTRIBUTE_TIPO_SISTEMA) final TipoSistemaDto tipoSistemaDto, 
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
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_TIPO_SISTEMA);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS_SISTEMAS);
		
			vista = VIEW_TIPO_SISTEMA;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());		
		
		} else {
			this.tiposSistemasService.update(tipoSistemaDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_TIPOS_SISTEMAS);
			log.info(LoggerConstants.LOG_UPDATE, tipoSistemaDto.getId());			
		}

		return vista;		
	}
	
	/**
	 * Preparamos la vista para la eliminación del tipo de sistema pasado como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_DELETE_TIPO_SISTEMA + "/{id}")
	public String deleteGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_TIPO_SISTEMA, this.tiposSistemasService.read(id));
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_PREGUNTA, 
				MessagesConstants.POPUP_ELIMINAR_TIPO_SISTEMA_PREGUNTA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_NO_PERMITIDO_MENSAJE, 
				MessagesConstants.POPUP_ELIMINAR_TIPO_SISTEMA_NO_PERMITIDO_MENSAJE);
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_TIPOS_SUBSISTEMAS_VISIBLE, Boolean.TRUE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_DELETE_TIPO_SISTEMA
				.concat(ControllerConstants.MAP_ACTION_SLASH).concat(String.valueOf(id)));
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS_SISTEMAS);
					
		log.info(LoggerConstants.LOG_DELETE);
		
		return VIEW_TIPO_SISTEMA;		
	}
	
	/**
	 * Eliminación del tipo de sistema pasado como parámetro
	 * @param id Identificador
	 * @return Vista
	 */
	@PostMapping(MAP_DELETE_TIPO_SISTEMA + "/{id}")
	public String deletePOST(@PathVariable("id") final Long id) {		
		this.tiposSistemasService.delete(id);					
		log.info(LoggerConstants.LOG_DELETE);		
		return ControllerConstants.REDIRECT.concat(MAP_READALL_TIPOS_SISTEMAS);		
	}
}
