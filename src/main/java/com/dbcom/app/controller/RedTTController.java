package com.dbcom.app.controller;

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
import com.dbcom.app.model.dto.FotografiaDto;
import com.dbcom.app.model.dto.RedTTDto;
import com.dbcom.app.service.RedTTService;
import com.dbcom.app.service.TipoTopologiaService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author neoris 
 * Enlace entre la vista y la lógica de negocio
 */
@Slf4j
@Controller
public class RedTTController {

	// Atributos de la vista
	private static final String ATTRIBUTE_REDTT = "redTT";
	private static final String ATTRIBUTE_TIPOS_TOPOLOGIA = "listaTiposTopologiaDisponibles";

	// Vistas	
	private static final String VIEW_REDTT= ControllerConstants.MAP_PATH_MENU + ATTRIBUTE_REDTT;
	private static final String VIEW_REDESTT = ControllerConstants.MAP_PATH_MENU + "redesTT";

	// Mapeo de las acciones
	public static final String MAP_CREATE_REDTT = ControllerConstants.MAP_ACTION_SLASH + VIEW_REDTT 
			+ ControllerConstants.MAP_ACTION_CREAR;
	public static final String MAP_DELETE_REDTT = ControllerConstants.MAP_ACTION_SLASH + VIEW_REDTT 
			+ ControllerConstants.MAP_ACTION_BORRAR;
	public static final String MAP_SAVE_REDTT = ControllerConstants.MAP_ACTION_SLASH + VIEW_REDTT 
			+ ControllerConstants.MAP_ACTION_GUARDAR;
	public static final String MAP_UPDATE_REDTT = ControllerConstants.MAP_ACTION_SLASH + VIEW_REDTT 
			+ ControllerConstants.MAP_ACTION_MODIFICAR;
	public static final String MAP_READ_REDTT = ControllerConstants.MAP_ACTION_SLASH + VIEW_REDTT;
	public static final String MAP_READALL_REDESTT = ControllerConstants.MAP_ACTION_SLASH + VIEW_REDESTT;
	
	public static final String MAP_DOWNLOAD_FOTOGRAFIA = ControllerConstants.MAP_ACTION_SLASH + VIEW_REDTT + 
			ControllerConstants.MAP_ACTION_DOWNLOAD_FOTOGRAFIA + ControllerConstants.MAP_ACTION_SLASH;
	public static final String MAP_INSERT_FOTOGRAFIA = ControllerConstants.MAP_ACTION_SLASH + VIEW_REDTT + 
			ControllerConstants.MAP_ACTION_INSERTAR_FOTOGRAFIA + ControllerConstants.MAP_ACTION_SLASH;
	public static final String MAP_DELETE_FOTOGRAFIA = ControllerConstants.MAP_ACTION_SLASH + VIEW_REDTT + 
			ControllerConstants.MAP_ACTION_DELETE_FOTOGRAFIA + ControllerConstants.MAP_ACTION_SLASH;
	
	private final RedTTService redTTService;
	private final TipoTopologiaService tipoTopologiaService;
	private final HttpServletRequest request;
	
	@Autowired
	public RedTTController(RedTTService redTTService,
			TipoTopologiaService tipoTopologiaService,
			HttpServletRequest request) {
		this.redTTService = redTTService;
		this.tipoTopologiaService = tipoTopologiaService;
		this.request = request;
	}
	
	/**
	 * Obtenemos un listado de las redes T/T
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READALL_REDESTT)
	public String readAll(final Model model) {
		
		// Contenido
		model.addAttribute(ControllerConstants.ATTRIBUTE_LISTA, this.redTTService.readAll());		

		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_LEER, MAP_READ_REDTT);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_AGNADIR, MAP_CREATE_REDTT);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_MODIFICAR, MAP_UPDATE_REDTT);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_BORRAR, MAP_DELETE_REDTT);
					
		log.info(LoggerConstants.LOG_READALL);
		
		return VIEW_REDESTT;		
	}
	
	/**
	 * Creamos una red T/T sin persistencia
	 * @param model modelo
	 * @return Vista
	 */
	@GetMapping(value = MAP_CREATE_REDTT)
	public String create(final Model model) {

		// Creamos el registro
		model.addAttribute(ATTRIBUTE_REDTT, this.redTTService.create());
		
		//Obtenemos la lista de tipos de topología y la añadimos al modelo
		obtenerListasTiposObjetos(model);
		
		model.addAttribute(ControllerConstants.FICHERO_TAMAGNO_MAX,ControllerConstants.FICHERO_TAMAGNO_MAX_NUM);
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_REDTT);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REDESTT);
					
		log.info(LoggerConstants.LOG_CREATE);
		
		return VIEW_REDTT;		
	}
	
	/**
	 * Persistimos la red T/T pasada como parámetro
	 * @param redTTDto Red T/T a persistir
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_SAVE_REDTT)
	public String save(@Valid @ModelAttribute(ATTRIBUTE_REDTT) final RedTTDto redTTDto, 
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
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_REDTT);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REDESTT);
			
			//Obtenemos la lista de tipos de topología y la añadimos al modelo
			obtenerListasTiposObjetos(model);
			
			model.addAttribute(ATTRIBUTE_REDTT, redTTDto);
			
			vista = VIEW_REDTT;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());	
		
			
		} else {		
			this.redTTService.save(redTTDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_REDESTT);
			log.info(LoggerConstants.LOG_SAVE, redTTDto.getId());
		}
		
		return vista;
	}
	
	/**
	 * Obtenemos la red T/T con el id facilitado
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READ_REDTT + "/{id}")
	public String read(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_REDTT, this.redTTService.read(id));
		
		//Obtenemos la lista de tipos de topología y la añadimos al modelo
		obtenerListasTiposObjetos(model);
		
		model.addAttribute(ControllerConstants.FICHERO_TAMAGNO_MAX,ControllerConstants.FICHERO_TAMAGNO_MAX_NUM);
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.TRUE);
		
		model.addAttribute(ControllerConstants.URL_DOWNLOAD_FOTOGRAFIAS, this.request.getContextPath() + MAP_DOWNLOAD_FOTOGRAFIA);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_ELIMINAR, MAP_READALL_REDESTT);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REDESTT);
					
		log.info(LoggerConstants.LOG_READ);
		
		return VIEW_REDTT;
		
	}
	
	/**
	 * Preparamos la vista para la actualización la red T/T pasada como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_UPDATE_REDTT + "/{id}")
	public String updateGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_REDTT, this.redTTService.read(id));
		
		model.addAttribute(ControllerConstants.FICHERO_TAMAGNO_MAX,ControllerConstants.FICHERO_TAMAGNO_MAX_NUM);
		
		//Obtenemos la lista de tipos de topología y la añadimos al modelo
		obtenerListasTiposObjetos(model);
				
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.TRUE);
		model.addAttribute(ControllerConstants.URL_DOWNLOAD_FOTOGRAFIAS, this.request.getContextPath() + MAP_DOWNLOAD_FOTOGRAFIA);
		model.addAttribute(ControllerConstants.URL_INSERT_FOTOGRAFIAS, this.request.getContextPath() + MAP_INSERT_FOTOGRAFIA);
		model.addAttribute(ControllerConstants.URL_DELETE_FOTOGRAFIAS, this.request.getContextPath() + MAP_DELETE_FOTOGRAFIA);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_REDTT);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REDESTT);
					
		log.info(LoggerConstants.LOG_UPDATE);
		
		return VIEW_REDTT;
		
	}
	
	/**
	 * Actualizamos la red T/T pasada como parámetro
	 * @param redTTDto Red T/T a actualizar
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_UPDATE_REDTT)
	public String updatePOST(@Valid @ModelAttribute(ATTRIBUTE_REDTT) final RedTTDto redTTDto, 
			final BindingResult bindingResult, final Model model) {		
		
		final String vista;
		if (bindingResult.hasErrors()) {		
			
			model.addAttribute(ControllerConstants.FICHERO_TAMAGNO_MAX,ControllerConstants.FICHERO_TAMAGNO_MAX_NUM);
			
			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			
			model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.TRUE);
			model.addAttribute(ControllerConstants.URL_DOWNLOAD_FOTOGRAFIAS, this.request.getContextPath() + MAP_DOWNLOAD_FOTOGRAFIA);
			model.addAttribute(ControllerConstants.URL_INSERT_FOTOGRAFIAS, this.request.getContextPath() + MAP_INSERT_FOTOGRAFIA);
			model.addAttribute(ControllerConstants.URL_DELETE_FOTOGRAFIAS, this.request.getContextPath() + MAP_DELETE_FOTOGRAFIA);
	
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_REDTT);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REDESTT);
			
			//Obtenemos la lista de tipos de topología y la añadimos al modelo
			obtenerListasTiposObjetos(model);
			
			this.redTTService.setAllAttributesRedTTDto(redTTDto);
			
			vista = VIEW_REDTT;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());		
		} else {
			this.redTTService.update(redTTDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_REDESTT);
			log.info(LoggerConstants.LOG_UPDATE, redTTDto.getId());			
		}

		return vista;		
	}
	
	/**
	 * Preparamos la vista para la eliminación de la red T/T pasada como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_DELETE_REDTT + "/{id}")
	public String deleteGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_REDTT, this.redTTService.read(id));
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_PREGUNTA, 
				MessagesConstants.POPUP_ELIMINAR_APLICACION_PREGUNTA);
		
		//Obtenemos la lista de tipos de topología y la añadimos al modelo
		obtenerListasTiposObjetos(model);
				
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.TRUE);
		
		model.addAttribute(ControllerConstants.ATTRIBUTE_CARDS_VISIBLE, Boolean.TRUE);
		model.addAttribute(ControllerConstants.URL_DOWNLOAD_FOTOGRAFIAS, this.request.getContextPath() + MAP_DOWNLOAD_FOTOGRAFIA);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_DELETE_REDTT
				.concat(ControllerConstants.MAP_ACTION_SLASH).concat(String.valueOf(id)));
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_REDESTT);
					
		log.info(LoggerConstants.LOG_DELETE);
		
		return VIEW_REDTT;		
	}
	
	/**
	 * Eliminación de la red T/T pasada como parámetro
	 * @param id Identificador
	 * @return Vista
	 */
	@PostMapping(MAP_DELETE_REDTT + "/{id}")
	public String deletePOST(@PathVariable("id") final Long id) {		
		this.redTTService.delete(id);					
		log.info(LoggerConstants.LOG_DELETE);		
		return ControllerConstants.REDIRECT.concat(MAP_READALL_REDESTT);		
	}
	
	
	@ResponseBody
	@GetMapping(value = MAP_DOWNLOAD_FOTOGRAFIA + "/{idFotografia}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<FotografiaDto> downloadFotografia(@PathVariable("idFotografia") final Long idFotografia) {
		
		return this.redTTService.getFotografia(idFotografia)
					.map(ResponseEntity::ok)
					.orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());					
	}
	
	@ResponseBody
	@PostMapping(value = MAP_INSERT_FOTOGRAFIA + "/{idRedTT}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<Long> insertFotografia(@PathVariable("idRedTT") final Long idRedTT, 
			@RequestBody final FotografiaDto fotografiaDto) {
		
		return this.redTTService.insertFotografia(idRedTT, fotografiaDto)
					.map(ResponseEntity::ok)
					.orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
	}
	
	@ResponseBody
	@DeleteMapping(value = MAP_DELETE_FOTOGRAFIA + "/{idFotografia}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public ResponseEntity<Long> deleteFotografia(@PathVariable("idFotografia") final Long idFotografia) {
		
		return ResponseEntity.ok(this.redTTService.deleteFotografia(idFotografia));					
	}
	
	/**
	 * Obtiene la listas de tipos de chasis y se añaden al modelo
	 * @param model Modelo
	 */
	private void obtenerListasTiposObjetos(final Model model) {
		
		//Obtenemos los tipos de chasis
		model.addAttribute(ATTRIBUTE_TIPOS_TOPOLOGIA, this.tipoTopologiaService.getTiposTopologiasConValorPorDefecto());
	}
}
