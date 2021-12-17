package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.RegionMantenimientoDto;

/**
 * Lógica para tipos de subsistemas
 * 
 * @author jgm
 */
public interface RegionMantenimientoService {

	/**
	 * Creamos una región de mantenimiento sin persistencia
	 * @return Nuevo tipo de subsistema
	 */
	RegionMantenimientoDto create();
	
	/**
	 * Borrado de la región de mantenimiento con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);
	
	/**
	 * Obtenemos un listado de laS regiones de mantenimiento
	 * @return Listado
	 */
	List<RegionMantenimientoDto> readAll();
	
	/**
	 * Obtenemos la región de mantenimiento con el id facilitado
	 * @param id Identificador
	 * @return Versión
	 */
	RegionMantenimientoDto read(final Long id);
	
	/**
	 * Persistimos la región de mantenimiento pasada como parámetro
	 * @param RegionMantenimientoDto Region de mantenimiento a persistir
	 * @return Region de mantenimiento persistida
	 */
	RegionMantenimientoDto save(final RegionMantenimientoDto regionMantenimientoDto);
	
	/**
	 * Actualizamos la región de mantenimiento pasada como parámetro
	 * @param regionMantenimientoDto Región de mantenimiento a actualizar
	 * @return Región de mantenimiento actualizada
	 */
	RegionMantenimientoDto update(final RegionMantenimientoDto regionMantenimientoDto);
}
