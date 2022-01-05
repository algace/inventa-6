package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoTiradaDto;

/**
 * Lógica para tipos de tirada
 * 
 * @author jose.vallve
 */
public interface TipoTiradaService {

	/**
	 * Creamos un tipo de tirada sin persistencia
	 * @return Nuevo tipo de tirada
	 */
	TipoTiradaDto create();

	/**
	 * Borrado del tipo de tirada con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de tirada
	 * @return Listado
	 */
	List<TipoTiradaDto> readAll();
	
	/**
	 * Obtenemos el tipo de tirada con el id facilitado
	 * @param id Identificador
	 * @return Tipo de tirada
	 */
	TipoTiradaDto read(final Short id);
	
	/**
	 * Persistimos el tipo de tirada pasado como parámetro
	 * @param tipoTiradaDto Tipo de tirada a persistir
	 * @return Tipo de tirada persistido
	 */
	TipoTiradaDto saveUpdate(final TipoTiradaDto tipoTiradaDto);
	
}
