package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoSistemaLocalDto;

/**
 * Lógica para tipos de sistema local
 * 
 * @author jose.vallve
 */
public interface TipoSistemaLocalService {

	/**
	 * Creamos un tipo de sistema local sin persistencia
	 * @return Nuevo tipo de sistema local
	 */
	TipoSistemaLocalDto create();

	/**
	 * Borrado del tipo de sistema local con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de sistema local
	 * @return Listado
	 */
	List<TipoSistemaLocalDto> readAll();
	
	/**
	 * Obtenemos el tipo de sistema local con el id facilitado
	 * @param id Identificador
	 * @return Tipo de sistema local
	 */
	TipoSistemaLocalDto read(final Short id);
	
	/**
	 * Persistimos el tipo de sistema local pasado como parámetro
	 * @param tipoSistemaLocalDto Tipo de sistema local a persistir
	 * @return Tipo de sistema local persistido
	 */
	TipoSistemaLocalDto saveUpdate(final TipoSistemaLocalDto tipoSistemaLocalDto);
	
}
