package com.dbcom.app.service;

import java.util.List;

import com.dbcom.app.model.dto.AplicacionSWDto;
import com.dbcom.app.model.dto.AplicacionSWLiteDto;
import com.dbcom.app.model.dto.EquipamientoLiteDto;
import com.dbcom.app.model.dto.VersionSWLiteDto;

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
	AplicacionSWDto saveUpdate(final AplicacionSWDto aplicacionSWDto);
	
	/**
	 * métodos internos para tratar las listas de Versiones de manera correcta según hayan sido seleccionadas o no.
	 */
	
	List<VersionSWLiteDto> listVersionesSeleccionadas(List<VersionSWLiteDto> allVersiones, List<VersionSWLiteDto> listVersion);
    
    List<VersionSWLiteDto> listVersionesNoSeleccionadas(List<VersionSWLiteDto> allVersiones, List<VersionSWLiteDto> versionesSeleccionadas);
	
	/**
	 * métodos internos para tratar las listas de Equipamientos de manera correcta según hayan sido seleccionados o no.
	 */
	
	List<EquipamientoLiteDto> listEquipamientosSeleccionados(List<EquipamientoLiteDto> allEquipamientos, List<EquipamientoLiteDto> listEquipamientos);
    
    List<EquipamientoLiteDto> listEquipamientosNoSeleccionados(List<EquipamientoLiteDto> allEquipamientos, List<EquipamientoLiteDto> equipamientosSeleccionados);
	
}
