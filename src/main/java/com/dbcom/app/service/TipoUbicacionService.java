package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoUbicacionDto;

/**
 * Lógica para tipos de ubicación
 * 
 * @author jose.vallve
 */
public interface TipoUbicacionService extends GenericService<TipoUbicacionDto, Short> {

	/**
	 * Creamos un tipo de ubicación sin persistencia
	 * @return Nuevo tipo de ubicación
	 */
	TipoUbicacionDto create();

	/**
	 * Borrado del tipo de ubicación con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de ubicación
	 * @return Listado
	 */
	List<TipoUbicacionDto> readAll();
	
	/**
	 * Obtenemos el tipo de ubicación con el id facilitado
	 * @param id Identificador
	 * @return Tipo de ubicación
	 */
	TipoUbicacionDto read(final Short id);
	
	/**
	 * Persistimos el tipo de ubicación pasado como parámetro
	 * @param tipoUbicacionDto Tipo de ubicación a persistir
	 * @return Tipo de ubicación persistido
	 */
	TipoUbicacionDto save(final TipoUbicacionDto tipoUbicacionDto);
		
	/**
	 * Actualizamos el tipo de ubicación pasado como parámetro
	 * @param tipoUbicacionDto Tipo de ubicación a actualizar
	 * @return Tipo de ubicación actualizado
	 */
	TipoUbicacionDto update(final TipoUbicacionDto tipoUbicacionDto);
	
}
