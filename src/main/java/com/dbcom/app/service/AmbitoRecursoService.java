package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.AmbitoRecursoDto;

public interface AmbitoRecursoService {

	/**
	 * Creamos un ámbito sin persistencia
	 * @return Nuevo tipo de chasis
	 */
	AmbitoRecursoDto create();

	/**
	 * Borrado del ámbito con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);

	/**
	 * Obtenemos un listado de los ámbitos
	 * @return Listado
	 */
	List<AmbitoRecursoDto> readAll();
	
	/**
	 * Obtenemos el ámbito con el id facilitado
	 * @param id Identificador
	 * @return Ámbito
	 */
	AmbitoRecursoDto read(final Long id);
	
	/**
	 * Persistimos el ámbito pasado como parámetro
	 * @param pasarelaChasisDto Tipo de pasarela a persistir
	 * @return Ámbito persistido
	 */
	AmbitoRecursoDto save(final AmbitoRecursoDto ambitoRecursoDto);
		
	/**
	 * Actualizamos el tipo de pasarela pasado como parámetro
	 * @param ambitoRecursoDto Ámbito a actualizar
	 * @return Ámbito actualizado
	 */
	AmbitoRecursoDto update(final AmbitoRecursoDto ambitoRecursoDto);

}
