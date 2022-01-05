package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoUsoAntenaDto;

/**
 * Lógica para tipos de uso antena
 * 
 * @author jose.vallve
 */
public interface TipoUsoAntenaService {

	/**
	 * Creamos un tipo de uso antena sin persistencia
	 * @return Nuevo tipo de uso antena
	 */
	TipoUsoAntenaDto create();

	/**
	 * Borrado del tipo de uso antena con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de uso antena
	 * @return Listado
	 */
	List<TipoUsoAntenaDto> readAll();
	
	/**
	 * Obtenemos el tipo de uso antena con el id facilitado
	 * @param id Identificador
	 * @return Tipo de uso antena
	 */
	TipoUsoAntenaDto read(final Short id);
	
	/**
	 * Persistimos el tipo de uso antena pasado como parámetro
	 * @param tipoUsoAntenaDto Tipo de uso antena a persistir
	 * @return Tipo de uso antena persistido
	 */
	TipoUsoAntenaDto saveUpdate(final TipoUsoAntenaDto tipoUsoAntenaDto);
	
}
