package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoEmplazamientoDto;

/**
 * Lógica para tipos de emplazamiento
 * 
 * @author jose.vallve
 */
public interface TipoEmplazamientoService {

	/**
	 * Creamos un tipo de emplazamiento sin persistencia
	 * @return Nuevo tipo de emplazamiento
	 */
	TipoEmplazamientoDto create();

	/**
	 * Borrado del tipo de emplazamiento con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de emplazamiento
	 * @return Listado
	 */
	List<TipoEmplazamientoDto> readAll();
	
	/**
	 * Obtenemos el tipo de emplazamiento con el id facilitado
	 * @param id Identificador
	 * @return Tipo de emplazamiento
	 */
	TipoEmplazamientoDto read(final Short id);
	
	/**
	 * Persistimos el tipo de emplazamiento pasado como parámetro
	 * @param tipoEmplazamientoDto Tipo de emplazamiento a persistir
	 * @return Tipo de emplazamiento persistido
	 */
	TipoEmplazamientoDto saveUpdate(final TipoEmplazamientoDto tipoEmplazamientoDto);

}
