package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoConfiguracionTiradaDto;

/**
 * Lógica para tipos de configuración tirada
 * 
 * @author jose.vallve
 */
public interface TipoConfiguracionTiradaService {

	/**
	 * Creamos un tipo de configuración tirada sin persistencia
	 * @return Nuevo tipo de configuración tirada
	 */
	TipoConfiguracionTiradaDto create();

	/**
	 * Borrado del tipo de configuración tirada con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de configuración tirada
	 * @return Listado
	 */
	List<TipoConfiguracionTiradaDto> readAll();
	
	/**
	 * Obtenemos el tipo de configuración tirada con el id facilitado
	 * @param id Identificador
	 * @return Tipo de configuración tirada
	 */
	TipoConfiguracionTiradaDto read(final Short id);
	
	/**
	 * Persistimos el tipo de configuración tirada pasado como parámetro
	 * @param tipoConfiguracionTiradaDto Tipo de configuración tirada a persistir
	 * @return Tipo de configuración tirada persistido
	 */
	TipoConfiguracionTiradaDto saveUpdate(final TipoConfiguracionTiradaDto tipoConfiguracionTiradaDto);
	
}
