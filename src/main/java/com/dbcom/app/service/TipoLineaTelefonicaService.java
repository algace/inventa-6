package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoLineaTelefonicaDto;

/**
 * Lógica para tipos de línea telefónica
 * 
 * @author jose.vallve
 */
public interface TipoLineaTelefonicaService {

	/**
	 * Creamos un tipo de línea telefónica sin persistencia
	 * @return Nuevo tipo de línea telefónica
	 */
	TipoLineaTelefonicaDto create();

	/**
	 * Borrado del tipo de línea telefónica con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de línea telefónica
	 * @return Listado
	 */
	List<TipoLineaTelefonicaDto> readAll();
	
	/**
	 * Obtenemos el tipo de línea telefónica con el id facilitado
	 * @param id Identificador
	 * @return Tipo de línea telefónica
	 */
	TipoLineaTelefonicaDto read(final Short id);
	
	/**
	 * Persistimos el tipo de línea telefónica pasado como parámetro
	 * @param tipoLineaTelefonicaDto Tipo de línea telefónica a persistir
	 * @return Tipo de línea telefónica persistido
	 */
	TipoLineaTelefonicaDto saveUpdate(final TipoLineaTelefonicaDto tipoLineaTelefonicaDto);
}
