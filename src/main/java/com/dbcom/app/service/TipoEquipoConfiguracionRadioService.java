package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoEquipoConfiguracionRadioDto;

/**
 * Lógica para tipos de equipo/configuración de radio
 * 
 * @author jose.vallve
 */
public interface TipoEquipoConfiguracionRadioService extends GenericService<TipoEquipoConfiguracionRadioDto, Short> {

	/**
	 * Creamos un tipo de equipo/configuración de radio sin persistencia
	 * @return Nuevo tipo de equipo/configuración de radio
	 */
	TipoEquipoConfiguracionRadioDto create();

	/**
	 * Borrado del tipo de equipo/configuración de radio con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de equipo/configuración de radio
	 * @return Listado
	 */
	List<TipoEquipoConfiguracionRadioDto> readAll();
	
	/**
	 * Obtenemos el tipo de equipo/configuración de radio con el id facilitado
	 * @param id Identificador
	 * @return Tipo de equipo/configuración de radio
	 */
	TipoEquipoConfiguracionRadioDto read(final Short id);
	
	/**
	 * Persistimos el tipo de equipo/configuración de radio pasado como parámetro
	 * @param tipoEquipoConfiguracionRadioDto Tipo de equipo/configuración de radio a persistir
	 * @return Tipo de equipo/configuración de radio persistido
	 */
	TipoEquipoConfiguracionRadioDto save(final TipoEquipoConfiguracionRadioDto tipoEquipoConfiguracionRadioDto);
		
	/**
	 * Actualizamos el tipo de equipo/configuración de radio pasado como parámetro
	 * @param tipoEquipoConfiguracionRadioDto Tipo de equipo/configuración de radio a actualizar
	 * @return Tipo de equipo/configuración de radio actualizado
	 */
	TipoEquipoConfiguracionRadioDto update(final TipoEquipoConfiguracionRadioDto tipoEquipoConfiguracionRadioDto);
	
}
