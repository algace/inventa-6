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

import com.dbcom.app.constants.ApplicationConstants;
import com.dbcom.app.constants.ControllerConstants;
import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.constants.MessagesConstants;
import com.dbcom.app.model.dto.FrecuenciaDto;
import com.dbcom.app.service.FrecuenciaService;
import com.dbcom.app.service.TipoBandaFrecuenciaService;
import com.dbcom.app.service.TipoFuenteInformacionService;
import com.dbcom.app.service.TipoUnidadFrecuenciaService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eduardo.tubilleja
 * Enlace entre la vista y la lógica de negocio
 */
@Slf4j
@Controller
public final class FrecuenciaController {

	// Atributos de la vista
	private static final String ATTRIBUTE_FRECUENCIA = "frecuencia";
	private static final String ATTRIBUTE_TIPOS_FUENTE_INFORMACION = "tiposFuenteInformacion";
	private static final String ATTRIBUTE_TIPOS_UNIDAD_FRECUENCIA = "tiposUnidadFrecuencia";
	private static final String ATTRIBUTE_TIPOS_BANDA_FRECUENCIA = "tiposBandaFrecuencia";

	// Vistas	
	private static final String VIEW_FRECUENCIA = ControllerConstants.MAP_PATH_MENU + ATTRIBUTE_FRECUENCIA;
	private static final String VIEW_FRECUENCIAS = ControllerConstants.MAP_PATH_MENU + "frecuencias";

	// Mapeo de las acciones
	public static final String MAP_CREATE_FRECUENCIA =  ControllerConstants.MAP_ACTION_SLASH + VIEW_FRECUENCIA 
			+ ControllerConstants.MAP_ACTION_CREAR;
	public static final String MAP_DELETE_FRECUENCIA = ControllerConstants.MAP_ACTION_SLASH + VIEW_FRECUENCIA 
			+ ControllerConstants.MAP_ACTION_BORRAR;
	public static final String MAP_SAVE_FRECUENCIA = ControllerConstants.MAP_ACTION_SLASH + VIEW_FRECUENCIA 
			+ ControllerConstants.MAP_ACTION_GUARDAR;
	public static final String MAP_UPDATE_FRECUENCIA = ControllerConstants.MAP_ACTION_SLASH + VIEW_FRECUENCIA
			+ ControllerConstants.MAP_ACTION_MODIFICAR;
	public static final String MAP_READ_FRECUENCIA =  ControllerConstants.MAP_ACTION_SLASH + VIEW_FRECUENCIA;
	public static final String MAP_READALL_FRECUENCIAS = ControllerConstants.MAP_ACTION_SLASH + VIEW_FRECUENCIAS;

	private final FrecuenciaService frecuenciaService;
	private final TipoUnidadFrecuenciaService tipoUnidadFrecuenciaService;
	private final TipoBandaFrecuenciaService tipoBandaFrecuenciaService;
	private final TipoFuenteInformacionService tipoFuenteInformacionService;
		
	@Autowired
	public FrecuenciaController(FrecuenciaService frecuenciaService,
			TipoUnidadFrecuenciaService tipoUnidadFrecuenciaService,
			TipoBandaFrecuenciaService tipoBandaFrecuenciaService,
			TipoFuenteInformacionService tipoFuenteInformacionService) {
		this.frecuenciaService = frecuenciaService;
		this.tipoUnidadFrecuenciaService = tipoUnidadFrecuenciaService;
		this.tipoBandaFrecuenciaService = tipoBandaFrecuenciaService;
		this.tipoFuenteInformacionService = tipoFuenteInformacionService;
	}
	
	/**
	 * Obtenemos un listado de las frecuencias
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READALL_FRECUENCIAS)
	public String readAll(final Model model) {
		
		// Contenido
		model.addAttribute(ControllerConstants.ATTRIBUTE_LISTA, this.frecuenciaService.readAll());		

		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_LEER, MAP_READ_FRECUENCIA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_AGNADIR, MAP_CREATE_FRECUENCIA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_MODIFICAR, MAP_UPDATE_FRECUENCIA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_BORRAR, MAP_DELETE_FRECUENCIA);
					
		log.info(LoggerConstants.LOG_READALL);
		
		return VIEW_FRECUENCIAS;		
	}
	
	/**
	 * Creamos una frecuencia sin persistencia
	 * @param model modelo
	 * @return Vista
	 */
	@GetMapping(value = MAP_CREATE_FRECUENCIA)
	public String create(final Model model) {

		// Creamos el registro
		model.addAttribute(ATTRIBUTE_FRECUENCIA, this.frecuenciaService.create());
		
		//Obtenemos los tipos de unidades de frecuencia
		model.addAttribute(ATTRIBUTE_TIPOS_UNIDAD_FRECUENCIA, this.tipoUnidadFrecuenciaService.getTiposUnidadFrecuenciaConValorPorDefecto(ApplicationConstants.UNIDAD_FRECUENCIA_POR_DEFECTO));
		
		//Obtenemos los tipos de bandas de frecuencia
		model.addAttribute(ATTRIBUTE_TIPOS_BANDA_FRECUENCIA, this.tipoBandaFrecuenciaService.getTiposBandaFrecuenciaConValorPorDefecto(ApplicationConstants.TIPO_BANDA_FRECUENCIA_POR_DEFECTO));

		//Obtenemos los tipos de fuentes de información
		model.addAttribute(ATTRIBUTE_TIPOS_FUENTE_INFORMACION, this.tipoFuenteInformacionService.getTiposFuenteInformacionConValorPorDefecto(ApplicationConstants.FUENTE_INFORMACION_POR_DEFECTO_FRECUENCIA));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_FRECUENCIA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_FRECUENCIAS);
					
		log.info(LoggerConstants.LOG_CREATE);
		
		return VIEW_FRECUENCIA;		
	}
	
	/**
	 * Persistimos la frecuencia pasada como parámetro
	 * @param frecuenciaDto Frecuencia a persistir
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_SAVE_FRECUENCIA)
	public String save(@Valid @ModelAttribute(ATTRIBUTE_FRECUENCIA) final FrecuenciaDto frecuenciaDto, 
			final BindingResult bindingResult, final Model model) {	
		
		final String vista;
		if (bindingResult.hasErrors()) {

			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_FRECUENCIA);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_FRECUENCIAS);
		
			//Obtenemos los tipos de unidades de frecuencia
			model.addAttribute(ATTRIBUTE_TIPOS_UNIDAD_FRECUENCIA, this.tipoUnidadFrecuenciaService.getTiposUnidadFrecuenciaConValorPorDefecto(ApplicationConstants.UNIDAD_FRECUENCIA_POR_DEFECTO));

			//Obtenemos los tipos de bandas de frecuencia
			model.addAttribute(ATTRIBUTE_TIPOS_BANDA_FRECUENCIA, this.tipoBandaFrecuenciaService.getTiposBandaFrecuenciaConValorPorDefecto(ApplicationConstants.TIPO_BANDA_FRECUENCIA_POR_DEFECTO));

			//Obtenemos los tipos de fuentes de información
			model.addAttribute(ATTRIBUTE_TIPOS_FUENTE_INFORMACION, this.tipoFuenteInformacionService.getTiposFuenteInformacionConValorPorDefecto(ApplicationConstants.FUENTE_INFORMACION_POR_DEFECTO_FRECUENCIA));
			
			vista = VIEW_FRECUENCIA;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());	
		
		} else {		
			this.frecuenciaService.saveUpdate(frecuenciaDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_FRECUENCIAS);
			log.info(LoggerConstants.LOG_SAVE, frecuenciaDto.getId());
		}
		
		return vista;
	}
	
	/**
	 * Obtenemos la frecuencia con el id facilitado
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READ_FRECUENCIA + "/{id}")
	public String read(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_FRECUENCIA, this.frecuenciaService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_ELIMINAR, MAP_READALL_FRECUENCIAS);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_FRECUENCIAS);
					
		log.info(LoggerConstants.LOG_READ);
		
		return VIEW_FRECUENCIA;
		
	}
	
	/**
	 * Preparamos la vista para la actulización de la frecuencia pasada como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_UPDATE_FRECUENCIA + "/{id}")
	public String updateGET(@PathVariable("id") final Long id, final Model model) {
	
		// Contenido
		model.addAttribute(ATTRIBUTE_FRECUENCIA, this.frecuenciaService.read(id));
		
		//Obtenemos los tipos de unidades de frecuencia
		model.addAttribute(ATTRIBUTE_TIPOS_UNIDAD_FRECUENCIA, this.tipoUnidadFrecuenciaService.getTiposUnidadFrecuenciaConValorPorDefecto(ApplicationConstants.UNIDAD_FRECUENCIA_POR_DEFECTO));

		//Obtenemos los tipos de bandas de frecuencia
		model.addAttribute(ATTRIBUTE_TIPOS_BANDA_FRECUENCIA, this.tipoBandaFrecuenciaService.getTiposBandaFrecuenciaConValorPorDefecto(ApplicationConstants.TIPO_BANDA_FRECUENCIA_POR_DEFECTO));

		//Obtenemos los tipos de fuentes de información
		model.addAttribute(ATTRIBUTE_TIPOS_FUENTE_INFORMACION, this.tipoFuenteInformacionService.getTiposFuenteInformacionConValorPorDefecto(ApplicationConstants.FUENTE_INFORMACION_POR_DEFECTO_FRECUENCIA));

		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_FRECUENCIA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_FRECUENCIAS);
					
		log.info(LoggerConstants.LOG_UPDATE);
		
		return VIEW_FRECUENCIA;
		
	}
	
	/**
	 * Actualizamos la frecuencia pasada como parámetro
	 * @param frecuenciaDto Frecuencia a actualizar
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_UPDATE_FRECUENCIA)
	public String updatePOST(@Valid @ModelAttribute(ATTRIBUTE_FRECUENCIA) final FrecuenciaDto frecuenciaDto, 
			final BindingResult bindingResult, final Model model) {		
		
		final String vista;
		if (bindingResult.hasErrors()) {	

			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
	
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_FRECUENCIA);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_FRECUENCIAS);
			
			//Obtenemos los tipos de unidades de frecuencia
			model.addAttribute(ATTRIBUTE_TIPOS_UNIDAD_FRECUENCIA, this.tipoUnidadFrecuenciaService.getTiposUnidadFrecuenciaConValorPorDefecto(ApplicationConstants.UNIDAD_FRECUENCIA_POR_DEFECTO));

			//Obtenemos los tipos de bandas de frecuencia
			model.addAttribute(ATTRIBUTE_TIPOS_BANDA_FRECUENCIA, this.tipoBandaFrecuenciaService.getTiposBandaFrecuenciaConValorPorDefecto(ApplicationConstants.TIPO_BANDA_FRECUENCIA_POR_DEFECTO));

			//Obtenemos los tipos de fuentes de información
			model.addAttribute(ATTRIBUTE_TIPOS_FUENTE_INFORMACION, this.tipoFuenteInformacionService.getTiposFuenteInformacionConValorPorDefecto(ApplicationConstants.FUENTE_INFORMACION_POR_DEFECTO_FRECUENCIA));

			vista = VIEW_FRECUENCIA;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());		
		
		} else {
			this.frecuenciaService.saveUpdate(frecuenciaDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_FRECUENCIAS);
			log.info(LoggerConstants.LOG_UPDATE, frecuenciaDto.getId());			
		}

		return vista;		
	}
	
	/**
	 * Preparamos la vista para la eliminación de la frecuencia pasada como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_DELETE_FRECUENCIA + "/{id}")
	public String deleteGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_FRECUENCIA, this.frecuenciaService.read(id));
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_PREGUNTA, 
				MessagesConstants.POPUP_ELIMINAR_EQUIPAMIENTO_PREGUNTA);

		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.TRUE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_DELETE_FRECUENCIA
				.concat(ControllerConstants.MAP_ACTION_SLASH).concat(String.valueOf(id)));
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_FRECUENCIAS);
					
		log.info(LoggerConstants.LOG_DELETE);
		
		return VIEW_FRECUENCIA;		
	}
	
	/**
	 * Eliminación de la frecuencia pasada como parámetro
	 * @param id Identificador
	 * @return Vista
	 */
	@PostMapping(MAP_DELETE_FRECUENCIA + "/{id}")
	public String deletePOST(@PathVariable("id") final Long id) {		
		this.frecuenciaService.delete(id);					
		log.info(LoggerConstants.LOG_DELETE);		
		return ControllerConstants.REDIRECT.concat(MAP_READALL_FRECUENCIAS);		
	}
	
}
