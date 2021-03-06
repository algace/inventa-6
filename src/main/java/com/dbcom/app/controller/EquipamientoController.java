package com.dbcom.app.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbcom.app.constants.ControllerConstants;
import com.dbcom.app.constants.ExceptionConstants;
import com.dbcom.app.constants.LoggerConstants;
import com.dbcom.app.constants.MessagesConstants;
import com.dbcom.app.model.dao.AplicacionSWRepository;
import com.dbcom.app.model.dto.DocumentoDto;
import com.dbcom.app.model.dto.EquipamientoDto;
import com.dbcom.app.model.dto.FotografiaDto;
import com.dbcom.app.service.EquipamientoService;
import com.dbcom.app.service.TipoDocumentoService;
import com.dbcom.app.service.TipoSistemaService;
import com.dbcom.app.service.TipoSubsistemaService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jose.vallve 
 * Enlace entre la vista y la lógica de negocio
 */
@Slf4j
@Controller
public final class EquipamientoController {

	// Atributos de la vista
	private static final String ATTRIBUTE_EQUIPAMIENTO = "equipamiento";
	private static final String ATTRIBUTE_TIPOS_SISTEMA = "listaTiposSistemaDisponibles";
	private static final String ATTRIBUTE_TIPOS_SUBSISTEMA = "listaTiposSubsistemaDisponibles";
	private static final String ATTRIBUTE_TIPOS_DOCUMENTO = "listaTiposDocumento";
	

	// Vistas	
	private static final String VIEW_EQUIPAMIENTO = ControllerConstants.MAP_PATH_MENU + ATTRIBUTE_EQUIPAMIENTO;
	private static final String VIEW_EQUIPAMIENTOS = ControllerConstants.MAP_PATH_MENU + "equipamientos";

	// Mapeo de las acciones
	public static final String MAP_CREATE_EQUIPAMIENTO =  ControllerConstants.MAP_ACTION_SLASH + VIEW_EQUIPAMIENTO 
			+ ControllerConstants.MAP_ACTION_CREAR;
	public static final String MAP_DELETE_EQUIPAMIENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_EQUIPAMIENTO 
			+ ControllerConstants.MAP_ACTION_BORRAR;
	public static final String MAP_SAVE_EQUIPAMIENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_EQUIPAMIENTO 
			+ ControllerConstants.MAP_ACTION_GUARDAR;
	public static final String MAP_UPDATE_EQUIPAMIENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_EQUIPAMIENTO 
			+ ControllerConstants.MAP_ACTION_MODIFICAR;
	public static final String MAP_READ_EQUIPAMIENTO =  ControllerConstants.MAP_ACTION_SLASH + VIEW_EQUIPAMIENTO;
	public static final String MAP_READALL_EQUIPAMIENTOS = ControllerConstants.MAP_ACTION_SLASH + VIEW_EQUIPAMIENTOS;
	
	public static final String MAP_DOWNLOAD_DOCUMENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_EQUIPAMIENTO + 
			ControllerConstants.MAP_ACTION_DOWNLOAD_DOCUMENTO + ControllerConstants.MAP_ACTION_SLASH;
	public static final String MAP_INSERT_DOCUMENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_EQUIPAMIENTO + 
			ControllerConstants.MAP_ACTION_INSERTAR_DOCUMENTO + ControllerConstants.MAP_ACTION_SLASH;
	public static final String MAP_DELETE_DOCUMENTO = ControllerConstants.MAP_ACTION_SLASH + VIEW_EQUIPAMIENTO + 
			ControllerConstants.MAP_ACTION_DELETE_DOCUMENTO + ControllerConstants.MAP_ACTION_SLASH;
	public static final String MAP_DOWNLOAD_FOTOGRAFIA = ControllerConstants.MAP_ACTION_SLASH + VIEW_EQUIPAMIENTO + 
			ControllerConstants.MAP_ACTION_DOWNLOAD_FOTOGRAFIA + ControllerConstants.MAP_ACTION_SLASH;
	public static final String MAP_INSERT_FOTOGRAFIA = ControllerConstants.MAP_ACTION_SLASH + VIEW_EQUIPAMIENTO + 
			ControllerConstants.MAP_ACTION_INSERTAR_FOTOGRAFIA + ControllerConstants.MAP_ACTION_SLASH;
	public static final String MAP_DELETE_FOTOGRAFIA = ControllerConstants.MAP_ACTION_SLASH + VIEW_EQUIPAMIENTO + 
			ControllerConstants.MAP_ACTION_DELETE_FOTOGRAFIA + ControllerConstants.MAP_ACTION_SLASH;

	private final EquipamientoService equipamientoService;
	private final AplicacionSWRepository aplicacionSWRepository;
	private final TipoSistemaService tipoSistemaService;
	private final TipoSubsistemaService tipoSubsistemaService;
	private final TipoDocumentoService tipoDocumentoService;
	private final HttpServletRequest request;
	
	@Autowired
	public EquipamientoController(EquipamientoService equipamientoService,
			AplicacionSWRepository aplicacionSWRepository,
			TipoSistemaService tipoSistemaService,
			TipoSubsistemaService tipoSubsistemaService,
			TipoDocumentoService tipoDocumentoService,
			HttpServletRequest request) {
		this.equipamientoService = equipamientoService;
		this.aplicacionSWRepository = aplicacionSWRepository;
		this.tipoSistemaService = tipoSistemaService;
		this.tipoSubsistemaService = tipoSubsistemaService;
		this.tipoDocumentoService = tipoDocumentoService;
		this.request = request;
	}
	
	/**
	 * Obtenemos un listado de los equipamientos
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READALL_EQUIPAMIENTOS)
	public String readAll(final Model model) {
		
		// Contenido
		model.addAttribute(ControllerConstants.ATTRIBUTE_LISTA, this.equipamientoService.readAll());		

		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_LEER, MAP_READ_EQUIPAMIENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_AGNADIR, MAP_CREATE_EQUIPAMIENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_MODIFICAR, MAP_UPDATE_EQUIPAMIENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_BORRAR, MAP_DELETE_EQUIPAMIENTO);
					
		log.info(LoggerConstants.LOG_READALL);
		
		return VIEW_EQUIPAMIENTOS;		
	}
	
	/**
	 * Creamos un equipamiento sin persistencia
	 * @param model modelo
	 * @return Vista
	 */
	@GetMapping(value = MAP_CREATE_EQUIPAMIENTO)
	public String create(final Model model) {

		// Creamos el registro
		model.addAttribute(ATTRIBUTE_EQUIPAMIENTO, this.equipamientoService.create());
		
		//Obtenemos la lista de tipos de sistemas, subsistemas y documentos y las añadimos al modelo
		obtenerListasTiposObjetos(model);
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_EQUIPAMIENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_EQUIPAMIENTOS);
					
		log.info(LoggerConstants.LOG_CREATE);
		
		return VIEW_EQUIPAMIENTO;		
	}
	
	/**
	 * Persistimos el equipamiento pasado como parámetro
	 * @param equipamientoDto Equipamiento a persistir
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 * @throws IOException 
	 */
	@PostMapping(MAP_SAVE_EQUIPAMIENTO)
	public String save(@Valid @ModelAttribute(ATTRIBUTE_EQUIPAMIENTO) final EquipamientoDto equipamientoDto, 
			final BindingResult bindingResult, final Model model) throws IOException {	
		
		final String vista;
		
		if (bindingResult.hasErrors()) {
			
			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.FALSE);
			
			obtenerListasTiposObjetos(model);
			
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_EQUIPAMIENTO);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_EQUIPAMIENTOS);
			
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_EQUIPAMIENTOS);
			
			vista = VIEW_EQUIPAMIENTO;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());	
		
		} else {		
			this.equipamientoService.save(equipamientoDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_EQUIPAMIENTOS);
			log.info(LoggerConstants.LOG_SAVE, equipamientoDto.getId());
		}
		
		return vista;
	}
	
	/**
	 * Obtenemos la equipamiento con el id facilitado
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READ_EQUIPAMIENTO + "/{id}")
	public String read(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_EQUIPAMIENTO, this.equipamientoService.read(id));
		model.addAttribute(ControllerConstants.FICHERO_TAMAGNO_MAX,ControllerConstants.FICHERO_TAMAGNO_MAX_NUM);
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.TRUE);
		
		model.addAttribute(ControllerConstants.URL_DOWNLOAD_DOCUMENTOS, this.request.getContextPath() + MAP_DOWNLOAD_DOCUMENTO);
		model.addAttribute(ControllerConstants.URL_DOWNLOAD_FOTOGRAFIAS, this.request.getContextPath() + MAP_DOWNLOAD_FOTOGRAFIA);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_ELIMINAR, MAP_READALL_EQUIPAMIENTOS);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_EQUIPAMIENTOS);
					
		log.info(LoggerConstants.LOG_READ);
		
		return VIEW_EQUIPAMIENTO;
		
	}
	
	/**
	 * Preparamos la vista para la actulización la equipamiento pasada como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_UPDATE_EQUIPAMIENTO + "/{id}")
	public String updateGET(@PathVariable("id") final Long id, final Model model) {
		
		// Subida/descarga de Ficheros
		model.addAttribute(ControllerConstants.ATTRIBUTE_UPLOAD_DOCUMENTO, DocumentoController.MAP_UPLOAD_DOCUMENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_UPLOAD_FOTOGRAFIA, FotografiaController.MAP_UPLOAD_FOTOGRAFIA);
		
		model.addAttribute(ControllerConstants.ATTRIBUTE_DOWNLOAD, DocumentoController.MAP_DOWNLOAD_DOCUMENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_FICHERO_TAMAGNO_MAX, MessagesConstants.FILE_MAX_SIZE);
	
		// Contenido
		model.addAttribute(ATTRIBUTE_EQUIPAMIENTO, this.equipamientoService.read(id));
		
		//Obtenemos las listas de tipos de sistemas, subsistemas y documentos y las añadimos al modelo
		obtenerListasTiposObjetos(model);
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.TRUE);
		model.addAttribute(ControllerConstants.URL_DOWNLOAD_DOCUMENTOS, this.request.getContextPath() + MAP_DOWNLOAD_DOCUMENTO);
		model.addAttribute(ControllerConstants.URL_INSERT_DOCUMENTOS, this.request.getContextPath() + MAP_INSERT_DOCUMENTO);
		model.addAttribute(ControllerConstants.URL_DELETE_DOCUMENTOS, this.request.getContextPath() + MAP_DELETE_DOCUMENTO);
		model.addAttribute(ControllerConstants.URL_DOWNLOAD_FOTOGRAFIAS, this.request.getContextPath() + MAP_DOWNLOAD_FOTOGRAFIA);
		model.addAttribute(ControllerConstants.URL_INSERT_FOTOGRAFIAS, this.request.getContextPath() + MAP_INSERT_FOTOGRAFIA);
		model.addAttribute(ControllerConstants.URL_DELETE_FOTOGRAFIAS, this.request.getContextPath() + MAP_DELETE_FOTOGRAFIA);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_EQUIPAMIENTO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_EQUIPAMIENTOS);
					
		log.info(LoggerConstants.LOG_UPDATE);
		
		return VIEW_EQUIPAMIENTO;
		
	}
	
	/**
	 * Actualizamos la equipamiento pasada como parámetro
	 * @param equipamientoDto Equipamiento a actualizar
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_UPDATE_EQUIPAMIENTO)
	public String updatePOST(@Valid @ModelAttribute(ATTRIBUTE_EQUIPAMIENTO) final EquipamientoDto equipamientoDto, 
			final BindingResult bindingResult, final Model model) {		
		
		final String vista;
		if (bindingResult.hasErrors()) {	
			
			// Subida/descarga de Ficheros
			model.addAttribute(ControllerConstants.ATTRIBUTE_UPLOAD_DOCUMENTO, DocumentoController.MAP_UPLOAD_DOCUMENTO);
			model.addAttribute(ControllerConstants.ATTRIBUTE_UPLOAD_FOTOGRAFIA, FotografiaController.MAP_UPLOAD_FOTOGRAFIA);
			model.addAttribute(ControllerConstants.ATTRIBUTE_DOWNLOAD, DocumentoController.MAP_DOWNLOAD_DOCUMENTO);
			model.addAttribute(ControllerConstants.ATTRIBUTE_FICHERO_TAMAGNO_MAX, MessagesConstants.FILE_MAX_SIZE);
			
			obtenerListasTiposObjetos(model);

			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			
			model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.TRUE);
			model.addAttribute(ControllerConstants.URL_DOWNLOAD_DOCUMENTOS, this.request.getContextPath() + MAP_DOWNLOAD_DOCUMENTO);
			model.addAttribute(ControllerConstants.URL_INSERT_DOCUMENTOS, this.request.getContextPath() + MAP_INSERT_DOCUMENTO);
			model.addAttribute(ControllerConstants.URL_DELETE_DOCUMENTOS, this.request.getContextPath() + MAP_DELETE_DOCUMENTO);
			model.addAttribute(ControllerConstants.URL_DOWNLOAD_FOTOGRAFIAS, this.request.getContextPath() + MAP_DOWNLOAD_FOTOGRAFIA);
			model.addAttribute(ControllerConstants.URL_INSERT_FOTOGRAFIAS, this.request.getContextPath() + MAP_INSERT_FOTOGRAFIA);
			model.addAttribute(ControllerConstants.URL_DELETE_FOTOGRAFIAS, this.request.getContextPath() + MAP_DELETE_FOTOGRAFIA);
	
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_EQUIPAMIENTO);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_EQUIPAMIENTOS);
			
			this.equipamientoService.setAllAttributesEquipamientoDto(equipamientoDto);
			
			vista = VIEW_EQUIPAMIENTO;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());		
		
		} else {
			this.equipamientoService.update(equipamientoDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_EQUIPAMIENTOS);
			log.info(LoggerConstants.LOG_UPDATE, equipamientoDto.getId());			
		}

		return vista;		
	}
	
	/**
	 * Preparamos la vista para la eliminación la equipamiento pasada como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_DELETE_EQUIPAMIENTO + "/{id}")
	public String deleteGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		EquipamientoDto equipamientoDto = this.equipamientoService.read(id);
		Long equipamientoAsignado = this.aplicacionSWRepository.countAplicacionesWithEquipamiento(equipamientoDto.getId());
		
		model.addAttribute(ATTRIBUTE_EQUIPAMIENTO, equipamientoDto);
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_PREGUNTA, 
				MessagesConstants.POPUP_ELIMINAR_EQUIPAMIENTO_PREGUNTA);
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_NO_PERMITIDO_MENSAJE, 
				MessagesConstants.POPUP_ELIMINAR_EQUIPAMIENTO_NO_PERMITIDO_MENSAJE);
		if (equipamientoAsignado > 0) {
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_NO_PERMITIDO_ACTIVO, Boolean.TRUE);
		} else {
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_NO_PERMITIDO_ACTIVO, Boolean.FALSE);
		}

		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.TRUE);
		
		model.addAttribute(ControllerConstants.URL_DOWNLOAD_DOCUMENTOS, this.request.getContextPath() + MAP_DOWNLOAD_DOCUMENTO);
		model.addAttribute(ControllerConstants.URL_DOWNLOAD_FOTOGRAFIAS, this.request.getContextPath() + MAP_DOWNLOAD_FOTOGRAFIA);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_DELETE_EQUIPAMIENTO
				.concat(ControllerConstants.MAP_ACTION_SLASH).concat(String.valueOf(id)));
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_EQUIPAMIENTOS);
					
		log.info(LoggerConstants.LOG_DELETE);
		
		return VIEW_EQUIPAMIENTO;		
	}
	
	/**
	 * Eliminación de la equipamiento pasada como parámetro
	 * @param id Identificador
	 * @return Vista
	 */
	@PostMapping(MAP_DELETE_EQUIPAMIENTO + "/{id}")
	public String deletePOST(@PathVariable("id") final Long id) {		
		this.equipamientoService.delete(id);					
		log.info(LoggerConstants.LOG_DELETE);		
		return ControllerConstants.REDIRECT.concat(MAP_READALL_EQUIPAMIENTOS);		
	}
	
	@ResponseBody
	@GetMapping(value = MAP_DOWNLOAD_DOCUMENTO + "/{idDocumento}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<DocumentoDto> downloadDocumento(@PathVariable("idDocumento") final Long idDocumento) {
		
		return this.equipamientoService.getDocumento(idDocumento)
					.map(ResponseEntity::ok)
					.orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());					
	}
	
	@ResponseBody
	@PostMapping(value = MAP_INSERT_DOCUMENTO + "/{idEquipamiento}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<Long> insertDocumento(@PathVariable("idEquipamiento") final Long idEquipamiento, 
			@RequestBody final DocumentoDto documentoDto) {
		
		return this.equipamientoService.insertDocumento(idEquipamiento, documentoDto)
					.map(ResponseEntity::ok)
					.orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());					
	}
	
	@ResponseBody
	@DeleteMapping(value = MAP_DELETE_DOCUMENTO + "/{idDocumento}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<Long> deleteDocumento(@PathVariable("idDocumento") final Long idDocumento) {
		
		return ResponseEntity.ok(this.equipamientoService.deleteDocumento(idDocumento));					
	}
	
	@ResponseBody
	@GetMapping(value = MAP_DOWNLOAD_FOTOGRAFIA + "/{idFotografia}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<FotografiaDto> downloadFotografia(@PathVariable("idFotografia") final Long idFotografia) {
		
		return this.equipamientoService.getFotografia(idFotografia)
					.map(ResponseEntity::ok)
					.orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());					
	}
	
	@ResponseBody
	@PostMapping(value = MAP_INSERT_FOTOGRAFIA + "/{idEquipamiento}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<Long> insertFotografia(@PathVariable("idEquipamiento") final Long idEquipamiento, 
			@RequestBody final FotografiaDto fotografiaDto) {
		
		return this.equipamientoService.insertFotografia(idEquipamiento, fotografiaDto)
					.map(ResponseEntity::ok)
					.orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
	}
	
	@ResponseBody
	@DeleteMapping(value = MAP_DELETE_FOTOGRAFIA + "/{idFotografia}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<Long> deleteFotografia(@PathVariable("idFotografia") final Long idFotografia) {
		
		return ResponseEntity.ok(this.equipamientoService.deleteFotografia(idFotografia));					
	}
	
	/**
	 * Obtiene la listas de tipos de sistemas, subsistemas y documentos y se añaden al modelo
	 * @param model Modelo
	 */
	private void obtenerListasTiposObjetos(final Model model) {
		
		//Obtenemos los tipos de sistema disponibles
		model.addAttribute(ATTRIBUTE_TIPOS_SISTEMA, this.tipoSistemaService.readAll());
		
		//Obtenemos los tipos de subsistema disponibles
		model.addAttribute(ATTRIBUTE_TIPOS_SUBSISTEMA, this.tipoSubsistemaService.readAll());
		
		//Obtenemos los tipos de documento disponibles
		model.addAttribute(ATTRIBUTE_TIPOS_DOCUMENTO, this.tipoDocumentoService.readAll());
	
	}
}
