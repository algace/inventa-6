package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.RegionOperativaDto;

/**
 * Lógica para regiones operativas
 * 
 * @author neoris
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
	RegionOperativaDto saveUpdate(final RegionOperativaDto regionOperativaDto);
	
	/**
	 * Obtenemos un listado de las regiones operativas teniendo en cuenta la región operativa por defecto
	 * Si la región por defecto existe en BBDD se debe poner la primera de la lista. Si no existe en BBDD
	 * se deberá poner como primer elemento de la lista una región con sus campos vacíos
	 * @return Listado
	 */
	List<RegionOperativaDto> getRegionesOperativasConValorPorDefecto();
}
