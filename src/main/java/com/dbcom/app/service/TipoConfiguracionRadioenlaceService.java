package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoConfiguracionRadioenlaceDto;

/**
 * Lógica para tipos de configuración radioenlace
 * 
 * @author jose.vallve
 */
public interface TipoConfiguracionRadioenlaceService {

	/**
	 * Creamos un tipo de configuración radioenlace sin persistencia
	 * @return Nuevo tipo de configuración radioenlace
	 */
	TipoConfiguracionRadioenlaceDto create();

	/**
	 * Borrado del tipo de configuración radioenlace con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de configuración radioenlace
	 * @return Listado
	 */
	List<TipoConfiguracionRadioenlaceDto> readAll();
	
	/**
	 * Obtenemos el tipo de configuración radioenlace con el id facilitado
	 * @param id Identificador
	 * @return Tipo de configuración radioenlace
	 */
	TipoConfiguracionRadioenlaceDto read(final Short id);
	
	/**
	 * Persistimos el tipo de configuración radioenlace pasado como parámetro
	 * @param tipoConfiguracionRadioenlaceDto Tipo de configuración radioenlace a persistir
	 * @return Tipo de configuración radioenlace persistido
	 */
	TipoConfiguracionRadioenlaceDto saveUpdate(final TipoConfiguracionRadioenlaceDto tipoConfiguracionRadioenlaceDto);
	
}
