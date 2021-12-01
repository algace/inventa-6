package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoRecursoOperativoDto;

/**
 * Lógica para tipos de recurso operativo
 * 
 * @author jose.vallve
 */
public interface TipoRecursoOperativoService extends GenericService<TipoRecursoOperativoDto, Short> {

	/**
	 * Creamos un tipo de recurso operativo sin persistencia
	 * @return Nuevo tipo de recurso operativo
	 */
	TipoRecursoOperativoDto create();

	/**
	 * Borrado del tipo de recurso operativo con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de recurso operativo
	 * @return Listado
	 */
	List<TipoRecursoOperativoDto> readAll();
	
	/**
	 * Obtenemos el tipo de recurso operativo con el id facilitado
	 * @param id Identificador
	 * @return Tipo de recurso operativo
	 */
	TipoRecursoOperativoDto read(final Short id);
	
	/**
	 * Persistimos el tipo de recurso operativo pasado como parámetro
	 * @param tipoRecursoOperativoDto Tipo de recurso operativo a persistir
	 * @return Tipo de recurso operativo persistido
	 */
	TipoRecursoOperativoDto save(final TipoRecursoOperativoDto tipoRecursoOperativoDto);
		
	/**
	 * Actualizamos el tipo de recurso operativo pasado como parámetro
	 * @param tipoRecursoOperativoDto Tipo de recurso operativo a actualizar
	 * @return Tipo de recurso operativo actualizado
	 */
	TipoRecursoOperativoDto update(final TipoRecursoOperativoDto tipoRecursoOperativoDto);
	
}
