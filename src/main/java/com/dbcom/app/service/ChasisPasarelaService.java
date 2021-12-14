package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.ChasisPasarelaDto;

public interface ChasisPasarelaService extends GenericService<ChasisPasarelaDto, Short>{

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
	ChasisPasarelaDto save(final ChasisPasarelaDto chasisPasarelaDto);
		
	/**
	 * Actualizamos el tipo de pasarela pasado como parámetro
	 * @param pasarelaChasisDto Tipo de chasis a actualizar
	 * @return Tipo de pasarela actualizado
	 */
	ChasisPasarelaDto update(final ChasisPasarelaDto chasisPasarelaDto);

}
