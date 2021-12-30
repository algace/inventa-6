package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TarjetaPasarelaDto;

public interface TarjetaPasarelaService extends GenericService<TarjetaPasarelaDto, Short> {
	
	/**
	 * Creamos una tarjeta sin persistencia
	 * @return Nuevo tipo de chasis
	 */
	TarjetaPasarelaDto create();

	/**
	 * Borrado de la tarjeta con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de las tarjetas
	 * @return Listado
	 */
	List<TarjetaPasarelaDto> readAll();
	
	/**
	 * Obtenemos la tarjeta con el id facilitado
	 * @param id Identificador
	 * @return Tarjeta
	 */
	TarjetaPasarelaDto read(final Short id);
	
	/**
	 * Persistimos la tarjeta pasado como parámetro
	 * @param pasarelaChasisDto Tipo de pasarela a persistir
	 * @return Tarjeta persistida
	 */
	TarjetaPasarelaDto save(final TarjetaPasarelaDto tarjetaPasarelaDto);
		
	/**
	 * Actualizamos la tarjeta pasado como parámetro
	 * @param pasarelaChasisDto Tarjeta a actualizar
	 * @return Tarjeta actualizado
	 */
	TarjetaPasarelaDto update(final TarjetaPasarelaDto tarjetaPasarelaDto);

}
