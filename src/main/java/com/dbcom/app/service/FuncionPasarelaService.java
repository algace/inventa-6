package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.FuncionPasarelaDto;

public interface FuncionPasarelaService {

	/**
	 * Creamos una función sin persistencia
	 * @return Nuevo tipo de chasis
	 */
	FuncionPasarelaDto create();

	/**
	 * Borrada la función con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de las funciones
	 * @return Listado
	 */
	List<FuncionPasarelaDto> readAll();
	
	/**
	 * Obtenemos la función con el id facilitado
	 * @param id Identificador
	 * @return FuncionPasarela
	 */
	FuncionPasarelaDto read(final Short id);
	
	/**
	 * Persistimos la función pasada como parámetro
	 * @param FuncionPasarelaDto Funcion de pasarela a persistir
	 * @return Tipo de chasis persistido
	 */
	FuncionPasarelaDto saveUpdate(final FuncionPasarelaDto funcionPasarelaDto);
}
