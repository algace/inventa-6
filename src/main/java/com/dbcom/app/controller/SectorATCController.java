package com.dbcom.app.controller;

import java.util.List;

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

import com.dbcom.app.constants.ApplicationConstants;
import com.dbcom.app.constants.ControllerConstants;
import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.constants.MessagesConstants;
import com.dbcom.app.model.dto.AirblockDto;
import com.dbcom.app.model.dto.SectorATCDto;
import com.dbcom.app.service.AirblockService;
import com.dbcom.app.service.RegionOperativaService;
import com.dbcom.app.service.SectorATCService;
import com.dbcom.app.service.TipoFuenteInformacionService;
import com.dbcom.app.service.TipoSectorATCService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SectorATCController {
	// Atributos de la vista
	private static final String ATTRIBUTE_TIPO = "sectorATC";
	private static final String ATTRIBUTE_REGIONES_OPERATIVAS = "listaRegionesOperativas";
	private static final String ATTRIBUTE_TIPOS_SECTOR_ATC = "listaTiposSectoresATC";
	private static final String ATTRIBUTE_TIPOS_FUENTE_INFORMACION = "listaTiposFuenteInformacion";
	private static final String ATTRIBUTE_AIRBLOCKS_DISPONIBLES = "listaAirblocksDisponibles";

	// Vistas	
	private static final String VIEW_TIPO = ControllerConstants.MAP_PATH_MENU_SECTORESESPACIOAEREO + ATTRIBUTE_TIPO;
	private static final String VIEW_TIPOS = ControllerConstants.MAP_PATH_MENU_SECTORESESPACIOAEREO + "sectoresATC";		

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
	
	public static final String MAP_INSERT_AIRBLOCK = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPO + 
			ControllerConstants.MAP_ACTION_INSERTAR_AIRBLOCK + ControllerConstants.MAP_ACTION_SLASH;
	public static final String MAP_DELETE_AIRBLOCK = ControllerConstants.MAP_ACTION_SLASH + VIEW_TIPO + 
			ControllerConstants.MAP_ACTION_DELETE_AIRBLOCK + ControllerConstants.MAP_ACTION_SLASH;

	private final SectorATCService sectorATCService;
	private final TipoFuenteInformacionService tipoFuenteInformacionService;
	private final TipoSectorATCService tipoSectorATCService;
	private final RegionOperativaService regionOperativaService;
	private final AirblockService airblockService;
	private final HttpServletRequest request;
	
	
	@Autowired
	public SectorATCController(SectorATCService sectorATCService,
			TipoFuenteInformacionService tipoFuenteInformacionService,
			TipoSectorATCService tipoSectorATCService,
			RegionOperativaService regionOperativaService,
			AirblockService airblockService,
			HttpServletRequest request) {
		this.sectorATCService = sectorATCService;
		this.tipoFuenteInformacionService = tipoFuenteInformacionService;
		this.tipoSectorATCService = tipoSectorATCService;
		this.regionOperativaService = regionOperativaService;
		this.airblockService = airblockService;
		this.request = request;
	}
	
	/**
	 * Obtenemos un listado de sectores ATC
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READALL_TIPOS)
	public String readAll(final Model model) {
		
		// Contenido
		model.addAttribute(ControllerConstants.ATTRIBUTE_LISTA, this.sectorATCService.readAll());		

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
	 * Creamos un sector ATC sin persistencia
	 * @param model modelo
	 * @return Vista
	 */
	@GetMapping(MAP_CREATE_TIPO)
	public String create(final Model model) {

		// Creamos el registro
		model.addAttribute(ATTRIBUTE_TIPO, this.sectorATCService.create());
		
		// Se obtiene la listas de tipos de sectores ATC, tipos de fuentes de información, regiones operativas
		// y airblocks disponibles y se añaden al modelo
		obtenerListasTiposObjetos(model, null);
					
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_TIPO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS);
					
		log.info(LoggerConstants.LOG_CREATE);
		
		return VIEW_TIPO;		
	}
	
	/**
	 * Persistimos el sector ATC pasado como parámetro
	 * @param sectorATCDto Sector ATC a persistir
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_SAVE_TIPO)
	public String save(@Valid @ModelAttribute(ATTRIBUTE_TIPO) final SectorATCDto sectorATCDto, 
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
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_TIPO);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS);
		
			// Se obtiene la listas de tipos de sectores ATC, tipos de fuentes de información, regiones operativas
			// y airblocks disponibles y se añaden al modelo
			obtenerListasTiposObjetos(model, null);
			
			//se recupera la lista de airblocks tratando correctamente los que ya hayan sido seleccionados
			List<AirblockDto> allAirblocks = airblockService.readAll();
			sectorATCDto.setAirblocks(sectorATCService.listAirblocksSeleccionados(allAirblocks, sectorATCDto.getAirblocks()));
			
			model.addAttribute(ATTRIBUTE_TIPO, sectorATCDto);
			
			vista = VIEW_TIPO;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());	
		
		} else {		
			this.sectorATCService.saveUpdate(sectorATCDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_TIPOS);
			log.info(LoggerConstants.LOG_SAVE, sectorATCDto.getId());
		}
		
		return vista;
	}
	
	/**
	 * Obtenemos el sector ATC con el id facilitado
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READ_TIPO + "/{id}")
	public String read(@PathVariable("id") final Short id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_TIPO, this.sectorATCService.read(id));
		
		// Se obtiene la listas de tipos de sectores ATC, tipos de fuentes de información, regiones operativas
		// y airblocks disponibles y se añaden al modelo
		obtenerListasTiposObjetos(model, null);
				
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.TRUE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_ELIMINAR, MAP_READALL_TIPOS);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS);
					
		log.info(LoggerConstants.LOG_READ);
		
		return VIEW_TIPO;
		
	}
	
	/**
	 * Preparamos la vista para la actualización del sector ATC pasado como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_UPDATE_TIPO + "/{id}")
	public String updateGET(@PathVariable("id") final Short id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_TIPO, this.sectorATCService.read(id));
		
		// Se obtiene la listas de tipos de sectores ATC, tipos de fuentes de información, regiones operativas
		// y airblocks disponibles y se añaden al modelo
		obtenerListasTiposObjetos(model, id);
				
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.TRUE);
		model.addAttribute(ControllerConstants.URL_INSERT_AIRBLOCKS_SECTORES_ATC, this.request.getContextPath() + MAP_INSERT_AIRBLOCK);
		model.addAttribute(ControllerConstants.URL_DELETE_AIRBLOCKS_SECTORES_ATC, this.request.getContextPath() + MAP_DELETE_AIRBLOCK);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_TIPO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS);
					
		log.info(LoggerConstants.LOG_UPDATE);
		
		return VIEW_TIPO;
		
	}
	
	/**
	 * Actualizamos el sector ATC pasado como parámetro
	 * @param sectorATCDto Sector ATC a actualizar
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_UPDATE_TIPO)
	public String updatePOST(@Valid @ModelAttribute(ATTRIBUTE_TIPO) final SectorATCDto sectorATCDto, 
			final BindingResult bindingResult, final Model model) {		
		
		final String vista;
		if (bindingResult.hasErrors()) {			

			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
	
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_TIPO);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS);
			
			model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.TRUE);
			model.addAttribute(ControllerConstants.URL_INSERT_AIRBLOCKS_SECTORES_ATC, this.request.getContextPath() + MAP_INSERT_AIRBLOCK);
			model.addAttribute(ControllerConstants.URL_DELETE_AIRBLOCKS_SECTORES_ATC, this.request.getContextPath() + MAP_DELETE_AIRBLOCK);
		
			// Se obtiene la listas de tipos de sectores ATC, tipos de fuentes de información, regiones operativas
			// y airblocks disponibles y se añaden al modelo
			obtenerListasTiposObjetos(model, sectorATCDto.getId());
			
			//se recupera la lista de airblocks tratando correctamente los que ya hayan sido seleccionados
			List<AirblockDto> allAirblocks = airblockService.readAll();
			sectorATCDto.setAirblocks(sectorATCService.listAirblocksSeleccionados(allAirblocks, sectorATCDto.getAirblocks()));
		
			model.addAttribute(ATTRIBUTE_TIPO, sectorATCDto);
			
			vista = VIEW_TIPO;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());		
		
		} else {
			this.sectorATCService.saveUpdate(sectorATCDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_TIPOS);
			log.info(LoggerConstants.LOG_UPDATE, sectorATCDto.getId());			
		}

		return vista;		
	}
	
	/**
	 * Preparamos la vista para la eliminación del sector ATC pasado como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_DELETE_TIPO + "/{id}")
	public String deleteGET(@PathVariable("id") final Short id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_TIPO, this.sectorATCService.read(id));
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_PREGUNTA, 
				MessagesConstants.POPUP_ELIMINAR_SECTORATC_PREGUNTA);
		
		// Se obtiene la listas de tipos de sectores ATC, tipos de fuentes de información, regiones operativas
		// y airblocks disponibles y se añaden al modelo
		obtenerListasTiposObjetos(model, id);
				
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.TRUE);
		model.addAttribute(ControllerConstants.URL_INSERT_AIRBLOCKS_SECTORES_ATC, this.request.getContextPath() + MAP_INSERT_AIRBLOCK);
		model.addAttribute(ControllerConstants.URL_DELETE_AIRBLOCKS_SECTORES_ATC, this.request.getContextPath() + MAP_DELETE_AIRBLOCK);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_DELETE_TIPO
				.concat(ControllerConstants.MAP_ACTION_SLASH).concat(String.valueOf(id)));
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_TIPOS);
					
		log.info(LoggerConstants.LOG_DELETE);
		
		return VIEW_TIPO;		
	}
	
	/**
	 * Eliminación del sector ATC pasado como parámetro
	 * @param id Identificador
	 * @return Vista
	 */
	@PostMapping(MAP_DELETE_TIPO + "/{id}")
	public String deletePOST(@PathVariable("id") final Short id) {		
		this.sectorATCService.delete(id);					
		log.info(LoggerConstants.LOG_DELETE);		
		return ControllerConstants.REDIRECT.concat(MAP_READALL_TIPOS);		
	}
	
	@ResponseBody
	@PostMapping(value = MAP_INSERT_AIRBLOCK + "/{idSectorATC}/{idAirblock}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<Long> insertAirblock(@PathVariable("idSectorATC") final Short idSectorATC, @PathVariable("idAirblock") final Long idAirblock) {
		
		return ResponseEntity.ok(this.sectorATCService.insertAirblock(idSectorATC, idAirblock));					
	}
	
	@ResponseBody
	@DeleteMapping(value = MAP_DELETE_AIRBLOCK + "/{idSectorATC}/{idAirblock}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<Long> deleteAirblock(@PathVariable("idSectorATC") final Short idSectorATC, @PathVariable("idAirblock") final Long idAirblock) {
		
		return ResponseEntity.ok(this.sectorATCService.deleteAirblock(idSectorATC, idAirblock));					
	}
	
	/**
	 * Obtiene la listas de tipos de sectores ATC, tipos de fuentes de información, regiones operativasç
	 * y airblocks disponibles y se añaden al modelo
	 * @param model Modelo
	 */
	private void obtenerListasTiposObjetos(final Model model, final Short id) {
		
		//Obtenemos la lista con las regiones operativas
		model.addAttribute(ATTRIBUTE_REGIONES_OPERATIVAS, this.regionOperativaService.getRegionesOperativasConValorPorDefecto());
				
		//Obtenemos los tipos de sectores ATC
		model.addAttribute(ATTRIBUTE_TIPOS_SECTOR_ATC, this.tipoSectorATCService.readAll());
		
		//Obtenemos los tipos de tpos de información
		model.addAttribute(ATTRIBUTE_TIPOS_FUENTE_INFORMACION, this.tipoFuenteInformacionService.getTiposFuenteInformacionConValorPorDefecto(ApplicationConstants.FUENTE_INFORMACION_POR_DEFECTO_SECTOR_ATC));
		
		//Obtenermos la lista de airblocks disponibles
		if (id == null) {
			model.addAttribute(ATTRIBUTE_AIRBLOCKS_DISPONIBLES, this.airblockService.readAll());
		} else {
			model.addAttribute(ATTRIBUTE_AIRBLOCKS_DISPONIBLES, this.airblockService.readNotContains(id));
		}
		
	}
}
