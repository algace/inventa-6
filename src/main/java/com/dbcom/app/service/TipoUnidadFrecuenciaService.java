package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.TipoUnidadFrecuenciaDto;

public interface TipoUnidadFrecuenciaService extends GenericService<TipoUnidadFrecuenciaDto, Short> {

	/**
	 * Creamos un tipo de unidad de frecuencia sin persistencia
	 * @return Nuevo tipo de unidad de frecuencia
	 */
	TipoUnidadFrecuenciaDto create();

	/**
	 * Borrado del tipo de unidad de frecuencia con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Short id);

	/**
	 * Obtenemos un listado de los tipos de unidades de frecuencia
	 * @return Listado
	 */
	List<TipoUnidadFrecuenciaDto> readAll();
	
	/**
	 * Obtenemos el tipo de unidad de frecuencia con el id facilitado
	 * @param id Identificador
	 * @return Tipo de unidad de frecuencia
	 */
	TipoUnidadFrecuenciaDto read(final Short id);
	
	/**
	 * Persistimos el tipo de unidad de frecuencia pasado como parámetro
	 * @param tipoPolarizacionDto Tipo de unidad de frecuencia a persistir
	 * @return Tipo de unidad de frecuencia persistido
	 */
	TipoUnidadFrecuenciaDto save(final TipoUnidadFrecuenciaDto tipoUnidadFrecuenciaDto);
		
	/**
	 * Actualizamos el tipo de unidad de frecuencia pasado como parámetro
	 * @param tipoPolarizacionDto Tipo de unidad de frecuencia a actualizar
	 * @return Tipo de unidad de frecuencia actualizado
	 */
	TipoUnidadFrecuenciaDto update(final TipoUnidadFrecuenciaDto tipoUnidadFrecuenciaDto);
	
	/**
	 * Obtenemos un listado de los tipos de unidad de frecuencia teniendo en cuenta el tipo de unidad por defecto
	 * Si el tipo de unidad por defecto existe en BBDD se debe poner el primero de la lista. Si no existe en BBDD
	 * se deberá poner como primer elemento de la lista un tipo de unidad de frecuencia con sus campos vacíos
	 * @return Listado
	 */
	List<TipoUnidadFrecuenciaDto> getTipoFuenteInformacionConValorPorDefecto(String nombreUnidadFrecuencia);
	
	/**
	 * Obtenemos un listado de los tipos de unidades de frecuencia teniendo en cuenta el tipo de unidad por defecto
	 * que se recibirá como parámetro
	 * Si el tipo de unidad por defecto existe en BBDD se debe poner el primero de la lista. Si no existe en BBDD
	 * se deberá poner como primer elemento de la lista un tipo de unidad de frecuencia con sus campos vacíos
	 * @return Listado
	 */
	List<TipoUnidadFrecuenciaDto> getTiposUnidadFrecuenciaConValorPorDefecto(String nombreUnidadFrecuencia);
}
