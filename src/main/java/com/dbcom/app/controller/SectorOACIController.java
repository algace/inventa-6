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
import com.dbcom.app.model.dto.RegionOperativaDto;
import com.dbcom.app.model.dto.SectorOACIDto;
import com.dbcom.app.service.RegionOperativaService;
import com.dbcom.app.service.SectorOACIService;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author neoris
 * Enlace entre la vista y la lógica de negocio
 */
@Slf4j
@Controller
public class SectorOACIController {

	// Atributos de la vista
	private static final String ATTRIBUTE_SECTOR_OACI = "sectorOACI";
			
	// Vistas	
	private static final String VIEW_SECTOR_OACI = ControllerConstants.MAP_PATH_MENU_SECTORESESPACIOAEREO + ATTRIBUTE_SECTOR_OACI;
	private static final String VIEW_SECTORES_OACI = ControllerConstants.MAP_PATH_MENU_SECTORESESPACIOAEREO + "sectoresOACI";
			
	// Mapeo de las acciones
	public static final String MAP_CREATE_SECTOR_OACI = ControllerConstants.MAP_ACTION_SLASH + VIEW_SECTOR_OACI 
			+ ControllerConstants.MAP_ACTION_CREAR;
	public static final String MAP_DELETE_SECTOR_OACI = ControllerConstants.MAP_ACTION_SLASH + VIEW_SECTOR_OACI 
			+ ControllerConstants.MAP_ACTION_BORRAR;
	public static final String MAP_SAVE_SECTOR_OACI = ControllerConstants.MAP_ACTION_SLASH + VIEW_SECTOR_OACI 
			+ ControllerConstants.MAP_ACTION_GUARDAR;
	public static final String MAP_UPDATE_SECTOR_OACI = ControllerConstants.MAP_ACTION_SLASH + VIEW_SECTOR_OACI 
			+ ControllerConstants.MAP_ACTION_MODIFICAR;
	public static final String MAP_READ_SECTOR_OACI = ControllerConstants.MAP_ACTION_SLASH + VIEW_SECTOR_OACI;
	public static final String MAP_READALL_SECTORES_OACI = ControllerConstants.MAP_ACTION_SLASH + VIEW_SECTORES_OACI;
	
	private final SectorOACIService sectorOACIService;
	private final RegionOperativaService regionOperativaService;
	private final ModelMapperUtils  modelMapperUtils;
	
	@Autowired
	public SectorOACIController(SectorOACIService sectorOACIService,
			RegionOperativaService regionOperativaService,
			ModelMapperUtils modelMapper) {
		this.sectorOACIService = sectorOACIService;
		this.regionOperativaService = regionOperativaService;
		this.modelMapperUtils = modelMapper;
	}
	
	
	/**
	 * Obtenemos un listado de los sectores OACI
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READALL_SECTORES_OACI)
	public String readAll(final Model model) {
		
		// Contenido
		model.addAttribute(ControllerConstants.ATTRIBUTE_LISTA, this.sectorOACIService.readAll());		

		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_LEER, MAP_READ_SECTOR_OACI);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_AGNADIR, MAP_CREATE_SECTOR_OACI);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_MODIFICAR, MAP_UPDATE_SECTOR_OACI);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_BORRAR, MAP_DELETE_SECTOR_OACI);
					
		log.info(LoggerConstants.LOG_READALL);
		
		return VIEW_SECTORES_OACI;		
	}
	
	/**
	 * Creamos un sector OACI sin persistencia
	 * @param model modelo
	 * @return Vista
	 */
	@GetMapping(value = MAP_CREATE_SECTOR_OACI)
	public String create(final Model model) {

		// Creamos el registro
		model.addAttribute(ATTRIBUTE_SECTOR_OACI, this.sectorOACIService.create());
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_SECTOR_OACI);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_SECTORES_OACI);
					
		log.info(LoggerConstants.LOG_CREATE);
		
		return VIEW_SECTOR_OACI;		
	}
	
	/**
	 * Persistimos el sector OACI pasado como parámetro
	 * @param sectorOACIDto Sector OACI a persistir
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_SAVE_SECTOR_OACI)
	public String save(@Valid @ModelAttribute(ATTRIBUTE_SECTOR_OACI) final SectorOACIDto sectorOACIDto, 
			final BindingResult bindingResult, final Model model) {	
		
		final String vista;
		if (bindingResult.hasErrors()) {
			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_SECTOR_OACI);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_SECTORES_OACI);
		
			//Se debe recuperar de nuevo la lista de regiones operativas disponibles 
			//y poner a null el id de la región operativa
			sectorOACIDto.setRegionesOperativasDisponibles(this.modelMapperUtils.mapAll2List(this.regionOperativaService.readAll(),RegionOperativaDto.class));
			sectorOACIDto.getRegionOperativa().setId(null);
			model.addAttribute(ATTRIBUTE_SECTOR_OACI, sectorOACIDto);
			
			vista = VIEW_SECTOR_OACI;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());	
	
		} else {		
			this.sectorOACIService.saveUpdate(sectorOACIDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_SECTORES_OACI);
			log.info(LoggerConstants.LOG_SAVE, sectorOACIDto.getId());
		}
		
		return vista;
	}
	
	/**
	 * Obtenemos el sector OACI con el id facilitado
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READ_SECTOR_OACI + "/{id}")
	public String read(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_SECTOR_OACI, this.sectorOACIService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_ELIMINAR, MAP_READALL_SECTORES_OACI);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_SECTORES_OACI);
					
		log.info(LoggerConstants.LOG_READ);
		
		return VIEW_SECTOR_OACI;
		
	}
	
	/**
	 * Preparamos la vista para la actulización del sector OACI pasado como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_UPDATE_SECTOR_OACI + "/{id}")
	public String updateGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_SECTOR_OACI, this.sectorOACIService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_SECTOR_OACI);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_SECTORES_OACI);
					
		log.info(LoggerConstants.LOG_UPDATE);
		
		return VIEW_SECTOR_OACI;
	}
	
	/**
	 * Actualizamos el sector OACI pasado como parámetro
	 * @param sectorMantenimientoDto Sector OACI a actualizar
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_UPDATE_SECTOR_OACI)
	public String updatePOST(@Valid @ModelAttribute(ATTRIBUTE_SECTOR_OACI) final SectorOACIDto sectorOACIDto, 
			final BindingResult bindingResult, final Model model) {		
		
		final String vista;
		if (bindingResult.hasErrors()) {			

			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
	
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_SECTOR_OACI);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_SECTORES_OACI);
		
			//Se debe recuperar de nuevo la lista de regiones operativas disponibles 
			//y poner a null el id de la región operativa
			sectorOACIDto.setRegionesOperativasDisponibles(this.modelMapperUtils.mapAll2List(this.regionOperativaService.readAll(),RegionOperativaDto.class));
			sectorOACIDto.getRegionOperativa().setId(null);
			model.addAttribute(ATTRIBUTE_SECTOR_OACI, sectorOACIDto);
			
			vista = VIEW_SECTOR_OACI;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());		
		
		} else {
			this.sectorOACIService.saveUpdate(sectorOACIDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_SECTORES_OACI);
			log.info(LoggerConstants.LOG_UPDATE, sectorOACIDto.getId());			
		}

		return vista;		
	}
	
	/**
	 * Preparamos la vista para la eliminación del sector OACI pasado como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_DELETE_SECTOR_OACI + "/{id}")
	public String deleteGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_SECTOR_OACI, this.sectorOACIService.read(id));
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_PREGUNTA, 
				MessagesConstants.POPUP_ELIMINAR_SECTOR_OACI_PREGUNTA);
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.TRUE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_DELETE_SECTOR_OACI
				.concat(ControllerConstants.MAP_ACTION_SLASH).concat(String.valueOf(id)));
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_SECTORES_OACI);
					
		log.info(LoggerConstants.LOG_DELETE);
		
		return VIEW_SECTOR_OACI;		
	}
	
	/**
	 * Eliminación del sector OACI pasado como parámetro
	 * @param id Identificador
	 * @return Vista
	 */
	@PostMapping(MAP_DELETE_SECTOR_OACI + "/{id}")
	public String deletePOST(@PathVariable("id") final Long id) {		
		this.sectorOACIService.delete(id);					
		log.info(LoggerConstants.LOG_DELETE);		
		return ControllerConstants.REDIRECT.concat(MAP_READALL_SECTORES_OACI);		
	}
}
