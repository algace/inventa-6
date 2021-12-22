package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.SectorMantenimientoDto;

/**
 * Lógica para tipos de subsistemas
 * 
 * @author jgm
 */
public interface SectorMantenimientoService {

	/**
	 * Creamos un sector de mantenimiento sin persistencia
	 * @return Nuevo sector de mantenimiento
	 */
	SectorMantenimientoDto create();
	
	/**
	 * Borrado del sector de mantenimiento con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);
	
	/**
	 * Obtenemos un listado de los sectores de mantenimiento
	 * @return Listado
	 */
	List<SectorMantenimientoDto> readAll();
	
	/**
	 * Obtenemos el sector de mantenimiento con el id facilitado
	 * @param id Identificador
	 * @return Versión
	 */
	SectorMantenimientoDto read(final Long id);
	
	/**
	 * Persistimos el sector de mantenimiento pasado como parámetro
	 * @param sectorMantenimientoDto Sector de mantenimiento a persistir
	 * @return Sector de mantenimiento persistido
	 */
	SectorMantenimientoDto save(final SectorMantenimientoDto sectorMantenimientoDto);
	
	/**
	 * Actualizamos el sector de mantenimiento pasado como parámetro
	 * @param tipoSubsistemaDto Sector de mantenimiento a actualizar
	 * @return Sector de mantenimiento actualizado
	 */
	SectorMantenimientoDto update(final SectorMantenimientoDto sectorMantenimientoDto);
}
