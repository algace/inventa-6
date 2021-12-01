package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoSectorATCDto;

/**
 * Lógica para tipos de sector ATC
 * 
 * @author jose.vallve
 */
public interface TipoSectorATCService extends GenericService<TipoSectorATCDto, Short> {

	/**
	 * Creamos un tipo de sector ATC sin persistencia
	 * @return Nuevo tipo de sector ATC
	 */
	TipoSectorATCDto create();

	/**
	 * Borrado del tipo de sector ATC con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de sector ATC
	 * @return Listado
	 */
	List<TipoSectorATCDto> readAll();
	
	/**
	 * Obtenemos el tipo de sector ATC con el id facilitado
	 * @param id Identificador
	 * @return Tipo de sector ATC
	 */
	TipoSectorATCDto read(final Short id);
	
	/**
	 * Persistimos el tipo de sector ATC pasado como parámetro
	 * @param tipoSectorATCDto Tipo de sector ATC a persistir
	 * @return Tipo de sector ATC persistido
	 */
	TipoSectorATCDto save(final TipoSectorATCDto tipoSectorATCDto);
		
	/**
	 * Actualizamos el tipo de sector ATC pasado como parámetro
	 * @param tipoSectorATCDto Tipo de sector ATC a actualizar
	 * @return Tipo de sector ATC actualizado
	 */
	TipoSectorATCDto update(final TipoSectorATCDto tipoSectorATCDto);
	
}
