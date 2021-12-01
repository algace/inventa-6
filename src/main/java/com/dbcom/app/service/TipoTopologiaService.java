package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoTopologiaDto;

/**
 * Lógica para tipos de topología
 * 
 * @author jose.vallve
 */
public interface TipoTopologiaService extends GenericService<TipoTopologiaDto, Short> {

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
	TipoTopologiaDto save(final TipoTopologiaDto tipoTopologiaDto);
		
	/**
	 * Actualizamos el tipo de topología pasado como parámetro
	 * @param tipoTopologiaDto Tipo de topología a actualizar
	 * @return Tipo de topología actualizado
	 */
	TipoTopologiaDto update(final TipoTopologiaDto tipoTopologiaDto);
	
}
