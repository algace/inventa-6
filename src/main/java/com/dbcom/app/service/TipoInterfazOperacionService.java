package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoInterfazOperacionDto;

/**
 * Lógica para tipos de interfaz operación
 * 
 * @author jose.vallve
 */
public interface TipoInterfazOperacionService {

	/**
	 * Creamos un tipo de interfaz operación sin persistencia
	 * @return Nuevo tipo de interfaz operación
	 */
	TipoInterfazOperacionDto create();

	/**
	 * Borrado del tipo de interfaz operación con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de interfaz operación
	 * @return Listado
	 */
	List<TipoInterfazOperacionDto> readAll();
	
	/**
	 * Obtenemos el tipo de interfaz operación con el id facilitado
	 * @param id Identificador
	 * @return Tipo de interfaz operación
	 */
	TipoInterfazOperacionDto read(final Short id);
	
	/**
	 * Persistimos el tipo de interfaz operación pasado como parámetro
	 * @param tipoInterfazOperacionDto Tipo de interfaz operación a persistir
	 * @return Tipo de interfaz operación persistido
	 */
	TipoInterfazOperacionDto saveUpdate(final TipoInterfazOperacionDto tipoInterfazOperacionDto);
	
}
