package com.dbcom.app.service;

import java.util.List;
import java.util.Optional;

import com.dbcom.app.model.dto.DocumentoDto;
import com.dbcom.app.model.dto.EquipamientoDto;
import com.dbcom.app.model.dto.EquipamientoLiteDto;
import com.dbcom.app.model.dto.FotografiaDto;


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
	 * Informa todas las listas y objetos para la recaraga de la vista
	 * @param equipamientoDto Equipamiento a persistir
	 * @return EquipamientoDto persistido
	 */
	void setAllAttributesEquipamientoDto(final EquipamientoDto equipamientoDto);
	
	/**
	 * Persistimos el sector ATCpasada como parámetro
	 * @param sectorATCDto Sector ATC a persistir
	 * @return Sector ATC persistido
	 */
	EquipamientoDto save(final EquipamientoDto equipamientoDto);
	
	/**
	 * Persistimos el sector ATCpasada como parámetro
	 * @param sectorATCDto Sector ATC a persistir
	 * @return Sector ATC persistido
	 */
	EquipamientoDto update(final EquipamientoDto equipamientoDto);
	
	
	Optional<DocumentoDto> getDocumento(Long idDocumento);
	
	Optional<Long> insertDocumento(Long idEquipamiento, DocumentoDto documentoDto);
	
	Long deleteDocumento(Long idDocumento);
	
	Optional<FotografiaDto> getFotografia(Long idFotografia);
	
	Optional<Long> insertFotografia(Long idEquipamiento, FotografiaDto fotografiaDto);
	
	Long deleteFotografia(Long idFotografia);
		
}
