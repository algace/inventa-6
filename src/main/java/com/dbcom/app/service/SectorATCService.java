package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.AirblockDto;
import com.dbcom.app.model.dto.SectorATCDto;

public interface SectorATCService {
	/**
	 * Creamos un sector ATC sin persistencia
	 * @return Nueva aplicación
	 */
	SectorATCDto create();

	/**
	 * Borrado del sector ATC con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los sectores ATC
	 * @return Listado
	 */
	List<SectorATCDto> readAll();
	
	/**
	 * Obtenemos el sector ATC con el id facilitado
	 * @param id Identificador
	 * @return Sector ATC
	 */
	SectorATCDto read(final Short id);
	
	/**
	 * Persistimos el sector ATCpasada como parámetro
	 * @param sectorATCDto Sector ATC a persistir
	 * @return Sector ATC persistido
	 */
	SectorATCDto saveUpdate(final SectorATCDto sectorATCDto);
	
	/**
	 * métodos internos para tratar las listas de Airblocks de manera correcta según hayan sido seleccionados o no.
	 */
    List<AirblockDto> listAirblocksSeleccionados(List<AirblockDto> allAirblocks, List<AirblockDto> listAirblock);
     
    List<AirblockDto> listAirblocksNoSeleccionados(List<AirblockDto> allAirblocks, List<AirblockDto> airblockSeleccionados);
	
}
