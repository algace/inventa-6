package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.SectorOACIDto;

/**
 * Lógica para tipos de subsistemas
 * 
 * @author neoris
 */
public interface SectorOACIService {
	
	/**
	 * Creamos un sector OACI sin persistencia
	 * @return Nuevo sector OACI
	 */
	SectorOACIDto create();
	
	/**
	 * Borrado del sector OACI con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);
	
	/**
	 * Obtenemos un listado de los sectores OACI
	 * @return Listado
	 */
	List<SectorOACIDto> readAll();
	
	/**
	 * Obtenemos el sector OACI con el id facilitado
	 * @param id Identificador
	 * @return Versión
	 */
	SectorOACIDto read(final Long id);
	
	/**
	 * Persistimos el sector OACI pasado como parámetro
	 * @param sectorOACIDto Sector OACI a persistir
	 * @return Sector OACI persistido
	 */
	SectorOACIDto saveUpdate(final SectorOACIDto sectorOACIDto);

}
