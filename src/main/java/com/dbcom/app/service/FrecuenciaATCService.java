package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.FrecuenciaATCDto;
import com.dbcom.app.model.dto.FrecuenciaATCLiteDto;


/**
 * Lógica para frecuenciasATC
 * 
 * @author eduardo.tubilleja
 */
public interface FrecuenciaATCService {

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
	List<FrecuenciaATCLiteDto> readAll();
	
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
	FrecuenciaATCDto saveUpdate(final FrecuenciaATCDto frecuenciaATCDto);
	
}
