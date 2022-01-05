package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoDependenciaDto;

/**
 * Lógica para tipos de dependencia
 * 
 * @author jose.vallve
 */
public interface TipoDependenciaService {

	/**
	 * Creamos un tipo de dependencia sin persistencia
	 * @return Nuevo tipo de dependencia
	 */
	TipoDependenciaDto create();

	/**
	 * Borrado del tipo de dependencia con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de dependencia
	 * @return Listado
	 */
	List<TipoDependenciaDto> readAll();
	
	/**
	 * Obtenemos el tipo de dependencia con el id facilitado
	 * @param id Identificador
	 * @return Tipo de dependencia
	 */
	TipoDependenciaDto read(final Short id);
	
	/**
	 * Persistimos el tipo de dependencia pasado como parámetro
	 * @param tipoDependenciaDto Tipo de dependencia a persistir
	 * @return Tipo de dependencia persistido
	 */
	TipoDependenciaDto saveUpdate(final TipoDependenciaDto tipoDependenciaDto);
	
}
