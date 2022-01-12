package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoSistemaDto;
import com.dbcom.app.model.dto.TipoSistemaLiteDto;
import com.dbcom.app.model.dto.TipoSubsistemaDto;

/**
 * Lógica para tipos de sistemas
 * 
 * @author neoris
 */
public interface TipoSistemaService {
	
	/**
	 * Creamos un tipo de sistema sin persistencia
	 * @return Nuevo tipo de sistema
	 */
	TipoSistemaDto create();
	
	/**
	 * Borrado del tipo de sistema con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);
	
	/**
	 * Obtenemos un listado de los tipos de sistemas
	 * @return Listado
	 */
	List<TipoSistemaLiteDto> readAll();
	
	/**
	 * Obtenemos el tipo de sistema con el id facilitado
	 * @param id Identificador
	 * @return Versión
	 */
	TipoSistemaDto read(final Long id);
	
	/**
	 * Persistimos el tipo de sistema pasado como parámetro
	 * @param tipoSistemaDto Tipo de sistema a persistir
	 * @return Tipo de sistema persistido
	 */
	TipoSistemaDto saveUpdate(final TipoSistemaDto tipoSistemaDto);

	/**
	 * Obtenemos la lista de tipos de subsistemas asociados al tipo de sistema cuyo 
	 * id se recibe por parámetro
	 * @param id Id de un tipo de sistema
	 * @return lista de los tipos de subsistemas asociados al tipo de sistema
	 */
	List<TipoSubsistemaDto> obtenerSubsistemasAsociados(Long id);
}
