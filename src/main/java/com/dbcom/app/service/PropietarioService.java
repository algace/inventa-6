package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.PropietarioDto;
import com.dbcom.app.model.dto.PropietarioLiteDto;

public interface PropietarioService {

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
	List<PropietarioLiteDto> readAll();
	
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
	PropietarioDto saveUpdate(final PropietarioDto propietarioDto);
	
	/**
	 * Obtenemos un listado de los propietarios teniendo en cuenta el propietario por defecto
	 * Si el propietario por defecto existe en BBDD se debe poner el primero de la lista. Si no existe en BBDD
	 * se deberá poner como primer elemento de la lista un propietario con sus campos vacíos
	 * @return Listado
	 */
	
	List<PropietarioLiteDto> getPropietariosConValorPorDefecto();
}
