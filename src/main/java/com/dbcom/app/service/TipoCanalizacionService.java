package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoCanalizacionDto;

/**
 * Lógica para tipos de canalización
 * 
 * @author jose.vallve
 */
public interface TipoCanalizacionService {

	/**
	 * Creamos un tipo de canalización sin persistencia
	 * @return Nuevo tipo de canalización
	 */
	TipoCanalizacionDto create();

	/**
	 * Borrado del tipo de canalización con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de canalización
	 * @return Listado
	 */
	List<TipoCanalizacionDto> readAll();
	
	/**
	 * Obtenemos el tipo de canalización con el id facilitado
	 * @param id Identificador
	 * @return Tipo de canalización
	 */
	TipoCanalizacionDto read(final Short id);
	
	/**
	 * Persistimos el tipo de canalización pasado como parámetro
	 * @param tipoCanalizacionDto Tipo de canalización a persistir
	 * @return Tipo de canalización persistido
	 */
	TipoCanalizacionDto saveUpdate(final TipoCanalizacionDto tipoCanalizacionDto);
	
}
