package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.ChasisPasarelaDto;
import com.dbcom.app.model.dto.ChasisPasarelaLiteDto;

public interface ChasisPasarelaService {

	/**
	 * Creamos una pasarela sin persistencia
	 * @return Nuevo tipo de chasis
	 */
	ChasisPasarelaDto create();

	/**
	 * Borrado del tipo de pasarela con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de pasarela
	 * @return Listado
	 */
	List<ChasisPasarelaDto> readAll();
	
	List<ChasisPasarelaLiteDto> readAllLite();

	
	/**
	 * Obtenemos el tipo de pasarela con el id facilitado
	 * @param id Identificador
	 * @return Tipo de chasis
	 */
	ChasisPasarelaDto read(final Short id);
	
	/**
	 * Persistimos el tipo de pasarela pasado como parámetro
	 * @param pasarelaChasisDto Tipo de pasarela a persistir
	 * @return Tipo de chasis persistido
	 */
	ChasisPasarelaDto saveUpdate(final ChasisPasarelaDto chasisPasarelaDto);

}
