package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoEstructuraDto;

/**
 * Lógica para tipos de estructura
 * 
 * @author jose.vallve
 */
public interface TipoEstructuraService {

	/**
	 * Creamos un tipo de estructura sin persistencia
	 * @return Nuevo tipo de estructura
	 */
	TipoEstructuraDto create();

	/**
	 * Borrado del tipo de estructura con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de estructura
	 * @return Listado
	 */
	List<TipoEstructuraDto> readAll();
	
	/**
	 * Obtenemos el tipo de estructura con el id facilitado
	 * @param id Identificador
	 * @return Tipo de estructura
	 */
	TipoEstructuraDto read(final Short id);
	
	/**
	 * Persistimos el tipo de estructura pasado como parámetro
	 * @param tipoEstructuraDto Tipo de estructura a persistir
	 * @return Tipo de estructura persistido
	 */
	TipoEstructuraDto saveUpdate(final TipoEstructuraDto tipoEstructuraDto);
	
}
