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
import com.dbcom.app.model.dto.EquipamientoDto;
import com.dbcom.app.model.dto.UsuarioDto;
import com.dbcom.app.service.EquipamientoService;
import com.dbcom.app.service.TipoDocumentoService;
import com.dbcom.app.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eduardo.tubilleja
 * Enlace entre la vista y la lógica de negocio
 */
@Slf4j
@Controller
public final class UsuarioController {

	// Atributos de la vista
	private static final String ATTRIBUTE_USUARIO = "usuario";

	// Vistas	
	private static final String VIEW_USUARIO = ControllerConstants.MAP_PATH_MENU + ATTRIBUTE_USUARIO;
	private static final String VIEW_USUARIOS = ControllerConstants.MAP_PATH_MENU + "usuarios";

	// Mapeo de las acciones
	public static final String MAP_CREATE_USUARIO =  ControllerConstants.MAP_ACTION_SLASH + VIEW_USUARIO 
			+ ControllerConstants.MAP_ACTION_CREAR;
	public static final String MAP_DELETE_USUARIO = ControllerConstants.MAP_ACTION_SLASH + VIEW_USUARIO 
			+ ControllerConstants.MAP_ACTION_BORRAR;
	public static final String MAP_SAVE_USUARIO = ControllerConstants.MAP_ACTION_SLASH + VIEW_USUARIO 
			+ ControllerConstants.MAP_ACTION_GUARDAR;
	public static final String MAP_UPDATE_USUARIO = ControllerConstants.MAP_ACTION_SLASH + VIEW_USUARIO 
			+ ControllerConstants.MAP_ACTION_MODIFICAR;
	public static final String MAP_READ_USUARIO =  ControllerConstants.MAP_ACTION_SLASH + VIEW_USUARIO;
	public static final String MAP_READALL_USUARIOS = ControllerConstants.MAP_ACTION_SLASH + VIEW_USUARIOS;

	private final UsuarioService usuarioService;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	/**
	 * Obtenemos un listado de los usuarios
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READALL_USUARIOS)
	public String readAll(final Model model) {
		
		// Contenido
		model.addAttribute(ControllerConstants.ATTRIBUTE_LISTA, this.usuarioService.readAll());		

		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_LEER, MAP_READ_USUARIO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_AGNADIR, MAP_CREATE_USUARIO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_MODIFICAR, MAP_UPDATE_USUARIO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_BORRAR, MAP_DELETE_USUARIO);
					
		log.info(LoggerConstants.LOG_READALL);
		
		return VIEW_USUARIOS;		
	}
	
	/**
	 * Creamos un usuario sin persistencia
	 * @param model modelo
	 * @return Vista
	 */
	@GetMapping(value = MAP_CREATE_USUARIO)
	public String create(final Model model) {

		// Creamos el registro
		model.addAttribute(ATTRIBUTE_USUARIO, this.usuarioService.create());
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_USUARIO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_USUARIOS);
					
		log.info(LoggerConstants.LOG_CREATE);
		
		return VIEW_USUARIO;		
	}
	
	/**
	 * Persistimos el usuario pasado como parámetro
	 * @param usuarioDto Usuario a persistir
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_SAVE_USUARIO)
	public String save(@Valid @ModelAttribute(ATTRIBUTE_USUARIO) final UsuarioDto usuarioDto, 
			final BindingResult bindingResult, final Model model) {	
		
		final String vista;
		if (bindingResult.hasErrors()) {

			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
			
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_SAVE_USUARIO);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_USUARIOS);
		
			vista = VIEW_USUARIO;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());	
		
		} else {		
			this.usuarioService.saveUpdate(usuarioDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_USUARIOS);
			log.info(LoggerConstants.LOG_SAVE, usuarioDto.getId());
		}
		
		return vista;
	}
	
	/**
	 * Obtenemos la usuario con el id facilitado
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_READ_USUARIO + "/{id}")
	public String read(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_USUARIO, this.usuarioService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
		
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_ELIMINAR, MAP_READALL_USUARIOS);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_USUARIOS);
					
		log.info(LoggerConstants.LOG_READ);
		
		return VIEW_USUARIO;
		
	}
	
	/**
	 * Preparamos la vista para la actulización del usuario pasado como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_UPDATE_USUARIO + "/{id}")
	public String updateGET(@PathVariable("id") final Long id, final Model model) {
	
		// Contenido
		model.addAttribute(ATTRIBUTE_USUARIO, this.usuarioService.read(id));
		
		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_USUARIO);
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_USUARIOS);
					
		log.info(LoggerConstants.LOG_UPDATE);
		
		return VIEW_USUARIO;
		
	}
	
	/**
	 * Actualizamos el usuario pasado como parámetro
	 * @param usuarioDto Usuario a actualizar
	 * @param bindingResult Validaciones
	 * @param model Modelo
	 * @return Vista
	 */
	@PostMapping(MAP_UPDATE_USUARIO)
	public String updatePOST(@Valid @ModelAttribute(ATTRIBUTE_USUARIO) final UsuarioDto usuarioDto, 
			final BindingResult bindingResult, final Model model) {		
		
		final String vista;
		if (bindingResult.hasErrors()) {	

			// Activación de los botones necesarios
			model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.FALSE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.TRUE);
			model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.FALSE);
	
			// Botones
			model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_UPDATE_USUARIO);
			model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_USUARIOS);
			
			vista = VIEW_USUARIO;
			log.error(ExceptionConstants.VALIDATION_EXCEPTION, bindingResult.getFieldError().getDefaultMessage());		
		
		} else {
			this.usuarioService.saveUpdate(usuarioDto);
			vista = ControllerConstants.REDIRECT.concat(MAP_READALL_USUARIOS);
			log.info(LoggerConstants.LOG_UPDATE, usuarioDto.getId());			
		}

		return vista;		
	}
	
	/**
	 * Preparamos la vista para la eliminación del usuario pasado como parámetro
	 * @param id Identificador
	 * @param model Modelo
	 * @return Vista
	 */
	@GetMapping(MAP_DELETE_USUARIO + "/{id}")
	public String deleteGET(@PathVariable("id") final Long id, final Model model) {
		
		// Contenido
		model.addAttribute(ATTRIBUTE_USUARIO, this.usuarioService.read(id));
		model.addAttribute(ControllerConstants.ATTRIBUTE_POPUP_ELIMINAR_PREGUNTA, 
				MessagesConstants.POPUP_ELIMINAR_EQUIPAMIENTO_PREGUNTA);

		// Activación de los botones necesarios
		model.addAttribute(ControllerConstants.ATTRIBUTE_ES_CAMPO_SOLO_LECTURA, Boolean.TRUE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ACEPTAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_CANCELAR_ACTIVO, Boolean.FALSE);
		model.addAttribute(ControllerConstants.ATTRIBUTE_ESTA_BOTON_ELIMINAR_ACTIVO, Boolean.TRUE);
				
		// Botones
		model.addAttribute(ControllerConstants.ATTRIBUTE_ACTION, MAP_DELETE_USUARIO
				.concat(ControllerConstants.MAP_ACTION_SLASH).concat(String.valueOf(id)));
		model.addAttribute(ControllerConstants.ATTRIBUTE_BOTON_VOLVER, MAP_READALL_USUARIOS);
					
		log.info(LoggerConstants.LOG_DELETE);
		
		return VIEW_USUARIO;		
	}
	
	/**
	 * Eliminación del usuario pasado como parámetro
	 * @param id Identificador
	 * @return Vista
	 */
	@PostMapping(MAP_DELETE_USUARIO + "/{id}")
	public String deletePOST(@PathVariable("id") final Long id) {		
		this.usuarioService.delete(id);					
		log.info(LoggerConstants.LOG_DELETE);		
		return ControllerConstants.REDIRECT.concat(MAP_READALL_USUARIOS);		
	}
	
}
