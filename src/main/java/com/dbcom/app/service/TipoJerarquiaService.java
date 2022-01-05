package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoJerarquiaDto;

/**
 * Lógica para tipos de jerarquía
 * 
 * @author jose.vallve
 */
public interface TipoJerarquiaService {

	/**
	 * Creamos un tipo de jerarquía sin persistencia
	 * @return Nuevo tipo de jerarquía
	 */
	TipoJerarquiaDto create();

	/**
	 * Borrado del tipo de jerarquía con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de jerarquía
	 * @return Listado
	 */
	List<TipoJerarquiaDto> readAll();
	
	/**
	 * Obtenemos el tipo de jerarquía con el id facilitado
	 * @param id Identificador
	 * @return Tipo de jerarquía
	 */
	TipoJerarquiaDto read(final Short id);
	
	/**
	 * Persistimos el tipo de jerarquía pasado como parámetro
	 * @param tipoJerarquiaDto Tipo de jerarquía a persistir
	 * @return Tipo de jerarquía persistido
	 */
	TipoJerarquiaDto saveUpdate(final TipoJerarquiaDto tipoJerarquiaDto);
	
}
