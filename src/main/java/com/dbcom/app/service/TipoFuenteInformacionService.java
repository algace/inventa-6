package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoFuenteInformacionDto;
import com.dbcom.app.model.dto.TipoFuenteInformacionLiteDto;

/**
 * Lógica para tipos de fuente de información
 * 
 * @author jose.vallve
 */
public interface TipoFuenteInformacionService {

	/**
	 * Creamos un tipo de fuente de información sin persistencia
	 * @return Nuevo tipo de fuente de información
	 */
	TipoFuenteInformacionDto create();

	/**
	 * Borrado del tipo de fuente de información con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de fuente de información
	 * @return Listado
	 */
	List<TipoFuenteInformacionLiteDto> readAll();
	
	/**
	 * Obtenemos el tipo de fuente de información con el id facilitado
	 * @param id Identificador
	 * @return Tipo de fuente de información
	 */
	TipoFuenteInformacionDto read(final Short id);
	
	/**
	 * Persistimos el tipo de fuente de información pasado como parámetro
	 * @param tipoFuenteInformacionDto Tipo de fuente de información a persistir
	 * @return Tipo de fuente de información persistido
	 */
	TipoFuenteInformacionDto saveUpdate(final TipoFuenteInformacionDto tipoFuenteInformacionDto);
	
	/**
	 * Obtenemos un listado de los tipos de fuentes de información teniendo en cuenta el tipo de fuente por defecto
	 * que se recibirá como parámetro
	 * Si el tipo de fuente por defecto existe en BBDD se debe poner el primero de la lista. Si no existe en BBDD
	 * se deberá poner como primer elemento de la lista un tipo de fuente de información con sus campos vacíos
	 * @return Listado
	 */
	List<TipoFuenteInformacionLiteDto> getTiposFuenteInformacionConValorPorDefecto(String nombreFuenteInformacion);
	
}
