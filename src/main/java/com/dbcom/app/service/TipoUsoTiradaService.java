package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoUsoTiradaDto;

/**
 * Lógica para tipos de uso tirada
 * 
 * @author jose.vallve
 */
public interface TipoUsoTiradaService {

	/**
	 * Creamos un tipo de uso tirada sin persistencia
	 * @return Nuevo tipo de uso tirada
	 */
	TipoUsoTiradaDto create();

	/**
	 * Borrado del tipo de uso tirada con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de uso tirada
	 * @return Listado
	 */
	List<TipoUsoTiradaDto> readAll();
	
	/**
	 * Obtenemos el tipo de uso tirada con el id facilitado
	 * @param id Identificador
	 * @return Tipo de uso tirada
	 */
	TipoUsoTiradaDto read(final Short id);
	
	/**
	 * Persistimos el tipo de uso tirada pasado como parámetro
	 * @param tipoUsoTiradaDto Tipo de uso tirada a persistir
	 * @return Tipo de uso tirada persistido
	 */
	TipoUsoTiradaDto saveUpdate(final TipoUsoTiradaDto tipoUsoTiradaDto);
	
}
