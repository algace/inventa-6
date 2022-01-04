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
import com.dbcom.app.model.dao.SectorMantenimientoRepository;
import com.dbcom.app.model.dto.RegionMantenimientoDto;
import com.dbcom.app.model.entity.RegionMantenimiento;
import com.dbcom.app.service.RegionMantenimientoService;
import com.dbcom.app.utils.ModelMapperUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author neoris
 * Enlace entre la vista y la lógica de negocio
 */
@Slf4j
@Controller
public class RegionMantenimientoController {

	// Atributos de la vista
	private static final String ATTRIBUTE_REGION_MANTENIMIENTO = "regionMantenimiento";
			
	// Vistas	
	private static final String VIEW_REGION_MANTENIMIENTO = ControllerConstants.MAP_PATH_MENU_ORGANIZACION + ATTRIBUTE_REGION_MANTENIMIENTO;
	private static final String VIEW_REGIONES_MANTENIMIENTO = ControllerConstants.MAP_PATH_MENU_ORGANIZACION + "regionesMantenimiento";
			
	// Mapeo de las acciones
	public static final String MAP_CREATE_REGION_MANTENIMIENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_REGION_MANTENIMIENTO 
			+ ControllerConstants.MAP_ACTION_CREAR;
	public static final String MAP_DELETE_REGION_MANTENIMIENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_REGION_MANTENIMIENTO 
			+ ControllerConstants.MAP_ACTION_BORRAR;
	public static final String MAP_SAVE_REGION_MANTENIMIENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_REGION_MANTENIMIENTO 
			+ ControllerConstants.MAP_ACTION_GUARDAR;
	public static final String MAP_UPDATE_REGION_MANTENIMIENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_REGION_MANTENIMIENTO 
			+ ControllerConstants.MAP_ACTION_MODIFICAR;
	public static final String MAP_READ_REGION_MANTENIMIENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_REGION_MANTENIMIENTO;
	public static final String MAP_READALL_REGIONES_MANTENIMIENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_REGIONES_MANTENIMIENTO;
	
	private final RegionMantenimientoService regionMantenimientoService;
	private final SectorMantenimientoRepository sectorMantenimientoRepository;
	private final ModelMapperUtils modelMapperUtils;
	
	@Autowired
	public RegionMantenimientoController(RegionMantenimientoService regionMantenimientoService,
			SectorMantenimientoRepository sectorMantenimientoRepository,
			ModelMapperUtils modelMapperUtils) {
		this.regionMantenimientoService = regionMantenimientoService;
		this.sectorMantenimientoRepository = sectorMantenimientoRepository;
		this.modelMapperUtils = modelMapperUtils;
	}
	
	/**
	 * Obtenemos un listado de las regiones de mantenimiento
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READALL_REGIONES_MANTENIMIENTO)
	public String readAll(final Model model) {
		
		// Contenido
		model.addAttribute(ControllerConstants.ATTRIBUTE_LISTA, this.regionMantenimientoService.readAll());		

		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_LEER, MAP_READ_REGION_MANTENIMIENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_AGNADIR, MAP_CREATE_REGION_MANTENIMIENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_MODIFICAR, MAP_UPDATE_REGION_MANTENIMIENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_BORRAR, MAP_DELETE_REGION_MANTENIMIENTO);
					
		log.info(LoggerConstants.LOG_READALL);
		
		return VIEW_REGIONES_MANTENIMIENTO;		
	}
	
	/**
	 * Creamos una región de mantenimiento sin persistencia
	 * @param model modelo
	 * @return Vista
	 */
	@GetMapping(value = MAP_CREATE_REGION_MANTENIMIENTO)
	public String create(final Model model) {

		// Creamos el registro
		model.addAttribute(ATTRIBUTE_REGION_MANTENIMIENTO, this.regionMantenimientoService.create());
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_REGION_MANTENIMIENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REGIONES_MANTENIMIENTO);
					
		log.info(LoggerConstants.LOG_CREATE);
		
		return VIEW_REGION_MANTENIMIENTO;		
	}
	
	/**
	 * Persistimos la region de mantenimiento pasada como parámetro
	 * @param regionMantenimientoDto Región de mantenimiento a persistir
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_SAVE_REGION_MANTENIMIENTO)
	public String save(@Valid @ModelAttribute(ATTRIBUTE_REGION_MANTENIMIENTO) final RegionMantenimientoDto regionMantenimientoDto, 
			final BindingResult bindingResult, final Model model) {	
		
		final String vista;
		if (bindingResult.hasErrors()) {
			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_REGION_MANTENIMIENTO);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REGIONES_MANTENIMIENTO);
		
			vista = VIEW_REGION_MANTENIMIENTO;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());	
	
		} else {		
			this.regionMantenimientoService.save(regionMantenimientoDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_REGIONES_MANTENIMIENTO);
			log.info(LoggerConstants.LOG_SAVE, regionMantenimientoDto.getId());
		}
		
		return vista;
	}
	
	/**
	 * Obtenemos la región de mantenimiento con el id facilitado
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READ_REGION_MANTENIMIENTO + "/{id}")
	public String read(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_REGION_MANTENIMIENTO, this.regionMantenimientoService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_ELIMINAR, MAP_READALL_REGIONES_MANTENIMIENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REGIONES_MANTENIMIENTO);
					
		log.info(LoggerConstants.LOG_READ);
		
		return VIEW_REGION_MANTENIMIENTO;
		
	}
	
	/**
	 * Preparamos la vista para la actulización de la región de mantenimiento pasada como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_UPDATE_REGION_MANTENIMIENTO + "/{id}")
	public String updateGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_REGION_MANTENIMIENTO, this.regionMantenimientoService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_REGION_MANTENIMIENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REGIONES_MANTENIMIENTO);
					
		log.info(LoggerConstants.LOG_UPDATE);
		
		return VIEW_REGION_MANTENIMIENTO;
	}
	
	/**
	 * Actualizamos la región de mantenimiento pasada como parámetro
	 * @param tipoSubsistemaDto Tipo de subsistema a actualizar
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_UPDATE_REGION_MANTENIMIENTO)
	public String updatePOST(@Valid @ModelAttribute(ATTRIBUTE_REGION_MANTENIMIENTO) final RegionMantenimientoDto regionMantenimientoDto, 
			final BindingResult bindingResult, final Model model) {		
		
		final String vista;
		if (bindingResult.hasErrors()) {			

			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
	
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_REGION_MANTENIMIENTO);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REGIONES_MANTENIMIENTO);
		
			vista = VIEW_REGION_MANTENIMIENTO;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());		
		
		} else {
			this.regionMantenimientoService.update(regionMantenimientoDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_REGIONES_MANTENIMIENTO);
			log.info(LoggerConstants.LOG_UPDATE, regionMantenimientoDto.getId());			
		}

		return vista;		
	}
	
	/**
	 * Preparamos la vista para la eliminación de la región de mantenimiento pasada como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_DELETE_REGION_MANTENIMIENTO + "/{id}")
	public String deleteGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		RegionMantenimientoDto regionMantenimientoDto = this.regionMantenimientoService.read(id);
		Long sectorMantenimientoAsignado = this.sectorMantenimientoRepository.countByRegionMantenimiento(this.modelMapperUtils.map(regionMantenimientoDto, RegionMantenimiento.class));
		
		model.addAttribute(ATTRIBUTE_REGION_MANTENIMIENTO, regionMantenimientoDto);
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_PREGUNTA, 
				MessagesConstants.POPUP_ELIMINAR_REGION_MANTENIMIENTO_PREGUNTA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_NO_PERMITIDO_MENSAJE, 
				MessagesConstants.POPUP_ELIMINAR_REGION_MANTENIMIENTO_NO_PERMITIDO_MENSAJE);
		if (sectorMantenimientoAsignado > 0) {
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
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_DELETE_REGION_MANTENIMIENTO
				.concat(ControllerConstants.MAP_ACTION_SLASH).concat(String.valueOf(id)));
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REGIONES_MANTENIMIENTO);
					
		log.info(LoggerConstants.LOG_DELETE);
		
		return VIEW_REGION_MANTENIMIENTO;		
	}
	
	/**
	 * Eliminación de la la región de mantenimiento pasada como parámetro
	 * @param id Identificador
	 * @return Vista
	 */
	@PostMapping(MAP_DELETE_REGION_MANTENIMIENTO + "/{id}")
	public String deletePOST(@PathVariable("id") final Long id) {		
		this.regionMantenimientoService.delete(id);					
		log.info(LoggerConstants.LOG_DELETE);		
		return ControllerConstants.REDIRECT.concat(MAP_READALL_REGIONES_MANTENIMIENTO);		
	}
}
