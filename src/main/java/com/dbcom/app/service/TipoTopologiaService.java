package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoTopologiaDto;
import com.dbcom.app.model.dto.TipoTopologiaLiteDto;

/**
 * Lógica para tipos de topología
 * 
 * @author jose.vallve
 */
public interface TipoTopologiaService {

	/**
	 * Creamos un tipo de topología sin persistencia
	 * @return Nuevo tipo de topología
	 */
	TipoTopologiaDto create();

	/**
	 * Borrado del tipo de topología con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de topología
	 * @return Listado
	 */
	List<TipoTopologiaDto> readAll();
	
	/**
	 * Obtenemos el tipo de topología con el id facilitado
	 * @param id Identificador
	 * @return Tipo de topología
	 */
	TipoTopologiaDto read(final Short id);
	
	/**
	 * Persistimos el tipo de topología pasado como parámetro
	 * @param tipoTopologiaDto Tipo de topología a persistir
	 * @return Tipo de topología persistido
	 */
	TipoTopologiaDto saveUpdate(final TipoTopologiaDto tipoTopologiaDto);
	
	/**
	 * Obtenemos un listado de los tipos de topología teniendo en cuenta el tipo de topología por defecto
	 * Si la topología por defecto existe en BBDD se debe poner la primera de la lista. Si no existe en BBDD
	 * se deberá poner como primer elemento de la lista una topología con sus campos vacíos
	 * @return Listado
	 */
	List<TipoTopologiaLiteDto> getTiposTopologiasConValorPorDefecto();
}
