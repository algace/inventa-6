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
import com.dbcom.app.model.dto.AirblockDto;
import com.dbcom.app.model.entity.Airblock;
import com.dbcom.app.service.AirblockService;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eduardo.tubilleja
 * Enlace entre la vista y la lógica de negocio
 */
@Slf4j
@Controller
public final class AirblockController {

	// Atributos de la vista
	private static final String ATTRIBUTE_AIRBLOCK = "airblock";

	// Vistas	
	private static final String VIEW_AIRBLOCK = ControllerConstants.MAP_PATH_MENU_SECTORESESPACIOAEREO + ATTRIBUTE_AIRBLOCK;
	private static final String VIEW_AIRBLOCKS = ControllerConstants.MAP_PATH_MENU_SECTORESESPACIOAEREO + "airblocks";
	

	// Mapeo de las acciones
	public static final String MAP_CREATE_AIRBLOCK =  ControllerConstants.MAP_ACTION_SLASH + VIEW_AIRBLOCK 
			+ ControllerConstants.MAP_ACTION_CREAR;
	public static final String MAP_DELETE_AIRBLOCK = ControllerConstants.MAP_ACTION_SLASH + VIEW_AIRBLOCK 
			+ ControllerConstants.MAP_ACTION_BORRAR;
	public static final String MAP_SAVE_AIRBLOCK = ControllerConstants.MAP_ACTION_SLASH + VIEW_AIRBLOCK 
			+ ControllerConstants.MAP_ACTION_GUARDAR;
	public static final String MAP_UPDATE_AIRBLOCK = ControllerConstants.MAP_ACTION_SLASH + VIEW_AIRBLOCK 
			+ ControllerConstants.MAP_ACTION_MODIFICAR;
	public static final String MAP_READ_AIRBLOCK =  ControllerConstants.MAP_ACTION_SLASH + VIEW_AIRBLOCK;
	public static final String MAP_READALL_AIRBLOCKS = ControllerConstants.MAP_ACTION_SLASH + VIEW_AIRBLOCKS;

	private final AirblockService airblockService;
	private final SectorATCRepository sectorATCRepository;
	private final ModelMapperUtils modelMapperUtils;
	
	@Autowired
	public AirblockController(AirblockService airblockService,
			SectorATCRepository sectorATCRepository,
			ModelMapperUtils  modelMapperUtils) {
		this.airblockService = airblockService;
		this.sectorATCRepository = sectorATCRepository;
		this.modelMapperUtils = modelMapperUtils;
	}
	
	/**
	 * Obtenemos un listado de los airblocks
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READALL_AIRBLOCKS)
	public String readAll(final Model model) {
		
		// Contenido
		model.addAttribute(ControllerConstants.ATTRIBUTE_LISTA, this.airblockService.readAll());		

		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_LEER, MAP_READ_AIRBLOCK);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_AGNADIR, MAP_CREATE_AIRBLOCK);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_MODIFICAR, MAP_UPDATE_AIRBLOCK);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_BORRAR, MAP_DELETE_AIRBLOCK);
					
		log.info(LoggerConstants.LOG_READALL);
		
		return VIEW_AIRBLOCKS;		
	}
	
	/**
	 * Creamos un airblock sin persistencia
	 * @param model modelo
	 * @return Vista
	 */
	@GetMapping(value = MAP_CREATE_AIRBLOCK)
	public String create(final Model model) {

		// Creamos el registro
		model.addAttribute(ATTRIBUTE_AIRBLOCK, this.airblockService.create());
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_SECTORES_ATC_VISIBLE, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_AIRBLOCK);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_AIRBLOCKS);
					
		log.info(LoggerConstants.LOG_CREATE);
		
		return VIEW_AIRBLOCK;		
	}
	
	/**
	 * Persistimos el airblock pasado como parámetro
	 * @param airblockDto Airblock a persistir
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_SAVE_AIRBLOCK)
	public String save(@Valid @ModelAttribute(ATTRIBUTE_AIRBLOCK) final AirblockDto airblockDto, 
			final BindingResult bindingResult, final Model model) {	
		
		final String vista;
		if (bindingResult.hasErrors()) {

			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_SECTORES_ATC_VISIBLE, Boolean.FALSE);
			
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_AIRBLOCK);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_AIRBLOCKS);
		
			vista = VIEW_AIRBLOCK;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());	
		
		} else {		
			this.airblockService.saveUpdate(airblockDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_AIRBLOCKS);
			log.info(LoggerConstants.LOG_SAVE, airblockDto.getId());
		}
		
		return vista;
	}
	
	/**
	 * Obtenemos el airblock con el id facilitado
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READ_AIRBLOCK + "/{id}")
	public String read(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_AIRBLOCK, this.airblockService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_SECTORES_ATC_VISIBLE, Boolean.TRUE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_ELIMINAR, MAP_READALL_AIRBLOCKS);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_AIRBLOCKS);
					
		log.info(LoggerConstants.LOG_READ);
		
		return VIEW_AIRBLOCK;
		
	}
	
	/**
	 * Preparamos la vista para la actulización del airblock pasado como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_UPDATE_AIRBLOCK + "/{id}")
	public String updateGET(@PathVariable("id") final Long id, final Model model) {
	
		// Contenido
		model.addAttribute(ATTRIBUTE_AIRBLOCK, this.airblockService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_SECTORES_ATC_VISIBLE, Boolean.TRUE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_AIRBLOCK);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_AIRBLOCKS);
					
		log.info(LoggerConstants.LOG_UPDATE);
		
		return VIEW_AIRBLOCK;
		
	}
	
	/**
	 * Actualizamos el airblock pasado como parámetro
	 * @param airblockDto Airblock a actualizar
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_UPDATE_AIRBLOCK)
	public String updatePOST(@Valid @ModelAttribute(ATTRIBUTE_AIRBLOCK) final AirblockDto airblockDto, 
			final BindingResult bindingResult, final Model model) {		
		
		final String vista;
		if (bindingResult.hasErrors()) {	

			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_SECTORES_ATC_VISIBLE, Boolean.TRUE);
	
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_AIRBLOCK);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_AIRBLOCKS);
			
			vista = VIEW_AIRBLOCK;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());		
		
		} else {
			this.airblockService.saveUpdate(airblockDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_AIRBLOCKS);
			log.info(LoggerConstants.LOG_UPDATE, airblockDto.getId());			
		}

		return vista;		
	}
	
	/**
	 * Preparamos la vista para la eliminación del airblock pasado como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_DELETE_AIRBLOCK + "/{id}")
	public String deleteGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		AirblockDto airblockDto = this.airblockService.read(id);
		Long tipoSectorAsignado = this.sectorATCRepository.countByAirblocks(this.modelMapperUtils.map(airblockDto, Airblock.class));
		
		model.addAttribute(ATTRIBUTE_AIRBLOCK, airblockDto);
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_PREGUNTA, 
				MessagesConstants.POPUP_ELIMINAR_AIRBLOCK_PREGUNTA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_NO_PERMITIDO_MENSAJE, 
				MessagesConstants.POPUP_ELIMINAR_AIRBLOCK_NO_PERMITIDO_MENSAJE);
		if (tipoSectorAsignado > 0) {
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_NO_PERMITIDO_ACTIVO, Boolean.TRUE);
		} else {
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_NO_PERMITIDO_ACTIVO, Boolean.FALSE);
		}

		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_SECTORES_ATC_VISIBLE, Boolean.TRUE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_DELETE_AIRBLOCK
				.concat(ControllerConstants.MAP_ACTION_SLASH).concat(String.valueOf(id)));
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_AIRBLOCKS);
					
		log.info(LoggerConstants.LOG_DELETE);
		
		return VIEW_AIRBLOCK;		
	}
	
	/**
	 * Eliminación del airblock pasado como parámetro
	 * @param id Identificador
	 * @return Vista
	 */
	@PostMapping(MAP_DELETE_AIRBLOCK + "/{id}")
	public String deletePOST(@PathVariable("id") final Long id) {		
		this.airblockService.delete(id);					
		log.info(LoggerConstants.LOG_DELETE);		
		return ControllerConstants.REDIRECT.concat(MAP_READALL_AIRBLOCKS);		
	}
	
}
