package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.AirblockDto;


/**
 * Lógica para airblocks
 * 
 * @author eduardo.tubilleja
 */
public interface AirblockService {

	/**
	 * Creamos un airblock sin persistencia
	 * @return Nuevo airblock
	 */
	AirblockDto create();

	/**
	 * Borrado del airblock con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);

	/**
	 * Obtenemos un listado de los airblocks
	 * @return Listado
	 */
	List<AirblockDto> readAll();
	
	/**
	 * Obtenemos el airblock con el id facilitado
	 * @param id Identificador
	 * @return Airblock
	 */
	AirblockDto read(final Long id);
	
	/**
	 * Obtenemos los airblocks que no tiene el sector ATC pasado como parámetro
	 * @param id Identificador de la aplicación
	 * @return Airblocks
	 */
	List<AirblockDto> readNotContains(final Short id);
	
	/**
	 * Persistimos el airblock pasado como parámetro
	 * @param airblockDto Airblock a persistir
	 * @return Airblock persistido
	 */
	AirblockDto saveUpdate(final AirblockDto airblockDto);
	
}
