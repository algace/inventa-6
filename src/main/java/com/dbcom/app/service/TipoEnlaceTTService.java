package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoEnlaceTTDto;

/**
 * Lógica para tipos de enlace TT
 * 
 * @author jose.vallve
 */
public interface TipoEnlaceTTService extends GenericService<TipoEnlaceTTDto, Short> {

	/**
	 * Creamos un tipo de enlace TT sin persistencia
	 * @return Nuevo tipo de enlace TT
	 */
	TipoEnlaceTTDto create();

	/**
	 * Borrado del tipo de enlace TT con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de enlace TT
	 * @return Listado
	 */
	List<TipoEnlaceTTDto> readAll();
	
	/**
	 * Obtenemos el tipo de enlace TT con el id facilitado
	 * @param id Identificador
	 * @return Tipo de enlace TT
	 */
	TipoEnlaceTTDto read(final Short id);
	
	/**
	 * Persistimos el tipo de enlace TT pasado como parámetro
	 * @param tipoEnlaceTTDto Tipo de enlace TT a persistir
	 * @return Tipo de enlace TT persistido
	 */
	TipoEnlaceTTDto save(final TipoEnlaceTTDto tipoEnlaceTTDto);
		
	/**
	 * Actualizamos el tipo de enlace TT pasado como parámetro
	 * @param tipoEnlaceTTDto Tipo de enlace TT a actualizar
	 * @return Tipo de enlace TT actualizado
	 */
	TipoEnlaceTTDto update(final TipoEnlaceTTDto tipoEnlaceTTDto);
	
}
