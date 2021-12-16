package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.PropietarioDto;

public interface PropietarioService extends GenericService<PropietarioDto, Short>{

	/**
	 * Creamos un propietario sin persistencia
	 * @return Nuevo tipo de chasis
	 */
	PropietarioDto create();

	/**
	 * Borrado del propietario con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de propietarios
	 * @return Listado
	 */
	List<PropietarioDto> readAll();
	
	/**
	 * Obtenemos el propietario con el id facilitado
	 * @param id Identificador
	 * @return Propietario
	 */
	PropietarioDto read(final Short id);
	
	/**
	 * Persistimos el propietario pasado como parámetro
	 * @param propietarioDto Propietario a persistir
	 * @return Propietario persistido
	 */
	PropietarioDto save(final PropietarioDto propietarioDto);
		
	/**
	 * Actualizamos el propietario pasado como parámetro
	 * @param propietarioDto Propietario a actualizar
	 * @return Propietario actualizado
	 */
	PropietarioDto update(final PropietarioDto propietarioDto);
}
