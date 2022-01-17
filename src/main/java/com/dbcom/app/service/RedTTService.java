package com.dbcom.app.service;

import java.util.List;
import java.util.Optional;

import com.dbcom.app.model.dto.FotografiaDto;
import com.dbcom.app.model.dto.RedTTDto;
import com.dbcom.app.model.dto.RedTTLiteDto;

/**
 * Lógica para aplicaciones
 * 
 * @author neoris
 */
public interface RedTTService {

	/**
	 * Creamos una red T/T sin persistencia
	 * @return Nueva red T/T
	 */
	RedTTDto create();

	/**
	 * Borrado de la red T/T con el id facilitado
	 * @param id Identificador
	 */
	void delete(final Long id);

	/**
	 * Obtenemos un listado de las redes T/T
	 * @return Listado
	 */
	List<RedTTLiteDto> readAll();
	
	/**
	 * Obtenemos la red T/T con el id facilitado
	 * @param id Identificador
	 * @return Red T/T
	 */
	RedTTDto read(final Long id);
	
	/**
	 * Persistimos la red T/T pasada como parámetro
	 * @param redTTDto Red T/T a persistir
	 * @return Red T/T persistida
	 */
	RedTTDto save(final RedTTDto redTTDto);
	
	RedTTDto update(final RedTTDto redTTDto);
	
	void setAllAttributesRedTTDto(final RedTTDto redTTDto);
	
	Optional<FotografiaDto> getFotografia(Long idFotografia);
	
	Optional<Long> insertFotografia(Long idRedTT, FotografiaDto fotografiaDto);
	
	Long deleteFotografia(Long idFotografia);
	
}
