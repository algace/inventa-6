package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.FrecuenciaATCDto;


/**
 * Lógica para frecuenciasATC
 * 
 * @author eduardo.tubilleja
 */
public interface FrecuenciaATCService extends GenericService<FrecuenciaATCDto, Long> {

	/**
	 * Creamos una frecuenciaATC sin persistencia
	 * @return Nueva frecuenciaATC
	 */
	FrecuenciaATCDto create();

	/**
	 * Borrado de la frecuenciaATC con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);

	/**
	 * Obtenemos un listado de las frecuenciasATC
	 * @return Listado
	 */
	List<FrecuenciaATCDto> readAll();
	
	/**
	 * Obtenemos la frecuenciaATC con el id facilitado
	 * @param id Identificador
	 * @return FrecuenciaATC
	 */
	FrecuenciaATCDto read(final Long id);
	
	/**
	 * Persistimos la frecuenciaATC pasada como parámetro
	 * @param frecuenciaATCDto FrecuenciaATC a persistir
	 * @return FrecuenciaATC persistido
	 */
	FrecuenciaATCDto save(final FrecuenciaATCDto frecuenciaATCDto);
		
	/**
	 * Actualizamos la frecuenciaATC pasada como parámetro
	 * @param frecuenciaDto FrecuenciaATC a actualizar
	 * @return FrecuenciaATC actualizado
	 */
	FrecuenciaATCDto update(final FrecuenciaATCDto frecuenciaATCDto);
	
}
