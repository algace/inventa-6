package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoSubsistemaDto;

/**
 * Lógica para tipos de subsistemas
 * 
 * @author jgm
 */
public interface TiposSubsistemasService {

	/**
	 * Creamos un tipo de subsistema sin persistencia
	 * @return Nuevo tipo de subsistema
	 */
	TipoSubsistemaDto create();
	
	/**
	 * Borrado del tipo de subsistema con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);
	
	/**
	 * Obtenemos un listado de los tipos de subsistemas
	 * @return Listado
	 */
	List<TipoSubsistemaDto> readAll();
	
	/**
	 * Obtenemos el tipo de subsistema con el id facilitado
	 * @param id Identificador
	 * @return Versión
	 */
	TipoSubsistemaDto read(final Long id);
	
	/**
	 * Persistimos el tipo de subsistema pasado como parámetro
	 * @param tipoSubsistemaDto Tipo de subsistema a persistir
	 * @return Tipo de subsistema persistido
	 */
	TipoSubsistemaDto save(final TipoSubsistemaDto tipoSubsistemaDto);
	
	/**
	 * Actualizamos el tipo de subsistema pasado como parámetro
	 * @param tipoSubsistemaDto Tipo de sibsistema a actualizar
	 * @return Tipo de subsistema actualizado
	 */
	TipoSubsistemaDto update(final TipoSubsistemaDto tipoSubsistemaDto);
}
