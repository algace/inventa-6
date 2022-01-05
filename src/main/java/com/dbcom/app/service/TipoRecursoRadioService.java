package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoRecursoRadioDto;

/**
 * Lógica para tipos de recurso radio
 * 
 * @author jose.vallve
 */
public interface TipoRecursoRadioService {

	/**
	 * Creamos un tipo de recurso radio sin persistencia
	 * @return Nuevo tipo de recurso radio
	 */
	TipoRecursoRadioDto create();

	/**
	 * Borrado del tipo de recurso radio con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de recurso radio
	 * @return Listado
	 */
	List<TipoRecursoRadioDto> readAll();
	
	/**
	 * Obtenemos el tipo de recurso radio con el id facilitado
	 * @param id Identificador
	 * @return Tipo de recurso radio
	 */
	TipoRecursoRadioDto read(final Short id);
	
	/**
	 * Persistimos el tipo de recurso radio pasado como parámetro
	 * @param tipoRecursoRadioDto Tipo de recurso radio a persistir
	 * @return Tipo de recurso radio persistido
	 */
	TipoRecursoRadioDto saveUpdate(final TipoRecursoRadioDto tipoRecursoRadioDto);
	
}
