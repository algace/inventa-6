package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.RegionOperativaDto;

/**
 * Lógica para regiones operativas
 * 
 * @author jgm
 */
public interface RegionOperativaService {

	/**
	 * Creamos una región operativa sin persistencia
	 * @return Nueva región operativa
	 */
	RegionOperativaDto create();
	
	/**
	 * Borrado de la región operativa con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);
	
	/**
	 * Obtenemos un listado de las regiones operativas
	 * @return Listado
	 */
	List<RegionOperativaDto> readAll();
	
	/**
	 * Obtenemos la región operativa con el id facilitado
	 * @param id Identificador
	 * @return Versión
	 */
	RegionOperativaDto read(final Long id);
	
	/**
	 * Persistimos la región operativa pasada como parámetro
	 * @param RegionOperativaDto Region operativa a persistir
	 * @return Region operativa persistida
	 */
	RegionOperativaDto save(final RegionOperativaDto regionOperativaDto);
	
	/**
	 * Actualizamos la región operativa pasada como parámetro
	 * @param regionOperativaDto Región operativa a actualizar
	 * @return Región operativa actualizada
	 */
	RegionOperativaDto update(final RegionOperativaDto regionOperativaDto);
}
