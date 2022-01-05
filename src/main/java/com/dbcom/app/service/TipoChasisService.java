package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoChasisDto;

public interface TipoChasisService {

	/**
	 * Creamos un tipo de chasis sin persistencia
	 * @return Nuevo tipo de chasis
	 */
	TipoChasisDto create();

	/**
	 * Borrado del tipo de chasis con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de chasis
	 * @return Listado
	 */
	List<TipoChasisDto> readAll();
	
	/**
	 * Obtenemos el tipo de chasis con el id facilitado
	 * @param id Identificador
	 * @return Tipo de chasis
	 */
	TipoChasisDto read(final Short id);
	
	/**
	 * Persistimos el tipo de chasis pasado como parámetro
	 * @param tipoChasisDto Tipo de chasis a persistir
	 * @return Tipo de chasis persistido
	 */
	TipoChasisDto saveUpdate(final TipoChasisDto tipoChasisDto);
}
