package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.AplicacionSWDto;
import com.dbcom.app.model.dto.AplicacionSWLiteDto;

/**
 * Lógica para aplicaciones
 * 
 * @author jose.vallve
 */
public interface AplicacionSWService {

	/**
	 * Creamos una aplicación sin persistencia
	 * @return Nueva aplicación
	 */
	AplicacionSWDto create();

	/**
	 * Borrado de la aplicación con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);

	/**
	 * Obtenemos un listado de las aplicaciones
	 * @return Listado
	 */
	List<AplicacionSWLiteDto> readAll();
	
	/**
	 * Obtenemos la aplicación con el id facilitado
	 * @param id Identificador
	 * @return Aplicación
	 */
	AplicacionSWDto read(final Long id);
	
	/**
	 * Persistimos la aplicación pasada como parámetro
	 * @param aplicacionSWDto Aplicación a persistir
	 * @return Aplicación persistida
	 */
	AplicacionSWDto save(final AplicacionSWDto aplicacionSWDto);
	
	/**
	 * Persistimos la aplicación pasada como parámetro
	 * @param aplicacionSWDto Aplicación a persistir
	 * @return Aplicación persistida
	 */
	AplicacionSWDto update(final AplicacionSWDto aplicacionSWDto);
	
	Long insertVersionSW(Long idAplicacionSW, Long idVersionSW);
	
	Long deleteVersionSW(Long idAplicacionSW, Long idVersionSW);
	
	Long insertEquipamiento(Long idAplicacionSW, Long idEquipamiento);
	
	Long deleteEquipamiento(Long idAplicacionSW, Long idEquipamiento);
	
	void setAllAttributesListEquipamientoDto(AplicacionSWDto aplicacionDto);
	
}
