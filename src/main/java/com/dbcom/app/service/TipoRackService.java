package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoRackDto;

/**
 * Lógica para tipos de rack
 * 
 * @author jose.vallve
 */
public interface TipoRackService {

	/**
	 * Creamos un tipo de rack sin persistencia
	 * @return Nuevo tipo de rack
	 */
	TipoRackDto create();

	/**
	 * Borrado del tipo de rack con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de rack
	 * @return Listado
	 */
	List<TipoRackDto> readAll();
	
	/**
	 * Obtenemos el tipo de rack con el id facilitado
	 * @param id Identificador
	 * @return Tipo de rack
	 */
	TipoRackDto read(final Short id);
	
	/**
	 * Persistimos el tipo de rack pasado como parámetro
	 * @param tipoRackDto Tipo de rack a persistir
	 * @return Tipo de rack persistido
	 */
	TipoRackDto saveUpdate(final TipoRackDto tipoRackDto);
	
}
