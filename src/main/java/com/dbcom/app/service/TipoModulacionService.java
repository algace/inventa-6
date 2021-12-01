package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoModulacionDto;

/**
 * Lógica para tipos de modulación
 * 
 * @author jose.vallve
 */
public interface TipoModulacionService extends GenericService<TipoModulacionDto, Short> {

	/**
	 * Creamos un tipo de modulación sin persistencia
	 * @return Nuevo tipo de modulación
	 */
	TipoModulacionDto create();

	/**
	 * Borrado del tipo de modulación con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de modulación
	 * @return Listado
	 */
	List<TipoModulacionDto> readAll();
	
	/**
	 * Obtenemos el tipo de modulación con el id facilitado
	 * @param id Identificador
	 * @return Tipo de modulación
	 */
	TipoModulacionDto read(final Short id);
	
	/**
	 * Persistimos el tipo de modulación pasado como parámetro
	 * @param tipoModulacionDto Tipo de modulación a persistir
	 * @return Tipo de modulación persistido
	 */
	TipoModulacionDto save(final TipoModulacionDto tipoModulacionDto);
		
	/**
	 * Actualizamos el tipo de modulación pasado como parámetro
	 * @param tipoModulacionDto Tipo de modulación a actualizar
	 * @return Tipo de modulación actualizado
	 */
	TipoModulacionDto update(final TipoModulacionDto tipoModulacionDto);

}
