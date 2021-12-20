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
	 * Creamos un tipo de subsistema sin persistencia
	 * @return Nuevo tipo de subsistema
	 */
	RegionMantenimientoDto create();
	
	/**
	 * Obtenemos un listado de los tipos de subsistemas
	 * @return Listado
	 */
	List<RegionMantenimientoDto> readAll();
	
	/**
	 * Obtenemos el tipo de subsistema con el id facilitado
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
}
