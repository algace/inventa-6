package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.UsuarioDto;


/**
 * Lógica para usuarios
 * 
 * @author jose.vallve
 */
public interface UsuarioService extends GenericService<UsuarioDto, Long> {

	/**
	 * Creamos un usuario sin persistencia
	 * @return Nuevo usuario
	 */
	UsuarioDto create();

	/**
	 * Borrado del usuario con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);

	/**
	 * Obtenemos un listado de los usuarios
	 * @return Listado
	 */
	List<UsuarioDto> readAll();
	
	/**
	 * Obtenemos el usuario con el id facilitado
	 * @param id Identificador
	 * @return Usuario
	 */
	UsuarioDto read(final Long id);
	
	/**
	 * Persistimos el usuario pasado como parámetro
	 * @param usuarioDto Usuario a persistir
	 * @return Usuario persistido
	 */
	UsuarioDto save(final UsuarioDto usuarioDto);
		
	/**
	 * Actualizamos el usuario pasado como parámetro
	 * @param usuarioDto Usuario a actualizar
	 * @return Usuario actualizado
	 */
	UsuarioDto update(final UsuarioDto usuarioDto);
	
}
