package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoCapacidadEnlaceTTDto;

/**
 * Lógica para tipos de capacidad enlace TT
 * 
 * @author jose.vallve
 */
public interface TipoCapacidadEnlaceTTService extends GenericService<TipoCapacidadEnlaceTTDto, Short> {

	/**
	 * Creamos un tipo de capacidad enlace TT sin persistencia
	 * @return Nuevo tipo de capacidad enlace TT
	 */
	TipoCapacidadEnlaceTTDto create();

	/**
	 * Borrado del tipo de capacidad enlace TT con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de capacidad enlace TT
	 * @return Listado
	 */
	List<TipoCapacidadEnlaceTTDto> readAll();
	
	/**
	 * Obtenemos el tipo de capacidad enlace TT con el id facilitado
	 * @param id Identificador
	 * @return Tipo de capacidad enlace TT
	 */
	TipoCapacidadEnlaceTTDto read(final Short id);
	
	/**
	 * Persistimos el tipo de capacidad enlace TT pasado como parámetro
	 * @param tipoCapacidadEnlaceTTDto Tipo de capacidad enlace TT a persistir
	 * @return Tipo de capacidad enlace TT persistido
	 */
	TipoCapacidadEnlaceTTDto save(final TipoCapacidadEnlaceTTDto tipoCapacidadEnlaceTTDto);
		
	/**
	 * Actualizamos el tipo de capacidad enlace TT pasado como parámetro
	 * @param tipoCapacidadEnlaceTTDto Tipo de capacidad enlace TT a actualizar
	 * @return Tipo de capacidad enlace TT actualizado
	 */
	TipoCapacidadEnlaceTTDto update(final TipoCapacidadEnlaceTTDto tipoCapacidadEnlaceTTDto);
	
}
