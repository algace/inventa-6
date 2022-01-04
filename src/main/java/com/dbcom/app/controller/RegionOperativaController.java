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
import com.dbcom.app.model.dao.SectorATCRepository;
import com.dbcom.app.model.dao.SectorOACIRepository;
import com.dbcom.app.model.dto.RegionOperativaDto;
import com.dbcom.app.model.entity.RegionOperativa;
import com.dbcom.app.service.RegionOperativaService;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author neoris
 * Enlace entre la vista y la lógica de negocio
 */
@Slf4j
@Controller
public class RegionOperativaController {

	// Atributos de la vista
	private static final String ATTRIBUTE_REGION_OPERATIVA = "regionOperativa";
			
	// Vistas	
	private static final String VIEW_REGION_OPERATIVA = ControllerConstants.MAP_PATH_MENU_ORGANIZACION + ATTRIBUTE_REGION_OPERATIVA;
	private static final String VIEW_REGIONES_OPERATIVAS = ControllerConstants.MAP_PATH_MENU_ORGANIZACION + "regionesOperativas";
			
	// Mapeo de las acciones
	public static final String MAP_CREATE_REGION_OPERATIVA = ControllerConstants.MAP_ACTION_SLASH + VIEW_REGION_OPERATIVA 
			+ ControllerConstants.MAP_ACTION_CREAR;
	public static final String MAP_DELETE_REGION_OPERATIVA = ControllerConstants.MAP_ACTION_SLASH + VIEW_REGION_OPERATIVA 
			+ ControllerConstants.MAP_ACTION_BORRAR;
	public static final String MAP_SAVE_REGION_OPERATIVA = ControllerConstants.MAP_ACTION_SLASH + VIEW_REGION_OPERATIVA 
			+ ControllerConstants.MAP_ACTION_GUARDAR;
	public static final String MAP_UPDATE_REGION_OPERATIVA = ControllerConstants.MAP_ACTION_SLASH + VIEW_REGION_OPERATIVA 
			+ ControllerConstants.MAP_ACTION_MODIFICAR;
	public static final String MAP_READ_REGION_OPERATIVA = ControllerConstants.MAP_ACTION_SLASH + VIEW_REGION_OPERATIVA;
	public static final String MAP_READALL_REGIONES_OPERATIVAS = ControllerConstants.MAP_ACTION_SLASH + VIEW_REGIONES_OPERATIVAS;
	
	private final RegionOperativaService regionOperativaService;
	private final SectorATCRepository sectorATCRepository;
	private final SectorOACIRepository sectorOACIRepository;
	private final ModelMapperUtils modelMapperUtils;
	
	@Autowired
	public RegionOperativaController(RegionOperativaService regionOperativaService,
			SectorATCRepository sectorATCRepository,
			SectorOACIRepository sectorOACIRepository,
			ModelMapperUtils  modelMapperUtils) {
		this.regionOperativaService = regionOperativaService;
		this.sectorATCRepository = sectorATCRepository;
		this.sectorOACIRepository = sectorOACIRepository;
		this.modelMapperUtils = modelMapperUtils;
	}
	
	/**
	 * Obtenemos un listado de las regiones operativas
	 * @param model Modelo
	 * @return Vista
	 */	
	@GetMapping(MAP_READALL_REGIONES_OPERATIVAS)
	public String readAll(final Model model) {
		
		// Contenido
		model.addAttribute(ControllerConstants.ATTRIBUTE_LISTA, this.regionOperativaService.readAll());		

		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_LEER, MAP_READ_REGION_OPERATIVA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_AGNADIR, MAP_CREATE_REGION_OPERATIVA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_MODIFICAR, MAP_UPDATE_REGION_OPERATIVA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_BORRAR, MAP_DELETE_REGION_OPERATIVA);
					
		log.info(LoggerConstants.LOG_READALL);
		
		return VIEW_REGIONES_OPERATIVAS;		
	}

	/**
	 * Creamos una región operativa sin persistencia
	 * @param model modelo
	 * @return Vista
	 */
	@GetMapping(value = MAP_CREATE_REGION_OPERATIVA)
	public String create(final Model model) {

		// Creamos el registro
		model.addAttribute(ATTRIBUTE_REGION_OPERATIVA, this.regionOperativaService.create());
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_REGION_OPERATIVA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REGIONES_OPERATIVAS);
					
		log.info(LoggerConstants.LOG_CREATE);
		
		return VIEW_REGION_OPERATIVA;		
	}

	/**
	 * Persistimos la region operativa pasada como parámetro
	 * @param regionOperativaDto Región operativa a persistir
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_SAVE_REGION_OPERATIVA)
	public String save(@Valid @ModelAttribute(ATTRIBUTE_REGION_OPERATIVA) final RegionOperativaDto regionOperativaDto, 
			final BindingResult bindingResult, final Model model) {	
		
		final String vista;
		if (bindingResult.hasErrors()) {
			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			//model.addAttribute(ControllerConstants.ATTRIBUTE_TIPOS_SUBSISTEMAS_VISIBLE, Boolean.FALSE);
			
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_REGION_OPERATIVA);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REGIONES_OPERATIVAS);
		
			vista = VIEW_REGION_OPERATIVA;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());	
	
		} else {		
			this.regionOperativaService.save(regionOperativaDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_REGIONES_OPERATIVAS);
			log.info(LoggerConstants.LOG_SAVE, regionOperativaDto.getId());
		}
		
		return vista;
	}

	/**
	 * Obtenemos la región operativa con el id facilitado
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READ_REGION_OPERATIVA + "/{id}")
	public String read(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_REGION_OPERATIVA, this.regionOperativaService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_ELIMINAR, MAP_READALL_REGIONES_OPERATIVAS);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REGIONES_OPERATIVAS);
					
		log.info(LoggerConstants.LOG_READ);
		
		return VIEW_REGION_OPERATIVA;
	}

	/**
	 * Preparamos la vista para la actulización de la región operativa pasada como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_UPDATE_REGION_OPERATIVA + "/{id}")
	public String updateGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_REGION_OPERATIVA, this.regionOperativaService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_REGION_OPERATIVA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REGIONES_OPERATIVAS);
					
		log.info(LoggerConstants.LOG_UPDATE);
		
		return VIEW_REGION_OPERATIVA;
	}

	/**
	 * Actualizamos la región operativa pasada como parámetro
	 * @param tipoSubsistemaDto Tipo de subsistema a actualizar
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_UPDATE_REGION_OPERATIVA)
	public String updatePOST(@Valid @ModelAttribute(ATTRIBUTE_REGION_OPERATIVA) final RegionOperativaDto regionOperativaDto, 
			final BindingResult bindingResult, final Model model) {		
		
		final String vista;
		if (bindingResult.hasErrors()) {			

			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
	
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_REGION_OPERATIVA);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REGIONES_OPERATIVAS);
		
			vista = VIEW_REGION_OPERATIVA;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());		
		
		} else {
			this.regionOperativaService.update(regionOperativaDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_REGIONES_OPERATIVAS);
			log.info(LoggerConstants.LOG_UPDATE, regionOperativaDto.getId());			
		}

		return vista;		
	}

	/**
	 * Preparamos la vista para la eliminación de la región operativa pasada como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_DELETE_REGION_OPERATIVA + "/{id}")
	public String deleteGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		RegionOperativaDto regionOperativaDto = this.regionOperativaService.read(id);
		Long sectorATCAsignado = this.sectorATCRepository.countByRegionOperativa(this.modelMapperUtils.map(regionOperativaDto, RegionOperativa.class));
		Long sectorOACIAsignado = this.sectorOACIRepository.countByRegionOperativa(this.modelMapperUtils.map(regionOperativaDto, RegionOperativa.class));
		
		model.addAttribute(ATTRIBUTE_REGION_OPERATIVA, regionOperativaDto);
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_PREGUNTA, 
				MessagesConstants.POPUP_ELIMINAR_REGION_OPERATIVA_PREGUNTA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_NO_PERMITIDO_MENSAJE, 
				MessagesConstants.POPUP_ELIMINAR_REGION_OPERATIVA_NO_PERMITIDO_MENSAJE);
		if ((sectorATCAsignado > 0) || (sectorOACIAsignado > 0)) {
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_NO_PERMITIDO_ACTIVO, Boolean.TRUE);
		} else {
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_NO_PERMITIDO_ACTIVO, Boolean.FALSE);
		}
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.TRUE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_DELETE_REGION_OPERATIVA
				.concat(ControllerConstants.MAP_ACTION_SLASH).concat(String.valueOf(id)));
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REGIONES_OPERATIVAS);
					
		log.info(LoggerConstants.LOG_DELETE);
		
		return VIEW_REGION_OPERATIVA;		
	}

	/**
	 * Eliminación de la la región operativa pasada como parámetro
	 * @param id Identificador
	 * @return Vista
	 */
	@PostMapping(MAP_DELETE_REGION_OPERATIVA + "/{id}")
	public String deletePOST(@PathVariable("id") final Long id) {		
		this.regionOperativaService.delete(id);					
		log.info(LoggerConstants.LOG_DELETE);		
		return ControllerConstants.REDIRECT.concat(MAP_READALL_REGIONES_OPERATIVAS);		
	}

}
