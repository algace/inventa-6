package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoRadioenlaceDto;

/**
 * Lógica para tipos de radioenlace
 * 
 * @author jose.vallve
 */
public interface TipoRadioenlaceService extends GenericService<TipoRadioenlaceDto, Short> {

	/**
	 * Creamos un tipo de radioenlace sin persistencia
	 * @return Nuevo tipo de radioenlace
	 */
	TipoRadioenlaceDto create();

	/**
	 * Borrado del tipo de radioenlace con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de radioenlace
	 * @return Listado
	 */
	List<TipoRadioenlaceDto> readAll();
	
	/**
	 * Obtenemos el tipo de radioenlace con el id facilitado
	 * @param id Identificador
	 * @return Tipo de radioenlace
	 */
	TipoRadioenlaceDto read(final Short id);
	
	/**
	 * Persistimos el tipo de radioenlace pasado como parámetro
	 * @param tipoRadioenlaceDto Tipo de radioenlace a persistir
	 * @return Tipo de radioenlace persistido
	 */
	TipoRadioenlaceDto save(final TipoRadioenlaceDto tipoRadioenlaceDto);
		
	/**
	 * Actualizamos el tipo de radioenlace pasado como parámetro
	 * @param tipoRadioenlaceDto Tipo de radioenlace a actualizar
	 * @return Tipo de radioenlace actualizado
	 */
	TipoRadioenlaceDto update(final TipoRadioenlaceDto tipoRadioenlaceDto);
	
}
