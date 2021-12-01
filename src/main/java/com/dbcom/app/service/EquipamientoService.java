package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.EquipamientoDto;


/**
 * Lógica para equipaciones
 * 
 * @author jose.vallve
 */
public interface EquipamientoService extends GenericService<EquipamientoDto, Long> {

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
	List<EquipamientoDto> readAll();
	
	/**
	 * Obtenemos el equipamiento con el id facilitado
	 * @param id Identificador
	 * @return Equipamiento
	 */
	EquipamientoDto read(final Long id);
	
	/**
	 * Persistimos el equipamiento pasado como parámetro
	 * @param equipamientoDto Equipamiento a persistir
	 * @return Equipamiento persistido
	 */
	EquipamientoDto save(final EquipamientoDto equipamientoDto);
		
	/**
	 * Actualizamos el equipamiento pasado como parámetro
	 * @param equipamientoDto Equipamiento a actualizar
	 * @return Equipamiento actualizado
	 */
	EquipamientoDto update(final EquipamientoDto equipamientoDto);
	
}
