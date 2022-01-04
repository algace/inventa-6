package com.dbcom.app.service;

import java.util.List;

import javax.validation.Valid;

import com.dbcom.app.model.dto.TarjetaPasarelaDto;

public interface TarjetaPasarelaService {
	
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
	TarjetaPasarelaDto saveUpdate(final TarjetaPasarelaDto tarjetaPasarelaDto) ;
	
	
	
	/**
	 * Se añade lista de chasis pasarelas al DTO
	 * @param tarjetaPasarelaDto Tipo de pasarela a actualizar
	 * @return tarjetaPasarelaDto actualizada
	 */
	TarjetaPasarelaDto setListChasisPasarelasInTarjetasPasarelasDTO(TarjetaPasarelaDto tarjetaPasarelaDto);
		
}
