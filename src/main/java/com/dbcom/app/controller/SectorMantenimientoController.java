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
import com.dbcom.app.model.dto.RegionMantenimientoDto;
import com.dbcom.app.model.dto.SectorMantenimientoDto;
import com.dbcom.app.service.RegionMantenimientoService;
import com.dbcom.app.service.SectorMantenimientoService;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author neoris
 * Enlace entre la vista y la lógica de negocio
 */
@Slf4j
@Controller
public class SectorMantenimientoController {

	// Atributos de la vista
	private static final String ATTRIBUTE_SECTOR_MANTENIMIENTO = "sectorMantenimiento";
		
	// Vistas	
	private static final String VIEW_SECTOR_MANTENIMIENTO = ControllerConstants.MAP_PATH_MENU_ORGANIZACION + ATTRIBUTE_SECTOR_MANTENIMIENTO;
	private static final String VIEW_SECTORES_MANTENIMIENTO = ControllerConstants.MAP_PATH_MENU_ORGANIZACION + "sectoresMantenimiento";
		
	// Mapeo de las acciones
	public static final String MAP_CREATE_SECTOR_MANTENIMIENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_SECTOR_MANTENIMIENTO 
			+ ControllerConstants.MAP_ACTION_CREAR;
	public static final String MAP_DELETE_SECTOR_MANTENIMIENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_SECTOR_MANTENIMIENTO 
			+ ControllerConstants.MAP_ACTION_BORRAR;
	public static final String MAP_SAVE_SECTOR_MANTENIMIENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_SECTOR_MANTENIMIENTO 
			+ ControllerConstants.MAP_ACTION_GUARDAR;
	public static final String MAP_UPDATE_SECTOR_MANTENIMIENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_SECTOR_MANTENIMIENTO 
			+ ControllerConstants.MAP_ACTION_MODIFICAR;
	public static final String MAP_READ_SECTOR_MANTENIMIENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_SECTOR_MANTENIMIENTO;
	public static final String MAP_READALL_SECTORES_MANTENIMIENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_SECTORES_MANTENIMIENTO;
		
	private final SectorMantenimientoService sectorMantenimientoService;
	private final RegionMantenimientoService regionMantenimientoService;
	private final ModelMapperUtils  modelMapperUtils;
	
	@Autowired
	public SectorMantenimientoController(SectorMantenimientoService sectorMantenimientoService,
			RegionMantenimientoService regionMantenimientoService,
			ModelMapperUtils  modelMapperUtils) {
		this.sectorMantenimientoService = sectorMantenimientoService;
		this.regionMantenimientoService = regionMantenimientoService;
		this.modelMapperUtils = modelMapperUtils;
	}
	
	/**
	 * Obtenemos un listado de los sectores de mantenimiento
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READALL_SECTORES_MANTENIMIENTO)
	public String readAll(final Model model) {
		
		// Contenido
		model.addAttribute(ControllerConstants.ATTRIBUTE_LISTA, this.sectorMantenimientoService.readAll());		

		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_LEER, MAP_READ_SECTOR_MANTENIMIENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_AGNADIR, MAP_CREATE_SECTOR_MANTENIMIENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_MODIFICAR, MAP_UPDATE_SECTOR_MANTENIMIENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_BORRAR, MAP_DELETE_SECTOR_MANTENIMIENTO);
					
		log.info(LoggerConstants.LOG_READALL);
		
		return VIEW_SECTORES_MANTENIMIENTO;		
	}
	
	/**
	 * Creamos un sector de mantenimiento sin persistencia
	 * @param model modelo
	 * @return Vista
	 */
	@GetMapping(value = MAP_CREATE_SECTOR_MANTENIMIENTO)
	public String create(final Model model) {

		// Creamos el registro
		model.addAttribute(ATTRIBUTE_SECTOR_MANTENIMIENTO, this.sectorMantenimientoService.create());
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_SECTOR_MANTENIMIENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_SECTORES_MANTENIMIENTO);
					
		log.info(LoggerConstants.LOG_CREATE);
		
		return VIEW_SECTOR_MANTENIMIENTO;		
	}
	
	/**
	 * Persistimos el sector de mantenimiento pasado como parámetro
	 * @param sectorMantenimientoDto Sector de mantenimiento a persistir
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_SAVE_SECTOR_MANTENIMIENTO)
	public String save(@Valid @ModelAttribute(ATTRIBUTE_SECTOR_MANTENIMIENTO) final SectorMantenimientoDto sectorMantenimientoDto, 
			final BindingResult bindingResult, final Model model) {	
		
		final String vista;
		if (bindingResult.hasErrors()) {
			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_SECTOR_MANTENIMIENTO);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_SECTORES_MANTENIMIENTO);
		
			//Se debe recuperar de nuevo la lista de regiones de mantenimiento disponibles 
			//y poner a null el id de la región de mantemiento
			sectorMantenimientoDto.setRegionesMantenimientoDisponibles(this.modelMapperUtils.mapAll2List(this.regionMantenimientoService.readAll(),RegionMantenimientoDto.class));
			sectorMantenimientoDto.getRegionMantenimiento().setId(null);
			model.addAttribute(ATTRIBUTE_SECTOR_MANTENIMIENTO, sectorMantenimientoDto);
			
			vista = VIEW_SECTOR_MANTENIMIENTO;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());	
	
		} else {		
			this.sectorMantenimientoService.save(sectorMantenimientoDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_SECTORES_MANTENIMIENTO);
			log.info(LoggerConstants.LOG_SAVE, sectorMantenimientoDto.getId());
		}
		
		return vista;
	}
	
	/**
	 * Obtenemos el sector de mantenimiento con el id facilitado
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READ_SECTOR_MANTENIMIENTO + "/{id}")
	public String read(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_SECTOR_MANTENIMIENTO, this.sectorMantenimientoService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_ELIMINAR, MAP_READALL_SECTORES_MANTENIMIENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_SECTORES_MANTENIMIENTO);
					
		log.info(LoggerConstants.LOG_READ);
		
		return VIEW_SECTOR_MANTENIMIENTO;
		
	}
	
	/**
	 * Preparamos la vista para la actulización del sector de mantenimiento pasado como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_UPDATE_SECTOR_MANTENIMIENTO + "/{id}")
	public String updateGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_SECTOR_MANTENIMIENTO, this.sectorMantenimientoService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_SECTOR_MANTENIMIENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_SECTORES_MANTENIMIENTO);
					
		log.info(LoggerConstants.LOG_UPDATE);
		
		return VIEW_SECTOR_MANTENIMIENTO;
	}
	
	/**
	 * Actualizamos el sector de mantenimiento pasado como parámetro
	 * @param sectorMantenimientoDto Sector de mantenimiento a actualizar
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_UPDATE_SECTOR_MANTENIMIENTO)
	public String updatePOST(@Valid @ModelAttribute(ATTRIBUTE_SECTOR_MANTENIMIENTO) final SectorMantenimientoDto sectorMantenimientoDto, 
			final BindingResult bindingResult, final Model model) {		
		
		final String vista;
		if (bindingResult.hasErrors()) {			

			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
	
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_SECTOR_MANTENIMIENTO);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_SECTORES_MANTENIMIENTO);
		
			//Se debe recuperar de nuevo la lista de regiones de mantenimiento disponibles 
			//y poner a null el id de la región de mantemiento
			sectorMantenimientoDto.setRegionesMantenimientoDisponibles(this.modelMapperUtils.mapAll2List(this.regionMantenimientoService.readAll(),RegionMantenimientoDto.class));
			sectorMantenimientoDto.getRegionMantenimiento().setId(null);
			model.addAttribute(ATTRIBUTE_SECTOR_MANTENIMIENTO, sectorMantenimientoDto);
			
			vista = VIEW_SECTOR_MANTENIMIENTO;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());		
		
		} else {
			this.sectorMantenimientoService.update(sectorMantenimientoDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_SECTORES_MANTENIMIENTO);
			log.info(LoggerConstants.LOG_UPDATE, sectorMantenimientoDto.getId());			
		}

		return vista;		
	}
	
	/**
	 * Preparamos la vista para la eliminación del sector de mantenimiento pasado como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_DELETE_SECTOR_MANTENIMIENTO + "/{id}")
	public String deleteGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_SECTOR_MANTENIMIENTO, this.sectorMantenimientoService.read(id));
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_PREGUNTA, 
				MessagesConstants.POPUP_ELIMINAR_SECTOR_MANTENIMIENTO_PREGUNTA);
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.TRUE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_DELETE_SECTOR_MANTENIMIENTO
				.concat(ControllerConstants.MAP_ACTION_SLASH).concat(String.valueOf(id)));
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_SECTORES_MANTENIMIENTO);
					
		log.info(LoggerConstants.LOG_DELETE);
		
		return VIEW_SECTOR_MANTENIMIENTO;		
	}
	
	/**
	 * Eliminación del sector de mantenimiento pasado como parámetro
	 * @param id Identificador
	 * @return Vista
	 */
	@PostMapping(MAP_DELETE_SECTOR_MANTENIMIENTO + "/{id}")
	public String deletePOST(@PathVariable("id") final Long id) {		
		this.sectorMantenimientoService.delete(id);					
		log.info(LoggerConstants.LOG_DELETE);		
		return ControllerConstants.REDIRECT.concat(MAP_READALL_SECTORES_MANTENIMIENTO);		
	}
	
}
