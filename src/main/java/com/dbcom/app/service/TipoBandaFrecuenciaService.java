package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoBandaFrecuenciaDto;

/**
 * Lógica para tipos de bandas de frecuencia
 * 
 * @author jose.vallve
 */
public interface TipoBandaFrecuenciaService extends GenericService<TipoBandaFrecuenciaDto, Short> {

	/**
	 * Creamos un tipo de banda de frecuencia sin persistencia
	 * @return Nuevo tipo de banda de frecuencia
	 */
	TipoBandaFrecuenciaDto create();

	/**
	 * Borrado del tipo de banda de frecuencia con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de bandas de frecuencia
	 * @return Listado
	 */
	List<TipoBandaFrecuenciaDto> readAll();
	
	/**
	 * Obtenemos el tipo de banda de frecuencia con el id facilitado
	 * @param id Identificador
	 * @return Tipo de banda de frecuencia
	 */
	TipoBandaFrecuenciaDto read(final Short id);
	
	/**
	 * Persistimos el tipo de banda de frecuencia pasado como parámetro
	 * @param tipoBandaFrecuenciaDto Tipo de banda de frecuencia a persistir
	 * @return Tipo de banda de frecuencia persistido
	 */
	TipoBandaFrecuenciaDto save(final TipoBandaFrecuenciaDto tipoBandaFrecuenciaDto);
		
	/**
	 * Actualizamos el tipo de bandas de frecuencia pasado como parámetro
	 * @param tipoBandaFrecuenciaDto Tipo de banda de frecuencia a actualizar
	 * @return Tipo de banda de frecuencia actualizado
	 */
	TipoBandaFrecuenciaDto update(final TipoBandaFrecuenciaDto tipoBandaFrecuenciaDto);
	
}
