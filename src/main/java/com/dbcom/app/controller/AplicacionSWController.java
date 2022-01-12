package com.dbcom.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbcom.app.constants.ControllerConstants;
import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.constants.MessagesConstants;
import com.dbcom.app.model.dto.AplicacionSWDto;
import com.dbcom.app.service.AplicacionSWService;
import com.dbcom.app.service.EquipamientoService;
import com.dbcom.app.service.VersionSWService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jose.vallve 
 * Enlace entre la vista y la lógica de negocio
 */
@Slf4j
@Controller
public final class AplicacionSWController {
	
	// Atributos de la vista
	private static final String ATTRIBUTE_APLICACION = "aplicacionSW";
	private static final String ATTRIBUTE_EQUIPAMIENTOS_DISPONIBLES = "listaEquipamientosDisponibles";
	private static final String ATTRIBUTE_VERSIONES_DISPONIBLES = "listaVersionesDisponibles";

	// Vistas	
	private static final String VIEW_APLICACION = ControllerConstants.MAP_PATH_MENU_CONTROLVERSIONESSW + ATTRIBUTE_APLICACION;
	private static final String VIEW_APLICACIONES = ControllerConstants.MAP_PATH_MENU_CONTROLVERSIONESSW + "aplicacionesSW";

	// Mapeo de las acciones
	public static final String MAP_CREATE_APLICACION = ControllerConstants.MAP_ACTION_SLASH + VIEW_APLICACION 
			+ ControllerConstants.MAP_ACTION_CREAR;
	public static final String MAP_DELETE_APLICACION = ControllerConstants.MAP_ACTION_SLASH + VIEW_APLICACION 
			+ ControllerConstants.MAP_ACTION_BORRAR;
	public static final String MAP_SAVE_APLICACION = ControllerConstants.MAP_ACTION_SLASH + VIEW_APLICACION 
			+ ControllerConstants.MAP_ACTION_GUARDAR;
	public static final String MAP_UPDATE_APLICACION = ControllerConstants.MAP_ACTION_SLASH + VIEW_APLICACION 
			+ ControllerConstants.MAP_ACTION_MODIFICAR;
	public static final String MAP_READ_APLICACION = ControllerConstants.MAP_ACTION_SLASH + VIEW_APLICACION;
	public static final String MAP_READALL_APLICACIONES = ControllerConstants.MAP_ACTION_SLASH + VIEW_APLICACIONES;
	
	public static final String MAP_INSERT_VERSION = ControllerConstants.MAP_ACTION_SLASH + VIEW_APLICACION + 
			ControllerConstants.MAP_ACTION_INSERTAR_VERSIONSW + ControllerConstants.MAP_ACTION_SLASH;
	public static final String MAP_DELETE_VERSION = ControllerConstants.MAP_ACTION_SLASH + VIEW_APLICACION + 
			ControllerConstants.MAP_ACTION_DELETE_VERSIONSW + ControllerConstants.MAP_ACTION_SLASH;
	
	public static final String MAP_INSERT_EQUIPAMIENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_APLICACION + 
			ControllerConstants.MAP_ACTION_INSERT_EQUIPAMIENTO + ControllerConstants.MAP_ACTION_SLASH;
	public static final String MAP_DELETE_EQUIPAMIENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_APLICACION + 
			ControllerConstants.MAP_ACTION_DELETE_EQUIPAMIENTO + ControllerConstants.MAP_ACTION_SLASH;
	
	private final AplicacionSWService aplicacionService;
	private final EquipamientoService equipamientoService;
	private final VersionSWService versionSWService;
	private final HttpServletRequest request;
	
	@Autowired
	public AplicacionSWController(AplicacionSWService aplicacionService, 
								  EquipamientoService equipamientoService,
								  VersionSWService versionSWService,
								  HttpServletRequest request) {
		this.aplicacionService = aplicacionService;
		this.equipamientoService = equipamientoService;
		this.versionSWService = versionSWService;
		this.request = request;
	}
	
	/**
	 * Obtenemos un listado de las aplicaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READALL_APLICACIONES)
	public String readAll(final Model model) {
		
		// Contenido
		model.addAttribute(ControllerConstants.ATTRIBUTE_LISTA, this.aplicacionService.readAll());		

		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_LEER, MAP_READ_APLICACION);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_AGNADIR, MAP_CREATE_APLICACION);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_MODIFICAR, MAP_UPDATE_APLICACION);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_BORRAR, MAP_DELETE_APLICACION);
					
		log.info(LoggerConstants.LOG_READALL);
		
		return VIEW_APLICACIONES;		
	}
	
	/**
	 * Creamos una aplicación sin persistencia
	 * @param model modelo
	 * @return Vista
	 */
	@GetMapping(value = MAP_CREATE_APLICACION)
	public String create(final Model model) {

		// Creamos el registro
		model.addAttribute(ATTRIBUTE_APLICACION, this.aplicacionService.create());
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_APLICACION);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_APLICACIONES);
					
		log.info(LoggerConstants.LOG_CREATE);
		
		return VIEW_APLICACION;		
	}
	
	/**
	 * Persistimos la aplicación pasada como parámetro
	 * @param aplicacionDto Aplicación a persistir
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_SAVE_APLICACION)
	public String save(@Valid @ModelAttribute(ATTRIBUTE_APLICACION) final AplicacionSWDto aplicacionDto, 
			final BindingResult bindingResult, final Model model) {	
		
		final String vista;
		if (bindingResult.hasErrors()) {

			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.FALSE);
			
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_APLICACION);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_APLICACIONES);
			
			vista = VIEW_APLICACION;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());	
		
		} else {		
			this.aplicacionService.save(aplicacionDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_APLICACIONES);
			log.info(LoggerConstants.LOG_SAVE, aplicacionDto.getId());
		}
		
		return vista;
	}
	
	/**
	 * Obtenemos la aplicación con el id facilitado
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READ_APLICACION + "/{id}")
	public String read(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_APLICACION, this.aplicacionService.read(id));
		
		//Obtenemos las listas de equipamientos y versiones disponibles que se pueden asociar a la aplicación
		obtenerListasObjetosDisponibles(model, id);
				
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.TRUE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_ELIMINAR, MAP_READALL_APLICACIONES);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_APLICACIONES);
					
		log.info(LoggerConstants.LOG_READ);
		
		return VIEW_APLICACION;
		
	}
	
	/**
	 * Preparamos la vista para la actualización la aplicación pasada como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_UPDATE_APLICACION + "/{id}")
	public String updateGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_APLICACION, this.aplicacionService.read(id));
		
		//Obtenemos las listas de equipamientos y versiones disponibles que se pueden asociar a la aplicación
		obtenerListasObjetosDisponibles(model, id);
				
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.TRUE);
		model.addAttribute(ControllerConstants.URL_INSERT_VERSIONES_APLICACIONES, this.request.getContextPath() + MAP_INSERT_VERSION);
		model.addAttribute(ControllerConstants.URL_DELETE_VERSIONES_APLICACIONES, this.request.getContextPath() + MAP_DELETE_VERSION);
		model.addAttribute(ControllerConstants.URL_INSERT_EQUIPAMIENTOS_APLICACIONES, this.request.getContextPath() + MAP_INSERT_EQUIPAMIENTO);
		model.addAttribute(ControllerConstants.URL_DELETE_EQUIPAMIENTOS_APLICACIONES, this.request.getContextPath() + MAP_DELETE_EQUIPAMIENTO);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_APLICACION);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_APLICACIONES);
					
		log.info(LoggerConstants.LOG_UPDATE);
		
		return VIEW_APLICACION;
		
	}
	
	/**
	 * Actualizamos la aplicación pasada como parámetro
	 * @param aplicacionDto Aplicación a actualizar
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_UPDATE_APLICACION)
	public String updatePOST(@Valid @ModelAttribute(ATTRIBUTE_APLICACION) final AplicacionSWDto aplicacionDto, 
			final BindingResult bindingResult, final Model model) {		
		
		final String vista;
		if (bindingResult.hasErrors()) {		
			
			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.TRUE);
			model.addAttribute(ControllerConstants.URL_INSERT_VERSIONES_APLICACIONES, this.request.getContextPath() + MAP_INSERT_VERSION);
			model.addAttribute(ControllerConstants.URL_DELETE_VERSIONES_APLICACIONES, this.request.getContextPath() + MAP_DELETE_VERSION);
			model.addAttribute(ControllerConstants.URL_INSERT_EQUIPAMIENTOS_APLICACIONES, this.request.getContextPath() + MAP_INSERT_EQUIPAMIENTO);
			model.addAttribute(ControllerConstants.URL_DELETE_EQUIPAMIENTOS_APLICACIONES, this.request.getContextPath() + MAP_DELETE_EQUIPAMIENTO);
	
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_APLICACION);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_APLICACIONES);
			

			aplicacionService.setAllAttributesListEquipamientoDto(aplicacionDto);

			
			//Obtenemos las listas de equipamientos y versiones disponibles que se pueden asociar a la aplicación
			obtenerListasObjetosDisponibles(model, aplicacionDto.getId());
		
			vista = VIEW_APLICACION;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());		
		} else {
			this.aplicacionService.update(aplicacionDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_APLICACIONES);
			log.info(LoggerConstants.LOG_UPDATE, aplicacionDto.getId());			
		}

		return vista;		
	}
	
	/**
	 * Preparamos la vista para la eliminación la aplicación pasada como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_DELETE_APLICACION + "/{id}")
	public String deleteGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_APLICACION, this.aplicacionService.read(id));
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_PREGUNTA, 
				MessagesConstants.POPUP_ELIMINAR_APLICACION_PREGUNTA);
		
		//Obtenemos las listas de equipamientos y versiones disponibles que se pueden asociar a la aplicación
		obtenerListasObjetosDisponibles(model, id);
				
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.TRUE);
		model.addAttribute(ControllerConstants.URL_INSERT_VERSIONES_APLICACIONES, this.request.getContextPath() + MAP_INSERT_VERSION);
		model.addAttribute(ControllerConstants.URL_DELETE_VERSIONES_APLICACIONES, this.request.getContextPath() + MAP_DELETE_VERSION);
		model.addAttribute(ControllerConstants.URL_INSERT_EQUIPAMIENTOS_APLICACIONES, this.request.getContextPath() + MAP_INSERT_EQUIPAMIENTO);
		model.addAttribute(ControllerConstants.URL_DELETE_EQUIPAMIENTOS_APLICACIONES, this.request.getContextPath() + MAP_DELETE_EQUIPAMIENTO);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_DELETE_APLICACION
				.concat(ControllerConstants.MAP_ACTION_SLASH).concat(String.valueOf(id)));
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_APLICACIONES);
					
		log.info(LoggerConstants.LOG_DELETE);
		
		return VIEW_APLICACION;		
	}
	
	/**
	 * Eliminación de la aplicación pasada como parámetro
	 * @param id Identificador
	 * @return Vista
	 */
	@PostMapping(MAP_DELETE_APLICACION + "/{id}")
	public String deletePOST(@PathVariable("id") final Long id) {		
		this.aplicacionService.delete(id);					
		log.info(LoggerConstants.LOG_DELETE);		
		return ControllerConstants.REDIRECT.concat(MAP_READALL_APLICACIONES);		
	}
	

	@ResponseBody
	@PostMapping(value = MAP_INSERT_VERSION + "/{idAplicacionSW}/{idVersionSW}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<Long> insertVersionSW(@PathVariable("idAplicacionSW") final Long idAplicacionSW, @PathVariable("idVersionSW") final Long idVersionSW) {
		
		return ResponseEntity.ok(this.aplicacionService.insertVersionSW(idAplicacionSW, idVersionSW));					
	}
	
	@ResponseBody
	@DeleteMapping(value = MAP_DELETE_VERSION+ "/{idAplicacionSW}/{idVersionSW}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<Long> deleteVersionSW(@PathVariable("idAplicacionSW") final Long idAplicacionSW, @PathVariable("idVersionSW") final Long idVersionSW) {
		
		return ResponseEntity.ok(this.aplicacionService.deleteVersionSW(idAplicacionSW, idVersionSW));					
	}
	
	@ResponseBody
	@PostMapping(value = MAP_INSERT_EQUIPAMIENTO + "/{idAplicacionSW}/{idEquipamiento}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<Long> insertEquipamiento(@PathVariable("idAplicacionSW") final Long idAplicacionSW, @PathVariable("idEquipamiento") final Long idEquipamiento) {
		
		return ResponseEntity.ok(this.aplicacionService.insertEquipamiento(idAplicacionSW, idEquipamiento));					
	}
	
	@ResponseBody
	@DeleteMapping(value = MAP_DELETE_EQUIPAMIENTO + "/{idAplicacionSW}/{idEquipamiento}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<Long> deleteEquipamiento(@PathVariable("idAplicacionSW") final Long idAplicacionSW, @PathVariable("idEquipamiento") final Long idEquipamiento) {
		
		return ResponseEntity.ok(this.aplicacionService.deleteEquipamiento(idAplicacionSW, idEquipamiento));					
	}
	

	/**
	 * Obtiene las listas de tipos de unidades de frecuencia, tipos de bandas de frecuencia y tipos
	 * de fuentes de información y las añade al modelo para que se muestren en las listas desplegables
	 * @param model Modelo
	 */
	private void obtenerListasObjetosDisponibles(final Model model, final Long id) {
		
		//Obtenemos los equipamientos disponibles
		if (id == null) {
			model.addAttribute(ATTRIBUTE_EQUIPAMIENTOS_DISPONIBLES, this.equipamientoService.readAll());
		} else {
			model.addAttribute(ATTRIBUTE_EQUIPAMIENTOS_DISPONIBLES, this.equipamientoService.readNotContains(id));
		}
		
		//Obtenemos las versiones disponibles
		if (id == null) {
			model.addAttribute(ATTRIBUTE_VERSIONES_DISPONIBLES, this.versionSWService.readAll());
		} else {
			model.addAttribute(ATTRIBUTE_VERSIONES_DISPONIBLES, this.versionSWService.readNotContains(id));
		}

	}

}
