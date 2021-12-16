package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.RecursoPasarelaDto;

public interface RecursoPasarelaService extends GenericService<RecursoPasarelaDto, Short>{

	/**
	 * Creamos un recurso sin persistencia
	 * @return Nuevo tipo de chasis
	 */
	RecursoPasarelaDto create();

	/**
	 * Borrado del recurso con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de recursos
	 * @return Listado
	 */
	List<RecursoPasarelaDto> readAll();
	
	/**
	 * Obtenemos el recurso con el id facilitado
	 * @param id Identificador
	 * @return Recurso
	 */
	RecursoPasarelaDto read(final Short id);
	
	/**
	 * Persistimos el recurso pasado como parámetro
	 * @param recursoPasarelaDto Recurso a persistir
	 * @return Tipo de chasis persistido
	 */
	RecursoPasarelaDto save(final RecursoPasarelaDto recursoPasarelaDto);
		
	/**
	 * Actualizamos el recurso pasado como parámetro
	 * @param recursoPasarelaDto Recurso a actualizar
	 * @return Recurso actualizado
	 */
	RecursoPasarelaDto update(final RecursoPasarelaDto recursoPasarelaDto);

}
