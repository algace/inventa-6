package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoPolarizacionDto;

/**
 * Lógica para tipos de polarización
 * 
 * @author jose.vallve
 */
public interface TipoPolarizacionService {

	/**
	 * Creamos un tipo de polarización sin persistencia
	 * @return Nuevo tipo de polarización
	 */
	TipoPolarizacionDto create();

	/**
	 * Borrado del tipo de polarización con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de polarización
	 * @return Listado
	 */
	List<TipoPolarizacionDto> readAll();
	
	/**
	 * Obtenemos el tipo de polarización con el id facilitado
	 * @param id Identificador
	 * @return Tipo de polarización
	 */
	TipoPolarizacionDto read(final Short id);
	
	/**
	 * Persistimos el tipo de polarización pasado como parámetro
	 * @param tipoPolarizacionDto Tipo de polarización a persistir
	 * @return Tipo de polarización persistido
	 */
	TipoPolarizacionDto saveUpdate(final TipoPolarizacionDto tipoPolarizacionDto);
	
}
