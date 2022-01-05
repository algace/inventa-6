package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.RedTTDto;
import com.dbcom.app.model.dto.TipoTopologiaDto;

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
	List<RedTTDto> readAll();
	
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
	
	/**
	 * Obtenemos los tipos de topologías posibles para una red T/T poniendo su tipo de topología
	 * asociado el primero de la lista
	 * @param redTTDto Red T/T
	 * @return lista de tipos de topologías disponibles con el tipo de topología asociado a la red T/T
	 *         el primero de la lista
	 */
	List<TipoTopologiaDto> getTiposTopologiasConAsociadaPrimero(RedTTDto redTTDto);
	
}
