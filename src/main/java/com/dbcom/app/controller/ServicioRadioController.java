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
import com.dbcom.app.model.dao.FrecuenciaATCRepository;
import com.dbcom.app.model.dto.ServicioRadioDto;
import com.dbcom.app.model.entity.ServicioRadio;
import com.dbcom.app.service.ServicioRadioService;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public final class ServicioRadioController {
	// Atributos de la vista
	private static final String ATTRIBUTE_TIPO = "servicioRadio";

	// Vistas	
	private static final String VIEW_TIPO = ControllerConstants.MAP_PATH_MENU + ATTRIBUTE_TIPO;
	private static final String VIEW_TIPOS = ControllerConstants.MAP_PATH_MENU + "serviciosRadio";		

	// Mapeo de las acciones
	public static final String MAP_CREATE_TIPO = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPO 
			+ ControllerConstants.MAP_ACTION_CREAR;
	public static final String MAP_DELETE_TIPO = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPO 
			+ ControllerConstants.MAP_ACTION_BORRAR;
	public static final String MAP_SAVE_TIPO = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPO 
			+ ControllerConstants.MAP_ACTION_GUARDAR;
	public static final String MAP_UPDATE_TIPO = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPO 
			+ ControllerConstants.MAP_ACTION_MODIFICAR;
	public static final String MAP_READ_TIPO = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPO;
	public static final String MAP_READALL_TIPOS = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPOS;	

	private final ServicioRadioService servicioRadioService;
	private final FrecuenciaATCRepository frecuenciaATCRepository;
	private final ModelMapperUtils modelMapperUtils;
	
	@Autowired
	public ServicioRadioController(ServicioRadioService servicioRadioService,
			FrecuenciaATCRepository frecuenciaATCRepository,
			ModelMapperUtils modelMapperUtils) {
		this.servicioRadioService = servicioRadioService;
		this.frecuenciaATCRepository = frecuenciaATCRepository;
		this.modelMapperUtils = modelMapperUtils;
	}
	
	/**
	 * Obtenemos un listado de los servicios de radio
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READALL_TIPOS)
	public String readAll(final Model model) {
		
		// Contenido
		model.addAttribute(ControllerConstants.ATTRIBUTE_LISTA, this.servicioRadioService.readAll());		

		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_LEER, MAP_READ_TIPO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_AGNADIR, MAP_CREATE_TIPO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_MODIFICAR, MAP_UPDATE_TIPO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_BORRAR, MAP_DELETE_TIPO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_BUSCAR, MAP_READALL_TIPOS);
					
		log.info(LoggerConstants.LOG_READALL);
		
		return VIEW_TIPOS;		
	}
	
	/**
	 * Creamos un servicio de radio sin persistencia
	 * @param model modelo
	 * @return Vista
	 */
	@GetMapping(MAP_CREATE_TIPO)
	public String create(final Model model) {

		// Creamos el registro
		model.addAttribute(ATTRIBUTE_TIPO, this.servicioRadioService.create());
		
		// Activaci??n de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_TIPO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS);
					
		log.info(LoggerConstants.LOG_CREATE);
		
		return VIEW_TIPO;		
	}
	
	/**
	 * Persistimos el servicio de radio pasado como par??metro
	 * @param servicioRadioDto Servicio de radio a persistir
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_SAVE_TIPO)
	public String save(@Valid @ModelAttribute(ATTRIBUTE_TIPO) final ServicioRadioDto servicioRadioDto, 
			final BindingResult bindingResult, final Model model) {	
		
		final String vista;
		if (bindingResult.hasErrors()) {

			// Activaci??n de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_TIPO);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS);
		
			vista = VIEW_TIPO;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());	
		
		} else {		
			this.servicioRadioService.saveUpdate(servicioRadioDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_TIPOS);
			log.info(LoggerConstants.LOG_SAVE, servicioRadioDto.getId());
		}
		
		return vista;
	}
	
	/**
	 * Obtenemos el servicio de radio con el id facilitado
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READ_TIPO + "/{id}")
	public String read(@PathVariable("id") final Short id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_TIPO, this.servicioRadioService.read(id));
		
		// Activaci??n de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_ELIMINAR, MAP_READALL_TIPOS);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS);
					
		log.info(LoggerConstants.LOG_READ);
		
		return VIEW_TIPO;
		
	}
	
	/**
	 * Preparamos la vista para la actualizaci??n del servicio de radio pasado como par??metro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_UPDATE_TIPO + "/{id}")
	public String updateGET(@PathVariable("id") final Short id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_TIPO, this.servicioRadioService.read(id));
		
		// Activaci??n de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_TIPO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS);
					
		log.info(LoggerConstants.LOG_UPDATE);
		
		return VIEW_TIPO;
		
	}
	
	/**
	 * Actualizamos el servicio de radio pasado como par??metro
	 * @param servicioRadioDto Servicio de radio a actualizar
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_UPDATE_TIPO)
	public String updatePOST(@Valid @ModelAttribute(ATTRIBUTE_TIPO) final ServicioRadioDto servicioRadioDto, 
			final BindingResult bindingResult, final Model model) {		
		
		final String vista;
		if (bindingResult.hasErrors()) {			

			// Activaci??n de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
	
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_TIPO);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS);
		
			vista = VIEW_TIPO;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());		
		
		} else {
			this.servicioRadioService.saveUpdate(servicioRadioDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_TIPOS);
			log.info(LoggerConstants.LOG_UPDATE, servicioRadioDto.getId());			
		}

		return vista;		
	}
	
	/**
	 * Preparamos la vista para la eliminaci??n del servicio de radio pasado como par??metro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_DELETE_TIPO + "/{id}")
	public String deleteGET(@PathVariable("id") final Short id, final Model model) {
		
		// Contenido
		ServicioRadioDto servicioRadioDto = this.servicioRadioService.read(id);
		Long servicioRadioAsignado = this.frecuenciaATCRepository.countByTipoServicio(this.modelMapperUtils.map(servicioRadioDto, ServicioRadio.class));
		
		model.addAttribute(ATTRIBUTE_TIPO, servicioRadioDto);
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_PREGUNTA, 
				MessagesConstants.POPUP_ELIMINAR_SERVICIORADIO_PREGUNTA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_NO_PERMITIDO_MENSAJE, 
				MessagesConstants.POPUP_ELIMINAR_SERVICIORADIO_NO_PERMITIDO_MENSAJE);
		if (servicioRadioAsignado > 0) {
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_NO_PERMITIDO_ACTIVO, Boolean.TRUE);
		} else {
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_NO_PERMITIDO_ACTIVO, Boolean.FALSE);
		}
		
		// Activaci??n de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.TRUE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_DELETE_TIPO
				.concat(ControllerConstants.MAP_ACTION_SLASH).concat(String.valueOf(id)));
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS);
					
		log.info(LoggerConstants.LOG_DELETE);
		
		return VIEW_TIPO;		
	}
	
	/**
	 * Eliminaci??n del servicio de radio pasado como par??metro
	 * @param id Identificador
	 * @return Vista
	 */
	@PostMapping(MAP_DELETE_TIPO + "/{id}")
	public String deletePOST(@PathVariable("id") final Short id) {		
		this.servicioRadioService.delete(id);					
		log.info(LoggerConstants.LOG_DELETE);		
		return ControllerConstants.REDIRECT.concat(MAP_READALL_TIPOS);		
	}
}
