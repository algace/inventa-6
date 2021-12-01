package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoInstalacionDto;

/**
 * Lógica para tipos de instalación
 * 
 * @author jose.vallve
 */
public interface TipoInstalacionService extends GenericService<TipoInstalacionDto, Short> {

	/**
	 * Creamos un tipo de instalación sin persistencia
	 * @return Nuevo tipo de instalación
	 */
	TipoInstalacionDto create();

	/**
	 * Borrado del tipo de instalación con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de instalación
	 * @return Listado
	 */
	List<TipoInstalacionDto> readAll();
	
	/**
	 * Obtenemos el tipo de instalación con el id facilitado
	 * @param id Identificador
	 * @return Tipo de instalación
	 */
	TipoInstalacionDto read(final Short id);
	
	/**
	 * Persistimos el tipo de instalación pasado como parámetro
	 * @param tipoInstalacionDto Tipo de instalación a persistir
	 * @return Tipo de instalación persistido
	 */
	TipoInstalacionDto save(final TipoInstalacionDto tipoInstalacionDto);
		
	/**
	 * Actualizamos el tipo de instalación pasado como parámetro
	 * @param tipoInstalacionDto Tipo de instalación a actualizar
	 * @return Tipo de instalación actualizado
	 */
	TipoInstalacionDto update(final TipoInstalacionDto tipoInstalacionDto);
	
}
