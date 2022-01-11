package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.EquipamientoDto;
import com.dbcom.app.model.dto.EquipamientoLiteDto;


/**
 * Lógica para equipaciones
 * 
 * @author jose.vallve
 */
public interface EquipamientoService {

	/**
	 * Creamos un equipamiento sin persistencia
	 * @return Nuevo equipamiento
	 */
	EquipamientoDto create();

	/**
	 * Borrado del equipamiento con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);

	/**
	 * Obtenemos un listado de los equipamientos
	 * @return Listado
	 */
	List<EquipamientoLiteDto> readAll();
	
	/**
	 * Obtenemos el equipamiento con el id facilitado
	 * @param id Identificador
	 * @return Equipamiento
	 */
	EquipamientoDto read(final Long id);
	
	/**
	 * Obtenemos los equipamientos que no tiene la aplicación pasada como parámetro
	 * @param id Identificador de la aplicación
	 * @return Equipamientos
	 */
	List<EquipamientoLiteDto> readNotContains(final Long id);
	
	/**
	 * Persistimos el equipamiento pasado como parámetro
	 * @param equipamientoDto Equipamiento a persistir
	 * @return Equipamiento persistido
	 */
	EquipamientoDto saveUpdate(final EquipamientoDto equipamientoDto);
	
	/**
	 * Informa todas las listas y objetos para la recaraga de la vista
	 * @param equipamientoDto Equipamiento a persistir
	 * @return EquipamientoDto persistido
	 */
	EquipamientoDto setAllAttributesEquipamientoDto(final EquipamientoDto equipamientoDto);
		
}
