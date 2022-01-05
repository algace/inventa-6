package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.FrecuenciaDto;


/**
 * Lógica para frecuencias
 * 
 * @author eduardo.tubilleja
 */
public interface FrecuenciaService {

	/**
	 * Creamos una frecuencia sin persistencia
	 * @return Nueva frecuencia
	 */
	FrecuenciaDto create();

	/**
	 * Borrado de la frecuencia con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);

	/**
	 * Obtenemos un listado de las frecuencias
	 * @return Listado
	 */
	List<FrecuenciaDto> readAll();
	
	/**
	 * Obtenemos la frecuencia con el id facilitado
	 * @param id Identificador
	 * @return Frecuencia
	 */
	FrecuenciaDto read(final Long id);
	
	/**
	 * Persistimos la frecuencia pasada como parámetro
	 * @param frecuenciaDto Frecuencia a persistir
	 * @return Frecuencia persistido
	 */
	FrecuenciaDto saveUpdate(final FrecuenciaDto frecuenciaDto);
	
}
