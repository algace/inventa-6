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
import com.dbcom.app.model.dto.FrecuenciaATCDto;
import com.dbcom.app.model.dto.PropietarioDto;
import com.dbcom.app.service.FrecuenciaATCService;
import com.dbcom.app.service.PropietarioService;
import com.dbcom.app.service.ServicioRadioService;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eduardo.tubilleja
 * Enlace entre la vista y la lógica de negocio
 */
@Slf4j
@Controller
public final class FrecuenciaATCController {

	// Atributos de la vista
	private static final String ATTRIBUTE_FRECUENCIAATC = "frecuenciaATC";

	// Vistas	
	private static final String VIEW_FRECUENCIAATC = ControllerConstants.MAP_PATH_MENU + ATTRIBUTE_FRECUENCIAATC;
	private static final String VIEW_FRECUENCIAS = ControllerConstants.MAP_PATH_MENU + "frecuenciasATC";

	// Mapeo de las acciones
	public static final String MAP_CREATE_FRECUENCIAATC =  ControllerConstants.MAP_ACTION_SLASH + VIEW_FRECUENCIAATC
			+ ControllerConstants.MAP_ACTION_CREAR;
	public static final String MAP_DELETE_FRECUENCIAATC = ControllerConstants.MAP_ACTION_SLASH + VIEW_FRECUENCIAATC 
			+ ControllerConstants.MAP_ACTION_BORRAR;
	public static final String MAP_SAVE_FRECUENCIAATC = ControllerConstants.MAP_ACTION_SLASH + VIEW_FRECUENCIAATC 
			+ ControllerConstants.MAP_ACTION_GUARDAR;
	public static final String MAP_UPDATE_FRECUENCIAATC = ControllerConstants.MAP_ACTION_SLASH + VIEW_FRECUENCIAATC
			+ ControllerConstants.MAP_ACTION_MODIFICAR;
	public static final String MAP_READ_FRECUENCIAATC =  ControllerConstants.MAP_ACTION_SLASH + VIEW_FRECUENCIAATC;
	public static final String MAP_READALL_FRECUENCIASATC = ControllerConstants.MAP_ACTION_SLASH + VIEW_FRECUENCIAS;

	private final FrecuenciaATCService frecuenciaATCService;
	private final PropietarioService propietarioService;
	private final ModelMapperUtils  modelMapperUtils;
	private final ServicioRadioService servicioRadioService;
	
	@Autowired
	public FrecuenciaATCController(FrecuenciaATCService frecuenciaATCService, 
								   PropietarioService propietarioService,
								   ModelMapperUtils  modelMapperUtils,
								   ServicioRadioService servicioRadioService) {
		this.frecuenciaATCService = frecuenciaATCService;
		this.propietarioService = propietarioService;
		this.modelMapperUtils = modelMapperUtils;
		this.servicioRadioService = servicioRadioService;
	}
	
	
	/**
	 * Obtenemos un listado de las frecuenciasATC
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READALL_FRECUENCIASATC)
	public String readAll(final Model model) {
		
		// Contenido
		model.addAttribute(ControllerConstants.ATTRIBUTE_LISTA, this.frecuenciaATCService.readAll());		

		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_LEER, MAP_READ_FRECUENCIAATC);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_AGNADIR, MAP_CREATE_FRECUENCIAATC);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_MODIFICAR, MAP_UPDATE_FRECUENCIAATC);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_BORRAR, MAP_DELETE_FRECUENCIAATC);
					
		log.info(LoggerConstants.LOG_READALL);
		
		return VIEW_FRECUENCIAS;		
	}
	
	/**
	 * Creamos una frecuenciaATC sin persistencia
	 * @param model modelo
	 * @return Vista
	 */
	@GetMapping(value = MAP_CREATE_FRECUENCIAATC)
	public String create(final Model model) {

		// Creamos el registro
		model.addAttribute(ATTRIBUTE_FRECUENCIAATC, this.frecuenciaATCService.create());
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_FRECUENCIAATC);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_FRECUENCIASATC);
					
		log.info(LoggerConstants.LOG_CREATE);
		
		return VIEW_FRECUENCIAATC;		
	}
	
	/**
	 * Persistimos la frecuenciaATC pasada como parámetro
	 * @param frecuenciaATCDto FrecuenciaATC a persistir
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_SAVE_FRECUENCIAATC)
	public String save(@Valid @ModelAttribute(ATTRIBUTE_FRECUENCIAATC) final FrecuenciaATCDto frecuenciaATCDto, 
			final BindingResult bindingResult, final Model model) {	
		
		final String vista;
		if (bindingResult.hasErrors()) {

			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_FRECUENCIAATC);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_FRECUENCIASATC);
		
			//se recupera la lista de servicios de radio
			frecuenciaATCDto.setTiposServicioDisponibles(servicioRadioService.readAll());
			
			//se recupera la lista de propietarios con el valor por defecto
			frecuenciaATCDto.setTitularesDisponibles(propietarioService.getPropietariosConValorPorDefecto());
			frecuenciaATCDto.setTitular(this.modelMapperUtils.map(frecuenciaATCDto.getTitularesDisponibles().get(0), PropietarioDto.class));
			
			vista = VIEW_FRECUENCIAATC;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());	
		
		} else {		
			this.frecuenciaATCService.saveUpdate(frecuenciaATCDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_FRECUENCIASATC);
			log.info(LoggerConstants.LOG_SAVE, frecuenciaATCDto.getId());
		}
		
		return vista;
	}
	
	/**
	 * Obtenemos la frecuenciaATC con el id facilitado
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READ_FRECUENCIAATC + "/{id}")
	public String read(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_FRECUENCIAATC, this.frecuenciaATCService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_ELIMINAR, MAP_READALL_FRECUENCIASATC);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_FRECUENCIASATC);
					
		log.info(LoggerConstants.LOG_READ);
		
		return VIEW_FRECUENCIAATC;
		
	}
	
	/**
	 * Preparamos la vista para la actulización de la frecuenciaATC pasada como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_UPDATE_FRECUENCIAATC + "/{id}")
	public String updateGET(@PathVariable("id") final Long id, final Model model) {
	
		// Contenido
		model.addAttribute(ATTRIBUTE_FRECUENCIAATC, this.frecuenciaATCService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_FRECUENCIAATC);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_FRECUENCIASATC);
					
		log.info(LoggerConstants.LOG_UPDATE);
		
		return VIEW_FRECUENCIAATC;
		
	}
	
	/**
	 * Actualizamos la frecuenciaATC pasada como parámetro
	 * @param frecuenciaATCDto FrecuenciaATC a actualizar
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_UPDATE_FRECUENCIAATC)
	public String updatePOST(@Valid @ModelAttribute(ATTRIBUTE_FRECUENCIAATC) final FrecuenciaATCDto frecuenciaATCDto, 
			final BindingResult bindingResult, final Model model) {		
		
		final String vista;
		if (bindingResult.hasErrors()) {	

			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
	
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_FRECUENCIAATC);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_FRECUENCIASATC);
			
			//se recupera la lista de servicios de radio
			frecuenciaATCDto.setTiposServicioDisponibles(servicioRadioService.readAll());
			
			//se recupera la lista de propietarios con el valor por defecto
			frecuenciaATCDto.setTitularesDisponibles(propietarioService.getPropietariosConValorPorDefecto());
			frecuenciaATCDto.setTitular(this.modelMapperUtils.map(frecuenciaATCDto.getTitularesDisponibles().get(0), PropietarioDto.class));
			
			
			vista = VIEW_FRECUENCIAATC;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());		
		
		} else {
			this.frecuenciaATCService.saveUpdate(frecuenciaATCDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_FRECUENCIASATC);
			log.info(LoggerConstants.LOG_UPDATE, frecuenciaATCDto.getId());			
		}

		return vista;		
	}
	
	/**
	 * Preparamos la vista para la eliminación de la frecuenciaATC pasada como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_DELETE_FRECUENCIAATC + "/{id}")
	public String deleteGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_FRECUENCIAATC, this.frecuenciaATCService.read(id));
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_PREGUNTA, 
				MessagesConstants.POPUP_ELIMINAR_EQUIPAMIENTO_PREGUNTA);

		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.TRUE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_DELETE_FRECUENCIAATC
				.concat(ControllerConstants.MAP_ACTION_SLASH).concat(String.valueOf(id)));
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_FRECUENCIASATC);
					
		log.info(LoggerConstants.LOG_DELETE);
		
		return VIEW_FRECUENCIAATC;		
	}
	
	/**
	 * Eliminación de la frecuenciaATC pasada como parámetro
	 * @param id Identificador
	 * @return Vista
	 */
	@PostMapping(MAP_DELETE_FRECUENCIAATC + "/{id}")
	public String deletePOST(@PathVariable("id") final Long id) {		
		this.frecuenciaATCService.delete(id);					
		log.info(LoggerConstants.LOG_DELETE);		
		return ControllerConstants.REDIRECT.concat(MAP_READALL_FRECUENCIASATC);		
	}
	
}
