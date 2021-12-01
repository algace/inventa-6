package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.VersionSWDto;

/**
 * Lógica para versiones
 * 
 * @author jose.vallve
 */
public interface VersionSWService extends GenericService<VersionSWDto, Long> {

	/**
	 * Creamos una versión sin persistencia
	 * @return Nueva versión
	 */
	VersionSWDto create();

	/**
	 * Borrado de la versión con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);

	/**
	 * Obtenemos un listado de las versiones
	 * @return Listado
	 */
	List<VersionSWDto> readAll();
	
	/**
	 * Obtenemos la versión con el id facilitado
	 * @param id Identificador
	 * @return Versión
	 */
	VersionSWDto read(final Long id);
	
	/**
	 * Obtenemos las versiones que no tiene la aplicación pasada como parámetro
	 * @param id Identificador de la aplicación
	 * @return Versiones
	 */
	List<VersionSWDto> readNotContains(final Long id);
	
	/**
	 * Persistimos la versión pasada como parámetro
	 * @param versionSWDto Versión a persistir
	 * @return Versión persistida
	 */
	VersionSWDto save(final VersionSWDto versionSWDto);
		
	/**
	 * Actualizamos la versión pasada como parámetro
	 * @param versionSWDto Versión a actualizar
	 * @return Versión actualizada
	 */
	VersionSWDto update(final VersionSWDto versionSWDto);
	
}
