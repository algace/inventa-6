package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.RedTTDto;
import com.dbcom.app.model.dto.RedTTLiteDto;

/**
 * Lógica para aplicaciones
 * 
 * @author neoris
 */
public interface RedTTService {

	/**
	 * Creamos una red T/T sin persistencia
	 * @return Nueva red T/T
	 */
	RedTTDto create();

	/**
	 * Borrado de la red T/T con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);

	/**
	 * Obtenemos un listado de las redes T/T
	 * @return Listado
	 */
	List<RedTTLiteDto> readAll();
	
	/**
	 * Obtenemos la red T/T con el id facilitado
	 * @param id Identificador
	 * @return Red T/T
	 */
	RedTTDto read(final Long id);
	
	/**
	 * Persistimos la red T/T pasada como parámetro
	 * @param redTTDto Red T/T a persistir
	 * @return Red T/T persistida
	 */
	RedTTDto saveUpdate(final RedTTDto redTTDto);
	
}
